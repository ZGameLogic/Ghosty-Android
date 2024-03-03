package com.zgamelogic.ghosty;

import com.shapesecurity.salvation2.Values.Hash;
import com.zgamelogic.ghosty.data.Ghost;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
     * @param ghost: Ghost object for this ghost
     */
    public GhostList(Ghost ghost){
        ghostName = ghost.getName();
        evidence1 = ghost.getEvidence().get(0);
        evidence2 = ghost.getEvidence().get(1);
        evidence3 = ghost.getEvidence().get(2);

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

    /** A ghost is valid if, based on the currently selected evidences, the ghost is still potentially the ghost in the game.
     * purpose:
     * check all of the evidences that are selected by the user.
     * if any of the evidences are not in this ghost, return false, otherwise return true.
     * @method isValid
     * @return valid or not
     */
    public Boolean isValid(List<String> toggleChecked) {
        if ((!toggleChecked.contains(evidence1)) || (!toggleChecked.contains(evidence2)) || (!toggleChecked.contains(evidence3))){
            return false;
        }
        else {
            return true;
        }
    }

}
