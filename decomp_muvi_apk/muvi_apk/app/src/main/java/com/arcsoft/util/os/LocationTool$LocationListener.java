// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

// Referenced classes of package com.arcsoft.util.os:
//            LocationTool

private class mProvider
    implements LocationListener
{

    Location mLastLocation;
    String mProvider;
    boolean mValided;
    final LocationTool this$0;

    Location current()
    {
        if (mValided)
        {
            return mLastLocation;
        } else
        {
            return null;
        }
    }

    public void onLocationChanged(Location location)
    {
        if (location.getLatitude() != 0.0D || location.getLongitude() != 0.0D)
        {
            mLastLocation.set(location);
            mValided = true;
            if (LocationTool.access$000(LocationTool.this) != null)
            {
                LocationTool.access$000(LocationTool.this).onLocationChanged(location);
                return;
            }
        }
    }

    public void onProviderDisabled(String s)
    {
        mValided = false;
        if (LocationTool.access$000(LocationTool.this) != null)
        {
            LocationTool.access$000(LocationTool.this).onProviderSetEnable(s, false);
        }
    }

    public void onProviderEnabled(String s)
    {
        mValided = true;
        if (LocationTool.access$000(LocationTool.this) != null)
        {
            LocationTool.access$000(LocationTool.this).onProviderSetEnable(s, true);
        }
    }

    public void onStatusChanged(String s, int i, Bundle bundle)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 54
    //                   1 70
    //                   2 62;
           goto _L1 _L2 _L3 _L4
_L1:
        if (LocationTool.access$000(LocationTool.this) != null)
        {
            LocationTool.access$000(LocationTool.this).onStatusChanged(s, i, bundle);
        }
        return;
_L2:
        mValided = false;
        continue; /* Loop/switch isn't completed */
_L4:
        mValided = true;
        continue; /* Loop/switch isn't completed */
_L3:
        mValided = false;
        if (true) goto _L1; else goto _L5
_L5:
    }

    Listener(String s)
    {
        this$0 = LocationTool.this;
        super();
        mProvider = null;
        mLastLocation = null;
        mValided = false;
        mProvider = s;
        mLastLocation = new Location(mProvider);
    }
}
