// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;

import android.net.Uri;

// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayer, DMCPlayEngine

private class  extends n
{

    RROR_UNSUPPORT mStopAction;
    private RROR_UNSUPPORT mUrlInfo;
    final DMCPlayer this$0;

    protected void doAction()
    {
        mEngine.open(Uri.parse(mUrlInfo.url), mUrlInfo.meta);
    }

    protected tion[] getActionConditions()
    {
        tion ation[] = new tion[2];
        ation[0] = tion.STOPPED;
        ation[1] = tion.URLREADY;
        return ation;
    }

    protected tion getFinishConditions()
    {
        return tion.OPENED;
    }

    protected n[] getPreActions()
    {
        n an[] = new n[1];
        an[0] = mStopAction;
        return an;
    }

    protected boolean onConditionArrival(tion tion, Object obj)
        throws tion
    {
        if (tion != tion.URLREADY)
        {
            return super.onConditionArrival(tion, obj);
        }
        tion tion1 = (tion)obj;
        if (tion1.mid == mUrlInfo.mid)
        {
            if (tion1.url != null)
            {
                mUrlInfo = tion1;
                return true;
            } else
            {
                throw new tion(DMCPlayer.this, RROR_UNSUPPORT);
            }
        } else
        {
            return false;
        }
    }

    (long l,  )
    {
        this$0 = DMCPlayer.this;
        super(DMCPlayer.this);
        mUrlInfo = null;
        mStopAction = null;
        mUrlInfo = new it>(DMCPlayer.this, null);
        mUrlInfo.mid = l;
        mStopAction = new <init>(DMCPlayer.this, );
    }
}
