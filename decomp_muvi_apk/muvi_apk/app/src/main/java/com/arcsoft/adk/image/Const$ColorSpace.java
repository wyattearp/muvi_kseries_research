// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;


// Referenced classes of package com.arcsoft.adk.image:
//            Const

public static final class nativeValue extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES RGB565;
    final int nativeValue;

    public static nativeValue valueOf(String s)
    {
        return (nativeValue)Enum.valueOf(com/arcsoft/adk/image/Const$ColorSpace, s);
    }

    public static nativeValue[] values()
    {
        return (nativeValue[])$VALUES.clone();
    }

    static 
    {
        RGB565 = new <init>("RGB565", 0, 0x15000454);
        e_3B_.clone aclone[] = new <init>[1];
        aclone[0] = RGB565;
        $VALUES = aclone;
    }

    private (String s, int i, int j)
    {
        super(s, i);
        nativeValue = j;
    }
}
