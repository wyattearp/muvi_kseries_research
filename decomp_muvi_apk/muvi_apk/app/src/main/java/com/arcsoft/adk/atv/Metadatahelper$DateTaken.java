// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            Metadatahelper

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES Day_item;
    public static final .VALUES Item;
    public static final .VALUES Month_item;
    public static final .VALUES Year_item;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/adk/atv/Metadatahelper$DateTaken, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        Year_item = new <init>("Year_item", 0);
        Month_item = new <init>("Month_item", 1);
        Day_item = new <init>("Day_item", 2);
        Item = new <init>("Item", 3);
        n_3B_.clone aclone[] = new <init>[4];
        aclone[0] = Year_item;
        aclone[1] = Month_item;
        aclone[2] = Day_item;
        aclone[3] = Item;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
