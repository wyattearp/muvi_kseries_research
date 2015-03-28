// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            Metadata

public static interface 
{

    public abstract void CloseRecordSet();

    public abstract long GetCount();

    public abstract  GetCurrent();

    public abstract boolean MoveNext();

    public abstract long MoveTo(long l);

    public abstract long OpenRecordSet(int i, String s,  a[],  , long l);
}
