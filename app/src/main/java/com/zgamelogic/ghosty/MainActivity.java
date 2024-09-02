package com.zgamelogic.ghosty;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View.OnClickListener;
import android.os.Bundle;

import com.zgamelogic.ghosty.data.Ghost;
import com.zgamelogic.ghosty.services.GhostyAPI;

import java.util.LinkedList;


public class MainActivity<dataSet> extends AppCompatActivity {

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