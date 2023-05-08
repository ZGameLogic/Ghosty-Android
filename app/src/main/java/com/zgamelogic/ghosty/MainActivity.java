package com.zgamelogic.ghosty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity<dataSet> extends AppCompatActivity {


    EvidenceViewAdapter adapterE;
    GhostViewAdapter adapterG;
    RecyclerView evRecyclerView, ghostRecyclerView;
    OnClickListener evListener, ghostListener;

    @Override
    //
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<EvidenceList> evidenceListItems = getEvidenceListData();
        ArrayList<GhostList> ghostListItems = getGhostListData();

        // Create evidence list in investigation view
        evRecyclerView = (RecyclerView)findViewById(R.id.evidenceRecyclerView);
        evListener = new OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MainActivity.this,"Ev list view was clicked",Toast.LENGTH_SHORT).show();
                Switch test = (Switch) findViewById(R.id.simpleSwitchList);
            }
        };
        adapterE = new EvidenceViewAdapter(evidenceListItems, getApplication(), evListener);
        evRecyclerView.setAdapter(adapterE);
        evRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        // Create ghost list in investigation view
        ghostRecyclerView = (RecyclerView)findViewById(R.id.ghostRecyclerView);
        ghostListener = new OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MainActivity.this,"Ghost list view was clicked",Toast.LENGTH_SHORT).show();
            }
        };
        adapterG = new GhostViewAdapter(ghostListItems, getApplication(), ghostListener);
        ghostRecyclerView.setAdapter(adapterG);
        ghostRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    //Back key detection
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    /**
     * Get information for evidence list data for RecyclerView
     * Each EvidenceList object is populated with a number of string/TextView's defined in view_item.xml
     * @return Arraylist<EvidenceList> containing data for evidence list
     */
    private ArrayList<EvidenceList> getEvidenceListData()
    {
        ArrayList<EvidenceList> list = new ArrayList<EvidenceList>();
        for (int i = 0; i < 20; i++) {
            list.add(new EvidenceList("This is evidence item ".concat(Integer.toString(i))));
        }
        return list;
    }

    /**
     * Sample ghosts list data for RecyclerView
     * Each GhostList object is populated with a number of string/TextView's defined in view_item.xml
     * @return Arraylist<GhostList> containing data for ghost list
     */
    private ArrayList<GhostList> getGhostListData()
    {
        ArrayList<GhostList> list = new ArrayList<GhostList>();
        list.add(new GhostList("Brandon", "Pointers", "None", "Mango juice"));
        list.add(new GhostList("Jason", "Tom Skilling", "Amouranth", "Milk"));
        list.add(new GhostList("Rob", "Teeth", "Vodka", "Sandshrew"));
        list.add(new GhostList("Ben", "Java compiler", "Hearthstone", "Kenobi"));
        list.add(new GhostList("Reagan", "World's largest wealth gap", "Drug epidemic", "Corn syrup"));
        return list;
    }
}