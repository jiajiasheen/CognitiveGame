package com.cognitive.game.android;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Draco on 2016-11-21.
 */

public class QuizFragment extends Fragment{
        private int[] numbers;
        private int pos = 0;
        private Integer correct = 0;
        private int nBack;
        private int hour_left = QuizActivity.hour_left;
        private View resultView;
        private long startTime;
        private long consumingTime;
        private String temp;
        private MediaPlayer mediaPlayer;

        //private int hour_left = 5;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            resultView = inflater.inflate(R.layout.quiz_layout, container, false);

            final TextView nText = (TextView) resultView.findViewById(R.id.N_Back_Number); //animation pop up text view
            final Button nextNumBtn = (Button) resultView.findViewById(R.id.next_btn); //initialize n-back question array
            final ImageView quiz_hint = (ImageView) resultView.findViewById(R.id.quiz_instruction); //image instruction button
            final Button corr_btn = (Button) resultView.findViewById(R.id.Correct);
            final Button wron_btn = (Button) resultView.findViewById(R.id.Wrong);

            nBack = QuizActivity.nBack;
            numbers = new QuestionRandomGenerator().generator(nBack);
            Log.i("===Nums array length: ", numbers.length + "");
            Log.i("===Backs: ", nBack + "");

            nText.setText(Integer.toString(numbers[pos]));

            //click event for next number button
            nextNumBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startTime = System.currentTimeMillis();
                    pos++;
                    nText.setText("");
                    nText.setText(Integer.toString(numbers[pos]));
                    nText.startAnimation(AnimationUtils.loadAnimation(resultView.getContext(), android.R.anim.slide_in_left));
                    if(pos == nBack) {
                        nextNumBtn.setVisibility(View.GONE);
                        corr_btn.setVisibility(View.VISIBLE);
                        wron_btn.setVisibility(View.VISIBLE);
                    }
                }
            });

            //click event for correct and wrong button
            corr_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if(pos < numbers.length) {
                    if(numbers[pos] == numbers[pos - nBack]) {
                        correct++;
                        Log.i("===correct", correct+"" + " numbers last: " + numbers[pos - nBack] + " number this: " + numbers[pos] +"");
                    }
                    Log.i("correct", correct+"" + " numbers last: " + numbers[pos - nBack] + " number this: " + numbers[pos] +"");
                    updateTextView(nText);
                    //}else
                    //FinishQuiz();
                }
            });

            wron_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(numbers[pos] != numbers[pos - nBack]) {
                        correct++;

                    }

                    updateTextView(nText);
                }
            });

            //click evnet for image construction
            quiz_hint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    generateHint(getActivity());
                }
            });

            onCreateHint(getActivity(), hour_left);


            return resultView;
        }

        private void updateTextView(TextView textView){
            pos++;
            if(pos < numbers.length) {

                textView.setText("");
                textView.setText(Integer.toString(numbers[pos]));
                textView.startAnimation(AnimationUtils.loadAnimation(resultView.getContext(), android.R.anim.slide_in_left));
            }
            else {
                consumingTime = System.currentTimeMillis() - startTime;
                FinishQuiz();
            }
        }

        private void FinishQuiz(){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Summary");
            builder.setMessage(correct.toString() + " correct among " + (numbers.length - nBack) + "" + "  time consuming: " + consumingTime / 1000 + " seconds");
            if (nBack == 3){
                temp = "Hard";
            }
            else{
                temp = "Normal";
            }
            AndroidLauncher.finalResTit.add(String.valueOf(nBack) + " Back  " + " Difficulty Level: " + temp);
            AndroidLauncher.finalResVal.add("Accuracy: " + correct.toString() + "/" + (numbers.length - nBack) + "  time consuming: " + consumingTime / 1000 + " seconds");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    QuizActivity.hour_left--;
                    Double accurcy = (double) correct / 10.0 * 100;

                    QuizActivity.accuracy_list.add(accurcy.intValue());
                    FragmentManager fragmentManager = getFragmentManager();

                    if(QuizActivity.hour_left == 5){ //if still has hours, back to main story
                        QuizFragment si = new QuizFragment();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.main_interface, si);
                        ft.commit();
                    }
                    else{
                        if (nBack == 3 && correct > 5){
                            QuizActivity.coins_quiz = QuizActivity.coins_quiz + 10;
                            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.coin_collect);
                            mediaPlayer.setLooping(false);
                            mediaPlayer.start();
                        }

                        Intent intent = new Intent (getActivity(), AndroidLauncher.class);
                        intent.putExtra("Player", QuizActivity.player_pos);
                        intent.putExtra("Box", QuizActivity.box_opened);
                        intent.putExtra("Logged", QuizActivity.logged);
                        intent.putExtra("Coins", QuizActivity.coins_quiz);
                        startActivity(intent);
                        getActivity().finish();

                        //TODO: didn't destroy current fragment, which leads wired behaviour when press back button
                    }
                }
            });
            builder.setCancelable(false);
            builder.create().show();
        }

        private void generateHint(Context context){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Question Instruction");
            builder.setMessage("This question requires you to finish " + nBack + "-back task, in this task," +
                    " you should try to indicate that if the current number matches the one from " +
                    nBack + " steps before. If it is, press correct button, otherwise press wrong button. " +
                    "You should press next number to start. Good luck traveller." );
            builder.setPositiveButton("Got it!", null);
            builder.setCancelable(false);
            builder.create().show();
        }

        private void onCreateHint(Context context, int hour){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            switch (hour){
                case 5:
                    generateHint(context);

                    break;

                case 4:
                    break;

                case 3:
                    builder.setTitle("Three Back!");
                    builder.setMessage("Things get a little bit harder, enjoy your 3-back challenge.");
                    builder.setPositiveButton("Ok", null);
                    builder.setCancelable(false);
                    builder.create().show();
                    break;

                case 2:
                    break;

                case 1:
                    builder.setTitle("Four Back!");
                    builder.setMessage("Oops! Things are getting insane. Monsters are trying to ask you " +
                            "questions that they even do not know the answer.");
                    builder.setPositiveButton("Alright...", null);
                    builder.setCancelable(false);
                    builder.create().show();
                    break;

                default:
                    break;
            }
        }
}
