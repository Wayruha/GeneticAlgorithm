package com.wayruha.methods;

import com.wayruha.Main;
import com.wayruha.Individual;

import java.util.Stack;

public enum CrossoverType {
    /*
    * A single crossover point on both parents' organism strings is selected.
    * All data beyond that point in either organism string is swapped between the two parent organisms
    * */
    SINGLE_POINT((parentA, parentB) -> {
        int pos = Main.rnd.nextInt(Main.GENOME_LENGTH + 1);
        //swaping parts of the genomes
        String newGenome1 = parentA.getGenome().substring(0, pos);
        newGenome1 += parentB.getGenome().substring(pos, Main.GENOME_LENGTH);
        String newGenome2 = parentB.getGenome().substring(0, pos);
        newGenome2 += parentA.getGenome().substring(pos, Main.GENOME_LENGTH);

        Stack<Individual> newIndividuals = new Stack<>();
        newIndividuals.push(new Individual(newGenome1));
        newIndividuals.push(new Individual(newGenome2));
        return newIndividuals;
    }),
    /*
  * Two-point crossover calls for two points to be selected on the parent organism strings.
  * Everything between the two points is swapped between the parent organisms
  * */
    TWO_POINT((parentA, parentB) -> {
        int pos1 = Main.rnd.nextInt(Main.GENOME_LENGTH + 1);
        int pos2 = Main.rnd.nextInt(Main.GENOME_LENGTH + 1);
        while (pos2 == pos1) {
            pos2 = Main.rnd.nextInt(Main.GENOME_LENGTH);
        }
        //in case pos1>pos2 - swap them
        if (pos1 > pos2) {
            int tmp = pos1;
            pos1 = pos2;
            pos2 = tmp;
        }
        //swaping parts of the genomes
        String newGenome1 = parentA.getGenome().substring(0, pos1);
        newGenome1 += parentB.getGenome().substring(pos1, pos2);
        newGenome1 += parentA.getGenome().substring(pos2, Main.GENOME_LENGTH);
        String newGenome2 = parentB.getGenome().substring(0, pos1);
        newGenome2 += parentA.getGenome().substring(pos1, pos2);
        newGenome2 += parentB.getGenome().substring(pos2, Main.GENOME_LENGTH);

        Stack<Individual> newIndividuals = new Stack<>();
        newIndividuals.push(new Individual(newGenome1));
        newIndividuals.push(new Individual(newGenome2));
        return newIndividuals;
    }),
    /*
     * The uniform crossover uses a fixed mixing ratio between two parents.
     *If the mixing ratio is 0.5, the offspring has approximately half of the genes from first parent and the other half from second parent
     * */
    UNIFORM((parentA, parentB) -> {
        StringBuilder newGenome1 = new StringBuilder(), newGenome2 = new StringBuilder();
        String genome1 = parentA.getGenome();
        String genome2 = parentB.getGenome();
        for (int i = 0; i < genome1.length(); i++) {
            if (Math.random() < 0.5d) {  //default mixing ratio = 0.5
                //direct passing genes
                newGenome1.append(genome1.charAt(i));
                newGenome2.append(genome2.charAt(i));
            } else {
                //swapping genes
                newGenome1.append(genome2.charAt(i));
                newGenome2.append(genome1.charAt(i));
            }
        }
        Stack<Individual> newIndividuals = new Stack<>();
        newIndividuals.push(new Individual(newGenome1.toString()));
        newIndividuals.push(new Individual(newGenome2.toString()));
        return newIndividuals;
    });

    private ICrossover crossoveringTechnique;

    CrossoverType(ICrossover crossoveringTechnique) {
        this.crossoveringTechnique = crossoveringTechnique;
    }

    public ICrossover getCrossoveringTechnique() {
        return crossoveringTechnique;
    }
}
