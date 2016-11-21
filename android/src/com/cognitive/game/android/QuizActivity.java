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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_interface);
        time_consump_list = new ArrayList<>();
        accuracy_list = new ArrayList<>();
        nBack = 2;
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
