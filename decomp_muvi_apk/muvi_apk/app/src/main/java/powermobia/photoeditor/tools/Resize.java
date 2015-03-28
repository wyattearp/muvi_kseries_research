// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;


// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class Resize extends ToolBase
{

    private static final int TOOLID_RESIZE = 0x90840338;

    public Resize()
    {
        mToolId = 0x90840338;
    }

    private native int native_RS_Resize(int i, int j, int k, boolean flag);

    public int resize(int i, int j, boolean flag)
    {
        return native_RS_Resize(getNativeEngine(), i, j, flag);
    }
}
