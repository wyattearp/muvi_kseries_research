// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;


// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcUtils

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES TV_PLAY_STARTUS_PLAYING;
    public static final .VALUES TV_PLAY_STARTUS_PLAYLIST;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/dmc/DmcUtils$TV_STATUS, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        TV_PLAY_STARTUS_PLAYING = new <init>("TV_PLAY_STARTUS_PLAYING", 0);
        TV_PLAY_STARTUS_PLAYLIST = new <init>("TV_PLAY_STARTUS_PLAYLIST", 1);
        S_3B_.clone aclone[] = new <init>[2];
        aclone[0] = TV_PLAY_STARTUS_PLAYING;
        aclone[1] = TV_PLAY_STARTUS_PLAYLIST;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
