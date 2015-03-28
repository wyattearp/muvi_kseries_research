// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import com.arcsoft.mediaplus.datasource.MediaItem;

// Referenced classes of package com.arcsoft.util.tool:
//            FileDelete

public static interface 
{

    public abstract void onDeleteStart(int i);

    public abstract void onDeleted(int i, int j);

    public abstract void onDeleting(MediaItem mediaitem, int i, int j, boolean flag);

    public abstract void onDeletingRemote(int i);
}
