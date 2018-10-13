package com.example.hp.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button b1;
    private EditText e1;
    private EditText e2;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.btFireBase);
        e1=(EditText)findViewById(R.id.etName);
        e2=(EditText)findViewById(R.id.etEmail);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=e1.getText().toString().trim();
                String email=e2.getText().toString().trim();
                HashMap<String, String> dataMap=new HashMap<String, String>();
                dataMap.put("Name",name);
                dataMap.put("Email",email);
                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Insertion Sucess",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Insertion Not Sucess",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
