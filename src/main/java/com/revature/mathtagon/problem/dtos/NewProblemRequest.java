package com.revature.mathtagon.problem.dtos;

import com.revature.mathtagon.problem.ProblemGenerator;

public class NewProblemRequest {
    private int maxOperations;
    private int maxDigits;
    private int problems;

    public NewProblemRequest(int maxOperations, int maxDigits, int problems) {
        this.maxOperations = maxOperations;
        this.maxDigits = maxDigits;
        this.problems = problems;
    }

    public int getMaxOperations() {
        return maxOperations;
    }

    public void setMaxOperations(int maxOperations) {
        this.maxOperations = maxOperations;
    }

    public int getMaxDigits() {
        return maxDigits;
    }

    public void setMaxDigits(int maxDigits) {
        this.maxDigits = maxDigits;
    }

    public ProblemGenerator getGenerator() {
        return new ProblemGenerator(maxDigits, maxOperations);
    }

    public int getProblems() {
        return problems;
    }

    public void setProblems(int problems) {
        this.problems = problems;
    }

    @Override
    public String toString() {
        return "NewProblemRequest{" +
                "maxOperations=" + maxOperations +
                ", maxDigits=" + maxDigits +
                ", problems=" + problems +
                '}';
    }
}
