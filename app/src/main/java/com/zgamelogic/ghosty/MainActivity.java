package com.zgamelogic.ghosty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.zgamelogic.ghosty.data.Ghost;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

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


        // Create evidence list in investigation view
        evRecyclerView = (RecyclerView)findViewById(R.id.evidenceRecyclerView);

        evListener = new OnClickListener() {
            @Override
            public void onClick(View view){

            };

        };

        adapterE = new EvidenceViewAdapter(evidenceListItems, getApplication(), evListener, adapterG);
        evRecyclerView.setAdapter(adapterE);
        evRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
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
        ArrayList<EvidenceList> evlist = new ArrayList<EvidenceList>();

        evlist.add(new EvidenceList("Fingerprints"));
        evlist.add(new EvidenceList("Freezing Temperatures"));
        evlist.add(new EvidenceList("Ghost Orb"));
        evlist.add(new EvidenceList("EMF Level 5"));
        evlist.add(new EvidenceList("D.O.T.S Projector"));
        evlist.add(new EvidenceList("Ghost Writing"));
        evlist.add(new EvidenceList("Spirit Box"));

        return evlist;
    }

    /**
     * Sample ghosts list data for RecyclerView
     * Each GhostList object is populated with a number of string/TextView's defined in view_item.xml
     * @return Arraylist<GhostList> containing data for ghost list
     */
    public ArrayList<GhostList> getGhostListData()
    {
        ArrayList<GhostList> glist = new ArrayList<GhostList>();

        glist.add(new GhostList("Banshee", "D.O.T.S Projector", "Ghost Orb", "Fingerprints"));
        glist.add(new GhostList("Demon", "Ghost Writing", "Fingerprints", "Freezing Temperatures"));
        glist.add(new GhostList("Deogen", "D.O.T.S Projector", "Ghost Writing", "Spirit Box"));
        glist.add(new GhostList("Goryo", "D.O.T.S Projector", "EMF Level 5", "Fingerprints"));
        glist.add(new GhostList("Hantu", "Ghost Orb", "Fingerprints", "Freezing Temperatures"));

        return glist;
    }
}