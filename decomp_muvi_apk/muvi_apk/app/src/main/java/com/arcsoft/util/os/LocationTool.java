// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import com.arcsoft.Recyclable;
import com.arcsoft.util.debug.Log;

public class LocationTool
    implements Recyclable
{
    public static interface IOnLocationChangeListener
    {

        public abstract void onLocationChanged(Location location);

        public abstract void onProviderSetEnable(String s, boolean flag);

        public abstract void onStatusChanged(String s, int i, Bundle bundle);
    }

    private class LocationListener
        implements android.location.LocationListener
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
                if (mListener != null)
                {
                    mListener.onLocationChanged(location);
                    return;
                }
            }
        }

        public void onProviderDisabled(String s)
        {
            mValided = false;
            if (mListener != null)
            {
                mListener.onProviderSetEnable(s, false);
            }
        }

        public void onProviderEnabled(String s)
        {
            mValided = true;
            if (mListener != null)
            {
                mListener.onProviderSetEnable(s, true);
            }
        }

        public void onStatusChanged(String s, int i, Bundle bundle)
        {
            i;
            JVM INSTR tableswitch 0 2: default 28
        //                       0 54
        //                       1 70
        //                       2 62;
               goto _L1 _L2 _L3 _L4
_L1:
            if (mListener != null)
            {
                mListener.onStatusChanged(s, i, bundle);
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

        LocationListener(String s)
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


    private IOnLocationChangeListener mListener;
    private LocationListener mLocationListeners[];
    private LocationManager mLocationManager;

    public LocationTool(Context context)
    {
        mLocationManager = null;
        mLocationListeners = null;
        mListener = null;
        mLocationManager = (LocationManager)context.getSystemService("location");
        startReceivingLocationUpdates();
    }

    private void startReceivingLocationUpdates()
    {
        while (mLocationManager == null || mLocationListeners != null) 
        {
            return;
        }
        LocationListener alocationlistener[] = new LocationListener[2];
        alocationlistener[0] = new LocationListener("gps");
        alocationlistener[1] = new LocationListener("network");
        mLocationListeners = alocationlistener;
        try
        {
            mLocationManager.requestLocationUpdates("network", 60000L, 100F, mLocationListeners[1]);
        }
        catch (SecurityException securityexception1)
        {
            Log.e("Location", "request listen Network location failed");
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            Log.e("Location", (new StringBuilder()).append("provider does not exist ").append(illegalargumentexception.getMessage()).toString());
        }
        try
        {
            mLocationManager.requestLocationUpdates("gps", 60000L, 100F, mLocationListeners[0]);
            return;
        }
        catch (SecurityException securityexception)
        {
            Log.e("Location", "request Gps location failed");
            return;
        }
        catch (IllegalArgumentException illegalargumentexception1)
        {
            Log.e("Location", (new StringBuilder()).append("provider does not exist ").append(illegalargumentexception1.getMessage()).toString());
        }
    }

    private void stopReceivingLocationUpdates()
    {
        if (mLocationManager != null)
        {
            int i = 0;
            while (i < mLocationListeners.length) 
            {
                try
                {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                }
                catch (Exception exception) { }
                i++;
            }
            mLocationListeners = null;
        }
    }

    public Location getCurrentLoacation()
    {
        if (mListener != null)
        {
            for (int i = 0; i < mLocationListeners.length; i++)
            {
                Location location = mLocationListeners[i].current();
                if (location != null)
                {
                    return location;
                }
                Location location1 = mLocationManager.getLastKnownLocation(mLocationListeners[i].mProvider);
                if (location1 != null)
                {
                    return location1;
                }
            }

        }
        return null;
    }

    public void recycle()
    {
        stopReceivingLocationUpdates();
    }

    public void setOnLocationChangeListener(IOnLocationChangeListener ionlocationchangelistener)
    {
        mListener = ionlocationchangelistener;
    }

}
