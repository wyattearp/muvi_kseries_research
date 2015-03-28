// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.utils.MRect;

// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class BasicEditor extends ToolBase
{

    private static final int TOOLID_BASICEDITING = 0x90840330;

    public BasicEditor()
    {
        mToolId = 0x90840330;
    }

    private native int native_BE_Brightness(int i, int j);

    private native int native_BE_Contrast(int i, int j);

    private native int native_BE_Crop(int i, MRect mrect);

    private native int native_BE_Flip(int i, boolean flag);

    private native int native_BE_GetBrightnessContrast(int i, Integer integer, Integer integer1);

    private native int native_BE_GetHueSaturation(int i, Integer integer, Integer integer1);

    private native int native_BE_Hue(int i, int j);

    private native int native_BE_Rotate(int i, int j);

    private native int native_BE_Saturation(int i, int j);

    public int brightness(int i)
    {
        return native_BE_Brightness(getNativeEngine(), i);
    }

    public int contrast(int i)
    {
        return native_BE_Contrast(getNativeEngine(), i);
    }

    public int crop(MRect mrect)
    {
        return native_BE_Crop(getNativeEngine(), mrect);
    }

    public int getBrightnessContrast(Integer integer, Integer integer1)
    {
        return native_BE_GetBrightnessContrast(getNativeEngine(), integer, integer1);
    }

    public int getHueSaturation(Integer integer, Integer integer1)
    {
        return native_BE_GetHueSaturation(getNativeEngine(), integer, integer1);
    }

    public int hFlip()
    {
        return native_BE_Flip(getNativeEngine(), true);
    }

    public int hue(int i)
    {
        return native_BE_Hue(getNativeEngine(), i);
    }

    public int rotate(int i)
    {
        return native_BE_Rotate(getNativeEngine(), i);
    }

    public int saturation(int i)
    {
        return native_BE_Saturation(getNativeEngine(), i);
    }

    public int vFlip()
    {
        return native_BE_Flip(getNativeEngine(), false);
    }
}
