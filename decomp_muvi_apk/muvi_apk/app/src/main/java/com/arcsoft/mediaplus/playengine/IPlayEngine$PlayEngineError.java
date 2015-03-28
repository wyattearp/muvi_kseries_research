// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            IPlayEngine

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES ERROR_GETMUTE;
    public static final .VALUES ERROR_GETVOLUME;
    public static final .VALUES ERROR_OPENFILE;
    public static final .VALUES ERROR_PAUSE;
    public static final .VALUES ERROR_PLAY;
    public static final .VALUES ERROR_RENDER_DISCONNECTED;
    public static final .VALUES ERROR_SEEK;
    public static final .VALUES ERROR_SERVER_DISCONNECTED;
    public static final .VALUES ERROR_STOP;
    public static final .VALUES ERROR_UNENCRYPTED_WIFI;
    public static final .VALUES ERROR_UNKNOWN;
    public static final .VALUES ERROR_UNSUPPORT;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/playengine/IPlayEngine$PlayEngineError, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        ERROR_UNENCRYPTED_WIFI = new <init>("ERROR_UNENCRYPTED_WIFI", 0);
        ERROR_SERVER_DISCONNECTED = new <init>("ERROR_SERVER_DISCONNECTED", 1);
        ERROR_RENDER_DISCONNECTED = new <init>("ERROR_RENDER_DISCONNECTED", 2);
        ERROR_UNKNOWN = new <init>("ERROR_UNKNOWN", 3);
        ERROR_OPENFILE = new <init>("ERROR_OPENFILE", 4);
        ERROR_PLAY = new <init>("ERROR_PLAY", 5);
        ERROR_PAUSE = new <init>("ERROR_PAUSE", 6);
        ERROR_SEEK = new <init>("ERROR_SEEK", 7);
        ERROR_STOP = new <init>("ERROR_STOP", 8);
        ERROR_GETVOLUME = new <init>("ERROR_GETVOLUME", 9);
        ERROR_GETMUTE = new <init>("ERROR_GETMUTE", 10);
        ERROR_UNSUPPORT = new <init>("ERROR_UNSUPPORT", 11);
        r_3B_.clone aclone[] = new <init>[12];
        aclone[0] = ERROR_UNENCRYPTED_WIFI;
        aclone[1] = ERROR_SERVER_DISCONNECTED;
        aclone[2] = ERROR_RENDER_DISCONNECTED;
        aclone[3] = ERROR_UNKNOWN;
        aclone[4] = ERROR_OPENFILE;
        aclone[5] = ERROR_PLAY;
        aclone[6] = ERROR_PAUSE;
        aclone[7] = ERROR_SEEK;
        aclone[8] = ERROR_STOP;
        aclone[9] = ERROR_GETVOLUME;
        aclone[10] = ERROR_GETMUTE;
        aclone[11] = ERROR_UNSUPPORT;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
