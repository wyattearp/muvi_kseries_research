// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngine, DMCPlayEngineStatusMachine

class this._cls0
    implements com.arcsoft.adk.atv.enderStatusListener
{

    final DMCPlayEngine this$0;

    public void onGetProtocolInfo(String s, com.arcsoft.adk.atv.aOnGetProtocolInfo aongetprotocolinfo, int i)
    {
    }

    public void onRenderAdded(final com.arcsoft.adk.atv.Desc data)
    {
        DMCPlayEngine.access$300(DMCPlayEngine.this, new Runnable() {

            final DMCPlayEngine._cls2 this$1;
            final com.arcsoft.adk.atv.UPnP.MediaRenderDesc val$data;

            public void run()
            {
                if (DMCPlayEngine.access$200(this$0) != null && data.m_strUuid.equals(DMCPlayEngine.access$200(this$0)))
                {
                    reset();
                }
            }

            
            {
                this$1 = DMCPlayEngine._cls2.this;
                data = mediarenderdesc;
                super();
            }
        });
    }

    public void onRenderInstalled(com.arcsoft.adk.atv.Desc desc, boolean flag, boolean flag1, boolean flag2)
    {
    }

    public void onRenderRemoved(final com.arcsoft.adk.atv.Desc data)
    {
        DMCPlayEngine.access$300(DMCPlayEngine.this, new Runnable() {

            final DMCPlayEngine._cls2 this$1;
            final com.arcsoft.adk.atv.UPnP.MediaRenderDesc val$data;

            public void run()
            {
                if (DMCPlayEngine.access$200(this$0) != null && data.m_strUuid.equals(DMCPlayEngine.access$200(this$0)))
                {
                    reset();
                    DMCPlayEngine.access$400(this$0).setStatus(DMCPlayEngineStatusMachine.Status.FAULT);
                    if (DMCPlayEngine.access$500(this$0) != null)
                    {
                        DMCPlayEngine.access$500(this$0).OnError(IPlayEngine.PlayEngineError.ERROR_RENDER_DISCONNECTED);
                    }
                }
            }

            
            {
                this$1 = DMCPlayEngine._cls2.this;
                data = mediarenderdesc;
                super();
            }
        });
    }

    _cls2.val.data()
    {
        this$0 = DMCPlayEngine.this;
        super();
    }
}
