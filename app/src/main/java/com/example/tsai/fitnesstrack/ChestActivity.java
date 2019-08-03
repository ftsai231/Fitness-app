package com.example.tsai.fitnesstrack;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

public class ChestActivity extends AppCompatActivity{

    private Button PopupBarbellBenchpress;
    private Button PopupDumbellBenchpress;
    private Button InclineDumbellFly;
    private Button CableCrossover;
    private Button InclineDumbbellPress;
    private Button MachineBenchPress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chest);

        PopupBarbellBenchpress = (Button) findViewById(R.id.barbenchpress);
        PopupBarbellBenchpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display popup attached to the button as a position anchor
                displayPopupWindow(v, R.layout.barbellbenchpress);
            }
        });

        PopupDumbellBenchpress = findViewById(R.id.dumbbellbenchpress);
        PopupDumbellBenchpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, R.layout.dumbbellbenchpress);
            }
        });

        InclineDumbellFly = findViewById(R.id.inclinedumbbellfly);
        InclineDumbellFly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, R.layout.inclinedumbbellfly);
            }
        });

        CableCrossover = findViewById(R.id.cabelcrossover);
        CableCrossover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, R.layout.cablecrossover);
            }
        });

        InclineDumbbellPress = findViewById(R.id.inclinedumbbellpress);
        InclineDumbbellPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, R.layout.inclinedumbbellpress);
            }
        });

        MachineBenchPress = findViewById(R.id.machinebenchpress);
        MachineBenchPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, R.layout.machinebenchpress);
            }
        });





    }

    private void displayPopupWindow(View anchorView, int lay) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        PopupWindow popup = new PopupWindow();
        View layout = getLayoutInflater().inflate(lay, null);

        popup.setContentView(layout);

        popup.setWidth(width-200);
        popup.setHeight(height-200);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.showAtLocation(layout, Gravity.CENTER,0,0);
        popup.showAsDropDown(anchorView);
    }





}
