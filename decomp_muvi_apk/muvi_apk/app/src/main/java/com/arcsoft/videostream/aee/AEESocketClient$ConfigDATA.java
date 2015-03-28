// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;


// Referenced classes of package com.arcsoft.videostream.aee:
//            AEESocketClient

public class typeIndex
{

    public static final int TYPE_INDEX_BOTTOM = 3;
    public static final int TYPE_INDEX_MID = 2;
    public static final int TYPE_INDEX_TOP = 1;
    public boolean isTypeTitle;
    public String name;
    public String options[];
    public String permission;
    public int stateIndex;
    public String stateVal;
    final AEESocketClient this$0;
    public String type;
    public int typeIndex;

    public String toString()
    {
        return (new StringBuilder()).append("name = ").append(name).append("; stateVal = ").append(stateVal).toString();
    }

    public ()
    {
        this$0 = AEESocketClient.this;
        super();
        type = null;
        name = null;
        stateVal = null;
        stateIndex = -1;
        isTypeTitle = false;
        options = null;
        permission = null;
        typeIndex = -1;
    }
}
