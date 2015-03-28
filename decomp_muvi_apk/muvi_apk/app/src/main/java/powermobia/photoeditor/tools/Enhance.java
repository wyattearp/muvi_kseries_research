// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;


// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class Enhance extends ToolBase
{

    public static final int ID_AUTOBRIGHTNESSCONTRAST = 0xaad402;
    public static final int ID_AUTOCOLOR = 0xaad401;
    public static final int ID_AUTOEXPOSURE = 0xaad404;
    public static final int ID_AUTOLEVEL = 0xaad400;
    public static final int ID_BRIGHTNESS_CONTRAST = 0xaad408;
    public static final int ID_FILLLIGHTING = 0xaad403;
    public static final int ID_HIGHLIGHTSSHADOWS = 0xaad406;
    public static final int ID_HUE_SATURATION = 0xaad409;
    public static final int ID_STRAIGHTEN = 0xaad407;
    public static final int ID_WHITEBALANCE = 0xaad405;
    private static final int TOOLID_PHOTOENHANCEMENT = 0x90840334;
    public static final int WB_TYPE_ASSHOT = 0xaad4f0;
    public static final int WB_TYPE_AUTOWB = 0xaad4f1;
    public static final int WB_TYPE_CLOUDY = 0xaad4f3;
    public static final int WB_TYPE_CUSTOM = 0xaad4f8;
    public static final int WB_TYPE_DAYLIGHT = 0xaad4f2;
    public static final int WB_TYPE_FLASH = 0xaad4f7;
    public static final int WB_TYPE_FLUORESCENT = 0xaad4f6;
    public static final int WB_TYPE_SHADE = 0xaad4f4;
    public static final int WB_TYPE_TUNGSTEN = 0xaad4f5;

    public Enhance()
    {
        mToolId = 0x90840334;
    }

    private native int native_EN_Enhance(int i, int j, int ai[]);

    public int enhance(int i)
    {
        return native_EN_Enhance(getNativeEngine(), i, null);
    }

    public int enhance(int i, int ai[])
    {
        return native_EN_Enhance(getNativeEngine(), i, ai);
    }
}
