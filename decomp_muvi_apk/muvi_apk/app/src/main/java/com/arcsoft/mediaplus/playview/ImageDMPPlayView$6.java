// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import com.arcsoft.adk.image.PhotoViewer;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            ImageDMPPlayView, DataSourcePlayList

class this._cls0
    implements com.arcsoft.mediaplus.datasource.hangeListener
{

    final ImageDMPPlayView this$0;

    public void onCountChanged(int i, int j)
    {
        Log.v("ImageDMPPlayView", (new StringBuilder()).append(" onCountChanged, newCount = ").append(i).append(", oldCount = ").append(j).toString());
        if (mPlayList == null || i <= 0 || i == j) goto _L2; else goto _L1
_L1:
        int k = 0;
_L8:
        if (k >= i) goto _L4; else goto _L3
_L3:
        if (ImageDMPPlayView.access$1300(ImageDMPPlayView.this) != mPlayList.getId(k)) goto _L6; else goto _L5
_L5:
        Log.d("FENG", (new StringBuilder()).append("FENG ******************* index: ").append(k).append(", mCurID: ").append(ImageDMPPlayView.access$1300(ImageDMPPlayView.this)).toString());
        ImageDMPPlayView.access$800(ImageDMPPlayView.this).setCurrentIndex(k);
_L4:
        ImageDMPPlayView.access$800(ImageDMPPlayView.this).refresh();
_L2:
        if (ImageDMPPlayView.access$1400(ImageDMPPlayView.this) != null)
        {
            ImageDMPPlayView.access$1400(ImageDMPPlayView.this).sendEmptyMessage(1);
        }
        return;
_L6:
        k++;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void onDataChanged(int i, Property property)
    {
        if (property == Property.PROP_IMAGE_FILEPATH)
        {
            ImageDMPPlayView.access$800(ImageDMPPlayView.this).editItem(i);
        } else
        if (property == Property.PROP_STORAGE_FULL && !ImageDMPPlayView.access$1500(ImageDMPPlayView.this))
        {
            Toast.makeText(mContext, mContext.getString(0x7f0b0198), 1).show();
            ImageDMPPlayView.access$1502(ImageDMPPlayView.this, true);
            return;
        }
    }

    aChangeListener()
    {
        this$0 = ImageDMPPlayView.this;
        super();
    }
}
