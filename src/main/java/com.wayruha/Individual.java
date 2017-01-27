package com.wayruha;

import com.wayruha.methods.IMutate;
import com.wayruha.fitness.Action;

public class Individual {
    // in this case, our genome is made up from integer numbers from fitness.Action.values()
    private String genome;
    private int fitness;
    private Generation generation;
    public int id;

    public Individual(String genome) {
        this.genome=genome;
    }
    public Individual() {
        this(randomGenerate());
    }


    //generate random genome
    public static String randomGenerate(){
        StringBuilder bld=new StringBuilder();
        for(int i=0;i<Main.GENOME_LENGTH;i++){
            bld.append(Main.rnd.nextInt(Action.values().length));
        }
        return bld.toString();
    }

    public void mutate(IMutate mutateTechnique){
        this.genome=mutateTechnique.mutate(genome);

    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getFitness() {
        return fitness;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public String getGenome() {
        return genome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Individual)) return false;

        Individual that = (Individual) o;

        return genome.equals(that.genome);

    }

    @Override
    public int hashCode() {
        return genome.hashCode();
    }

    @Override
    public String toString() {
        return "["+generation==null?"-":generation.id+"]"+"["+id+"]("+fitness+")"+"-"+genome;
    }
}
