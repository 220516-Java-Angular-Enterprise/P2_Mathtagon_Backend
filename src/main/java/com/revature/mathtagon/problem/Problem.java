package com.revature.mathtagon.problem;

public class Problem {
    public enum Bin_Operator { MUL, DIV, ADD, SUB }
    public enum Uni_Operator { }
    final String[] binOps = {"*","/","+","-"};

    //Problem Grammar Rules
    /*
    problem:
        | '(' expression Bin_operator expression ')'

    expression:
        | '(' Integer ')'
        | '(' problem ')'

    Examples:
        ( ( 5 ) / ( 10 ) )
        ( ( 10 ) * ( ( 0 ) - ( 5 ) ) )
     */

    protected final String value;

    public Problem(Integer i) {
        value = String.format("(%d)", i);
    }
    public Problem(Integer a, Bin_Operator o, Integer b) {
        this.value = String.format("((%d)%s(%d))",
                a, binOps[o.ordinal()], b);
    }
    public Problem(Problem a, Bin_Operator o, Problem b) {
        this.value = String.format("((%s)%s(%s))",
                a.value, binOps[o.ordinal()], b.value);
    }
    public Problem(Integer a, Bin_Operator o, Problem b) {
        this.value = String.format("((%d)%s(%s))",
                a, binOps[o.ordinal()], b.value);
    }
    public Problem(Problem a, Bin_Operator o, Integer b) {
        this.value = String.format("((%s)%s(%d))",
                a.value, binOps[o.ordinal()], b);
    }

    public Problem(ProblemBuilder pb) {
        value = new String(pb.getBuilder());
    }


    public String getValue() {
        return value;
    }
}
