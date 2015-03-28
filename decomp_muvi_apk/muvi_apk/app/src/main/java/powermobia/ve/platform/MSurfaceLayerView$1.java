// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.platform;

import java.util.TimerTask;

// Referenced classes of package powermobia.ve.platform:
//            MSurfaceLayerView

class this._cls0 extends TimerTask
{

    final MSurfaceLayerView this$0;

    public void run()
    {
        synchronized (MSurfaceLayerView.access$0(MSurfaceLayerView.this))
        {
            if (MSurfaceLayerView.access$1(MSurfaceLayerView.this))
            {
                postInvalidate();
                MSurfaceLayerView.access$2(MSurfaceLayerView.this, false);
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ()
    {
        this$0 = MSurfaceLayerView.this;
        super();
    }
}
