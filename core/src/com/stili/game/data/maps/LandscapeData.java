package com.stili.game.data.maps;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class LandscapeData {

    private final Array<Vector2> points;
    private final Array<LandscapeLine> lines;
    private final float mapWidth;
    private float lowestAltitude;
    private float highestAltitude;

    public LandscapeData(Array<Vector2> points, Array<LandscapeLine> lines, float highestAltitude, float lowestAltitude) {
        this.points = points;
        this.lines = lines;
        this.highestAltitude = highestAltitude;
        this.lowestAltitude = lowestAltitude;
        this.mapWidth = points.get(points.size - 1).x - points.get(0).x;
    }

    public Array<Vector2> getPoints() {
        return points;
    }

    public Array<LandscapeLine> getLines() {
        return lines;
    }

    public float getMapWidth() {
        return mapWidth;
    }

    public float getLowestAltitude() {
        return lowestAltitude;
    }

    public float getHighestAltitude() {
        return highestAltitude;
    }
}
