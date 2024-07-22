package com.stili.game.data.maps;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Landscape {
    private final LandscapeData data;
    private final LandscapeRenderer renderer;

    public Landscape() {
        this.data = LandscapeLoader.loadMap("./maps/map.json");
        this.renderer = new LandscapeRenderer(this.data);
    }

    public void render(OrthographicCamera camera) {
        renderer.render(camera);
    }

    public void dispose() {
        renderer.dispose();
    }
}

