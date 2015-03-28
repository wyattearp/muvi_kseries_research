// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;


// Referenced classes of package com.arcsoft.workshop.ui:
//            UIManagerConsole, EditorView, UICropStyleBar, UICropBar, 
//            UIActionBar, UIEffectBar

class this._cls0
    implements chEdit
{

    final UIManagerConsole this$0;

    public void switchToEdit()
    {
        if (UIManagerConsole.access$300(UIManagerConsole.this) != null)
        {
            UIManagerConsole.access$300(UIManagerConsole.this).setPanZoomEnabeState(false);
        }
        UIManagerConsole.access$400(UIManagerConsole.this).uninit();
        UIManagerConsole.access$500(UIManagerConsole.this).setVisibility(8);
        UIManagerConsole.access$400(UIManagerConsole.this).setVisibility(8);
        UIManagerConsole.access$000(UIManagerConsole.this).setVisibility(0);
        UIManagerConsole.access$100(UIManagerConsole.this).setVisibility(0);
    }

    chEdit()
    {
        this$0 = UIManagerConsole.this;
        super();
    }
}
