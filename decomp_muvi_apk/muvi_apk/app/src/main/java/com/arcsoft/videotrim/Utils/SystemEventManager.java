// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// Referenced classes of package com.arcsoft.videotrim.Utils:
//            SDCardManager, MediaFileObserver, UtilFunc

public class SystemEventManager
    implements Observer, SDCardManager.SDCardEventListener
{
    public static interface SystemEventListener
    {

        public abstract void OnSystemEvent(int i, Bundle bundle);
    }


    private static final String TAG = "FileEventManager";
    private Context mContext;
    private SDCardManager mSDCardManager;
    private SystemEventListener mSystemEventListener;
    private ArrayList m_ArryMediaFileObserver;
    private boolean m_bSdcardEJECT;

    public SystemEventManager(Context context)
    {
        mContext = null;
        m_ArryMediaFileObserver = null;
        mSDCardManager = null;
        m_bSdcardEJECT = false;
        mSystemEventListener = null;
        mContext = context;
        Init();
    }

    private void Init()
    {
        registerSDCardEventReceiver();
    }

    private void registerSDCardEventReceiver()
    {
        mSDCardManager = new SDCardManager();
        mSDCardManager.registerSDCardEventListener(this);
        if (mContext != null)
        {
            mContext.registerReceiver(mSDCardManager.getSDCardReceiver(), SDCardManager.getIntentFilter());
        }
        if (m_ArryMediaFileObserver == null)
        {
            m_ArryMediaFileObserver = new ArrayList();
        }
    }

    public void Destroy()
    {
        if (m_ArryMediaFileObserver != null)
        {
            for (int i = 0; i < m_ArryMediaFileObserver.size(); i++)
            {
                MediaFileObserver mediafileobserver = (MediaFileObserver)m_ArryMediaFileObserver.get(i);
                mediafileobserver.stopWatching();
                mediafileobserver.destroy();
            }

            m_ArryMediaFileObserver.clear();
            m_ArryMediaFileObserver = null;
        }
        if (mContext != null)
        {
            mContext.unregisterReceiver(mSDCardManager.getSDCardReceiver());
            mContext = null;
        }
        if (mSDCardManager != null)
        {
            mSDCardManager.destroy();
            mSDCardManager = null;
        }
        mSystemEventListener = null;
    }

    public void addMediaFileObserverPath(String s)
    {
_L2:
        return;
        if (s == null || m_ArryMediaFileObserver == null) goto _L2; else goto _L1
_L1:
        int i = 0;
label0:
        do
        {
label1:
            {
                if (i >= m_ArryMediaFileObserver.size())
                {
                    break label1;
                }
                if (((MediaFileObserver)m_ArryMediaFileObserver.get(i)).getPath().equals(s))
                {
                    break label0;
                }
                i++;
            }
        } while (true);
        if (true) goto _L2; else goto _L3
_L3:
        MediaFileObserver mediafileobserver = new MediaFileObserver(s, this);
        mediafileobserver.startWatching();
        m_ArryMediaFileObserver.add(mediafileobserver);
        return;
    }

    public void onSDCardEvent(Constants.SDCardEvent sdcardevent)
    {
        static class _cls1
        {

            static final int $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent[];

            static 
            {
                $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent = new int[Constants.SDCardEvent.values().length];
                try
                {
                    $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent[Constants.SDCardEvent.MOUNTED.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent[Constants.SDCardEvent.UNMOUNTED.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent[Constants.SDCardEvent.EJECT.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent[Constants.SDCardEvent.SCANNER_STARTED.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent[Constants.SDCardEvent.SCANNER_FINISHED.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.com.arcsoft.videotrim.Utils.Constants.SDCardEvent[sdcardevent.ordinal()];
        JVM INSTR tableswitch 1 5: default 44
    //                   1 45
    //                   2 108
    //                   3 185
    //                   4 255
    //                   5 296;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L2:
        Bundle bundle4 = new Bundle();
        bundle4.putLong("diskchange_eventid", 1L);
        bundle4.putBoolean("diskchange_about_to_remove", false);
        bundle4.putBoolean("diskchange_remove_complete", false);
        bundle4.putString("diskchange_card_name", SDCardManager.getSDCardVolumeName());
        if (mSystemEventListener != null)
        {
            mSystemEventListener.OnSystemEvent(1, bundle4);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (m_bSdcardEJECT)
        {
            Bundle bundle3 = new Bundle();
            bundle3.putLong("diskchange_eventid", 2L);
            bundle3.putBoolean("diskchange_about_to_remove", false);
            bundle3.putBoolean("diskchange_remove_complete", true);
            bundle3.putString("diskchange_card_name", SDCardManager.getSDCardVolumeName());
            if (mSystemEventListener != null)
            {
                mSystemEventListener.OnSystemEvent(1, bundle3);
            }
            m_bSdcardEJECT = false;
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
        m_bSdcardEJECT = true;
        Bundle bundle2 = new Bundle();
        bundle2.putLong("diskchange_eventid", 2L);
        bundle2.putBoolean("diskchange_about_to_remove", true);
        bundle2.putBoolean("diskchange_remove_complete", false);
        bundle2.putString("diskchange_card_name", SDCardManager.getSDCardVolumeName());
        if (mSystemEventListener != null)
        {
            mSystemEventListener.OnSystemEvent(1, bundle2);
            return;
        }
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
_L5:
        Bundle bundle1 = new Bundle();
        bundle1.putBoolean("media scanner started", true);
        bundle1.putBoolean("media scanner finished", false);
        if (mSystemEventListener != null)
        {
            mSystemEventListener.OnSystemEvent(3, bundle1);
            return;
        }
        if (true) goto _L1; else goto _L6
_L6:
        Bundle bundle = new Bundle();
        bundle.putBoolean("media scanner started", false);
        bundle.putBoolean("media scanner finished", true);
        if (mSystemEventListener != null)
        {
            mSystemEventListener.OnSystemEvent(3, bundle);
            return;
        }
        if (true) goto _L1; else goto _L7
_L7:
    }

    public void removeMediaFileObserverPath(String s)
    {
        if (s != null && m_ArryMediaFileObserver != null)
        {
            int i = 0;
            while (i < m_ArryMediaFileObserver.size()) 
            {
                MediaFileObserver mediafileobserver = (MediaFileObserver)m_ArryMediaFileObserver.get(i);
                if (mediafileobserver.getPath().equals(s))
                {
                    mediafileobserver.stopWatching();
                    mediafileobserver.destroy();
                    m_ArryMediaFileObserver.remove(i);
                    return;
                }
                i++;
            }
        }
    }

    public void setFileEventListener(SystemEventListener systemeventlistener)
    {
        mSystemEventListener = systemeventlistener;
    }

    public void update(Observable observable, Object obj)
    {
        UtilFunc.Log("FileEventManager", (new StringBuilder()).append("update--->data: ").append(obj).toString());
        if ((observable instanceof MediaFileObserver.MediaFileObserverProxy) && obj != null && (obj instanceof Bundle))
        {
            Bundle bundle = (Bundle)obj;
            if (mSystemEventListener != null)
            {
                mSystemEventListener.OnSystemEvent(2, bundle);
            }
        }
    }
}
