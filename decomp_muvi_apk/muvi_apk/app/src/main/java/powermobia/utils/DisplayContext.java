// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;


// Referenced classes of package powermobia.utils:
//            MBitmap, MRect

public abstract class DisplayContext
{

    public int displayHeight;
    public int displayWidth;
    public int pixelArrayFormat;

    public DisplayContext()
    {
    }

    public abstract int bitBlt(MBitmap mbitmap, MRect mrect);

    public abstract int eraseBackground(MBitmap mbitmap, MRect mrect);

    public abstract MBitmap lockOffScreenBuffer(int i, int j);

    public abstract int unlockOffScreenBuffer(MBitmap mbitmap);
}
