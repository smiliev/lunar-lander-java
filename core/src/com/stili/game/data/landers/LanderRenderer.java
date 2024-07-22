package com.stili.game.data.landers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static com.stili.game.Constants.LINE_THICKNESS;

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
        drawDescentStage(position);
        drawLandingGears(position);
        drawEngine(position);

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
        float angleRadians = MathUtils.degreesToRadians * data.getLandingGearAngle();
        float x = position.x - data.getAscendStageRadius();
        float y = position.y - data.getAscendStageRadius() + (LINE_THICKNESS / 4);

        // left side line
        float startX1 = x + data.getLandingGearInset();
        float startY = y - data.getDescendStageHeight();
        float endX1 = startX1 - data.getLandingGearLength() * MathUtils.cos(angleRadians);
        float endY = startY - data.getLandingGearLength() * MathUtils.sin(angleRadians);

        // right side line
        float startX2 = x + data.getDescendStageWidth() - data.getLandingGearInset();
        float endX2 = startX2 + data.getLandingGearLength() * MathUtils.cos(angleRadians);

        // draw left line
        renderer.rectLine(startX1, startY, endX1, endY, LINE_THICKNESS);
        // draw left pad
        renderer.rectLine(endX1  - data.getLandingPadRadius(), endY, endX1 + data.getLandingPadRadius(), endY, LINE_THICKNESS);
        // draw right line
        renderer.rectLine(startX2, startY, endX2, endY, LINE_THICKNESS);
        // draw right pad
        renderer.rectLine(endX2  - data.getLandingPadRadius(), endY, endX2 + data.getLandingPadRadius(), endY, LINE_THICKNESS);

    }

    private void drawEngine(Vector2 position) {
        float angleRadians = MathUtils.degreesToRadians * data.getEngineAngle();
        float x = position.x - data.getDescendStageWidth() / 2;
        float y = position.y - data.getAscendStageRadius() + (LINE_THICKNESS / 4);
        // left side line
        float startX1 = x + data.getEngineInset();
        float startY = y - data.getDescendStageHeight();
        float endX1 = startX1 - data.getEngineHeight() * MathUtils.cos(angleRadians);
        float endY = startY - data.getEngineHeight() * MathUtils.sin(angleRadians);

        // right side line
        float startX2 = x + data.getDescendStageWidth() - data.getEngineInset();
        float endX2 = startX2 + data.getEngineHeight() * MathUtils.cos(angleRadians);

        // draw left line
        renderer.rectLine(startX1, startY, endX1, endY, LINE_THICKNESS);
        renderer.circle(endX1, endY, LINE_THICKNESS/2);
        // draw right line
        renderer.rectLine(startX2, startY, endX2, endY, LINE_THICKNESS);
        renderer.circle(endX2, endY, LINE_THICKNESS/2);

        // draw bottom line
        renderer.rectLine(endX1, endY, endX2, endY, LINE_THICKNESS);
    }


    public void dispose() {
        renderer.dispose();
    }
}
