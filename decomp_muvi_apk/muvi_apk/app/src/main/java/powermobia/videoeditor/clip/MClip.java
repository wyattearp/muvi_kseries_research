// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.clip;

import powermobia.ve.utils.MBitmap;
import powermobia.videoeditor.MEngine;
import powermobia.videoeditor.base.MSession;

// Referenced classes of package powermobia.videoeditor.clip:
//            MEffect, MMediaSource

public class MClip extends MSession
{

    public static final int COVER_TYPE_BACKCOVER = 2;
    public static final int COVER_TYPE_COVER = 1;
    public static final int COVER_TYPE_NONE = 0;
    public static final int PROP_AUDIO_ADJUSTDB = 12299;
    public static final int PROP_AUDIO_DISABLED = 12300;
    public static final int PROP_AUDIO_FADEIN = 12297;
    public static final int PROP_AUDIO_FADEOUT = 12298;
    private static final int PROP_BASE = 12288;
    public static final int PROP_BUBBLE_BG_COLOR = 12306;
    public static final int PROP_BUBBLE_HOR_REVERSAL = 12308;
    public static final int PROP_BUBBLE_REGION_RATIO = 12311;
    public static final int PROP_BUBBLE_ROTATE_ANGLE = 12309;
    public static final int PROP_BUBBLE_ROTATE_CENTER = 12310;
    public static final int PROP_BUBBLE_TRANSPARENCY = 12312;
    public static final int PROP_BUBBLE_VER_REVERSAL = 12307;
    public static final int PROP_CLIP_MOTION = 12318;
    public static final int PROP_COVER_TYPE = 12313;
    private static final int PROP_EFFECT_GROUP_BASE = 8192;
    public static final int PROP_EFFECT_GROUP_EFFECT_COUNT = 8193;
    public static final int PROP_EFFECT_GROUP_EFFECT_HANDLE = 8194;
    public static final int PROP_EFFECT_GROUP_EFFECT_OVERLAP = 8195;
    public static final int PROP_PRIMAL_AUDIO_DISABLED = 12301;
    public static final int PROP_PRIMAL_VIDEO_DISABLED = 12305;
    public static final int PROP_RANGE = 12292;
    public static final int PROP_RESAMPLE_MODE = 12295;
    public static final int PROP_SOURCE = 12290;
    public static final int PROP_SOURCE_INFO = 12291;
    public static final int PROP_TIME_SCALE = 12293;
    public static final int PROP_TRANSITION = 12294;
    public static final int PROP_TYPE = 12289;
    public static final int PROP_USERDATA = 12296;
    public static final int PROP_VIDEO_DISABLED = 12304;
    public static final int PROP_VIDEO_FADEIN = 12302;
    public static final int PROP_VIDEO_FADEOUT = 12303;
    public static final int TIMESCALE_DIV_2 = 129;
    public static final int TIMESCALE_DIV_4 = 130;
    public static final int TIMESCALE_MUL_2 = 1;
    public static final int TIMESCALE_MUL_4 = 2;
    public static final int TIMESCALE_NORMAL = 0;
    public static final int TYPE_AUDIO = 3;
    private static final int TYPE_BASE = 0;
    public static final int TYPE_BUBBLETEXT = 6;
    public static final int TYPE_IMAGE = 2;
    public static final int TYPE_SVG = 4;
    public static final int TYPE_SWF = 5;
    public static final int TYPE_VIDEO = 1;
    private long nativeThumbnailManager;
    private long tmpbufferhandle;

    public MClip()
    {
        tmpbufferhandle = 0L;
        nativeThumbnailManager = 0L;
    }

    private native int nativeCreate(MEngine mengine, MMediaSource mmediasource, MClip mclip);

    private native int nativeCreateThumbnailManager(int i, int j, int k, boolean flag);

    private native int nativeDestroy(MClip mclip);

    private native int nativeDestroyThumbnailManager(long l);

    private native int nativeDuplicate(MClip mclip, MClip mclip1);

    private native int nativeExtractAudioSample(long l, int i, int j, byte abyte0[], byte abyte1[], Integer ainteger[]);

