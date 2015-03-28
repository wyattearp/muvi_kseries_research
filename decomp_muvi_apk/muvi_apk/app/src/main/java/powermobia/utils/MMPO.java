// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;


// Referenced classes of package powermobia.utils:
//            MStream

public final class MMPO
{

    private int mMpo;

    public MMPO()
    {
    }

    private native int native_MPOAdd(int i, int j);

    private native int native_MPOCreate(int i);

    private native int native_MPODestroy(int i);

    public int AddJpgtoMPO(MStream mstream)
    {
        int i = mstream.getNativeHandle();
        return native_MPOAdd(mMpo, i);
    }

    public int Destroy()
    {
        return native_MPODestroy(mMpo);
    }

    public boolean Init(MStream mstream)
    {
        mMpo = native_MPOCreate(mstream.getNativeHandle());
        return mMpo != 0;
    }
}
