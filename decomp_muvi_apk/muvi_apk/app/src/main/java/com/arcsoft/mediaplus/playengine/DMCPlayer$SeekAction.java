// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayer, DMCPlayEngine

public class  extends n
{

    private mPlayAction mPlayAction;
    private long mSeekPos;
    final DMCPlayer this$0;

    protected void doAction()
    {
        if (mSeekPos > 0L)
        {
            mEngine.seekTo(mSeekPos);
        }
    }

    protected tion[] getActionConditions()
    {
        tion ation[] = new tion[1];
        ation[0] = tion.STARTEDPLAY;
        return ation;
    }

    protected tion getFinishConditions()
    {
        if (mSeekPos > 0L)
        {
            return tion.SEEKED;
        } else
        {
            return null;
        }
    }

    protected n[] getPreActions()
    {
        n an[] = new n[1];
        an[0] = mPlayAction;
        return an;
    }

    public (long l, long l1,  )
    {
        this$0 = DMCPlayer.this;
        super(DMCPlayer.this);
        mSeekPos = 0L;
        mPlayAction = null;
        mSeekPos = l1;
        mPlayAction = new <init>(DMCPlayer.this, l, );
    }
}
