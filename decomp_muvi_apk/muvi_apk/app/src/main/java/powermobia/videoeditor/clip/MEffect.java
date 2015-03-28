// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.clip;

import powermobia.videoeditor.MEngine;

// Referenced classes of package powermobia.videoeditor.clip:
//            IHandwritingListener, MHandwritingData

public class MEffect
{

    public static final int AUDIO_FRAME_MODE_ALIGN_LEFT = 2;
    public static final int AUDIO_FRAME_MODE_ALIGN_RIGHT = 3;
    public static final int AUDIO_FRAME_MODE_NORMAL = 0;
    public static final int AUDIO_FRAME_MODE_REPEAT = 1;
    public static final int PROP_AUDIO_FRAME_ADJUST_DB = 4115;
    public static final int PROP_AUDIO_FRAME_FADEIN = 4116;
    public static final int PROP_AUDIO_FRAME_FADEOUT = 4117;
    public static final int PROP_AUDIO_FRAME_MIXPERCENT = 4114;
    public static final int PROP_AUDIO_FRAME_RANGE = 4112;
    public static final int PROP_AUDIO_FRAME_REPEAT_MODE = 4113;
    public static final int PROP_AUDIO_FRAME_SOURCE = 4111;
    private static final int PROP_BASE = 4096;
    public static final int PROP_GROUP = 4099;
    public static final int PROP_HANDWRITING_ID = 4127;
    public static final int PROP_LAYER = 4100;
    public static final int PROP_RANGE = 4098;
    public static final int PROP_TEXT_FRAME_SOURCE = 4125;
    public static final int PROP_TEXT_FRAME_TEMPLATE = 4124;
    public static final int PROP_TYPE = 4097;
    public static final int PROP_USERDATA = 4101;
    public static final int PROP_USERDATA_CALLBACK = 4126;
    public static final int PROP_VIDEO_FRAME_EFFECT = 4106;
    public static final int PROP_VIDEO_FRAME_FPS = 4109;
    public static final int PROP_VIDEO_FRAME_MASK = 4105;
    public static final int PROP_VIDEO_FRAME_RANGE = 4108;
    public static final int PROP_VIDEO_FRAME_SOURCE = 4104;
    public static final int PROP_VIDEO_FRAME_SURFACE_LAYER_VIEW_ID = 4128;
    public static final int PROP_VIDEO_FRAME_TRANSITION = 4110;
    public static final int PROP_VIDEO_FRAME_TRANSPARENCY = 4107;
    public static final int PROP_VIDEO_IE_SOURCE = 4103;
    public static final int PROP_VIDEO_REGION_RATIO = 4102;
    public static final int TRACK_TYPE_AUDIO = 3;
    private static final int TRACK_TYPE_BASE = 0;
    public static final int TRACK_TYPE_PRIMAL_VIDEO = 1;
    public static final int TRACK_TYPE_VIDEO = 2;
    public static final int TYPE_AUDIO_FRAME = 3;
    private static final int TYPE_BASE = 0;
    public static final int TYPE_TEXT_FRAME = 5;
    public static final int TYPE_USER_DATA = 6;
    public static final int TYPE_VIDEO_FRAME = 2;
    public static final int TYPE_VIDEO_IE = 1;
    private long handle;
    private IHandwritingListener handwritingListener;
    private long jniglobalobjectref;
    private long masktmpbufferhandle;
    private long tmpbufferhandle;

    public MEffect()
    {
        tmpbufferhandle = 0L;
        masktmpbufferhandle = 0L;
        handle = 0L;
        jniglobalobjectref = 0L;
        handwritingListener = null;
    }

    private native int nativeCreate(MEngine mengine, MEffect meffect, int i, int j, int k, int l);

    private native int nativeDestroy(MEffect meffect);

    private native Object nativeGetProp(long l, int i);

    private native Object nativeGetProp(long l, int i, Object obj);

    private native int nativeSetProp(MEffect meffect, int i, Object obj);

    public int create(MEngine mengine, int i, int j, int k, int l)
    {
        return nativeCreate(mengine, this, j, k, i, l);
    }

    public int destory()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeDestroy(this);
        }
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

    public Object getProperty(int i, Object obj)
    {
        if (0L == handle)
        {
            return null;
        } else
        {
            return nativeGetProp(handle, i, obj);
        }
    }

    public int onHandwriting(MHandwritingData mhandwritingdata)
    {
        if (handwritingListener != null)
        {
            handwritingListener.onHandwriting(mhandwritingdata);
        }
        return 0;
    }

    public int setHandwritingListener(IHandwritingListener ihandwritinglistener)
    {
        handwritingListener = ihandwritinglistener;
        return 0;
    }

    public int setProperty(int i, Object obj)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSetProp(this, i, obj);
        }
    }
}
