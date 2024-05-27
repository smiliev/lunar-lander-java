package com.stili.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.stili.game.maps.LunarLandscape;

public class GameScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
//    private final World world;
    private final Box2DDebugRenderer box2DDebugRenderer;
    private final LunarLandscape landscape;

    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        this.batch = new SpriteBatch();
//        this.world = new World(new Vector2(0,0),  false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.landscape = new LunarLandscape("map_v2.json");

    }

    @Override
    public void render(float delta) {
        camera.zoom = 2f;
        update();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        landscape.render(camera);

        batch.begin();

        //render objectsdf

        batch.end();

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

        batch.setProjectionMatrix(camera.combined);
    }

    private void cameraUpdate() {
        camera.position.set(new Vector3(camera.position.x += 3,150,0));
//        camera.position.set(new Vector3(0,0,0));
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }
}

