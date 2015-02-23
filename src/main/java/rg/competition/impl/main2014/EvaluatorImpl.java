package rg.competition.impl.main2014;

import rg.competition.Evaluator;

public class EvaluatorImpl implements Evaluator {
    @Override
    public String score(String data) {
        return Integer.toString(data.split("[\n|\r]").length);
    }
}
