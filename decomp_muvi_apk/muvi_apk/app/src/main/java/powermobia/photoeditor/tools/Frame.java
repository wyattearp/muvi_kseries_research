// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.utils.MStream;

// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class Frame extends ToolBase
{

    private static final int DATASOURCE_FILE = 1;
    private static final int TOOLID_FRAME = 0x90840331;

    public Frame()
    {
        mToolId = 0x90840331;
    }

    private native int native_FM_SetFillColor(int i, int j);

    private native int native_FM_SetFrame(int i, int j, int k, Object obj, int l, int i1, Object obj1, 
            int j1);

    public int setFillColor(int i)
    {
        return native_FM_SetFillColor(getNativeEngine(), i);
    }

    public int setFrame(int i, String s, int j, String s1, int k)
    {
        return native_FM_SetFrame(getNativeEngine(), 1, i, s, 1, j, s1, k);
    }

    public int setFrame(int i, MStream mstream, int j, MStream mstream1, int k)
    {
        return native_FM_SetFrame(getNativeEngine(), 1, i, mstream, 1, j, mstream1, k);
    }
}
