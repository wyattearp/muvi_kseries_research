// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.PowerManager;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.arcsoft.MediaPlayer.audiofx.Equalizer;
import com.arcsoft.MediaPlayer.audiofx.StereoWidening;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.arcsoft.MediaPlayer:
//            IAudioSink, MPTimer

public class ArcMediaPlayer extends MediaPlayer
{
    class ARCTimer extends MPTimer
    {

        final ArcMediaPlayer this$0;

        public void TimerCallback(int i, int j)
        {
            ARCTimerCallback(i, j);
        }

        ARCTimer()
        {
            this$0 = ArcMediaPlayer.this;
            super();
        }
    }

    private class EventHandler extends Handler
    {

        private ArcMediaPlayer mArcMediaPlayer;
        final ArcMediaPlayer this$0;

        public void handleMessage(Message message)
        {
            if (mArcMediaPlayer.mNativeContext != 0) goto _L2; else goto _L1
_L1:
            Log.w("ArcMediaPlayer", "ArcMediaPlayer went away with unhandled events");
_L4:
            return;
_L2:
            switch (message.what)
            {
            default:
                Log.e("ArcMediaPlayer", (new StringBuilder("Unknown message type ")).append(message.what).toString());
                return;

            case 1000: 
                continue; /* Loop/switch isn't completed */

            case 1: // '\001'
                if (mOnPreparedListener != null)
                {
                    mOnPreparedListener.onPrepared(mArcMediaPlayer);
                    return;
                }
                break;

            case 2: // '\002'
                if (mOnCompletionListener != null)
                {
                    mOnCompletionListener.onCompletion(mArcMediaPlayer);
                }
                stayAwake(false);
                return;

            case 3: // '\003'
                if (mOnBufferingUpdateListener != null)
                {
                    mOnBufferingUpdateListener.onBufferingUpdate(mArcMediaPlayer, message.arg1);
                    return;
                }
                break;

            case 4: // '\004'
                if (mOnSeekCompleteListener != null)
                {
                    mOnSeekCompleteListener.onSeekComplete(mArcMediaPlayer);
                    return;
                }
                break;

            case 5: // '\005'
                if (mOnVideoSizeChangedListener != null)
                {
                    mOnVideoSizeChangedListener.onVideoSizeChanged(mArcMediaPlayer, message.arg1, message.arg2);
                    return;
                }
                break;

            case 100: // 'd'
                Log.e("ArcMediaPlayer", (new StringBuilder("Error (")).append(message.arg1).append(",").append(message.arg2).append(")").toString());
                android.media.MediaPlayer.OnErrorListener onerrorlistener = mOnErrorListener;
                boolean flag = false;
                if (onerrorlistener != null)
                {
                    flag = mOnErrorListener.onError(mArcMediaPlayer, message.arg1, message.arg2);
                }
                if (mOnCompletionListener != null && !flag)
                {
                    mOnCompletionListener.onCompletion(mArcMediaPlayer);
                }
                stayAwake(false);
                return;

            case 200: 
                Log.i("ArcMediaPlayer", (new StringBuilder("Info (")).append(message.arg1).append(",").append(message.arg2).append(")").toString());
                if (mOnInfoListener != null)
                {
                    mOnInfoListener.onInfo(mArcMediaPlayer, message.arg1, message.arg2);
                    return;
                }
                break;

            case 0: // '\0'
                break;
            }
            continue; /* Loop/switch isn't completed */
            if (mOnMessageListener == null) goto _L4; else goto _L3
_L3:
            mOnMessageListener.onMessage(mArcMediaPlayer, message.arg1, message.arg2);
            return;
            if (true) goto _L4; else goto _L5
_L5:
        }

        public EventHandler(ArcMediaPlayer arcmediaplayer1, Looper looper)
        {
            this$0 = ArcMediaPlayer.this;
            super(looper);
            mArcMediaPlayer = arcmediaplayer1;
        }
    }

    public static interface onMessageListener
    {

