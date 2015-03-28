// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.utils.MPoint;

// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class Draw extends ToolBase
{
    public static class Brush
    {

        public int iBrushColor;
        public int iStyle;
        public int iTransparency;

        public Brush()
        {
        }
    }

    public static class ClrGradient
    {

        public int iColor;
        public int iGradient;

        public ClrGradient()
        {
        }
    }

    public static class ColorfulPen
    {

        public ClrGradient aClrGradients[];
        public int iPenThickness;
        public int iSpreadMethod;
        public int iStyle;

        public ColorfulPen()
        {
        }
    }

    public static class Pen
    {

        public int iPenColor;
        public int iPenThickness;
        public int iStyle;
        public int iTransparency;

        public Pen()
        {
        }
    }


    public static final int BRUSH_SOLID = 0xaad620;
    public static final int COLOR_SPREAD_REFLECT = 0xaad640;
    public static final int COLOR_SPREAD_REPEAT = 0xaad641;
    public static final int PEN_SOLID = 0xaad600;
    public static final int SHAPE_CIRCLE = 0xaad6b3;
    public static final int SHAPE_ELLIPSE = 0xaad6b4;
    public static final int SHAPE_LINE = 0xaad6b1;
    public static final int SHAPE_PENCIL = 0xaad6b0;
    public static final int SHAPE_RECTANGLE = 0xaad6b2;
    private static final int TOOLID_DRAWING = 0x90840335;

    public Draw()
    {
        mToolId = 0x90840335;
    }

    private native int native_DW_DrawPolyLine(int i, MPoint ampoint[]);

    private native int native_DW_DrawTo(int i, MPoint mpoint);

    private native int native_DW_EndDraw(int i);

    private native int native_DW_SetBrush(int i, Brush brush);

    private native int native_DW_SetColorfulPen(int i, ColorfulPen colorfulpen);

    private native int native_DW_SetPen(int i, Pen pen);

    private native int native_DW_SetShape(int i, int j);

    private native int native_DW_StartDraw(int i, MPoint mpoint);

    public int drawPolyLine(MPoint ampoint[])
    {
        return native_DW_DrawPolyLine(getNativeEngine(), ampoint);
    }

    public int drawTo(MPoint mpoint)
    {
        return native_DW_DrawTo(getNativeEngine(), mpoint);
    }

    public int endDraw()
    {
        return native_DW_EndDraw(getNativeEngine());
    }

    public int setBrush(Brush brush)
    {
        return native_DW_SetBrush(getNativeEngine(), brush);
    }

    public int setColorfulPen(ColorfulPen colorfulpen)
    {
        return native_DW_SetColorfulPen(getNativeEngine(), colorfulpen);
    }

    public int setPen(Pen pen)
    {
        return native_DW_SetPen(getNativeEngine(), pen);
    }

    public int setShape(int i)
    {
        return native_DW_SetShape(getNativeEngine(), i);
    }

    public int startDraw(MPoint mpoint)
    {
        return native_DW_StartDraw(getNativeEngine(), mpoint);
    }
}
