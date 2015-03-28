// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.tools;

import com.arcsoft.workshop.EditorEngineWrapper;
import com.arcsoft.workshop.OnCommandListener;
import com.arcsoft.workshop.WorkShop;

public class EditorToolsBase
    implements OnCommandListener
{

    protected EditorEngineWrapper mEditorEngineWrapper;
    protected com.arcsoft.workshop.WorkShop.IImageNotifyChange mImageNotify;
    protected com.arcsoft.workshop.ui.UIManagerConsole.IUIMethodForTools mUIMethodForTools;
    protected WorkShop mWorkShop;

    public EditorToolsBase(WorkShop workshop)
    {
        mWorkShop = null;
        mEditorEngineWrapper = null;
        mUIMethodForTools = null;
        mImageNotify = null;
        mWorkShop = workshop;
        mEditorEngineWrapper = mWorkShop.getEditorEngineWrapper();
    }

    public void init()
    {
    }

    public int onCommand(int i, Object obj, Object obj1)
    {
        return 0;
    }

    public void setIUIMethodForTools(com.arcsoft.workshop.ui.UIManagerConsole.IUIMethodForTools iuimethodfortools)
    {
        mUIMethodForTools = iuimethodfortools;
    }

    public void setImageNotifyChange(com.arcsoft.workshop.WorkShop.IImageNotifyChange iimagenotifychange)
    {
        mImageNotify = iimagenotifychange;
    }

    public void uninit()
    {
        mEditorEngineWrapper = null;
    }
}
