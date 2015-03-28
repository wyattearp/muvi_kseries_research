// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.util.debug.DebugUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.DoubleBufferList;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsListDataSource, MediaItem

public abstract class AbsLocalDataSource extends AbsListDataSource
{
    protected static abstract class AbsItemBuilder
        implements ItemBuilder
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

        public ItemBuilder open()
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

        protected AbsItemBuilder()
        {
            mPos = -1;
            mIsOpened = false;
        }
    }

    protected static interface ItemBuilder
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

        public abstract ItemBuilder open();
    }


    private static final String TAG = "AbsLocalDataSource";
    private final ContentObserver mContentObserver = new ContentObserver(null) {

        final AbsLocalDataSource this$0;

        public void onChange(boolean flag)
        {
            if (mSelfDeleteCount < 0)
            {
                mSelfDeleteCount = 0;
            }
            if (mSelfDeleteCount == 0)
            {
                mUpdateHandler.sendEmptyMessageDelayed(0, 5000L);
                super.onChange(flag);
            } else
            if (mSelfDeleteCount > 0)
            {
                int i = -1 + 
// JavaClassFileOutputException: get_constant: invalid tag

            
            {
                this$0 = AbsLocalDataSource.this;
                super(handler);
            }
    };
    protected final ContentResolver mContentResolver;
    private int mSelfDeleteCount;
    private int mTotalCount;
    private final Handler mUpdateHandler = new Handler() {

        final AbsLocalDataSource this$0;

        public void handleMessage(Message message)
        {
            if (mList != null)
            {
                Log.e("MediaSee", "Local files updated while mediastore scan operation");
                mList.invalide();
            }
            if (hasMessages(0))
            {
                removeMessages(0);
                sendEmptyMessageDelayed(0, 5000L);
            }
        }

            
            {
                this$0 = AbsLocalDataSource.this;
                super();
            }
    };

    public AbsLocalDataSource(ContentResolver contentresolver)
    {
        mTotalCount = -1;
        mSelfDeleteCount = 0;
        mContentResolver = contentresolver;
    }

    public boolean delete(int i)
    {
        boolean flag = super.delete(i);
        if (flag)
        {
            mSelfDeleteCount = 1 + mSelfDeleteCount;
        }
        return flag;
    }

    public int getTotalCount()
    {
        if (mTotalCount == -1)
        {
            ItemBuilder itembuilder = openItemBuilder();
            if (itembuilder != null)
            {
                mTotalCount = itembuilder.getCount();
                itembuilder.close();
            } else
            {
                Log.w("AbsLocalDataSource", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("openCuror failed").toString());
                mTotalCount = 0;
            }
        }
        return mTotalCount;
    }

    public boolean onBuildList(List list, com.arcsoft.util.tool.DoubleBufferList.Options options)
    {
        ItemBuilder itembuilder = openItemBuilder();
        if (itembuilder != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        do
        {
label0:
            {
                boolean flag = itembuilder.moveToNext();
                boolean flag1 = false;
                if (flag)
                {
                    boolean flag2 = isCountArriveExpect(list.size());
                    flag1 = false;
                    if (!flag2)
                    {
                        if (!options.mIsCanceled)
                        {
                            break label0;
                        }
                        flag1 = true;
                    }
                }
                itembuilder.close();
                quickIndexSort(list);
                if (!flag1)
                {
                    return true;
                }
            }
            if (true)
            {
                continue;
            }
            list.add(itembuilder.buildItem());
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected void onDestoryList(List list)
    {
        list.clear();
    }

    protected void onDisable()
    {
        unRegisterContentObserver(mContentObserver);
        super.onDisable();
    }

    protected void onEnable()
    {
        super.onEnable();
        registerContentObserver(mContentObserver);
        mList.invalide();
    }

    protected abstract ItemBuilder openItemBuilder();

    protected abstract void registerContentObserver(ContentObserver contentobserver);

    protected abstract void unRegisterContentObserver(ContentObserver contentobserver);



/*
    static int access$002(AbsLocalDataSource abslocaldatasource, int i)
    {
        abslocaldatasource.mSelfDeleteCount = i;
        return i;
    }

*/


/*
    static int access$006(AbsLocalDataSource abslocaldatasource)
    {
        int i = -1 + abslocaldatasource.mSelfDeleteCount;
        abslocaldatasource.mSelfDeleteCount = i;
        return i;
    }

*/

}
