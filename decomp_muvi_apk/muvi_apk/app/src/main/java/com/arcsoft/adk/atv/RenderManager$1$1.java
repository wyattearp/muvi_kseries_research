// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.util.debug.Log;
import java.util.HashMap;

// Referenced classes of package com.arcsoft.adk.atv:
//            RenderManager, DLNA, MRCPCallback

class nRenderAdded
    implements Runnable
{

    final is._cls0 this$1;
    final nRenderAdded val$data;

    public void run()
    {
        while (!isRenderOnline(val$data.m_RenderDesc.m_strUuid) || getRenderProtocolInfo(val$data.m_RenderDesc.m_strUuid) != null) 
        {
            return;
        }
        try
        {
            getDMRProtocolInfoAsync(val$data.m_RenderDesc.m_strUuid);
            return;
        }
        catch (Exception exception)
        {
            RenderManager.access$400(_fld0).postRunnable(this, 2000L);
        }
    }

    nRenderAdded()
    {
        this$1 = final_nrenderadded;
        val$data = nRenderAdded.this;
        super();
    }

    // Unreferenced inner class com/arcsoft/adk/atv/RenderManager$1

/* anonymous class */
    class RenderManager._cls1
        implements MRCPCallback
    {

        final RenderManager this$0;

        public void OnGetCurrentTransportActions(int i, String s, String s1)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onGetCurrentTransportActions(i, s, s1);
                }

            }
        }

        public void OnGetMeidaInfo(int i, MRCPCallback.DataOnGetMediaInfo dataongetmediainfo, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onGetMeidaInfo(i, dataongetmediainfo, s);
                }

            }
        }

        public void OnGetMute(int i, boolean flag, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onGetMute(i, flag, s);
                }

            }
        }

        public void OnGetPositionInfo(int i, MRCPCallback.DataOnGetPositionInfo dataongetpositioninfo, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onGetPositionInfo(i, dataongetpositioninfo, s);
                }

            }
        }

        public void OnGetProtocolInfo(int i, MRCPCallback.DataOnGetProtocolInfo dataongetprotocolinfo, String s)
        {
            mProtocolInfos.remove(s);
            mProtocolInfos.put(s, dataongetprotocolinfo);
            RenderManager.IRenderStatusListener airenderstatuslistener[] = RenderManager.access$500(RenderManager.this);
            if (airenderstatuslistener != null)
            {
                int j = airenderstatuslistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderstatuslistener[k].onGetProtocolInfo(s, dataongetprotocolinfo, i);
                }

            }
        }

        public void OnGetTransportInfo(int i, MRCPCallback.DataOnGetTransportInfo dataongettransportinfo, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onGetTransportInfo(i, dataongettransportinfo, s);
                }

            }
        }

        public void OnGetTransportSettings(int i, MRCPCallback.DataOnGetTransportSettings dataongettransportsettings, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onGetTransportSettings(i, dataongettransportsettings, s);
                }

            }
        }

        public void OnGetVolume(int i, int j, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int k = airenderplaylistener.length;
                for (int l = 0; l < k; l++)
                {
                    airenderplaylistener[l].onGetVolume(i, j, s);
                }

            }
        }

        public void OnMediaNext(int i, String s)
        {
        }

        public void OnMediaPause(int i, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onMediaPause(i, s);
                }

            }
        }

        public void OnMediaPlay(int i, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onMediaPlay(i, s);
                }

            }
        }

        public void OnMediaPrevious(int i, String s)
        {
        }

        public void OnMediaSeek(int i, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onMediaSeek(i, s);
                }

            }
        }

        public void OnMediaStop(int i, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onMediaStop(i, s);
                }

            }
        }

        public void OnRenderAdded(MRCPCallback.DataOnRenderAdded dataonrenderadded)
        {
            Log.i("", (new StringBuilder()).append("OnRenderAdded: ").append(dataonrenderadded.m_RenderDesc.m_strUuid).toString());
            RenderManager.access$300(RenderManager.this, dataonrenderadded.m_RenderDesc);
            RenderManager.access$400(RenderManager.this).postRunnable(dataonrenderadded. new RenderManager._cls1._cls1(), 2000L);
            RenderManager.access$300(RenderManager.this, dataonrenderadded.m_RenderDesc);
            RenderManager.IRenderStatusListener airenderstatuslistener[] = RenderManager.access$500(RenderManager.this);
            if (airenderstatuslistener != null)
            {
                int i = airenderstatuslistener.length;
                for (int j = 0; j < i; j++)
                {
                    airenderstatuslistener[j].onRenderAdded(dataonrenderadded.m_RenderDesc);
                }

            }
        }

        public void OnRenderInstalled(UPnP.MediaRenderDesc mediarenderdesc, boolean flag, boolean flag1, boolean flag2)
        {
            RenderManager.IRenderStatusListener airenderstatuslistener[] = RenderManager.access$500(RenderManager.this);
            if (airenderstatuslistener != null)
            {
                int i = airenderstatuslistener.length;
                for (int j = 0; j < i; j++)
                {
                    airenderstatuslistener[j].onRenderInstalled(mediarenderdesc, flag, flag1, flag2);
                }

            }
        }

        public void OnRenderRemoved(MRCPCallback.DataOnRenderRemoved dataonrenderremoved)
        {
            Log.i("", (new StringBuilder()).append("OnRenderRemoved: ").append(dataonrenderremoved.m_RenderDesc.m_strUuid).toString());
            RenderManager.access$600(RenderManager.this, dataonrenderremoved.m_RenderDesc);
            String s = Settings.instance().getDefaultDMRUDN();
            if (s != null && dataonrenderremoved.m_RenderDesc.m_strUuid.equalsIgnoreCase(s))
            {
                Context context = RenderManager.access$700(RenderManager.this).getApplicationContext();
                String s1 = context.getString(0x7f0b0017);
                Object aobj[] = new Object[1];
                aobj[0] = dataonrenderremoved.m_RenderDesc.m_strFriendlyName;
                Toast.makeText(context, String.format(s1, aobj), 1).show();
            }
            RenderManager.IRenderStatusListener airenderstatuslistener[] = RenderManager.access$500(RenderManager.this);
            if (airenderstatuslistener != null)
            {
                int i = airenderstatuslistener.length;
                for (int j = 0; j < i; j++)
                {
                    airenderstatuslistener[j].onRenderRemoved(dataonrenderremoved.m_RenderDesc);
                }

            }
        }

        public void OnSetAVTransportURI(int i, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onOpenMedia(i, s);
                }

            }
        }

        public void OnSetMute(int i, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onSetMute(i, s);
                }

            }
        }

        public void OnSetVolume(int i, String s)
        {
            RenderManager.IRenderPlayListener airenderplaylistener[] = RenderManager.access$800(RenderManager.this);
            if (airenderplaylistener != null)
            {
                int j = airenderplaylistener.length;
                for (int k = 0; k < j; k++)
                {
                    airenderplaylistener[k].onSetVolume(i, s);
                }

            }
        }

            
            {
                this$0 = RenderManager.this;
                super();
            }
    }

}
