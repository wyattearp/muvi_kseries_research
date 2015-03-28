// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package com.arcsoft.workshop.ui:
//            IManagerViewProcess, UIManagerConsole, UICropBar, UICropStyleBar, 
//            UIActionBar, UIEffectBar, EditorView

class this._cls0
    implements IManagerViewProcess
{

    final UIManagerConsole this$0;

    public void addView(View view)
    {
        if (view != null)
        {
            UIManagerConsole.access$600(UIManagerConsole.this).addView(view);
            if (UIManagerConsole.access$500(UIManagerConsole.this).getVisibility() == 0)
            {
                UIManagerConsole.access$600(UIManagerConsole.this).bringChildToFront(UIManagerConsole.access$500(UIManagerConsole.this));
            }
            if (UIManagerConsole.access$400(UIManagerConsole.this).getVisibility() == 0)
            {
                UIManagerConsole.access$600(UIManagerConsole.this).bringChildToFront(UIManagerConsole.access$400(UIManagerConsole.this));
            }
            if (UIManagerConsole.access$000(UIManagerConsole.this).getVisibility() == 0)
            {
                UIManagerConsole.access$600(UIManagerConsole.this).bringChildToFront(UIManagerConsole.access$000(UIManagerConsole.this));
            }
            if (UIManagerConsole.access$100(UIManagerConsole.this).getVisibility() == 0)
            {
                UIManagerConsole.access$600(UIManagerConsole.this).bringChildToFront(UIManagerConsole.access$100(UIManagerConsole.this));
            }
        }
    }

    public void addView(View view, android.widget.utParams utparams)
    {
        if (view != null)
        {
            UIManagerConsole.access$600(UIManagerConsole.this).addView(view, utparams);
            if (UIManagerConsole.access$500(UIManagerConsole.this).getVisibility() == 0)
            {
                UIManagerConsole.access$600(UIManagerConsole.this).bringChildToFront(UIManagerConsole.access$500(UIManagerConsole.this));
            }
            if (UIManagerConsole.access$400(UIManagerConsole.this).getVisibility() == 0)
            {
                UIManagerConsole.access$600(UIManagerConsole.this).bringChildToFront(UIManagerConsole.access$400(UIManagerConsole.this));
            }
            if (UIManagerConsole.access$000(UIManagerConsole.this).getVisibility() == 0)
            {
                UIManagerConsole.access$600(UIManagerConsole.this).bringChildToFront(UIManagerConsole.access$000(UIManagerConsole.this));
            }
            if (UIManagerConsole.access$100(UIManagerConsole.this).getVisibility() == 0)
            {
                UIManagerConsole.access$600(UIManagerConsole.this).bringChildToFront(UIManagerConsole.access$100(UIManagerConsole.this));
            }
        }
    }

    public void registerTouchDistribution(stribution stribution)
    {
        if (UIManagerConsole.access$300(UIManagerConsole.this) != null)
        {
            UIManagerConsole.access$300(UIManagerConsole.this).registerITouchDistribution(stribution);
        }
    }

    public void removeView(View view)
    {
        if (view != null)
        {
            UIManagerConsole.access$600(UIManagerConsole.this).removeView(view);
        }
    }

    stribution()
    {
        this$0 = UIManagerConsole.this;
        super();
    }
}
