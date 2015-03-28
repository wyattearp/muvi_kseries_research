// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.service.util;


// Referenced classes of package com.arcsoft.mediaplus.service.util:
//            DLNAService

private class <init>
    implements com.arcsoft.adk.atv.er
{

    final DLNAService this$0;

    public void OnDestroyObject(String s, int i)
    {
    }

    public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.StatusListener statuslistener, int i)
    {
    }

    public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv. , int i)
    {
    }

    public void OnDigaDeleteRecordSchedule(String s, int i)
    {
    }

    public void OnDigaDisableRecordSchedule(String s, int i)
    {
    }

    public void OnDigaEnableRecordSchedule(String s, int i)
    {
    }

    public void OnDigaXP9eGetContainerIds(String s, String s1, int i)
    {
    }

    public void onGetSearchCapabilities(String s, String s1, int i)
    {
    }

    public void onGetSortCapabilities(String s, String s1, int i)
    {
    }

    public void onServerAdded(com.arcsoft.adk.atv.StatusListener statuslistener)
    {
        if (statuslistener != null)
        {
            DLNAService.access$2500(DLNAService.this, statuslistener._fld0);
        }
    }

    public void onServerMetaChanged(com.arcsoft.adk.atv.StatusListener statuslistener)
    {
    }

    public void onServerRemoved(com.arcsoft.adk.atv.StatusListener statuslistener)
    {
        if (statuslistener != null)
        {
            DLNAService.access$2600(DLNAService.this, statuslistener._fld0);
        }
    }

    private I()
    {
        this$0 = DLNAService.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
