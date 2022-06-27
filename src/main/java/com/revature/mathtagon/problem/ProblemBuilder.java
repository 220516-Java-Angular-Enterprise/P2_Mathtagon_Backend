package com.revature.mathtagon.problem;

import com.revature.mathtagon.problem.Problem.*;

/**
 * A wrapper of {@code java.lang.StringBuilder} for the purpose of easily
 * generating a {@code Problem} with the {@code ProblemGenerator} class.
 *
 * @see com.revature.mathtagon.problem.Problem
 * @see com.revature.mathtagon.problem.ProblemGenerator
 */
class ProblemBuilder {
    private final StringBuilder builder;

    ProblemBuilder(Integer i) {
        this(new Problem(i));
    }

    ProblemBuilder(Problem p) {
        builder = new StringBuilder(p.toString());
    }

    StringBuilder getBuilder() {
        return builder;
    }

    ProblemBuilder postOp(Bin_Operator op, Problem p) {

        this.builder.insert(0, "(")
                .append(String.format("%s%s)", p.binOps[op.ordinal()], p));
        return this;
    }

    ProblemBuilder preOp(Problem p, Bin_Operator op) {
        this.builder.insert(0, String.format("(%s%s", p, p.binOps[op.ordinal()]))
                .append(")");
        return this;
    }
}
