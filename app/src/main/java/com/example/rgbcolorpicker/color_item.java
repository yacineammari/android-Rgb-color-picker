package com.example.rgbcolorpicker;


public class color_item {

    public String hsv;

    public String hex;

    public String rgb;
    public long id;

    public color_item(String hsv, String hex, String rgb) {
        this.hsv = hsv;
        this.hex = hex;
        this.rgb = rgb;
    }

    public String getHsv() {
        return hsv;
    }

    public void setHsv(String hsv) {
        this.hsv = hsv;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }
}
