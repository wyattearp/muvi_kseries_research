// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.view.View;
import android.widget.HorizontalScrollView;
import com.arcsoft.workshop.OnCommandListener;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UIEffectBar, UICallBack

class this._cls0
    implements android.view.stener
{

    final UIEffectBar this$0;

    public void onClick(View view)
    {
        UIEffectBar.access$000(UIEffectBar.this).callback();
        if (UIEffectBar.access$100(UIEffectBar.this) == null || UIEffectBar.access$100(UIEffectBar.this).getId() != view.getId()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (UIEffectBar.access$100(UIEffectBar.this) != null)
        {
            UIEffectBar.access$100(UIEffectBar.this).setSelected(false);
        }
        UIEffectBar.access$200(UIEffectBar.this).onCommand(0, Integer.valueOf(1), null);
        view.getId();
        JVM INSTR tableswitch 2131296656 2131296702: default 288
    //                   2131296656 381
    //                   2131296657 288
    //                   2131296658 461
    //                   2131296659 288
    //                   2131296660 433
    //                   2131296661 288
    //                   2131296662 405
    //                   2131296663 288
    //                   2131296664 489
    //                   2131296665 288
    //                   2131296666 517
    //                   2131296667 288
    //                   2131296668 599
    //                   2131296669 288
    //                   2131296670 545
    //                   2131296671 288
    //                   2131296672 572
    //                   2131296673 288
    //                   2131296674 626
    //                   2131296675 288
    //                   2131296676 653
    //                   2131296677 288
    //                   2131296678 677
    //                   2131296679 288
    //                   2131296680 701
    //                   2131296681 288
    //                   2131296682 779
    //                   2131296683 288
    //                   2131296684 752
    //                   2131296685 288
    //                   2131296686 725
    //                   2131296687 288
    //                   2131296688 806
    //                   2131296689 288
    //                   2131296690 833
    //                   2131296691 288
    //                   2131296692 860
    //                   2131296693 288
    //                   2131296694 884
    //                   2131296695 288
    //                   2131296696 908
    //                   2131296697 288
    //                   2131296698 932
    //                   2131296699 288
    //                   2131296700 956
    //                   2131296701 288
    //                   2131296702 980;
           goto _L3 _L4 _L3 _L5 _L3 _L6 _L3 _L7 _L3 _L8 _L3 _L9 _L3 _L10 _L3 _L11 _L3 _L12 _L3 _L13 _L3 _L14 _L3 _L15 _L3 _L16 _L3 _L17 _L3 _L18 _L3 _L19 _L3 _L20 _L3 _L21 _L3 _L22 _L3 _L23 _L3 _L24 _L3 _L25 _L3 _L26 _L3 _L27
_L3:
        int j;
        int l;
        UIEffectBar.access$102(UIEffectBar.this, view);
        UIEffectBar.access$100(UIEffectBar.this).setSelected(true);
        int ai[] = new int[2];
        UIEffectBar.access$100(UIEffectBar.this).getLocationOnScreen(ai);
        int i = UIEffectBar.access$100(UIEffectBar.this).getWidth();
        j = UIEffectBar.access$300(UIEffectBar.this).getWidth();
        int k = ai[0];
        l = k + i;
        if (k < 0)
        {
            UIEffectBar.access$300(UIEffectBar.this).smoothScrollBy(k, 0);
            return;
        }
        break; /* Loop/switch isn't completed */
_L4:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(9, Integer.valueOf(0xaad300), null);
        continue; /* Loop/switch isn't completed */
_L7:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(9, Integer.valueOf(0xaad32c), Integer.valueOf(0xaad4e0));
        continue; /* Loop/switch isn't completed */
_L6:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(9, Integer.valueOf(0xaad32c), Integer.valueOf(0xaad4e1));
        continue; /* Loop/switch isn't completed */
_L5:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(9, Integer.valueOf(0xaad32c), Integer.valueOf(0xaad4e2));
        continue; /* Loop/switch isn't completed */
_L8:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(9, Integer.valueOf(0xaad32c), Integer.valueOf(0xaad4e3));
        continue; /* Loop/switch isn't completed */
_L9:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(9, Integer.valueOf(0xaad32c), Integer.valueOf(0xaad4e4));
        continue; /* Loop/switch isn't completed */
_L11:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(23), Integer.valueOf(1));
        continue; /* Loop/switch isn't completed */
_L12:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(23), Integer.valueOf(2));
        continue; /* Loop/switch isn't completed */
_L10:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(23), Integer.valueOf(0));
        continue; /* Loop/switch isn't completed */
_L13:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(23), Integer.valueOf(3));
        continue; /* Loop/switch isn't completed */
_L14:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(35), null);
        continue; /* Loop/switch isn't completed */
_L15:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(24), null);
        continue; /* Loop/switch isn't completed */
_L16:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(25), null);
        continue; /* Loop/switch isn't completed */
_L19:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(36), Integer.valueOf(1));
        continue; /* Loop/switch isn't completed */
_L18:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(36), Integer.valueOf(0));
        continue; /* Loop/switch isn't completed */
_L17:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(36), Integer.valueOf(2));
        continue; /* Loop/switch isn't completed */
_L20:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(36), Integer.valueOf(3));
        continue; /* Loop/switch isn't completed */
_L21:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(36), Integer.valueOf(4));
        continue; /* Loop/switch isn't completed */
_L22:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(33), null);
        continue; /* Loop/switch isn't completed */
_L23:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(9, Integer.valueOf(0xaad301), null);
        continue; /* Loop/switch isn't completed */
_L24:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(9, Integer.valueOf(0xaad31f), null);
        continue; /* Loop/switch isn't completed */
_L25:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(7, Integer.valueOf(7), null);
        continue; /* Loop/switch isn't completed */
_L26:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(34), null);
        continue; /* Loop/switch isn't completed */
_L27:
        UIEffectBar.access$200(UIEffectBar.this).onCommand(22, Integer.valueOf(37), null);
        if (true) goto _L3; else goto _L28
_L28:
        if (l <= j) goto _L1; else goto _L29
_L29:
        UIEffectBar.access$300(UIEffectBar.this).smoothScrollBy(l - j, 0);
        return;
    }

    ()
    {
        this$0 = UIEffectBar.this;
        super();
    }
}
