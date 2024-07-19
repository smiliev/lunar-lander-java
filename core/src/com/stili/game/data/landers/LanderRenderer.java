package com.stili.game.data.landers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static com.stili.game.Constants.LINE_THICKNESS;
import static com.stili.game.Constants.PPM;

public class LanderRenderer {
    private final LanderData.VisualData data;
    private final ShapeRenderer renderer;


    public LanderRenderer(LanderData.VisualData data) {
        this.data = data;
        this.renderer = new ShapeRenderer();
    }

    public void render(OrthographicCamera camera, Vector2 position) {
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);

        drawAscentStage(position.x, position.y);

//        float rectX = position.x - data.getAscendStageRadius();
//        float rectY = position.y - data.getAscendStageRadius() + (LINE_THICKNESS / 4);

        drawDescentStage(position);

        drawLandingGears(position);
        //TODO: magic numbers, diameter
        //TODO: fix PPM scaling for drawEngine
        drawEngine(position, data.getAscendStageRadius() * 2, 1.5f * PPM, 3);
        //TODO: add comments saying what is draw (left pad)
        renderer.end();
    }



    private void drawAscentStage(float centerX, float centerY) {
        float angleStep = 360f / 8; // 45 degrees for each side
        float startAngle = 360f / 16; // Start at 22.5 degrees to have flat sides at the
        for (int i = 0; i < 8; i++) {
            float angle = MathUtils.degreesToRadians * (startAngle + angleStep * i);
            float x1 = centerX + data.getAscendStageRadius() * MathUtils.cos(angle);
            float y1 = centerY + data.getAscendStageRadius() * MathUtils.sin(angle);

            int nextIndex = (i + 1) % 8;
            float x2 = centerX + data.getAscendStageRadius() * MathUtils.cos(MathUtils.degreesToRadians * (startAngle + angleStep * nextIndex));
            float y2 = centerY + data.getAscendStageRadius() * MathUtils.sin(MathUtils.degreesToRadians * (startAngle + angleStep * nextIndex));

            // Draw the octagon side
            renderer.rectLine(x1, y1, x2, y2, LINE_THICKNESS);
            renderer.circle(x2, y2, LINE_THICKNESS / 2);
        }
    }

    private void drawDescentStage(Vector2 position) {
        float x = position.x - data.getAscendStageRadius();
        float y = position.y - data.getAscendStageRadius() + (LINE_THICKNESS / 4);

        renderer.rectLine(x, y, x + data.getDescendStageWidth(), y, LINE_THICKNESS);
        renderer.rectLine(x, y - data.getDescendStageHeight(), x + data.getDescendStageWidth(), y - data.getDescendStageHeight(), LINE_THICKNESS);
        renderer.rectLine(x, y, x, y - data.getDescendStageHeight(), LINE_THICKNESS);
        renderer.rectLine(x + data.getDescendStageWidth(), y, x + data.getDescendStageWidth(), y - data.getDescendStageHeight(), LINE_THICKNESS);
    }
    private void drawLandingGears(Vector2 position) {
        float angleRadians = MathUtils.degreesToRadians * 60;
        float x = position.x - data.getAscendStageRadius();
        float y = position.y - data.getAscendStageRadius() + (LINE_THICKNESS / 4);

        //TODO extract same code,
        // calculate y only once

        // Bottom-left side line
        float startX1 = x + data.getLandingGearInset();
        float startY = y - data.getDescendStageHeight();
        float endX1 = startX1 - data.getLandingGearLength() * MathUtils.cos(angleRadians);
        float endY = startY - data.getLandingGearLength() * MathUtils.sin(angleRadians);

        // Bottom-right side line
        float startX2 = x + data.getDescendStageWidth() - data.getLandingGearInset();
        float endX2 = startX2 + data.getLandingGearLength() * MathUtils.cos(angleRadians);

        // Draw the lines
        renderer.rectLine(startX1, startY, endX1, endY, LINE_THICKNESS);
        renderer.rectLine(endX1  - data.getLandingPadRadius(), endY, endX1 + data.getLandingPadRadius(), endY, LINE_THICKNESS);
        renderer.rectLine(startX2, startY, endX2, endY, LINE_THICKNESS);
        renderer.rectLine(endX2  - data.getLandingPadRadius(), endY, endX2 + data.getLandingPadRadius(), endY, LINE_THICKNESS);
    }

    private void drawEngine(Vector2 position, float width, float lineLength, float insetDistance) {
        float angleRadians = MathUtils.degreesToRadians * 60;
        float x = position.x - data.getAscendStageRadius();
        float y = position.y - data.getAscendStageRadius() + (LINE_THICKNESS / 4);
        // Bottom-left side line
        float startX1 = x + insetDistance;
        float startY1 = y - data.getDescendStageHeight();
        float endX1 = startX1 - lineLength * MathUtils.cos(angleRadians);
        float endY1 = startY1 - lineLength * MathUtils.sin(angleRadians);

        // Bottom-right side line
        float startX2 = x + width - insetDistance;
        float startY2 = y - data.getDescendStageHeight();
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
