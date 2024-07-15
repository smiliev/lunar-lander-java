package com.stili.game.landers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static com.stili.game.Constants.LINE_THICKNESS;
import static com.stili.game.Constants.PPM;

public class LunarModuleRenderer {
    private final float ascendStageRadius;
    private final float descendStageWidth;
    private final float descendStageHeight;
    private final float landingGearLength;
    private final float landingPadRadius;
    private final float landingGearInset;

    private final ShapeRenderer renderer;

    public LunarModuleRenderer(float ascendStageRadius, float descendStageHeight, float landingGearLength, float landingPadRadius, float landingGearInset) {
        this.renderer = new ShapeRenderer();
        //TODO: idea have a LunarModule instance and call its sizes and stuff

        this.ascendStageRadius = ascendStageRadius;
        this.descendStageWidth = ascendStageRadius * 2;
        this.descendStageHeight = descendStageHeight;
        this.landingGearLength = landingGearLength;
        this.landingPadRadius = landingPadRadius;
        this.landingGearInset = landingGearInset;
    }

    public void render(OrthographicCamera camera, Vector2 position) {
        //TODO: decide how should the final variables be (make "EagleLEM" which contains these properties)

        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);

        drawAscentStage(position.x, position.y);

//        float rectX = position.x - ascendStageRadius;
//        float rectY = position.y - ascendStageRadius + (LINE_THICKNESS / 4);

        drawDescentStage(position);
        //TODO: do not pass local fields (landingGearLength for example)
        drawLandingGears(position);
        //TODO: magic numbers, diameter
        drawEngine(position, ascendStageRadius * 2, 1.5f * PPM, 3);
        //TODO: add comments saying what is draw (left pad)
        renderer.end();
    }



    private void drawAscentStage(float centerX, float centerY) {
        float angleStep = 360f / 8; // 45 degrees for each side
        float startAngle = 360f / 16; // Start at 22.5 degrees to have flat sides at the
        for (int i = 0; i < 8; i++) {
            float angle = MathUtils.degreesToRadians * (startAngle + angleStep * i);
            float x1 = centerX + ascendStageRadius * MathUtils.cos(angle);
            float y1 = centerY + ascendStageRadius * MathUtils.sin(angle);

            int nextIndex = (i + 1) % 8;
            float x2 = centerX + ascendStageRadius * MathUtils.cos(MathUtils.degreesToRadians * (startAngle + angleStep * nextIndex));
            float y2 = centerY + ascendStageRadius * MathUtils.sin(MathUtils.degreesToRadians * (startAngle + angleStep * nextIndex));

            // Draw the octagon side
            renderer.rectLine(x1, y1, x2, y2, LINE_THICKNESS);
            renderer.circle(x2, y2, LINE_THICKNESS / 2);
        }
    }

    private void drawDescentStage(Vector2 position) {
        float x = position.x - ascendStageRadius;
        float y = position.y - ascendStageRadius + (LINE_THICKNESS / 4);

        renderer.rectLine(x, y, x + descendStageWidth, y, LINE_THICKNESS);
        renderer.rectLine(x, y - descendStageHeight, x + descendStageWidth, y - descendStageHeight, LINE_THICKNESS);
        renderer.rectLine(x, y, x, y - descendStageHeight, LINE_THICKNESS);
        renderer.rectLine(x + descendStageWidth, y, x + descendStageWidth, y - descendStageHeight, LINE_THICKNESS);
    }
    private void drawLandingGears(Vector2 position) {
        float angleRadians = MathUtils.degreesToRadians * 60;
        float x = position.x - ascendStageRadius;
        float y = position.y - ascendStageRadius + (LINE_THICKNESS / 4);

        //TODO extract same code,
        // calculate y only once

        // Bottom-left side line
        float startX1 = x + landingGearInset;
        float startY = y - descendStageHeight;
        float endX1 = startX1 - landingGearLength * MathUtils.cos(angleRadians);
        float endY = startY - landingGearLength * MathUtils.sin(angleRadians);

        // Bottom-right side line
        float startX2 = x + descendStageWidth - landingGearInset;
        float endX2 = startX2 + landingGearLength * MathUtils.cos(angleRadians);

        // Draw the lines
        renderer.rectLine(startX1, startY, endX1, endY, LINE_THICKNESS);
        renderer.rectLine(endX1  - landingPadRadius, endY, endX1 + landingPadRadius, endY, LINE_THICKNESS);
        renderer.rectLine(startX2, startY, endX2, endY, LINE_THICKNESS);
        renderer.rectLine(endX2  - landingPadRadius, endY, endX2 + landingPadRadius, endY, LINE_THICKNESS);
    }

    private void drawEngine(Vector2 position, float width, float lineLength, float insetDistance) {
        float angleRadians = MathUtils.degreesToRadians * 60;
        float x = position.x - ascendStageRadius;
        float y = position.y - ascendStageRadius + (LINE_THICKNESS / 4);
        // Bottom-left side line
        float startX1 = x + insetDistance;
        float startY1 = y - descendStageHeight;
        float endX1 = startX1 - lineLength * MathUtils.cos(angleRadians);
        float endY1 = startY1 - lineLength * MathUtils.sin(angleRadians);

        // Bottom-right side line
        float startX2 = x + width - insetDistance;
        float startY2 = y - descendStageHeight;
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
