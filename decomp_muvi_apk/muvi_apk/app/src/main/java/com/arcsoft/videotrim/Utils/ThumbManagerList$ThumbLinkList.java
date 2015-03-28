// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;

import android.graphics.Bitmap;

// Referenced classes of package com.arcsoft.videotrim.Utils:
//            ThumbManagerList

public class midentifier
{

    midentifier mNext;
    midentifier mPrevious;
    Bitmap mThumbBmp;
    boolean mbDecoded;
    int midentifier;
    final ThumbManagerList this$0;

    ()
    {
        this$0 = ThumbManagerList.this;
        super();
        mThumbBmp = null;
        midentifier = -1;
        mbDecoded = false;
    }

    mbDecoded(mbDecoded mbdecoded, mbDecoded mbdecoded1, Bitmap bitmap, int i)
    {
        this$0 = ThumbManagerList.this;
        super();
        mThumbBmp = null;
        midentifier = -1;
        mbDecoded = false;
        mPrevious = mbdecoded;
        mNext = mbdecoded1;
        mThumbBmp = bitmap;
        midentifier = i;
    }
}
