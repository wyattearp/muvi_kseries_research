// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.controller;

import android.graphics.Bitmap;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;

public interface CacheMgr
{

    public static final String MSG_SYNC_CACHE_BEGIN = "com.arcsoft.picture.sync_cache_begin";
    public static final String MSG_SYNC_CACHE_END = "com.arcsoft.picture.sync_cache_end";

    public abstract Bitmap getAlbum(MediaItem mediaitem);

    public abstract void onTaskBusy();

    public abstract void onTaskFree();

    public abstract void removeAllTask();

    public abstract void setDataSource(IDataSource idatasource);

    public abstract void setRomoteFlag(boolean flag);

    public abstract void submitTask(DecodeTask.DecodeListener decodelistener, MediaItem mediaitem, int i);
}
