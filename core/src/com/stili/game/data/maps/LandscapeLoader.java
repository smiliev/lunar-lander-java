package com.stili.game.data.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import static com.stili.game.Constants.MAP_SCALE;
import static com.stili.game.Constants.PPM;

public class LandscapeLoader {

    public static LandscapeData loadMap(String fileName) {
        FileHandle fileHandle = Gdx.files.internal(fileName);
        Array<Vector2> points = new Array<>();
        Array<LandscapeLine> lines = new Array<>();
        float lowestAltitude = Float.MAX_VALUE;
        float highestAltitude = Float.MIN_VALUE;

        if (fileHandle.exists()) {
            Vector2 prevPoint = null;
            JsonValue root = new JsonReader().parse(fileHandle);
            if (root != null && root.isArray()) {
                for (JsonValue value : root) {
                    float x = value.getFloat("x", 0) * PPM * MAP_SCALE;
                    float y = value.getFloat("y", 0) * PPM * MAP_SCALE;
                    Vector2 point = new Vector2(x, y);

                    points.add(point);

                    if (prevPoint != null) {
                        lines.add(new LandscapeLine(prevPoint, point));
                    }

                    if (point.y > highestAltitude) {
                        highestAltitude = point.y;
                    }
                    if (point.y < lowestAltitude) {
                        lowestAltitude = point.y;
                    }

                    prevPoint = point;
                }
            } else {
                Gdx.app.error("LunarSurfaceService", "Failed to parse map data.");
            }
        } else {
            Gdx.app.error("LunarSurfaceService", "Map file not found: " + fileName);
        }

        return new LandscapeData(points, lines, highestAltitude, lowestAltitude);
    }
}
