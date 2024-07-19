package com.stili.game.data.maps;

import com.badlogic.gdx.math.Vector2;

public class LandscapeLine {
    private final Vector2 p1;
    private final Vector2 p2;
    private final boolean landable;

    public LandscapeLine(Vector2 p1, Vector2 p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.landable = p1.y == p2.y;
    }

    public Vector2 getP1() {
        return p1;
    }

    public Vector2 getP2() {
        return p2;
    }

    public boolean isLandable() {
        return landable;
    }
}
