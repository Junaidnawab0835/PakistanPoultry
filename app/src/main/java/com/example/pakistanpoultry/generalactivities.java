package com.example.pakistanpoultry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


@SuppressLint("Registered")
public class generalactivities {



    public static boolean hostAvailable() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("www.google.com", 80), 10000);
            ConnectionStatus=true;
            return true;
        } catch (IOException e) {
            // Either we have a timeout or unreachable host or failed DNS lookup
            ConnectionStatus=false;
            System.out.println(e);
            return false;
        }
    }
    static  boolean ConnectionStatus=false;
    public static void promptNoInternetDialog(final Activity ac,final Class secondActivity) {
        try {
            AlertDialog.Builder alert = new AlertDialog.Builder(ac);
            LinearLayout layout = new LinearLayout(ac);
            layout.setOrientation(LinearLayout.VERTICAL);
            final ImageView noInternetImage = new ImageView(ac);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                noInternetImage.setAlpha(0.79f);
            }
            noInternetImage.setImageDrawable(ac.getResources().getDrawable(R.drawable.img_nointernet));
            final TextView Title = new TextView(ac);
            final Button offline = new Button(ac);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 20, 0, 0);
            offline.setLayoutParams(params);
            offline.setTypeface(Typeface.SERIF);
            offline.setText("Use Offline Mode ");
            Title.setPadding(0, 10, 0, 0);
            Title.setText("My Kfueit Is Facing Connectivity Issues Check Your Internet Connection Or Use Offline Mode");
            Title.setTypeface(Typeface.SERIF);
            Title.setTextSize(15f);
            Title.setGravity(Gravity.CENTER);
            layout.addView(noInternetImage);
            layout.addView(Title);
            layout.addView(offline);
            layout.setPadding(30, 20, 30, 20);
            alert.setView(layout);
            alert.setCancelable(false);
            final AlertDialog testDialog = alert.create();
            testDialog.show();
            offline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    testDialog.dismiss();
                    ac.finish();
                    ac.startActivity(new Intent(ac, secondActivity));
                }
            });
        }
        catch (Exception e)
        {
            ac.finish();
            ac.startActivity(new Intent(ac, secondActivity));
        }
    }
    public static void Dialog(final Activity ac, String Message) {

        try {

            AlertDialog.Builder alert = new AlertDialog.Builder(ac);
            LinearLayout layout = new LinearLayout(ac);
            layout.setOrientation(LinearLayout.VERTICAL);
            final TextView Title = new TextView(ac);
            final Button offline = new Button(ac);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 20, 0, 0);
            offline.setLayoutParams(params);
            offline.setTypeface(Typeface.SERIF);
            offline.setText("OK");
            Title.setPadding(0, 10, 0, 0);
            Title.setText(Message);
            Title.setTypeface(Typeface.MONOSPACE);
            Title.setTextSize(13f);
            layout.addView(Title);
            layout.addView(offline);
            layout.setPadding(30, 20, 30, 20);
            alert.setView(layout);
            alert.setCancelable(false);
            final AlertDialog testDialog = alert.create();
            testDialog.show();
            offline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    testDialog.dismiss();
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static AlertDialog loading(final Activity ac) {

        try {
            AlertDialog.Builder alert = new AlertDialog.Builder(ac);
            LinearLayout layout = new LinearLayout(ac);
            layout.setOrientation(LinearLayout.VERTICAL);
            final ProgressBar progress = new ProgressBar(ac);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 20, 0, 0);
            layout.addView(progress);
            layout.setPadding(30, 20, 30, 20);
            layout.setAlpha(0.7f);
            alert.setView(layout);
            alert.setCancelable(false);
            final AlertDialog testDialog = alert.create();
            testDialog.show();
            return testDialog;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void ImageDialog(final Activity ac, String Message,String btnTitle, Drawable img) {

        try {

            AlertDialog.Builder alert = new AlertDialog.Builder(ac);
            LinearLayout layout = new LinearLayout(ac);
            layout.setOrientation(LinearLayout.VERTICAL);
            final ImageView noInternetImage = new ImageView(ac);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                noInternetImage.setAlpha(0.79f);
            }
            if(img!=null)
            noInternetImage.setImageDrawable(img);
            final TextView Title = new TextView(ac);
            final Button offline = new Button(ac);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 20, 0, 0);
            offline.setLayoutParams(params);
            offline.setTypeface(Typeface.SERIF);
            offline.setText(btnTitle);
            Title.setPadding(0, 10, 0, 0);
            Title.setText(Message);
            Title.setGravity(Gravity.CENTER);
            Title.setTypeface(Typeface.MONOSPACE);
            Title.setTextSize(14f);
            layout.addView(noInternetImage);
            layout.addView(Title);
            layout.addView(offline);
            layout.setPadding(30, 20, 30, 20);
            alert.setView(layout);
            alert.setCancelable(false);
            final AlertDialog testDialog = alert.create();
            testDialog.show();
            offline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    testDialog.dismiss();
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
