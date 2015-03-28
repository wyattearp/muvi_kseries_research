// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.videotrim.Utils.AppContext;
import com.arcsoft.videotrim.Utils.RuntimeConfig;
import com.arcsoft.videotrim.Utils.SystemEventManager;
import com.arcsoft.videotrim.Utils.UtilFunc;
import java.io.File;
import powermobia.videoeditor.MEngine;
import powermobia.videoeditor.base.ISessionStateListener;
import powermobia.videoeditor.base.MRange;
import powermobia.videoeditor.base.MSessionState;
import powermobia.videoeditor.base.MSessionStream;
import powermobia.videoeditor.base.MVideoInfo;
import powermobia.videoeditor.clip.MClip;
import powermobia.videoeditor.clip.MMediaSource;
import powermobia.videoeditor.producer.MProducer;
import powermobia.videoeditor.producer.MProducerProperty;
import powermobia.videoeditor.storyboard.MStoryboard;

// Referenced classes of package com.arcsoft.videotrim:
//            VideoClip

public class TrimSaveOper
    implements ISessionStateListener, com.arcsoft.videotrim.Utils.SystemEventManager.SystemEventListener
{
    private class FileObserverEventHandler extends Handler
    {

        final TrimSaveOper this$0;

        public void handleMessage(Message message)
        {
            Bundle bundle = message.getData();
            if (bundle != null)
            {
                long l = bundle.getLong("filechange_eventid");
                String s = bundle.getString("filechange_item_name");
                if (1L == l || 2L == l)
                {
                    UtilFunc.Log("VideoSplite", (new StringBuilder()).append("FileObserverEventHandler--- path: ").append(s).append(", m_bFileChangeNotifeid: ").append(m_bFileChangeNotifeid).toString());
                    if (m_Storyboard != null && !m_bFileChangeNotifeid)
                    {
                        m_bFileChangeNotifeid = true;
                        onMessage(5);
                        return;
                    }
                }
            }
        }

        public FileObserverEventHandler(Looper looper)
        {
            this$0 = TrimSaveOper.this;
            super(looper);
        }
    }

    public static interface OnSaveTrimListener
    {

        public abstract void OnTrimResult(int i);
    }

    private class SaveEventHandler extends Handler
    {

        final TrimSaveOper this$0;

        public void handleMessage(Message message)
        {
            switch (message.what)
            {
            case 1: // '\001'
            case 2: // '\002'
            case 4: // '\004'
            default:
                return;

            case 3: // '\003'
                UtilFunc.RenameFile(mStrTempFile, mStrDstFile);
                onMessage(0);
                return;

            case 5: // '\005'
                releaseProducer();
                UtilFunc.DeleteFileByName(mStrTempFile);
                onMessage(7);
                mStrDstFile = null;
                return;

            case 6: // '\006'
                onMessage(3);
                break;
            }
            UtilFunc.DeleteFileByName(mStrTempFile);
            mStrDstFile = null;
        }

        public SaveEventHandler(Looper looper)
        {
            this$0 = TrimSaveOper.this;
            super(looper);
        }
    }


    private static final String DEFAULT_OUTPUT_DIR = "/sdcard/videos/";
    private static final String DEFAULT_OUTPUT_FILETYPE = ".mp4";
    public static final int MSG_EXPORT_CANCEL = 7;
    public static final int MSG_EXPORT_ERROR = 2;
    public static final int MSG_EXPORT_SUCCESS = 0;
    public static final int MSG_FILSIZE_EXCEEDED_ERROR = 3;
    public static final int MSG_INVALID_VIDEO_FILE = 4;
    public static final int MSG_NO_SOURCE_FILE_ERROR = 5;
    private static final int MSG_PRODUCER_CANCEL = 5;
    private static final int MSG_PRODUCER_CREATE = 1;
    private static final int MSG_PRODUCER_FILESIZE_ERROR = 6;
    private static final int MSG_PRODUCER_READY = 2;
    private static final int MSG_PRODUCER_RUNNING = 4;
    private static final int MSG_PRODUCER_STOPPED = 3;
    public static final int MSG_SDCARD_CHANGED = 6;
    public static final int MSG_UNKNOW_ERROR = 1;
    private static final long MVE_SAVE_MIN_SPACE = 0x7d000L;
    private static final int STR_DESNAME__END_FIRST = 1;
    private final String TAG = "VideoSplite";
    private int mCallbackReturn;
    private Context mContext;
    private int mCurPercent;
    private String mStrDstFile;
    private String mStrSrcFileName;
    private String mStrSrcPathName;
    private String mStrTempFile;
    private VideoClip mTrimClip;
    private OnSaveTrimListener mTrimListener;
    private FileObserverEventHandler m_FileObserverEventHandler;
    private MProducer m_Producer;
    private SaveEventHandler m_SaveEventHandler;
    private MStoryboard m_Storyboard;
    private MSessionStream m_Stream;
    private SystemEventManager m_SystemEventManager;
    private boolean m_bFileChangeNotifeid;
    private boolean m_bSDCardStateChanged;
    private boolean m_bShouldCancel;
    private String m_strOutputPath;
    private boolean mbEnoughSpaceForLimitedSize;

    public TrimSaveOper(VideoClip videoclip, Context context)
    {
        mContext = null;
        mStrSrcPathName = null;
        mStrDstFile = null;
        mStrSrcFileName = null;
        mStrTempFile = null;
        m_Storyboard = null;
        m_Producer = null;
        m_Stream = null;
        m_strOutputPath = null;
        m_SaveEventHandler = null;
        m_FileObserverEventHandler = null;
        m_SystemEventManager = null;
        m_bShouldCancel = false;
        mbEnoughSpaceForLimitedSize = false;
        m_bFileChangeNotifeid = false;
        m_bSDCardStateChanged = false;
        mTrimClip = null;
        mCurPercent = 0;
        mTrimListener = null;
        mCallbackReturn = 0;
        mContext = context;
        mTrimClip = videoclip;
        m_SystemEventManager = new SystemEventManager(context);
        Init();
    }

    private int CreateSourceStream()
    {
        int i = RuntimeConfig.OUTPUT_RESOL_WIDTH;
        int j = RuntimeConfig.OUTPUT_RESOL_HEIGHT;
        if (i == 0 && j == 0)
        {
            MClip mclip = m_Storyboard.getDataClip();
            if (mclip != null)
            {
                MVideoInfo mvideoinfo = (MVideoInfo)mclip.getProperty(12291);
                if (mvideoinfo != null)
                {
                    i = mvideoinfo.get(3);
                    j = mvideoinfo.get(4);
                }
            }
        }
        UtilFunc.Log("VideoSplite", (new StringBuilder()).append("CreateSourceStream--->width: ").append(i).append(", height: ").append(j).toString());
        int k = m_Storyboard.getDuration();
        UtilFunc.Log("VideoSplite", (new StringBuilder()).append("CreateSourceStream--->iDuration: ").append(k).toString());
        MRange mrange = new MRange(0, k);
        m_Stream = new MSessionStream();
        int l = m_Stream.open(1, m_Storyboard, i, j, mrange, null, null, 2);
        if (l != 0)
        {
            return l;
        } else
        {
            return 0;
        }
    }

    private void Init()
    {
        if (mTrimClip == null)
        {
            return;
        }
        mStrSrcPathName = mTrimClip.mStrFile;
        if (mStrSrcPathName == null || mStrSrcPathName.length() <= 0)
        {
            m_strOutputPath = "/sdcard/videos/";
            return;
        } else
        {
            m_strOutputPath = mStrSrcPathName.substring(0, 1 + mStrSrcPathName.lastIndexOf("/"));
            mStrSrcFileName = mStrSrcPathName.substring(1 + mStrSrcPathName.lastIndexOf("/"), mStrSrcPathName.length());
            return;
        }
    }

    private int PreSave()
    {
        if (!UtilFunc.CreateMultilevelDirectory(m_strOutputPath))
        {
            onMessage(1);
            return 1;
        }
        if (!setDesFilename())
        {
            onMessage(1);
            return 1;
        }
        MEngine mengine = AppContext.getAppContext().getVEEngine();
        if (mengine != null)
        {
            mengine.setProperty(1, OEMConfig.TEMP_BASE_PATH);
        }
        return 0;
    }

    private int StartProducer()
    {
        MEngine mengine = AppContext.getAppContext().getVEEngine();
        UtilFunc.Log("VideoSplite", (new StringBuilder()).append("StartProducer--->amveEngine: ").append(mengine).toString());
        long l = RuntimeConfig.FILESIZE_LIMIT;
        long l1 = UtilFunc.GetFreeSpace(m_strOutputPath);
        if (l1 <= 0x7d000L)
        {
            onMessage(3);
            return 0x80001;
        }
        long l2;
        if (0L == l)
        {
            l2 = l1 - 0x7d000L;
        } else
        if (l1 - 0x7d000L > l)
        {
            l2 = l;
        } else
        {
            l2 = l1 - 0x7d000L;
        }
        if (CreateSourceStream() != 0)
        {
            onMessage(1);
            return 0x80001;
        }
        m_Producer = new MProducer();
        int i = m_Producer.init(mengine, this);
        UtilFunc.Log("VideoSplite", (new StringBuilder()).append("m_Producer.init() result = ").append(i).toString());
        if (i != 0)
        {
            m_Producer = null;
            onMessage(1);
            return i;
        }
        int j = m_Producer.setStream(m_Stream);
        UtilFunc.Log("VideoSplite", (new StringBuilder()).append("m_Producer.setStream() result = ").append(j).toString());
        if (j != 0)
        {
            m_Producer.unInit();
            m_Producer = null;
            onMessage(1);
            return j;
        }
        int k = RuntimeConfig.FILE_FORMAT;
        int i1 = RuntimeConfig.VIDEOFORMAT;
        int j1 = RuntimeConfig.AUDIOFORMAT;
        int k1 = RuntimeConfig.OUTPUT_RESOL_WIDTH;
        int i2 = RuntimeConfig.OUTPUT_RESOL_HEIGHT;
        if (k1 == 0 && i2 == 0)
        {
            MClip mclip = m_Storyboard.getDataClip();
            if (mclip != null)
            {
                MVideoInfo mvideoinfo = (MVideoInfo)mclip.getProperty(12291);
                if (mvideoinfo != null)
                {
                    k1 = mvideoinfo.get(3);
                    i2 = mvideoinfo.get(4);
                }
            }
        }
        long l3 = UtilFunc.GetOutputBitrate(k1, i2);
        UtilFunc.Log("VideoSplite", (new StringBuilder()).append("startProducer--->iFrameWidth: ").append(k1).append(", iFrameHeight: ").append(i2).append(", iFileFormat: ").append(k).append(", iVideoFormat: ").append(i1).append(", iAudioFormat: ").append(j1).append(", lVideoBitrate: ").append(l3).append(", mStrTempFile: ").append(mStrTempFile).toString());
        MProducerProperty mproducerproperty = new MProducerProperty(k, i1, j1, 30000, (int)l3, l2, mStrTempFile);
        int j2 = m_Producer.setProperty(24577, mproducerproperty);
        UtilFunc.Log("VideoSplite", (new StringBuilder()).append("m_Producer.setProperty() result = ").append(j2).toString());
        if (j2 != 0)
        {
            m_Producer.unInit();
            m_Producer = null;
            onMessage(1);
            return j2;
        } else
        {
            UtilFunc.Log("VideoSplite", "before m_Producer.start()");
            int k2 = m_Producer.start();
            UtilFunc.Log("VideoSplite", (new StringBuilder()).append("m_Producer.start() result = ").append(k2).toString());
            return 0;
        }
    }

    private void onMessage(int i)
    {
        if (mTrimListener != null)
        {
            mTrimListener.OnTrimResult(i);
        }
    }

    private void releaseProducer()
    {
        if (m_Producer != null)
        {
            m_Producer.unInit();
            m_Producer = null;
        }
        if (m_Stream != null)
        {
            m_Stream.close();
            m_Stream = null;
        }
        if (m_Storyboard != null)
        {
            m_Storyboard.unInit();
            m_Storyboard = null;
        }
    }

    private boolean setDesFilename()
    {
        if (mStrSrcFileName == null)
        {
            return false;
        }
        String as[] = mStrSrcFileName.split("\\.");
        String s = as[0];
        String s1 = as[-1 + as.length];
        if (s1 == null || s1.length() <= 0)
        {
            s1 = ".mp4";
        }
        String s2 = (new StringBuilder()).append(m_strOutputPath).append(s).append("_").toString();
        int i;
        for (i = 1; (new File((new StringBuilder()).append(s2).append(i).append(".").append(s1).toString())).exists(); i++) { }
        mStrDstFile = (new StringBuilder()).append(s2).append(i).append(".").append(s1).toString();
        mStrTempFile = (new StringBuilder()).append(OEMConfig.TEMP_BASE_PATH).append("mve_tmp_file_arcsoft_mgl_888").toString();
        UtilFunc.Log("VideoSplite", (new StringBuilder()).append("m_strOutputPath: ").append(m_strOutputPath).append(", mStrDstFile: ").append(mStrDstFile).append(", mStrTempFile = ").append(mStrTempFile).toString());
        return true;
    }

    private int videoSave()
    {
        if (m_strOutputPath == null || m_strOutputPath.length() <= 0)
        {
            m_strOutputPath = "/sdcard/videos/";
        }
        for (int i = PreSave(); i != 0 || StartProducer() != 0;)
        {
            return i;
        }

        return 0;
    }

    private int videoTrim()
    {
        if (m_Storyboard == null)
        {
            m_Storyboard = new MStoryboard();
        }
        MEngine mengine = AppContext.getAppContext().getVEEngine();
        int i = m_Storyboard.init(mengine, null);
        if (i != 0)
        {
            onMessage(1);
            return i;
        }
        boolean flag;
        if (mTrimClip.isValidVideoPath())
        {
            if (mTrimClip.mStrFile == null || mTrimClip.mStrFile.length() <= 0 || mTrimClip.mStartPos < 0 || mTrimClip.mDuration <= 0)
            {
                return 2;
            }
            MClip mclip = new MClip();
            mclip.init(mengine, new MMediaSource(0, false, mTrimClip.mStrFile));
            mclip.setProperty(12292, new MRange(mTrimClip.mStartPos, mTrimClip.mDuration));
            int j = m_Storyboard.insertClip(mclip, 0);
            flag = false;
            if (j == 0)
            {
                if (m_SystemEventManager != null)
                {
                    m_SystemEventManager.addMediaFileObserverPath(mTrimClip.mStrFile);
                }
                int _tmp = 0 + 1;
            }
        } else
        {
            flag = true;
        }
        if (flag)
        {
            onMessage(4);
            return 1;
        } else
        {
            return 0;
        }
    }

    public void Cancel()
    {
        if (m_Producer == null)
        {
            return;
        } else
        {
            m_bShouldCancel = true;
            mCallbackReturn = 0x801f7;
            return;
        }
    }

    public void OnSystemEvent(int i, Bundle bundle)
    {
        if (i != 2) goto _L2; else goto _L1
_L1:
        if (m_FileObserverEventHandler != null) goto _L4; else goto _L3
_L3:
        return;
_L4:
        long l;
        String s;
        Message message = m_FileObserverEventHandler.obtainMessage();
        message.setData(bundle);
        m_FileObserverEventHandler.sendMessage(message);
        l = bundle.getLong("filechange_eventid");
        s = bundle.getString("filechange_item_name");
        if (l != 1L && l != 2L) goto _L3; else goto _L5
_L5:
        m_SystemEventManager.removeMediaFileObserverPath(s);
        return;
_L2:
        if (i != 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (m_bSDCardStateChanged) goto _L3; else goto _L6
_L6:
        m_bSDCardStateChanged = true;
        onMessage(6);
        return;
        if (i != 3) goto _L3; else goto _L7
_L7:
    }

    public void StartSplite()
    {
        m_bShouldCancel = false;
        mCurPercent = 0;
        if (AppContext.getAppContext().getVEEngine() == null)
        {
            onMessage(1);
        } else
        {
            m_SaveEventHandler = new SaveEventHandler(Looper.myLooper());
            if (m_SystemEventManager != null)
            {
                m_SystemEventManager.setFileEventListener(this);
                m_FileObserverEventHandler = new FileObserverEventHandler(Looper.myLooper());
            }
            if (mTrimClip != null && videoTrim() == 0)
            {
                videoSave();
                return;
            }
        }
    }

    public void destroy()
    {
        mTrimListener = null;
        if (m_SystemEventManager != null)
        {
            m_SystemEventManager.Destroy();
            m_SystemEventManager = null;
        }
        releaseProducer();
        mTrimClip = null;
    }

    public int getCurrentPercent()
    {
        return mCurPercent;
    }

    public String getDstFileName()
    {
        return mStrDstFile;
    }

    public int onSessionStatus(MSessionState msessionstate)
    {
        UtilFunc.Log("VideoSplite", (new StringBuilder()).append("onSessionStatus--->status.getStatus(): ").append(msessionstate.getStatus()).append(", status.getErrorCode(): ").append(msessionstate.getErrorCode()).toString());
        if (!m_bFileChangeNotifeid && !m_bSDCardStateChanged) goto _L2; else goto _L1
_L1:
        return 0x801f7;
_L2:
        if (msessionstate == null || msessionstate.getDuration() == 0)
        {
            return 0x80003;
        }
        if (msessionstate.getErrorCode() != 0 && 0x80191 != msessionstate.getErrorCode())
        {
            Message message2 = new Message();
            message2.what = 5;
            m_SaveEventHandler.sendMessage(message2);
            return 0x801f7;
        }
        msessionstate.getStatus();
        JVM INSTR tableswitch 1 4: default 156
    //                   1 156
    //                   2 161
    //                   3 156
    //                   4 323;
           goto _L3 _L3 _L4 _L3 _L5
_L3:
        return mCallbackReturn;
_L4:
        if (m_bShouldCancel) goto _L1; else goto _L6
_L6:
        if (0x80191 == msessionstate.getErrorCode())
        {
            mbEnoughSpaceForLimitedSize = true;
            Message message1 = new Message();
            message1.what = 6;
            m_SaveEventHandler.sendMessage(message1);
            return 0x80191;
        }
        MStoryboard mstoryboard = m_Storyboard;
        int i = 0;
        if (mstoryboard != null)
        {
            i = m_Storyboard.getDuration();
        }
        if (i <= 0)
        {
            mCurPercent = 0;
        } else
        {
            mCurPercent = (100 * msessionstate.getCurrentTime()) / i;
        }
        UtilFunc.Log("VideoSplite", (new StringBuilder()).append("mCurPercent: ").append(mCurPercent).append(" = ").append(msessionstate.getCurrentTime()).append("/").append(i).append("*100").toString());
          goto _L3
_L5:
        if (m_bShouldCancel)
        {
            Message message = new Message();
            message.what = 5;
            m_SaveEventHandler.sendMessage(message);
            return 0x801f7;
        }
        if (!mbEnoughSpaceForLimitedSize)
        {
            m_SaveEventHandler.sendEmptyMessage(3);
        }
          goto _L3
    }

    public void setOnSaveTrimListener(OnSaveTrimListener onsavetrimlistener)
    {
        mTrimListener = onsavetrimlistener;
    }

    public void setSourceClips(VideoClip videoclip)
    {
        mTrimClip = videoclip;
    }



/*
    static boolean access$002(TrimSaveOper trimsaveoper, boolean flag)
    {
        trimsaveoper.m_bFileChangeNotifeid = flag;
        return flag;
    }

*/






/*
    static String access$402(TrimSaveOper trimsaveoper, String s)
    {
        trimsaveoper.mStrDstFile = s;
        return s;
    }

*/

}
