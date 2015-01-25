package rg.competition.display;

public class ConsoleDisplay implements Display {
    @Override
    public void render(String data) {
        System.out.println(data);
    }
}
