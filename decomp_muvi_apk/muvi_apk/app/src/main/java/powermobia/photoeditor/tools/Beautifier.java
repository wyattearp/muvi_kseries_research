// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.utils.MRect;

// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class Beautifier extends ToolBase
{

    private static final int TOOLID_FACEBEAUTIFIER = 0x9084033b;

    public Beautifier()
    {
        mToolId = 0x9084033b;
    }

    private native int native_FB_Beautify(int i, MRect mrect, int j);

    private native MRect[] native_FB_FaceDetect(int i, Integer integer);

    public int beautify(MRect mrect, int i)
    {
        return native_FB_Beautify(getNativeEngine(), mrect, i);
    }

    public MRect[] faceDetect(Integer integer)
    {
        return native_FB_FaceDetect(getNativeEngine(), integer);
    }
}
