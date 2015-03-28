// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;


// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            VideoRootFolderRemoteDS

class this._cls0
    implements com.arcsoft.mediaplus.datasource.db.ner
{

    final VideoRootFolderRemoteDS this$0;

    public void OnDBDataMounted(String s)
    {
        VideoRootFolderRemoteDS.this.OnDBDataMounted(s);
    }

    public void OnDBDataTransmittedBegin(String s)
    {
        NotifyOnDataBuiltBegin();
    }

    public void OnDBDataTransmittedFinish(String s)
    {
        NotifyOnDataBuiltFinish();
    }

    public void OnDBDataUnMounted(String s)
    {
        VideoRootFolderRemoteDS.this.OnDBDataUnMounted(s);
    }

    public void OnDBDataUpdated(String s, com.arcsoft.mediaplus.datasource.db.ataUnMounted ataunmounted)
    {
        if (ataunmounted.eorootfolderadded)
        {
            VideoRootFolderRemoteDS.this.OnDBDataUpdated(s, ataunmounted);
        }
    }

    stener()
    {
        this$0 = VideoRootFolderRemoteDS.this;
        super();
    }
}
