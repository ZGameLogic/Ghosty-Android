package com.zgamelogic.ghosty;

import java.util.ArrayList;
import java.util.HashSet;

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
    HashSet<String> evSet = new HashSet<String>();

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


        evSet.add(evidence1);
        evSet.add(evidence2);
        evSet.add(evidence3);
    }

    /**
     * purpose: Takes a evidence type and determines if it matches a particular ghost name.
     * @method isMatched
     * @return a match or not
     */
    public Boolean isMatched(String evClicked) {
        return evSet.contains(evClicked);

    }

    /**
     * purpose: Takes a ghost and checks against a hashset of remaining evidence to see if ghost is valid
     * @method isValid
     * @return valid or not
     */
    //public Boolean isValid(String evClicked) {
      //  for (int i = 0,) {
            ;
        //}
    //}

}
