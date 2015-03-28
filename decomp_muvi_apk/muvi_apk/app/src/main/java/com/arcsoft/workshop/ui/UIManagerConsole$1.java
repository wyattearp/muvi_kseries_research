// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.arcsoft.workshop.WorkShop;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UIManagerConsole, UIActionBar, UIEffectBar

class this._cls0
    implements IMethodForTools
{

    final UIManagerConsole this$0;

    public void autofixUIProcess(boolean flag)
    {
        if (UIManagerConsole.access$000(UIManagerConsole.this) != null)
        {
            UIManagerConsole.access$000(UIManagerConsole.this).autofixUIProcess(flag);
        }
    }

    public void effectUIProcess(boolean flag, int i, int j, int k)
    {
        if (UIManagerConsole.access$100(UIManagerConsole.this) != null)
        {
            UIManagerConsole.access$100(UIManagerConsole.this).effectUIProcess(flag, i, j, k);
        }
    }

    public void errorForReset()
    {
        if (UIManagerConsole.access$000(UIManagerConsole.this) != null)
        {
            UIManagerConsole.access$000(UIManagerConsole.this).autofixUIProcess(false);
        }
        if (UIManagerConsole.access$000(UIManagerConsole.this) != null)
        {
            UIManagerConsole.access$000(UIManagerConsole.this).miniatureUIProcess(-1);
        }
        if (UIManagerConsole.access$100(UIManagerConsole.this) != null)
        {
            UIManagerConsole.access$100(UIManagerConsole.this).effectUIProcess(true, 0, 0, 0);
        }
        Toast toast = Toast.makeText(UIManagerConsole.access$200(UIManagerConsole.this), 0x7f0b0156, 1);
        int i = UIManagerConsole.access$200(UIManagerConsole.this).getResources().getInteger(0x7f0a001b);
        float f = UIManagerConsole.access$200(UIManagerConsole.this).getResources().getDisplayMetrics().density;
        toast.setGravity(1, 0, (int)((float)i + 10F * f));
        toast.show();
    }

    public void facebeautifyFailed()
    {
    }

    public void miniatureUIProcess(int i)
    {
        if (UIManagerConsole.access$000(UIManagerConsole.this) != null)
        {
            UIManagerConsole.access$000(UIManagerConsole.this).miniatureUIProcess(i);
        }
    }

    public void resetUI()
    {
        if (UIManagerConsole.access$000(UIManagerConsole.this) != null)
        {
            UIManagerConsole.access$000(UIManagerConsole.this).autofixUIProcess(false);
        }
        if (UIManagerConsole.access$000(UIManagerConsole.this) != null)
        {
            UIManagerConsole.access$000(UIManagerConsole.this).miniatureUIProcess(-1);
        }
        if (UIManagerConsole.access$100(UIManagerConsole.this) != null)
        {
            UIManagerConsole.access$100(UIManagerConsole.this).effectUIProcess(true, 0, 0, 0);
        }
        Toast toast = Toast.makeText(UIManagerConsole.access$200(UIManagerConsole.this), 0x7f0b0157, 1);
        int i = UIManagerConsole.access$200(UIManagerConsole.this).getResources().getInteger(0x7f0a001b);
        float f = UIManagerConsole.access$200(UIManagerConsole.this).getResources().getDisplayMetrics().density;
        toast.setGravity(1, 0, (int)((float)i + 10F * f));
        toast.show();
    }

    IMethodForTools()
    {
        this$0 = UIManagerConsole.this;
        super();
    }
}
