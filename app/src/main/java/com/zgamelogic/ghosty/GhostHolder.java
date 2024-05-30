package com.zgamelogic.ghosty;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Purpose: represents the XML of a single list item from the ghost list.
 */
public class GhostHolder extends RecyclerView.ViewHolder{
    // Elements in the list item
    public TextView ghostName;
    public TextView evidence1;
    public TextView evidence2;
    public TextView evidence3;

    // Entire view of the list item
    public View view;

    public GhostHolder(View itemView) {
        super(itemView); // Call parent constructor
        ghostName = (TextView)itemView.findViewById(R.id.ghostListName);
//        evidence1 = (TextView)itemView.findViewById(R.id.ghostEv1);
//        evidence2 = (TextView)itemView.findViewById(R.id.ghostEv2);
//        evidence3 = (TextView)itemView.findViewById(R.id.ghostEv3);
//        view = itemView;
//
//        // Make ghost name textview visible
//        ghostName.setVisibility(View.VISIBLE);
//
//        // Make linear layout serving as bottom row of textviews visible
//        view.findViewById(R.id.ghostEvRow).setVisibility(View.VISIBLE);
    }
}
