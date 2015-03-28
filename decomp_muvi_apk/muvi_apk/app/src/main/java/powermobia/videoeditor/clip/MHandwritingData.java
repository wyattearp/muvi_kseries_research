// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.clip;

import powermobia.ve.utils.MBitmap;

public class MHandwritingData
{

    int curHandwritingId;
    MBitmap rgb32Data;

    public MHandwritingData()
    {
        curHandwritingId = 0;
        rgb32Data = null;
    }

    public int getCurrentHandwritingId()
    {
        return curHandwritingId;
    }

    public MBitmap getRGB32Data()
    {
        return rgb32Data;
    }

    public int setCurrentHandwritingId(int i)
    {
        curHandwritingId = i;
        return 0;
    }

    public int setRGB32Data(MBitmap mbitmap)
    {
        rgb32Data = mbitmap;
        return 0;
    }
}
