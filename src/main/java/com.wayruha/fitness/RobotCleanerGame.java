package com.wayruha.fitness;

import com.wayruha.Individual;

import java.util.ArrayList;

/*
* Example of solving the robot-cleaner problem
* Simple implementation of fitness function
* */
public class RobotCleanerGame implements IFitnessCalculator {
    static final int MAP_SIZE = 10,
            TEST_MAPS_COUNT = 50,
            MOVE_LIMIT = 3000;
    public static final Point startPoint = new Point(4, 4);
    public static ArrayList<boolean[][]> maps = new ArrayList<>();
    private static final double minRarefaction = 30, rarefactionStep = 0.3;
    static {
        MapGenerator generator = new MapGenerator(startPoint);
        for (int i = 0; i < TEST_MAPS_COUNT; i++) {
            maps.add(generator.generateMap(MAP_SIZE, minRarefaction + i * rarefactionStep));
        }
    }

    @Override
    public int fitness(Individual ind) {
        int fitness;
        int averagePoints = 0;
        //generate few maps to test our solution on
        //Its fitness is average of earned points on each map
        for (int i = 0; i < TEST_MAPS_COUNT; i++) {
            boolean[][] map = cloneMap(maps.get(i));
            TestSolution game = new TestSolution(ind, map, new Point(5, 5));
            int points = game.runGame();
            averagePoints += points;
        }
        fitness = averagePoints / TEST_MAPS_COUNT;
        ind.setFitness(fitness);
        return fitness;
    }

    private static boolean[][] cloneMap(boolean[][] map) {
        boolean[][] resMap = new boolean[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < map.length; i++) {
            boolean[] mapRow = map[i].clone();
            resMap[i] = mapRow;
        }
        return resMap;
    }

}
