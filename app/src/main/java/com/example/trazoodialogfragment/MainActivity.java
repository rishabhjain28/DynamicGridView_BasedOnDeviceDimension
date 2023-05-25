package com.example.trazoodialogfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout parentLayout = findViewById(R.id.parent_layout);
        int maxColumns = calculateMaxColumns(); //3;

        String[] textArray = {"1", "22", "Short Text", "Medium Text Length","err", "Longer Text Length", "ab", "cd",    "Very Long Text Length", "Short Text", "Medium Text Length", "Longer Text Length"};
        int length = textArray.length;
        boolean[] isSelected = new boolean[length];
        int currentRow = 0;
        int currentColumn = 0;
        int totalColumns = 0;
        int padding = getResources().getDimensionPixelSize(R.dimen.textview_padding);
        int margin = getResources().getDimensionPixelSize(R.dimen.textview_margin);

        for(int i = 0;i<length;i++) {
            String text = textArray[i];

            TextView textView = new TextView(this);
            textView.setText(text);
            textView.setTextColor(getResources().getColor(android.R.color.black));
            textView.setTextSize(12);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(padding, padding, padding, padding);
            textView.setBackgroundResource(R.drawable.oval_background);

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
            int numColumns = calculateNumColumns(text);
            if (currentColumn + numColumns > maxColumns) {
                currentRow++;
                currentColumn = 0;
            }

            layoutParams.columnSpec = GridLayout.spec(currentColumn, numColumns, GridLayout.FILL, 1f);
            layoutParams.rowSpec = GridLayout.spec(currentRow);
            layoutParams.rowSpec = GridLayout.spec(currentRow);
            layoutParams.setMargins(margin,margin,margin,margin);
            parentLayout.addView(textView, layoutParams);

            int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Clicked: " + text, Toast.LENGTH_SHORT).show();

                    if(isSelected[finalI] == false) {
                        isSelected[finalI] = true;
                        textView.setBackgroundResource(R.drawable.oval_background_onclick);
                    } else {
                        isSelected[finalI] = false;
                        textView.setBackgroundResource(R.drawable.oval_background);
                    }

                }
            });

        totalColumns = Math.max(totalColumns, currentColumn + numColumns);
        currentColumn += numColumns;

        }
    }

    private int calculateNumColumns(String text) {
        Paint paint = new Paint();
        paint.setTextSize(getResources().getDimension(R.dimen.text_size)); // Set the desired text size
        float textWidth = paint.measureText(text);
        int itemWidth = getResources().getDimensionPixelSize(R.dimen.item_width); // Set the desired width of each item (TextView)
        int margin = getResources().getDimensionPixelSize(R.dimen.textview_margin); // Set the desired margin size
        return (int) Math.ceil((textWidth + 2 * margin) / (itemWidth + margin));
    }


  private int calculateMaxColumns() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels; // Get the screen width
        int itemWidth = getResources().getDimensionPixelSize(R.dimen.item_width); // Set the desired width of each item (TextView)
        int margin = getResources().getDimensionPixelSize(R.dimen.textview_margin); // Set the desired margin size
        //Log.d("max_column", " --> " + (screenWidth - margin) / (itemWidth + margin));
        return (screenWidth + margin) / (itemWidth + margin);
    }

}