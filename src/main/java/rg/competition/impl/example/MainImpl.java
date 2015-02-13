package rg.competition.impl.example;

import rg.competition.Executable;
import rg.competition.display.Display;
import rg.competition.display.FileDisplay;

public class MainImpl {
    public static void main(String[] args) {
        Executable main = new ExecutableImpl();
        Display display = new FileDisplay();
        String response = main.execute();
        display.render(response, new EvaluatorImpl().score(response));
    }
}
