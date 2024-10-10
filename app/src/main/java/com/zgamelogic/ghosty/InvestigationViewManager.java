package com.zgamelogic.ghosty;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.zgamelogic.ghosty.data.Ghost;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Manages the evidence and ghost lists on the Investigation Screen.
 *
 * This is responsible for ensuring that only valid candidates for the Ghost of the
 * Phasmophobia game are still on the screen.
 *
 * This class will further manage the list of evidences, ensuring that invalid evidences
 * are unable to be selected by the user.
 * An evidence is only valid if it is possible for it to be in the game given the
 * other evidences that have been selected by the user.
 */
public class InvestigationViewManager {

    // Items to display on evidence and ghost list, respectively
    private final LinkedList<Ghost> ghosts;
    private final LinkedList<String> evidences;

    // Evidences that have been removed from pool of remaining evidences or selected
    private final HashSet<String> selectedEvidences = new HashSet<>();

    // XMLs for the linear layout of each list
    private final LinearLayout evidenceList;
    private final LinearLayout ghostList;

    // Context of app
    private final Activity activity;

    // Tag for logging
    private final String LOG_IVM = "InvestigationView";

    private final int defaultTextColor;
    private final int deactivatedTextColor;
    private boolean greyStatus;

    /**
     * Initialize class.
     * @param ghosts ghosts in Phasmophobia
     * @param evidences evidences in Phasmophobia
     */
    public InvestigationViewManager(Activity activity, LinkedList<Ghost> ghosts, LinkedList<String> evidences) {
        this.ghosts = ghosts;
        this.evidences = evidences;
        evidenceList = activity.findViewById(R.id.evidenceList);
        ghostList = activity.findViewById(R.id.ghostList);
        this.activity = activity;
        defaultTextColor = getColor(android.R.attr.textColor);
        deactivatedTextColor = getColor(R.attr.colorDeactivated);

    }

    /**
     * Populate the evidence list based on evidences.
     *
     * This is intended to be called once by MainActivity's getEvidences method
     * as this will remake the list from scratch.
     */
    public void populateEvidenceList() {
        RelativeLayout itemView;
        LayoutInflater inflater;
        TextView textview;
        Switch switchview;

        // Clear evidenceList of any existing items
        evidenceList.removeAllViews();

        // Add each evidence back in as a default state
        inflater = activity.getLayoutInflater();
        for (String ev : evidences) {
            itemView = (RelativeLayout)inflater.inflate(R.layout.view_item, evidenceList, false);

            // Textview
            textview = itemView.findViewById(R.id.evidenceListString);
            textview.setText(ev);
            textview.setVisibility(View.VISIBLE);

            // Switch
            switchview = itemView.findViewById(R.id.evSwitch);
            switchview.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                RelativeLayout evidenceLayout;
                TextView evidenceTextview;
                String evidence;
                HashSet<String> remainingEv;
                Log.i(LOG_IVM, "Clicked");

                evidenceLayout = (RelativeLayout) compoundButton.getParent();
                evidenceTextview = evidenceLayout.findViewById(R.id.evidenceListString);
                evidence = (String) evidenceTextview.getText();

                // Update list of selected evidences
                if (isChecked) {
                    Log.i(LOG_IVM, "isChecked");
                    selectedEvidences.add(evidence);
                    Log.i(LOG_IVM, String.valueOf(selectedEvidences.contains(evidence)));

                } else {
                    Log.i(LOG_IVM, "notChecked");
                    selectedEvidences.remove(evidence);
                }

                // Update ghosts on the UI
                remainingEv = updateGhostLayout();
                Log.i(LOG_IVM, "updated");

                // Update evidences on the UI
                updateEvidenceLayout(remainingEv);


            });
            switchview.setVisibility(View.VISIBLE);

