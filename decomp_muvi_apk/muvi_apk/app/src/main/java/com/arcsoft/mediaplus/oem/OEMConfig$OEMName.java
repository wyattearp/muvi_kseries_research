// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.oem;


// Referenced classes of package com.arcsoft.mediaplus.oem:
//            OEMConfig

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES GENERIC;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/oem/OEMConfig$OEMName, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        GENERIC = new <init>("GENERIC", 0);
        e_3B_.clone aclone[] = new <init>[1];
        aclone[0] = GENERIC;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
