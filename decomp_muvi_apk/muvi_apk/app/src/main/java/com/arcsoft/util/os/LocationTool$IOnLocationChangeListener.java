// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.location.Location;
import android.os.Bundle;

// Referenced classes of package com.arcsoft.util.os:
//            LocationTool

public static interface 
{

    public abstract void onLocationChanged(Location location);

    public abstract void onProviderSetEnable(String s, boolean flag);

    public abstract void onStatusChanged(String s, int i, Bundle bundle);
}
