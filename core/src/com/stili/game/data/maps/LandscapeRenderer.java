package com.stili.game.data.maps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.stili.game.Constants.PPM;

public class LandscapeRenderer {
    private final LandscapeData data;
    private final ShapeRenderer renderer;

    public LandscapeRenderer(LandscapeData data) {
        this.data = data;
        this.renderer = new ShapeRenderer();

    }

    public void render(OrthographicCamera camera) {
        renderer.setProjectionMatrix(camera.combined);

        float halfViewportWidth = (camera.viewportWidth * camera.zoom) / 2;
        float viewLeft = camera.position.x - halfViewportWidth;
        float viewRight = camera.position.x + halfViewportWidth;

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);

        renderLandscape(viewLeft, viewRight);

        renderer.end();
    }

    private void renderLandscape(float viewLeft, float viewRight) {
        float offset = 0;

        while (viewLeft - offset > data.getMapWidth()) {
            offset += data.getMapWidth();
        }

        while (viewLeft - offset < 0) {
            offset -= data.getMapWidth();
        }

        if (!data.getLines().isEmpty()) {
            int i = 0;
            float p1x, p2x;
            LandscapeLine line = data.getLines().get(i);

            while (true) {
                p1x = line.getP1().x + offset;
                p2x = line.getP2().x + offset;

                if (p1x >= viewRight) {
                    break;
                }

                if (p2x > viewLeft) {
                    renderer.rectLine(p1x, line.getP1().y, p2x, line.getP2().y, PPM);
                    renderer.circle(p2x, line.getP2().y, PPM / 2);
                }

                i++;
                if (i >= data.getLines().size) {
                    i = 0;
                    offset += data.getMapWidth();
                }

                line = data.getLines().get(i);
            }
        }
    }

    public void dispose() {
        renderer.dispose();
    }
}
