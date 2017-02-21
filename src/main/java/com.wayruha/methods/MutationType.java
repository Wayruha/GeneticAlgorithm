package com.wayruha.methods;

import com.wayruha.Main;
import com.wayruha.fitness.Action;


public enum MutationType {

    RANDOM_INSERTION(genome -> {
        int posToInsert = Main.rnd.nextInt(Main.GENOME_LENGTH);
        StringBuilder strBld = new StringBuilder(genome);
        char randomChar=(char)(Main.rnd.nextInt(Action.values().length)+48);
        strBld.setCharAt(posToInsert,randomChar);
        return strBld.toString();
    }),
    /*
    * We simply choose two genes at random  and swap them:
    * */
    EXCHANGE(genome -> {
        int pos1 = Main.rnd.nextInt(Main.GENOME_LENGTH);
        int pos2 = Main.rnd.nextInt(Main.GENOME_LENGTH);
        while (pos2 == pos1) {
            pos2 = Main.rnd.nextInt(Main.GENOME_LENGTH);
        }

        StringBuilder strBld = new StringBuilder(genome);
        //swap chars at pos1 <-> pos2
        char char1 = genome.charAt(pos1);
        strBld.setCharAt(pos1, genome.charAt(pos2));
        strBld.setCharAt(pos2, char1);
        return strBld.toString();
    }),
    /*
    * Choose two random points and shuffle the genes located between them:
    * */
    SCRAMBLE(genome -> {
        int pos1 = Main.rnd.nextInt(Main.GENOME_LENGTH);
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
        StringBuilder strBld = new StringBuilder(genome);
        StringBuilder subStrBld = new StringBuilder(strBld.substring(pos1, pos2));
        //shuffle the substring
        char[] characters = subStrBld.toString().toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = (int) (Math.random() * characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        strBld.replace(pos1, pos2, new String(characters));
        return strBld.toString();
    }),
    /*
    * Select two random points grab the genes between them as a group,
    *  then reinsert the group at a random position displaced from the original.
    * */
    DISPLACEMENT(genome -> {
        int pos1 = Main.rnd.nextInt(Main.GENOME_LENGTH);
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
        StringBuilder strBld = new StringBuilder(genome);
        String substr = strBld.substring(pos1, pos2);
        strBld.delete(pos1, pos2);
        int insertPos = Main.rnd.nextInt(Main.GENOME_LENGTH - (pos2 - pos1)+1);
        strBld.insert(insertPos, substr);
        return strBld.toString();
    }),
    /*
    * Take random gene out then reinsert int at random position
    * */
    INSERTION(genome -> {
        int posToTake = Main.rnd.nextInt(Main.GENOME_LENGTH);
        int posToInsert = Main.rnd.nextInt(Main.GENOME_LENGTH);
        while (posToInsert == posToTake) {
            posToInsert = Main.rnd.nextInt(Main.GENOME_LENGTH);
        }
        StringBuilder strBld = new StringBuilder(genome);
        strBld.delete(posToTake, posToTake + 1);
        strBld.insert(posToInsert, genome.charAt(posToTake));
        return strBld.toString();
    }),
    /*
    * Select two random points and reverse the genes between them.
    * */
    INVERSION(genome -> {
        int pos1 = Main.rnd.nextInt(Main.GENOME_LENGTH);
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
        StringBuilder strBld = new StringBuilder(genome);
        StringBuilder subStrBld = new StringBuilder(strBld.substring(pos1, pos2));
        strBld.replace(pos1, pos2, subStrBld.reverse().toString());
        return strBld.toString();
    }),
    /*
    * Select two random points, reverse the gene order between the two points,
     * and then displace them somewhere.
    * */
    DISPLACED_INVERSION(genome -> {
        int pos1 = Main.rnd.nextInt(Main.GENOME_LENGTH);
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
        StringBuilder strBld = new StringBuilder(genome);
        StringBuilder subStrBld = new StringBuilder(strBld.substring(pos1, pos2));
        strBld.delete(pos1, pos2);
        int posToInsert = Main.rnd.nextInt(Main.GENOME_LENGTH - (pos2 - pos1)+1);
        strBld.insert(posToInsert, subStrBld.reverse().toString());
        return strBld.toString();
    });

    private IMutate mutationTechnique;

    MutationType(IMutate mutationTechnique) {
        this.mutationTechnique = mutationTechnique;
    }

    public IMutate getMutationTechnique() {
        return mutationTechnique;
    }
}
