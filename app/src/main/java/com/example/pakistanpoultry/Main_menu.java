package com.example.pakistanpoultry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class Main_menu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setonclicklisteners();
    }

    private void setonclicklisteners()
    {


        for( int id: new int[]{R.id.marketPrice_cardview,R.id.events,R.id.news,R.id.tips,R.id.contact_us,R.id.comapny,R.id.fcr_calculator,R.id.poultry_points})
        {
            findViewById(id).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {

            case R.id.marketPrice_cardview:
                startActivity(new Intent(this,market_price.class));
                break;
            case R.id.events:
                startActivity(new Intent(this,posts.class).putExtra("Type","Event"));
                break;
            case R.id.tips:
                startActivity(new Intent(this,posts.class).putExtra("Type","Tips"));
                break;
            case R.id.news:
                startActivity(new Intent(this,posts.class).putExtra("Type","News"));
                break;
            case R.id.contact_us:
                startActivity(new Intent(this,feedback.class));
                break;
            case R.id.comapny:
                startActivity(new Intent(this,company.class));
                break;
            case R.id.fcr_calculator:
                startActivity(new Intent(this,fcr_calculator.class));
                break;
            case R.id.poultry_points:
                Toast.makeText(this,"Coming Soon",Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
