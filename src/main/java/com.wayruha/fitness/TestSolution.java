package com.wayruha.fitness;

import com.wayruha.Individual;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class TestSolution {

    private static final Logger log = (Logger) LogManager.getLogger(TestSolution.class);
    boolean[][] map;
    private Point position;
    String algorithm;
    Individual object;
    private int gamePoints;

    public TestSolution(Individual object, boolean[][] map, Point startPos) {
        this.object = object;
        this.algorithm=object.getGenome();
        this.map = map;
        this.position = startPos;
        gamePoints = 0;
    }

    public int runGame() {
        int actionCode;
        int i = 0;
        //Play until the map is clean or moves is over
        while (!isMapClean() && i < RobotCleanerGame.MOVE_LIMIT) {
            int situationNumber = getSituationNumber(map, position);
            try {
                //what to do in current situation according to genome
                actionCode = algorithm.charAt(situationNumber) - 48;
                Action action = Action.values()[actionCode];
                doAction(action);
            } catch (Exception ex){
                log.error("Can`t do action in situation #"+ situationNumber +" and genome "+algorithm,ex);
            }
            i++;
        }
        return gamePoints;
    }

    private void doAction(Action action) {
   //     log.trace(position + " " + getsituationNumberuationNumber(map,position) + " Do " + action);
        if (action == Action.PICK_UP) {
            //if it`s "dirty" place and we try to pick up we will get more points
            if (map[position.getY()][position.getX()]) {
                gamePoints += 10;
                map[position.getY()][position.getX()] = false;
            } else {
                //if  there wasn`t any trash lose some points
                gamePoints -= 5;
            }
        } else {
            //each move costs some points. Like fuel or energy
            gamePoints -= 1;
            move(action);
        }

    }

    private void move(Action action) {
        int newX = position.getX() + action.getChangeX();
        int newY = position.getY() + action.getChangeY();
        //if new pos have acceptable  value  - make the changes
        if ((newX >= 0 && newX < RobotCleanerGame.MAP_SIZE) && (newY >= 0 && newY < RobotCleanerGame.MAP_SIZE))
            position.setLocation(newX,newY);
        else {
            //hit the border
            gamePoints -= 5;
            doAction(Action.MOVE_RANDOM);
        }

    }


    /*
    * Check surrounding cells and determine the situation
    * */
    public static int getSituationNumber(boolean[][]map, Point position) {
        int i = 0b00001, situationNumber = 0b00000;
        //was i*base +1
        if (isTrashRelativeToPosition(map,position,0, 0)) {
            situationNumber+=i;
        }
        i<<=1;
        if (isTrashRelativeToPosition(map,position,-1, 0)) {
            situationNumber+=i;
        }
        i<<=1;
        if (isTrashRelativeToPosition(map,position,0, -1)) {
            situationNumber+=i;
        }
        i<<=1;
        if (isTrashRelativeToPosition(map,position,1, 0)) {
            situationNumber+=i;
        }
        i<<=1;
        if (isTrashRelativeToPosition(map,position,0, 1)) {
            situationNumber+=i;
        }
        return situationNumber;
    }
    public static boolean isTrashRelativeToPosition(boolean[][] map,Point position,int offsetX, int offsetY) {
        try {
            return map[position.getY() + offsetY][position.getX() + offsetX];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return false;
        }

    }
    private boolean isMapClean() {
        for (boolean[] row : map)
            for (boolean value : row)
                if (value) return false;
        return true;
    }

}