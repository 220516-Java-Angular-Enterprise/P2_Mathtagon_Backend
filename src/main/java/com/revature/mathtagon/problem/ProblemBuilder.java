package com.revature.mathtagon.problem;

import com.revature.mathtagon.problem.Problem.*;

public class ProblemBuilder {
    private final StringBuilder builder;

    public ProblemBuilder(Integer i) {
        this(new Problem(i));
    }

    public ProblemBuilder(Problem p) {
        builder = new StringBuilder(p.getValue());
    }

    public StringBuilder getBuilder() {
        return builder;
    }

    public ProblemBuilder postOp(Bin_Operator op, Problem p) {

        this.builder.insert(0, "(")
                .append(String.format("%s%s)", p.binOps[op.ordinal()], p.getValue()));
        return this;
    }

    public ProblemBuilder preOp(Problem p, Bin_Operator op) {
        this.builder.insert(0, String.format("(%s%s", p.getValue(), p.binOps[op.ordinal()]))
                .append(")");
        return this;
    }
}
