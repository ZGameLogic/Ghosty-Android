package com.zgamelogic.ghosty;

import com.zgamelogic.ghosty.data.Ghost;

/**
 * Purpose: stores the information that will be displayed in a single item in
 * the ghost list.
 * Stores the name of the ghost and its three types of evidence
 */
public class GhostList {
    // Name of the ghost
    String ghostName;

    // Applicable evidences for the ghost
    String evidence1;
    String evidence2;
    String evidence3;

    /**
     * Constructor below
     * @param ghostName: name of the ghost for this ghost list item
     */
    public GhostList(String ghostName, String evidence1, String evidence2, String evidence3)
    {
        this.ghostName = ghostName;
        this.evidence1 = evidence1;
        this.evidence2 = evidence2;
        this.evidence3 = evidence3;
    }

    public GhostList(Ghost ghost){
        ghostName = ghost.getName();
        evidence1 = ghost.getEvidence().get(0);
        evidence2 = ghost.getEvidence().get(1);
        evidence3 = ghost.getEvidence().get(2);
    }
}
