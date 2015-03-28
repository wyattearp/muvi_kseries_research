// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;

import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayer, DMCPlayEngine

private class > extends >
{

    private mOpenAction mOpenAction;
    private long mSeekPos;
    final DMCPlayer this$0;

    protected void doAction()
    {
        Log.v(DMCPlayer.TAG, (new StringBuilder()).append("SharpSeekAction pos=").append(mSeekPos).toString());
        if (mSeekPos > 0L)
        {
            mEngine.seekTo(mSeekPos);
        }
    }

    protected mSeekPos[] getActionConditions()
    {
        mSeekPos amseekpos[] = new mSeekPos[1];
        amseekpos[0] = OPENED;
        return amseekpos;
    }

    protected OPENED getFinishConditions()
    {
        if (mSeekPos > 0L)
        {
            return SEEKED;
        } else
        {
            return null;
        }
    }

    protected SEEKED[] getPreActions()
    {
        SEEKED aseeked[] = new SEEKED[1];
        aseeked[0] = mOpenAction;
        return aseeked;
    }

    (long l, long l1,  )
    {
        this$0 = DMCPlayer.this;
        super(DMCPlayer.this);
        mSeekPos = 0L;
        mOpenAction = null;
        mSeekPos = l1;
        mOpenAction = new >(DMCPlayer.this, l, );
    }
}
