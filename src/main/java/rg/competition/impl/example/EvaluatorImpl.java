package rg.competition.impl.example;

import rg.competition.Evaluator;

public class EvaluatorImpl implements Evaluator {
    @Override
    public String score(String data) {
        return Integer.toString(data.split("[\n|\r]").length);
    }
}
