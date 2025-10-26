package com.stili.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.stili.game.Config;

/**
 * Input handler for developer mode features.
 * Checks config once at construction to see if dev mode is allowed,
 * then manages its own state for toggling.
 */
public class DevInputHandler extends InputAdapter {
    private final OrthographicCamera camera;
    private final boolean devModeAllowed;
    private boolean devModeActive;
    
    // Zoom constraints
    private static final float MIN_ZOOM = 0.1f;
    private static final float MAX_ZOOM = 5.0f;
    private static final float ZOOM_SPEED = 0.1f;

    public DevInputHandler(OrthographicCamera camera) {
        this.camera = camera;

        this.devModeAllowed = Config.getInstance().isDevModeAllowed();
        this.devModeActive = false;
        
        if (devModeAllowed) {
            Gdx.app.log("DevMode", "Dev mode available - press F3 to toggle");
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        // F3 to toggle dev mode (only if allowed in config)
        if (keycode == Input.Keys.F3 && devModeAllowed) {
            devModeActive = !devModeActive;
            Gdx.app.log("DevMode", "Dev mode " + (devModeActive ? "ENABLED" : "DISABLED"));
            return true;
        }
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (!devModeActive) {
            return false;
        }

        // amountY is positive when scrolling down, negative when scrolling up
        // We want to zoom in (decrease zoom) when scrolling up, and zoom out (increase zoom) when scrolling down
        camera.zoom += amountY * ZOOM_SPEED;
        
        // Clamp zoom to prevent excessive zooming
        camera.zoom = MathUtils.clamp(camera.zoom, MIN_ZOOM, MAX_ZOOM);
        
        camera.update();
        return true;
    }
    
    public boolean isDevModeActive() {
        return devModeActive;
    }
}

