package com.example.rgbcolorpicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

import com.example.rgbcolorpicker.convert;

public class rand_color extends AppCompatActivity {

    private static final String TABLE_NAME = "Favorite";
    private static final String DATABASENAME = "COLOR.db";
    private static final String COL1 = "ID";
    private static final String COL2 = "rgb";
    private static final String COL3 = "hex";
    private static final String COL4 = "hsv";

    public RelativeLayout r1, r2, r3, r4, r5;
    public TextView i1, i2, i3, i4, i5;
    public ImageView h1, h2, h3, h4, h5;
    public ImageView l1, l2, l3, l4, l5;
    public LinearLayout finale_v;

    boolean lock1, lock2, lock3, lock4, lock5;
    public String color1, color2, color3, color4, color5;

    Button mix;

    SQLiteDatabase db;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rand_color);

        helper = new DatabaseHelper(this);
        db = helper.getWritableDatabase();


        lock1 = false;
        lock2 = false;
        lock3 = false;
        lock4 = false;
        lock5 = false;


        r1=findViewById(R.id.RelativeLayout1);
        r2=findViewById(R.id.RelativeLayout2);
        r3=findViewById(R.id.RelativeLayout3);
        r4=findViewById(R.id.RelativeLayout4);
        r5=findViewById(R.id.RelativeLayout5);

        i1=findViewById(R.id.color1);
        i2=findViewById(R.id.color2);
        i3=findViewById(R.id.color3);
        i4=findViewById(R.id.color4);
        i5=findViewById(R.id.color5);

        mix = findViewById(R.id.mix);

        mix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color_rand();

            }
        });

        h1=findViewById(R.id.heart1);
        h2=findViewById(R.id.heart2);
        h3=findViewById(R.id.heart3);
        h4=findViewById(R.id.heart4);
        h5=findViewById(R.id.heart5);


        l1=findViewById(R.id.lock_ic1);
        l2=findViewById(R.id.lock_ic2);
        l3=findViewById(R.id.lock_ic3);
        l4=findViewById(R.id.lock_ic4);
        l5=findViewById(R.id.lock_ic5);

        finale_v=findViewById(R.id.finale_v);




    }

    @Override
    protected void onStart() {
        super.onStart();
        color_rand();
    }

    public void fav5(View view) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, convert.hex2rgb(color5));
        contentValues.put(COL3, color5);
        contentValues.put(COL4, convert.hex2hsv(color5));

        db.insert(TABLE_NAME, null, contentValues);
        Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
    }

    public void fav4(View view) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, convert.hex2rgb(color4));
        contentValues.put(COL3, color4);
        contentValues.put(COL4, convert.hex2hsv(color4));
        db.insert(TABLE_NAME, null, contentValues);
        Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
    }

    public void fav3(View view) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, convert.hex2rgb(color3));
        contentValues.put(COL3, color3);
        contentValues.put(COL4, convert.hex2hsv(color3));
        db.insert(TABLE_NAME, null, contentValues);
        Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
    }

    public void fav2(View view) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, convert.hex2rgb(color2));
        contentValues.put(COL3, color2);
        contentValues.put(COL4, convert.hex2hsv(color2));
        db.insert(TABLE_NAME, null, contentValues);
        Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
    }

    public void fav1(View view) {


        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, convert.hex2rgb(color1));
        contentValues.put(COL3, color1);
        contentValues.put(COL4, convert.hex2hsv(color1));
        db.insert(TABLE_NAME, null, contentValues);
        Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
    }


    public void color_rand() {
        Random random = new Random();


        if (!lock1) {
            int colo = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            color1 = String.format("#%02X%02X%02X", red(colo), green(colo), blue(colo));
            r1.setBackgroundColor(colo);
            i1.setText(color1);

            if(is_dark(color1))
            {
              i1.setTextColor(Color.parseColor("#000000"));
              l1.setImageResource(R.drawable.ic_unlock_b);
              h1.setImageResource(R.drawable.ic_heart_b);

            }
            else
            {
                i1.setTextColor(Color.parseColor("#FFFFFF"));
                l1.setImageResource(R.drawable.ic_unlock_w);
                h1.setImageResource(R.drawable.ic_heart_w);
            }
        }
        if (!lock2) {
            int colo2 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            color2 = String.format("#%02X%02X%02X", red(colo2), green(colo2), blue(colo2));
            r2.setBackgroundColor(colo2);
            i2.setText(color2);

            if(is_dark(color2))
            {
                i2.setTextColor(Color.parseColor("#000000"));
                l2.setImageResource(R.drawable.ic_unlock_b);
                h2.setImageResource(R.drawable.ic_heart_b);

            }
            else
            {
                i2.setTextColor(Color.parseColor("#FFFFFF"));
                l2.setImageResource(R.drawable.ic_unlock_w);
                h2.setImageResource(R.drawable.ic_heart_w);
            }
        }
        if (!lock3) {
            int colo3 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            color3 = String.format("#%02X%02X%02X", red(colo3), green(colo3), blue(colo3));
            r3.setBackgroundColor(colo3);
            i3.setText(color3);

            if(is_dark(color3))
            {
                i3.setTextColor(Color.parseColor("#000000"));
                l3.setImageResource(R.drawable.ic_unlock_b);
                h3.setImageResource(R.drawable.ic_heart_b);

            }
            else
            {
                i3.setTextColor(Color.parseColor("#FFFFFF"));
                l3.setImageResource(R.drawable.ic_unlock_w);
                h3.setImageResource(R.drawable.ic_heart_w);
            }
        }
        if (!lock4) {
            int colo4 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            color4 = String.format("#%02X%02X%02X", red(colo4), green(colo4), blue(colo4));
            r4.setBackgroundColor(colo4);
            i4.setText(color4);

            if(is_dark(color4))
            {
                i4.setTextColor(Color.parseColor("#000000"));
                l4.setImageResource(R.drawable.ic_unlock_b);
                h4.setImageResource(R.drawable.ic_heart_b);

            }
            else
            {
                i4.setTextColor(Color.parseColor("#FFFFFF"));
                l4.setImageResource(R.drawable.ic_unlock_w);
                h4.setImageResource(R.drawable.ic_heart_w);
            }
        }
        if (!lock5) {
            int colo5 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            color5 = String.format("#%02X%02X%02X", red(colo5), green(colo5), blue(colo5));
            r5.setBackgroundColor(colo5);
            i5.setText(color5);

            if(is_dark(color5))
            {
                i5.setTextColor(Color.parseColor("#000000"));
                l5.setImageResource(R.drawable.ic_unlock_b);
                h5.setImageResource(R.drawable.ic_heart_b);

            }
            else
            {
                i5.setTextColor(Color.parseColor("#FFFFFF"));
                l5.setImageResource(R.drawable.ic_unlock_w);
                h5.setImageResource(R.drawable.ic_heart_w);
            }
        }





    }



    public boolean is_dark(String hex_cod) {
        int color = Color.parseColor(hex_cod);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;

        return r * 0.299 + g * 0.587 + b * 0.114 > 186;
    }

    public void Lock1(View view) {

        if(lock1)
        {
            if(is_dark(color1))
            {
                lock1=false;
                l1.setImageResource(R.drawable.ic_unlock_b);
                Toast.makeText(rand_color.this,"Unlock",Toast.LENGTH_SHORT).show();

            }
            else {
                lock1=false;
                l1.setImageResource(R.drawable.ic_unlock_w);
                Toast.makeText(rand_color.this,"Unlock",Toast.LENGTH_SHORT).show();

            }
        }
        else
         {
             if(is_dark(color1))
             {
                 lock1=true;
                 l1.setImageResource(R.drawable.ic_loack_b);
                 Toast.makeText(rand_color.this,"lock",Toast.LENGTH_SHORT).show();

             }
             else {
                 lock1=true;
                 l1.setImageResource(R.drawable.ic_loack_w);
                 Toast.makeText(rand_color.this,"lock",Toast.LENGTH_SHORT).show();

             }
         }



    }

    public void Lock2(View view) {

        if(lock2)
        {
            if(is_dark(color2))
            {
                lock2=false;
                l2.setImageResource(R.drawable.ic_unlock_b);
                Toast.makeText(rand_color.this,"Unlock",Toast.LENGTH_SHORT).show();

            }
            else {
                lock2=false;
                l2.setImageResource(R.drawable.ic_unlock_w);
                Toast.makeText(rand_color.this,"Unlock",Toast.LENGTH_SHORT).show();

            }
        }
        else
        {
            if(is_dark(color2))
            {
                lock2=true;
                l2.setImageResource(R.drawable.ic_loack_b);
                Toast.makeText(rand_color.this,"lock",Toast.LENGTH_SHORT).show();

            }
            else {
                lock2=true;
                l2.setImageResource(R.drawable.ic_loack_w);
                Toast.makeText(rand_color.this,"lock",Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void Lock3(View view) {


        if(lock3)
        {
            if(is_dark(color3))
            {
                lock3=false;
                l3.setImageResource(R.drawable.ic_unlock_b);
                Toast.makeText(rand_color.this,"Unlock",Toast.LENGTH_SHORT).show();

            }
            else {
                lock3=false;
                l3.setImageResource(R.drawable.ic_unlock_w);
                Toast.makeText(rand_color.this,"Unlock",Toast.LENGTH_SHORT).show();

            }
        }
        else
        {
            if(is_dark(color3))
            {
                lock3=true;
                l3.setImageResource(R.drawable.ic_loack_b);
                Toast.makeText(rand_color.this,"lock",Toast.LENGTH_SHORT).show();

            }
            else {
                lock3=true;
                l3.setImageResource(R.drawable.ic_loack_w);
                Toast.makeText(rand_color.this,"lock",Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void Lock4(View view) {

        if(lock4)
        {
            if(is_dark(color4))
            {
                lock4=false;
                l4.setImageResource(R.drawable.ic_unlock_b);
                Toast.makeText(rand_color.this,"Unlock",Toast.LENGTH_SHORT).show();

            }
            else {
                lock4=false;
                l4.setImageResource(R.drawable.ic_unlock_w);
                Toast.makeText(rand_color.this,"Unlock",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            if(is_dark(color4))
            {
                lock4=true;
                l4.setImageResource(R.drawable.ic_loack_b);
                Toast.makeText(rand_color.this,"lock",Toast.LENGTH_SHORT).show();
            }
            else {
                lock4=true;
                l4.setImageResource(R.drawable.ic_loack_w);
                Toast.makeText(rand_color.this,"lock",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Lock5(View view) {
        if(lock5)
        {
            if(is_dark(color5))
            {
                lock5=false;
                l5.setImageResource(R.drawable.ic_unlock_b);
                Toast.makeText(rand_color.this,"Unlock",Toast.LENGTH_SHORT).show();
            }
            else {
                lock5=false;
                l5.setImageResource(R.drawable.ic_unlock_w);
                Toast.makeText(rand_color.this,"Unlock",Toast.LENGTH_SHORT).show();

            }
        }
        else
        {
            if(is_dark(color5))
            {
                lock5=true;
                l5.setImageResource(R.drawable.ic_loack_b);
                Toast.makeText(rand_color.this,"lock",Toast.LENGTH_SHORT).show();

            }
            else {
                lock5=true;
                l5.setImageResource(R.drawable.ic_loack_w);
                Toast.makeText(rand_color.this,"lock",Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void save_img(View view) throws IOException {




        int code = getApplicationContext().getPackageManager().checkPermission(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                getApplicationContext().getPackageName());
        if (code == PackageManager.PERMISSION_GRANTED) {


            l1.setVisibility(View.INVISIBLE);
            l2.setVisibility(View.INVISIBLE);
            l3.setVisibility(View.INVISIBLE);
            l4.setVisibility(View.INVISIBLE);
            l5.setVisibility(View.INVISIBLE);

            h1.setVisibility(View.INVISIBLE);
            h2.setVisibility(View.INVISIBLE);
            h3.setVisibility(View.INVISIBLE);
            h4.setVisibility(View.INVISIBLE);
            h5.setVisibility(View.INVISIBLE);


            final View v = finale_v; // The view that you want to save as an image

            Random random = new Random();
            int x = random.nextInt(100000000);


            Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bitmap);
            v.draw(c);




            String storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

            FileOutputStream output = new FileOutputStream(storageDir+"/"+x+".png");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.close();

            Toast.makeText(rand_color.this,"path: "+storageDir,Toast.LENGTH_SHORT).show();



            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.VISIBLE);
            l3.setVisibility(View.VISIBLE);
            l4.setVisibility(View.VISIBLE);
            l5.setVisibility(View.VISIBLE);

            h1.setVisibility(View.VISIBLE);
            h2.setVisibility(View.VISIBLE);
            h3.setVisibility(View.VISIBLE);
            h4.setVisibility(View.VISIBLE);
            h5.setVisibility(View.VISIBLE);



        }
        else {

            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED1", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED1", Toast.LENGTH_SHORT).show();
            }
        }

    }
    /*

    public void save_img(View view) throws IOException {

        final View v = big_ll; // The view that you want to save as an image

        Random random = new Random();
        int x = random.nextInt();


        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        v.draw(c);
        FileOutputStream output = new FileOutputStream(Environment.getApplicationContext().getApplicationInfo().dataDir+"/file"+x+".png");
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        output.close();

        Toast.makeText(this, getApplicationContext().getApplicationInfo().dataDir + "/file" + x + ".png", Toast.LENGTH_LONG).show();
        System.out.println(getApplicationContext().getApplicationInfo().dataDir + "/file" + x + ".png");
    }


     */


}