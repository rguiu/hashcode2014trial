package rg.competition.display;

public class ConsoleDisplay implements Display {
    @Override
    public void render(String data, String score) {
        System.out.println(data);
        System.out.println(score);
    }
}
