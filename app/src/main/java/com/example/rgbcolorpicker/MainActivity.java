package com.example.rgbcolorpicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;



import java.text.DecimalFormat;


import static android.graphics.Color.RGBToHSV;
import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;




public class MainActivity extends AppCompatActivity implements ColorPicker.OnColorChangedListener, ValueBar.OnValueChangedListener, SaturationBar.OnSaturationChangedListener, View.OnLongClickListener {
    TextView current_color;
    ColorPicker picker;
    SaturationBar saturationBar;
    ValueBar valueBar;
    Button fave;
    String current_rgb;
    String current_hex;
    String current_hsv;
    RadioGroup color_type;
    RadioButton color_type_slected;
    DecimalFormat df = new DecimalFormat();

    DecimalFormat df2 = new DecimalFormat("#.####");
    SQLiteDatabase db;
    DatabaseHelper helper;



    private static final String TABLE_NAME = "Favorite";
    private static final String DATABASENAME = "COLOR.db";
    private static final String COL1 = "ID";
    private static final String COL2 = "rgb";
    private static final String COL3 = "hex";
    private static final String COL4 = "hsv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper=new DatabaseHelper(this);
        db=helper.getWritableDatabase();

        picker = (ColorPicker) findViewById(R.id.picker);
        current_color = findViewById(R.id.color);
        picker.setShowOldCenterColor(false);
        picker.setOnColorChangedListener(this);
        saturationBar = (SaturationBar) findViewById(R.id.saturationbar);
        valueBar = (ValueBar) findViewById(R.id.valuebar);
        picker.addSaturationBar(saturationBar);
        picker.addValueBar(valueBar);
        valueBar.setOnValueChangedListener(this);
        saturationBar.setOnSaturationChangedListener(this);
        picker.setOnLongClickListener(this);
        fave = findViewById(R.id.fav);
        df.setMaximumFractionDigits(3);

        fave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                save_to_text(current_rgb , current_hex ,current_hsv);
            }
        });

    }



    public void save_to_text(String rgb,String hex,String hsv) {

        ContentValues contentValues=new ContentValues();

        contentValues.put(COL2,rgb);
        contentValues.put(COL3,hex);
        contentValues.put(COL4,hsv);
        db.insert(TABLE_NAME,null,contentValues);
        Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();


    }







    public void copy(View view) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("color", "" + current_color.getText().toString().trim());
        clipboardManager.setPrimaryClip(clipData);
        clipData.getDescription();
        Toast.makeText(getApplicationContext(), "color copied", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onColorChanged(int color) {
        color_type = findViewById(R.id.color_type);
        int type_chois = color_type.getCheckedRadioButtonId();
        color_type_slected = findViewById(type_chois);

        current_rgb = "RGB(" + red(color) + "," + green(color) + "," + blue(color) + ")";
        current_hex = String.format("#%02X%02X%02X", red(color), green(color), blue(color));

        float[] hsv = new float[3];
        RGBToHSV(red(color), green(color), blue(color), hsv);
        current_hsv = "HSV( " + df.format(hsv[0]) + " , " + df.format(hsv[1]) + " , " + df.format(hsv[2]) + ")";


        switch (color_type_slected.getId()) {
            case (R.id.rgb): {
                current_color.setText(current_rgb);
                break;
            }
            case (R.id.hsv): {

                current_color.setText(current_hsv);
                break;
            }
            case (R.id.hex): {
                current_color.setText(current_hex);
                break;
            }
        }


    }

    @Override
    public void onValueChanged(int value) {
        picker.setColor(value);


    }

    @Override
    public void onSaturationChanged(int saturation) {
        picker.setColor(saturation);

    }


    @Override
    public boolean onLongClick(View view) {
        Toast.makeText(getApplicationContext(), "long Click", Toast.LENGTH_SHORT).show();
        return true;
    }


    public void change(View view) {
        onColorChanged(picker.getColor());
    }




    public void go_convert(View view) {
        Intent i = new Intent(MainActivity.this, rand_color.class);
        startActivity(i);
    }

    public void go_fave(View view) {
        Intent i = new Intent(MainActivity.this, Favorite.class);
        startActivity(i);
    }
}
