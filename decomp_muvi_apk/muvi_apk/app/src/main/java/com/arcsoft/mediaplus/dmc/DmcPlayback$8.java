// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.view.View;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcPlayback, DmcPlayerEx, DmcPlayingDataProvider

class this._cls0
    implements android.view.stener
{

    final DmcPlayback this$0;

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131296345 2131296352: default 52
    //                   2131296345 224
    //                   2131296346 97
    //                   2131296347 253
    //                   2131296348 89
    //                   2131296349 52
    //                   2131296350 52
    //                   2131296351 52
    //                   2131296352 53;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L1 _L1 _L6
_L1:
        return;
_L6:
        DmcPlayback dmcplayback = DmcPlayback.this;
        boolean flag;
        if (DmcPlayback.access$1500(DmcPlayback.this) != ATUS.TV_PLAY_STARTUS_PLAYING)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        dmcplayback.ctlTv(flag);
        return;
_L5:
        finish();
        return;
_L3:
        if (DmcPlayback.access$1000(DmcPlayback.this).getStatus() == com.arcsoft.mediaplus.playengine..STATUS_STOPPED || DmcPlayback.access$1000(DmcPlayback.this).getStatus() == com.arcsoft.mediaplus.playengine..STATUS_IDLE || DmcPlayback.access$1000(DmcPlayback.this).getStatus() == com.arcsoft.mediaplus.playengine..STATUS_STOPPING)
        {
            DmcPlayback.access$1000(DmcPlayback.this).play(DmcPlayback.access$900(DmcPlayback.this).getCurrentIndex(), 0L, com.arcsoft.mediaplus.playengine..EFFECT.EFFECT_START_PLAY_FADE);
            return;
        }
        if (DmcPlayback.access$1000(DmcPlayback.this).getStatus() == com.arcsoft.mediaplus.playengine..STATUS_PAUSED)
        {
            DmcPlayback.access$1000(DmcPlayback.this).resume();
            return;
        }
        if (DmcPlayback.access$1000(DmcPlayback.this).canPause())
        {
            DmcPlayback.access$1000(DmcPlayback.this).pause();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (DmcPlayback.access$1000(DmcPlayback.this) != null)
        {
            DmcPlayback.access$800(DmcPlayback.this);
            DmcPlayback.access$1000(DmcPlayback.this).playprev();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (DmcPlayback.access$1000(DmcPlayback.this) != null)
        {
            DmcPlayback.access$800(DmcPlayback.this);
            DmcPlayback.access$1000(DmcPlayback.this).playnext();
            return;
        }
        if (true) goto _L1; else goto _L7
_L7:
    }

    yEffect.EFFECT()
    {
        this$0 = DmcPlayback.this;
        super();
    }
}
