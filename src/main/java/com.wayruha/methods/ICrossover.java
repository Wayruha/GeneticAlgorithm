package com.wayruha.methods;

import com.wayruha.Individual;

import java.util.Stack;

public interface ICrossover {
    Stack<Individual> crossover(Individual parentA, Individual parentB);
}
