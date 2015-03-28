// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.service.util;

import android.os.Parcel;

// Referenced classes of package com.arcsoft.mediaplus.service.util:
//            RemoteVideoInfo

static final class 
    implements android.os.
{

    public RemoteVideoInfo createFromParcel(Parcel parcel)
    {
        return new RemoteVideoInfo(parcel, null);
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public RemoteVideoInfo[] newArray(int i)
    {
        return new RemoteVideoInfo[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }

    ()
    {
    }
}
