package com.wayruha;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainMethods {

    private static final Logger log = (Logger) LogManager.getLogger(MainMethods.class);
    private static Random rnd = new Random();


    public static ArrayList<Individual> selectBest(Generation generation) {
        ArrayList<Individual> list = new ArrayList<>(2);
        List<Individual> population = generation.getPopulation();
        Individual bestIndividual = population.get(0), secondBestIndividual = population.get(1);
        // markGeneration(population);
        int points;
        for (Individual object : population) {
            points = object.getFitness();
            if (points > bestIndividual.getFitness())
                bestIndividual = object;
            if (points > secondBestIndividual.getFitness() && points < bestIndividual.getFitness())
                secondBestIndividual = object;
        }
        list.add(bestIndividual);
        list.add(secondBestIndividual);

        //log.trace("Best points in "+generation+" are:"+bestIndividual.getFitness()+" and "+ secondBestIndividual.getFitness());
        return list;
    }
}
