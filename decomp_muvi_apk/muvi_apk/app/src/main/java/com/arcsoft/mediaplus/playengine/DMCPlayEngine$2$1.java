// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngine, DMCPlayEngineStatusMachine

class val.data
    implements Runnable
{

    final t this$1;
    final com.arcsoft.adk.atv.sc val$data;

    public void run()
    {
        if (DMCPlayEngine.access$200(_fld0) != null && val$data.m_strUuid.equals(DMCPlayEngine.access$200(_fld0)))
        {
            reset();
        }
    }

    is._cls0()
    {
        this$1 = final__pcls0;
        val$data = com.arcsoft.adk.atv.sc.this;
        super();
    }

    // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$2

/* anonymous class */
    class DMCPlayEngine._cls2
        implements com.arcsoft.adk.atv.RenderManager.IRenderStatusListener
    {

        final DMCPlayEngine this$0;

        public void onGetProtocolInfo(String s, com.arcsoft.adk.atv.MRCPCallback.DataOnGetProtocolInfo dataongetprotocolinfo, int i)
        {
        }

        public void onRenderAdded(com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc)
        {
            DMCPlayEngine.access$300(DMCPlayEngine.this, mediarenderdesc. new DMCPlayEngine._cls2._cls1());
        }

        public void onRenderInstalled(com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc, boolean flag, boolean flag1, boolean flag2)
        {
        }

        public void onRenderRemoved(final com.arcsoft.adk.atv.UPnP.MediaRenderDesc data)
        {
            DMCPlayEngine.access$300(DMCPlayEngine.this, new DMCPlayEngine._cls2._cls2());
        }

            
            {
                this$0 = DMCPlayEngine.this;
                super();
            }

        // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$2$2

/* anonymous class */
        class DMCPlayEngine._cls2._cls2
            implements Runnable
        {

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
        }

    }

}
