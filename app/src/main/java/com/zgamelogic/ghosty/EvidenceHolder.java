package com.zgamelogic.ghosty;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Purpose: represents the XML of a single list item from the evidence list.
 */
public class EvidenceHolder extends RecyclerView.ViewHolder{

    public TextView toggleName;
    public View view;
    //Creates both a TextView and a View.
    //itemView is local and view is global.
    //Placeholder for 'Toggle switch.'

    public EvidenceHolder(View itemView) {
        super(itemView); // Call parent constructor
        toggleName = (TextView)itemView.findViewById(R.id.evidenceListString);
        view = itemView;

        // Make views visible
        toggleName.setVisibility(View.VISIBLE);
        view.setVisibility(View.VISIBLE);
    }
}
//Need to add 'framework' for future toggle operations.