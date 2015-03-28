// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.RenderManager;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity, DataSourcePlayList, RootPlayView

class this._cls0
    implements imationSetListener
{

    private sion mTempSession;
    final PlayActivity this$0;

    public void onAnimationEnd(int i, Animation animation, View view)
    {
    }

    public void onAnimationGroupCanceled(int i)
    {
        if (i == 0)
        {
            PlayActivity.access$2300(PlayActivity.this, 0);
        }
    }

    public boolean onAnimationGroupEnd(int i)
    {
        Log.d("PlayActivity", (new StringBuilder()).append("onAnimationGroupEnd -----> group = ").append(i).toString());
        if (i == 0)
        {
            mTempSession = PlayActivity.access$2000(PlayActivity.this);
        } else
        if (i == 1)
        {
            if (PlayActivity.access$600(PlayActivity.this)[PlayActivity.access$700(PlayActivity.this)] != null)
            {
                PlayActivity playactivity = PlayActivity.this;
                long l;
                if (mTempSession == null)
                {
                    l = 0L;
                } else
                {
                    l = mTempSession.position;
                }
                PlayActivity.access$2200(playactivity, l);
                return true;
            } else
            {
                release(true);
                finish();
                return true;
            }
        }
        return true;
    }

    public boolean onAnimationGroupPaused(int i)
    {
        Log.d("PlayActivity", "onAnimationGroupPaused");
        boolean flag;
        if (PlayActivity.access$700(PlayActivity.this) == 1)
        {
            PlayActivity.access$2500(PlayActivity.this).setPressed(true);
            flag = true;
        } else
        {
            flag = true;
            String s = Settings.instance().getDefaultDMRUDN();
            if (!DLNA.instance().getRenderManager().isRenderOnline(s))
            {
                flag = false;
            }
            int j = mPlayList.getCurrentIndex();
            android.net.Uri uri;
            if (j < 0)
            {
                uri = null;
            } else
            {
                uri = mPlayList.getUri(j);
            }
            if (uri == null)
            {
                flag = false;
            }
            if (flag)
            {
                PlayActivity.access$2600(PlayActivity.this).setPressed(true);
                return flag;
            }
        }
        return flag;
    }

    public void onAnimationGroupStart(int i)
    {
        while (i == 0 || i != 1) 
        {
            return;
        }
    }

    public void onAnimationStart(int i, Animation animation, View view)
    {
    }

    public boolean onCreateAnimation()
    {
        boolean flag = true;
        String s = Settings.instance().getDefaultDMRUDN();
        if (!DLNA.instance().getRenderManager().isRenderOnline(s))
        {
            Toast.makeText(PlayActivity.this, 0x7f0b0058, flag).show();
        }
        int i = getMediaType();
        RootPlayView rootplayview = PlayActivity.access$2400(PlayActivity.this);
        PlayView playview = PlayActivity.access$600(PlayActivity.this)[PlayActivity.access$700(PlayActivity.this)];
        PlayView playview1 = PlayActivity.access$600(PlayActivity.this)[1 ^ PlayActivity.access$700(PlayActivity.this)];
        if (i == 2)
        {
            flag = false;
        }
        return rootplayview.initAnimation(playview, playview1, flag);
    }

    public boolean onDispatchTouchEvent()
    {
        return false;
    }

    public void onPrepareAnimationGroup(int i)
    {
        if (i == 0)
        {
            PlayActivity.access$1900(PlayActivity.this);
        } else
        if (i == 1)
        {
            PlayActivity.access$702(PlayActivity.this, 1 ^ PlayActivity.access$700(PlayActivity.this));
            PlayActivity.access$2100(PlayActivity.this);
            return;
        }
    }

    sion()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
