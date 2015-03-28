// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.widget.Toast;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.util.TimeUtils;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.arcsoft.adk.atv:
//            DLNA, DLNAException, MRCPCallback

public class RenderManager
{
    public static interface IRenderPlayListener
    {

        public abstract void onGetCurrentTransportActions(int i, String s, String s1);

        public abstract void onGetMeidaInfo(int i, MRCPCallback.DataOnGetMediaInfo dataongetmediainfo, String s);

        public abstract void onGetMute(int i, boolean flag, String s);

        public abstract void onGetPositionInfo(int i, MRCPCallback.DataOnGetPositionInfo dataongetpositioninfo, String s);

        public abstract void onGetTransportInfo(int i, MRCPCallback.DataOnGetTransportInfo dataongettransportinfo, String s);

        public abstract void onGetTransportSettings(int i, MRCPCallback.DataOnGetTransportSettings dataongettransportsettings, String s);

        public abstract void onGetVolume(int i, int j, String s);

        public abstract void onMediaPause(int i, String s);

        public abstract void onMediaPlay(int i, String s);

        public abstract void onMediaSeek(int i, String s);

        public abstract void onMediaStop(int i, String s);

        public abstract void onOpenMedia(int i, String s);

        public abstract void onSetMute(int i, String s);

        public abstract void onSetVolume(int i, String s);
    }

    public static interface IRenderStatusListener
    {

        public abstract void onGetProtocolInfo(String s, MRCPCallback.DataOnGetProtocolInfo dataongetprotocolinfo, int i);

        public abstract void onRenderAdded(UPnP.MediaRenderDesc mediarenderdesc);

        public abstract void onRenderInstalled(UPnP.MediaRenderDesc mediarenderdesc, boolean flag, boolean flag1, boolean flag2);

        public abstract void onRenderRemoved(UPnP.MediaRenderDesc mediarenderdesc);
    }

    public static final class PlaySpeed extends Enum
    {

        private static final PlaySpeed $VALUES[];
        public static final PlaySpeed NORMAL;
        private final String nativeValue;

        public static PlaySpeed valueOf(String s)
        {
            return (PlaySpeed)Enum.valueOf(com/arcsoft/adk/atv/RenderManager$PlaySpeed, s);
        }

        public static PlaySpeed[] values()
        {
            return (PlaySpeed[])$VALUES.clone();
        }

        static 
        {
            NORMAL = new PlaySpeed("NORMAL", 0, "1");
            PlaySpeed aplayspeed[] = new PlaySpeed[1];
            aplayspeed[0] = NORMAL;
            $VALUES = aplayspeed;
        }


        private PlaySpeed(String s, int i, String s1)
        {
            super(s, i);
            nativeValue = s1;
        }
    }

    public static final class SeekMode extends Enum
    {

        private static final SeekMode $VALUES[];
        public static final SeekMode RELTIME;
        private final String nativeValue;

        public static SeekMode valueOf(String s)
        {
            return (SeekMode)Enum.valueOf(com/arcsoft/adk/atv/RenderManager$SeekMode, s);
        }

        public static SeekMode[] values()
        {
            return (SeekMode[])$VALUES.clone();
        }

        static 
        {
            RELTIME = new SeekMode("RELTIME", 0, "REL_TIME");
            SeekMode aseekmode[] = new SeekMode[1];
            aseekmode[0] = RELTIME;
            $VALUES = aseekmode;
        }


        private SeekMode(String s, int i, String s1)
        {
            super(s, i);
            nativeValue = s1;
        }
    }

    public static final class VolumeChannel extends Enum
    {

        private static final VolumeChannel $VALUES[];
        public static final VolumeChannel MASTER;
        private final String nativeValue;

        public static VolumeChannel valueOf(String s)
        {
            return (VolumeChannel)Enum.valueOf(com/arcsoft/adk/atv/RenderManager$VolumeChannel, s);
        }

        public static VolumeChannel[] values()
        {
            return (VolumeChannel[])$VALUES.clone();
        }

        static 
        {
            MASTER = new VolumeChannel("MASTER", 0, "Master");
            VolumeChannel avolumechannel[] = new VolumeChannel[1];
            avolumechannel[0] = MASTER;
            $VALUES = avolumechannel;
        }


        private VolumeChannel(String s, int i, String s1)
        {
            super(s, i);
            nativeValue = s1;
        }
    }


