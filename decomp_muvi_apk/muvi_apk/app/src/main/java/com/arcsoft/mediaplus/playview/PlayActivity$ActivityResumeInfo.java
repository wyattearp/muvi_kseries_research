// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;


// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

protected class mPlayPosition
{

    protected boolean mIsActivityResume;
    protected long mPlayPosition;
    protected boolean mStartDMPAfterResume;
    final PlayActivity this$0;

    protected ()
    {
        this$0 = PlayActivity.this;
        super();
        mIsActivityResume = false;
        mStartDMPAfterResume = false;
        mPlayPosition = 0L;
    }
}
