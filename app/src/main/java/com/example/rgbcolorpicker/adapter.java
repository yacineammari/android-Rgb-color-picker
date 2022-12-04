package com.example.rgbcolorpicker;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.adapterViewHolder> {
    public List<color_item> my_list_of_fav;
    public onitemclick onitemclick;

    public interface onitemclick {
        public void onitemclick(int pos);
    }

    public boolean is_dark(String hex_cod) {
        int color = Color.parseColor(hex_cod);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;

        return r * 0.299 + g * 0.587 + b * 0.114 > 186;
    }

    public adapter(List<color_item> my_list_of_fav, onitemclick onitemclick) {
        this.my_list_of_fav = my_list_of_fav;
        this.onitemclick = onitemclick;
    }


    @NonNull
    @Override
    public adapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new adapterViewHolder(view, this.onitemclick);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterViewHolder holder, int position) {
        color_item create_color = my_list_of_fav.get(position);
        holder.rgb.setText(create_color.getRgb());
        holder.hex.setText(create_color.getHex());
        holder.hsv.setText(create_color.getHsv());
        holder.color.setBackgroundColor(Color.parseColor(create_color.getHex()));

        if (is_dark(create_color.getHex())) {
            holder.rgb.setTextColor(Color.parseColor("#000000"));
            holder.hex.setTextColor(Color.parseColor("#000000"));
            holder.hsv.setTextColor(Color.parseColor("#000000"));
        }
        else
            {
                holder.rgb.setTextColor(Color.parseColor("#FFFFFF"));
                holder.hex.setTextColor(Color.parseColor("#FFFFFF"));
                holder.hsv.setTextColor(Color.parseColor("#FFFFFF"));
            }

    }

    @Override
    public int getItemCount() {
        return my_list_of_fav.size();
    }

    public class adapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView rgb;
        public TextView hex;
        public TextView hsv;
        public LinearLayout color;
        public onitemclick onitemclick;

        public adapterViewHolder(@NonNull final View itemView, onitemclick onitemclick) {
            super(itemView);
            rgb = itemView.findViewById(R.id.rgb_tv);
            hex = itemView.findViewById(R.id.hex_tv);
            hsv = itemView.findViewById(R.id.hsv_tv);
            color = itemView.findViewById(R.id.bg);
            this.onitemclick = onitemclick;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onitemclick.onitemclick(getAdapterPosition());
        }
    }

}
