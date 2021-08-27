package com.sprintkeyz.realisticspyglass.spyglass;

import org.javatuples.Quartet;

import java.util.LinkedHashMap;

public class Sun {

    // sun locs are <timesofday>, <yaw1-yaw2, pitch1-pitch2>!
    // each sun "unit" is about 560 sun time units (mc format) and about 8.2-8.6 pitch.
    public static LinkedHashMap<Integer, Quartet<Float, Float, Float, Float>> sunLocations = new LinkedHashMap<>();
    public static LinkedHashMap<Integer, Integer> sunLocTimes = new LinkedHashMap<>();

    public static void setSunLocations() {
        sunLocations.put(0, new Quartet<>(-8f, -16.6f, -95f, -84.2f));
        sunLocations.put(1, new Quartet<>(-16.6f, -25.3f, -95f, -84.2f));
        sunLocations.put(2, new Quartet<>(-24.3f, -33.5f, -95f, -84.2f));
        sunLocations.put(3, new Quartet<>(-33.0f, -42.4f, -95f, -84.2f));
        sunLocations.put(4, new Quartet<>(-37.8f, -51.3f, -95f, -84.2f));
        sunLocations.put(5, new Quartet<>(-92.3f, -93.8f, -95f, -84.2f));
        sunLocations.put(6, new Quartet<>(-60.0f, -72.8f, -95f, -84.2f));
        sunLocations.put(7, new Quartet<>(-68.0f, -78.0f, -95f, -84.2f));
        sunLocations.put(8, new Quartet<>(-75.0f, -84.0f, -95f, -84.2f));
        sunLocations.put(9, new Quartet<>(-80.0f, -90.0f, -95f, -84.2f));
        sunLocations.put(10, new Quartet<>(-86.0f, -83.0f, -95f, -84.2f));
        sunLocations.put(11, new Quartet<>(-86.8f, -77.7f, -95f, -84.2f));
        sunLocations.put(12, new Quartet<>(-79.5f, -71.5f, -95f, -84.2f));
        sunLocations.put(13, new Quartet<>(-73.9f, -64.8f, -95f, -84.2f));
        sunLocations.put(14, new Quartet<>(-67.1f, -58.1f, -95f, -84.2f));
        sunLocations.put(15, new Quartet<>(-60.1f, -50.8f, -95f, -84.2f));
        sunLocations.put(16, new Quartet<>(-52.9f, -43.5f, -95f, -84.2f));
        sunLocations.put(17, new Quartet<>(-44.8f, -35.8f, -95f, -84.2f));
        sunLocations.put(18, new Quartet<>(-36.9f, -28.1f, -95f, -84.2f));
        sunLocations.put(19, new Quartet<>(-28.9f, -20.2f, -95f, -84.2f));
        sunLocations.put(20, new Quartet<>(-2.6f, 6.1f, -95f, -84.2f));
        sunLocations.put(21, new Quartet<>(6.6f, 12.2f, -95f, -84.2f));

        for (int i=0; i<22; i++) {
            sunLocTimes.put(i, 560*i);
        }
    }

    public static Integer getClosestTimeIndex(long timeOfDay) {
        int timePerUnit = 560;
        // subtract 1 to get actual index (starts at 0)
        return ((int) timeOfDay/timePerUnit);
    }
}
