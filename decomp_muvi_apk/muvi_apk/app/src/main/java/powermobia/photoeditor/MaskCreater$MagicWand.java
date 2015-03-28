// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor;

import powermobia.utils.MBitmap;
import powermobia.utils.MPoint;

// Referenced classes of package powermobia.photoeditor:
//            MaskCreater

public static class iMagicWandHandle
{

    private int iMagicWandHandle;

    public int destroyMagicWand()
    {
        return MaskCreater.access$1(iMagicWandHandle);
    }

    protected void finalize()
        throws Throwable
    {
        Exception exception;
        try
        {
            destroyMagicWand();
        }
        catch (Exception exception1)
        {
            super.finalize();
            return;
        }
        finally
        {
            super.finalize();
        }
        super.finalize();
        return;
        throw exception;
    }

    public int getMagicMask(MPoint mpoint, int i, boolean flag, nfo nfo)
    {
        if (iMagicWandHandle != 0)
        {
            return MaskCreater.access$2(iMagicWandHandle, mpoint, i, flag, nfo);
        } else
        {
            return 2;
        }
    }

    public nfo(MBitmap mbitmap)
    {
        iMagicWandHandle = 0;
        Integer integer = new Integer(0);
        iMagicWandHandle = MaskCreater.access$0(mbitmap, integer);
        if (integer.intValue() != 0 && iMagicWandHandle != 0)
        {
            int _tmp = MaskCreater.access$1(iMagicWandHandle);
            iMagicWandHandle = 0;
        }
    }
}
