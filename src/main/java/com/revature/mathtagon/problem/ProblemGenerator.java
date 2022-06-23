package com.revature.mathtagon.problem;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProblemGenerator {
    private final int numLength;
    private int maxOps;
    private int operations;
    private final Random seeder;

    public ProblemGenerator(int numLength, int maxOps) {
        this.numLength = numLength;
        this.maxOps = maxOps;
        this.seeder = new Random();
    }

    public Problem generate() {
        operations = maxOps;
        ProblemBuilder pb = new ProblemBuilder(generateHelper());
        while(operations-- > 0) {
            if(seeder.nextInt(2) == 0) {
                pb.postOp(Problem.Bin_Operator.values()[seeder.nextInt(4)],
                        generateHelper());
            }
            else pb.preOp(generateHelper(),
                    Problem.Bin_Operator.values()[seeder.nextInt(4)]);
        }

        return new Problem(pb);
    }

    private Problem generateHelper() {
        if (operations-- > 0) {
            switch(seeder.nextInt(4)) {
                case 0:
                    return new Problem(
                            new Problem(seeder.nextInt((int)Math.pow(10, numLength))),
                            Problem.Bin_Operator.values()[seeder.nextInt(4)],
                            new Problem(seeder.nextInt((int)Math.pow(10, numLength))));
                case 1:
                    return new Problem(
                            generateHelper(),
                            Problem.Bin_Operator.values()[seeder.nextInt(4)],
                            new Problem(seeder.nextInt((int)Math.pow(10, numLength))));
                case 2:
                    return new Problem(
                            new Problem(seeder.nextInt((int)Math.pow(10, numLength))),
                            Problem.Bin_Operator.values()[seeder.nextInt(4)],
                            generateHelper());
                case 3:
                    return new Problem(
                            generateHelper(),
                            Problem.Bin_Operator.values()[seeder.nextInt(4)],
                            generateHelper());
            }
        }
        return new Problem(seeder.nextInt((int)Math.pow(10, numLength)));
    }
}