// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0
    implements com.arcsoft.mediaplus.setting.angedListener
{

    final MediaPlusActivity this$0;

    public void OnDefaultDownloadDestinationChanged(String s)
    {
    }

    public void OnDefaultRenderChanged(String s)
    {
    }

    public void OnDefaultServerChanged(String s)
    {
        MediaPlusActivity.access$1800(MediaPlusActivity.this, s);
        if (isRemote())
        {
            showLoadingDialog();
            RemoteDBMgr.instance().setContentParam(false);
            if (MediaPlusActivity.access$1900(MediaPlusActivity.this) != null && !MediaPlusActivity.access$1900(MediaPlusActivity.this).equals(s))
            {
                if (isDigaActivity() && isRemote() && mCurrentType == 4)
                {
                    RemoteDBMgr.instance().setCurrentServer(s, "AV", true);
                } else
                {
                    RemoteDBMgr.instance().setCurrentServer(s, null, true);
                }
            }
        }
        MediaPlusActivity.access$2000(MediaPlusActivity.this, s);
    }

    public void onBrowseModeChanged(boolean flag)
    {
        if (isRemote())
        {
            RemoteDBMgr.instance().setBrowseByFolder(flag);
        }
    }

    public void onSortModeChanged(boolean flag)
    {
    }

    public void onTVStreamingResolutionChange(boolean flag)
    {
    }

    BMgr()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
