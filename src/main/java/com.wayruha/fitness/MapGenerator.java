package com.wayruha.fitness;


public class MapGenerator {

    Point startPoint;

    public MapGenerator(Point startPoint) {
        this.startPoint = startPoint;

    }

    public boolean[][] generateMap(int size, double rarefaction) {
        //generate  bool map using some rule
        boolean[][] map = new boolean[size][size];
        for (int k = 0; k < size; k++) {
            for (int l = 0; l < size; l++) {
                map[k][l] = Math.random() < rarefaction / 100d;
            }
        }
        map[startPoint.getY()][startPoint.getX()] = false;

        return map;
    }

}
