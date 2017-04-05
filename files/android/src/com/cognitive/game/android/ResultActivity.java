package com.cognitive.game.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jiaxing on 29/11/2016.
 */

public class ResultActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> result_list_title;
    ArrayList<String> result_list_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        TextView goldenText = (TextView)findViewById(R.id.gold_text);
        System.out.println("^^^^^^^^^^^^^^^^" + EndActivity.coins_final);
        goldenText.setText(" x " + String.valueOf(EndActivity.coins_final));
        goldenText.setVisibility(View.VISIBLE);
        result_list_title = AndroidLauncher.finalResTit;
        result_list_value = AndroidLauncher.finalResVal;

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerViewAdapter(this, result_list_title, result_list_value);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mf = this.getMenuInflater();
        mf.inflate(R.menu.back_to_end, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.back_to_end){

            Intent intent = new Intent (this, EndActivity.class);
            intent.putExtra("Coins", EndActivity.coins_final);
            startActivity(intent);
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
