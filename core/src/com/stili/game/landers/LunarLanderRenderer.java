package com.stili.game.landers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static com.stili.game.Constants.LINE_THICKNESS;
import static com.stili.game.Constants.PPM;

public class LunarLanderRenderer {
    private final float moduleRadius;
    private final float platformThickness;
    private final float landingGearLength;

    private final ShapeRenderer renderer;

    public LunarLanderRenderer(float moduleRadius, float platformThickness, float landingGearLength) {
        this.renderer = new ShapeRenderer();
        this.moduleRadius = moduleRadius;
        this.platformThickness = platformThickness;
        this.landingGearLength = landingGearLength;
    }

    public void render(OrthographicCamera camera, Vector2 position) {
        //TODO: seperate physics and rendering logic in different classes
        //TODO: decide how should the final variables be (make "EagleLEM" which contains these properties)

        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);

        drawModule(position.x, position.y);

        float rectX = position.x - moduleRadius;
        float rectY = position.y - moduleRadius + (LINE_THICKNESS / 4);

        drawPlatform(rectX, rectY, moduleRadius * 2);
        //TODO: do not pass local fields (landingGearLength for example)
        drawLandingGears(rectX, rectY, moduleRadius * 2, landingGearLength, 1);
        //TODO: magic numbers, diameter
        drawEngine(rectX, rectY, moduleRadius * 2, 1.5f * PPM, 3);

        renderer.end();
    }



    private void drawModule(float centerX, float centerY) {
        float angleStep = 360f / 8; // 45 degrees for each side
        float startAngle = 360f / 16; // Start at 22.5 degrees to have flat sides at the
        for (int i = 0; i < 8; i++) {
            float angle = MathUtils.degreesToRadians * (startAngle + angleStep * i);
            float x1 = centerX + moduleRadius * MathUtils.cos(angle);
            float y1 = centerY + moduleRadius * MathUtils.sin(angle);

            int nextIndex = (i + 1) % 8;
            float x2 = centerX + moduleRadius * MathUtils.cos(MathUtils.degreesToRadians * (startAngle + angleStep * nextIndex));
            float y2 = centerY + moduleRadius * MathUtils.sin(MathUtils.degreesToRadians * (startAngle + angleStep * nextIndex));

            // Draw the octagon side
            renderer.rectLine(x1, y1, x2, y2, LINE_THICKNESS);
            renderer.circle(x2, y2, LINE_THICKNESS / 2);
        }
    }

    private void drawPlatform(float x, float y, float width) {
        // Bottom side
        renderer.rectLine(x, y, x + width, y, LINE_THICKNESS);
        // Top side
        renderer.rectLine(x, y - platformThickness, x + width, y - platformThickness, LINE_THICKNESS);
        // Left side
        renderer.rectLine(x, y, x, y - platformThickness, LINE_THICKNESS);
        // Right side
        renderer.rectLine(x + width, y, x + width, y - platformThickness, LINE_THICKNESS);
    }
    private void drawLandingGears(float x, float y, float width, float lineLength, float insetDistance) {
        float angleRadians = MathUtils.degreesToRadians * 60;


        //TODO extract same code,
        // calculate y only once

        // Bottom-left side line
        float startX1 = x + insetDistance;
        float startY1 = y - platformThickness;
        float endX1 = startX1 - lineLength * MathUtils.cos(angleRadians);
        float endY1 = startY1 - lineLength * MathUtils.sin(angleRadians);

        // Bottom-right side line
        float startX2 = x + width - insetDistance;
        float startY2 = y - platformThickness;
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
        float startY1 = y - platformThickness;
        float endX1 = startX1 - lineLength * MathUtils.cos(angleRadians);
        float endY1 = startY1 - lineLength * MathUtils.sin(angleRadians);

        // Bottom-right side line
        float startX2 = x + width - insetDistance;
        float startY2 = y - platformThickness;
        float endX2 = startX2 + lineLength * MathUtils.cos(angleRadians);
        float endY2 = startY2 - lineLength * MathUtils.sin(angleRadians);

        // Draw the lines
        renderer.rectLine(startX1, startY1, endX1, endY1, LINE_THICKNESS);
        renderer.circle(endX1, endY1, LINE_THICKNESS/2);
        renderer.rectLine(startX2, startY2, endX2, endY2, LINE_THICKNESS);
        renderer.circle(endX2, endY2, LINE_THICKNESS/2);
        renderer.rectLine(endX1, endY1, endX2, endY2, LINE_THICKNESS);
    }
}
