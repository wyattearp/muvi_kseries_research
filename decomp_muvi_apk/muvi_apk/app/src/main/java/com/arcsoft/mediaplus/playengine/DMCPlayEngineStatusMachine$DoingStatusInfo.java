// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

private abstract class <init>
    implements <init>
{

    final DMCPlayEngineStatusMachine this$0;

    public <init> getAfterDoActionStatus(<init> <init>1, boolean flag)
    {
        return <init>;
    }

    abstract <init> getCauseAction();

    abstract <init> getDestinationStatus(<init> <init>1, boolean flag);

    public <init> getDoActionByRequestAction(<init> <init>1)
    {
        return <init>1;
    }

    public boolean isSupportedAction(<init> <init>1)
    {
        return false;
    }

    private Q()
    {
        this$0 = DMCPlayEngineStatusMachine.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
