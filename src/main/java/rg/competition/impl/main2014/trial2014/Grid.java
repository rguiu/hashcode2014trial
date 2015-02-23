package rg.competition.impl.main2014.trial2014;

import java.util.ArrayList;
import java.util.List;

public class Grid {
//    ○ N denotes the number of junctionsCount in the city
//    ○ M denotes the number of streets in the city
//    ○ T denotes the virtual time in seconds allowed for the car itineraries
//    ○ C denotes the number of cars in the fleet
//    ○ S denotes the junction at which all the cars are located initially
    int junctionsCount;
    int streets;
    int seconds;
    int cars;
    int startingJunction;

    List<Junction> junctions;

    public Grid(String line) {
        String s[]  = line.split(" ");
        junctionsCount = Integer.parseInt(s[0]);
        junctions = new ArrayList<>(junctionsCount);
        streets = Integer.parseInt(s[1]);
        seconds = Integer.parseInt(s[2]);
        cars = Integer.parseInt(s[3]);
        startingJunction = Integer.parseInt(s[4]);
    }

    public void addJunction(String s, int index) {
        String ss[] = s.split(" ");
        junctions.add(new Junction(Double.parseDouble(ss[0]),Double.parseDouble(ss[1]), index));
    }

    public void addStreet(String s, int index) {
        String ss[] = s.split(" ");
        int s1 = Integer.parseInt(ss[0]);
        int s2 = Integer.parseInt(ss[1]);
        int direction = Integer.parseInt(ss[2]);
        int time = Integer.parseInt(ss[3]);
        int distance = Integer.parseInt(ss[4]);

        Street c = new Street();
        c.to = junctions.get(s2);
        c.time = time;
        c.distance = distance;
        junctions.get(s1).connections.add(c);
        c.index = index;
        if (direction==2) {
            Street c2 = new Street();
            c2.to = junctions.get(s1);
            c2.time = time;
            c2.distance = distance;
            c2.index = index;
            junctions.get(s2).connections.add(c2);
        }
    }

    class Junction {
        double lat, lon;
        int index;
        List<Street> connections = new ArrayList<>();
        Junction(double lat, double lon, int index) {
            this.lat = lat;
            this.lon = lon;
            this.index = index;
        }
    }

    class Street implements Comparable<Street> {
        Junction to;
        int time;
        int distance;
        int index;

        @Override
        public int compareTo(Street o) {
            return time - o.time;
        }
    }

    public int getJunctionsCount() {
        return junctionsCount;
    }

    public int getStreets() {
        return streets;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getCars() {
        return cars;
    }

    public int getStartingJunction() {
        return startingJunction;
    }

}
