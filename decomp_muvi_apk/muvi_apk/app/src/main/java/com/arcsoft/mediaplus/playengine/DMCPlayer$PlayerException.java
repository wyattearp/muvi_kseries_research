// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayer

private class mError extends Exception
{

    public mError mError;
    final DMCPlayer this$0;

    public mError getError()
    {
        return mError;
    }

    public ( )
    {
        this$0 = DMCPlayer.this;
        super();
        mError = UNKNOWN;
        mError = ;
    }
}
