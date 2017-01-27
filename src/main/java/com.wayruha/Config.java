package com.wayruha;

import com.wayruha.methods.CrossoverType;
import com.wayruha.methods.MutationType;

public class Config {
    private final int populationsCount, individualsCount;
    private final double mutationChance;
    private CrossoverType crossoverType;
    private MutationType mutationType;

    public Config(int populatioCount, int individualsCount, double mutationChance, CrossoverType crossoverType, MutationType mutationType) {
        this.populationsCount = populatioCount;
        this.individualsCount = individualsCount;
        this.mutationChance = mutationChance;
        this.crossoverType = crossoverType;
        this.mutationType = mutationType;
    }

    @Override
    public String toString() {
        return  "[Gen:" + populationsCount + ",Ind:" + individualsCount + ",Mut.ch:" + mutationChance +
                ",Crssvr:"+crossoverType+",Mut.tp:"+mutationType+"] ";
    }

    public int getPopulationsCount() {
        return populationsCount;
    }

    public int getIndividualsCount() {
        return individualsCount;
    }

    public double getMutationChance() {
        return mutationChance;
    }

    public CrossoverType getCrossoverType() {
        return crossoverType;
    }

    public MutationType getMutationType() {
        return mutationType;
    }
}
