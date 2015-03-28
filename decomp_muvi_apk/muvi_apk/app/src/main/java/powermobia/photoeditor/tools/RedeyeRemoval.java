// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.utils.MRect;

// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class RedeyeRemoval extends ToolBase
{

    private static final int TOOLID_REDEYE = 0x90840336;

    public RedeyeRemoval()
    {
        mToolId = 0x90840336;
    }

    private native int native_RE_AutoFix(int i);

    private native int native_RE_Fix(int i, MRect mrect);

    public int autoFix()
    {
        return native_RE_AutoFix(getNativeEngine());
    }

    public int fix(MRect mrect)
    {
        return native_RE_Fix(getNativeEngine(), mrect);
    }
}
