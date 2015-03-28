// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import com.arcsoft.adk.image.IFileList;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            PictureDataSource, Property

class this._cls0
    implements IFileList
{

    final PictureDataSource this$0;

    public int getCount()
    {
        return PictureDataSource.this.getCount();
    }

    public String getFilePath(int i)
    {
        boolean flag = PictureDataSource.access$000(PictureDataSource.this);
        String s = null;
        if (!flag)
        {
            s = getStringProp(i, Property.PROP_IMAGE_FILEPATH, null);
        }
        if (s == null)
        {
            s = getStringProp(i, Property.PROP_THUMBNAIL_FILEPATH, null);
        }
        return s;
    }

    ()
    {
        this$0 = PictureDataSource.this;
        super();
    }
}
