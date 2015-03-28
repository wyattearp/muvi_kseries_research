// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import com.arcsoft.mediaplus.datasource.MediaItem;

// Referenced classes of package com.arcsoft.util.tool:
//            FileDelete

public class fileItem
{

    public static final int DATA_TYPE_IMAGE = 1;
    public static final int DATA_TYPE_UNDEFINE = 0;
    public static final int DATA_TYPE_VIDEO = 2;
    public int dataType;
    public MediaItem fileItem;
    public String filePath;
    public long mId;
    public String mstr_objId;
    final FileDelete this$0;
    public String title;

    public ()
    {
        this$0 = FileDelete.this;
        super();
        dataType = 0;
        mId = 0L;
        filePath = null;
        title = null;
        mstr_objId = null;
        fileItem = null;
    }
}
