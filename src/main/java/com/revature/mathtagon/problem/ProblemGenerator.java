package com.revature.mathtagon.problem;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemGenerator {
    private final int numLength;
    private final int maxOps;

    public ProblemGenerator(int numLength, int maxOps) {
        this.numLength = numLength;
        this.maxOps = maxOps;
    }

    public Problem generate() throws ClassNotFoundException {
        Class<?> problemClass = Class.forName(Problem.class.getName());
        Class<?> probBuilderClass = Class.forName(ProblemBuilder.class.getName());

        List<Problem> problems = new ArrayList<>();

        return new Problem(5);
    }
}