        public static final int MESSAGE_INFO_ACODEC_DECODE_ERROR = 12293;
        public static final int MESSAGE_INFO_ACODEC_UNSUPPORTAUDIO = 20492;
        public static final int MESSAGE_INFO_AUDIOOUTPUT_FAIL = 28723;
        public static final int MESSAGE_INFO_AVCODEC_UNSUPPORT = 32773;
        public static final int MESSAGE_INFO_DISPLAY_FAIL = 28673;
        public static final int MESSAGE_INFO_HDCP_AKE_FAILED = 32778;
        public static final int MESSAGE_INFO_HDCP_AKE_SUCC = 32777;
        public static final int MESSAGE_INFO_NOAUDIO_UPSUPPORTVIDEO = 32771;
        public static final int MESSAGE_INFO_NOVIDEO_UPSUPPORTAUDIO = 32772;
        public static final int MESSAGE_INFO_RTP_PORT_READY = 32779;
        public static final int MESSAGE_INFO_SPLITTER_AUDIO_END = 12290;
        public static final int MESSAGE_INFO_SPLITTER_HTTP_NETWORK = 18;
        public static final int MESSAGE_INFO_SPLITTER_NOAUDIO = 32770;
        public static final int MESSAGE_INFO_SPLITTER_NOVIDEO = 32769;
        public static final int MESSAGE_INFO_SPLITTER_UNSUPPORT_FORMAT = 16390;
        public static final int MESSAGE_INFO_SPLITTER_VIDEO_END = 12289;
        public static final int MESSAGE_INFO_UNKNOWN = 65535;
        public static final int MESSAGE_INFO_VCODEC_DECODE_ERROR = 12297;
        public static final int MESSAGE_INFO_VCODEC_UNSUPPORTVIDEO = 20491;
        public static final int MESSAGE_LEVEL_ERROR = 259;
        public static final int MESSAGE_LEVEL_NONE = 257;
        public static final int MESSAGE_LEVEL_USERDEFINED = 260;
        public static final int MESSAGE_LEVEL_WARNING = 258;
        public static final int MV2_MESSAGE_INFO_SPLITTER_SEEK_UNAVAILABLE = 32774;
        public static final int MV2_MESSAGE_INFO_VCODEC_HW2SW = 32775;

        public abstract boolean onMessage(MediaPlayer mediaplayer, int i, int j);
    }


