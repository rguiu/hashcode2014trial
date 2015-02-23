package rg.competition.impl.main2014;

import rg.competition.Input;
import rg.competition.display.Display;
import rg.competition.display.FileDisplay;

public class MainImpl {
    public static void main(String[] args) {
        Input <Grid>input = new InputImpl();

        Grid grid = input.fetch("paris_54000.txt");
        System.out.println(grid.junctions.get(grid.getStartingJunction()).connections.size());
        ExecutableImpl main = new ExecutableImpl(grid);
        Display display = new FileDisplay();
        String response = main.execute();
        display.render(response, Integer.toString(main.getScore()));
    }
}
