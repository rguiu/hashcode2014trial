package rg.competition.impl.trial2014;

import java.util.ArrayList;
import java.util.List;
import rg.competition.Executable;

public class ExecutableImpl implements Executable{

    Boolean[][] data;
    boolean[][] processed;
    boolean[][] selected;

    List<String>lines = new ArrayList<>();
    List<String>eraselines = new ArrayList<>();

    int score = -1;

    ExecutableImpl(Boolean[][] data){
        this.data = data;
        this.processed = new boolean[data.length][data[0].length];
        this.selected = new boolean[data.length][data[0].length];
    }

    public boolean isWorth(int k, int score) {
        if (k>10 && score<0) return false;
        int kk = 2*k + 1;
        int k2 = 2*(k-1) + 1;
        return (k>0 && (kk*kk)-(k2*k2) > -score);
    }

    @Override
    public String execute() {
        while(!finished()) {
            int[] currentWinner = new int[]{-1,-1,-1};
            int currentWinnerScore = 0;
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    if (selected[i][j]) {
                        continue;
                    }
                    int currentScore = 0;
                    for (int k = 0; k < 110; k++) {
                        if (k==0) {
                            if (!processed[i][j] && data[i][j]) {
                                currentScore = 1;
                            }
                        } else {
                            if (!inBounds(i, j, k)) break;
                            if (!isWorth(k, currentScore)) {
                                break;
                            }


                            int kk = 2 * k + 1;
                            int scp = 0;
                            int scn = 0;
                            for (int ii = -k; ii <= k; ii++) {
                                if (!processed[i - ii][j - k]) {
                                    if (data[i - ii][j - k]) {
                                        scp++;
                                    } else {
                                        scn++;
                                    }
                                }
                                if (!processed[i - ii][j + k]) {
                                    if (data[i - ii][j + k]) {
                                        scp++;
                                    } else {
                                        scn++;
                                    }
                                }
                            }
                            for (int jj = -k + 1; jj < k; jj++) {
                                if (!processed[i - k][j - jj]) {
                                    if (data[i - k][j - jj]) {
                                        scp++;
                                    } else {
                                        scn++;
                                    }
                                }
                                if (!processed[i + k][j - jj]) {
                                    if (data[i + k][j - jj]) {
                                        scp++;
                                    } else {
                                        scn++;
                                    }
                                }
                            }
                            currentScore += scp - scn - 1;
                        }
                        if (currentScore > currentWinnerScore) {
                            currentWinnerScore = currentScore;
                            currentWinner = new int[]{i, j, k};
                        }
                    }
                }
            }

            if (currentWinner[0]<0) break;
            System.out.println(currentWinner[0] + "," + currentWinner[1] + "," + currentWinner[2]+"["+currentWinnerScore+"]");
            selected(currentWinner[0], currentWinner[1], currentWinner[2]);
            if (currentWinner[2]==0) break;
        }

        if (!finished()) {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    if (!processed[i][j] && data[i][j]) {
                        selected(i, j, 0);
                    }
                }
            }
        }

        StringBuilder buffer = new StringBuilder();
        this.score = lines.size() + eraselines.size();
        buffer.append(lines.size() + eraselines.size());
        for(String line:lines) {
            buffer.append("\n");
            buffer.append(line);
        }
        for(String line:eraselines) {
            buffer.append("\n");
            buffer.append(line);
        }
        return buffer.toString();
    }

    private void selected(int i, int j, int k) {
        selected[i][j] = true;
        lines.add("PAINTSQ " + i + " " + j + " "+ k);
        for (int l = i-k; l <= i+k; l++) {
            for (int m = j-k; m <= j+k; m++) {
                if (!processed[l][m] && !data[l][m]) {
                    eraselines.add("ERASECELL " + l + " " + m);
                }
                processed[l][m]=true;
            }
        }
    }

    public boolean inBounds(int i, int j, int radius) {
        if (i-radius < 0) return false;
        if (j-radius < 0) return false;
        if (i+radius >= data.length) return false;
        if (j+radius >= data[0].length) return false;
        return true;
    }

    public boolean finished() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] && !processed[i][j]) return false;
            }
        }
        return true;
    }

    public int getScore() {
        return score;
    }
}