    public static final String SHARPTV_EXITFALSE = "FALSE";
    public static final String SHARPTV_EXITTRUE = "TRUE";
    public static final String SHARPTV_FADEINEFFECT[] = {
        "FADE_IN"
    };
    public static final String SHARPTV_FADEOUTEFFECT[] = {
        "FADE_OUT"
    };
    public static final String SHARPTV_SLIDEDOWNEFFECT[] = {
        "SLIDE_DOWN", "FADE_OUT"
    };
    public static final String SHARPTV_SLIDEUPEFFECT[] = {
        "SLIDE_UP", "FADE_IN"
    };
    private static final String SHARPTV_SUBSTRING = "aquos";
    public static final String TRANSPORT_INFO_NOMEDIA = "NO_MEDIA_PRESENT";
    public static final String TRANSPORT_INFO_PAUSED = "PAUSED_PLAYBACK";
    public static final String TRANSPORT_INFO_PLAYING = "PLAYING";
    public static final String TRANSPORT_INFO_STOPPED = "STOPPED";
    private Application mApplication;
    private DLNA mDLNA;
    private DLNA.IOnDLNAStatusChangeListener mDLNAStatusListener;
    HashMap mProtocolInfos;
    private List mRenderCache;
    private MRCPCallback mRenderCallback;
    private List mRenderPlayListeners;
    private List mRenderStatusListeners;

