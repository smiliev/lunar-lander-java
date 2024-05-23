package com.stili.game.helper.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;

public class MapWriter {

    public static void writeVector2ArrayToJson(Array<Vector2> vectors, String filePath) {
        Json json = new Json();

        // Register the custom serializer for Vector2
        json.setSerializer(Vector2.class, new Vector2Serializer());
        json.setOutputType(JsonWriter.OutputType.json);  // Ensure proper JSON format

        // Convert the array of Vector2 to JSON format
        String jsonString = json.prettyPrint(vectors);

        // Write the JSON string to a file
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
