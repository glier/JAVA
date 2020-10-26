package com.geekbrains.level2.lesson1;

public class Track extends TrackElement {
    private final int trackDistance;

    public Track(int trackDistance) {
        this.trackDistance = trackDistance;
    }

    public void run(Running obj) {
        obj.run(trackDistance);
    }
}
