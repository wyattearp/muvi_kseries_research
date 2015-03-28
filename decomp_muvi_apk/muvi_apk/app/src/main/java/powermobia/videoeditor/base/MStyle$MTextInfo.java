// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.base;

import powermobia.ve.utils.MRect;

// Referenced classes of package powermobia.videoeditor.base:
//            MStyle

public static class words
{

    public static final int TEXTINFO_ALIGNMENT = 2;
    public static final int TEXTINFO_ALIGNMENT_BOTTOM = 8;
    public static final int TEXTINFO_ALIGNMENT_LEFT = 1;
    public static final int TEXTINFO_ALIGNMENT_MIDDLE = 16;
    public static final int TEXTINFO_ALIGNMENT_RIGHT = 2;
    public static final int TEXTINFO_ALIGNMENT_TOP = 4;
    public static final int TEXTINFO_COLOR = 1;
    public static final int TEXTINFO_EDITABLE = 5;
    public static final int TEXTINFO_EDITABLE_MASK_ALIGNMENT = 8;
    public static final int TEXTINFO_EDITABLE_MASK_COLOR = 4;
    public static final int TEXTINFO_EDITABLE_MASK_REGION = 1;
    public static final int TEXTINFO_EDITABLE_MASK_ROTATE = 16;
    public static final int TEXTINFO_EDITABLE_MASK_SIZE = 2;
    public static final int TEXTINFO_EDITABLE_MASK_TEXTALL = 31;
    public static final int TEXTINFO_ROTATE = 3;
    public static final int TEXTINFO_SIZE = 0;
    public static final int TEXTINFO_TIMESTAMP = 4;
    private int alignment;
    private int color;
    private int editable;
    private MRect region;
    private int rotate;
    private int size;
    private int timestamp;
    private String words;

    public int GetTextInfo(int i)
    {
        switch (i)
        {
        default:
            return 0;

        case 0: // '\0'
            return size;

        case 1: // '\001'
            return color;

        case 2: // '\002'
            return alignment;

        case 3: // '\003'
            return rotate;

        case 4: // '\004'
            return timestamp;

        case 5: // '\005'
            return editable;
        }
    }

    public MRect GetTextRect()
    {
        return region;
    }

    public String GetTextWords()
    {
        return words;
    }

    public ()
    {
        region = null;
        size = 0;
        color = 0;
        alignment = 0;
        rotate = 0;
        timestamp = 0;
        editable = 0;
        words = null;
    }

    public words(words words1)
    {
        if (words1 != null)
        {
            region = words1.region;
            size = words1.size;
            color = words1.color;
            alignment = words1.alignment;
            rotate = words1.rotate;
            timestamp = words1.timestamp;
            editable = words1.editable;
            words = words1.words;
        }
    }
}
