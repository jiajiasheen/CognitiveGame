package com.cognitive.game.android;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by Jiaxing on 24/11/2016.
 */

public class DSSTActivity extends Activity {
    public static float[] player_pos_dsst;
    public static boolean[] box_opened_dsst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dsst_activity);
        player_pos_dsst = getIntent().getFloatArrayExtra("Player");
        box_opened_dsst = getIntent().getBooleanArrayExtra("Box");
        if(savedInstanceState == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            DSSTFragment dsst = new DSSTFragment();
            fragmentTransaction.add(R.id.dsst_activity, dsst);
            fragmentTransaction.commit();
        }
    }
}
