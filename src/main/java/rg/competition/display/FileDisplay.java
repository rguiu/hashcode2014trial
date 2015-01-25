package rg.competition.display;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDisplay implements Display {
    private final String path;

    public FileDisplay(String path) {
        this.path = path;
    }

    public FileDisplay() {
        this.path = "./";
    }

    @Override
    public void render(String data) {
        try {
            Files.write(Paths.get(path + "solution.txt"), data.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
