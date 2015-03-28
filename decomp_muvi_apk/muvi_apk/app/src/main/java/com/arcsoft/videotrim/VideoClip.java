// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import com.arcsoft.videotrim.Utils.MediaFileUtils;
import com.arcsoft.videotrim.Utils.UtilFunc;

public class VideoClip
{

    public int mDuration;
    public int mStartPos;
    public String mStrFile;

    public VideoClip()
    {
        mStrFile = null;
        mStartPos = -1;
        mDuration = -1;
    }

    public boolean isValidVideoPath()
    {
        boolean flag = UtilFunc.IsFileExisted(mStrFile);
        boolean flag1 = false;
        if (flag)
        {
            MediaFileUtils mediafileutils = MediaFileUtils.getMediaFileUtils();
            boolean flag2 = mediafileutils.IsVideoFileType(mediafileutils.GetFileMediaType(mStrFile));
            flag1 = false;
            if (flag2)
            {
                flag1 = true;
            }
        }
        return flag1;
    }
}
