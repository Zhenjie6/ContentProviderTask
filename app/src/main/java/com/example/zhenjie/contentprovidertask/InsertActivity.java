package com.example.zhenjie.contentprovidertask;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    EditText et;
    Toolbar toolbar;
    Spinner spinner;
    String type,name;
    Button button;
    ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        initView();
        resolver = getContentResolver();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = (String) spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("dish_name", String.valueOf(et.getText()));
                values.put("dish_type",type);
                Uri uri = resolver.insert(Uri.parse("content://com.imooc.menuprovider"),values);
                Toast.makeText(InsertActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
                et.clearComposingText();
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.tool_bar);
        spinner = findViewById(R.id.my_spinner);
        button = findViewById(R.id.button);
        et = findViewById(R.id.editText);
    }
}
