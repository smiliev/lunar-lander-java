package com.stili.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stili.game.data.landers.Lander;
import com.stili.game.data.maps.Landscape;
import com.stili.game.input.DevInputHandler;
import com.stili.game.input.LanderInputHandler;

public class GameScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final Landscape landscape;
    private final Lander lander;
    private final Viewport viewport;

    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        camera.zoom = 0.5f;

        //TODO read all maps from /maps and make menu to select which map to choose; same for lunar landers
        this.landscape = new Landscape();
        this.lander = new Lander();

        // Setup input handling
        setupInputHandlers();

        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();
    }

    private void setupInputHandlers() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        
        // Dev mode input handler (always added to handle F3 toggle)
        multiplexer.addProcessor(new DevInputHandler(camera));
        
        // Add lander input handler
        multiplexer.addProcessor(new LanderInputHandler(lander));
        
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        update();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        landscape.render(camera);
        lander.render(camera);
    }

    @Override
    public void dispose() {
        super.dispose();

        landscape.dispose();
        lander.dispose();
    }

    private void update() {
        cameraUpdate();
    }

    private void cameraUpdate() {
        camera.position.set(new Vector3(lander.getPosition().x, lander.getPosition().y, 0));
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.update();
    }
}

