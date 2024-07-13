package com.stili.game.landers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.stili.game.maps.LunarLandscape;

import static com.stili.game.Constants.*;

public class LunarLander {
    private final ShapeRenderer renderer;
    private Vector2 position;
    private final float ASC_STAGE_RADIUS = 2 * PPM;
    private final float PLATFORM_THICKNESS = 0.75f * PPM;
    private final float LANDING_GEAR_LENGTH = 2f * PPM;
    private boolean thrust = true;

    public LunarLander(LunarLandscape landscape) {
        float initialY = LANDER_HEIGHT_RATIO * (landscape.getHighestAltitude() - landscape.getLowestAltitude()) + landscape.getLowestAltitude();
        this.position = new Vector2(0,300);
        renderer = new ShapeRenderer();


    }

    public void render(OrthographicCamera camera) {
        //TODO: seperate physics and rendering logic in different classes
        //TODO: decide how should the final variables be (make "EagleLEM" which contains these properties)
        if (thrust) {
            position.x++;
        }

        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);

        float octagonCenterX = position.x;
        float octagonCenterY = position.y;

        drawModule(octagonCenterX, octagonCenterY);

        float rectX = octagonCenterX - ASC_STAGE_RADIUS;
        float rectY = octagonCenterY - ASC_STAGE_RADIUS + (LINE_THICKNESS / 4);

        drawPlatform(rectX, rectY, ASC_STAGE_RADIUS * 2);

        drawLandingGears(rectX, rectY, ASC_STAGE_RADIUS * 2, LANDING_GEAR_LENGTH, 1);
        drawEngine(rectX, rectY, ASC_STAGE_RADIUS * 2, 1.5f * PPM, 3);


        renderer.end();
    }



    private void drawModule(float centerX, float centerY) {
        float angleStep = 360f / 8; // 45 degrees for each side
        float startAngle = 360f / 16; // Start at 22.5 degrees to have flat sides at the
        for (int i = 0; i < 8; i++) {
            float angle = MathUtils.degreesToRadians * (startAngle + angleStep * i);
            float x1 = centerX + ASC_STAGE_RADIUS * MathUtils.cos(angle);
            float y1 = centerY + ASC_STAGE_RADIUS * MathUtils.sin(angle);

            int nextIndex = (i + 1) % 8;
            float x2 = centerX + ASC_STAGE_RADIUS * MathUtils.cos(MathUtils.degreesToRadians * (startAngle + angleStep * nextIndex));
            float y2 = centerY + ASC_STAGE_RADIUS * MathUtils.sin(MathUtils.degreesToRadians * (startAngle + angleStep * nextIndex));

            // Draw the octagon side
            renderer.rectLine(x1, y1, x2, y2, LINE_THICKNESS);
            renderer.circle(x2, y2, LINE_THICKNESS / 2);
        }
    }

    private void drawPlatform(float x, float y, float width) {
        // Bottom side
        renderer.rectLine(x, y, x + width, y, LINE_THICKNESS);
        // Top side
        renderer.rectLine(x, y - PLATFORM_THICKNESS, x + width, y - PLATFORM_THICKNESS, LINE_THICKNESS);
        // Left side
        renderer.rectLine(x, y, x, y - PLATFORM_THICKNESS, LINE_THICKNESS);
        // Right side
        renderer.rectLine(x + width, y, x + width, y - PLATFORM_THICKNESS, LINE_THICKNESS);
    }
    private void drawLandingGears(float x, float y, float width, float lineLength, float insetDistance) {
        float angleRadians = MathUtils.degreesToRadians * 60;

        // Bottom-left side line
        float startX1 = x + insetDistance;
        float startY1 = y - PLATFORM_THICKNESS;
        float endX1 = startX1 - lineLength * MathUtils.cos(angleRadians);
        float endY1 = startY1 - lineLength * MathUtils.sin(angleRadians);

        // Bottom-right side line
        float startX2 = x + width - insetDistance;
        float startY2 = y - PLATFORM_THICKNESS;
        float endX2 = startX2 + lineLength * MathUtils.cos(angleRadians);
        float endY2 = startY2 - lineLength * MathUtils.sin(angleRadians);

        // Draw the lines
        renderer.rectLine(startX1, startY1, endX1, endY1, LINE_THICKNESS);
        renderer.rectLine(endX1  - insetDistance, endY1, endX1 + insetDistance, endY1, LINE_THICKNESS);
        renderer.rectLine(startX2, startY2, endX2, endY2, LINE_THICKNESS);
        renderer.rectLine(endX2  - insetDistance, endY2, endX2 + insetDistance, endY2, LINE_THICKNESS);
    }

    private void drawEngine(float x, float y, float width, float lineLength, float insetDistance) {
        float angleRadians = MathUtils.degreesToRadians * 60;

        // Bottom-left side line
        float startX1 = x + insetDistance;
        float startY1 = y - PLATFORM_THICKNESS;
        float endX1 = startX1 - lineLength * MathUtils.cos(angleRadians);
        float endY1 = startY1 - lineLength * MathUtils.sin(angleRadians);

        // Bottom-right side line
        float startX2 = x + width - insetDistance;
        float startY2 = y - PLATFORM_THICKNESS;
        float endX2 = startX2 + lineLength * MathUtils.cos(angleRadians);
        float endY2 = startY2 - lineLength * MathUtils.sin(angleRadians);

        // Draw the lines
        renderer.rectLine(startX1, startY1, endX1, endY1, LINE_THICKNESS);
        renderer.circle(endX1, endY1, LINE_THICKNESS/2);
        renderer.rectLine(startX2, startY2, endX2, endY2, LINE_THICKNESS);
        renderer.circle(endX2, endY2, LINE_THICKNESS/2);
        renderer.rectLine(endX1, endY1, endX2, endY2, LINE_THICKNESS);
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
