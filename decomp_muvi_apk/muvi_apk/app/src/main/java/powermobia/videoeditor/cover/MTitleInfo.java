// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.cover;

import powermobia.ve.utils.MRect;

public class MTitleInfo
{

    public static final int TITLEINFO_ALIGNMENT = 2;
    public static final int TITLEINFO_ALIGNMENT_BOTTOM = 8;
    public static final int TITLEINFO_ALIGNMENT_LEFT = 1;
    public static final int TITLEINFO_ALIGNMENT_MIDDLE = 16;
    public static final int TITLEINFO_ALIGNMENT_RIGHT = 2;
    public static final int TITLEINFO_ALIGNMENT_TOP = 4;
    public static final int TITLEINFO_COLOR = 1;
    public static final int TITLEINFO_ROTATE = 3;
    private int alignment;
    private int color;
    private MRect region;
    private int rotate;
    private String words;

    public MTitleInfo()
    {
        region = null;
        color = 0;
        alignment = 0;
        rotate = 0;
        words = null;
    }

    public MTitleInfo(MTitleInfo mtitleinfo)
    {
        region = null;
        color = 0;
        alignment = 0;
        rotate = 0;
        words = null;
        if (mtitleinfo != null)
        {
            region = mtitleinfo.region;
            color = mtitleinfo.color;
            alignment = mtitleinfo.alignment;
            rotate = mtitleinfo.rotate;
            words = mtitleinfo.words;
        }
    }

    public int GetTitleInfo(int i)
    {
        switch (i)
        {
        default:
            return 0;

        case 1: // '\001'
            return color;

        case 2: // '\002'
            return alignment;

        case 3: // '\003'
            return rotate;
        }
    }

    public MRect GetTitleRect()
    {
        return region;
    }

    public String GetTitleWords()
    {
        return words;
    }
}
