// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.cover;

import powermobia.ve.utils.MRect;
import powermobia.videoeditor.base.MBubbleTextSource;
import powermobia.videoeditor.clip.MClip;
import powermobia.videoeditor.clip.MMediaSource;

// Referenced classes of package powermobia.videoeditor.cover:
//            MTitleInfo

public class MCover extends MClip
{

    public MCover()
    {
    }

    private native int nativeCoverCompose(long l);

    private native int nativeGetImageCount(long l);

    private native int nativeGetImageRegion(long l, int i, MRect mrect);

    private native int nativeGetImageRotate(long l, int i);

    private native int nativeGetImageSource(long l, int i, MMediaSource mmediasource);

    private native int nativeGetTitle(long l, MBubbleTextSource mbubbletextsource);

    private native int nativeGetTitleDefaultInfo(long l, MTitleInfo mtitleinfo);

    private native int nativeSetImageSource(long l, int i, MMediaSource mmediasource);

    private native int nativeSetTitle(long l, MBubbleTextSource mbubbletextsource);

    public int coverCompose()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeCoverCompose(handle);
        }
    }

    public int getImageCount()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeGetImageCount(handle);
        }
    }

    public MRect getImageRegion(int i)
    {
        MRect mrect;
        if (0L == handle)
        {
            mrect = null;
        } else
        {
            mrect = new MRect();
            if (nativeGetImageRegion(handle, i, mrect) != 0)
            {
                return null;
            }
        }
        return mrect;
    }

    public int getImageRotate(int i)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeGetImageRotate(handle, i);
        }
    }

    public MMediaSource getImageSource(int i)
    {
        MMediaSource mmediasource;
        if (0L == handle)
        {
            mmediasource = null;
        } else
        {
            mmediasource = new MMediaSource();
            if (nativeGetImageSource(handle, i, mmediasource) != 0)
            {
                return null;
            }
        }
        return mmediasource;
    }

    public MBubbleTextSource getTitle()
    {
        MBubbleTextSource mbubbletextsource;
        if (0L == handle)
        {
            mbubbletextsource = null;
        } else
        {
            mbubbletextsource = new MBubbleTextSource();
            if (nativeGetTitle(handle, mbubbletextsource) != 0)
            {
                return null;
            }
        }
        return mbubbletextsource;
    }

    public MTitleInfo getTitleDefaultInfo()
    {
        MTitleInfo mtitleinfo;
        if (0L == handle)
        {
            mtitleinfo = null;
        } else
        {
            mtitleinfo = new MTitleInfo();
            if (nativeGetTitleDefaultInfo(handle, mtitleinfo) != 0)
            {
                return null;
            }
        }
        return mtitleinfo;
    }

    public int setImageSource(int i, MMediaSource mmediasource)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSetImageSource(handle, i, mmediasource);
        }
    }

    public int setTitle(MBubbleTextSource mbubbletextsource)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSetTitle(handle, mbubbletextsource);
        }
    }
}
