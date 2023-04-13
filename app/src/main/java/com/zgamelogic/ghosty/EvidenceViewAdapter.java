package com.zgamelogic.ghosty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

/**
 * View adapter class for evidence list.
 * This class allows us to tailor the recyclerview to our needs. It ties together
 * all of the various classes for the list and defines the functionality of the list.
 */
public class EvidenceViewAdapter extends RecyclerView.Adapter<EvidenceHolder> {
    List<EvidenceList> itemList = Collections.emptyList();

    Context context;
    View.OnClickListener listener;

    public EvidenceViewAdapter(List<EvidenceList> itemList, Context context, View.OnClickListener listener)
    {
        this.itemList = itemList;
        this.context = context;
        this.listener = listener;
    }

    /**
     * Creates EvidenceHolder objects for each view that is created.
     * Each EvidenceHolder will represent one view that will be recycled
     * during scrolling to show different list items as necessary
     * @param parent idk, following tutorial
     * @param viewType idk, following tutorial
     * @return EvidenceHolder for a specific view to be recycled in the recyclerview
     */
    @Override
    public EvidenceHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout
        //creates a generic xml view
        View viewItem = inflater.inflate(R.layout.view_item, parent, false);

        EvidenceHolder viewHolder = new EvidenceHolder(viewItem);
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
    public void onBindViewHolder(final EvidenceHolder viewHolder, final int position)
    {
        //int index = viewHolder.getAdapterPosition(); // Redundant with position param?
        // Set the text for the list item
        viewHolder.toggleName.setText(itemList.get(position).evidenceText);

        // Set functionality for the toggle button
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
