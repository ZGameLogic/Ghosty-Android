package com.zgamelogic.ghosty;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;


    // ViewHolder code for RecyclerView

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView ghostEvidence1;
    TextView ghostEvidence2;
    TextView ghostEvidence3;
    TextView ghostEvidence4;
    //TextView ghost;
    View view;

    ViewHolder(View itemView)
    {
        super(itemView);
        ghostEvidence1 = (TextView)itemView.findViewById(R.id.evidenceView1);

        ghostEvidence2 = (TextView)itemView.findViewById(R.id.evidenceView2);

        ghostEvidence3 = (TextView)itemView.findViewById(R.id.evidenceView3);

        ghostEvidence4 = (TextView)itemView.findViewById(R.id.evidenceView4);

        view = itemView;
     }
}



