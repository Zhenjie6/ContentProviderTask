package com.example.zhenjie.contentprovidertask;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.zhenjie.contentprovidertask.adapter.MExpandableListViewAdapter;
import com.example.zhenjie.contentprovidertask.bean.DishGroup;
import com.example.zhenjie.contentprovidertask.dataUtils.DishDataUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expListView;
    private List<DishGroup> groups;
    private Toolbar toolbar;
    private MExpandableListViewAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        Cursor c = getContentResolver().query(Uri.parse("content://com.imooc.menuprovider"), null, null,
                null, null, null);
        groups = DishDataUtils.getDishListFromProvider(c);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_menu_item:
                Intent i = new Intent(this, InsertActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expListView = findViewById(R.id.exp_list_view);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ContentResolver contentResolver = getContentResolver();
        Cursor c = contentResolver.query(Uri.parse("content://com.imooc.menuprovider"), null, null,
                null, null, null);
        groups = DishDataUtils.getDishListFromProvider(c);
        adapter = new MExpandableListViewAdapter(groups,this);
        expListView.setAdapter(adapter);
    }

}
