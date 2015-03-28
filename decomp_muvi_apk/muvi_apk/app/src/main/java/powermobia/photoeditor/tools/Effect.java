// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.photoeditor.IPAdaptor;
import powermobia.utils.MBitmap;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;

// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class Effect extends ToolBase
{

    public static final int ID_3D_GRID = 0xaad320;
    public static final int ID_ANTIQUE = 0xaad31d;
    public static final int ID_BLACKWHITE = 0xaad301;
    public static final int ID_BLUR = 0xaad305;
    public static final int ID_CARTOON = 0xaad31f;
    public static final int ID_COLDCOLOR = 0xaad326;
    public static final int ID_COLORBOOST = 0xaad319;
    public static final int ID_COLORCHANNEL_B = 0xaad30d;
    public static final int ID_COLORCHANNEL_G = 0xaad30c;
    public static final int ID_COLORCHANNEL_R = 0xaad30b;
    public static final int ID_COLORCHANNEL_RGB = 0xaad311;
    public static final int ID_COLOREMBOSS = 0xaad308;
    public static final int ID_COLORSKETCH = 0xaad30a;
    public static final int ID_DESPECKLE = 0xaad306;
    public static final int ID_DIFFUSE = 0xaad32e;
    public static final int ID_DITHER = 0xaad30e;
    public static final int ID_FILLCOLOR = 0xaad313;
    public static final int ID_FIRELIGHT = 0xaad323;
    public static final int ID_FOCALWHITEBLACK = 0xaad327;
    public static final int ID_FOCUS = 0xaad31c;
    public static final int ID_FOG = 0xaad31b;
    public static final int ID_GLOW = 0xaad31e;
    public static final int ID_GPEN = 0xaad32a;
    public static final int ID_GRAYEMBOSS = 0xaad307;
    public static final int ID_GRAYNEGATIVE = 0xaad316;
    public static final int ID_GRAYSKETCH = 0xaad309;
    public static final int ID_HATCH = 0xaad32d;
    public static final int ID_LOMO = 0xaad32c;
    public static final int ID_MILKY = 0xaad32f;
    public static final int ID_MIRROR = 0xaad331;
    public static final int ID_MOONLIGHT = 0xaad31a;
    public static final int ID_MOONNIGHT = 0xaad329;
    public static final int ID_MOSAIC = 0xaad324;
    public static final int ID_NEGATIVE = 0xaad302;
    public static final int ID_NEON = 0xaad322;
    public static final int ID_NOISE = 0xaad315;
    public static final int ID_NONE = 0xaad300;
    public static final int ID_OILPAINTING = 0xaad310;
    public static final int ID_OLDFILM = 0xaad330;
    public static final int ID_OLDPHOTO = 0xaad303;
    public static final int ID_POSTERIZE = 0xaad312;
    public static final int ID_SHARPEN = 0xaad304;
    public static final int ID_SOLARIZE = 0xaad30f;
    public static final int ID_SPOTLIGHT = 0xaad317;
    public static final int ID_STAMP = 0xaad318;
    public static final int ID_TEXTURE = 0xaad321;
    public static final int ID_VIVID = 0xaad32b;
    public static final int ID_WARMCOLOR = 0xaad325;
    public static final int ID_WARPING = 0xaad314;
    public static final int ID_WATERCOLOR = 0xaad328;
    public static final int LOMO_TYPE_COLDCOLOR = 0xaad4e1;
    public static final int LOMO_TYPE_DARKCORNER = 0xaad4e2;
    public static final int LOMO_TYPE_MAGIC = 0xaad4e3;
    public static final int LOMO_TYPE_SOFT = 0xaad4e4;
    public static final int LOMO_TYPE_WARMCOLOR = 0xaad4e0;
    public static final int MIRROR_TYPE_TABLE = 0xaad4e6;
    public static final int MIRROR_TYPE_WAll = 0xaad4e5;
    private static final int TOOLID_SPECIALEFFECT = 0x90840333;

    public Effect()
    {
        mToolId = 0x90840333;
    }

    private native int native_SE_DoAdaptiveEffect(int i, IPAdaptor ipadaptor);

    private native int native_SE_DoEffect(int i, int j, Object obj);

    private native int native_SE_SetAreaByRect(int i, MRect mrect, boolean flag);

    private native int native_SE_SetEffectMask(int i, MBitmap mbitmap, MPoint mpoint);

    public int doAdaptiveEffect(IPAdaptor ipadaptor)
    {
        return native_SE_DoAdaptiveEffect(getNativeEngine(), ipadaptor);
    }

    public int doEffect(int i)
    {
        return native_SE_DoEffect(getNativeEngine(), i, null);
    }

    public int doEffect(int i, MBitmap mbitmap)
    {
        return native_SE_DoEffect(getNativeEngine(), i, mbitmap);
    }

    public int doEffect(int i, int ai[])
    {
        return native_SE_DoEffect(getNativeEngine(), i, ai);
    }

    public int setAreaByRect(MRect mrect, boolean flag)
    {
        return native_SE_SetAreaByRect(getNativeEngine(), mrect, flag);
    }

    public int setEffectMask(MBitmap mbitmap, MPoint mpoint)
    {
        return native_SE_SetEffectMask(getNativeEngine(), mbitmap, mpoint);
    }
}
