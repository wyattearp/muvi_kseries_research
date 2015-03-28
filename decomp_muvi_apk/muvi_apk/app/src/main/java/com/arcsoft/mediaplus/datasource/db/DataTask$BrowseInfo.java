// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            DataTask

class refreshMeta
{

    int mDelayRetryTimes;
    final String mDirObjID;
    int mRetryCnt;
    long mRetryStartTick;
    int mRetryType;
    final String mServerUDN;
    final int mUpdateType;
    boolean refreshMeta;
    final DataTask this$0;

    (String s, String s1, int i, boolean flag)
    {
        this$0 = DataTask.this;
        super();
        mDelayRetryTimes = 0;
        mRetryType = 1;
        mRetryCnt = 0;
        mRetryStartTick = 0L;
        refreshMeta = false;
        mServerUDN = s;
        mUpdateType = i;
        mDirObjID = s1;
        refreshMeta = flag;
    }
}
