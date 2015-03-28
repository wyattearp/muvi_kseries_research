// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;


// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity, PlayView

class this._cls0
    implements ewContorller
{

    final PlayActivity this$0;

    public void requestQuit(PlayView playview)
    {
        if (playview != PlayActivity.access$600(PlayActivity.this)[PlayActivity.access$700(PlayActivity.this)])
        {
            return;
        }
        if (isDMPView() || PlayActivity.access$600(PlayActivity.this)[0] == null)
        {
            release(true);
            finish();
            return;
        }
        PlayActivity.access$1900(PlayActivity.this);
        sion sion = PlayActivity.access$2000(PlayActivity.this);
        PlayActivity.access$702(PlayActivity.this, 1 ^ PlayActivity.access$700(PlayActivity.this));
        if (mResumeInfo.mIsActivityResume)
        {
            PlayActivity.access$2100(PlayActivity.this);
            PlayActivity.access$2200(PlayActivity.this, sion.position);
            return;
        } else
        {
            mResumeInfo.mPlayPosition = sion.position;
            mResumeInfo.mStartDMPAfterResume = true;
            return;
        }
    }

    sion()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
