// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.data;

import android.content.Context;
import android.graphics.Bitmap;
import com.arcsoft.mediaplus.datasource.MediaItem;

// Referenced classes of package com.arcsoft.mediaplus.picture.data:
//            LocalCacheProvider

public static interface 
{

    public abstract Bitmap decodeImage(MediaItem mediaitem, Context context);

    public abstract void registerDecodeOkListener();

    public abstract void unRegisterDecodeOkListener();
}
