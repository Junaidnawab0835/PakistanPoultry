package com.example.pakistanpoultry;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class company extends AppCompatActivity {
    ListView listView;
    ArrayList<String> list;
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        listView=findViewById(R.id.company_listview);
        Context context=getApplicationContext();
        Resources resources=context.getResources();

        ArrayList<Object [] > values=new ArrayList<>();


        values.add(new Object[]{"Poultry Health/Medicine","Company",getDrawable(R.drawable.poultry_health)});
        values.add(new Object[]{"Feed Production","Company",getDrawable(R.drawable.feed_production)});
        values.add(new Object[]{"Chicks Production/Hatchery","Comapny",getDrawable(R.drawable.chicks_production)});
        values.add(new Object[]{"Equipment and Machinery","Comapny",getDrawable(R.drawable.machinery)});



        adapter = new ListAdapter(company.this,values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView parent, View v, int position, long id){

              Toast.makeText(company.this,position+" ",Toast.LENGTH_SHORT).show();

            }
        });



    }
    public class ListAdapter extends BaseAdapter {

        Context context;
        ArrayList<Object[]> X;
        public ListAdapter(Context context,ArrayList<Object[]> X) {
            this.X=X;
            this.context = context;
        }

        @Override
        public int getCount() {
            return X.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            ViewHolder viewHolder;

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.listview_layout, parent, false);
                viewHolder.txtName = (TextView) convertView.findViewById(R.id.comapany_type_title);
                viewHolder.txtdescription = (TextView) convertView.findViewById(R.id.listview_item_short_description);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.comapany_image);
                result = convertView;
                convertView.setTag(viewHolder);
                try
                {
                    String CompanyName= (String) X.get(position)[0];
                    String Discp= (String) X.get(position)[1];
                    Drawable mypic= (Drawable) X.get(position)[2];

                    viewHolder.txtName.setText(CompanyName);
                    viewHolder.txtdescription.setText(Discp);
                    viewHolder.icon.setImageDrawable(mypic);



                }
                catch (Exception e)
                {
                    System.out.println("ERROR "+e);
                    e.printStackTrace();
                }

            }
            else
                {
                viewHolder = (ViewHolder) convertView.getTag();
                result = convertView;
            }
            return convertView;
        }

        public class ViewHolder {

            public TextView txtdescription;
            TextView txtName;
            ImageView icon;

        }
    }
}
















