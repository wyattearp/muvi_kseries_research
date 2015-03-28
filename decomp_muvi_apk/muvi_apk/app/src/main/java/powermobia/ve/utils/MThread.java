// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.utils;


public class MThread
{

    Thread hThread;

    private MThread()
    {
        hThread = null;
    }

    private native int nativeThreadProc(long l, long l1);

    public int create(final long threadProc, final long userData)
    {
        hThread = new Thread(new Runnable() {

            final MThread this$0;
            private final long val$threadProc;
            private final long val$userData;

            public void run()
            {
                nativeThreadProc(threadProc, userData);
            }

            
            {
                this$0 = MThread.this;
                threadProc = l;
                userData = l1;
                super();
            }
        });
        hThread.start();
        return 0;
    }

    public int destroy()
    {
        return 0;
    }

    public int exit()
    {
        if (hThread.isAlive())
        {
            try
            {
                hThread.join();
            }
            catch (Exception exception) { }
        }
        return 0;
    }

    public int resume()
    {
        this;
        JVM INSTR monitorenter ;
        hThread.notify();
        this;
        JVM INSTR monitorexit ;
        return 0;
        Exception exception;
        exception;
        throw exception;
    }

    public int sleep(int i)
    {
        long l = i;
        try
        {
            Thread.sleep(l);
        }
        catch (Exception exception) { }
        return 0;
    }

    public int suspend()
    {
        this;
        JVM INSTR monitorenter ;
        hThread.wait();
_L2:
        this;
        JVM INSTR monitorexit ;
        return 0;
        InterruptedException interruptedexception;
        interruptedexception;
        interruptedexception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

}
