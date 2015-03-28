// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;


// Referenced classes of package powermobia.utils:
//            MStream, MImgFileInfo, MBitmapFactory, MBitmap

public class MMPOToJpg
{

    private int mMpo;

    public MMPOToJpg()
    {
    }

    private native int native_MPOGetJpgCount(int i);

    private native int native_MPOGetJpgFromMPO(int i, int j, int k);

    private native int native_MPOToJPGCreate(int i);

    private native int native_MPOToJPGDestroy(int i);

    public int Destroy()
    {
        return native_MPOToJPGDestroy(mMpo);
    }

    public int GetJpgCount()
    {
        return native_MPOGetJpgCount(mMpo);
    }

    public int GetJpgFromMPO(int i, MStream mstream)
    {
        int j = mstream.getNativeHandle();
        return native_MPOGetJpgFromMPO(mMpo, i, j);
    }

    public MBitmap GetJpgFromMPO(int i, int j, int k, int l, boolean flag)
    {
        MStream mstream = new MStream();
        mstream.open(null);
        int i1 = GetJpgFromMPO(i, mstream);
        MBitmap mbitmap = null;
        if (i1 == 0)
        {
            if (flag)
            {
                MImgFileInfo mimgfileinfo = new MImgFileInfo(mstream);
                int j1 = mimgfileinfo.getWidth();
                int k1 = mimgfileinfo.getHeight();
                if (j1 == 0 || k1 == 0)
                {
                    return null;
                }
                if ((float)j / (float)j1 < (float)k / (float)k1)
                {
                    k = (j * k1) / j1;
                } else
                {
                    j = (k * j1) / k1;
                }
            }
            mbitmap = MBitmapFactory.createMBitmapFromMStream(mstream, j, k, l);
        }
        mstream.close();
        return mbitmap;
    }

    public boolean Init(MStream mstream)
    {
        mMpo = native_MPOToJPGCreate(mstream.getNativeHandle());
        return mMpo != 0;
    }
}
