// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.media;


// Referenced classes of package android.media:
//            MediaCodec

public static final class mErrorCode extends RuntimeException
{

    private int mErrorCode;

    public int getErrorCode()
    {
        return mErrorCode;
    }

    public (int i, String s)
    {
        super(s);
        mErrorCode = i;
    }
}
