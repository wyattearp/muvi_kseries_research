// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.view.ViewGroup;
import com.arcsoft.mediaplus.datasource.DMCDataSource;
import com.arcsoft.mediaplus.datasource.IDataSource;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity, ImageDMCPlayView, ImageDMPPlayView, DataSourcePlayList, 
//            PlayView

public class ImagePlayActivity extends PlayActivity
{

    public ImagePlayActivity()
    {
    }

    protected PlayView createDMCPlayView(ViewGroup viewgroup)
    {
        return new ImageDMCPlayView(this, viewgroup);
    }

    protected PlayView createDMPPlayView(ViewGroup viewgroup)
    {
        return new ImageDMPPlayView(this, viewgroup);
    }

    protected DataSourcePlayList createPlayList(IDataSource idatasource)
    {
        DataSourcePlayList datasourceplaylist = new DataSourcePlayList();
        DMCDataSource dmcdatasource = new DMCDataSource(idatasource, getApplication());
        dmcdatasource.init();
        datasourceplaylist.setDataSource(dmcdatasource);
        return datasourceplaylist;
    }

    protected void destoryPlayList(DataSourcePlayList datasourceplaylist)
    {
        DMCDataSource dmcdatasource;
        if (datasourceplaylist != null)
        {
            if ((dmcdatasource = (DMCDataSource)datasourceplaylist.getDataSource()) != null)
            {
                dmcdatasource.unInit();
                return;
            }
        }
    }

    protected int getMediaType()
    {
        return 2;
    }

    protected boolean isThirdPartyPlayer()
    {
        return false;
    }
}
