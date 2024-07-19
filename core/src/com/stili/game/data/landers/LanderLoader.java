package com.stili.game.data.landers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class LanderLoader {

    private static final Json json = new Json();

    public static LanderData loadLanderData(String filePath) {
        FileHandle fileHandle = Gdx.files.internal(filePath);
        String jsonString = fileHandle.readString();

        LanderData data = json.fromJson(LanderData.class, jsonString);
        data.getVisualData().applyPPMScaling();
        return data;
    }
}