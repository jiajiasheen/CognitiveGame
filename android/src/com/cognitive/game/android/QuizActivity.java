package com.cognitive.game.android;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by Draco on 2016-11-21.
 */

public class QuizActivity extends Activity {
    public static ArrayList<Integer> time_consump_list;
    public static ArrayList<Integer> accuracy_list;
    public static int nBack;
    public static Integer hour_left;
    public static float[] player_pos;
    public static boolean[] box_opened;
    public static boolean logged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_interface);

        int extra = getIntent().getIntExtra("Level", 2);
        player_pos = getIntent().getFloatArrayExtra("Player");
        box_opened = getIntent().getBooleanArrayExtra("Box");
        logged = getIntent().getBooleanExtra("Logged", true);

        time_consump_list = new ArrayList<>();
        accuracy_list = new ArrayList<>();
        nBack = extra;
        hour_left = 5;
        if(savedInstanceState == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            QuizFragment quizFragment = new QuizFragment();
            fragmentTransaction.add(R.id.main_interface, quizFragment);
            fragmentTransaction.commit();
        }

    }
}
