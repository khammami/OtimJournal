package com.otimware.myjournal;

import android.arch.persistence.room.Query;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by OtimDiBossman on 6/29/2018.
 */

public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.EntryViewHolder> {
    protected static final String TAG = GreenAdapter.class.getSimpleName();
    private int mNumberItems;
    final private ListItemClickListener mOnClickListener;
    protected database DBEntries;
    protected DB_entity entity;
    protected Query DBQuery;

    public interface ListItemClickListener {
        void OnListItemClick(int clickedListItem);
    }

    public GreenAdapter(int numOfItem, ListItemClickListener listener) {
        mNumberItems = numOfItem;
        mOnClickListener = listener;

    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIDforListItem = R.layout.views_recycler;
        LayoutInflater inflater = LayoutInflater.from(context);
        Boolean AttachToParent = false;

        View view = inflater.inflate(layoutIDforListItem, viewGroup, AttachToParent);
        EntryViewHolder viewHolder = new EntryViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        DBEntries.journalDao().loadTaskById(position);
        String dateTime=entity.getDate();
        String Title=entity.getTitle();

        holder.holderTitle.setText(Title);
        holder.holderDate.setText(dateTime);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class EntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView holderTitle,holderDate;

        public EntryViewHolder(View ItemView) {
            super(ItemView);
            holderTitle = (TextView) ItemView.findViewById(R.id.text_entryholderTitle);
            holderDate=(TextView) ItemView.findViewById(R.id.entryholderDate);
            ItemView.setOnClickListener(this);


        }



        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.OnListItemClick(clickedPosition);
        }


    }
    public void setTasks(Query dbQuery){
        DBQuery=dbQuery;


    }


}
