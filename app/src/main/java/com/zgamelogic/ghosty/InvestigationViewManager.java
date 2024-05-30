package com.zgamelogic.ghosty;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.zgamelogic.ghosty.EvidenceList;
import com.zgamelogic.ghosty.R;
import com.zgamelogic.ghosty.data.Ghost;
import java.util.ArrayList;
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
    private LinkedList<Ghost> ghosts;
    private LinkedList<String> evidences;

    // Evidences that have been removed from pool of remaining evidences or selected
    private LinkedList<String> graveyard;
    private HashSet<String> selectedEvidences;

    // XMLs for the linear layout of each list
    private LinearLayout evidenceList;
    private LinearLayout ghostList;

    // Context of app
    private Activity activity;

    // Tag for logging
    private final String LOG_IVM = "InvestigationView";

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
            textview = (TextView)itemView.findViewById(R.id.evidenceListString);
            textview.setText(ev);
            textview.setVisibility(View.VISIBLE);

            // Switch
            switchview = (Switch)itemView.findViewById(R.id.simpleSwitchList);
            switchview.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    RelativeLayout evidenceLayout;
                    TextView evidenceTextview;
                    String evidence;

                    evidenceLayout = (RelativeLayout) compoundButton.getParent();
                    evidenceTextview = (TextView) evidenceLayout.findViewById(R.id.evidenceListString);
                    evidence = (String) evidenceTextview.getText();

                    // Update list of selected evidences
                    if (isChecked) {
                        selectedEvidences.add(evidence);
                    } else {
                        selectedEvidences.remove(evidence);
                    }

                    // Update ghosts on the UI
                    //updateGhostLayout();

                    // Update evidences on the UI

                    /*Switch switch = (Switch) viewHolder.view.findViewById(R.id.simpleSwitchList);
                    TextView textView = (TextView) viewHolder.view.findViewById(R.id.evidenceListString);
                    String switchText = textView.getText().toString();
                    Boolean isglistModified = false;
                    Boolean isevidenceUiModified = false;
                    EvidenceList ev;

                    Log.i(LOG_EVA, "Switch is " + isChecked);
                    Log.i(LOG_EVA, switchText);

                    //Update toggleChecked, glist, and graveYard
                    if (isChecked) {
                        toggleChecked.add(switchText);
                        for (int i = 0; i < glist.size(); i++) {
                            if (!glist.get(i).isMatched(switchText)) {
                                graveYard.add(glist.remove(i));
                                i--;
                                isglistModified = true;
                            }
                        }
                    } else {
                        toggleChecked.remove(switchText);
                        Log.i(LOG_EVA, "Removed switch name from checked list");
                        for (int i = 0; i < graveYard.size(); i++) {
                            Log.i(LOG_EVA, "for loop started");
                            if (graveYard.get(i).isValid(toggleChecked)) {
                                glist.add(graveYard.remove(i));
                                i--;
                                isglistModified = true;
                                Log.i(LOG_EVA, "isglistModified is true");
                            }
                        }
                    }

                    //Update the GhostViewAdapter ui
                    if (isglistModified) {
                        adapterG.notifyDataSetChanged();
                        Log.i(LOG_EVA, "adapterG was notified");
                    }

                    //Update evidence color and switch state
                    for (int i = 0; i < itemList.size(); ++i) {
                        ev = itemList.get(i);

                        if (switchText.equals(ev.evidenceText)) {
                            ev.switchState = isChecked;
                        }

                        if (!ev.isValid(glist) && !ev.isDisabled()) {
                            Log.i(LOG_EVA, ev.evidenceText + " greyed out");
                            //UpdateGreyStatus
                            ev.setGreyStatus(true);
                            Log.i(LOG_EVA, ev.evidenceText + " status = grey");
                            isevidenceUiModified = true;
                            Log.i(LOG_EVA, "isevidenceUiModified is true");
                        } else if (ev.isValid(glist) && ev.isDisabled()) {
                            Log.i(LOG_EVA, ev.evidenceText + " grey be gone");
                            //UpdateGreyStatus
                            ev.setGreyStatus(false);
                            Log.i(LOG_EVA, ev.evidenceText + " status = not grey");
                            isevidenceUiModified = true;
                            Log.i(LOG_EVA, "isevidenceUiModified is true");
                        } else {
                            Log.i(LOG_EVA, ev.evidenceText + " unchanged");
                        }
                    }

                    //Update the EvidenceViewAdapter ui
                    if (isevidenceUiModified) {
                        notifyDataSetChanged();
                        Log.i(LOG_EVA, "EvidenceViewAdapter was notified");
                    }*/
                }
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
        LinkedList<String> evidences;

        // Clear ghostList of any existing items
        ghostList.removeAllViews();

        /* Need to manually set the layout parameters for each evidence
           that is added to the ghost's ui list of evidences. */
        layoutParams = new LinearLayout.LayoutParams(0,
                                LinearLayout.LayoutParams.WRAP_CONTENT,1);

        // Add each ghost back in as a default state
        inflater = activity.getLayoutInflater();

        for (Ghost ghost : ghosts) {
            itemView = (RelativeLayout)inflater.inflate(R.layout.view_item, ghostList, false);

            // Textview for ghost name
            textview = (TextView)itemView.findViewById(R.id.ghostListName);
            textview.setText(ghost.getName());
            textview.setVisibility(View.VISIBLE);

            // Textviews for evidences
            evidences = ghost.getEvidence();
            ghostEvidenceList = (LinearLayout)itemView.findViewById(R.id.ghostEvRow);
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
     * Updates the list of evidences on the UI.
     * If there are no remaining ghosts in the ghost list that have a given
     * evidence, then that evidence should be greyed out and disabled.
     */
    public void updateEvidenceLayout() {
        ;
    }

    /**
     * Updates the list of ghosts on the UI.
     * Based on the evidences that the user has selected, the only ghosts
     * displayed in the list should be remaining possible outcomes for the
     * game.
     */
    public void updateGhostLayout() {
        for (Ghost ghost : ghosts) {
            ghost.updateVisibility(selectedEvidences);
        }
    }
}
