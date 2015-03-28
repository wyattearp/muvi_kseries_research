// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.media;


// Referenced classes of package android.media:
//            MediaCodec

public static final class 
{

    public int flags;
    public int offset;
    public long presentationTimeUs;
    public int size;

    public void set(int i, int j, long l, int k)
    {
        offset = i;
        size = j;
        presentationTimeUs = l;
        flags = k;
    }

    public ()
    {
    }
}
