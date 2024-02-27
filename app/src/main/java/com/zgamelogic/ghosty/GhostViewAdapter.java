package com.zgamelogic.ghosty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

/**
 * View adapter class for ghost list.
 * This class allows us to tailor the recyclerview to our needs. It ties together
 * all of the various classes for the list and defines the functionality of the list.
 */
public class GhostViewAdapter extends RecyclerView.Adapter<GhostHolder> {
    List<GhostList> itemList = Collections.emptyList();

    Context context;
    View.OnClickListener listener;



    public GhostViewAdapter(List<GhostList> itemList, Context context, View.OnClickListener listener)
    {
        this.itemList = itemList;
        this.context = context;
        this.listener = listener;
    }

    /**
     * Creates GhostHolder objects for each view that is created.
     * Each GhostHolder will represent one view that will be recycled
     * during scrolling to show different list items as necessary
     * @param parent idk, following tutorial
     * @param viewType idk, following tutorial
     * @return GhostHolder for a specific view to be recycled in the recyclerview
     */
    @Override
    public GhostHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout
        //creates a generic xml view
        View viewItem = inflater.inflate(R.layout.view_item, parent, false);

        GhostHolder viewHolder = new GhostHolder(viewItem);
        return viewHolder;
    }

    /**
     * Binds data to a view_item
     * As we scroll, old view_items that move off the screen are recycled as new view_items
     * moving onto the screen. This method is called to bind new data to these recycled views.
     * Additional reading: https://stackoverflow.com/questions/37523308/when-onbindviewholder-is-called-and-how-it-works
     * @param viewHolder java object representing the view_item xml
     * @param position indicates the position in our list of items
     */
    @Override
    public void onBindViewHolder(final GhostHolder viewHolder, final int position)
    {
        //int index = viewHolder.getAdapterPosition(); // Redundant with position param?
        // Set the text for the list item
        viewHolder.ghostName.setText(itemList.get(position).ghostName);
        viewHolder.evidence1.setText(itemList.get(position).evidence1);
        viewHolder.evidence2.setText(itemList.get(position).evidence2);
        viewHolder.evidence3.setText(itemList.get(position).evidence3);

        // Set functionality for clicking this list item
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                listener.onClick(view);
            }
        });
    }

    /**
     * Get number of items on the list
     * @return number of items on the list
     */
    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    /**
     * This is called when the recyclerview starts "observing" this adapter object
     * Additional reading:
     * https://stackoverflow.com/questions/51069491/the-difference-between-onviewrecycled-ondetachedfromrecyclerview-and-onviewde
     * https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter#onAttachedToRecyclerView(androidx.recyclerview.widget.RecyclerView)
     * @param recyclerView RecyclerView for the evidence list
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}