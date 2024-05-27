package com.stili.game.landers;

import com.badlogic.gdx.math.Vector2;
import com.stili.game.maps.LunarLandscape;

import static com.stili.game.Constants.LANDER_HEIGHT_RATIO;

public class LunarLander {
    private Vector2 position;

    public LunarLander(LunarLandscape landscape) {
        double calculatedHeightOfLander = LANDER_HEIGHT_RATIO * (landscape.getHighestAltitude() - landscape.getLowestAltitude()) + landscape.getLowestAltitude();

    }
}
