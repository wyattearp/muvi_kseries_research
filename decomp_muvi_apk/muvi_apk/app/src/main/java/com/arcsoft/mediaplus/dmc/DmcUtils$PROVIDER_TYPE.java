// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;


// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcUtils

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES TYPE_FROM_LARGE_VIEW;
    public static final .VALUES TYPE_FROM_MULTI_VIEW;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/dmc/DmcUtils$PROVIDER_TYPE, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        TYPE_FROM_LARGE_VIEW = new <init>("TYPE_FROM_LARGE_VIEW", 0);
        TYPE_FROM_MULTI_VIEW = new <init>("TYPE_FROM_MULTI_VIEW", 1);
        E_3B_.clone aclone[] = new <init>[2];
        aclone[0] = TYPE_FROM_LARGE_VIEW;
        aclone[1] = TYPE_FROM_MULTI_VIEW;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
