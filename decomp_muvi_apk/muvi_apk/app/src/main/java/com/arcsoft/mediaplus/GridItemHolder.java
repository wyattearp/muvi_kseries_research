// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.arcsoft.mediaplus.widget.AutoRecycleImageView;

// Referenced classes of package com.arcsoft.mediaplus:
//            ItemHolder

public class GridItemHolder
    implements ItemHolder
{

    public ImageView mDownloadIcon;
    public RelativeLayout mDownloadLayout;
    public TextView mFileDuration;
    public RelativeLayout mNormalLayout;
    public ProgressBar mProgress;
    public ImageView mSelectIcon;
    public AutoRecycleImageView mThumb;
    public RelativeLayout mVideoInfo;

    public GridItemHolder()
    {
        mThumb = null;
        mVideoInfo = null;
        mFileDuration = null;
        mSelectIcon = null;
        mDownloadIcon = null;
        mProgress = null;
        mNormalLayout = null;
        mDownloadLayout = null;
    }
}
