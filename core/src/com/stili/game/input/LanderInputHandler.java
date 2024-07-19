package com.stili.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.stili.game.data.landers.Lander;

public class LanderInputHandler extends InputAdapter {
    private final Lander lander;

    public LanderInputHandler(Lander lander) {
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
