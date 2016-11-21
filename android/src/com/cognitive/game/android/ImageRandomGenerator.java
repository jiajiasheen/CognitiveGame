package com.cognitive.game.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Draco on 2016-11-21.
 */

public class ImageRandomGenerator {
    public static int result;

    public static Integer[][] qAndSGenerator(){

        Integer[][] res = new Integer[4][5];
        Integer res1[] = new Integer[5];
        Integer res2[] = new Integer[5];
        Integer res3[] = new Integer[5];
        Integer res4[] = new Integer[5];
        Integer shuff[] = new Integer[5];
        ArrayList<Integer> currArr= new ArrayList<>();
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < res1.length; i ++){
            int temp = randomWithRange(0, 9, currArr);
            res1[i] = temp;
            currArr.add(temp);
        }
        shuff = res1.clone();
        int ans = randomWithRange(1, 3, answer);
        result = ans;
        switch(ans){
            case 1:
                res2 = res1.clone();
                Collections.shuffle(Arrays.asList(shuff));
                res3 = shuff.clone();
                Collections.shuffle(Arrays.asList(shuff));
                res4 = shuff.clone();
                break;
            case 2:
                res3 = res1.clone();
                Collections.shuffle(Arrays.asList(shuff));
                res2 = shuff.clone();
                Collections.shuffle(Arrays.asList(shuff));
                res4 = shuff.clone();
                break;
            case 3:
                res4 = res1.clone();
                Collections.shuffle(Arrays.asList(shuff));
                res2 = shuff.clone();
                Collections.shuffle(Arrays.asList(shuff));
                res3 = shuff.clone();
                break;
        }
        res[0] = res1;
        res[1] = res2;
        res[2] = res3;
        res[3] = res4;
        return res;
    }
    private static int randomWithRange(int min, int max,ArrayList<Integer> currArr)
    {
        int range = (max - min) + 1;
        int temp = 0;
        boolean repeat = true;
        while (repeat == true){
            temp = (int)(Math.random() * range) + min;
            if (currArr.contains(temp) == false)
            {
                break;
            }
        }
        return temp;
    }
}
