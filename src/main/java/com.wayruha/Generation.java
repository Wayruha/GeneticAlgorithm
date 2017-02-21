package com.wayruha;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.*;


public class Generation {
    private static final Logger log = (Logger) LogManager.getLogger(Generation.class);
    public int id;
    List<Individual> population;
    public static ArrayList<Generation> generationsList = new ArrayList<>();


    public Generation(Individual parentA, Individual parentB) {
        generationsList.add(this);
        this.id = generationsList.size();
        population = new ArrayList<>();
        if (parentA != null && parentB != null)
            log.info("Generation #" + id + " was created (best points=" + parentA.getFitness() + "," + parentB.getFitness() + ").");
    }

    public List<Individual> getPopulation() {
        return population;
    }

    public void addIndividual(Individual ind) {
        population.add(ind);
        ind.setGeneration(this);
        ind.setId(population.size());
    }

    @Override
    public String toString() {
        return "Generation #" + id;
    }
}
