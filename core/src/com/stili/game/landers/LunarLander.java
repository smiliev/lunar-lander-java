package com.stili.game.landers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class LunarLander {
    protected LunarModuleRenderer renderer;
    private Vector2 position;
    private boolean thrust = true;

    public LunarLander() {
//        float initialY = LANDER_HEIGHT_RATIO * (landscape.getHighestAltitude() - landscape.getLowestAltitude()) + landscape.getLowestAltitude();
        this.position = new Vector2(0,300);
    }

    public void render(OrthographicCamera camera) {
       renderer.render(camera, position);
    }


    public void thrust() {
        this.thrust = true;
    }

    public void unthrust() {
        this.thrust = false;
    }

    public Vector2 getPosition() {
        return position;
    }
}
