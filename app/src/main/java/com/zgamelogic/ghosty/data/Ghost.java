package com.zgamelogic.ghosty.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Data structure to hold ghost information from Ghosty API
 * @author Ben Shabowski
 */
public class Ghost {

    private String name;
    private String description;
    private int id;
    private LinkedList<String> evidence;

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
        this.evidence = evidence;
    }

    public Ghost(){
        evidence = new LinkedList<>();
    }

    /**
     * This method returns the remaining evidence needed to identify the ghost as this ghost
     * @param currentEvidence The evidence currently already gathered
     * @return Collection of evidence still needed to be gathered
     */
    public LinkedList<String> remainingEvidence(Collection<String> currentEvidence){
        LinkedList<String> ghostEvidence = (LinkedList<String>) evidence.clone();
        ghostEvidence.removeAll(currentEvidence);
        return ghostEvidence;
    }

    /**
     * Based off of current evidence, decides if the ghost can be valid
     * @param currentEvidence the evidence currently gathered
     * @return true if the ghost can still be a possible outcome
     */
    public boolean isValid(Collection<String> currentEvidence){
        return evidence.containsAll(currentEvidence);
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
    public LinkedList<String> getEvidence() {
        return evidence;
    }
}
