package com.zgamelogic.ghosty;

public class EvidenceFilter {
    //This holds the evidence name for all of the toggles
    String evidenceFilter;

    EvidenceList(String evidenceFilter)

//This is local
    {
        //this is assigning local to global
        //Local has priority over global.
        this.evidenceFilter = evidenceFilter; //global = Local

    }
}
