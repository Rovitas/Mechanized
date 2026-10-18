package com.ronicus.mechanized.client;

public class BraceletConfig {

    public static class Transform {
        public float offsetX;
        public float offsetY;
        public float offsetZ;

        public float scaleX;
        public float scaleY;
        public float scaleZ;

        public float rotationX;
        public float rotationY;
        public float rotationZ;

        public Transform(float ox, float oy, float oz, float sx, float sy, float sz, float rx, float ry, float rz) {
            this.offsetX = ox;
            this.offsetY = oy;
            this.offsetZ = oz;
            this.scaleX = sx;
            this.scaleY = sy;
            this.scaleZ = sz;
            this.rotationX = rx;
            this.rotationY = ry;
            this.rotationZ = rz;
        }

        public Transform copy() {
            return new Transform(offsetX, offsetY, offsetZ, scaleX, scaleY, scaleZ, rotationX, rotationY, rotationZ);
        }

        public void copyFrom(Transform other) {
            this.offsetX = other.offsetX;
            this.offsetY = other.offsetY;
            this.offsetZ = other.offsetZ;
            this.scaleX = other.scaleX;
            this.scaleY = other.scaleY;
            this.scaleZ = other.scaleZ;
            this.rotationX = other.rotationX;
            this.rotationY = other.rotationY;
            this.rotationZ = other.rotationZ;
        }
    }

    // Default reference presets for each of the 4 arm profiles (User calibrated)
    private static final Transform DEFAULT_RIGHT_WIDE = new Transform(-0.095f, 0.4f, 0.0f, 0.5f, 0.5f, 0.5f, 90.0f,  150.0f,   0.0f);
    private static final Transform DEFAULT_RIGHT_SLIM = new Transform(-0.085f, 0.4f, 0.0f, 0.36f, 0.5f, 0.5f, 90.0f,  150.0f,   0.0f);
    private static final Transform DEFAULT_LEFT_WIDE  = new Transform( 0.095f, 0.4f, 0.0f, 0.5f, 0.5f, 0.5f, 90.0f, -150.0f, 180.0f);
    private static final Transform DEFAULT_LEFT_SLIM  = new Transform( 0.085f, 0.4f, 0.0f, 0.36f, 0.5f, 0.5f, 90.0f, -150.0f, 180.0f);

    // Active mutable instances
    public static final Transform RIGHT_WIDE = DEFAULT_RIGHT_WIDE.copy();
    public static final Transform RIGHT_SLIM = DEFAULT_RIGHT_SLIM.copy();
    public static final Transform LEFT_WIDE  = DEFAULT_LEFT_WIDE.copy();
    public static final Transform LEFT_SLIM  = DEFAULT_LEFT_SLIM.copy();

    /**
     * Resolves the active transform profile based on arm handedness and skin model.
     */
    public static Transform get(boolean isLeftArm, boolean isSlim) {
        if (isLeftArm) {
            return isSlim ? LEFT_SLIM : LEFT_WIDE;
        } else {
            return isSlim ? RIGHT_SLIM : RIGHT_WIDE;
        }
    }

    /**
     * Resets a specific arm profile to its default values.
     */
    public static void reset(boolean isLeftArm, boolean isSlim) {
        if (isLeftArm) {
            if (isSlim) {
                LEFT_SLIM.copyFrom(DEFAULT_LEFT_SLIM);
            } else {
                LEFT_WIDE.copyFrom(DEFAULT_LEFT_WIDE);
            }
        } else {
            if (isSlim) {
                RIGHT_SLIM.copyFrom(DEFAULT_RIGHT_SLIM);
            } else {
                RIGHT_WIDE.copyFrom(DEFAULT_RIGHT_WIDE);
            }
        }
    }

    /**
     * Resets all 4 arm profiles to defaults.
     */
    public static void resetAll() {
        RIGHT_WIDE.copyFrom(DEFAULT_RIGHT_WIDE);
        RIGHT_SLIM.copyFrom(DEFAULT_RIGHT_SLIM);
        LEFT_WIDE.copyFrom(DEFAULT_LEFT_WIDE);
        LEFT_SLIM.copyFrom(DEFAULT_LEFT_SLIM);
    }
}

