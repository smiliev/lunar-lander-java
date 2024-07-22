package com.stili.game.data.landers;


import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import static com.stili.game.Constants.PPM;

public class LanderData implements Json.Serializable {
    private VisualData visualData;
    private PhysicsData physicsData;

    // Empty constructor for JSON deserialization
    public LanderData() {
    }
    public VisualData getVisualData() {
        return visualData;
    }

    public PhysicsData getPhysicsData() {
        return physicsData;
    }

    @Override
    public void write(Json json) {
        json.writeValue("visualData", visualData);
        json.writeValue("physicsData", physicsData);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        visualData = json.readValue(VisualData.class, jsonData.get("visualData"));
        physicsData = json.readValue(PhysicsData.class, jsonData.get("physicsData"));
    }

    public void applyPPMScaling() {

    }

    public static class VisualData {
        private float ascendStageRadius;
        private float descendStageWidth;
        private float descendStageHeight;
        private float landingGearLength;
        private float landingPadRadius;
        private float landingGearInset;
        private float landingGearAngle;
        private float engineHeight;
        private float engineInset;
        private float engineAngle;

        // Empty constructor for JSON deserialization
        public VisualData() {
        }

        public float getAscendStageRadius() {
            return ascendStageRadius;
        }

        public float getDescendStageWidth() {
            return descendStageWidth;
        }

        public float getDescendStageHeight() {
            return descendStageHeight;
        }

        public float getLandingGearLength() {
            return landingGearLength;
        }

        public float getLandingPadRadius() {
            return landingPadRadius;
        }

        public float getLandingGearInset() {
            return landingGearInset;
        }

        public float getLandingGearAngle() {
            return landingGearAngle;
        }

        public float getEngineHeight() {
            return engineHeight;
        }

        public float getEngineInset() {
            return engineInset;
        }

        public float getEngineAngle() {
            return engineAngle;
        }

        public void applyPPMScaling() {
            this.ascendStageRadius *= PPM;
            this.descendStageWidth *= PPM;
            this.descendStageHeight *= PPM;
            this.landingGearLength *= PPM;
            this.landingPadRadius *= PPM;
            this.landingGearInset *= PPM;
            this.landingGearAngle *= 1;
            this.engineHeight *= PPM;
            this.engineInset *= PPM;
            this.engineAngle *= 1;
        }
    }

    public static class PhysicsData {
        public float mass;
        public float inertia;
        public float fuelCapacity;
        public float thrustForce;
        public float rotationSpeed;

        // Empty constructor for JSON deserialization
        public PhysicsData() {
        }
    }
}

