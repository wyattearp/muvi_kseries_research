// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.utils.MPoint;
import powermobia.utils.MRect;

// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class FunnyFace extends ToolBase
{

    public static final int ID_ALIEN = 0xaad70c;
    public static final int ID_ANGRY = 0xaad702;
    public static final int ID_BIGFACE = 0xaad70a;
    public static final int ID_BUBBLE = 0xaad716;
    public static final int ID_FAT = 0xaad703;
    public static final int ID_FROWN = 0xaad70d;
    public static final int ID_GOBBLER = 0xaad711;
    public static final int ID_GRIT = 0xaad70f;
    public static final int ID_HIPPY = 0xaad705;
    public static final int ID_LONGFACE = 0xaad70b;
    public static final int ID_LONGNOSE = 0xaad709;
    public static final int ID_LOOKDOWN = 0xaad706;
    public static final int ID_NONE = 0xaad6ff;
    public static final int ID_PINCH = 0xaad715;
    public static final int ID_PROFESSOR = 0xaad710;
    public static final int ID_SAD = 0xaad701;
    public static final int ID_SHARPCHIN = 0xaad70e;
    public static final int ID_SMILE = 0xaad700;
    public static final int ID_SQUARE = 0xaad712;
    public static final int ID_SURPRISE = 0xaad707;
    public static final int ID_THIN = 0xaad704;
    public static final int ID_UPNOSE = 0xaad708;
    public static final int ID_WILDSAD = 0xaad714;
    public static final int ID_WILDSMILE = 0xaad713;
    private static final int TOOLID_FUNNYFACE = 0x9084033a;

    public FunnyFace()
    {
        mToolId = 0x9084033a;
    }

    private native int native_FF_DoTransform(int i, MRect mrect, MPoint ampoint[], int j, int k);

    private native MPoint[] native_FF_FaceDetect(int i, MRect mrect, Integer integer);

    public int doTransform(MRect mrect, MPoint ampoint[], int i, int j)
    {
        return native_FF_DoTransform(getNativeEngine(), mrect, ampoint, i, j);
    }

    public MPoint[] faceDetect(MRect mrect, Integer integer)
    {
        return native_FF_FaceDetect(getNativeEngine(), mrect, integer);
    }
}
