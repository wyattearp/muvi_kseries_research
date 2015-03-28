// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import com.arcsoft.util.debug.DebugUtils;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsLocalDataSource, MediaItem

protected static abstract class mIsOpened
    implements mIsOpened
{

    private boolean mIsOpened;
    private int mPos;

    public abstract MediaItem buildItem();

    public void close()
    {
        if (mIsOpened)
        {
            onClose();
        } else
        {
            Log.w("AbsLocalDataSource", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("ItemBuilder has already closed").toString());
        }
        mPos = -1;
        mIsOpened = false;
    }

    public abstract int getCount();

    public int getPosition()
    {
        return mPos;
    }

    public boolean isAfterLast()
    {
        while (getCount() == 0 || mPos == getCount()) 
        {
            return true;
        }
        return false;
    }

    public boolean isBeforeFirst()
    {
        while (getCount() == 0 || mPos == -1) 
        {
            return true;
        }
        return false;
    }

    public boolean isFirst()
    {
        return mPos == 0 && getCount() != 0;
    }

    public boolean isLast()
    {
        int i = getCount();
        return mPos == i - 1 && i != 0;
    }

    public boolean isOpened()
    {
        return mIsOpened;
    }

    public boolean move(int i)
    {
        return moveToPosition(i + mPos);
    }

    public boolean moveToFirst()
    {
        return moveToPosition(0);
    }

    public boolean moveToLast()
    {
        return moveToPosition(-1 + getCount());
    }

    public boolean moveToNext()
    {
        return moveToPosition(1 + mPos);
    }

    public boolean moveToPosition(int i)
    {
        int j = getCount();
        if (i >= j)
        {
            mPos = j;
            return false;
        }
        if (i < 0)
        {
            mPos = -1;
            return false;
        }
        if (i == mPos)
        {
            return true;
        }
        boolean flag = onMove(mPos, i);
        if (!flag)
        {
            mPos = -1;
            return flag;
        } else
        {
            mPos = i;
            return flag;
        }
    }

    public boolean moveToPrevious()
    {
        return moveToPosition(-1 + mPos);
    }

    protected abstract void onClose();

    protected abstract boolean onMove(int i, int j);

    protected abstract void onOpen();

    public mPos open()
    {
        if (!mIsOpened)
        {
            onOpen();
        } else
        {
            Log.w("AbsLocalDataSource", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("ItemBuilder has already opened").toString());
        }
        mIsOpened = true;
        return this;
    }

    protected ()
    {
        mPos = -1;
        mIsOpened = false;
    }
}
