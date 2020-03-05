package com.alenmutum21.ac_twitterclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button signin,btnGetalldata;
    private EditText name,standard,roll,subject;
    private TextView txtGetData;
    private String allinfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();

        signin = findViewById(R.id.signBtn);
        name = findViewById(R.id.name);
        standard = findViewById(R.id.standard);
        roll = findViewById(R.id.roll);
        subject = findViewById(R.id.subject);
        txtGetData = findViewById(R.id.txtGetData);
        btnGetalldata = findViewById(R.id.btnAllData);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("College");
                parseQuery.getInBackground("isdcVBcdYz", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {


                        if (object != null && e == null){
                            txtGetData.setText(object.get("roll").toString());
                            FancyToast.makeText(MainActivity.this,"name: " + object.get("name"),FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                        }
                    }
                });
            }
        });

        btnGetalldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allinfo = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("College");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            if (objects.size() > 0){
                                for (ParseObject infos : objects){
                                    allinfo = allinfo + infos.get("name") + "\n";
                                }
                                FancyToast.makeText(MainActivity.this,allinfo,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();

                            }else {
                                FancyToast.makeText(MainActivity.this,"Error at: "+ e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();

                            }
                        }
                    }
                });
            }
        });


        final ParseObject Student = new ParseObject("College");

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final ParseObject coder = new ParseObject("Coder");
//                coder.put("name","Alen Mutum");
                try {
                    Student.put("name",name.getText().toString());
                    Student.put("roll",Integer.parseInt(roll.getText().toString()));
                    Student.put("standard",standard.getText().toString());
                    Student.put("subject",subject.getText().toString());

                    Student.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(MainActivity.this,"infos Saved succesfully  ! " + Student.get("name"),FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            } else {
                                String error = e.getMessage().toString();
                                FancyToast.makeText(MainActivity.this,"Error at: "+ error,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            }
                        }
                    });
                }catch (Exception e){
                    FancyToast.makeText(MainActivity.this,"Error at: "+ e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }

            }
        });



    }
}
