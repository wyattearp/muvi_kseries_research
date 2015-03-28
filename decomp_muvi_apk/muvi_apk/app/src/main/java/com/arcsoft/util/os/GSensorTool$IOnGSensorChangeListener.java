// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

// Referenced classes of package com.arcsoft.util.os:
//            GSensorTool

public static interface 
{

    public abstract void OnSensorChanged(SensorEvent sensorevent);

    public abstract void onAccuracyChanged(Sensor sensor, int i);
}
