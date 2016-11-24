package com.cognitive.game.android;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by Draco on 2016-11-21.
 */

public class VisualActivity extends Activity {
    public static int IMAGECOUNTER;
    public static float[] player_pos_visual;
    public static boolean[] box_opened_visual;
    public static boolean logged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visual_activity);
        int extra =getIntent().getIntExtra("ImageLevel",5);
        player_pos_visual = getIntent().getFloatArrayExtra("Player");
        box_opened_visual = getIntent().getBooleanArrayExtra("Box");
        logged = getIntent().getBooleanExtra("Logged", true);
        IMAGECOUNTER = extra;
        if(savedInstanceState == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            VisualMemFragment vm = new VisualMemFragment();
            fragmentTransaction.add(R.id.visual_activity, vm);
            fragmentTransaction.commit();
        }
    }
}
