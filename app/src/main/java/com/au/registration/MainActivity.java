package com.au.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name;
    CheckBox reading,coding,cricket;

    String[] group={"Please Select Course","B.SCIT","BCA","MSC.IT"};
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=findViewById(R.id.spinner);
        ArrayAdapter<String> adpt=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,group)
        {
            @Override
            public boolean isEnabled(int position) {
            if (position==0)
            {
                return false;
            }
            else
            {
                return true;
            }
            }
        };
        sp.setAdapter(adpt);

        name=findViewById(R.id.name);

        reading=findViewById(R.id.ch1);
        coding=findViewById(R.id.ch2);
        cricket=findViewById(R.id.ch3);

    }


    public void insert(View view) {
        RadioGroup radioGroup=findViewById(R.id.radiogroup);
        int i=radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton=(RadioButton) findViewById(i);

        String res="";
        if(reading.isChecked())
        {
            res +=reading.getText().toString()+",";
        }
        if(coding.isChecked())
        {
            res +=coding.getText().toString()+",";;
        }
        if(cricket.isChecked())
        {
            res +=cricket.getText().toString()+",";;
        }

        database db2 =new database(this);
        boolean insert=db2.insert(name.getText().toString(),radioButton.getText().toString(),res,sp.getSelectedItem().toString());
        if (insert==true)
        {
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Nathi Thatu", Toast.LENGTH_SHORT).show();
        }
        name.setText("");
        radioButton.setChecked(false);
        reading.setChecked(false);
        coding.setChecked(false);
        cricket.setChecked(false);
        sp.setSelection(0);
    }

    public void show(View view) {
        database db =new database(this);
        Cursor c=db.select();
        AlertDialog.Builder bc=new AlertDialog.Builder(this);
        if (c.getCount()==0)
        {
            bc.setMessage("Kaik Data To Nath Pela");
            bc.setTitle("Table");
            bc.setNegativeButton("Cancle",null);
            bc.setPositiveButton("Ok",null);
            bc.show();
        }
        else {

            String msg="";
            while (c.moveToNext())
            {
                msg +=c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getString(3)+" "+c.getString(4)+"\n";

            }
            bc.setMessage(msg);
            bc.setTitle("Table");
            bc.setNegativeButton("Cancle",null);

            bc.setPositiveButton("Ok",null);
            bc.show();


        }
    }
}