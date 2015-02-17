package rg.competition.impl.trial2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import rg.competition.Input;

public class Verifier {
    public static boolean[][] fetch(String fileName, int x, int y) {
        Path path = Paths.get(fileName);
        boolean[][] response = null;
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line = reader.readLine();

            int lines = Integer.parseInt(line);
            response = new boolean[x][y];


            for (int i = 0; i < lines; i++) {
                line = reader.readLine();
                //System.out.println(line);
                //PAINTSQ 155 638 1
                if (line.startsWith("PAINTS")) {
                    String[] tokens = line.split(" ");
                    int xx = Integer.parseInt(tokens[1]);
                    int yy = Integer.parseInt(tokens[2]);
                    int kk = Integer.parseInt(tokens[3]);
                   // System.out.println("Printing "+xx+":"+yy+","+kk);
                    for (int j = -kk; j <= kk; j++) {
                        for (int k = -kk; k <= kk; k++) {
                            int xxx = xx+j;
                            int yyy = yy+k;
                            response[xxx][yyy] = true;
                        }
                    }
                } else if (line.startsWith("ERASE")) {
                    String[] tokens = line.split(" ");
                    int xx = Integer.parseInt(tokens[1]);
                    int yy = Integer.parseInt(tokens[2]);
                    response[xx][yy] = false;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public static void main(String[] args) {
        Input<Boolean[][]> input = new InputImpl();

        Boolean[][] data = input.fetch("realx.txt");
        ExecutableImpl main = new ExecutableImpl(data);

        boolean[][]response = fetch("target/solution.txt", data.length, data[0].length);

        System.out.println("ERRORS: "+areEqual(data, response));
    }

    public static int areEqual(Boolean original[][], boolean processed[][]) {
        if (original.length != processed.length) return -1;
        if (original[0].length != processed[0].length) return -1;
        System.out.println("GOOD SIZE");
        int errors = 0;
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[0].length; j++) {
                if (original[i][j]!=processed[i][j]) {
//                    System.out.println("WRONG "+i+","+j);
//                    System.out.println(original[i][j] +":"+ processed[i][j]);
                    errors++;
                }
            }
        }
        return errors;
    }
}
