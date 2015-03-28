// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.photoeditor.EditorEngine;

public class ToolBase
{

    protected EditorEngine mEngine;
    protected int mToolId;

    protected ToolBase()
    {
        mEngine = null;
        mToolId = 0;
    }

    protected int getNativeEngine()
    {
        return mEngine.getNativeEngine();
    }

    public int getToolId()
    {
        return mToolId;
    }

    public void setEngine(EditorEngine editorengine)
    {
        mEngine = editorengine;
    }

    protected void setToolId(int i)
    {
        mToolId = i;
    }
}
