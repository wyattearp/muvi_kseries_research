// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            ImageDMCPlayView

class this._cls0
    implements com.arcsoft.mediaplus.playengine.ener
{

    final ImageDMCPlayView this$0;

    public void onBuffering()
    {
        mRootView.findViewById(0x7f090083).setVisibility(0);
    }

    public void onCompleted()
    {
        mRootView.findViewById(0x7f090083).setVisibility(8);
    }

    public void onError(com.arcsoft.mediaplus.playengine. )
    {
        switch (.SwitchMap.com.arcsoft.mediaplus.playengine.IPlayer.PlayerError[.ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            requestQuit();
            return;

        case 2: // '\002'
            Toast.makeText(mContext, 0x7f0b005d, 0).show();
            return;

        case 3: // '\003'
        case 4: // '\004'
            Toast.makeText(mContext, 0x7f0b005e, 0).show();
            return;

        case 5: // '\005'
            Toast.makeText(mContext, 0x7f0b005e, 0).show();
            break;
        }
        requestQuit();
    }

    public void onMute(boolean flag)
    {
    }

    public void onPaused()
    {
        mRootView.findViewById(0x7f090083).setVisibility(8);
    }

    public void onPlayIndexChanged(int i)
    {
        if (ImageDMCPlayView.access$300(ImageDMCPlayView.this) == null)
        {
            return;
        }
        if (ImageDMCPlayView.access$400(ImageDMCPlayView.this) != null)
        {
            ImageDMCPlayView.access$500(ImageDMCPlayView.this, i);
        }
        ImageDMCPlayView.access$600(ImageDMCPlayView.this, i);
        updateBtnsEnableStatus(false);
    }

    public void onPlayStarted()
    {
        mRootView.findViewById(0x7f090083).setVisibility(8);
        ImageDMCPlayView.access$200(ImageDMCPlayView.this).sendEmptyMessageDelayed(1000, ImageDMCPlayView.access$700(ImageDMCPlayView.this));
    }

    public void onProgressChanged(long l, long l1)
    {
    }

    public void onSeeked()
    {
    }

    public void onStopped()
    {
        mRootView.findViewById(0x7f090083).setVisibility(8);
    }

    public void onVolumeChanged(int i)
    {
    }

    or()
    {
        this$0 = ImageDMCPlayView.this;
        super();
    }
}
