package com.cognitive.game.android;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.VISIBLE;


/**
 * Created by Jiaxing on 23/11/2016.
 */

public class DSSTFragment extends Fragment {
    private ArrayList<TextView> textVacant = new ArrayList<TextView>();
    private ArrayList<TextView> textTest = new ArrayList<TextView>();
    private ArrayList<Integer> textClicked = new ArrayList<Integer>();
    private char[] answerArray = {'~', '!', '@', '#', '$', '%', '^', '{', '*', '('};
    private char[] selectionTextArray = {'~', '!', '@', '#', '$', '%', '^', '{', '*', '('};
    public int k;
    private TextView[] selection = new TextView[10];
    final int SPLASH_TIME_OUT = 10000;
    public String  finalAccuracy;

    public int[] testNumArray = new QuestionRandomGenerator().generator(10);
    public ArrayList<Integer> accCheck = new ArrayList<Integer>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View dsstView = inflater.inflate(R.layout.dsst_layout, container, false);

            textTest.add((TextView) dsstView.findViewById(R.id.textView111));
            textTest.add((TextView) dsstView.findViewById(R.id.textView112));
            textTest.add((TextView) dsstView.findViewById(R.id.textView113));
            textTest.add((TextView) dsstView.findViewById(R.id.textView114));
            textTest.add((TextView) dsstView.findViewById(R.id.textView115));
            textTest.add((TextView) dsstView.findViewById(R.id.textView116));
            textTest.add((TextView) dsstView.findViewById(R.id.textView117));
            textTest.add((TextView) dsstView.findViewById(R.id.textView118));
            textTest.add((TextView) dsstView.findViewById(R.id.textView119));
            textTest.add((TextView) dsstView.findViewById(R.id.textView120));
            for (int i = 0; i < 10; i ++){
                textTest.get(i).setText(String.valueOf(testNumArray[i]));
            }

            selection[0] = (TextView) dsstView.findViewById(R.id.textView168);
            selection[1] = (TextView) dsstView.findViewById(R.id.textView169);
            selection[2] = (TextView) dsstView.findViewById(R.id.textView166);
            selection[3] = (TextView) dsstView.findViewById(R.id.textView167);
            selection[4] = (TextView) dsstView.findViewById(R.id.textView170);
            selection[5] = (TextView) dsstView.findViewById(R.id.textView171);
            selection[6] = (TextView) dsstView.findViewById(R.id.textView172);
            selection[7] = (TextView) dsstView.findViewById(R.id.textView173);
            selection[8] = (TextView) dsstView.findViewById(R.id.textView174);
            selection[9] = (TextView) dsstView.findViewById(R.id.textView175);
            for (int i = 0; i < selection.length; i ++){
                selection[i].setText(String.valueOf(selectionTextArray[i]));
            }

