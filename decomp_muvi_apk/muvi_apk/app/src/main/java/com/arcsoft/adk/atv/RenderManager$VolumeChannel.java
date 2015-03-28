// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            RenderManager

public static final class nativeValue extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES MASTER;
    private final String nativeValue;

    public static nativeValue valueOf(String s)
    {
        return (nativeValue)Enum.valueOf(com/arcsoft/adk/atv/RenderManager$VolumeChannel, s);
    }

    public static nativeValue[] values()
    {
        return (nativeValue[])$VALUES.clone();
    }

    static 
    {
        MASTER = new <init>("MASTER", 0, "Master");
        l_3B_.clone aclone[] = new <init>[1];
        aclone[0] = MASTER;
        $VALUES = aclone;
    }


    private (String s, int i, String s1)
    {
        super(s, i);
        nativeValue = s1;
    }
}
