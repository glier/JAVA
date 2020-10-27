package com.geekbrains.level2.lesson1;

public class Wall extends TrackElement {
    private int wallHeight;

    public Wall(int wallHeight) {
        this.wallHeight = wallHeight;
    }

    public void jump(Leaping obj) {
        obj.jump(wallHeight);
    }
}
