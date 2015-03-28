// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.photoeditor.IPAdaptor;
import powermobia.utils.MBitmap;

// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class PhotoFix extends ToolBase
{
    public static class PhotoFixParam
    {

        public int aIntDenoiseConfig[];
        public boolean bAutoLevel;
        public boolean bDenoise;
        public int iBrightness;
        public int iColorBalanceBlue;
        public int iColorBalanceGreen;
        public int iColorBalanceRed;
        public int iColorTemperature;
        public int iContrastMax;
        public int iContrastMin;
        public int iHighlights;
        public int iSaturation;
        public int iShadows;
        public int iSharpness;
        public int iTint;

        public PhotoFixParam()
        {
        }
    }


    private static final int ID_HISTOGRAM_B = 4;
    private static final int ID_HISTOGRAM_G = 2;
    private static final int ID_HISTOGRAM_GRAY = 8;
    private static final int ID_HISTOGRAM_R = 1;
    private static final int TOOLID_PHOTOFIX = 0x9084033c;

    public PhotoFix()
    {
        mToolId = 0x9084033c;
    }

    private native int native_PF_Analyze(int i, PhotoFixParam photofixparam);

    private native int native_PF_DoAdaptiveAnalyze(int i, IPAdaptor ipadaptor);

    private native int native_PF_DoAdaptiveEnhance(int i, IPAdaptor ipadaptor);

    private native int native_PF_Enhance(int i, PhotoFixParam photofixparam);

    private native int native_PF_GetHistogram(int i, MBitmap mbitmap, int j, int ai[]);

    public int analyze(PhotoFixParam photofixparam)
    {
        return native_PF_Analyze(getNativeEngine(), photofixparam);
    }

    public int doAdaptiveAnalyze(IPAdaptor ipadaptor)
    {
        return native_PF_DoAdaptiveAnalyze(getNativeEngine(), ipadaptor);
    }

    public int doAdaptiveEnhance(IPAdaptor ipadaptor)
    {
        return native_PF_DoAdaptiveEnhance(getNativeEngine(), ipadaptor);
    }

    public int enhance(PhotoFixParam photofixparam)
    {
        return native_PF_Enhance(getNativeEngine(), photofixparam);
    }

    public int getHistogramB(MBitmap mbitmap, int ai[])
    {
        return native_PF_GetHistogram(getNativeEngine(), mbitmap, 4, ai);
    }

    public int getHistogramG(MBitmap mbitmap, int ai[])
    {
        return native_PF_GetHistogram(getNativeEngine(), mbitmap, 2, ai);
    }

    public int getHistogramGray(MBitmap mbitmap, int ai[])
    {
        return native_PF_GetHistogram(getNativeEngine(), mbitmap, 8, ai);
    }

    public int getHistogramR(MBitmap mbitmap, int ai[])
    {
        return native_PF_GetHistogram(getNativeEngine(), mbitmap, 1, ai);
    }

    public int getHistogramRGB(MBitmap mbitmap, int ai[])
    {
        return native_PF_GetHistogram(getNativeEngine(), mbitmap, 7, ai);
    }

    public int getHistogramRGBGray(MBitmap mbitmap, int ai[])
    {
        return native_PF_GetHistogram(getNativeEngine(), mbitmap, 15, ai);
    }
}
