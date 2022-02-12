package com.rushikeshnarkhede.coursetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id, name, drtn, desn;
    Button saveBtn, lstBtn, showBtn, updateBtn, deleteBtn;
    Intent intent;
    DatabaseHandler db;
    String uid, uname, udrn, udsn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);
        id = findViewById(R.id.txtId);
        name = findViewById(R.id.txtName);
        drtn = findViewById(R.id.txtdrtn);
        desn = findViewById(R.id.txtdesn);

        saveBtn = findViewById(R.id.btnSave);
        lstBtn = findViewById(R.id.btnList);
        updateBtn = findViewById(R.id.btnUpdate);
        deleteBtn = findViewById(R.id.btnDelete);
        showBtn = findViewById(R.id.btnShowRec);

        //Save record
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = id.getText().toString();
                uname = name.getText().toString();
                udrn = drtn.getText().toString();
                udsn = desn.getText().toString();

                try {
                    db.addCourse(new Course(Integer.parseInt(uid),uname,udrn,udsn));
                    Toast.makeText(getApplicationContext(), "Course Added", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid = id.getText().toString();
                uname = name.getText().toString();
                udrn= drtn.getText().toString();
                udsn=desn.getText().toString();
                try {
                    db.updateCourse(new Course(Integer.parseInt(uid), uname, udrn,udsn));
                    Toast.makeText(getApplicationContext(), "Course  Updated",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid = id.getText().toString();
                try {
                    db.deleteCourse(Integer.parseInt(uid));
                    Toast.makeText(getApplicationContext(), "Record Deleted",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // To Display all records from table
        lstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        // To Display a single record from table
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = id.getText().toString();
                intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("userid", uid);
                startActivity(intent);
            }
        });

    }
}
