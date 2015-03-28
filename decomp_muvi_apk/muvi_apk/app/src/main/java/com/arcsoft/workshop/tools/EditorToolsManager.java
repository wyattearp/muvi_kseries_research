// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.tools;

import com.arcsoft.workshop.WorkShop;

// Referenced classes of package com.arcsoft.workshop.tools:
//            EditorToolsBase, EditorTools

public class EditorToolsManager
{

    public static final int EDITOR_TOOLS = 1;
    public static final int EDITOR_TOOLS_NONE;
    private EditorToolsBase mEditorTools;
    private int mEditorToolsIdCur;
    private WorkShop mWorkShop;

    public EditorToolsManager(WorkShop workshop)
    {
        mWorkShop = null;
        mEditorTools = null;
        mEditorToolsIdCur = 0;
        mWorkShop = workshop;
    }

    public EditorToolsBase getEditorTools()
    {
        return mEditorTools;
    }

    public int getEditorToolsIdCur()
    {
        return mEditorToolsIdCur;
    }

    public int selectTools(int i)
    {
        if (i == mEditorToolsIdCur)
        {
            return 0;
        }
        if (mEditorTools != null)
        {
            mEditorTools.uninit();
        }
        i;
        JVM INSTR tableswitch 0 1: default 48
    //                   0 97
    //                   1 105;
           goto _L1 _L2 _L3
_L1:
        if (mEditorTools != null)
        {
            mEditorTools.init();
            mEditorTools.setIUIMethodForTools(mWorkShop.getIUIMethodForTools());
            mEditorTools.setImageNotifyChange(mWorkShop.getImageNotifyChange());
        }
        mEditorToolsIdCur = i;
        return 0;
_L2:
        mEditorTools = null;
        continue; /* Loop/switch isn't completed */
_L3:
        mEditorTools = new EditorTools(mWorkShop);
        if (true) goto _L1; else goto _L4
_L4:
    }
}
