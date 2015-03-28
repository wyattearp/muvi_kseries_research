// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.utils.MPoint;

// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class FogDraw extends ToolBase
{
    public static class FogDrawInfo
    {

        public boolean bAdded;
        public int iClrFog;
        public int iTransparency;

        public FogDrawInfo()
        {
        }
    }

    public static class FogDrawPen
    {

        public int iThickness;

        public FogDrawPen()
        {
        }
    }


    private static final int TOOLID_FOGDRAWING = 0x9084033f;

    public FogDraw()
    {
        mToolId = 0x9084033f;
    }

    private native int native_FDW_DrawTo(int i, MPoint mpoint);

    private native int native_FDW_EndDraw(int i);

    private native int native_FDW_GetInfo(int i, FogDrawInfo fogdrawinfo);

    private native int native_FDW_SetFog(int i, int j, int k);

    private native int native_FDW_SetPen(int i, FogDrawPen fogdrawpen);

    private native int native_FDW_StartDraw(int i, MPoint mpoint);

    public int drawTo(MPoint mpoint)
    {
        return native_FDW_DrawTo(getNativeEngine(), mpoint);
    }

    public int endDraw()
    {
        return native_FDW_EndDraw(getNativeEngine());
    }

    public int getInfo(FogDrawInfo fogdrawinfo)
    {
        return native_FDW_GetInfo(getNativeEngine(), fogdrawinfo);
    }

    public int setFog(int i, int j)
    {
        return native_FDW_SetFog(getNativeEngine(), i, j);
    }

    public int setPen(FogDrawPen fogdrawpen)
    {
        return native_FDW_SetPen(getNativeEngine(), fogdrawpen);
    }

    public int startDraw(MPoint mpoint)
    {
        return native_FDW_StartDraw(getNativeEngine(), mpoint);
    }
}
