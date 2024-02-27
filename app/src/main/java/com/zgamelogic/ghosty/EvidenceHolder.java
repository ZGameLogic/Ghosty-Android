package com.zgamelogic.ghosty;

import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Purpose: represents the XML of a single list item from the evidence list.
 */
public class EvidenceHolder extends RecyclerView.ViewHolder{

    public TextView toggleName;
    public Switch evSwitch;
    public View view;
    //itemView is local and view is global.

    public EvidenceHolder(View itemView) {
        super(itemView); // Call parent constructor
        toggleName = (TextView)itemView.findViewById(R.id.evidenceListString);
        evSwitch = (Switch)itemView.findViewById(R.id.simpleSwitchList);
        view = itemView;

        // Make views visible
        toggleName.setVisibility(View.VISIBLE);
        evSwitch.setVisibility(View.VISIBLE);
        view.setVisibility(View.VISIBLE);
    }
}