            textVacant.add((TextView) dsstView.findViewById(R.id.textView121));
            textVacant.add((TextView) dsstView.findViewById(R.id.textView122));
            textVacant.add((TextView) dsstView.findViewById(R.id.textView123));
            textVacant.add((TextView) dsstView.findViewById(R.id.textView124));
            textVacant.add((TextView) dsstView.findViewById(R.id.textView125));
            textVacant.add((TextView) dsstView.findViewById(R.id.textView126));
            textVacant.add((TextView) dsstView.findViewById(R.id.textView127));
            textVacant.add((TextView) dsstView.findViewById(R.id.textView128));
            textVacant.add((TextView) dsstView.findViewById(R.id.textView129));
            textVacant.add((TextView) dsstView.findViewById(R.id.textView130));

/*
        for (int i = 0; i < 10; i ++) {
            k = i;
            selection[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    System.out.println("****************before k");
                    textClicked.add(k);
                    System.out.println(k);
                    for (int j = 0; j < textClicked.size(); j ++){
                        textVacant.get(j).setText(String.valueOf(answerArray[textClicked.get(j)]));
                        System.out.println("****************");
                        System.out.println(textClicked.get(j));
                        textVacant.get(j).setVisibility(VISIBLE);
                    }
                }
            });
        }
*/
            selection[0].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    textClicked.add(0);
                    for (int j = 0; j < textClicked.size(); j++) {
                        int temp = textClicked.get(j);
                        textVacant.get(j).setText(String.valueOf(answerArray[temp]));
                        textVacant.get(j).setVisibility(VISIBLE);
                        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^");
                        System.out.println(temp);
                    }

                }
            });
            selection[1].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    textClicked.add(1);
                    for (int j = 0; j < textClicked.size(); j++) {
                        textVacant.get(j).setText(String.valueOf(answerArray[textClicked.get(j)]));
                        textVacant.get(j).setVisibility(VISIBLE);

                    }
                    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                    System.out.println(textClicked.toString());
                }
            });
            selection[2].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    textClicked.add(2);
                    for (int j = 0; j < textClicked.size(); j++) {
                        textVacant.get(j).setText(String.valueOf(answerArray[textClicked.get(j)]));
                        textVacant.get(j).setVisibility(VISIBLE);
                    }
                }
            });
            selection[3].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    textClicked.add(3);
                    for (int j = 0; j < textClicked.size(); j++) {
                        textVacant.get(j).setText(String.valueOf(answerArray[textClicked.get(j)]));
                        textVacant.get(j).setVisibility(VISIBLE);
                    }

                }
            });
            selection[4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    textClicked.add(4);
                    for (int j = 0; j < textClicked.size(); j++) {
                        textVacant.get(j).setText(String.valueOf(answerArray[textClicked.get(j)]));
                        textVacant.get(j).setVisibility(VISIBLE);
                    }

                }
            });
            selection[5].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    textClicked.add(5);
                    for (int j = 0; j < textClicked.size(); j++) {
                        textVacant.get(j).setText(String.valueOf(answerArray[textClicked.get(j)]));
                        textVacant.get(j).setVisibility(VISIBLE);
                    }

                }
            });
            selection[6].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    textClicked.add(6);
                    for (int j = 0; j < textClicked.size(); j++) {
                        textVacant.get(j).setText(String.valueOf(answerArray[textClicked.get(j)]));
                        textVacant.get(j).setVisibility(VISIBLE);
                    }

                }
            });
            selection[7].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    textClicked.add(7);
                    for (int j = 0; j < textClicked.size(); j++) {
                        textVacant.get(j).setText(String.valueOf(answerArray[textClicked.get(j)]));
                        textVacant.get(j).setVisibility(VISIBLE);
                    }

                }
            });
            selection[8].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    textClicked.add(8);
                    for (int j = 0; j < textClicked.size(); j++) {
                        textVacant.get(j).setText(String.valueOf(answerArray[textClicked.get(j)]));
                        textVacant.get(j).setVisibility(VISIBLE);
                    }
                }
            });
            selection[9].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    textClicked.add(9);
                    for (int j = 0; j < textClicked.size(); j++) {
                        textVacant.get(j).setText(String.valueOf(answerArray[textClicked.get(j)]));
                        textVacant.get(j).setVisibility(VISIBLE);
                    }
                }
            });


            onCreateIns(getActivity());
/*
                new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    finalAccuracy = accuracyCheck(textClicked,testNumArray);
                    resultDisplay();

                }
            }, SPLASH_TIME_OUT);

*/
        return dsstView;
    }

    public String accuracyCheck(ArrayList<Integer> listA, int[] arrayB){
        String accuracy;
        int counterCorr = 0;
        int counterFal = 0;
        if (listA.size() <= arrayB.length){
            for (int i = 0; i < listA.size(); i ++){
                if ( listA.get(i) == arrayB[i]){
                    counterCorr ++;
                }
                else {
                    counterFal ++;
                }
            }
        }
        accuracy = "You Got " + counterCorr + " Correct" + " , " + counterFal + " Wrong ";
        return accuracy;
    }
    private void resultDisplay(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Your Time is Up");
        builder.setMessage(finalAccuracy);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*
                FragmentManager fragmentManager = getFragmentManager();
                MainStoryFragment mst = new MainStoryFragment();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_interface, mst);
                ft.commit();
                */
                Intent intent = new Intent (getActivity(), AndroidLauncher.class);
                intent.putExtra("Player",DSSTActivity.player_pos_dsst);
                intent.putExtra("Box", DSSTActivity.box_opened_dsst);
                intent.putExtra("Logged", DSSTActivity.logged);
                startActivity(intent);
                getActivity().finish();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
    private void onCreateIns(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("DSST Instruction");
        builder.setMessage("Digital Symbol Substitution Test: 0 -> ~, 1 -> !, 2 -> @, " +
                "3 -> #, 4 -> $, 5 -> %, 6 -> ^, 7 -> {, 8 -> *, 9 -> " +
                "(      You have 10 seconds once started");
        builder.setPositiveButton("Ready", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        finalAccuracy = accuracyCheck(textClicked,testNumArray);
                        resultDisplay();

                    }
                }, SPLASH_TIME_OUT);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
}
