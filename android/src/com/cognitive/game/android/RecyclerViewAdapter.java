package com.cognitive.game.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Jiaxing on 29/11/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewHolder>{
    private ArrayList<String> data_tit;
    private ArrayList<String> data_val;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<String> data_tit, ArrayList<String> data_val){
        this.data_tit = data_tit;
        this.data_val = data_val;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.result_recycler_list, parent,false);
        RecyclerViewHolder rcv = new RecyclerViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {


            String itemTitle2 = data_tit.get(position);
            String itemResult2 = data_val.get(position);
            holder.itemTitle.setText(itemTitle2);
            holder.itemResult.setText(itemResult2);

    }

    @Override
    public int getItemCount() {
        return this.data_tit.size();
    }
}
