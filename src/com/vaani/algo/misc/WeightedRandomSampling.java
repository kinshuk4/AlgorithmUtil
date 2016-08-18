package com.vaani.algo.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeightedRandomSampling {

    private Map<Integer, Double> probs = new HashMap<Integer, Double>();
    private Random rnd = new Random();

    public WeightedRandomSampling(Map<Integer, Double> probs) {
        this.setProbs(probs);
    }

    public void setProbs(Map<Integer, Double> probs) {
        this.probs = probs;
    }

    public int randomSampling() {
        double accumulate = 0;
        double random = rnd.nextDouble();
        for (Map.Entry<Integer, Double> entry : probs.entrySet()) {
            accumulate += entry.getValue();
            if (accumulate > random) {
                return entry.getKey();
            }
        }
        return 0;
    }

}
