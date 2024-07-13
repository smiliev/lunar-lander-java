package com.stili.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stili.game.helper.map.LanderInputHandler;
import com.stili.game.landers.LunarLander;
import com.stili.game.landers.LunarLanderEagle;
import com.stili.game.maps.LunarLandscape;

public class GameScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
//    private final World world;
    private final Box2DDebugRenderer box2DDebugRenderer;
    private final LunarLandscape landscape;
    private final LunarLander lander;
    private final Viewport viewport;

    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
//        this.world = new World(new Vector2(0,0),  false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.landscape = new LunarLandscape("map.json");
        this.lander = new LunarLanderEagle(landscape);

        Gdx.input.setInputProcessor(new LanderInputHandler(lander));

        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
//        viewport.update(800, 600);
        viewport.apply();

    }

    @Override
    public void render(float delta) {
        camera.zoom = 0.5f;
        update();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

        landscape.render(camera);
        lander.render(camera);

//        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
    }

    @Override
    public void dispose() {
        super.dispose();

        landscape.dispose();
    }

    private void update() {
//        world.step(1/60f, 6, 2);

        cameraUpdate();
    }

    private void cameraUpdate() {
//        camera.position.set(new Vector3(camera.position.x += 3,150,0));
        camera.position.set(new Vector3(lander.getPosition().x,lander.getPosition().y, 0));
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(0, 0, 0);
        camera.update();
    }
}

