package com.cognitive.game.android;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by Draco on 2016-11-21.
 */

public class VisualActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visual_activity);

        if(savedInstanceState == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            VisualMemFragment vm = new VisualMemFragment();
            fragmentTransaction.add(R.id.visual_activity, vm);
            fragmentTransaction.commit();
        }
    }
}
