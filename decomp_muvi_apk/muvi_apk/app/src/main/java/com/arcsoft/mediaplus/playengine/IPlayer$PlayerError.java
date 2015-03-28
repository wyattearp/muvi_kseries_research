// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            IPlayer

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES ERROR_OPENFILE;
    public static final .VALUES ERROR_PLAY;
    public static final .VALUES ERROR_PLAYLIST_URI_NULL;
    public static final .VALUES ERROR_RENDER_DISCONNECTED;
    public static final .VALUES ERROR_SEEK;
    public static final .VALUES ERROR_SERVER_DISCONNECTED;
    public static final .VALUES ERROR_UNENCRYPTED_WIFI;
    public static final .VALUES ERROR_UNKNOWN;
    public static final .VALUES ERROR_UNSUPPORT;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/playengine/IPlayer$PlayerError, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        ERROR_UNENCRYPTED_WIFI = new <init>("ERROR_UNENCRYPTED_WIFI", 0);
        ERROR_RENDER_DISCONNECTED = new <init>("ERROR_RENDER_DISCONNECTED", 1);
        ERROR_SERVER_DISCONNECTED = new <init>("ERROR_SERVER_DISCONNECTED", 2);
        ERROR_UNKNOWN = new <init>("ERROR_UNKNOWN", 3);
        ERROR_OPENFILE = new <init>("ERROR_OPENFILE", 4);
        ERROR_PLAY = new <init>("ERROR_PLAY", 5);
        ERROR_SEEK = new <init>("ERROR_SEEK", 6);
        ERROR_UNSUPPORT = new <init>("ERROR_UNSUPPORT", 7);
        ERROR_PLAYLIST_URI_NULL = new <init>("ERROR_PLAYLIST_URI_NULL", 8);
        r_3B_.clone aclone[] = new <init>[9];
        aclone[0] = ERROR_UNENCRYPTED_WIFI;
        aclone[1] = ERROR_RENDER_DISCONNECTED;
        aclone[2] = ERROR_SERVER_DISCONNECTED;
        aclone[3] = ERROR_UNKNOWN;
        aclone[4] = ERROR_OPENFILE;
        aclone[5] = ERROR_PLAY;
        aclone[6] = ERROR_SEEK;
        aclone[7] = ERROR_UNSUPPORT;
        aclone[8] = ERROR_PLAYLIST_URI_NULL;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
