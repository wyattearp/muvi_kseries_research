// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.rtsp;


// Referenced classes of package com.arcsoft.videostream.rtsp:
//            VerticalSeekBar

public static interface 
{

    public abstract void onProgressChanged(VerticalSeekBar verticalseekbar, int i, boolean flag);

    public abstract void onStartTrackingTouch(VerticalSeekBar verticalseekbar);

    public abstract void onStopTrackingTouch(VerticalSeekBar verticalseekbar);
}
