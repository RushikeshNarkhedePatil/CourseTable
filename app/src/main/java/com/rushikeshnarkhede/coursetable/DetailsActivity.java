package com.rushikeshnarkhede.coursetable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    DatabaseHandler db;
    ListView lv;
    int i = 0,uid;
    ArrayList<Course> course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        db = new DatabaseHandler(this);
        lv = findViewById(R.id.user_list);
        Intent intent=getIntent();
        if (intent.hasExtra("userid")) {
            uid = Integer.parseInt(intent.getStringExtra("userid"));
            course = db.GetUserByUserId(uid);
        }
        else
        {
//code to read all contacts
            course = db.getAllCourse();
        }
        List<String> ls = new ArrayList<String>();
        for (Course cn : course) {
            String s1 = " Id: " + cn.get_id() + "\t Name: " + cn.get_name() + "\n Duration: " +
                    cn.get_duration()+"\n Description: "+cn.get_description()+"\n";
            ls.add(s1);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (DetailsActivity.this,
                        android.R.layout.simple_expandable_list_item_1,
                        ls);

        lv.setAdapter(adapter);
    }
}