    RenderManager(Application application, DLNA dlna)
    {
        mApplication = null;
        mDLNA = null;
        mRenderCache = new ArrayList();
        mProtocolInfos = new HashMap();
        mRenderStatusListeners = new ArrayList();
        mRenderPlayListeners = new ArrayList();
        mRenderCallback = new MRCPCallback() {

            final RenderManager this$0;

            public void OnGetCurrentTransportActions(int i, String s, String s1)
            {
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                IRenderStatusListener airenderstatuslistener[] = getStatusListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                addRenderCache(dataonrenderadded.m_RenderDesc);
                mDLNA.postRunnable(dataonrenderadded. new Runnable() {

                    final _cls1 this$1;
                    final MRCPCallback.DataOnRenderAdded val$data;

                    public void run()
                    {
                        while (!isRenderOnline(data.m_RenderDesc.m_strUuid) || getRenderProtocolInfo(data.m_RenderDesc.m_strUuid) != null) 
                        {
                            return;
                        }
                        try
                        {
                            getDMRProtocolInfoAsync(data.m_RenderDesc.m_strUuid);
                            return;
                        }
                        catch (Exception exception)
                        {
                            mDLNA.postRunnable(this, 2000L);
                        }
                    }

            
            {
                this$1 = final__pcls1;
                data = MRCPCallback.DataOnRenderAdded.this;
                super();
            }
                }, 2000L);
                addRenderCache(dataonrenderadded.m_RenderDesc);
                IRenderStatusListener airenderstatuslistener[] = getStatusListenersCopy();
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
                IRenderStatusListener airenderstatuslistener[] = getStatusListenersCopy();
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
                removeRenderCache(dataonrenderremoved.m_RenderDesc);
                String s = Settings.instance().getDefaultDMRUDN();
                if (s != null && dataonrenderremoved.m_RenderDesc.m_strUuid.equalsIgnoreCase(s))
                {
                    Context context = mApplication.getApplicationContext();
                    String s1 = context.getString(0x7f0b0017);
                    Object aobj[] = new Object[1];
                    aobj[0] = dataonrenderremoved.m_RenderDesc.m_strFriendlyName;
                    Toast.makeText(context, String.format(s1, aobj), 1).show();
                }
                IRenderStatusListener airenderstatuslistener[] = getStatusListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
                IRenderPlayListener airenderplaylistener[] = getPlayListenersCopy();
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
        };
        mDLNAStatusListener = new DLNA.IOnDLNAStatusChangeListener() {

            final RenderManager this$0;

            public void OnDLNAConnected()
            {
            }

            public void OnDLNADisconnected()
            {
                IRenderStatusListener airenderstatuslistener[] = getStatusListenersCopy();
                if (airenderstatuslistener != null)
                {
                    for (Iterator iterator = mRenderCache.iterator(); iterator.hasNext();)
                    {
                        UPnP.MediaRenderDesc mediarenderdesc = (UPnP.MediaRenderDesc)iterator.next();
                        int i = airenderstatuslistener.length;
                        int j = 0;
                        while (j < i) 
                        {
                            airenderstatuslistener[j].onRenderRemoved(mediarenderdesc);
                            j++;
                        }
                    }

                }
                clearRenderCache();
            }

            public void OnDLNAInternalError(int i)
            {
            }

            
            {
                this$0 = RenderManager.this;
                super();
            }
        };
        mDLNA = dlna;
        mApplication = application;
        mDLNA.registerDLNAStatusListener(mDLNAStatusListener);
    }

    private void addRenderCache(UPnP.MediaRenderDesc mediarenderdesc)
    {
        if (mediarenderdesc == null || mediarenderdesc.m_strUuid == null)
        {
            return;
        }
        synchronized (mRenderCache)
        {
            removeRenderCache(mediarenderdesc);
            mRenderCache.add(mediarenderdesc);
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void checkDLNAorThrow()
    {
        if (mDLNA == null)
        {
            throw new IllegalStateException("Invalide ServerManager State - DLNA obj is null");
        } else
        {
            return;
        }
    }

    private void clearRenderCache()
    {
        if (mRenderCache == null)
        {
            return;
        }
        List list = mRenderCache;
        list;
        JVM INSTR monitorenter ;
        for (Iterator iterator = mRenderCache.iterator(); iterator.hasNext(); recycleRenderDesc((UPnP.MediaRenderDesc)iterator.next())) { }
        break MISSING_BLOCK_LABEL_55;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
        mRenderCache.clear();
        list;
        JVM INSTR monitorexit ;
    }

    private IRenderPlayListener[] getPlayListenersCopy()
    {
        List list = mRenderPlayListeners;
        list;
        JVM INSTR monitorenter ;
        int i = mRenderPlayListeners.size();
        IRenderPlayListener airenderplaylistener[];
        airenderplaylistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        IRenderPlayListener airenderplaylistener1[] = new IRenderPlayListener[mRenderPlayListeners.size()];
        airenderplaylistener = (IRenderPlayListener[])mRenderPlayListeners.toArray(airenderplaylistener1);
        list;
        JVM INSTR monitorexit ;
        return airenderplaylistener;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private IRenderStatusListener[] getStatusListenersCopy()
    {
        List list = mRenderStatusListeners;
        list;
        JVM INSTR monitorenter ;
        int i = mRenderStatusListeners.size();
        IRenderStatusListener airenderstatuslistener[];
        airenderstatuslistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        IRenderStatusListener airenderstatuslistener1[] = new IRenderStatusListener[mRenderStatusListeners.size()];
        airenderstatuslistener = (IRenderStatusListener[])mRenderStatusListeners.toArray(airenderstatuslistener1);
        list;
        JVM INSTR monitorexit ;
        return airenderstatuslistener;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void recycleRenderDesc(UPnP.MediaRenderDesc mediarenderdesc)
    {
        if (mediarenderdesc != null && mediarenderdesc.m_DeviceIcon != null)
        {
            mediarenderdesc.m_DeviceIcon.recycle();
        }
    }

    private void removeRenderCache(UPnP.MediaRenderDesc mediarenderdesc)
    {
        if (mediarenderdesc == null || mediarenderdesc.m_strUuid == null)
        {
            return;
        }
        synchronized (mRenderCache)
        {
            Iterator iterator = mRenderCache.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                UPnP.MediaRenderDesc mediarenderdesc1 = (UPnP.MediaRenderDesc)iterator.next();
                if (!mediarenderdesc1.m_strUuid.equalsIgnoreCase(mediarenderdesc.m_strUuid))
                {
                    continue;
                }
                recycleRenderDesc(mediarenderdesc1);
                iterator.remove();
                break;
            } while (true);
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean getCurrentTransportActionsAsync(String s)
    {
        checkDLNAorThrow();
        long al[] = {
            0L
        };
        return DLNA.JNI_GetCurrentTransportActions(mDLNA.mNativeUPnP, s, 0, al) == 0;
    }

    public void getDMRProtocolInfoAsync(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        long al[] = {
            0L
        };
        int i = DLNA.JNI_GetProtocolInfo(mDLNA.mNativeUPnP, s, al);
        if (i != 0)
        {
            throw new DLNAException(i);
        } else
        {
            return;
        }
    }

    public boolean getMediaInfoAsync(String s)
    {
        checkDLNAorThrow();
        long al[] = {
            0L
        };
        DLNA.JNI_GetMediaInfo(mDLNA.mNativeUPnP, s, 0, al);
        return true;
    }

    public boolean getMuteAsync(String s, VolumeChannel volumechannel)
    {
        checkDLNAorThrow();
        long al[] = {
            0L
        };
        return DLNA.JNI_GetMute(mDLNA.mNativeUPnP, s, 0, volumechannel.nativeValue, al) == 0;
    }

    public boolean getPositionInfoAsync(String s)
    {
        checkDLNAorThrow();
        long al[] = {
            0L
        };
        return DLNA.JNI_GetPositionInfo(mDLNA.mNativeUPnP, s, 0, al) == 0;
    }

    MRCPCallback getProxyCallback()
    {
        return mRenderCallback;
    }

    public UPnP.MediaRenderDesc getRenderDesc(String s)
    {
        if (s == null)
        {
            return null;
        }
        UPnP.MediaRenderDesc mediarenderdesc;
        synchronized (mRenderCache)
        {
            Iterator iterator = mRenderCache.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break MISSING_BLOCK_LABEL_68;
                }
                mediarenderdesc = (UPnP.MediaRenderDesc)iterator.next();
            } while (!s.equalsIgnoreCase(mediarenderdesc.m_strUuid));
        }
        return mediarenderdesc;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
        list;
        JVM INSTR monitorexit ;
        return null;
    }

    public List getRenderList()
    {
        return mRenderCache;
    }

    public MRCPCallback.DataOnGetProtocolInfo getRenderProtocolInfo(String s)
    {
        MRCPCallback.DataOnGetProtocolInfo dataongetprotocolinfo;
        synchronized (mProtocolInfos)
        {
            dataongetprotocolinfo = (MRCPCallback.DataOnGetProtocolInfo)mProtocolInfos.get(s);
        }
        return dataongetprotocolinfo;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean getTransportInfoAsync(String s)
    {
        checkDLNAorThrow();
        long al[] = {
            0L
        };
        DLNA.JNI_GetTransportInfo(mDLNA.mNativeUPnP, s, 0, al);
        return true;
    }

    public boolean getTransportSettingsAsync(String s)
    {
        checkDLNAorThrow();
        long al[] = {
            0L
        };
        return DLNA.JNI_GetTransportSettings(mDLNA.mNativeUPnP, s, 0, al) == 0;
    }

    public boolean getVolumeAsync(String s, VolumeChannel volumechannel)
    {
        checkDLNAorThrow();
        long al[] = {
            0L
        };
        return DLNA.JNI_GetVolume(mDLNA.mNativeUPnP, s, 0, volumechannel.nativeValue, al) == 0;
    }

    public boolean installRender(String s)
    {
        checkDLNAorThrow();
        boolean aflag[] = {
            false
        };
        return DLNA.JNI_InstallRender(mDLNA.mNativeUPnP, s, aflag) == 0;
    }

    public boolean installRender(String s, boolean aflag[])
    {
        checkDLNAorThrow();
        return DLNA.JNI_InstallRender(mDLNA.mNativeUPnP, s, aflag) == 0;
    }

    public boolean isRenderOnline(String s)
    {
        return getRenderDesc(s) != null;
    }

    public boolean isSharpDMR(UPnP.MediaRenderDesc mediarenderdesc)
    {
        if (mediarenderdesc == null)
        {
            return false;
        }
        return mediarenderdesc != null && mediarenderdesc.m_strModelName != null && mediarenderdesc.m_strModelName.toLowerCase().contains("aquos");
    }

    public boolean isSharpDMR(String s)
    {
        if (s == null)
        {
            return false;
        } else
        {
            return isSharpDMR(getRenderDesc(s));
        }
    }

    public boolean openMediaAsync(String s, String s1, UPnP.RemoteItemDesc remoteitemdesc)
    {
        checkDLNAorThrow();
        long al[] = {
            0L
        };
        int i = DLNA.JNI_SetAVTransportURI_EX(mDLNA.mNativeUPnP, s, 0, s1, remoteitemdesc, al);
        boolean flag = false;
        if (i == 0)
        {
            flag = true;
        }
        return flag;
    }

    public boolean openMediaAsync(String s, String s1, String s2)
    {
        checkDLNAorThrow();
        long al[] = {
            0L
        };
        if (s2 != null)
        {
            int j = s2.indexOf(">");
            if (j > 1)
            {
                s2 = (new StringBuilder()).append(s2.substring(0, j - 1)).append("\" xmlns:pxn=\"urn:schemas-panasonic-com:pxn\"").append(s2.substring(j)).toString();
            }
            if (s2.contains("vli:playitemNum"))
            {
                int k = s2.indexOf("vli:playitemNum");
                int l = s2.indexOf(">", k);
                s2 = s2.replace(s2.substring(k - 1, l), "");
            }
        }
        int i = DLNA.JNI_SetAVTransportURI(mDLNA.mNativeUPnP, s, 0, s1, s2, al);
        boolean flag = false;
        if (i == 0)
        {
            flag = true;
        }
        return flag;
    }

    public boolean pauseMediaAsync(String s)
    {
        checkDLNAorThrow();
        long al[] = {
            0L
        };
        return DLNA.JNI_PauseMedia(mDLNA.mNativeUPnP, s, 0, al) == 0;
    }

    public boolean playMediaAsync(String s, PlaySpeed playspeed, String as[])
    {
        boolean flag = true;
        checkDLNAorThrow();
        long al[] = new long[flag];
        al[0] = 0L;
        if (isSharpDMR(s))
        {
            int i = DLNA.JNI_Sharp5906PlayMedia(mDLNA.mNativeUPnP, s, 0, playspeed.nativeValue, Build.MODEL, as, al);
            boolean flag1 = false;
            if (i == 0)
            {
                flag1 = flag;
            }
            return flag1;
        }
        if (DLNA.JNI_PlayMedia(mDLNA.mNativeUPnP, s, 0, playspeed.nativeValue, al) != 0)
        {
            flag = false;
        }
        return flag;
    }

    public void registerRenderPlayListener(IRenderPlayListener irenderplaylistener)
    {
        synchronized (mRenderPlayListeners)
        {
            if (!mRenderPlayListeners.contains(irenderplaylistener))
            {
                mRenderPlayListeners.add(irenderplaylistener);
            }
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void registerRenderStatusListener(IRenderStatusListener irenderstatuslistener)
    {
        synchronized (mRenderStatusListeners)
        {
            if (!mRenderStatusListeners.contains(irenderstatuslistener))
            {
                mRenderStatusListeners.add(irenderstatuslistener);
            }
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void release()
    {
        reset();
        mDLNA.unregisterDLNAStatusListener(mDLNAStatusListener);
    }

    void reset()
    {
        clearRenderCache();
    }

    public boolean seekMediaAsync(String s, long l)
    {
        checkDLNAorThrow();
        String s1 = TimeUtils.convertSecToTimeString(l / 1000L, true);
        long al[] = {
            0L
        };
        int i = DLNA.JNI_SeekMedia(mDLNA.mNativeUPnP, s, 0, SeekMode.RELTIME.nativeValue, s1, al);
        boolean flag = false;
        if (i == 0)
        {
            flag = true;
        }
        return flag;
    }

    public boolean setMuteAsync(String s, VolumeChannel volumechannel, boolean flag)
    {
        checkDLNAorThrow();
        long al[] = {
            0L
        };
        int i = DLNA.JNI_SetMute(mDLNA.mNativeUPnP, s, 0, volumechannel.nativeValue, flag, al);
        boolean flag1 = false;
        if (i == 0)
        {
            flag1 = true;
        }
        return flag1;
    }

    public boolean setVolumeAsync(String s, VolumeChannel volumechannel, int i)
    {
        checkDLNAorThrow();
        long al[] = {
            0L
        };
        int j = DLNA.JNI_SetVolume(mDLNA.mNativeUPnP, s, 0, volumechannel.nativeValue, i, al);
        boolean flag = false;
        if (j == 0)
        {
            flag = true;
        }
        return flag;
    }

    public boolean stopMediaAsync(String s, String as[], String s1)
    {
        boolean flag = true;
        checkDLNAorThrow();
        long al[] = new long[flag];
        al[0] = 0L;
        if (isSharpDMR(s))
        {
            int i = DLNA.JNI_Sharp5906StopMedia(mDLNA.mNativeUPnP, s, 0, as, s1, al);
            boolean flag1 = false;
            if (i == 0)
            {
                flag1 = flag;
            }
            return flag1;
        }
        if (DLNA.JNI_StopMedia(mDLNA.mNativeUPnP, s, 0, al) != 0)
        {
            flag = false;
        }
        return flag;
    }

    public boolean uninstallRender(String s)
    {
        checkDLNAorThrow();
        return DLNA.JNI_UninstallRender(mDLNA.mNativeUPnP, s) == 0;
    }

    public void unregisterRenderPlayListener(IRenderPlayListener irenderplaylistener)
    {
        synchronized (mRenderPlayListeners)
        {
            if (mRenderPlayListeners.contains(irenderplaylistener))
            {
                mRenderPlayListeners.remove(irenderplaylistener);
            }
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void unregisterRenderStatusListener(IRenderStatusListener irenderstatuslistener)
    {
        synchronized (mRenderStatusListeners)
        {
            if (mRenderStatusListeners.contains(irenderstatuslistener))
            {
                mRenderStatusListeners.remove(irenderstatuslistener);
            }
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }









}
