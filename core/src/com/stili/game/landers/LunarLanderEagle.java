package com.stili.game.landers;

import com.stili.game.maps.LunarLandscape;

import static com.stili.game.Constants.LANDER_HEIGHT_RATIO;
import static com.stili.game.Constants.PPM;

public class LunarLanderEagle extends LunarLander {
    public final float ASC_STAGE_RADIUS = 2 * PPM;
    public final float PLATFORM_THICKNESS = 0.75f * PPM;
    public final float LANDING_GEAR_LENGTH = 2f * PPM;


    public LunarLanderEagle(LunarLandscape landscape) {
        super();
        float initialY = LANDER_HEIGHT_RATIO * (landscape.getHighestAltitude() - landscape.getLowestAltitude()) + landscape.getLowestAltitude();
        this.renderer = new LunarLanderRenderer(ASC_STAGE_RADIUS, PLATFORM_THICKNESS, LANDING_GEAR_LENGTH);

    }
}
