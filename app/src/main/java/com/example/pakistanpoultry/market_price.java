package com.example.pakistanpoultry;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

public class market_price extends AppCompatActivity{
     Spinner citiesList;
     ListView RatesList;
     ImageView date_btn;
     TextView date;
    int m_day=0;
    int m_month=0;
    int m_year=0;
    Calendar calendar;
    boolean activityRunning=true;
    String donTRemin="DontRemind";
    String saveCity="City";
    ArrayList<String> citiesArrayList=new ArrayList<>();
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_market_price);
            setIds();
            setAdapterforCities();
       //     getData("");
        }
    private void getData(String query)
    {

        final HashMap<String,String> data=new HashMap<>();
        FirebaseDatabase.getInstance().getReference("MarketPrice").child(query).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                System.out.println("DATA SNAP SHOT :"+dataSnapshot);
                System.out.println("VALUE :"+dataSnapshot.getValue());
                try
                {
                    data.put("BroilerRate",dataSnapshot.child("BroilerRate").getValue().toString());
                    data.put("ChicksRate",dataSnapshot.child("ChicksRate").getValue().toString());
                    data.put("EggsRate",dataSnapshot.child("EggsRate").getValue().toString());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                if(data.size()<1)
                {
                    RatesList.setAdapter(new ArrayAdapter<>(market_price.this,R.layout.support_simple_spinner_dropdown_item,new String[]{"No Data To Show"}));
                }
                else
                    setDataonList(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }
        });
    }
    private void setDataonList(final HashMap<String, String> data)
    {

        RatesList.setAdapter(new BaseAdapter()
        {

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View convertView, ViewGroup viewGroup)
            {

                if (convertView == null)
                {

                    LayoutInflater inflater = LayoutInflater.from(market_price.this);
                    convertView = inflater.inflate(R.layout.custommarketview,viewGroup, false);

                    ImageView img=convertView.findViewById(R.id.custommarket_imageview);
                    TextView rate=convertView.findViewById(R.id.custommarketText);
                    if(i==0) //BROILER
                    {
                        if(!data.get("BroilerRate").equals(""))
                        {
                            img.setImageDrawable(convertView.getResources().getDrawable(R.drawable.hen));
                            rate.setText(String.format("Chicken Alive :  Rs  %s/", data.get("BroilerRate")));
                        }
                        else
                            convertView.setVisibility(View.GONE);
                    }
                    if(i==1) //Chicks
                    {

                        if(!data.get("ChicksRate").equals(""))
                        {
                            img.setImageDrawable(convertView.getResources().getDrawable(R.drawable.chicks));
                            rate.setText(String.format("Chicken Rate  :  Rs  %s/", data.get("ChicksRate")));
                        }
                        else
                        {
                            img.setVisibility(View.GONE);
                            rate.setVisibility(View.GONE);
                        }
                    }
                    if(i==2) //Eggs
                    {

                        if(!data.get("EggsRate").equals(""))
                        {
                            img.setImageDrawable(convertView.getResources().getDrawable(R.drawable.eggspng));
                            rate.setText(String.format("Eggs  Rate :  RPK  %s", data.get("EggsRate")));
                        }
                        else
                            convertView.setVisibility(View.GONE);
                    }

                }
                return convertView;
            }
        });
    }
    private void setAdapterforCities()
    {

        final AlertDialog loading = generalactivities.loading(market_price.this);
        citiesArrayList.add("Select City");
        FirebaseDatabase.getInstance().getReference("General/Cities").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                if(!activityRunning)
                    return;
                if(dataSnapshot.getValue()!=null)
                {
                    String[] cities = String.valueOf(dataSnapshot.getValue()).split(",");
                    citiesArrayList.addAll(Arrays.asList(cities));
                    if(citiesArrayList.size()>=1)
                        citiesList.setAdapter(new ArrayAdapter<>(market_price.this,R.layout.support_simple_spinner_dropdown_item,citiesArrayList));
                    else
                        citiesList.setAdapter(new ArrayAdapter<>(market_price.this,R.layout.support_simple_spinner_dropdown_item,new String[]{"Select City"}));
                    /////// Get Todays Data //

                    getTodaysData();
                }
                else
                    citiesList.setAdapter(new ArrayAdapter<>(market_price.this,R.layout.support_simple_spinner_dropdown_item,new String[]{"Select City"}));
                loading.dismiss();
               // progressBar.setVisibility(View.GONE);
                FirebaseDatabase.getInstance().getReference("General/Cities").removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                FirebaseDatabase.getInstance().getReference("General/Cities").removeEventListener(this);
            }
        });
    }

    private void getTodaysData()
    {
    if(!TextUtils.isEmpty(getPref(saveCity)))
    {
        getData(date.getText() + "_" + getPref(saveCity));
        citiesArrayList.remove(0);
        citiesArrayList.add(0,getPref(saveCity));
        citiesList.setAdapter(new ArrayAdapter<>(market_price.this,R.layout.support_simple_spinner_dropdown_item,citiesArrayList));
    }
    }

    public  void setData(String City,String date)
    {
        if(TextUtils.isEmpty(getPref(saveCity)) && TextUtils.isEmpty(getPref(donTRemin)))
            promptToSaveCity(City,date);
        else
            getData(date+"_"+City);
    }

    @Override
    protected void onDestroy() {
        activityRunning=false;
        super.onDestroy();
    }
    private void setIds()
    {
        RatesList=findViewById(R.id.listdata);
        citiesList=findViewById(R.id.city_spinner);
        date_btn=findViewById(R.id.date_btn);
        date=findViewById(R.id.date_show);
        ////////////// DATE //////////////////
        calendar=Calendar.getInstance();
        m_day=calendar.get(Calendar.DAY_OF_MONTH);
        m_month=calendar.get(Calendar.MONTH);
        m_year=calendar.get(Calendar.YEAR);
        citiesList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(citiesList.getAdapter()==null || citiesList.getAdapter().getItem(i).equals("Select City") || date.getText().equals("Select Date"))
                    return;

               setData(String.valueOf(citiesList.getAdapter().getItem(i)),String.valueOf(date.getText()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //
        date.setText(String.format("%d-%d-%d", m_day, m_month + 1, m_year));
        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(market_price.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker VIew, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(String.format("%d-%d-%d", dayOfMonth, monthOfYear + 1, year));
                    }
                },m_year, m_month, m_day);
                datePickerDialog.show();
            }
        });
    }
    private void promptToSaveCity(final String city,final String date)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(market_price.this);
        alert.setTitle("Save City");
        alert.setMessage("Do You Wants To Set "+city+" As Default City ?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    setPref(saveCity,city);
                getData(date+"_"+city);
            }
        });
        alert.setNeutralButton("Later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getData(date+"_"+city);
            }
        });
        alert.setNegativeButton("Don't Prompt Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                setPref(donTRemin,"True");
                getData(date+"_"+city);
            }
        });
        alert.show();

    }

    public String getPref(String Key)
    {
        SharedPreferences prefs = getSharedPreferences("marketPrice", MODE_PRIVATE);
        String name = prefs.getString(Key, "");//"No name defined" is the default value.
        return  name;
    }
    public  void setPref(String key,String val)
    {
        SharedPreferences.Editor editor = getSharedPreferences("marketPrice", MODE_PRIVATE).edit();
        editor.putString(key, val);
        editor.apply();
    }



}
