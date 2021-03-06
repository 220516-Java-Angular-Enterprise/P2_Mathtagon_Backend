package com.revature.mathtagon.problem;

import java.util.Random;

/**
 * Do a {@code POST} request to the endpoint {@code http://<host_ip>:8080/mathtagon/problems}
 * <br>Responses will be in the form of a list value. This value is to given as
 * the value for the {@code "expr"} key in a MathJS {@code POST} request.
 * <br><br>
 * Example:
 * <pre>
 *     Request Body:
 *     {
 *         "maxOperations":<int>3</int>
 *         "problems":<int>5</int>
 *     }
 *
 *     Response Body:
 *     [
 *         "(3+(7*(6*9)))",
 *         "(2*(4+9))",
 *         "((7-2)-(0-4))",
 *         "((7-6)/0)",
 *         "((9-7)/(8+0))"
 *      ]
 * </pre>
 *
 * @see com.revature.mathtagon.problem.Problem
 * @see com.revature.mathtagon.problem.ProblemBuilder
 */
public class ProblemGenerator {
    private final int numLength;
    private int maxOps;
    private int operations;
    private final Random seeder;

    /**
     * @param numLength     {@code int}: Maximum digits in any atom of the {@code Problem}
     * @param maxOps        {@code int}: Maximum operations allowed in generated {@code Problem}
     */
    public ProblemGenerator(int numLength, int maxOps) {
        this.numLength = numLength;
        this.maxOps = maxOps;
        this.seeder = new Random();
    }

    Problem generate() {
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
            switch(seeder.nextInt(5)) {
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
                case 4:
                    return new Problem(seeder.nextInt((int)Math.pow(10, numLength)));
            }
        }
        return new Problem(seeder.nextInt((int)Math.pow(10, numLength)));
    }
}
