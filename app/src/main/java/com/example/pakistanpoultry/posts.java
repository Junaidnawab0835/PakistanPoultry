package com.example.pakistanpoultry;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.Adapters.postsADapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class posts extends AppCompatActivity {

    ListView posts;
    ProgressDialog Pb;
    String Type="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        Pb=new ProgressDialog(this);
        posts=findViewById(R.id.posts);


        Type=getIntent().getExtras().getString("Type");
         getData(Type);



    }

    private void convertUrltoBitmap(final ArrayList<HashMap<String, String>> data)
    {


        final ArrayList<HashMap<String,Object>> toReturn=new ArrayList<>();
        new Thread()
        {
            @Override
            public void run()
            {
                URL url = null;
                try
                {
                    for (HashMap<String,String> val:data)
                    {

                        try
                        {

                            HashMap<String,Object> temp=new HashMap<>();
                            url = new URL(val.get("URL"));
                            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            temp.put("TITLE",val.get("TITLE"));
                            temp.put("DATE",val.get("Date"));
                            temp.put("IMAGE",image);
                            toReturn.add(temp);

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run()
                        {
                            if(Pb.isShowing())
                                Pb.dismiss();

                            posts.setAdapter(new postsADapter(posts.this,toReturn));

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private  void getData(String type)
    {
        Pb.show();
        final ArrayList<HashMap<String,String>> values=new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Posts").child(type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
              for(DataSnapshot data:dataSnapshot.getChildren())
              {
                  HashMap<String,String> toPut=new HashMap<>();
                  toPut.put("TITLE",String.valueOf(data.child("Title").getValue()));
                  toPut.put("URL",String.valueOf(data.child("Image_URL").getValue()));
                  toPut.put("Date",String.valueOf(data.child("Date").getValue()));
                  values.add(toPut);
              }

              FirebaseDatabase.getInstance().getReference("Posts").removeEventListener(this);
                if(values.size()<1)
                {
                    posts.setAdapter(new ArrayAdapter<>(posts.this,R.layout.support_simple_spinner_dropdown_item,new String[]{"No Data To Show"}));
                    Pb.dismiss();
                }
                else
                {
                    convertUrltoBitmap(values);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                FirebaseDatabase.getInstance().getReference("Posts").removeEventListener(this);
                Pb.dismiss();
                if(values.size()<1)
                {
                    posts.setAdapter(new ArrayAdapter<>(posts.this,R.layout.support_simple_spinner_dropdown_item,new String[]{"No Data To Show"}));
                    Pb.dismiss();
                }
                else
                {
                    convertUrltoBitmap(values);
                }
            }
        });

    }


}
