package com.zgamelogic.ghosty;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * View adapter class for evidence list.
 * This class allows us to tailor the recyclerview to our needs. It ties together
 * all of the various classes for the list and defines the functionality of the list.
 */
public class EvidenceViewAdapter extends RecyclerView.Adapter<EvidenceHolder> {
    List<EvidenceList> itemList = new ArrayList<EvidenceList>();
    List<String> toggleChecked = new ArrayList<String>();
    GhostViewAdapter adapterG;
    Context context;
    View.OnClickListener listener;
    List<GhostList> glist;
    final String LOG_EVA = "eva";
    List<GhostList> graveYard = new ArrayList<GhostList>();   //Ghost objects that don't match checked switches' names

    public EvidenceViewAdapter(List<EvidenceList> itemList, Context context, View.OnClickListener listener, GhostViewAdapter adapterG)
    {
        this.itemList = itemList;
        this.context = context;
        this.listener = listener;
        this.adapterG = adapterG;
        this.glist = adapterG.itemList;
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
        /*In order to avoid the listener repeatedly being triggered, we:
        * 1. Remove listener
        * 2. Update ViewHolder
        * 3. Add listener
        */
        viewHolder.evSwitch.setOnCheckedChangeListener(null);

        EvidenceList ev = itemList.get(position);

        // Set the text for the list item
        viewHolder.toggleName.setText(ev.evidenceText);
        viewHolder.toggleName.setTextColor(ev.getColor());

        //Set switch XML state
        viewHolder.evSwitch.setChecked(ev.switchState);

        //Enable or Disable current switch
        viewHolder.evSwitch.setEnabled(!ev.isDisabled());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //viewHolder.evSwitch.setThumbTintList(0);
        }

        viewHolder.evSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Switch switchView = (Switch) viewHolder.view.findViewById(R.id.simpleSwitchList);
                TextView textView = (TextView) viewHolder.view.findViewById(R.id.evidenceListString);
                String switchText = textView.getText().toString();
                Boolean isglistModified = false;
                Boolean isevidenceUiModified = false;
                EvidenceList ev;

                Log.i(LOG_EVA, "Switch is " + isChecked);
                Log.i(LOG_EVA, switchText);

                //Update toggleChecked, glist, and graveYard
                if (isChecked) {
                    toggleChecked.add(switchText);
                    for (int i = 0; i < glist.size(); i++) {
                        if (!glist.get(i).isMatched(switchText)) {
                            graveYard.add(glist.remove(i));
                            i--;
                            isglistModified = true;
                        }
                    }
                }
                else{
                    toggleChecked.remove(switchText);
                    Log.i(LOG_EVA, "Removed switch name from checked list");
                    for (int i = 0; i < graveYard.size(); i++) {
                        Log.i(LOG_EVA, "for loop started");
                        if (graveYard.get(i).isValid(toggleChecked) ){
                            glist.add(graveYard.remove(i));
                            i--;
                            isglistModified = true;
                            Log.i(LOG_EVA, "isglistModified is true");
                        }
                    }
                }

                //Update the GhostViewAdapter ui
                if (isglistModified) {
                    adapterG.notifyDataSetChanged();
                    Log.i(LOG_EVA, "adapterG was notified");
                }

                //Update evidence color and switch state
                for (int i = 0; i < itemList.size(); ++i) {
                    ev = itemList.get(i);

                    if (switchText.equals(ev.evidenceText)){
                        ev.switchState = isChecked;
                    }

                    if(!ev.isValid(glist) && !ev.isDisabled()){
                        Log.i(LOG_EVA, ev.evidenceText +" greyed out");
                        //UpdateGreyStatus
                        ev.setGreyStatus(true);
                        Log.i(LOG_EVA, ev.evidenceText +" status = grey");
                        isevidenceUiModified = true;
                        Log.i(LOG_EVA, "isevidenceUiModified is true");
                    }
                    else if(ev.isValid(glist) && ev.isDisabled()){
                        Log.i(LOG_EVA, ev.evidenceText +" grey be gone");
                        //UpdateGreyStatus
                        ev.setGreyStatus(false);
                        Log.i(LOG_EVA, ev.evidenceText +" status = not grey");
                        isevidenceUiModified = true;
                        Log.i(LOG_EVA, "isevidenceUiModified is true");
                    }
                    else {
                        Log.i(LOG_EVA, ev.evidenceText + " unchanged");
                    }
                }

                //Update the EvidenceViewAdapter ui
                if (isevidenceUiModified) {
                    notifyDataSetChanged();
                    Log.i(LOG_EVA, "EvidenceViewAdapter was notified");
                }

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
