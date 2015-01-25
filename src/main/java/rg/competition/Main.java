package rg.competition;

import rg.competition.display.Display;
import rg.competition.display.FileDisplay;
import rg.competition.impl.ExecutableImpl;

public class Main {

    public static void main(String[] args) {
        Executable main = new ExecutableImpl();
        Display display = new FileDisplay();
        display.render(main.execute());
    }


}
