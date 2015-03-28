// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;


// Referenced classes of package powermobia.utils:
//            MStream

public class MJpsEncoder
{

    private int mJps;

    public MJpsEncoder()
    {
    }

    private native int native_JpsEncoderCreate(int i, Object obj, Object obj1, Object obj2);

    private native int native_JpsEncoderDestroy(int i);

    private native int native_JpsEncoderDoStep(int i);

    public int Destroy()
    {
        return native_JpsEncoderDestroy(mJps);
    }

    public int DoStep()
    {
        return native_JpsEncoderDoStep(mJps);
    }

    public boolean Init(String s, String s1, String s2)
    {
        mJps = native_JpsEncoderCreate(1, s, s1, s2);
        return mJps != 0;
    }

    public boolean Init(MStream mstream, MStream mstream1, MStream mstream2)
    {
        mJps = native_JpsEncoderCreate(2, mstream, mstream1, mstream2);
        return mJps != 0;
    }
}
