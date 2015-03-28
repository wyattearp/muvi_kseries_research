// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import com.arcsoft.adk.image.ThumbEngine;
import com.arcsoft.mediaplus.oem.OEMConfig;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            PictureDataSource, Property

class this._cls0
    implements angeListener
{

    final PictureDataSource this$0;

    public void onCountChanged(int i, int j)
    {
        refreshThumbEngine();
        notifyOnCountChanged(i, j);
    }

    public void onDataChanged(int i, Property property)
    {
        if (!OEMConfig.OPENGL_OPTIMIZE && (property.equals(Property.PROP_THUMBNAIL_FILEPATH) || property.equals(Property.PROP_IMAGE_FILEPATH)))
        {
            if (PictureDataSource.access$100(PictureDataSource.this) != null)
            {
                PictureDataSource.access$100(PictureDataSource.this).editItem(i);
            }
            return;
        } else
        {
            notifyOnDataChanged(i, property);
            return;
        }
    }

    angeListener()
    {
        this$0 = PictureDataSource.this;
        super();
    }
}
