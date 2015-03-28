// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.DialogInterface;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity, PlayView

class this._cls0
    implements android.content.OnClickListener
{

    final PlayActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        PlayActivity.access$600(PlayActivity.this)[PlayActivity.access$700(PlayActivity.this)].setCurrentAudioTrackIndex(i);
        dialoginterface.cancel();
    }

    ()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
