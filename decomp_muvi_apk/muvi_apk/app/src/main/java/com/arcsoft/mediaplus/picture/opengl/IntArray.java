// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;


public class IntArray
{

    private static final int INIT_CAPACITY = 8;
    private int mData[];
    private int mSize;

    public IntArray()
    {
        mData = new int[8];
        mSize = 0;
    }

    public void add(int i)
    {
        if (mData.length == mSize)
        {
            int ai1[] = new int[mSize + mSize];
            System.arraycopy(mData, 0, ai1, 0, mSize);
            mData = ai1;
        }
        int ai[] = mData;
        int j = mSize;
        mSize = j + 1;
        ai[j] = i;
    }

    public void clear()
    {
        mSize = 0;
        if (mData.length != 8)
        {
            mData = new int[8];
        }
    }

    public int[] getInternalArray()
    {
        return mData;
    }

    public int size()
    {
        return mSize;
    }

    public int[] toArray(int ai[])
    {
        if (ai == null || ai.length < mSize)
        {
            ai = new int[mSize];
        }
        System.arraycopy(mData, 0, ai, 0, mSize);
        return ai;
    }
}
