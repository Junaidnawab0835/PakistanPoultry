package com.example.pakistanpoultry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback extends AppCompatActivity {
    EditText nametxt;
    EditText phonetxt;
    EditText questiontxt;
    EditText emailtxt;
    Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference info=database.getReference("Feedback");
        nametxt=findViewById(R.id.name_edt);
        phonetxt=findViewById(R.id.number_edt);
        questiontxt=findViewById(R.id.question_edt);
        emailtxt=findViewById(R.id.email_edt);
        submitbtn=findViewById(R.id.submitbutton);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    feedback_modelclass obj = new feedback_modelclass(nametxt.getText().toString(), emailtxt.getText().toString(), phonetxt.getText().toString(), questiontxt.getText().toString());
                    info.child(nametxt.getText().toString()).setValue(obj);
                    Toast.makeText(feedback.this,"FeedBack Submitted Successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    public boolean validate() {
        boolean valid = true;

        if (nametxt.getText().toString().isEmpty()) {
            nametxt.setError("Empty");
            valid = false;
        }
        if (phonetxt.getText().toString().isEmpty()) {
            phonetxt.setError("Empty");
            valid = false;
        }
        if (questiontxt.getText().toString().isEmpty()) {
            questiontxt.setError("Empty");
            valid = false;
        }



        return valid;
    }
}
