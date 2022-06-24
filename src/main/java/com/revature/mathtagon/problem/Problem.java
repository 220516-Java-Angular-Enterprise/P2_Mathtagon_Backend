package com.revature.mathtagon.problem;

/**
 * Problem Grammar Rules
 * <p>
 *     A wrapper class for {@code java.lang.String} as a mathematical
 *     expression.
 * </p>
 * <pre>
 *
 * ===Grammars===
 *
 * problem:
 *  | '(' expression Bin_operator expression ')'
 *
 *  expression:
 *  | '(' Integer ')'
 *  | '(' problem ')'
 *
 *  Examples:
 *  ( ( 5 ) / ( 10 ) )
 *  ( ( 10 ) * ( ( 0 ) - ( 5 ) ) )
 *  </pre>
 * @see com.revature.mathtagon.problem.ProblemBuilder
 * @see com.revature.mathtagon.problem.ProblemGenerator
 */
class Problem {
    enum Bin_Operator { MUL, DIV, ADD, SUB }
    enum Uni_Operator { }
    final String[] binOps = {"*","/","+","-"};


    private String value;

    Problem() {}

    Problem(Integer i) {
        value = String.format("%d", i);
    }
    Problem(Integer a, Bin_Operator o, Integer b) {
        this.value = String.format("(%d%s%d)",
                a, binOps[o.ordinal()], b);
    }
    Problem(Problem a, Bin_Operator o, Problem b) {
        this.value = String.format("(%s%s%s)",
                a.value, binOps[o.ordinal()], b.value);
    }
    Problem(Integer a, Bin_Operator o, Problem b) {
        this.value = String.format("(%d%s%s)",
                a, binOps[o.ordinal()], b.value);
    }
    Problem(Problem a, Bin_Operator o, Integer b) {
        this.value = String.format("(%s%s%d)",
                a.value, binOps[o.ordinal()], b);
    }

    Problem(ProblemBuilder pb) {
        value = new String(pb.getBuilder());
    }


    @Override
    public String toString() {
        return value;
    }
}
