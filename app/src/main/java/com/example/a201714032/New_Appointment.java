package com.example.a201714032;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class New_Appointment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] time = new String[]{"AM","PM"};
    private CheckBox blood,urine,ecg,mri;

    EditText Doctors_name, Date, Time;
    String doctorName, str_date,str_time, test , time_period
            , str_blood,str_urine,str_ecg,str_mri;
    Button setBtn;
    Spinner spinner;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__appointment);

        databaseReference = FirebaseDatabase.getInstance().getReference("Appointment History");

        Doctors_name = findViewById(R.id.doctor_name);
        Date = findViewById(R.id.date);
        Time = findViewById(R.id.time);
        setBtn = findViewById(R.id.setBtn);

        blood = findViewById(R.id.chkBlood);
        urine=findViewById(R.id.chkUrine);
        ecg=findViewById(R.id.chkECG);
        mri=findViewById(R.id.chkMRI);

        spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item,time);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        test = "";


        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorName = Doctors_name.getText().toString().trim();
                str_date = Date.getText().toString().trim();
                str_time = Time.getText().toString().trim() + time_period;

                if(blood.isChecked())
                {
                    test = test + "Blood ";
                }
                if(urine.isChecked())
                {
                    test = test + "Urine ";
                }
                if(ecg.isChecked())
                {
                    test = test + "ECG ";
                }
                if(mri.isChecked())
                {
                    test = test + "MRI ";
                }
               // Toast.makeText(getApplicationContext(),test,Toast.LENGTH_SHORT).show();
                SaveData();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                time_period = " AM";
                break;
            case 1:
                time_period = " PM";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void SaveData() {
        String key = databaseReference.push().getKey();
        StoreResult storeResult = new StoreResult(doctorName, str_time,str_date,test);
        databaseReference.child(key).setValue(storeResult);
        Toast.makeText(getApplicationContext(),"Appointment Successfully Uploaded",Toast.LENGTH_SHORT).show();
    }
}
