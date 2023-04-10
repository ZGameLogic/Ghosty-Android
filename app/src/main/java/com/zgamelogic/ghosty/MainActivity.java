package com.zgamelogic.ghosty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity<dataSet> extends AppCompatActivity {


    ViewAdapter adapter;
    RecyclerView recyclerView;
    OnClickListener listener;

    @Override
    //
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ListItem> list = new ArrayList<>();
        list = getData();

        recyclerView
                = (RecyclerView)findViewById(
                R.id.recyclerView);
        listener = new OnClickListener() {
            @Override
            public void onClick(View view){
                //
                Toast.makeText(MainActivity.this,"This View Was Clicked",Toast.LENGTH_LONG).show();
            }
        };
        adapter
                = new ViewAdapter(
                list, getApplication(),listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(MainActivity.this));

        //Example of a switch button implementation
        SwitchLayoutBinding binding = SwitchLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.materialSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // The switch is checked.
            } else {
                // The switch isn't checked.
            }
        });
    }

    @Override
    //Back key detection
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    /**
     * Sample data for RecyclerView
     * Each ListItem is populated with a number of string/TextView's defined in view_item.xml?
     * @return
     */
    private List<ListItem> getData()
    {
        List<ListItem> list = new ArrayList<>();
        list.add(new ListItem("First Exam", "May 23, 2015", "Best Of Luck", "666"));
        list.add(new ListItem("Second Exam", "June 09, 2015", "b of l", "666"));
        list.add(new ListItem("My Test Exam", "April 27, 2017", "This is testing exam ..", "666"));
        list.add(new ListItem("My Test Exam", "April 27, 2017", "This is testing exam ..", "666"));
        list.add(new ListItem("My Test Exam", "April 27, 2017", "This is testing exam ..", "666"));
        list.add(new ListItem("My Test Exam", "April 27, 2017", "This is testing exam ..", "666"));

        return list;
    }
}