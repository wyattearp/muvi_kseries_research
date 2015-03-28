// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import com.arcsoft.adk.image.IFileList;
import com.arcsoft.mediaplus.datasource.Property;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            ImageDMPPlayView, DataSourcePlayList

class this._cls0
    implements com.arcsoft.adk.image.ewerListener
{

    final ImageDMPPlayView this$0;

    public boolean isPreparingFilePath(int i)
    {
        return mPlayList.getCurrentFilePathByIndex(i).startsWith("http://");
    }

    public boolean isSupportZoom(int i)
    {
        return ImageDMPPlayView.access$700(ImageDMPPlayView.this).getFilePath(i) != null;
    }

    public boolean isVideo(int i)
    {
        return ImageDMPPlayView.access$600(ImageDMPPlayView.this, i);
    }

    public void onClick()
    {
        resetHideControlTimer(true);
    }

    public void onCurrentIndexChanged(int i)
    {
        if (mPlayList != null)
        {
            mPlayList.setCurrentIndex(i);
        }
        ImageDMPPlayView.access$300(ImageDMPPlayView.this, i, false);
    }

    public void onModeChanged(com.arcsoft.adk.image.ayView._cls2 _pcls2)
    {
        if (_pcls2 == com.arcsoft.adk.image.rmal)
        {
            ImageDMPPlayView.access$402(ImageDMPPlayView.this, com.arcsoft.adk.image.rmal);
        } else
        if (_pcls2 == com.arcsoft.adk.image.om)
        {
            ImageDMPPlayView.access$402(ImageDMPPlayView.this, com.arcsoft.adk.image.om);
            return;
        }
    }

    public void onPrefetch(int i, int j)
    {
        if (mDataSourceController != null)
        {
            com.arcsoft.mediaplus.datasource.ller ller = mDataSourceController;
            Property aproperty[] = new Property[1];
            aproperty[0] = Property.PROP_IMAGE_FILEPATH;
            ller.prefetch(i, j, aproperty);
        }
    }

    public void onPrefetchEx(int ai[])
    {
        if (mDataSourceController != null)
        {
            com.arcsoft.mediaplus.datasource.ller ller = mDataSourceController;
            Property aproperty[] = new Property[1];
            aproperty[0] = Property.PROP_IMAGE_FILEPATH;
            ller.prefetchEx(ai, aproperty);
        }
    }

    public void onSliding(boolean flag)
    {
        ImageDMPPlayView.access$500(ImageDMPPlayView.this, flag);
    }

    public void resetControlTimer()
    {
        resetHideControlTimerEx();
    }

    roller()
    {
        this$0 = ImageDMPPlayView.this;
        super();
    }
}
