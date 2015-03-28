// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class GSensorTool
{
    public static interface IOnGSensorChangeListener
    {

        public abstract void OnSensorChanged(SensorEvent sensorevent);

        public abstract void onAccuracyChanged(Sensor sensor, int i);
    }


    public GSensorTool()
    {
    }

    public void setOnAccelerometerChangeListener(IOnGSensorChangeListener iongsensorchangelistener)
    {
    }

    public void setOnAccelorateChangeListener(IOnGSensorChangeListener iongsensorchangelistener, int i)
    {
    }

    public void setOnGravityChangeListener(IOnGSensorChangeListener iongsensorchangelistener, int i)
    {
    }

    public void setOnMagnificChangeListener(IOnGSensorChangeListener iongsensorchangelistener, int i)
    {
    }
}
