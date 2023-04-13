package com.zgamelogic.ghosty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity<dataSet> extends AppCompatActivity {


    EvidenceViewAdapter adapter;
    RecyclerView recyclerView;
    OnClickListener listener;

    @Override
    //
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<EvidenceList> evidenceListItems = getEvidenceTestData();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        listener = new OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MainActivity.this,"This View Was Clicked",Toast.LENGTH_LONG).show();
            }
        };
        adapter = new EvidenceViewAdapter(evidenceListItems, getApplication(),listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //Example of a switch button implementation
        /**SwitchLayoutBinding binding = SwitchLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.materialSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // The switch is checked.
            } else {
                // The switch isn't checked.
            }
        });*/
    }

    @Override
    //Back key detection
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    /**
     * Sample evidence list data for RecyclerView
     * Each EvidenceList object is populated with a number of string/TextView's defined in view_item.xml?
     * @return Arraylist<EvidenceList> containing testing data for evidence list
     */
    private ArrayList<EvidenceList> getEvidenceTestData()
    {
        ArrayList<EvidenceList> list = new ArrayList<EvidenceList>();
        for (int i = 0; i < 20; i++) {
            list.add(new EvidenceList("This is evidence item ".concat(Integer.toString(i))));
        }
        return list;
    }

    /**
     * Sample ghosts list data for RecyclerView
     * Each GhostList object is populated with a number of string/TextView's defined in view_item.xml?
     * @return Arraylist<GhostList> containing testing data for ghost list
     */
    private ArrayList<EvidenceList> getGhostTestData()
    {
        ArrayList<EvidenceList> list = new ArrayList<EvidenceList>();
        return list;
    }
}