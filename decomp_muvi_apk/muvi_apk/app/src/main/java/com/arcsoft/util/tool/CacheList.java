// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;


public class CacheList
{
    private static class CacheNode
    {

        Object item;
        int next;
        int prev;

        private CacheNode()
        {
            item = null;
            next = 0;
            prev = 0;
        }

    }

    public static abstract class CacheObjCreator
    {

        public abstract Object createItem(int i);

        public void recycleItem(Object obj)
        {
        }

        public CacheObjCreator()
        {
        }
    }


    private static final int CACHE_TIMES = 3;
    private CacheNode mCacheArray[];
    private CacheObjCreator mItemCreator;
    private int m_nCurArrayPos;
    private int m_nScreenCapability;
    private int m_nStartIndex;

    public CacheList(int i, CacheObjCreator cacheobjcreator)
    {
        m_nScreenCapability = 3;
        mItemCreator = null;
        mCacheArray = null;
        if (i < 3)
        {
            i = 3;
        }
        if (i > 2000)
        {
            i = 2000;
        }
        m_nScreenCapability = i;
        mCacheArray = new CacheNode[i * 3];
        for (int j = 0; j < mCacheArray.length; j++)
        {
            mCacheArray[j] = new CacheNode();
            mCacheArray[j].next = (j + 1) % mCacheArray.length;
            mCacheArray[j].prev = ((j - 1) + mCacheArray.length) % mCacheArray.length;
        }

        m_nCurArrayPos = 0;
        m_nStartIndex = 0;
        mItemCreator = cacheobjcreator;
    }

    public void clear()
    {
        for (int i = 0; i < mCacheArray.length; i++)
        {
            if (mCacheArray[i].item == null)
            {
                continue;
            }
            if (mItemCreator != null)
            {
                mItemCreator.recycleItem(mCacheArray[i].item);
            }
            mCacheArray[i].item = null;
        }

    }

    public int getCapability()
    {
        return mCacheArray.length;
    }

    public Object getItem(int i)
    {
        int j = m_nStartIndex;
        Object obj = null;
        if (i >= j)
        {
            int k = m_nStartIndex + mCacheArray.length;
            obj = null;
            if (i < k)
            {
                obj = mCacheArray[((i + m_nCurArrayPos) - m_nStartIndex) % mCacheArray.length].item;
                if (obj == null)
                {
                    obj = mItemCreator.createItem(i);
                    mCacheArray[((i + m_nCurArrayPos) - m_nStartIndex) % mCacheArray.length].item = obj;
                }
            }
        }
        return obj;
    }

    public int getStartIndex()
    {
        return m_nStartIndex;
    }

    public boolean setItem(int i, Object obj)
    {
        if (i >= m_nStartIndex && i < m_nStartIndex + mCacheArray.length)
        {
            int j = ((i + m_nCurArrayPos) - m_nStartIndex) % mCacheArray.length;
            if (obj != null && mCacheArray[j].item != null && mCacheArray[j].item != obj)
            {
                if (mItemCreator != null)
                {
                    mItemCreator.recycleItem(mCacheArray[j].item);
                }
                throw new IllegalStateException((new StringBuilder()).append("Error State : index = ").append(i).toString());
            } else
            {
                mCacheArray[j].item = obj;
                return true;
            }
        } else
        {
            return false;
        }
    }

    public void setStartIndex(int i)
    {
        if (i != m_nStartIndex && i >= 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int l;
        int i1;
        if (Math.abs(i - m_nStartIndex) >= mCacheArray.length)
        {
            clear();
            m_nStartIndex = i;
            m_nCurArrayPos = m_nStartIndex % mCacheArray.length;
            return;
        }
        int j = 2 * m_nScreenCapability;
        if (i >= m_nStartIndex && i < j + m_nStartIndex)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (i < j + m_nStartIndex)
        {
            break; /* Loop/switch isn't completed */
        }
        i1 = m_nStartIndex % mCacheArray.length;
        l = 1 + (i - m_nStartIndex - j);
        m_nStartIndex = l + m_nStartIndex;
        m_nCurArrayPos = (l + m_nCurArrayPos + mCacheArray.length) % mCacheArray.length;
_L4:
        int j1 = l;
        while (j1 > 0) 
        {
            if (mCacheArray[i1].item != null)
            {
                if (mItemCreator != null)
                {
                    mItemCreator.recycleItem(mCacheArray[i1].item);
                }
                mCacheArray[i1].item = null;
            }
            i1 = mCacheArray[i1].next;
            j1--;
        }
        if (true) goto _L1; else goto _L3
_L3:
        int k = m_nStartIndex;
        l = 0;
        i1 = 0;
        if (i < k)
        {
            i1 = i % mCacheArray.length;
            l = m_nStartIndex - i;
            m_nStartIndex = m_nStartIndex - l;
            m_nCurArrayPos = ((m_nCurArrayPos - l) + mCacheArray.length) % mCacheArray.length;
        }
          goto _L4
        if (true) goto _L1; else goto _L5
_L5:
    }
}
