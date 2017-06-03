package com.sensei.gesture.properties;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.Hashtable;

public class Properties {

    private static final String TAG = "sensorProperties";
    //which gestures the user can use depending on the sensors of the device
    private Hashtable<String, Boolean> validGestures = new Hashtable <> ();
    private Hashtable<String, String> smikSmaks = new Hashtable <> ();

    public Properties (Context context){
        //init valid gestures
        validGestures.put ("shake", true);

        //disable invalid gestures
        disableGestures (context);
    }

    public boolean setSmikSmak (String gestureKey, String actionKey) {
        if (isGestureValid(gestureKey)) {
            smikSmaks.put(gestureKey, actionKey);
            return true;
        }
        return false;
    }

    public boolean removeSmikSmak (String gestureKey) {
        if (smikSmaks.get(gestureKey) != null) {
            smikSmaks.remove(gestureKey);
            return true;
        }
        return false;
    }

    public String getAction (String gestureKey) {
        return smikSmaks.get(gestureKey);
    }

    private boolean isGestureValid (String gestureKey) {
        return validGestures.get(gestureKey);
    }

    private void disableGestures (Context context){
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //TODO: disable specific gestures depending on which sensors are available
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) {
            validGestures.put("shake", false);
        }
    }
}