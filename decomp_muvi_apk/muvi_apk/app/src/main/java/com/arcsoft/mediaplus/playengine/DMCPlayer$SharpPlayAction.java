// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayer, DMCPlayEngine

public class > extends >
{

    private mOpenAction mOpenAction;
    private mOpenAction mSeekAction;
    final DMCPlayer this$0;

    protected void doAction()
    {
        mEngine.play();
    }

    protected >[] getActionConditions()
    {
        if (mSeekAction != null)
        {
            > a>1[] = new mSeekAction[1];
            a>1[0] = SEEKED;
            return a>1;
        } else
        {
            > a>[] = new SEEKED[1];
            a>[0] = OPENED;
            return a>;
        }
    }

    protected OPENED getFinishConditions()
    {
        return STARTEDPLAY;
    }

    protected STARTEDPLAY[] getPreActions()
    {
        if (mSeekAction != null)
        {
            STARTEDPLAY astartedplay1[] = new mSeekAction[1];
            astartedplay1[0] = mSeekAction;
            return astartedplay1;
        } else
        {
            STARTEDPLAY astartedplay[] = new mSeekAction[1];
            astartedplay[0] = mOpenAction;
            return astartedplay;
        }
    }

    public (long l, long l1,  )
    {
        this$0 = DMCPlayer.this;
        super(DMCPlayer.this);
        mSeekAction = null;
        mOpenAction = null;
        if (l1 > 0L)
        {
            mSeekAction = new <init>(DMCPlayer.this, l, l1, );
            return;
        } else
        {
            mOpenAction = new >(DMCPlayer.this, l, );
            return;
        }
    }
}
