// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.utils;


// Referenced classes of package powermobia.ve.utils:
//            MThread

class val.userData
    implements Runnable
{

    final MThread this$0;
    private final long val$threadProc;
    private final long val$userData;

    public void run()
    {
        MThread.access$0(MThread.this, val$threadProc, val$userData);
    }

    ()
    {
        this$0 = final_mthread;
        val$threadProc = l;
        val$userData = J.this;
        super();
    }
}
