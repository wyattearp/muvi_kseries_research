// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.widget.TextView;
import com.arcsoft.videotrim.Utils.ThumbManagerList;
import com.arcsoft.videotrim.Utils.UtilFunc;

// Referenced classes of package com.arcsoft.videotrim:
//            videoTrimActivity

class this._cls0
    implements rListener
{

    final videoTrimActivity this$0;

    public void notifyCurScaleLevelChanged()
    {
        videoTrimActivity.access$1500(videoTrimActivity.this);
    }

    public void notifyScaleLevelChanged(int i, int j)
    {
        videoTrimActivity.access$1500(videoTrimActivity.this);
    }

    public void notifySeekToValue(int i)
    {
        videoTrimActivity.access$1300(videoTrimActivity.this).setText(UtilFunc.TransTimeToString(i, 0));
        if (i >= videoTrimActivity.access$1000(videoTrimActivity.this)) goto _L2; else goto _L1
_L1:
        boolean flag;
        boolean flag1;
        int k1 = videoTrimActivity.access$1100(videoTrimActivity.this) - i;
        flag1 = false;
        flag = false;
        if (k1 >= 1000)
        {
            flag = true;
        }
_L4:
        videoTrimActivity.access$1200(videoTrimActivity.this, flag, flag1);
        videoTrimActivity.access$1400(videoTrimActivity.this, i);
        return;
_L2:
        if (i > videoTrimActivity.access$1100(videoTrimActivity.this))
        {
            int j1 = i - videoTrimActivity.access$1000(videoTrimActivity.this);
            flag1 = false;
            flag = false;
            if (j1 >= 1000)
            {
                flag1 = true;
                flag = false;
            }
        } else
        {
            int j = videoTrimActivity.access$1000(videoTrimActivity.this);
            flag = false;
            if (i != j)
            {
                int i1 = videoTrimActivity.access$1100(videoTrimActivity.this) - i;
                flag = false;
                if (i1 >= 1000)
                {
                    flag = true;
                }
            }
            int k = videoTrimActivity.access$1100(videoTrimActivity.this);
            flag1 = false;
            if (i != k)
            {
                int l = i - videoTrimActivity.access$1000(videoTrimActivity.this);
                flag1 = false;
                if (l >= 1000)
                {
                    flag1 = true;
                }
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void notifyValueChanged(boolean flag, int i)
    {
        videoTrimActivity.access$402(videoTrimActivity.this, false);
        if (flag)
        {
            videoTrimActivity.access$1002(videoTrimActivity.this, i);
        } else
        {
            videoTrimActivity.access$1102(videoTrimActivity.this, i);
        }
        videoTrimActivity.access$1200(videoTrimActivity.this, false, false);
        videoTrimActivity.access$1300(videoTrimActivity.this).setText(UtilFunc.TransTimeToString(i, 0));
        videoTrimActivity.access$1400(videoTrimActivity.this, i);
    }

    public void onTrimThumbManagerList(ThumbManagerList thumbmanagerlist)
    {
        videoTrimActivity.access$1602(videoTrimActivity.this, thumbmanagerlist);
    }

    st()
    {
        this$0 = videoTrimActivity.this;
        super();
    }
}
