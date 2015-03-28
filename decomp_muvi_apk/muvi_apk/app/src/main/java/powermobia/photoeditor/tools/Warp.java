// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.utils.MPoint;

// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class Warp extends ToolBase
{

    private static final int TOOLID_WARP = 0x90840339;

    public Warp()
    {
        mToolId = 0x90840339;
    }

    private native int native_WP_EndWarp(int i);

    private native int native_WP_SetWarpRadius(int i, int j);

    private native int native_WP_StartWarp(int i, MPoint mpoint);

    private native int native_WP_WarpTo(int i, MPoint mpoint);

    public int endWarp()
    {
        return native_WP_EndWarp(getNativeEngine());
    }

    public int setWarpRadius(int i)
    {
        return native_WP_SetWarpRadius(getNativeEngine(), i);
    }

    public int startWarp(MPoint mpoint)
    {
        return native_WP_StartWarp(getNativeEngine(), mpoint);
    }

    public int warpTo(MPoint mpoint)
    {
        return native_WP_WarpTo(getNativeEngine(), mpoint);
    }
}
