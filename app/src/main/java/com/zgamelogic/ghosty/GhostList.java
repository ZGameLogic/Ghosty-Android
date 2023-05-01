package com.zgamelogic.ghosty;

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
}
