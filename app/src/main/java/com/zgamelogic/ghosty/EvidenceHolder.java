package com.zgamelogic.ghosty;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * purpose: represents the XML of a single list item.
 */
public class EvidenceHolder extends RecyclerView.ViewHolder{


    TextView toggleName;
    View view;
    //Creates both a TextView and a View.
    //itemView is local and view is global.
    //Placeholder for 'Toggle switch.'

    public EvidenceHolder(View itemView) {
        super(itemView);
            //What is super for?
        toggleName = (TextView)itemView.findViewById(R.id.evidenceView);

        view = itemView;
    }


}
//Need to add 'framework' for future toggle operations.