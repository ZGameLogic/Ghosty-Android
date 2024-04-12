package com.zgamelogic.ghosty;


import static androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;

import androidx.core.content.ContextCompat;

import java.util.List;

/**
 * Purpose: stores a string for one of the evidence toggles.
 * evidenceText(global) is generally named so that any specified evidence string can be stored.
 */
public class EvidenceList {
    //This holds the evidence name for one of the toggles
    String evidenceText;

    boolean deactivatedStatus;

    int colorStatus;

    int defaultTextColor;

    int deactivatedTextColor;

    boolean switchState;

    Context context;

    /**
     * Constructor below
     * @param evidenceFilter string that identifies the accompanying toggle.
     */
    public EvidenceList(String evidenceFilter, Context context)
    {
        //this is assigning local to global
        //Local has priority over global.
        this.context = context;
        this.evidenceText = evidenceFilter; //global = Local
        this.deactivatedStatus = false;
        switchState = false;
        defaultTextColor = getColor(android.R.attr.textColor);
        colorStatus = defaultTextColor;
        deactivatedTextColor = getColor(R.attr.colorDeactivated);
    }


    /**
     * Gets a color from the ViewItem style
     * @param colorRes
     * @return
     */
    private int getColor(int colorRes) {
        int[] attrs = {colorRes};
        @SuppressLint("ResourceType") TypedArray ta = context.obtainStyledAttributes(R.style.ViewItem, attrs);
        int defaultColor = ContextCompat.getColor(context, R.color.black);
        int textColor = ta.getColor(0, defaultColor);
        ta.recycle();

        return textColor;
    }

    /**
     * Returns grey status
     * @return true if switch is disabled, false if switch is enabled.
     */
    public boolean isDisabled(){
        return deactivatedStatus;
    }


    public void setGreyStatus(boolean greyStatus) {
        if (greyStatus) {
            this.deactivatedStatus = true;
            colorStatus = deactivatedTextColor;
        }
        else {
            this.deactivatedStatus = false;
            colorStatus = defaultTextColor;
        }


    }
    public int getColor(){
        return colorStatus;
    }

    /**
     * Determines whether the evidence is in the ghost list pool.
     * @return True (if at least one ghost has this evidence) or false
     */
    public Boolean isValid(List<GhostList> glist){
        for (GhostList ghost : glist) {
            if (ghost.isMatched(evidenceText)){
                return true;
            }
        }
        return false;
    }
}
