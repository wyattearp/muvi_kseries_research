// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.view.View;
import com.arcsoft.mediaplus.dmc.RenderDevSelector;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity, PlayView

class this._cls0
    implements android.view.ener
{

    final PlayActivity this$0;

    public void onClick(View view)
    {
        while (view.getId() != 0x7f0900b4 && view.getId() != 0x7f0900b6 && isInfoViewShown() || System.currentTimeMillis() - PlayActivity.access$500(PlayActivity.this) <= 1000L) 
        {
            return;
        }
        PlayActivity.access$600(PlayActivity.this)[PlayActivity.access$700(PlayActivity.this)].stopHideControlTimer();
        view.getId();
        JVM INSTR lookupswitch 10: default 160
    //                   2131296306: 275
    //                   2131296392: 206
    //                   2131296393: 246
    //                   2131296394: 265
    //                   2131296395: 216
    //                   2131296396: 172
    //                   2131296398: 182
    //                   2131296432: 236
    //                   2131296436: 226
    //                   2131296438: 226;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L10
_L1:
        PlayActivity.access$502(PlayActivity.this, System.currentTimeMillis());
        return;
_L7:
        PlayActivity.access$800(PlayActivity.this);
        continue; /* Loop/switch isn't completed */
_L8:
        PlayActivity.access$1000(PlayActivity.this, PlayActivity.access$900(PlayActivity.this), mDataSource);
        continue; /* Loop/switch isn't completed */
_L3:
        PlayActivity.access$1100(PlayActivity.this);
        continue; /* Loop/switch isn't completed */
_L6:
        PlayActivity.access$1200(PlayActivity.this);
        continue; /* Loop/switch isn't completed */
_L10:
        PlayActivity.access$1300(PlayActivity.this);
        continue; /* Loop/switch isn't completed */
_L9:
        PlayActivity.access$1400(PlayActivity.this);
        continue; /* Loop/switch isn't completed */
_L4:
        PlayActivity.access$1502(PlayActivity.this, true);
        PlayActivity.access$1600(PlayActivity.this);
        continue; /* Loop/switch isn't completed */
_L5:
        downloadCurrentFile();
        continue; /* Loop/switch isn't completed */
_L2:
        if (PlayActivity.access$1700(PlayActivity.this) == null)
        {
            PlayActivity.access$1702(PlayActivity.this, new RenderDevSelector(PlayActivity.this));
        }
        RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL = isLocalContent();
        RenderDevSelector.CURRENT_PROVIDER_TYPE = com.arcsoft.mediaplus.dmc.R_TYPE.TYPE_FROM_LARGE_VIEW;
        PlayActivity.access$1700(PlayActivity.this).setDataSourceFilter(PlayActivity.access$1800(PlayActivity.this));
        PlayActivity.access$1700(PlayActivity.this).setCurrentIndex(PlayActivity.access$900(PlayActivity.this));
        PlayActivity.access$1700(PlayActivity.this).start();
        if (true) goto _L1; else goto _L11
_L11:
    }

    E()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
