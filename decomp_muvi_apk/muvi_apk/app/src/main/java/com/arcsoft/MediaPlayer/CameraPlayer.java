// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer;


// Referenced classes of package com.arcsoft.MediaPlayer:
//            ArcMediaPlayer

public class CameraPlayer extends ArcMediaPlayer
{

    private static final String TAG = "CameraPlayer";
    private static final int mNativeMethodCount = 1;

    public CameraPlayer()
    {
    }

    public native void setCallbackFunc(int i);
}
