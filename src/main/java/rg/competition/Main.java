package rg.competition;

import rg.competition.display.Display;
import rg.competition.display.FileDisplay;
import rg.competition.impl.EvaluatorImpl;
import rg.competition.impl.ExecutableImpl;

public class Main {

    public static void main(String[] args) {
        Executable main = new ExecutableImpl();
        Display display = new FileDisplay();
        String response = main.execute();
        display.render(response, new EvaluatorImpl().score(response));
    }
}
