package rg.competition.impl.main2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import rg.competition.Input;

public class InputImpl implements Input<Grid>{
    @Override
    public Grid fetch(String fileName) {
        Path path = Paths.get(fileName);
        Grid response = null;
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line = reader.readLine();

            response = new Grid(line);

            for (int i = 0; i < response.junctionsCount; i++) {
                response.addJunction(reader.readLine(), i);
            }

            for (int i = 0; i < response.streets; i++) {
                response.addStreet(reader.readLine(), i);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < response.junctions.size(); i++) {
            Collections.sort(response.junctions.get(i).connections);
            Collections.reverse(response.junctions.get(i).connections);
        }
        return response;
    }
}
