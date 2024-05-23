package com.stili.game.helper.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Vector2Serializer implements Json.Serializer<Vector2> {

    @Override
    public void write(Json json, Vector2 object, Class knownType) {
        json.writeObjectStart();
        json.writeValue("x", object.x);
        json.writeValue("y", object.y);
        json.writeObjectEnd();
    }

    @Override
    public Vector2 read(Json json, JsonValue jsonData, Class type) {
        float x = jsonData.getFloat("x");
        float y = jsonData.getFloat("y");
        return new Vector2(x, y);
    }
}