package com.cognitive.game.android;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;

/**
 * Created by Draco on 2016-11-21.
 */

public class VisualActivity extends Activity {
    public static int IMAGECOUNTER;
    public static float[] player_pos_visual;
    public static boolean[] box_opened_visual;
    public static boolean logged;
    public static int coins_visual;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = MediaPlayer.create(this, R.raw.box_open);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
        setContentView(R.layout.visual_activity);
        int extra =getIntent().getIntExtra("ImageLevel",5);
        player_pos_visual = getIntent().getFloatArrayExtra("Player");
        box_opened_visual = getIntent().getBooleanArrayExtra("Box");
        logged = getIntent().getBooleanExtra("Logged", true);
        coins_visual = getIntent().getIntExtra("Coins", 0);
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
