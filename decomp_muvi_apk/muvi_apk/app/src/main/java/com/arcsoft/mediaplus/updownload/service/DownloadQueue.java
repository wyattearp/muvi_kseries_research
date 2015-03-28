// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import java.util.Vector;

public class DownloadQueue
{

    private Vector mQueue;

    public DownloadQueue()
    {
        mQueue = new Vector();
    }

    public void clear()
    {
        if (mQueue == null)
        {
            return;
        } else
        {
            mQueue.clear();
            return;
        }
    }

    public Object deQueue()
    {
        if (mQueue == null || mQueue.size() == 0)
        {
            return null;
        } else
        {
            Object obj = mQueue.firstElement();
            mQueue.remove(0);
            return obj;
        }
    }

    public void destroy()
    {
        clear();
        mQueue = null;
    }

    public void enQueue(Object obj)
    {
        if (mQueue == null || obj == null)
        {
            return;
        } else
        {
            mQueue.add(obj);
            return;
        }
    }

    public void insertToQueue(int i, Object obj)
    {
        if (mQueue == null || obj == null)
        {
            return;
        } else
        {
            mQueue.insertElementAt(obj, i);
            return;
        }
    }

    public int size()
    {
        return mQueue.size();
    }
}
