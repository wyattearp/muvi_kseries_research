// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor;

import powermobia.ve.utils.MBitmap;
import powermobia.videoeditor.base.IUserBubbleText;
import powermobia.videoeditor.base.MBubbleTextSource;

public class MEngine
{

    public static final int DEFAULT_THEME_ID = 0x2fbe4a40;
    public static final int PERCENT_PRECISION = 10000;
    public static final int PROP_AUTOCUT_KEY_FRAME_MODE = 27;
    private static final int PROP_CONTEXT_BASE = 0;
    public static final int PROP_COVER_PATH = 14;
    public static final int PROP_DEFAULT_OUTPUT_AUDIO_FORMAT = 3;
    public static final int PROP_DEFAULT_OUTPUT_FILE_FORMAT = 4;
    public static final int PROP_DEFAULT_OUTPUT_VIDEO_FORMAT = 2;
    public static final int PROP_DEFAULT_PLAYBACK_MUTE = 7;
    public static final int PROP_DEFAULT_PLAYBACK_VOLUME = 6;
    public static final int PROP_DEFAULT_RESAMPLE_MODE = 5;
    public static final int PROP_HW_READER_PP_MULTI_THREAD = 25;
    public static final int PROP_IMAGE_EFFECT_PATH = 13;
    public static final int PROP_KEEP_ORIENTATION_ANGLE = 22;
    public static final int PROP_MAX_SUPPORT_RESOLUTION = 9;
    public static final int PROP_ORIENTATION_ANGLE = 23;
    public static final int PROP_PLAYBACK_SKIP_TRANSITION = 21;
    public static final int PROP_PP_PROCESS_TYPE = 26;
    public static final int PROP_STATIC_DURATION = 19;
    public static final int PROP_SURFACE_LAYER_VIEW_HANDLES = 24;
    public static final int PROP_TEMP_PATH = 1;
    public static final int PROP_TEXT_FRAME_PATH = 12;
    public static final int PROP_THEME_PATH = 10;
    public static final int PROP_TRANSITION_PATH = 11;
    public static final int PROP_TRIM_TYPE = 20;
    public static final int PROP_USER_BUBBLETEXT_CALLBACK = 8;
    public static final int TRIM_FLAG_LEFT_KEYFRAME = 1;
    public static final int TRIM_FLAG_NORMAL = 0;
    public static final int TRIM_FLAG_RIGHT_KEYFRAME = 2;
    public static final int USE_CODEC_TYPE_HW_FIRST_SW_SECOND = 2;
    public static final int USE_CODEC_TYPE_HW_ONLY = 0;
    public static final int USE_CODEC_TYPE_SW_FIRST_HW_SECOND = 3;
    public static final int USE_CODEC_TYPE_SW_ONLY = 1;
    private long amcmHandle;
    private long engineHandle;
    private IUserBubbleText userBubbleText;

    public MEngine()
    {
        amcmHandle = 0L;
        engineHandle = 0L;
        userBubbleText = null;
    }

    private int callUserBubbleText(MBitmap mbitmap, MBubbleTextSource mbubbletextsource, int i, int j, Object obj)
    {
        if (userBubbleText == null)
        {
            return 0;
        } else
        {
            return userBubbleText.onUserBubbleText(mbitmap, mbubbletextsource, i, j, obj);
        }
    }

    private native int nativeCreate(MEngine mengine);

    private native int nativeDestroy(MEngine mengine);

    private native Object nativeGetProp(long l, int i);

    private native int nativeSetProp(long l, int i, Object obj);

    public int create()
    {
        return nativeCreate(this);
    }

    public int destory()
    {
        return nativeDestroy(this);
    }

    public Object getProperty(int i)
    {
        return nativeGetProp(engineHandle, i);
    }

    public int setProperty(int i, Object obj)
    {
        if (i == 8)
        {
            userBubbleText = (IUserBubbleText)obj;
            return 0;
        } else
        {
            return nativeSetProp(engineHandle, i, obj);
        }
    }
}