    public static final int ANDROID_CPU_ARM_FEATURE_ARMv7 = 1;
    public static final int ANDROID_CPU_ARM_FEATURE_NEON = 4;
    public static final int ANDROID_CPU_ARM_FEATURE_VFPv3 = 2;
    public static final int ANDROID_CPU_FAMILY_ARM = 1;
    public static final int ANDROID_CPU_FAMILY_UNKNOWN = 0;
    public static final int ANDROID_CPU_FAMILY_X86 = 2;
    public static final int ANDROID_CPU_TYPE_C110 = 8;
    public static final int ANDROID_CPU_TYPE_C210 = 9;
    public static final int ANDROID_CPU_TYPE_MSM7225 = 2;
    public static final int ANDROID_CPU_TYPE_MSM7227 = 1;
    public static final int ANDROID_CPU_TYPE_MSM8250 = 3;
    public static final int ANDROID_CPU_TYPE_MSM8255 = 4;
    public static final int ANDROID_CPU_TYPE_MSM8260 = 5;
    public static final int ANDROID_CPU_TYPE_OMAP4 = 7;
    public static final int ANDROID_CPU_TYPE_TEGRA2 = 6;
    public static final int ANDROID_CPU_TYPE_UNKNOW = 0;
    public static final int ANDROID_DISPLAY_CHOOSE_AUTO = 1;
    public static final int ANDROID_DISPLAY_CHOOSE_SETED = 0;
    public static final int ANDROID_DISPLAY_SURFACETYPE_NORMAL = 0;
    public static final int ANDROID_DISPLAY_SURFACETYPE_PUSHBUFFER = 1;
    public static final int BENCHMARK_LEVEL_FULL = 2;
    public static final int BENCHMARK_LEVEL_NONE = 0;
    public static final int BENCHMARK_LEVEL_SIMPLE = 1;
    private static final String IMEDIA_PLAYER = "android.media.IArcMediaPlayer";
    public static final int MEDIA_ASPECT_RATIO_100_221 = 3;
    public static final int MEDIA_ASPECT_RATIO_16_9 = 2;
    public static final int MEDIA_ASPECT_RATIO_4_3 = 1;
    public static final int MEDIA_ASPECT_RATIO_UNKNOWN = 0;
    private static final int MEDIA_BUFFERING_UPDATE = 3;
    private static final int MEDIA_ERROR = 100;
    public static final int MEDIA_ERROR_CODEC_UNSUPPORT = 300;
    public static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 200;
    public static final int MEDIA_ERROR_SERVER_DIED = 100;
    public static final int MEDIA_ERROR_UNKNOWN = 1;
    private static final int MEDIA_INFO = 200;
    public static final int MEDIA_INFO_BAD_INTERLEAVING = 800;
    public static final int MEDIA_INFO_BUFFERING_END = 702;
    public static final int MEDIA_INFO_BUFFERING_START = 701;
    public static final int MEDIA_INFO_METADATA_UPDATE = 802;
    public static final int MEDIA_INFO_NOT_SEEKABLE = 801;
    public static final int MEDIA_INFO_RENDERING_START = 900;
    public static final int MEDIA_INFO_UNKNOWN = 1;
    public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
    private static final int MEDIA_MESSAGE = 1000;
    private static final int MEDIA_NOP = 0;
    private static final int MEDIA_PLAYBACK_COMPLETE = 2;
    public static int MEDIA_PLAYER_AUDIO_LEFT_ONLY = 0;
    public static int MEDIA_PLAYER_AUDIO_RIGHT_ONLY = 0;
    public static int MEDIA_PLAYER_AUDIO_STEREO = 0;
    public static int MEDIA_PLAYER_MODE_BACKWARD = 0;
    public static int MEDIA_PLAYER_MODE_FORWARD = 0;
    public static int MEDIA_PLAYER_MODE_NORMAL = 0;
    public static final int MEDIA_PLAYER_PARAM_BANDWIDTH = 2001;
    public static final int MEDIA_PLAYER_PARAM_BASE = 2000;
    public static final int MEDIA_PLAYER_PARAM_OVERPLATFORM_ANDROID_CPUFAMILY = 3004;
    public static final int MEDIA_PLAYER_PARAM_OVERPLATFORM_ANDROID_CPUFEATURE = 3003;
    public static final int MEDIA_PLAYER_PARAM_OVERPLATFORM_ANDROID_CPUTYPE = 3002;
    public static final int MEDIA_PLAYER_PARAM_OVERPLATFORM_ANDROID_DISPLAY_CHOOSE = 3006;
    public static final int MEDIA_PLAYER_PARAM_OVERPLATFORM_ANDROID_DISSUR_TYPE = 3005;
    public static final int MEDIA_PLAYER_PARAM_OVERPLATFORM_ANDROID_VERSION = 3001;
    public static final int MEDIA_PLAYER_PARAM_OVERPLATFORM_BASE = 3000;
    public static final int MEDIA_PLAYER_PARAM_RTSP_DEBUG_INFO = 3007;
    private static final int MEDIA_PREPARED = 1;
    private static final int MEDIA_SEEK_COMPLETE = 4;
    private static final int MEDIA_SET_VIDEO_SIZE = 5;
    private static final String TAG = "ArcMediaPlayer";
    private static final int mNativeMethodCount = 55;
    private IAudioSink mAudioSink;
    private Equalizer mEqualizer;
    private EventHandler mEventHandler;
    private int mListenerContext;
    private int mNativeContext;
    private int mNativeJObjectSurface;
    private android.media.MediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener;
    private android.media.MediaPlayer.OnCompletionListener mOnCompletionListener;
    private android.media.MediaPlayer.OnErrorListener mOnErrorListener;
    private android.media.MediaPlayer.OnInfoListener mOnInfoListener;
    public onMessageListener mOnMessageListener;
    private android.media.MediaPlayer.OnPreparedListener mOnPreparedListener;
    private android.media.MediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener;
    private android.media.MediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangedListener;
    private boolean mScreenOnWhilePlaying;
    private boolean mStayAwake;
    private StereoWidening mStereoWidening;
    private Surface mSurface;
    private SurfaceHolder mSurfaceHolder;
    private android.os.PowerManager.WakeLock mWakeLock;
    private ArrayList m_TimerManager;

    public ArcMediaPlayer()
    {
        m_TimerManager = new ArrayList();
        mWakeLock = null;
        mAudioSink = null;
        mNativeJObjectSurface = 0;
        mOnMessageListener = null;
        mEqualizer = null;
        mStereoWidening = null;
        Looper looper = Looper.myLooper();
        if (looper != null)
        {
            mEventHandler = new EventHandler(this, looper);
        } else
        {
            Looper looper1 = Looper.getMainLooper();
            if (looper1 != null)
            {
                mEventHandler = new EventHandler(this, looper1);
            } else
            {
                mEventHandler = null;
            }
        }
        Log.d("ArcMediaPlayer", "call native_setup");
        native_setup(new WeakReference(this));
    }

