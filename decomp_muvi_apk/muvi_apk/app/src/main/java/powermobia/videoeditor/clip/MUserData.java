// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.clip;


public class MUserData
{

    private byte data[];
    private int dataLen;

    private MUserData()
    {
        data = null;
        dataLen = 0;
    }

    public MUserData(int i)
    {
        data = null;
        dataLen = 0;
        dataLen = i;
        data = new byte[i];
    }

    public byte[] getUserData()
    {
        return data;
    }

    public int getUserDataLength()
    {
        return dataLen;
    }
}
