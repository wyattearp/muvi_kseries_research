// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class SafeBuffer
{

    private final LinkedList mQueue = new LinkedList();

    public SafeBuffer()
    {
    }

    public int append(Collection collection)
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator = collection.iterator();
        int i = 0;
_L2:
        boolean flag;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        flag = append(iterator.next());
        if (flag)
        {
            i++;
        }
        if (true) goto _L2; else goto _L1
_L1:
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int append(Object aobj[])
    {
        this;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        if (i >= aobj.length)
        {
            break; /* Loop/switch isn't completed */
        }
        if (aobj == null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        mQueue.add(aobj[i]);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        return 0;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean append(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        if (obj == null) goto _L2; else goto _L1
_L1:
        mQueue.add(obj);
        boolean flag = true;
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void clear()
    {
        this;
        JVM INSTR monitorenter ;
        mQueue.clear();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Object get()
    {
        this;
        JVM INSTR monitorenter ;
        int i = getCount();
        if (i != 0) goto _L2; else goto _L1
_L1:
        Object obj1 = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return obj1;
_L2:
        Object obj = mQueue.remove();
        obj1 = obj;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void get(int i, List list)
    {
        this;
        JVM INSTR monitorenter ;
        int k;
        if (i <= getCount())
        {
            break MISSING_BLOCK_LABEL_19;
        }
        k = getCount();
        i = k;
        if (i != 0) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        int j = 0;
_L4:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        list.add(mQueue.remove());
        j++;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L1; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public int getCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mQueue.size();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int insert(Collection collection, int i)
    {
        this;
        JVM INSTR monitorenter ;
        int j = 0;
        collection;
        JVM INSTR monitorenter ;
        Iterator iterator = collection.iterator();
        int k = i;
_L2:
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        if (insert(iterator.next(), k))
        {
            k++;
            j++;
        }
        if (true) goto _L2; else goto _L1
_L1:
        collection;
        JVM INSTR monitorexit ;
_L4:
        this;
        JVM INSTR monitorexit ;
        return j;
        Exception exception2;
        exception2;
        collection;
        JVM INSTR monitorexit ;
        throw exception2;
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public int insert(Object aobj[], int i)
    {
        this;
        JVM INSTR monitorenter ;
        int j = 0;
        int k = -1 + aobj.length;
_L3:
        if (k <= 0) goto _L2; else goto _L1
_L1:
        boolean flag = insert(aobj[k], i);
        if (flag)
        {
            j++;
        }
        k--;
          goto _L3
_L2:
        this;
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean insert(Object obj, int i)
    {
        this;
        JVM INSTR monitorenter ;
        if (obj == null) goto _L2; else goto _L1
_L1:
        mQueue.add(i, obj);
        boolean flag = true;
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Object pick()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj1 = mQueue.remove();
        Object obj = obj1;
_L2:
        this;
        JVM INSTR monitorexit ;
        return obj;
        Exception exception1;
        exception1;
        obj = null;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public Object pick(int i)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj1 = mQueue.remove(i);
        Object obj = obj1;
_L2:
        this;
        JVM INSTR monitorexit ;
        return obj;
        Exception exception1;
        exception1;
        obj = null;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public Object query(int i)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj1 = mQueue.get(i);
        Object obj = obj1;
_L2:
        this;
        JVM INSTR monitorexit ;
        return obj;
        Exception exception1;
        exception1;
        obj = null;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public int removeSame(Object obj, Comparator comparator)
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator = mQueue.iterator();
        int i = 0;
_L7:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Object obj1 = iterator.next();
        if (comparator != null) goto _L4; else goto _L3
_L3:
        boolean flag = obj.equals(obj1);
_L5:
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        iterator.remove();
        i++;
        continue; /* Loop/switch isn't completed */
_L4:
        int j = comparator.compare(obj, obj1);
        if (j == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (true) goto _L5; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
        if (true) goto _L7; else goto _L6
_L6:
    }
}
