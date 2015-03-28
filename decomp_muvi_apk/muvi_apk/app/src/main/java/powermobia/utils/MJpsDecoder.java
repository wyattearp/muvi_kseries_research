// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;


// Referenced classes of package powermobia.utils:
//            MStream

public final class MJpsDecoder
{

    private final int STREAM_LEFT = 0;
    private final int STREAM_RIGHT = 1;
    private int mJps;

    public MJpsDecoder()
    {
    }

    private native int native_JpsDecoderCreate(int i, Object obj);

    private native int native_JpsDecoderDestroy(int i);

    private native int native_JpsDecoderDoStep(int i);

    private native int native_JpsDecoderGetStream(int i, int j, int k);

    public int Destroy()
    {
        return native_JpsDecoderDestroy(mJps);
    }

    public int Dostep()
    {
        return native_JpsDecoderDoStep(mJps);
    }

    public int GetLeftStream(MStream mstream)
    {
        return native_JpsDecoderGetStream(mJps, 0, mstream.getNativeHandle());
    }

    public int GetRightStream(MStream mstream)
    {
        return native_JpsDecoderGetStream(mJps, 1, mstream.getNativeHandle());
    }

    public boolean Init(String s)
    {
        mJps = native_JpsDecoderCreate(1, s);
        return mJps != 0;
    }

    public boolean Init(MStream mstream)
    {
        mJps = native_JpsDecoderCreate(2, mstream);
        return mJps != 0;
    }
}
