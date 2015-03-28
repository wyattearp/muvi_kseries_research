// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;

import android.graphics.Bitmap;

public final class MAGIFDecoder
{

    private static int FLOAT_MULTI_EX = 10;
    private int mAGIFDecoder;
    private int mDelayTime;
    private int mDspHeight;
    private int mDspWidth;
    private int mFrameCount;
    private int mImageHeigth;
    private int mImageWidth;
    private int mLoopNum;

    public MAGIFDecoder()
    {
        mAGIFDecoder = 0;
        mImageWidth = 0;
        mImageHeigth = 0;
        mLoopNum = 0;
        mFrameCount = 0;
        mDelayTime = 0;
        mDspWidth = 0;
        mDspHeight = 0;
    }

    private static native int native_AGIFDecoderCreate(int i, Object obj);

    private static native int native_AGIFDecoderDestory(int i);

    private static native int native_AGIFDecoderGetDelayTime(int i);

    private static native int native_AGIFDecoderGetFrameCount(int i);

    private static native Object native_AGIFDecoderGetFrameData(int i, int j, int k, int l);

    private static native int native_AGIFDecoderGetHeight(int i);

    private static native int native_AGIFDecoderGetLoopNum(int i);

    private static native int native_AGIFDecoderGetWidth(int i);

    private static native int native_AGIFDecoderReSet(int i);

    public Bitmap GetFrameData(int i, int j, int k)
    {
        Bitmap bitmap;
        if (mAGIFDecoder == 0)
        {
            bitmap = null;
        } else
        {
            bitmap = (Bitmap)native_AGIFDecoderGetFrameData(mAGIFDecoder, i, j, k);
            if (bitmap == null)
            {
                return null;
            }
        }
        return bitmap;
    }

    public int getDelayTime()
    {
        mDelayTime = native_AGIFDecoderGetDelayTime(mAGIFDecoder);
        return mDelayTime;
    }

    public int getFrameCount()
    {
        return mFrameCount;
    }

    public int getImageHeight()
    {
        return mImageHeigth;
    }

    public int getImageWidth()
    {
        return mImageWidth;
    }

    public int getLoopNum()
    {
        return mLoopNum;
    }

    public boolean init(String s)
    {
        mAGIFDecoder = native_AGIFDecoderCreate(1, s);
        if (mAGIFDecoder == 0)
        {
            return false;
        } else
        {
            mImageWidth = native_AGIFDecoderGetWidth(mAGIFDecoder);
            mImageHeigth = native_AGIFDecoderGetHeight(mAGIFDecoder);
            mLoopNum = native_AGIFDecoderGetLoopNum(mAGIFDecoder);
            mFrameCount = native_AGIFDecoderGetFrameCount(mAGIFDecoder);
            return true;
        }
    }

    public int reSet()
    {
        return native_AGIFDecoderReSet(mAGIFDecoder);
    }

    public void unInit()
    {
        if (mAGIFDecoder != 0)
        {
            native_AGIFDecoderDestory(mAGIFDecoder);
        }
    }

}
