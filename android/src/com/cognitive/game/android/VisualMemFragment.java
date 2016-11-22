package com.cognitive.game.android;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Arrays;

import static com.cognitive.game.android.ImageRandomGenerator.result;

/**
 * Created by Draco on 2016-11-21.
 */

public class VisualMemFragment extends Fragment {
    private View resultView;
    private ImageView img1, img2, img3, img4, img5;
    private ImageView[] img = new ImageView[15];
    private Integer[][] qus = new Integer[4][5];
    private Button readyBtn, aBtn, bBtn, cBtn;
    private int[] resource ={R.drawable.mc1, R.drawable.mc2, R.drawable.mc3, R.drawable.mc4, R.drawable.mc5,
            R.drawable.mc6,R.drawable.mc7,R.drawable.mc8,R.drawable.mc9,R.drawable.mc10};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resultView = inflater.inflate(R.layout.visualmem_layout, container, false);
/*
        img1 = (ImageView) resultView.findViewById(R.id.visual1);
        img2 = (ImageView) resultView.findViewById(R.id.visual2);
        img3 = (ImageView) resultView.findViewById(R.id.visual3);
        img4 = (ImageView) resultView.findViewById(R.id.visual4);
        img5 = (ImageView) resultView.findViewById(R.id.visual5);
*/
        img[0] = (ImageView) resultView.findViewById(R.id.visual1);
        img[1] = (ImageView) resultView.findViewById(R.id.visual2);
        img[2] = (ImageView) resultView.findViewById(R.id.visual3);
        img[3] = (ImageView) resultView.findViewById(R.id.visual4);
        img[4] = (ImageView) resultView.findViewById(R.id.visual5);
        img[5] = (ImageView) resultView.findViewById(R.id.visual6);
        img[6] = (ImageView) resultView.findViewById(R.id.visual7);
        img[7] = (ImageView) resultView.findViewById(R.id.visual8);
        img[8] = (ImageView) resultView.findViewById(R.id.visual9);
        img[9] = (ImageView) resultView.findViewById(R.id.visual10);
        img[10] = (ImageView) resultView.findViewById(R.id.visual11);
        img[11] = (ImageView) resultView.findViewById(R.id.visual12);
        img[12] = (ImageView) resultView.findViewById(R.id.visual13);
        img[13] = (ImageView) resultView.findViewById(R.id.visual14);
        img[14] = (ImageView) resultView.findViewById(R.id.visual15);

        readyBtn = (Button) resultView.findViewById(R.id.ready_btn);
        aBtn = (Button) resultView.findViewById(R.id.A_btn);
        bBtn = (Button) resultView.findViewById(R.id.B_btn);
        cBtn = (Button) resultView.findViewById(R.id.C_btn);
        qus = new ImageRandomGenerator().qAndSGenerator(5);
        System.out.print(Arrays.deepToString(qus));
        System.out.print("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
/*
        img1.setImageResource(resource[qus[0][0]]);
        img2.setImageResource(resource[qus[0][1]]);
        img3.setImageResource(resource[qus[0][2]]);
        img4.setImageResource(resource[qus[0][3]]);
        img5.setImageResource(resource[qus[0][4]]);
*/
        for (int i = 0; i < 5; i ++){
            img[i].setImageResource(resource[qus[0][i]]);
        }

        readyBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int count = 0;
                for (int j = 1;j < 4; j ++ ){
                    for (int k = 0; k < 5; k ++){
                        img[count].setImageResource(resource[qus[j][k]]);
                        count ++;
                    }
                }
                readyBtn.setVisibility(View.GONE);
                aBtn.setVisibility(View.VISIBLE);
                bBtn.setVisibility(View.VISIBLE);
                cBtn.setVisibility(View.VISIBLE);
            }
        });
        aBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (result == 1){
                    correctSol();
                }
                else{
                    wrongSol();
                }
            }
        });
        bBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (result == 2){
                    correctSol();
                }
                else{
                    wrongSol();
                }
            }
        });
        cBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (result == 3){
                    correctSol();
                }
                else{
                    wrongSol();
                }
            }
        });
        return resultView;
    }
    private void correctSol(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Correct!");
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
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
    private void wrongSol(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("False");
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
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
}
