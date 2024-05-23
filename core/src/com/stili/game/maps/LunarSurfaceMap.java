package com.stili.game.maps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class LunarSurfaceMap {
    private final Array<Vector2> points;
    private final Array<LandscapeLine> lines;
    private final ShapeRenderer renderer;
    private final float mapWidth;

    public LunarSurfaceMap (String map) {
        this.points = new Array<>();
        this.lines = new Array<>();
        loadMap(map);
        this.mapWidth = points.get(points.size - 1).x - points.get(0).x;
        this.renderer = new ShapeRenderer();

//        renderer.setAutoShapeType(true);
    }

    public void render(OrthographicCamera camera) {
        renderer.setProjectionMatrix(camera.combined);
        float offset = 0;

        float halfViewportWidth = (camera.viewportWidth * camera.zoom) / 2;

        float viewLeft = camera.position.x - halfViewportWidth;
        float viewRight = camera.position.x + halfViewportWidth;

        while (viewLeft - offset > mapWidth) {
            offset += mapWidth;
        }

        while (viewLeft - offset < 0) {
            offset -= mapWidth;
        }

        int i = 0;

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);

        //todo: use variables you filthy barbarian
        if (!lines.isEmpty()) {
            LandscapeLine line = lines.get(i);

            while (line.getP1().x + offset < viewRight) {

                if (line.getP2().x + offset > viewLeft)
                    renderer.rectLine(line.getP1().x + offset, line.getP1().y, line.getP2().x + offset, line.getP2().y, 2);

                i++;
                if (i >= lines.size) {
                    i = 0;
                    offset += mapWidth;
                }

                line = lines.get(i);
            }
        }

        renderer.end();
    }

    //todo: flip and save into json
    private void loadMap(String map) {
        points.add(new Vector2(0.5f, 355.55f));
        points.add(new Vector2(5.45f, 355.55f));
        points.add(new Vector2(6.45f, 359.4f));
        points.add(new Vector2(11.15f, 359.4f));
        points.add(new Vector2(12.1f, 363.65f));
        points.add(new Vector2(14.6f, 363.65f));
        points.add(new Vector2(15.95f, 375.75f));
        points.add(new Vector2(19.25f, 388));
        points.add(new Vector2(19.25f, 391.9f));
        points.add(new Vector2(21.65f, 400));
        points.add(new Vector2(28.85f, 404.25f));
        points.add(new Vector2(30.7f, 412.4f));
        points.add(new Vector2(33.05f, 416.7f));
        points.add(new Vector2(37.9f, 420.5f));
        points.add(new Vector2(42.7f, 420.5f));
        points.add(new Vector2(47.4f, 416.65f));
        points.add(new Vector2(51.75f, 409.5f));
        points.add(new Vector2(56.55f, 404.25f));
        points.add(new Vector2(61.3f, 400));
        points.add(new Vector2(63.65f, 396.15f));
        points.add(new Vector2(68, 391.9f));
        points.add(new Vector2(70.3f, 388));
        points.add(new Vector2(75.1f, 386.1f));
        points.add(new Vector2(79.85f, 379.95f));
        points.add(new Vector2(84.7f, 378.95f));
        points.add(new Vector2(89.05f, 375.65f));
        points.add(new Vector2(93.75f, 375.65f));
        points.add(new Vector2(98.5f, 376.55f));
        points.add(new Vector2(103.2f, 379.95f));
        points.add(new Vector2(104.3f, 383.8f));
        points.add(new Vector2(107.55f, 388));
        points.add(new Vector2(108.95f, 391.9f));
        points.add(new Vector2(112.4f, 396.15f));
        points.add(new Vector2(113.3f, 400));
        points.add(new Vector2(117.1f, 404.25f));
        points.add(new Vector2(121.95f, 404.25f));
        points.add(new Vector2(125.3f, 396.3f));
        points.add(new Vector2(128.6f, 394.2f));
        points.add(new Vector2(132.45f, 396.15f));
        points.add(new Vector2(135.75f, 399.9f));
        points.add(new Vector2(138.15f, 408.15f));
        points.add(new Vector2(144.7f, 412.4f));
        points.add(new Vector2(146.3f, 424.8f));
        points.add(new Vector2(149.55f, 436.65f));
        points.add(new Vector2(149.55f, 441.05f));
        points.add(new Vector2(154.35f, 444.85f));
        points.add(new Vector2(163.45f, 444.85f));
        points.add(new Vector2(168.15f, 441.05f));
        points.add(new Vector2(172.95f, 436.75f));
        points.add(new Vector2(175.45f, 432.9f));
        points.add(new Vector2(179.7f, 428.6f));
        points.add(new Vector2(181.95f, 424.8f));
        points.add(new Vector2(186.7f, 422.5f));
        points.add(new Vector2(189.15f, 412.4f));
        points.add(new Vector2(191.55f, 404.35f));
        points.add(new Vector2(196.35f, 402.4f));
        points.add(new Vector2(200.7f, 398.1f));
        points.add(new Vector2(205.45f, 391.9f));
        points.add(new Vector2(210.15f, 383.8f));
        points.add(new Vector2(212.55f, 375.75f));
        points.add(new Vector2(216.85f, 371.8f));
        points.add(new Vector2(219.3f, 367.55f));
        points.add(new Vector2(220.65f, 363.65f));
        points.add(new Vector2(224f, 359.4f));
        points.add(new Vector2(228.8f, 359.4f));
        points.add(new Vector2(233.55f, 355.55f));
        points.add(new Vector2(237.85f, 348.45f));
        points.add(new Vector2(242.65f, 343.2f));
        points.add(new Vector2(245, 335.15f));
        points.add(new Vector2(247.35f, 322.8f));
        points.add(new Vector2(247.3f, 314.5f));
        points.add(new Vector2(248.35f, 306.55f));
        points.add(new Vector2(252.2f, 296.5f));
        points.add(new Vector2(256.55f, 294.55f));
        points.add(new Vector2(257.95f, 290.4f));
        points.add(new Vector2(261.25f, 285.95f));
        points.add(new Vector2(265.95f, 285.95f));
        points.add(new Vector2(267, 290.25f));
        points.add(new Vector2(271.75f, 290.25f));
        points.add(new Vector2(273.25f, 294.55f));
        points.add(new Vector2(275.2f, 294.55f));
        points.add(new Vector2(278.95f, 296.5f));
        points.add(new Vector2(282.25f, 300.3f));
        points.add(new Vector2(284.7f, 308.45f));
        points.add(new Vector2(291.85f, 312.65f));
        points.add(new Vector2(298.55f, 330.8f));
        points.add(new Vector2(303.25f, 331.8f));
        points.add(new Vector2(308, 335.05f));
        points.add(new Vector2(309, 338.9f));
        points.add(new Vector2(312.35f, 343.2f));
        points.add(new Vector2(313.8f, 347.05f));
        points.add(new Vector2(317.05F, 351.4f));
        points.add(new Vector2(321.9f, 351.4f));
        points.add(new Vector2(322.85f, 363.8f));
        points.add(new Vector2(326.6f, 375.75f));
        points.add(new Vector2(326.6f, 379.95f));
        points.add(new Vector2(330.9f, 379.95f));
        points.add(new Vector2(332.4f, 383.8f));
        points.add(new Vector2(335.8f, 388));
        points.add(new Vector2(338.1f, 396.15f));
        points.add(new Vector2(340.45f, 400.1f));
        points.add(new Vector2(345.3f, 404.25f));
        points.add(new Vector2(346.25f, 416.65f));
        points.add(new Vector2(349.6f, 428.7f));
        points.add(new Vector2(349.6f, 432.85f));
        points.add(new Vector2(350.95f, 436.75f));
        points.add(new Vector2(354.3f, 441.05f));
        points.add(new Vector2(359, 441.05f));
        points.add(new Vector2(361.4f, 449.1f));
        points.add(new Vector2(363.95f, 453));
        points.add(new Vector2(368.2f, 457.2f));
        points.add(new Vector2(372.9f, 461));
        points.add(new Vector2(410.2f, 461));
        points.add(new Vector2(412.55f, 449.1f));
        points.add(new Vector2(417.4f, 441.05f));
        points.add(new Vector2(419.7f, 432.9f));
        points.add(new Vector2(422.05f, 432.9f));
        points.add(new Vector2(425.45f, 424.8f));
        points.add(new Vector2(428.8f, 422.35f));
        points.add(new Vector2(433.45f, 416.65f));
        points.add(new Vector2(438.25f, 415.15f));
        points.add(new Vector2(442.6f, 412.4f));
        points.add(new Vector2(447.4f, 412.4f));
        points.add(new Vector2(448.8f, 416.65f));
        points.add(new Vector2(454.55f, 430.55f));
        points.add(new Vector2(455.5f, 434.8f));
        points.add(new Vector2(459.25f, 438.6f));
        points.add(new Vector2(462.6f, 440.9f));
        points.add(new Vector2(466, 444.85f));
        points.add(new Vector2(468.35f, 452.9f));
        points.add(new Vector2(475.55f, 457.3f));
        points.add(new Vector2(484.7f, 457.3f));
        points.add(new Vector2(494.7f, 458.2f));
        points.add(new Vector2(503.75f, 461.1f));
        points.add(new Vector2(522.2f, 461.1f));
        points.add(new Vector2(524.75f, 453));
        points.add(new Vector2(527.1f, 441.05f));
        points.add(new Vector2(527.1f, 432.9f));
        points.add(new Vector2(531.9f, 432.9f));
        points.add(new Vector2(534.15f, 424.8f));
        points.add(new Vector2(538.6f, 420.5f));
        points.add(new Vector2(540.9f, 416.65f));
        points.add(new Vector2(542.35f, 412.5f));
        points.add(new Vector2(545.7f, 408));
        points.add(new Vector2(550.45f, 408));
        points.add(new Vector2(552.85f, 398.1f));
        points.add(new Vector2(554.75f, 389.95f));
        points.add(new Vector2(559.55f, 388));
        points.add(new Vector2(564.35f, 391.9f));
        points.add(new Vector2(573.35f, 391.9f));
        points.add(new Vector2(578.1f, 388));
        points.add(new Vector2(579.55f, 379.95f));
        points.add(new Vector2(582.9f, 369.4f));
        points.add(new Vector2(587.75f, 367.55f));
        points.add(new Vector2(588.65f, 363.8f));
        points.add(new Vector2(592.05f, 359.5f));
        points.add(new Vector2(596.85f, 355.55f));

        Vector2 prev = null;
        for (Vector2 point : points) {
            if (prev != null) {
                lines.add(new LandscapeLine(prev, point));
            }
            prev = point;
        }
    }

    public void dispose() {
        renderer.dispose();
    }
}
