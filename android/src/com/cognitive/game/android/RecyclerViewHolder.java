package com.cognitive.game.android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Jiaxing on 29/11/2016.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder{

    public TextView itemTitle;
    public TextView itemResult;

    public RecyclerViewHolder(View itemView){
        super(itemView);

        itemTitle = (TextView) itemView.findViewById(R.id.item_title);
        itemResult = (TextView) itemView.findViewById(R.id.item_result);
    }
}
