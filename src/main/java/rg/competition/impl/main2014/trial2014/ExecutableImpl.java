package rg.competition.impl.main2014.trial2014;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import rg.competition.Executable;

public class ExecutableImpl implements Executable{

    Grid data;

    int score = -1;

    ExecutableImpl(Grid data){
        this.data = data;
    }

    Random ra = new Random(90);

    @Override
    public String execute() {

        Queue<Car> cars = new PriorityQueue<>();

        Car[] orderedCars = new Car[data.cars];

        for (int i = 0; i < data.cars; i++) {
            Car c = new Car();
            c.index = i;
            c.newPosition(data.startingJunction, 0, 0);
            cars.add(c);
            orderedCars[i] = c;
        }

        boolean[] streetsVisited = new boolean[data.streets];

        while(true) {
            Car c = cars.poll();
            if (c==null) break; // for first try this may happen.
            int position = c.position;

            boolean moved = false;
            for(Grid.Street s:data.junctions.get(position).connections) {
                if (!streetsVisited[s.index] && c.time + s.time <= data.seconds) {
                    c.newPosition(s.to.index, s.distance, s.time);
                    cars.add(c);
                    streetsVisited[s.index] = true;
                    moved = true;
                    break;
                }
            }
            if (!moved && data.junctions.get(position).connections.size()>0) {
                for (Grid.Street s : data.junctions.get(position).connections) {
                    for (Grid.Street s1 : data.junctions.get(s.to.index).connections) {
                        if (!streetsVisited[s1.index] && c.time + s1.time + s.time <= data.seconds) {
                            c.newPosition(s.to.index, s.distance, s.time);
                            cars.add(c);
                            streetsVisited[s.index] = true;
                            moved = true;
                            break;
                        }
                    }
                }
            }
            if (!moved && data.junctions.get(position).connections.size()>0) {
                int u = ra.nextInt(data.junctions.get(position).connections.size());
                Grid.Street s = data.junctions.get(position).connections.get(u);
                if (c.time + s.time <= data.seconds) {
                    c.newPosition(s.to.index, 0, s.time);
                    cars.add(c);
                    streetsVisited[s.index] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(data.cars + "\n");
        score = 0;
        for (int i = 0; i < orderedCars.length; i++) {
            sb.append(orderedCars[i].positions.size() + "\n");
            for(int p:orderedCars[i].positions) {
                sb.append(p+"\n");
            }
            score += orderedCars[i].distance;
        }
        return  sb.toString();
    }

    class Car implements Comparable<Car> {
        int index;
        int time;
        int distance;
        int position;
        List<Integer> positions = new ArrayList<>();
        @Override
        public int compareTo(Car o) {
            return time - o.time;
        }
        void newPosition(int position, int distance, int time) {
            this.distance += distance;
            this.time += time;
            this.position = position;
            positions.add(position);
        }
    }

    public int closerDistance(int a, int b) {
        return 0;
    }

    public int getScore() {
        return score;
    }
}
