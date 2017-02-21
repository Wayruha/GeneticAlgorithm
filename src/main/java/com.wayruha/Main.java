package com.wayruha;

import com.wayruha.fitness.IFitnessCalculator;
import com.wayruha.fitness.RobotCleanerGame;
import com.wayruha.methods.CrossoverType;
import com.wayruha.methods.MutationType;

import java.util.Random;

public class Main {

    public static final int GENOME_LENGTH = 32;
    public static final Random rnd = new Random();
    private static final IFitnessCalculator FITNESS_CALCULATOR_INSTANCE = new RobotCleanerGame();
    private static final int INDIVIDUALS_COUNT = 40, POPULATION_COUNT = 4000;
    private static final double MUTATION_CHANCE = 0.5;
    private static final CrossoverType CROSSOVER_TYPE = CrossoverType.SINGLE_POINT;
    private static final MutationType MUTATION_TYPE = MutationType.RANDOM_INSERTION;


    public static void main(String[] args) {
        Config config = new Config(POPULATION_COUNT, INDIVIDUALS_COUNT, MUTATION_CHANCE, CROSSOVER_TYPE, MUTATION_TYPE);
        new Runner(config).run();
        /*compareCrossoverTechniques();
        compareMutationTechniques();
        compareMutationChances();*/
    }

    /*
    * Comparing different crossover techniques
    * */
    private static void compareCrossoverTechniques() {
        for (CrossoverType type : CrossoverType.values()) {
            Config config = new Config(POPULATION_COUNT, INDIVIDUALS_COUNT, MUTATION_CHANCE, type, MUTATION_TYPE);
            new Runner(config).run();
        }
    }

    /*
    * Comparing different mutation techniques
    * */
    private static void compareMutationTechniques() {
        for (MutationType type : MutationType.values()) {
            Config config = new Config(POPULATION_COUNT, INDIVIDUALS_COUNT, MUTATION_CHANCE, CROSSOVER_TYPE, type);
            new Runner(config).run();
        }
    }

    /*
    * Comparing mutation chances
    * */
    private static void compareMutationChances() {
        for (double chance = 0; chance < 1; chance += 0.1) {
            Config config = new Config(POPULATION_COUNT, INDIVIDUALS_COUNT, chance, CROSSOVER_TYPE, MUTATION_TYPE);
            new Runner(config).run();
        }
    }

    public static IFitnessCalculator getFitnessCalculatorInstance() {
        return FITNESS_CALCULATOR_INSTANCE;
    }
}
