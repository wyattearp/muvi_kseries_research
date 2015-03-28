// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

private abstract class <init> extends <init>
{

    final DMCPlayEngineStatusMachine this$0;

    public <init> getAfterDoActionStatus(<init> <init>1, boolean flag)
    {
label0:
        {
            <init> <init>2 = <init>;
            if (<init>1 == <init>)
            {
                if (!flag)
                {
                    break label0;
                }
                <init>2 = <init>;
            }
            return <init>2;
        }
        return <init>;
    }

    public <init> getDoActionByRequestAction(<init> <init>1)
    {
        return <init>1;
    }

    public boolean isSupportedAction(<init> <init>1)
    {
        return <init>1 == <init>;
    }

    private Y()
    {
        this$0 = DMCPlayEngineStatusMachine.this;
        super(DMCPlayEngineStatusMachine.this, null);
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
