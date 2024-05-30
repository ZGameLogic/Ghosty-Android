package com.zgamelogic.ghosty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Toast;

import com.zgamelogic.ghosty.data.Ghost;
import com.zgamelogic.ghosty.services.GhostyAPI;
import com.zgamelogic.ghosty.services.InvestigationViewManager;

import java.util.ArrayList;
import java.util.LinkedList;


public class MainActivity<dataSet> extends AppCompatActivity {


    EvidenceViewAdapter adapterE;
    GhostViewAdapter adapterG;
    InvestigationViewManager ivManager;
    OnClickListener evListener, ghostListener;

    final Integer SHORT_WAIT = 200;  // ms

    @Override
    //
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinkedList<String> evidenceListItems = getEvidenceListData();
        LinkedList<Ghost> ghostListItems = getGhostListData();

        ivManager =  new InvestigationViewManager(this, ghostListItems, evidenceListItems);

//        // Create ghost list in investigation view
//        ghostRecyclerView = (RecyclerView)findViewById(R.id.ghostRecyclerView);
//        ghostListener = new OnClickListener() {
//            @Override
//            public void onClick(View view){
//                Toast.makeText(MainActivity.this,"Ghost list view was clicked",Toast.LENGTH_SHORT).show();
//            }
//        };
//        adapterG = new GhostViewAdapter(ghostListItems, getApplication(), ghostListener);
//        ghostRecyclerView.setAdapter(adapterG);
//        ghostRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//
//
//        // Create evidence list in investigation view
//        evRecyclerView = (RecyclerView)findViewById(R.id.evidenceListView);
//
//        evListener = new OnClickListener() {
//            @Override
//            public void onClick(View view){
//
//            };
//
//        };
//
//        adapterE = new EvidenceViewAdapter(evidenceListItems, getApplication(), evListener, adapterG);
//        evRecyclerView.setAdapter(adapterE);
//        evRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    //Back key detection
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    /**
     * Get information for evidence list data for RecyclerView
     * This will signal to InvestigationViewManager when the list is ready.
     * @return Arraylist<String> containing data for evidence list
     */
    private LinkedList<String> getEvidenceListData()
    {
        LinkedList<String> list = new LinkedList<>();
        GhostyAPI.getEvidence(evidences -> {
            for(String evidence: evidences){
                Log.d(GhostyAPI.LOG_API, "we read: " + evidence);
                list.add(evidence);
            }

            // Update UI once recycler view adapter support is initialized
            while (ivManager == null) {
                try {
                    Thread.sleep(SHORT_WAIT);
                } catch (InterruptedException e) {
                    Log.e(GhostyAPI.LOG_API, "interrupted evidence api sleep");
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ivManager.populateEvidenceList();
                }});
        }).start();
        return list;
    }

    /**
     * Sample ghosts list data for RecyclerView
     * Each GhostList object is populated with a number of string/TextView's defined in view_item.xml
     * @return Arraylist<Ghost> containing data for ghost list
     */
    private LinkedList<Ghost> getGhostListData()
    {
        LinkedList<Ghost> list = new LinkedList<>();
        GhostyAPI.getGhosts(ghosts -> {
            for(Ghost ghost: ghosts){
                Log.d(GhostyAPI.LOG_API, "we read: " + ghost.getName());
                list.add(ghost);
            }

            // Update UI once recycler view adapter support is initialized
            while (ivManager == null) {
                try {
                    Thread.sleep(SHORT_WAIT);
                } catch (InterruptedException e) {
                    Log.e(GhostyAPI.LOG_API, "interrupted ghost api sleep");
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ivManager.populateGhostList();
                }});
        }).start();
        return list;
    }
}