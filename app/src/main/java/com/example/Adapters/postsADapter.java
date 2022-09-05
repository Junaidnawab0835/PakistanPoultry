package com.example.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pakistanpoultry.R;

import java.util.ArrayList;
import java.util.HashMap;

public class postsADapter  extends ArrayAdapter {
    ArrayList<HashMap<String,Object>> posts;
    public postsADapter(Context context, ArrayList<HashMap<String,Object>>  posts)
    {
        super(context, R.layout.customposts);
        this.posts=posts;
    }
    @Override
    public int getCount() {
        return posts.size();
    }
    @Override
    public int getViewTypeCount() {

        return getCount();
    }
    @Override
    public int getItemViewType(int position)
    {
        return position;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder")
        View row = inflater.inflate(R.layout.customposts, parent, false);

        try
        {
            HashMap<String, Object> data = posts.get(position);

            TextView Title=row.findViewById(R.id.postsTitle);
            TextView Date=row.findViewById(R.id.postsDate);
            ImageView img=row.findViewById(R.id.postsImg);

            Title.setText(String.valueOf(data.get("TITLE")));
            Date.setText(String.valueOf(data.get("DATE")));
            Bitmap bitMapimg= (Bitmap) data.get("IMAGE");
            if(bitMapimg!=null)
                img.setImageBitmap(bitMapimg);
            else
                img.setImageDrawable(row.getResources().getDrawable(R.drawable.noimg));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return row;
    }
}
