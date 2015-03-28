// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.utils.MBitmap;

// Referenced classes of package powermobia.photoeditor.tools:
//            Overlay

public static abstract class 
{

    public boolean bHorizFlip;
    public boolean bVertFlip;
    public int iClrTxt;
    public int iDegree;
    public String strText;

    public abstract MBitmap cbDrawText(String s);

    public abstract int cbFreeText();

    public ()
    {
    }
}
