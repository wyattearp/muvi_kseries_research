// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.graphics.drawable.LevelListDrawable;
import android.view.View;
import android.widget.ImageButton;
import com.arcsoft.workshop.OnCommandListener;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UIActionBar, UIMiniatureLineView, IManagerViewProcess, UICallBack

class this._cls0
    implements android.view.stener
{

    final UIActionBar this$0;

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131296644 2131296647: default 36
    //                   2131296644 37
    //                   2131296645 476
    //                   2131296646 54
    //                   2131296647 611;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return;
_L2:
        UIActionBar.access$000(UIActionBar.this).onCommand(4, null, null);
        return;
_L4:
        if (UIActionBar.access$100(UIActionBar.this) == null)
        {
            UIActionBar.access$102(UIActionBar.this, new UIMiniatureLineView(getContext()));
            MRect mrect = new MRect();
            UIActionBar.access$000(UIActionBar.this).onCommand(18, mrect, null);
            android.widget..LayoutParams layoutparams = new android.widget..LayoutParams(mrect.width(), mrect.height());
            layoutparams.leftMargin = mrect.left;
            layoutparams.topMargin = mrect.top;
            UIActionBar.access$200(UIActionBar.this).addView(UIActionBar.access$100(UIActionBar.this), layoutparams);
            UIActionBar.access$200(UIActionBar.this).registerTouchDistribution(UIActionBar.access$100(UIActionBar.this));
            UIActionBar.access$100(UIActionBar.this).setOnCommandListener(UIActionBar.access$000(UIActionBar.this));
            UIActionBar.access$100(UIActionBar.this).setImageRect(mrect);
            UIActionBar.access$100(UIActionBar.this).setDrawType(eView.DrawType.NONE);
        }
        if (UIActionBar.access$300(UIActionBar.this) == 0)
        {
            UIActionBar.access$302(UIActionBar.this, 2);
            ((LevelListDrawable)UIActionBar.access$400(UIActionBar.this).getBackground()).setLevel(2);
            UIActionBar.access$100(UIActionBar.this).setDrawType(eView.DrawType.RECT);
            return;
        }
        if (UIActionBar.access$300(UIActionBar.this) == 2)
        {
            UIActionBar.access$302(UIActionBar.this, 1);
            ((LevelListDrawable)UIActionBar.access$400(UIActionBar.this).getBackground()).setLevel(1);
            UIActionBar.access$100(UIActionBar.this).setDrawType(eView.DrawType.CIRCLE);
            return;
        }
        if (UIActionBar.access$300(UIActionBar.this) == 1)
        {
            UIActionBar.access$302(UIActionBar.this, 0);
            ((LevelListDrawable)UIActionBar.access$400(UIActionBar.this).getBackground()).setLevel(0);
            UIActionBar.access$100(UIActionBar.this).setDrawType(eView.DrawType.NONE);
            UIActionBar.access$100(UIActionBar.this).uninit();
            UIActionBar.access$200(UIActionBar.this).removeView(UIActionBar.access$100(UIActionBar.this));
            UIActionBar.access$200(UIActionBar.this).registerTouchDistribution(null);
            UIActionBar.access$102(UIActionBar.this, null);
            UIActionBar.access$000(UIActionBar.this).onCommand(0, Integer.valueOf(1), null);
            UIActionBar.access$000(UIActionBar.this).onCommand(21, null, null);
            return;
        }
          goto _L1
_L3:
        UIActionBar.access$500(UIActionBar.this).callback();
        if (UIActionBar.access$600(UIActionBar.this))
        {
            UIActionBar.access$700(UIActionBar.this).setSelected(false);
            UIActionBar.access$602(UIActionBar.this, false);
            UIActionBar.access$000(UIActionBar.this).onCommand(0, Integer.valueOf(1), null);
            UIActionBar.access$000(UIActionBar.this).onCommand(19, null, null);
            return;
        } else
        {
            UIActionBar.access$700(UIActionBar.this).setSelected(true);
            UIActionBar.access$602(UIActionBar.this, true);
            UIActionBar.access$000(UIActionBar.this).onCommand(0, Integer.valueOf(1), null);
            UIActionBar.access$000(UIActionBar.this).onCommand(1, null, null);
            return;
        }
_L5:
        UIActionBar.access$800(UIActionBar.this, view);
        return;
    }

    wable()
    {
        this$0 = UIActionBar.this;
        super();
    }
}
