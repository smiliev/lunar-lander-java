package com.stili.game.helper.map;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.stili.game.landers.LunarLander;

public class LanderInputHandler extends InputAdapter {
    private final LunarLander lander;

    public LanderInputHandler(LunarLander lander) {
        this.lander = lander;
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                lander.getPosition().y+=5;
                return true;
            case Input.Keys.D:
                lander.thrust();
                return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                lander.unthrust();
                return true;
        }
        return false;
    }
}
