package com.zgamelogic.ghosty.data;

import android.view.View;
import android.widget.RelativeLayout;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Data structure to hold ghost information from Ghosty API
 * @author Ben Shabowski
 */
public class Ghost {

    private String name;
    private String description;
    private int id;
    private HashSet<String> evidence;
    @JsonIgnore
    private RelativeLayout uiItem; // The xml element representing this ghost

    /**
     * Construct a ghost with a name, description, id, and evidence
     * @param name Name of the ghost
     * @param description Description of ghost
     * @param id ID of ghost
     * @param evidence evidence of ghost
     */
    public Ghost(String name, String description, int id, LinkedList<String> evidence) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.evidence = new HashSet<>(evidence);
        uiItem = null;
    }

    public Ghost() {
        evidence = new HashSet<>();
        uiItem = null;
    }

    /**
     * This method returns the remaining evidence needed to identify the ghost as this ghost
     * @param selectedEvidence The evidence currently already gathered
     * @return Collection of evidence still needed to be gathered
     */
    public LinkedList<String> remainingEvidence(Collection<String> selectedEvidence){
        LinkedList<String> ghostEvidence = new LinkedList<>();
        for (String ev : evidence) {
            if (!selectedEvidence.contains(ev)) {
                ghostEvidence.add(ev);
            }
        }
        return ghostEvidence;
    }

    /**
     * Based off of current evidence, decides if the ghost can be valid
     * @param currentEvidence the evidence currently gathered
     * @return true if the ghost can still be a possible outcome
     */
    public boolean isValid(Collection<String> currentEvidence){
        for (String ev : currentEvidence) {
            if (!evidence.contains(ev)) {
                return false;
            }
        }
        return true;
    }

    public String toString(){
        return name;
    }

    /**
     * Gets the name of the ghost
     * @return name of the ghost
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the ghost
     * @return description of the ghost
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the id of the ghost
     * @return id of the ghost
     */
    public int getId() {
        return id;
    }

    /**
     * Gets a list of evidence of for the ghost
     * @return evidence for the ghost
     */
    public HashSet<String> getEvidence() {
        return evidence;
    }

    /**
     * Get the xml element for this ghost.
     * @return RelativeLayout for this ghost on the UI, or null.
     */
    public RelativeLayout getUiItem() {
        return uiItem;
    }

    /**
     * Save the xml element for this ghost to this object.
     * @param uiItem the RelativeLayout for this ghost in the UI.
     */
    public void setUiItem(RelativeLayout uiItem) {
        this.uiItem = uiItem;
    }

    /**
     * Update the visibility of this ghost's UI item based on the user
     * selected evidences.
     * @param selectedEvidences list of evidences that the user has currently
     *                          selected.
     * @return true if ghost is a candidate.
     */
    public boolean updateVisibility(Collection<String> selectedEvidences) {
        boolean isVisible;
        boolean shouldBeVisible;

        // Determine if currently visible
        isVisible = uiItem.getVisibility() == View.VISIBLE;
        //Log in order to figure out if uiItem is affecting the visibility of all ghosts.

        // Determine if ghost should be visible
        shouldBeVisible = isValid(selectedEvidences);

        // Update visibility if necessary
        if (shouldBeVisible && !isVisible) {
            uiItem.setVisibility(View.VISIBLE);
        } else if (!shouldBeVisible && isVisible) {
            uiItem.setVisibility(View.GONE);
        }
        return shouldBeVisible;
    }
}
