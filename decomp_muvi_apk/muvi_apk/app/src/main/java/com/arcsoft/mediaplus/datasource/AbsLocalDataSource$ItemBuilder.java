// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;


// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsLocalDataSource, MediaItem

protected static interface 
{

    public abstract MediaItem buildItem();

    public abstract void close();

    public abstract int getCount();

    public abstract int getPosition();

    public abstract boolean isAfterLast();

    public abstract boolean isBeforeFirst();

    public abstract boolean isFirst();

    public abstract boolean isLast();

    public abstract boolean isOpened();

    public abstract boolean move(int i);

    public abstract boolean moveToFirst();

    public abstract boolean moveToLast();

    public abstract boolean moveToNext();

    public abstract boolean moveToPosition(int i);

    public abstract boolean moveToPrevious();

    public abstract  open();
}
