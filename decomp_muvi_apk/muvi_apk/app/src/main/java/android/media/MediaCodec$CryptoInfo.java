// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.media;


// Referenced classes of package android.media:
//            MediaCodec

public static final class 
{

    public byte iv[];
    public byte key[];
    public int mode;
    public int numBytesOfClearData[];
    public int numBytesOfEncryptedData[];
    public int numSubSamples;

    public void set(int i, int ai[], int ai1[], byte abyte0[], byte abyte1[], int j)
    {
        numSubSamples = i;
        numBytesOfClearData = ai;
        numBytesOfEncryptedData = ai1;
        key = abyte0;
        iv = abyte1;
        mode = j;
    }

    public ()
    {
    }
}
