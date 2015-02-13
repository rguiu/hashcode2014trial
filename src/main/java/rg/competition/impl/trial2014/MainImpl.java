package rg.competition.impl.trial2014;

import rg.competition.Input;
import rg.competition.display.Display;
import rg.competition.display.FileDisplay;

public class MainImpl {
    public static void main(String[] args) {
        Input <Boolean[][]>input = new InputImpl();

        Boolean[][] data = input.fetch("input.txt");

        for (Boolean [] b:data) {
            for (boolean f:b) {
                char c = f?'#':'.';
                System.out.print(c);
            }
            System.out.println();
        }
        ExecutableImpl main = new ExecutableImpl(data);
        Display display = new FileDisplay();
        String response = main.execute();
        display.render(response, Integer.toString(main.getScore()));
    }
}
