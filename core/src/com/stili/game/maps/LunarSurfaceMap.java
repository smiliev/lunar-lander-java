package com.stili.game.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class LunarSurfaceMap {
    private final Array<Vector2> points;
    private final ShapeRenderer renderer;

    public LunarSurfaceMap (String map) {
        this.points = loadMapPoints(map);
        this.renderer = new ShapeRenderer(points.size);

//        renderer.setAutoShapeType(true);
    }

    private Array<Vector2> loadMapPoints(String fileName) {
        Array<Vector2> points = new Array<>();
        FileHandle fileHandle = Gdx.files.internal(fileName);

        if (fileHandle.exists()) {
            JsonValue root = new JsonReader().parse(fileHandle);
            if (root != null && root.isArray()) {
                for (JsonValue value : root) {
                    float x = value.getFloat("x", 0);
                    float y = value.getFloat("y", 0);
                    points.add(new Vector2(x, y));
                }
            } else {
                Gdx.app.error("LunarSurfaceService", "Failed to parse map data.");
            }
        } else {
            Gdx.app.error("LunarSurfaceService", "Map file not found: " + fileName);
        }

        return points;
    }

    public void render(OrthographicCamera camera) {
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);

        for (int i = 0; i < points.size - 1; i++) {
            Vector2 p1 = points.get(i);
            Vector2 p2 = points.get(i + 1);
            renderer.rectLine(p1, p2, 2);
            renderer.circle(p1.x, p1.y, 1);
        }

        renderer.end();
    }

    public void dispose() {
        renderer.dispose();
    }
}
