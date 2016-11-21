package com.cognitive.game.android;

/**
 * Created by Draco on 2016-11-21.
 */

public class QuestionRandomGenerator {

    public int[] generator(int n)
    {
        int[] res = new int[n + 10];
        int trueCount = 3;
        int falseCount = 7;
        int indexCounter = 0;

        for(int i = 0;i < n;i++){
            res[i] = randomWithRange(0, 9);
        }

        for (int i = n; i < res.length; i ++)
        {
            if (randomWithRange(0, 11) < 5)
            {
                res[i] = res[i - n];
                trueCount--;
            }
            else
            {
                res[i] = randomWithRange(0, 9);
                if (res[i] == res [i - n])
                {
                    if (res[i] == 9)
                    {
                        res[i]--;
                    }
                    else
                    {
                        res[i]++;
                    }
                }
                falseCount--;
            }
            if (trueCount == 0 || falseCount == 0)
            {
                indexCounter = i;
                break;
            }
        }

        if (trueCount == 0)
        {
            for (int i = indexCounter + 1; i < res.length; i ++)
            {
                res[i] = randomWithRange(0, 9);
                if (res[i] == res[i - n])
                {
                    if (res[i] == 9)
                    {
                        res[i]--;
                    }
                    else
                    {
                        res[i]++;
                    }
                }
            }
        }
        if (falseCount == 0)
        {
            for(int i = indexCounter + 1; i < res.length; i ++)
            {
                res[i] = res[i - n];
            }
        }
        return res;
    }

    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

}
