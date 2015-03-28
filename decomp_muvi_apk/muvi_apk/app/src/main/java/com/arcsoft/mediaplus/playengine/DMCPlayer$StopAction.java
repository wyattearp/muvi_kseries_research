// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayer, DMCPlayEngine

private class mPlayEffect extends n
{

     mPlayEffect;
    final DMCPlayer this$0;

    protected void doAction()
    {
        mEngine.stop(mPlayEffect);
    }

    protected boolean finishAfterDoAction()
    {
        return false;
    }

    protected tion[] getActionConditions()
    {
        tion ation[] = new tion[1];
        ation[0] = tion.ENGINESTABEL;
        return ation;
    }

    protected tion getFinishConditions()
    {
        return tion.STOPPED;
    }

    protected n[] getPreActions()
    {
        return null;
    }

    ( )
    {
        this$0 = DMCPlayer.this;
        super(DMCPlayer.this);
        mPlayEffect = .EFFECT_NONE;
        mPlayEffect = ;
    }
}
