// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.utils.MPoint;
import powermobia.utils.MRect;

// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class MotionBlur extends ToolBase
{
    public static class MBlurPen
    {

        public int iPenColor;
        public int iPenThickness;
        public int iStyle;
        public int iTransparency;

        public MBlurPen()
        {
        }
    }


    public static final int MBLURPEN_SOLID = 0xaad600;
    private static final int TOOLID_MOTIONBLUR = 0x9084033d;

    public MotionBlur()
    {
        mToolId = 0x9084033d;
    }

    private native int native_MB_AutoMotionBlur(int i, int j, int k);

    private native int native_MB_DoMotionBlur(int i, int j, int k, MRect amrect[]);

    private native int native_MB_DoMotionBlurBasedOnFreeSelection(int i, int j, int k);

    private native int native_MB_DrawTo(int i, MPoint mpoint);

    private native int native_MB_EndDraw(int i);

    private native int native_MB_SetPenStyle(int i, MBlurPen mblurpen);

    private native int native_MB_StarDraw(int i, MPoint mpoint);

    public int autoMotionBlur(int i, int j)
    {
        return native_MB_AutoMotionBlur(getNativeEngine(), i, j);
    }

    public int doMotionBlur(int i, int j, MRect amrect[])
    {
        return native_MB_DoMotionBlur(getNativeEngine(), i, j, amrect);
    }

    public int doMotionBlurBasedOnFreeSelection(int i, int j)
    {
        return native_MB_DoMotionBlurBasedOnFreeSelection(getNativeEngine(), i, j);
    }

    public int drawTo(MPoint mpoint)
    {
        return native_MB_DrawTo(getNativeEngine(), mpoint);
    }

    public int endDraw()
    {
        return native_MB_EndDraw(getNativeEngine());
    }

    public int setPenStyle(MBlurPen mblurpen)
    {
        return native_MB_SetPenStyle(getNativeEngine(), mblurpen);
    }

    public int startDraw(MPoint mpoint)
    {
        return native_MB_StarDraw(getNativeEngine(), mpoint);
    }
}
