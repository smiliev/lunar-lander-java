package com.stili.game.landers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.stili.game.maps.LunarLandscape;

import static com.stili.game.Constants.LANDER_HEIGHT_RATIO;

public class LunarLander {
    private final ShapeRenderer renderer;
    private Vector2 position;
    private boolean thrust;

    public LunarLander(LunarLandscape landscape) {
        float initialY = LANDER_HEIGHT_RATIO * (landscape.getHighestAltitude() - landscape.getLowestAltitude()) + landscape.getLowestAltitude();
        this.position = new Vector2(0,0);
        renderer = new ShapeRenderer();


    }

    public void render(OrthographicCamera camera) {
        if (thrust) {
            position.x++;
        }

        renderer.setProjectionMatrix(camera.combined);
        renderer.setAutoShapeType(true);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);
//        float rad = camera.position.x + 9.5f / PPM;
//        float dia = rad*2;
//        float width = rad / 2;
//        renderer.circle(camera.position.x, camera.position.y, rad);
//        renderer.rectLine(camera.position.x - rad, camera.position.y - rad - (width / 2), camera.position.x + rad, camera.position.y - rad - (width / 2), width);
//
//
//        renderer.end();

        float octagonCenterX = position.x;
//        System.out.println(position.y);
        float octagonCenterY = position.y;
        float octagonRadius = 9.5f;
        drawOctagon(octagonCenterX, octagonCenterY, octagonRadius, camera);

        float rectWidth = octagonRadius * 2;
        float rectHeight = octagonRadius / 2;
        float rectX = octagonCenterX - rectWidth / 2;
        float rectY = octagonCenterY - octagonRadius - rectHeight;

        drawRectangle(rectX, rectY, rectWidth, rectHeight);

//        this.position.x += 1;

        renderer.end();
    }



    private void drawOctagon(float centerX, float centerY, float radius, OrthographicCamera camera) {
        float dpiScalingFactor = Gdx.graphics.getDensity();
        float angleStep = 360f / 8; // 45 degrees for each side
        float startAngle = 22.5f;   // Start at 22.5 degrees to have flat sides at the top and bottom
        for (int i = 0; i < 8; i++) {
            float x1 = centerX + radius * (float) Math.cos(Math.toRadians(startAngle + angleStep * i));
            float y1 = centerY + radius * (float) Math.sin(Math.toRadians(startAngle + angleStep * i));
            float x2 = centerX + radius * (float) Math.cos(Math.toRadians(startAngle + angleStep * (i + 1)));
            float y2 = centerY + radius * (float) Math.sin(Math.toRadians(startAngle + angleStep * (i + 1)));

            renderer.rectLine(x1, y1, x2, y2, 2);
            renderer.circle(x2, y2, 1);
        }
    }

    private void drawRectangle(float x, float y, float width, float height) {
        renderer.rect(x, y, width, height);
    }

    public void thrust() {
        this.thrust = true;
    }

    public void unthrust() {
        this.thrust = false;
    }

    public Vector2 getPosition() {
        return position;
    }
}
