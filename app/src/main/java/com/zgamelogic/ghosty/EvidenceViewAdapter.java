package com.zgamelogic.ghosty;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.zgamelogic.ghosty.data.Ghost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * View adapter class for evidence list.
 * This class allows us to tailor the recyclerview to our needs. It ties together
 * all of the various classes for the list and defines the functionality of the list.
 */
public class EvidenceViewAdapter extends RecyclerView.Adapter<EvidenceHolder> {
    List<EvidenceList> itemList = Collections.emptyList();

    List<String> toggleChecked = Collections.emptyList();

    GhostViewAdapter adapterG;
    Context context;
    View.OnClickListener listener;

    List<GhostList> glist;

    final String LOG_EVA = "eva";

    List<GhostList> graveYard = Collections.emptyList();
    //

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
        //int index = viewHolder.getAdapterPosition(); // Redundant with position param?
        // Set the text for the list item
        viewHolder.toggleName.setText(itemList.get(position).evidenceText);

        viewHolder.evSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Log.v("Switch State=", "" + isChecked);
                Switch switchView = (Switch) viewHolder.view.findViewById(R.id.simpleSwitchList);
                TextView textView = (TextView) viewHolder.view.findViewById(R.id.evidenceListString);
                String switchText = textView.getText().toString();
                //String ghostMatch;


                Boolean isglistModified = false;

                Log.i(LOG_EVA, switchText);

                if (isChecked) {
                    toggleChecked.add(switchText);
                    for (int i = 0; i < glist.size(); i++) {

                        if (!glist.get(i).isMatched(switchText)) {
                            Log.i(LOG_EVA, glist.get(i).ghostName + " was selected");
                            Log.i(LOG_EVA, "The size of glist before remove " + glist.size());
                            Log.i(LOG_EVA, "The size of adapterG list before remove " + adapterG.itemList.size());
                            graveYard.add(glist.remove(i));
                            Log.i(LOG_EVA, "The size of glist after remove " + glist.size());
                            Log.i(LOG_EVA, "The size of adapterG list after remove " + adapterG.itemList.size());
                            i--;
                            Log.i(LOG_EVA, "Every element in the list was moved over one");
                            Log.i(LOG_EVA, "isRemoved is set to 'true'");
                            isglistModified = true;
                        }

                    }


                }
                else{
                    toggleChecked.remove(switchText);
                    for (int i = 0; i < graveYard.size(); i++) {
                        if (graveYard.get(i).isValid(toggleChecked) ){
                            glist.add(graveYard.remove(i));
                            isglistModified = true;
                        }

                    }
                }
                if (isglistModified) {
                    adapterG.notifyDataSetChanged();
                    Log.i(LOG_EVA, "adapterG was notified");
                }
                //TODO: NEED TO TEST CODE.
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
/*

                            public remove (int element){
                                String wasRemoved = arr[element];
                                arr[element] = 0;
                                for (int i = element; i < arr[].size(); ++i){
                                    arr[i] = arr[i + 1];
                                }



                                return wasRemoved;
                            }

                            public remove (String object){
                                int i;

                                for (i = 0; arr[i] != object; ++i){

                                }
                                for (int j = i; j < arr[].size(); ++j){
                                    arr[j] = arr[j + 1];
                                }

                            }
*/