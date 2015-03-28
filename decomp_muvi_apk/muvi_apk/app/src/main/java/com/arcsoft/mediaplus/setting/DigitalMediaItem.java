// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.graphics.Bitmap;
import com.arcsoft.mediaplus.service.util.DeviceIcon;

public class DigitalMediaItem
{

    public static final int STATE_OFF = 501;
    public static final int STATE_ON = 500;
    public Bitmap deviceBitmap;
    public DeviceIcon deviceIcon;
    public String name;
    public int state;
    public String udn;

    public DigitalMediaItem()
    {
    }
}
