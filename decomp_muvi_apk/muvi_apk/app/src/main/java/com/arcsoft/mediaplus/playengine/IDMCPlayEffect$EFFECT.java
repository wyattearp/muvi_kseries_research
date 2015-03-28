// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            IDMCPlayEffect

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES EFFECT_NONE;
    public static final .VALUES EFFECT_START_PLAY_FADE;
    public static final .VALUES EFFECT_START_PLAY_SLIDE;
    public static final .VALUES EFFECT_STOP_PLAY_FADE;
    public static final .VALUES EFFECT_STOP_PLAY_SLIDE;
    public static final .VALUES EFFECT_TURNTO_PLAY;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/playengine/IDMCPlayEffect$EFFECT, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        EFFECT_NONE = new <init>("EFFECT_NONE", 0);
        EFFECT_START_PLAY_SLIDE = new <init>("EFFECT_START_PLAY_SLIDE", 1);
        EFFECT_START_PLAY_FADE = new <init>("EFFECT_START_PLAY_FADE", 2);
        EFFECT_TURNTO_PLAY = new <init>("EFFECT_TURNTO_PLAY", 3);
        EFFECT_STOP_PLAY_SLIDE = new <init>("EFFECT_STOP_PLAY_SLIDE", 4);
        EFFECT_STOP_PLAY_FADE = new <init>("EFFECT_STOP_PLAY_FADE", 5);
        T_3B_.clone aclone[] = new <init>[6];
        aclone[0] = EFFECT_NONE;
        aclone[1] = EFFECT_START_PLAY_SLIDE;
        aclone[2] = EFFECT_START_PLAY_FADE;
        aclone[3] = EFFECT_TURNTO_PLAY;
        aclone[4] = EFFECT_STOP_PLAY_SLIDE;
        aclone[5] = EFFECT_STOP_PLAY_FADE;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
