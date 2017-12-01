package com.example.amine.svgschemasunifilaires;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import uk.co.senab.photoview.PhotoViewAttacher;

public class schema2 extends AppCompatActivity {

    PhotoViewAttacher pAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schema2);


        LoadURITask LoadURITask = new LoadURITask();
        try {

            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/7/78/ElectronicOscillator_Clapp-JFET-D.svg");
            LoadURITask.execute(url);

        } catch (MalformedURLException e) {

            Toast.makeText(getApplicationContext(), getString(R.string.malformed_url), Toast.LENGTH_LONG).show();
        }
    }

    private class LoadURITask extends AsyncTask<URL, Integer, SVG> {

        public LoadURITask() {}

        protected SVG doInBackground(URL... urls) {
            URL url = urls[0];
            InputStream is = null;

            try {
                is = url.openConnection().getInputStream();
                SVG svg = SVG.getFromInputStream(is);
                return svg;
            } catch (SVGParseException e) {
                Log.e("SVGImageView", "Parse error loading URI: " + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) { /* do nothing */ }
            }
            return null;
        }

        protected void onPostExecute(SVG svg) {

            if (svg != null) {
                SVGImageView svgImageView = new SVGImageView(getApplicationContext());
                pAttacher = new PhotoViewAttacher(svgImageView);
                pAttacher.update();
                svgImageView.setSVG(svg);
                svgImageView.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));

                RelativeLayout layout =
                        (RelativeLayout) findViewById(R.id.layout_card);

                layout.addView(svgImageView);

            }
        }
    }

}