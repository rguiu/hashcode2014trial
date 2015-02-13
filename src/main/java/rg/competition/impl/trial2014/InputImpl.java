package rg.competition.impl.trial2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import rg.competition.Input;

public class InputImpl implements Input<Boolean[][]>{
    @Override
    public Boolean[][] fetch(String fileName) {
        Path path = Paths.get(fileName);
        Boolean[][] response = null;
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line = reader.readLine();

            int lines = Integer.parseInt(line.split(" ")[0]);
            int columns = Integer.parseInt(line.split(" ")[1]);

            response = new Boolean[lines][columns];

            for (int i = 0; i < lines; i++) {
                line = reader.readLine();
                for (int j = 0; j < columns; j++) {
                    response[i][j] = '#' == line.charAt(j);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