    private native int ARCTimerCallback(int i, int j);

    private int ARCTimerCancel(ARCTimer arctimer)
    {
        m_TimerManager.remove(arctimer);
        return arctimer.TimerCancel();
    }

    private ARCTimer ARCTimerCreate()
    {
        ARCTimer arctimer = new ARCTimer();
        m_TimerManager.add(arctimer);
        return arctimer;
    }

    private int ARCTimerDestroy(ARCTimer arctimer)
    {
        return 0;
    }

    private void ARCTimerManagerPauseAll()
    {
        if (m_TimerManager == null || m_TimerManager.isEmpty()) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = m_TimerManager.size();
        j = 0;
_L5:
        if (j < i) goto _L3; else goto _L2
_L2:
        return;
_L3:
        ARCTimer arctimer = (ARCTimer)m_TimerManager.get(j);
        if (arctimer != null)
        {
            arctimer.removeMessages(10);
            arctimer.sendEmptyMessage(11);
        }
        j++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    private void ARCTimerManagerResumeAll()
    {
        if (m_TimerManager == null || m_TimerManager.isEmpty()) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = m_TimerManager.size();
        j = 0;
_L5:
        if (j < i) goto _L3; else goto _L2
_L2:
        return;
_L3:
        ARCTimer arctimer = (ARCTimer)m_TimerManager.get(j);
        if (arctimer != null)
        {
            arctimer.sendEmptyMessage(12);
        }
        j++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    private int ARCTimerSet(ARCTimer arctimer, int i, int j, int k)
    {
        int l;
        try
        {
            l = arctimer.TimerSet(i, j, k);
        }
        catch (Exception exception)
        {
            return 0;
        }
        return l;
    }

    private int ARCTimerSetEx(ARCTimer arctimer, int i, boolean flag, int j, int k)
    {
        return arctimer.TimerSetEx(i, flag, j, k);
    }

    private native void _captureFrame(int i, byte abyte0[]);

    private native int _getAspectRatio();

    private native int _getAudioEffectParam(int i, int j, int ai[], int k, byte abyte0[]);

    private native int _getAudioTrackNum();

    private native int _getCurrentAudioTrackIndex();

    private native int _getCurrentBufferingPercent();

    private native int _getCurrentPosition();

    private native int _getDuration();

    private native void _getParam(int i, int ai[]);

    private native int _getPcmData(short aword0[], int i);

    private native double _getRate();

    private native int _getVideoHeight();

    private native int _getVideoWidth();

    private native boolean _isHardware();

    private native boolean _isLooping();

    private native boolean _isMultiLanguage();

    private native boolean _isPlaying();

    private native void _pause()
        throws IllegalStateException;

    private native void _prepare()
        throws IOException, IllegalStateException;

    private native void _prepareAsync()
        throws IllegalStateException;

    private native void _release();

    private native void _reset();

    private native void _seekTo(int i)
        throws IllegalStateException;

    private native void _seekToSyncFrame(int i);

    private native void _selectAudioChannel(int i);

    private native void _set3DDisplayMode(boolean flag);

    private native void _set3DPupilDist(long l);

    private native void _setASMEOptions(int i, int j, int k, int l);

    private native int _setAudioEffectParam(int i, int j, int ai[]);

    private native void _setAudioSink();

    private native void _setAudioStreamType(int i);

    private native void _setBenchmark(int i);

    private native void _setConfig(int i, int j);

    private native void _setConfigFile(String s);

    private native void _setCurrentAudioTrackIndex(int i);

    private native void _setDataSource(String s)
        throws IOException, IllegalArgumentException, IllegalStateException;

    private native void _setDataSource(String s, Map map)
        throws IOException, IllegalArgumentException, IllegalStateException;

    private native void _setHardwareMode(boolean flag);

    private native void _setLooping(boolean flag);

    private native void _setMode(int i, double d);

    private native void _setParam(int i, int j);

    private native void _setVideoSurface();

    private native void _setViewRect(int i, int j, int k, int l);

    private native void _setVolume(float f, float f1);

    private static native int _snoop(short aword0[], int i);

    private native void _start()
        throws IllegalStateException;

    private native void _stop()
        throws IllegalStateException;

    public static volatile MediaPlayer create(Context context, int i)
    {
        return create(context, i);
    }

    public static volatile MediaPlayer create(Context context, Uri uri)
    {
        return create(context, uri);
    }

    public static volatile MediaPlayer create(Context context, Uri uri, SurfaceHolder surfaceholder)
    {
        return create(context, uri, surfaceholder);
    }

    public static ArcMediaPlayer create(Context context, int i)
    {
        AssetFileDescriptor assetfiledescriptor = context.getResources().openRawResourceFd(i);
        if (assetfiledescriptor == null)
        {
            return null;
        }
        ArcMediaPlayer arcmediaplayer;
        arcmediaplayer = new ArcMediaPlayer();
        arcmediaplayer.setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getLength());
        assetfiledescriptor.close();
        arcmediaplayer.prepare();
        return arcmediaplayer;
        IOException ioexception;
        ioexception;
        Log.d("ArcMediaPlayer", "create failed:", ioexception);
_L2:
        return null;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        Log.d("ArcMediaPlayer", "create failed:", illegalargumentexception);
        continue; /* Loop/switch isn't completed */
        SecurityException securityexception;
        securityexception;
        Log.d("ArcMediaPlayer", "create failed:", securityexception);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static ArcMediaPlayer create(Context context, Uri uri)
    {
        return create(context, uri, null);
    }

    public static ArcMediaPlayer create(Context context, Uri uri, SurfaceHolder surfaceholder)
    {
        ArcMediaPlayer arcmediaplayer;
        arcmediaplayer = new ArcMediaPlayer();
        arcmediaplayer.setDataSource(context, uri);
        if (surfaceholder == null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        arcmediaplayer.setDisplay(surfaceholder);
        arcmediaplayer.prepare();
        return arcmediaplayer;
        IOException ioexception;
        ioexception;
        Log.d("ArcMediaPlayer", "create failed:", ioexception);
_L2:
        return null;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        Log.d("ArcMediaPlayer", "create failed:", illegalargumentexception);
        continue; /* Loop/switch isn't completed */
        SecurityException securityexception;
        securityexception;
        Log.d("ArcMediaPlayer", "create failed:", securityexception);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static ArcMediaPlayer create(String s)
    {
        return null;
    }

    private final native void native_finalize();

    private final native boolean native_getMetadata(boolean flag, boolean flag1, Parcel parcel);

    private static final native void native_init(String s);

    private final native int native_invoke(Parcel parcel, Parcel parcel1);

    private final native int native_setMetadataFilter(Parcel parcel);

    private final native void native_setup(Object obj);

    private native int native_suspend_resume(boolean flag);

    private static void postEventFromNative(Object obj, int i, int j, int k, Object obj1)
    {
        ArcMediaPlayer arcmediaplayer;
        for (arcmediaplayer = (ArcMediaPlayer)((WeakReference)obj).get(); arcmediaplayer == null || arcmediaplayer.mEventHandler == null;)
        {
            return;
        }

        Message message = arcmediaplayer.mEventHandler.obtainMessage(i, j, k, obj1);
        arcmediaplayer.mEventHandler.sendMessage(message);
    }

    public static int snoop(short aword0[], int i)
    {
        return _snoop(aword0, i);
    }

    private void stayAwake(boolean flag)
    {
        if (mWakeLock == null) goto _L2; else goto _L1
_L1:
        if (!flag || mWakeLock.isHeld()) goto _L4; else goto _L3
_L3:
        mWakeLock.acquire();
_L2:
        mStayAwake = flag;
        updateSurfaceScreenOn();
        return;
_L4:
        if (!flag && mWakeLock.isHeld())
        {
            mWakeLock.release();
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    private void updateSurfaceScreenOn()
    {
        if (mSurfaceHolder != null)
        {
            SurfaceHolder surfaceholder = mSurfaceHolder;
            boolean flag;
            if (mScreenOnWhilePlaying && mStayAwake)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            surfaceholder.setKeepScreenOn(flag);
        }
    }

    public native int _getMode();

    public native void _setConfigFileHWDecCap(String s);

    public native void _setDataSource(FileDescriptor filedescriptor, long l, long l1)
        throws IOException, IllegalArgumentException, IllegalStateException;

    public Bitmap captureFrame(int i)
    {
        int j;
        int k;
        int l;
        android.graphics.Bitmap.Config.RGB_565;
        j = getVideoWidth();
        k = getVideoHeight();
        l = j * k;
        i;
        JVM INSTR tableswitch 1 4: default 52
    //                   1 63
    //                   2 52
    //                   3 52
    //                   4 110;
           goto _L1 _L2 _L1 _L1 _L3
_L1:
        Log.e("ArcMediaPlayer", "Unsupported pixel format");
        return null;
_L2:
        android.graphics.Bitmap.Config config;
        int i1;
        config = android.graphics.Bitmap.Config.ARGB_8888;
        i1 = l << 2;
_L5:
        Bitmap bitmap = Bitmap.createBitmap(j, k, config);
        byte abyte0[] = new byte[i1];
        _captureFrame(i, abyte0);
        bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(abyte0));
        return bitmap;
_L3:
        config = android.graphics.Bitmap.Config.RGB_565;
        i1 = l << 1;
        if (true) goto _L5; else goto _L4
_L4:
    }

    protected void finalize()
    {
        native_finalize();
    }

    public int getAspectRatio()
    {
        return _getAspectRatio();
    }

    public int getAudioEffectParam(int i, int ai[], byte abyte0[])
    {
        int j = 0;
        if (ai != null)
        {
            j = ai.length;
        }
        return _getAudioEffectParam(i, j, ai, abyte0.length, abyte0);
    }

    public int getAudioTrackNum()
    {
        return _getAudioTrackNum();
    }

    public int getCurrentAudioTrackIndex()
    {
        return _getCurrentAudioTrackIndex();
    }

    public int getCurrentBufferingPercent()
    {
        return _getCurrentBufferingPercent();
    }

    public int getCurrentPosition()
    {
        return _getCurrentPosition();
    }

    public int getDuration()
    {
        return _getDuration();
    }

    public Equalizer getEqualizer()
    {
        if (mEqualizer == null)
        {
            mEqualizer = new Equalizer(this);
        }
        return mEqualizer;
    }

    public Bitmap getFrameAt(int i)
        throws IllegalStateException
    {
        return captureFrame(i);
    }

    public int getMode()
    {
        return _getMode();
    }

    public void getParam(int i, int ai[])
    {
        _getParam(i, ai);
    }

    public int getPcmData(short aword0[], int i)
    {
        return _getPcmData(aword0, i);
    }

    public double getRate()
    {
        return _getRate();
    }

    public StereoWidening getStereoWidening()
    {
        if (mStereoWidening == null)
        {
            mStereoWidening = new StereoWidening(this);
        }
        return mStereoWidening;
    }

    public int getVideoHeight()
    {
        return _getVideoHeight();
    }

    public int getVideoWidth()
    {
        return _getVideoWidth();
    }

    public int invoke(Parcel parcel, Parcel parcel1)
    {
        int i = native_invoke(parcel, parcel1);
        parcel1.setDataPosition(0);
        return i;
    }

    public boolean isHardware()
    {
        return _isHardware();
    }

    public boolean isLooping()
    {
        return _isLooping();
    }

    protected boolean isMultiLanguage()
    {
        return _isMultiLanguage();
    }

    public boolean isPlaying()
    {
        return _isPlaying();
    }

    public Parcel newRequest()
    {
        Parcel parcel = Parcel.obtain();
        parcel.writeInterfaceToken("android.media.IArcMediaPlayer");
        return parcel;
    }

    public void pause()
        throws IllegalStateException
    {
        stayAwake(false);
        _pause();
    }

    public void prepare()
        throws IOException, IllegalStateException
    {
        _prepare();
    }

    public void prepareAsync()
        throws IllegalStateException
    {
        _prepareAsync();
    }

    public void release()
    {
        stayAwake(false);
        updateSurfaceScreenOn();
        mOnPreparedListener = null;
        mOnBufferingUpdateListener = null;
        mOnCompletionListener = null;
        mOnSeekCompleteListener = null;
        mOnErrorListener = null;
        mOnInfoListener = null;
        mOnVideoSizeChangedListener = null;
        _release();
    }

    public void reset()
    {
        stayAwake(false);
        _reset();
        mEventHandler.removeCallbacksAndMessages(null);
    }

    public boolean resume()
    {
        if (native_suspend_resume(false) < 0)
        {
            return false;
        }
        if (isPlaying())
        {
            stayAwake(true);
        }
        return true;
    }

    public void seekTo(int i)
        throws IllegalStateException
    {
        _seekTo(i);
    }

    public void seekToSyncFrame(int i)
    {
        _seekToSyncFrame(i);
    }

    protected void selectAudioChannel(int i)
    {
        _selectAudioChannel(i);
    }

    public void set3DDisplayMode(boolean flag)
    {
        _set3DDisplayMode(flag);
    }

    public void set3DPupilDist(long l)
    {
        _set3DPupilDist(l);
    }

    public void setASMEOptions(int i, int j, int k, int l)
    {
        _setASMEOptions(i, j, k, l);
    }

    public int setAudioEffectParam(int i, int ai[])
    {
        if (ai == null)
        {
            return 1;
        } else
        {
            return _setAudioEffectParam(i, ai.length, ai);
        }
    }

    public void setAudioSink(IAudioSink iaudiosink)
    {
        mAudioSink = iaudiosink;
        _setAudioSink();
    }

    public void setAudioStreamType(int i)
    {
        _setAudioStreamType(i);
    }

    public void setBenchmark(int i)
    {
        _setBenchmark(i);
    }

    public void setConfig(int i, int j)
    {
        _setConfig(i, j);
    }

    public void setConfigFile(String s)
    {
        _setConfigFile(s);
    }

    public void setConfigFileHWDecCap(String s)
    {
        _setConfigFileHWDecCap(s);
    }

    public void setCurrentAudioTrackIndex(int i)
    {
        _setCurrentAudioTrackIndex(i);
    }

    public void setDataSource(Context context, Uri uri)
        throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
    {
        setDataSource(context, uri, ((Map) (null)));
    }

    public void setDataSource(Context context, Uri uri, Map map)
        throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
    {
        String s = uri.getScheme();
        if (s != null && !s.equals("file")) goto _L2; else goto _L1
_L1:
        setDataSource(uri.getPath());
_L10:
        return;
_L2:
        AssetFileDescriptor assetfiledescriptor = null;
        AssetFileDescriptor assetfiledescriptor1 = context.getContentResolver().openAssetFileDescriptor(uri, "r");
        assetfiledescriptor = assetfiledescriptor1;
        if (assetfiledescriptor != null) goto _L4; else goto _L3
_L3:
        if (assetfiledescriptor != null)
        {
            assetfiledescriptor.close();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (assetfiledescriptor.getDeclaredLength() < 0L)
        {
            setDataSource(assetfiledescriptor.getFileDescriptor());
            continue; /* Loop/switch isn't completed */
        }
          goto _L5
        SecurityException securityexception;
        securityexception;
        if (assetfiledescriptor != null)
        {
            assetfiledescriptor.close();
        }
_L7:
        Log.d("ArcMediaPlayer", "Couldn't open file on client side, trying server side");
        setDataSource(uri.toString(), map);
        return;
_L5:
        setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getDeclaredLength());
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        if (assetfiledescriptor != null)
        {
            assetfiledescriptor.close();
        }
        if (true) goto _L7; else goto _L6
_L6:
        Exception exception;
        exception;
        if (assetfiledescriptor != null)
        {
            assetfiledescriptor.close();
        }
        throw exception;
        if (true) goto _L3; else goto _L8
_L8:
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void setDataSource(FileDescriptor filedescriptor)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        setDataSource(filedescriptor, 0L, 0x7ffffffffffffffL);
    }

    public void setDataSource(FileDescriptor filedescriptor, long l, long l1)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        _setDataSource(filedescriptor, l, l1);
    }

    public void setDataSource(String s)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        _setDataSource(s);
    }

    public void setDataSource(String s, Map map)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        _setDataSource(s, map);
    }

    public void setDisplay(SurfaceHolder surfaceholder)
    {
        mSurfaceHolder = surfaceholder;
        if (surfaceholder != null)
        {
            mSurface = surfaceholder.getSurface();
        } else
        {
            mSurface = null;
        }
        _setVideoSurface();
        updateSurfaceScreenOn();
    }

    public void setDisplayRect(int i, int j, int k, int l)
    {
        _setViewRect(i, j, k, l);
    }

    public void setHardwareMode(boolean flag)
    {
        _setHardwareMode(flag);
    }

    public void setLooping(boolean flag)
    {
        _setLooping(flag);
    }

    public int setMetadataFilter(Set set, Set set1)
    {
        Parcel parcel;
        Iterator iterator;
        parcel = newRequest();
        int i = parcel.dataSize() + 4 * (1 + (1 + set.size()) + set1.size());
        if (parcel.dataCapacity() < i)
        {
            parcel.setDataCapacity(i);
        }
        parcel.writeInt(set.size());
        iterator = set.iterator();
_L3:
        if (iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Iterator iterator1;
        parcel.writeInt(set1.size());
        iterator1 = set1.iterator();
_L4:
        if (!iterator1.hasNext())
        {
            return native_setMetadataFilter(parcel);
        }
        break MISSING_BLOCK_LABEL_128;
_L2:
        parcel.writeInt(((Integer)iterator.next()).intValue());
          goto _L3
        parcel.writeInt(((Integer)iterator1.next()).intValue());
          goto _L4
    }

    public void setMode(int i, double d)
    {
        _setMode(i, d);
    }

    public void setOnBufferingUpdateListener(android.media.MediaPlayer.OnBufferingUpdateListener onbufferingupdatelistener)
    {
        mOnBufferingUpdateListener = onbufferingupdatelistener;
    }

    public void setOnCompletionListener(android.media.MediaPlayer.OnCompletionListener oncompletionlistener)
    {
        mOnCompletionListener = oncompletionlistener;
    }

    public void setOnErrorListener(android.media.MediaPlayer.OnErrorListener onerrorlistener)
    {
        mOnErrorListener = onerrorlistener;
    }

    public void setOnInfoListener(android.media.MediaPlayer.OnInfoListener oninfolistener)
    {
        mOnInfoListener = oninfolistener;
    }

    public boolean setOnMessageListener(onMessageListener onmessagelistener)
    {
        mOnMessageListener = onmessagelistener;
        return true;
    }

    public void setOnPreparedListener(android.media.MediaPlayer.OnPreparedListener onpreparedlistener)
    {
        mOnPreparedListener = onpreparedlistener;
    }

    public void setOnSeekCompleteListener(android.media.MediaPlayer.OnSeekCompleteListener onseekcompletelistener)
    {
        mOnSeekCompleteListener = onseekcompletelistener;
    }

    public void setOnVideoSizeChangedListener(android.media.MediaPlayer.OnVideoSizeChangedListener onvideosizechangedlistener)
    {
        mOnVideoSizeChangedListener = onvideosizechangedlistener;
    }

    public void setParam(int i, int j)
    {
        _setParam(i, j);
    }

    public void setScreenOnWhilePlaying(boolean flag)
    {
        if (mScreenOnWhilePlaying != flag)
        {
            mScreenOnWhilePlaying = flag;
            updateSurfaceScreenOn();
        }
    }

    public void setVolume(float f, float f1)
    {
        _setVolume(f, f1);
    }

    public void setWakeMode(Context context, int i)
    {
        android.os.PowerManager.WakeLock wakelock = mWakeLock;
        boolean flag = false;
        if (wakelock != null)
        {
            boolean flag1 = mWakeLock.isHeld();
            flag = false;
            if (flag1)
            {
                flag = true;
                mWakeLock.release();
            }
            mWakeLock = null;
        }
        mWakeLock = ((PowerManager)context.getSystemService("power")).newWakeLock(0x20000000 | i, com/arcsoft/MediaPlayer/ArcMediaPlayer.getName());
        mWakeLock.setReferenceCounted(false);
        if (flag)
        {
            mWakeLock.acquire();
        }
    }

    public void start()
        throws IllegalStateException
    {
        stayAwake(true);
        _start();
    }

    public void stop()
        throws IllegalStateException
    {
        stayAwake(false);
        _stop();
    }

    public boolean suspend()
    {
        if (native_suspend_resume(true) < 0)
        {
            return false;
        } else
        {
            stayAwake(false);
            mEventHandler.removeCallbacksAndMessages(null);
            return true;
        }
    }

    static 
    {
        MEDIA_PLAYER_MODE_NORMAL = 0;
        MEDIA_PLAYER_MODE_FORWARD = 1;
        MEDIA_PLAYER_MODE_BACKWARD = 2;
        MEDIA_PLAYER_AUDIO_STEREO = 0;
        MEDIA_PLAYER_AUDIO_LEFT_ONLY = 1;
        MEDIA_PLAYER_AUDIO_RIGHT_ONLY = 2;
    }










}
