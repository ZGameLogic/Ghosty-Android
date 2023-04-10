package com.zgamelogic.ghosty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;
import java.util.List;


//import com.zgamelogic.ghosty.examData;

public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<ListItem> list = Collections.emptyList();

    Context context;
    OnClickListener listener;

    public ViewAdapter(List<ListItem> list, Context context, OnClickListener listener)
    {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType)
    {

        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout
        //creates a generic xml view
        View photoView
                = inflater
                .inflate(R.layout.view_item,
                        parent, false);

        ViewHolder viewHolder
                = new ViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final ViewHolder viewHolder,
                     final int position)
    {
        int index = viewHolder.getAdapterPosition();
        viewHolder.evidenceText
                .setText(list.get(position).evidenceFilter);


        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                listener.onClick(view);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

