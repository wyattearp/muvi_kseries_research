// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            ServerManager

public static interface 
{

    public abstract void OnDestroyObject(String s, int i);

    public abstract void OnDigaBrowseRecordTasks(String s,  , int i);

    public abstract void OnDigaCreateRecordSchedule(String s,  , int i);

    public abstract void OnDigaDeleteRecordSchedule(String s, int i);

    public abstract void OnDigaDisableRecordSchedule(String s, int i);

    public abstract void OnDigaEnableRecordSchedule(String s, int i);

    public abstract void OnDigaXP9eGetContainerIds(String s, String s1, int i);

    public abstract void onGetSearchCapabilities(String s, String s1, int i);

    public abstract void onGetSortCapabilities(String s, String s1, int i);

    public abstract void onServerAdded( );

    public abstract void onServerMetaChanged( );

    public abstract void onServerRemoved( );
}
