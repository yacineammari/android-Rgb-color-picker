package com.example.rgbcolorpicker;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import android.widget.Toast;

import java.text.DecimalFormat;

import static android.graphics.Color.HSVToColor;
import static android.graphics.Color.RGBToHSV;
import static android.graphics.Color.blue;
import static android.graphics.Color.colorToHSV;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

public class convert {





    public String rgb2rgb(int r, int g, int b) {
        return "RGB(" + r + "," + g + "," + b + ")";
    }

    public String rgb2hex(int r, int g, int b) {

        return String.format("#%02X%02X%02X", r, g, b);

    }

    public static String rgb2hsv(int r, int g, int b) {

        float[] hsv = new float[3];
        RGBToHSV(r, g, b, hsv);
        return "HSV( " + hsv[0] + " , " + hsv[1] + " , " + hsv[2] + ")";
    }

    public String hex2hex(String my_color) {
        return my_color;
    }

    public static String hex2rgb(String my_color) {

        int color = Color.parseColor(my_color);
        System.out.println(color);
        int r = red(color);
        int g = green(color);
        int b = blue(color);
        return "RGB(" + r + "," + g + "," + b + ")";


    }


    public static String hex2hsv(String my_color) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);

        String color = hex2rgb(my_color);
        String[] color2;
        color2 = new String[3];
        color2 = color.split(",");
        color2[0].replace("RGB(", "");
        color2[2] = color2[2].replace(color2[2], color2[2].substring(0, color2[2].length() - 1));


        return rgb2hsv(Integer.parseInt(color2[1]), Integer.parseInt(color2[1]), Integer.parseInt(color2[2]));


    }

    public String hsv2hsv(float h, float s, float v) {
        return "HSV( " + h + " , " + s + " , " + v + ")";
    }

    public String hsv2rgb(float h, float s, float v) {

        float[] my_color = new float[3];
        my_color[0] = h;
        my_color[1] = s;
        my_color[2] = v;
        int x = HSVToColor(my_color);

        return "RGB(" + red(x) + "," + green(x) + "," + blue(x) + ")";
    }

    public String hsv2hex(float h, float s, float v) {

        String color = hsv2rgb(h, s, v);
        String[] color2;
        color2 = new String[3];
        color2 = color.split(",");
        color2[0].replace("RGB(", "");
        color2[2] = color2[2].replace(color2[2], color2[2].substring(0, color2[2].length() - 1));


        return rgb2hex(Integer.parseInt(color2[1]), Integer.parseInt(color2[1]), Integer.parseInt(color2[2]));
    }




    public boolean rgb_val_correct(int r, int g, int b) {
        return (r >= 0 && r <= 255) && (g >= 0 && g <= 255) && (b >= 0 && b <= 255);

    }
    public boolean hsv_val_correct(float h, float s, float v) {
        return (h >= 0 && h <= 360) && (s >= 0 && s <= 1) && (v >= 0 && v <= 1);

    }



}