            // Add evidence to layout
            evidenceList.addView(itemView);
        }
    }


    /**
     * Populate the ghost list based on ghosts.
     *
     * This is intended to be called once by MainActivity's getGhosts method
     * as this will remake the list from scratch.
     */
    public void populateGhostList() {
        RelativeLayout itemView;
        LinearLayout ghostEvidenceList;
        LayoutInflater inflater;
        LinearLayout.LayoutParams layoutParams;
        TextView textview;
        HashSet<String> evidences;

        // Clear ghostList of any existing items
        ghostList.removeAllViews();

        /* Need to manually set the layout parameters for each evidence
           that is added to the ghost's ui list of evidences. */
        layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1);

        // Add each ghost back in as a default state
        inflater = activity.getLayoutInflater();

        for (Ghost ghost : ghosts) {
            itemView = (RelativeLayout)inflater.inflate(R.layout.view_item, ghostList, false);

            // Textview for ghost name
            textview = itemView.findViewById(R.id.ghostListName);
            textview.setText(ghost.getName());
            textview.setVisibility(View.VISIBLE);

            // Textviews for evidences
            evidences = ghost.getEvidence();
            ghostEvidenceList = itemView.findViewById(R.id.ghostEvRow);
            for (String ev : evidences) {

                textview = (TextView)inflater.inflate(R.layout.ghost_evidence_textview, null);
                textview.setText(ev);
                textview.setLayoutParams(layoutParams);
                textview.setVisibility(View.VISIBLE);
                ghostEvidenceList.addView(textview);

            }
            ghostEvidenceList.setVisibility(View.VISIBLE);

            // Add ghost to layout
            ghostList.addView(itemView);

            // Save to ghost object
            ghost.setUiItem(itemView);
        }
    }

    /**
     * Disables or enables switch based on a given boolean
     * @param itemView A view that holds a switch and a textview
     * @param isAble True in order to enable switch
     */
    private void setSwitchAble(RelativeLayout itemView, boolean isAble) {
        itemView.findViewById(R.id.evSwitch).setClickable(isAble);
    }

    private int getColor(int colorRes) {

        int[] attrs = {colorRes};
        @SuppressLint("ResourceType") TypedArray ta = activity.obtainStyledAttributes(R.style.ViewItem, attrs);
        int defaultColor = ContextCompat.getColor(activity, R.color.black);
        int textColor = ta.getColor(0, defaultColor);
        ta.recycle();

        return textColor;
    }

    private void setevColor(RelativeLayout itemView){
        TextView textColor;
        textColor = itemView.findViewById(R.id.evidenceListString);
        if (greyStatus) {
            textColor.setTextColor(deactivatedTextColor);
        }
        else {
            textColor.setTextColor(defaultTextColor);
        }
    }

    /**
     * Updates the list of evidences on the UI.
     * If there are no remaining ghosts in the ghost list that have a given
     * evidence, then that evidence should be greyed out and disabled.
     */
    public void updateEvidenceLayout(HashSet<String> evList) {
        int i = 0;

        //determine if switch should be enabled or disabled
        for (String currentEv : evidences) {
            if (evList.contains(currentEv)) {
                //ungrey evidence
                greyStatus = false;
                setevColor((RelativeLayout)evidenceList.getChildAt(i));
                setSwitchAble((RelativeLayout)evidenceList.getChildAt(i), true);
            }
            else {
                //grey out evidence
                greyStatus = true;
                setevColor((RelativeLayout)evidenceList.getChildAt(i));
                setSwitchAble((RelativeLayout)evidenceList.getChildAt(i), false);
            }
            i++;
        }
    }

    /**
     * Updates the list of ghosts on the UI.
     * Based on the evidences that the user has selected, the only ghosts
     * displayed in the list should be remaining possible outcomes for the
     * game.
     */
    public HashSet<String> updateGhostLayout() {
        HashSet<String> evList = new HashSet<>();

        for (Ghost ghost : ghosts) {
            Log.i(LOG_IVM, ghost.getName());

            //the current ghost is a candidate then add it to a set.
            if (ghost.updateVisibility(selectedEvidences)) {
                evList.addAll(ghost.getEvidence());
            }
        }
        return evList;
        //return a set of all of the evidences that are "tied" to the remaining ghosts
    }
}
