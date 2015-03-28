// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayer, DMCPlayEngine

private class  extends n
{

    mOpenAction mOpenAction;
    final DMCPlayer this$0;

    protected void doAction()
    {
        mEngine.play();
    }

    protected tion[] getActionConditions()
    {
        tion ation[] = new tion[1];
        ation[0] = tion.OPENED;
        return ation;
    }

    protected tion getFinishConditions()
    {
        return tion.STARTEDPLAY;
    }

    protected n[] getPreActions()
    {
        n an[] = new n[1];
        an[0] = mOpenAction;
        return an;
    }

    (long l,  )
    {
        this$0 = DMCPlayer.this;
        super(DMCPlayer.this);
        mOpenAction = null;
        mOpenAction = new <init>(DMCPlayer.this, l, );
    }
}