    private native int nativeGetEffect(long l, int i, int j, int k, MEffect meffect);

    private native int nativeGetEffectCount(long l, int i, int j);

    private native Object nativeGetEffectGroupProp(long l, int i, int j, int k, Object obj);

    private native int nativeGetKeyframe(long l, MBitmap mbitmap, int i, boolean flag, int j);

    private native Object nativeGetProp(long l, int i);

    private native int nativeGetThumbnail(long l, MBitmap mbitmap, int i, boolean flag);

    private native int nativeInsertEffect(long l, MEffect meffect);

    private native int nativeMoveEffect(long l, MEffect meffect, int i);

    private native int nativeRemoveEffect(long l, MEffect meffect);

    private native int nativeSetEffectGroupProp(long l, int i, int j, int k, Object obj, Object obj1);

    private native int nativeSetProp(long l, int i, Object obj);

    public int createThumbnailManager(int i, int j, int k)
    {
        return nativeCreateThumbnailManager(i, j, k, false);
    }

    public int createThumbnailManager(int i, int j, int k, boolean flag)
    {
        return nativeCreateThumbnailManager(i, j, k, flag);
    }

    public int destroyThumbnailManager()
    {
        if (0L != nativeThumbnailManager)
        {
            nativeDestroyThumbnailManager(nativeThumbnailManager);
        }
        return 0;
    }

    public int duplicate(MClip mclip)
    {
        return nativeDuplicate(this, mclip);
    }

    public int extractAudioSample(int i, int j, byte abyte0[], byte abyte1[], Integer ainteger[])
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeExtractAudioSample(handle, i, j, abyte0, abyte1, ainteger);
        }
    }

    public MEffect getEffect(int i, int j, int k)
    {
        MEffect meffect;
        if (0L == handle)
        {
            meffect = null;
        } else
        {
            meffect = new MEffect();
            if (nativeGetEffect(handle, i, j, k, meffect) != 0)
            {
                return null;
            }
        }
        return meffect;
    }

    public int getEffectCount(int i, int j)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeGetEffectCount(handle, i, j);
        }
    }

    public Object getEffectGroupProp(int i, int j, int k, Object obj)
    {
        if (0L == handle)
        {
            return null;
        } else
        {
            return nativeGetEffectGroupProp(handle, i, j, k, obj);
        }
    }

    public int getKeyframe(MBitmap mbitmap, int i, boolean flag, int j)
    {
        int k;
        if (0L == handle)
        {
            k = 0x80006;
        } else
        {
            if (mbitmap == null)
            {
                return 0x80003;
            }
            k = nativeGetKeyframe(handle, mbitmap, i, flag, j);
            if (k == 0)
            {
                return 0;
            }
        }
        return k;
    }

    public Object getProperty(int i)
    {
        if (0L == handle)
        {
            return null;
        } else
        {
            return nativeGetProp(handle, i);
        }
    }

    public int getThumbnail(MBitmap mbitmap, int i, boolean flag)
    {
        int j;
        if (0L == handle)
        {
            j = 0x80006;
        } else
        {
            if (mbitmap == null)
            {
                return 0x80003;
            }
            j = nativeGetThumbnail(handle, mbitmap, i, flag);
            if (j == 0)
            {
                return 0;
            }
        }
        return j;
    }

    public int init(MEngine mengine, MMediaSource mmediasource)
    {
        return nativeCreate(mengine, mmediasource, this);
    }

    public int insertEffect(MEffect meffect)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeInsertEffect(handle, meffect);
        }
    }

    public int moveEffect(MEffect meffect, int i)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeMoveEffect(handle, meffect, i);
        }
    }

    public int removeEffect(MEffect meffect)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeRemoveEffect(handle, meffect);
        }
    }

    public int setEffectGroupProp(int i, int j, int k, Object obj, Object obj1)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSetEffectGroupProp(handle, i, j, k, obj, obj1);
        }
    }

    public int setProperty(int i, Object obj)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSetProp(handle, i, obj);
        }
    }

    public int unInit()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeDestroy(this);
        }
    }
}
