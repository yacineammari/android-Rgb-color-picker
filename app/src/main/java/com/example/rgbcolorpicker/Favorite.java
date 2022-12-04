package com.example.rgbcolorpicker;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Favorite extends AppCompatActivity implements adapter.onitemclick, PopupMenu.OnMenuItemClickListener {

    private static final String TABLE_NAME = "Favorite";
    private static final String DATABASENAME = "COLOR.db";
    private static final String COL1 = "ID";
    private static final String COL2 = "rgb";
    private static final String COL3 = "hex";
    private static final String COL4 = "hsv";

    private List<color_item> list_of_fav_color;
    public final String Text_File_NAME = "Favorite";
    private RecyclerView recyclerView;
    private Cursor mCursor;
    SQLiteDatabase db;
    DatabaseHelper helper;
    adapter itemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        helper = new DatabaseHelper(this);
        db = helper.getWritableDatabase();
        mCursor = getAllItems();

        list_of_fav_color = new ArrayList<>();
        list_of_fav_color = load_my_color(mCursor);

        recyclerView = findViewById(R.id.rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Favorite.this);
        itemAdapter = new adapter(list_of_fav_color, this);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(layoutManager);
        registerForContextMenu(recyclerView);
    }


    public List<color_item> load_my_color(Cursor mCursor) {
        mCursor.moveToFirst();

        while (mCursor.isAfterLast() == false) {
            long id = mCursor.getLong(mCursor.getColumnIndex(COL1));
            String rgb = mCursor.getString(mCursor.getColumnIndex(COL2));
            String hex = mCursor.getString(mCursor.getColumnIndex(COL3));
            String hsv = mCursor.getString(mCursor.getColumnIndex(COL4));
            color_item color = new color_item(rgb, hex, hsv);
            color.id=id;
            list_of_fav_color.add(color);
            mCursor.moveToNext();

        }


        return list_of_fav_color;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        list_of_fav_color.clear();
        list_of_fav_color.removeAll(list_of_fav_color);
        this.finish();
    }

    private Cursor getAllItems() {
        return db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }


    int postion;

    @Override
    public void onitemclick(int pos) {
        Showpopup(recyclerView.findViewHolderForAdapterPosition(pos).itemView, pos);


    }

    public void Showpopup(View v, int pos) {
        PopupMenu popupMenu = new PopupMenu(this, v, Gravity.CENTER);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.item_menu);
        popupMenu.show();
        postion = pos;
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.copy_rgb: {
                copy(list_of_fav_color.get(postion).getHsv());
                Toast.makeText(getApplicationContext(), "copy rgb "+list_of_fav_color.get(postion).getHsv(), Toast.LENGTH_SHORT).show();
                return true;
            }


            case R.id.copy_hex: {
                copy(list_of_fav_color.get(postion).getHex());
                Toast.makeText(getApplicationContext(), "copy hex "+list_of_fav_color.get(postion).getHex(), Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.copy_hsv: {
                copy(list_of_fav_color.get(postion).getRgb());
                Toast.makeText(getApplicationContext(), "copy hex "+list_of_fav_color.get(postion).getRgb(), Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.delete_color: {
                db.delete(TABLE_NAME,COL1 + "=" + list_of_fav_color.get(postion).id,null);
                mCursor.close();
                mCursor=getAllItems();
                list_of_fav_color.remove(postion);
                itemAdapter.notifyItemRemoved(postion);
                Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_SHORT).show();
                return true;
            }
            default:
                return false;
        }

    }



    public void copy(String text) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("color", "" + text);
        clipboardManager.setPrimaryClip(clipData);
        clipData.getDescription();
    }
}
