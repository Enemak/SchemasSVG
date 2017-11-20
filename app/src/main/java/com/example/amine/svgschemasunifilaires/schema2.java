package com.example.amine.svgschemasunifilaires;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;

public class schema2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schema2);



        try {
            SVG svg = SVG.getFromResource(this, R.raw.exemple2);

            SVGImageView svgImageView = new SVGImageView(this);
            svgImageView.setSVG(svg);
            svgImageView.setLayoutParams(
                    new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));

            RelativeLayout layout =
                    (RelativeLayout) findViewById(R.id.layout_card);

            layout.addView(svgImageView);

        } catch (SVGParseException e){
            e.printStackTrace();
        }



    }
}
