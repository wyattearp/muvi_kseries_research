// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor;

import powermobia.utils.MBitmap;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;

public class MaskCreater
{
    public static class MagicWand
    {

        private int iMagicWandHandle;

        public int destroyMagicWand()
        {
            return MaskCreater.native_DestroyMagicWand(iMagicWandHandle);
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

        public int getMagicMask(MPoint mpoint, int i, boolean flag, MagicWandInfo magicwandinfo)
        {
            if (iMagicWandHandle != 0)
            {
                return MaskCreater.native_GetMagicMask(iMagicWandHandle, mpoint, i, flag, magicwandinfo);
            } else
            {
                return 2;
            }
        }

        public MagicWand(MBitmap mbitmap)
        {
            iMagicWandHandle = 0;
            Integer integer = new Integer(0);
            iMagicWandHandle = MaskCreater.native_CreateMagicWand(mbitmap, integer);
            if (integer.intValue() != 0 && iMagicWandHandle != 0)
            {
                MaskCreater.native_DestroyMagicWand(iMagicWandHandle);
                iMagicWandHandle = 0;
            }
        }
    }

    public static final class MagicWandInfo
    {

        public int aIntPoints[];
        public MBitmap bitmapMask;

        public MagicWandInfo()
        {
        }
    }


    public MaskCreater()
    {
    }

    public static int ellipse2Mask(MPoint mpoint, MRect mrect, MBitmap mbitmap, MRect mrect1, boolean flag)
    {
        return native_Ellipse2Mask2(mpoint, mrect, mbitmap, mrect1, flag);
    }

    public static int ellipse2Mask(MPoint mpoint, MRect mrect, MBitmap mbitmap, boolean flag)
    {
        return native_Ellipse2Mask(mpoint, mrect, mbitmap, flag);
    }

    private static native int native_CreateMagicWand(MBitmap mbitmap, Integer integer);

    private static native int native_DestroyMagicWand(int i);

    private static native int native_Ellipse2Mask(MPoint mpoint, MRect mrect, MBitmap mbitmap, boolean flag);

    private static native int native_Ellipse2Mask2(MPoint mpoint, MRect mrect, MBitmap mbitmap, MRect mrect1, boolean flag);

    private static native int native_GetMagicMask(int i, MPoint mpoint, int j, boolean flag, MagicWandInfo magicwandinfo);

    private static native int native_Point2Mask(MPoint mpoint, MPoint ampoint[], MBitmap mbitmap, boolean flag);

    public static int point2Mask(MPoint mpoint, MPoint ampoint[], MBitmap mbitmap, boolean flag)
    {
        return native_Point2Mask(mpoint, ampoint, mbitmap, flag);
    }



}
