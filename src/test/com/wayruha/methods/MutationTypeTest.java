package com.wayruha.methods;

import org.junit.Test;
import com.wayruha.Individual;

public class MutationTypeTest {

    @Test
    public void mutationTest() {
      /*  for (int i = 0; i < 100; i++) {
            for (MutationType type : MutationType.values()) {
                System.out.println("Testing #"+i+" " + type);
                Individual ind = new Individual();
                String oldGenome = ind.getGenome();
                ind.mutate(type.getMutationTechnique());
                String newGenome = ind.getGenome();

                System.out.println(oldGenome);
                System.out.println(newGenome);
                Assert.assertNotEquals(oldGenome, newGenome);
            }

        }*/
        MutationType type=MutationType.EXCHANGE;
        Individual ind = new Individual();
        String oldGenome = ind.getGenome();
        ind.mutate(type.getMutationTechnique());
        String newGenome = ind.getGenome();

        System.out.println(oldGenome);
        System.out.println(newGenome);
     //   Assert.assertNotEquals(oldGenome, newGenome);
    }


}