// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import com.arcsoft.util.debug.Log;
import com.arcsoft.workshop.tools.EditorTools;
import com.arcsoft.workshop.tools.EditorToolsManager;
import com.arcsoft.workshop.utils.WorkShopUtils;
import powermobia.utils.DisplayContext;
import powermobia.utils.MBitmap;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop:
//            WorkShop, EditorEngineWrapper

private class <init> extends DisplayContext
{

    final WorkShop this$0;

    public int bitBlt(MBitmap mbitmap, MRect mrect)
    {
        Log.v("Miniature", "bitBlt");
        if (!isSurfaceDestory()) goto _L2; else goto _L1
_L1:
        return 0;
_L2:
        if (WorkShop.access$100(WorkShop.this) == null)
        {
            break; /* Loop/switch isn't completed */
        }
        com.arcsoft.workshop.tools.EditorToolsBase editortoolsbase = WorkShop.access$100(WorkShop.this).getEditorTools();
        if (editortoolsbase == null || !(editortoolsbase instanceof EditorTools))
        {
            break; /* Loop/switch isn't completed */
        }
        EditorTools editortools = (EditorTools)editortoolsbase;
        if (!editortools.isNeedToDraw())
        {
            continue; /* Loop/switch isn't completed */
        }
        String s = editortools.getFramePath();
        String s1 = editortools.getTexturePath();
        if (s != null || s1 != null)
        {
            MRect mrect1 = new MRect();
            WorkShop.access$200(WorkShop.this).getImgScreenRect(mrect1);
            int i = WorkShopUtils.addFrame(mbitmap, mrect1, s, s1);
            Log.v("Frame", (new StringBuilder()).append("addFrame : ").append(i).toString());
        }
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L3
_L3:
        updateView(mrect);
        return 0;
    }

    public int eraseBackground(MBitmap mbitmap, MRect mrect)
    {
        if (WorkShop.access$300(WorkShop.this) == 0)
        {
            WorkShop.access$600().fillColor(0x383837, new MRect(0, 0, WorkShop.access$400(WorkShop.this), WorkShop.access$500(WorkShop.this)), null, 100);
        } else
        if (WorkShop.access$300(WorkShop.this) == 1)
        {
            WorkShopUtils.copyBitmap(WorkShop.access$600(), WorkShop.access$700(WorkShop.this), new MPoint());
            return 0;
        }
        return 0;
    }

    public MBitmap lockOffScreenBuffer(int i, int j)
    {
        screenBufferNullProcess();
        return WorkShop.access$600();
    }

    public int unlockOffScreenBuffer(MBitmap mbitmap)
    {
        return 0;
    }

    private ()
    {
        this$0 = WorkShop.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
