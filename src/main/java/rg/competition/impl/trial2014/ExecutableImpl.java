package rg.competition.impl.trial2014;

import java.util.ArrayList;
import java.util.List;
import rg.competition.Executable;

public class ExecutableImpl implements Executable{

    Boolean[][] data;

    int score = -1;

    ExecutableImpl(Boolean[][] data){
        this.data = data;
    }

    @Override
    public String execute() {
        StringBuilder buffer = new StringBuilder();
        List<String>lines = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j]) {
                    lines.add("PAINTSQ " + i + " " + j + " 0");
                }
            }
        }
        this.score = lines.size();
        buffer.append(lines.size());
        for(String line:lines) {
            buffer.append("\n");
            buffer.append(line);
        }
        return buffer.toString();
    }

    public int getScore() {
        return score;
    }
}
