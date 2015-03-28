// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import com.arcsoft.adk.image.IFileList;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.Property;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            ImageDMPPlayView, DataSourcePlayList

class this._cls0
    implements IFileList
{

    final ImageDMPPlayView this$0;

    public int getCount()
    {
        if (mPlayList == null)
        {
            return 0;
        } else
        {
            return mPlayList.getCount();
        }
    }

    public String getFilePath(int i)
    {
        IDataSource idatasource = ImageDMPPlayView.access$1200(ImageDMPPlayView.this);
        String s = null;
        if (idatasource != null)
        {
            s = null;
            if (true)
            {
                s = ImageDMPPlayView.access$1200(ImageDMPPlayView.this).getStringProp(i, Property.PROP_IMAGE_FILEPATH, null);
            }
        }
        return s;
    }

    ()
    {
        this$0 = ImageDMPPlayView.this;
        super();
    }
}
