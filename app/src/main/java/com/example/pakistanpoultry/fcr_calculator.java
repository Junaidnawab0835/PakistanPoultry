package com.example.pakistanpoultry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class fcr_calculator extends AppCompatActivity {
    Float cal_result;
    Integer bags;
    Float weight;
    EditText bag_edtxt;
    EditText weight_edtxt;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcr_calculator);
        bag_edtxt=findViewById(R.id.bags_edttext);
        weight_edtxt=findViewById(R.id.weight_edittext);
        result=findViewById(R.id.resulttxt);
        final TextView fcr_status_edttxt=findViewById(R.id.fcr_status_edtxt);
        Button button=findViewById(R.id.calculator_btn);

      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

             validate();
             if(validate()==true) {
                 bags=Integer.parseInt(bag_edtxt.getText().toString());
                 weight = Float.parseFloat(weight_edtxt.getText().toString());
                 cal_result = weight / bags;
                 result.setText(cal_result.toString());
                 if(cal_result<30)
                 {
                     fcr_status_edttxt.setText("BAD");
                 }
                 else if(cal_result>=30&&cal_result<33)
                 {
                     fcr_status_edttxt.setText("AVERAGE");
                 }
                 else
                 {
                     fcr_status_edttxt.setText("GOOD");
                 }
             }

              }



      });



    }



    public boolean validate() {
        boolean valid = true;

        String bags = bag_edtxt.getText().toString();
        String weight = weight_edtxt.getText().toString();

        if (bags.isEmpty()) {
            bag_edtxt.setError("Empty");
            valid = false;
        }

        if (weight.isEmpty()) {
            weight_edtxt.setError("Empty");
            valid = false;
        }


        return valid;
    }
}
