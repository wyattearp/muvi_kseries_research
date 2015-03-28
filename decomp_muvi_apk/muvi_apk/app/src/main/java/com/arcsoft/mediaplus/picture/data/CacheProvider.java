// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.data;

import android.graphics.Bitmap;
import com.arcsoft.mediaplus.datasource.MediaItem;

public interface CacheProvider
{

    public abstract Bitmap getAlbum(MediaItem mediaitem);

    public abstract android.opengl.ETC1Util.ETC1Texture getETC1Texture(MediaItem mediaitem);

    public abstract void onTaskBusy();

    public abstract void onTaskFree();
}
