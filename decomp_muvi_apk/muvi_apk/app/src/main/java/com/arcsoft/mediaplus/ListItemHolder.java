// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

// Referenced classes of package com.arcsoft.mediaplus:
//            ItemHolder

public class ListItemHolder
    implements ItemHolder
{

    public ImageView mDivider;
    public ImageView mDownloadStatus;
    public TextView mFileDate;
    public TextView mFileName;
    public TextView mFileSize;
    public ImageView mMediaType;
    public ProgressBar mProgress;

    public ListItemHolder()
    {
        mMediaType = null;
        mFileName = null;
        mFileSize = null;
        mFileDate = null;
        mDownloadStatus = null;
        mDivider = null;
        mProgress = null;
    }
}
