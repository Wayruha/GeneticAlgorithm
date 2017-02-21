package com.wayruha;


import com.wayruha.methods.ICrossover;
import com.wayruha.methods.IMutate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.FileAppender;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Runner {

    private static final Logger log = (Logger)LogManager.getLogger(Runner.class);
    LoggerContext context = (LoggerContext) LogManager.getContext(false);
    private Config config;
    ICrossover crossoverTechnique;
    IMutate mutationTechnique;

    public Runner(Config config) {
        this.config = config;
        this.crossoverTechnique = config.getCrossoverType().getCrossoveringTechnique();
        this.mutationTechnique = config.getMutationType().getMutationTechnique();
    }

    /*
    * Start genetic algorithm with specified configurations
    * */
    public int run() {

        clearLogFiles();
        List<Individual> bestInPopulation;
        Individual parentA;
        Individual parentB;
        //evolve first generation by hand randomly
        Generation generation = evolveNewRandomGeneration();
        //loop for number of generations
        while (Generation.generationsList.size() < config.getPopulationsCount()) {
            bestInPopulation = MainMethods.selectBest(generation);
            parentA = bestInPopulation.get(0);
            parentB = bestInPopulation.get(1);
            generation = evolveNewGeneration(parentA, parentB);

        }
        bestInPopulation = MainMethods.selectBest(generation);
        parentA = bestInPopulation.get(0);
        parentB = bestInPopulation.get(1);
        log.info(config.toString() + "Best solutions:" + parentA + " and " + parentB);
        Generation.generationsList.clear();
        return parentA.getFitness();
    }

    /*
    * The first generation is made up from a random individuals
    * */
    private Generation evolveNewRandomGeneration() {
        Generation generation = new Generation(null, null);
        for (int i = 0; i < config.getIndividualsCount(); i++) {
            Individual ind = new Individual();
            ind.setGeneration(generation);
            if (Math.random() < config.getMutationChance())
                ind.mutate(mutationTechnique);
            generation.addIndividual(ind);
            Main.getFitnessCalculatorInstance().fitness(ind);
            log.trace(ind);
        }
        return generation;
    }


    private Generation evolveNewGeneration(Individual parentA, Individual parentB) {
        Generation generation = new Generation(parentA, parentB);
        log.trace("-----" + generation + "----- from " + parentA + " and " + parentB);
        //putting individuals into the new generation
        generation.addIndividual(parentA);
        generation.addIndividual(parentB);

        while (generation.getPopulation().size() < config.getIndividualsCount()) {
            outer:
            //prevent from evolving individuals that are already in population
            while (true) {
                Stack<Individual> children = crossoverTechnique.crossover(parentA, parentB);
                for (Individual ind : children) {
                    //mutate with some chance
                    if (Math.random() < config.getMutationChance())
                        ind.mutate(mutationTechnique);

                    if (generation.getPopulation().contains(ind))
                        continue outer;
                    generation.addIndividual(ind);
                    Main.getFitnessCalculatorInstance().fitness(ind);
                    log.trace(ind);
                }
                break;
            }
        }
        return generation;
    }

    private void clearLogFiles() {
        ArrayList<File> fileList = new ArrayList<>();
        Logger logger= (Logger) LogManager.getLogger(Generation.class);
        logger.getAppenders();
        FileAppender appender = (FileAppender) ((Logger)LogManager.getLogger(Generation.class)).getAppenders().get("file");
        fileList.add(new File(appender.getFileName()));
        appender = (FileAppender) ((Logger)LogManager.getLogger(Runner.class)).getAppenders().get("generations-file-log");
        fileList.add(new File(appender.getFileName()));

        for (File logFile : fileList) {
            try (FileWriter writer = new FileWriter(logFile)) {
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
