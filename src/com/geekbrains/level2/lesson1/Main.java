package com.geekbrains.level2.lesson1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static final int[] PERSON_RANGE_RUN = {400, 500};
    private static final int[] PERSON_RANGE_JUMP = {1, 3};

    private static final int[] CAT_RANGE_RUN = {150, 200};
    private static final int[] CAT_RANGE_JUMP = {5, 10};

    private static final int[] ROBOT_RANGE_RUN = {1000, 2000};
    private static final int[] ROBOT_RANGE_JUMP = {15, 30};

    private static final int[] TRACK_RANG_DIST = {5, 20};
    private static final int[] WALL_RANG_HEIGHT = {0, 3};
    private static final int MAX_TRACK_ELEMENT = 10;

    public static void main(String[] args) {
        List<TrackElement> track = initTrack();
        List<Sportsman> athletes = initSportsman();

        printTrack(track);
        printSportsman(athletes);

        for (Sportsman sportsman : athletes) {
            for (TrackElement trackElement : track) {
                if (sportsman.isStrength()) break;
                if (trackElement instanceof Track) {
                    // Не совсем удачная реализация.
                    // Спортсмен попадающий на трек, должен сам понять, что ему делать.
                    // Прыгать или бежать.
                    ((Track) trackElement).run((Running) sportsman);
                } else if (trackElement instanceof Wall){
                    ((Wall) trackElement).jump((Leaping) sportsman);
                }
            }
        }

        checkWinners(athletes);
    }

    public static void printSportsman(List<Sportsman> athletes) {
        for (Sportsman sportsman : athletes) {
            System.out.println(sportsman);
        }
    }

    public static void checkWinners(List<Sportsman> athletes) {
        for (Sportsman sportsman: athletes) {
            if (!sportsman.isStrength()) {
                System.out.println(sportsman.getName() + " дошел до финиша.");
            } else {
                System.out.println(sportsman.getName() + " устал и сошел с дистанции.");
            }
        }
    }

    public static void printTrack(List<TrackElement> track) {
        for (TrackElement trackElement : track) {
            if (trackElement instanceof Track)
                System.out.print("__.");
            if (trackElement instanceof Wall)
                System.out.print("|.");
        }
        System.out.println();
    }

    public static List<TrackElement> initTrack() {
        List<TrackElement> track = new LinkedList<>();

        for (int i = 0; i < MAX_TRACK_ELEMENT; i++) {
            if (getTrackType().equals(TrackType.TRACK)) {
                track.add(new Track(getRandom(TRACK_RANG_DIST)));
            } else {
                track.add(new Wall(getRandom(WALL_RANG_HEIGHT)));
            }
        }

        return track;
    }

    public static List<Sportsman> initSportsman() {
        List<Sportsman> athletes = new ArrayList<>();

        athletes.add(new Person("Person 1", getRandom(PERSON_RANGE_RUN), getRandom(PERSON_RANGE_JUMP)));
        athletes.add(new Person("Person 2", getRandom(PERSON_RANGE_RUN), getRandom(PERSON_RANGE_JUMP)));
        athletes.add(new Cat("Cat 1", getRandom(CAT_RANGE_RUN), getRandom(CAT_RANGE_JUMP)));
        athletes.add(new Robot("Robot 1", getRandom(ROBOT_RANGE_RUN), getRandom(ROBOT_RANGE_JUMP)));

        return athletes;
    }

    public static int getRandom(int[] range) {
        return range[0] + (int) (Math.random() * range[1]);
    }

    public static TrackType getTrackType() {
        int rand = getRandom(new int[]{0,2});
        if (rand == 0) return TrackType.TRACK;
        return TrackType.WALL;
    }
}
