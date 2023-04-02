package com.zgamelogic.ghosty;

/**
 * Purpose: stores a string for one of the evidence toggles.
 * evidenceText(global) is generally named so that any specified evidence string can be stored.
 */
public class EvidenceList {
    //This holds the evidence name for one of the toggles
    String evidenceText;

    /**
     * Constructor below
     * @param evidenceFilter string that identifies the accompanying toggle.
     */
    public EvidenceList(String evidenceFilter)
    {
        //this is assigning local to global
        //Local has priority over global.
        this.evidenceText = evidenceFilter; //global = Local
    }
}
