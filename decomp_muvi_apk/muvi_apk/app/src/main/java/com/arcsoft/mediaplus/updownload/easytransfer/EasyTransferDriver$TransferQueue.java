// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import com.arcsoft.Recyclable;
import com.arcsoft.util.debug.Log;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver

private class <init>
    implements Recyclable
{

    private final Comparator mCmpId;
    private final Comparator mCmpUdn;
    LinkedList mWaitQueue;
    final EasyTransferDriver this$0;

    public void add(String s, long l, int i, boolean flag)
    {
        Log.w("TransferQueue", (new StringBuilder()).append("add() id=").append(l).toString());
        LinkedList linkedlist = mWaitQueue;
        linkedlist;
        JVM INSTR monitorenter ;
        Node node;
        node = new Node(EasyTransferDriver.this, null);
        EasyTransferDriver.access$1400(EasyTransferDriver.this, node, l);
        node.serverudn = s;
        node.tableid = l;
        node.serverstate = i;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        mWaitQueue.addFirst(node);
_L2:
        linkedlist;
        JVM INSTR monitorexit ;
        return;
        mWaitQueue.add(node);
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        linkedlist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Node find(String s, long l)
    {
label0:
        {
            Node node = new Node(EasyTransferDriver.this, null);
            node.serverudn = s;
            node.tableid = l;
            Comparator comparator;
            Node node1;
            if (s != null)
            {
                comparator = mCmpUdn;
            } else
            {
                comparator = mCmpId;
            }
            synchronized (mWaitQueue)
            {
                Iterator iterator = mWaitQueue.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break label0;
                    }
                    node1 = (Node)iterator.next();
                } while (comparator.compare(node, node1) != 0);
                Log.w("TransferQueue", (new StringBuilder()).append("find() =").append(l).toString());
            }
            return node1;
        }
        Log.w("TransferQueue", (new StringBuilder()).append("find() fail =").append(l).toString());
        linkedlist;
        JVM INSTR monitorexit ;
        return null;
        exception;
        linkedlist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Node first()
    {
label0:
        {
            Node node;
            synchronized (mWaitQueue)
            {
                Iterator iterator = mWaitQueue.iterator();
                if (!iterator.hasNext())
                {
                    break label0;
                }
                node = (Node)iterator.next();
                Log.w("TransferQueue", (new StringBuilder()).append("first(), tableid =").append(node.tableid).toString());
            }
            return node;
        }
        Log.w("TransferQueue", "first() fail.");
        linkedlist;
        JVM INSTR monitorexit ;
        return null;
        exception;
        linkedlist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getCount()
    {
        int i;
        synchronized (mWaitQueue)
        {
            i = mWaitQueue.size();
            Log.w("TransferQueue", (new StringBuilder()).append("getCount() =").append(i).toString());
        }
        return i;
        exception;
        linkedlist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void recycle()
    {
        Log.w("TransferQueue", "recycle()");
        synchronized (mWaitQueue)
        {
            mWaitQueue.clear();
        }
        return;
        exception;
        linkedlist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Node remove(String s, long l)
    {
        Node node1;
        LinkedList linkedlist;
        Log.w("TransferQueue", (new StringBuilder()).append("remove() id=").append(l).toString());
        Node node = new Node(EasyTransferDriver.this, null);
        node.serverudn = s;
        node.tableid = l;
        Comparator comparator;
        Iterator iterator;
        Node node2;
        if (s != null)
        {
            comparator = mCmpUdn;
        } else
        {
            comparator = mCmpId;
        }
        node1 = null;
        linkedlist = mWaitQueue;
        linkedlist;
        JVM INSTR monitorenter ;
        iterator = mWaitQueue.iterator();
_L1:
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_141;
            }
            node2 = (Node)iterator.next();
        } while (comparator.compare(node, node2) != 0);
        iterator.remove();
        node1 = node2;
          goto _L1
        linkedlist;
        JVM INSTR monitorexit ;
        return node1;
        Exception exception;
        exception;
        linkedlist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void update(String s, long l)
    {
        Log.w("TransferQueue", (new StringBuilder()).append("update() id=").append(l).toString());
        Node node = find(s, l);
        if (node != null)
        {
            synchronized (mWaitQueue)
            {
                EasyTransferDriver.access$1400(EasyTransferDriver.this, node, l);
            }
            return;
        } else
        {
            return;
        }
        exception;
        linkedlist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private _cls2.this._cls1()
    {
        this$0 = EasyTransferDriver.this;
        super();
        mWaitQueue = new LinkedList();
        mCmpUdn = new Comparator() {

            final EasyTransferDriver.TransferQueue this$1;

            public int compare(EasyTransferDriver.TransferServerNode transferservernode, EasyTransferDriver.TransferServerNode transferservernode1)
            {
                return !transferservernode.serverudn.equalsIgnoreCase(transferservernode1.serverudn) ? -1 : 0;
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((EasyTransferDriver.TransferServerNode)obj, (EasyTransferDriver.TransferServerNode)obj1);
            }

            
            {
                this$1 = EasyTransferDriver.TransferQueue.this;
                super();
            }
        };
        mCmpId = new Comparator() {

            final EasyTransferDriver.TransferQueue this$1;

            public int compare(EasyTransferDriver.TransferServerNode transferservernode, EasyTransferDriver.TransferServerNode transferservernode1)
            {
                return transferservernode.tableid != transferservernode1.tableid ? -1 : 0;
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((EasyTransferDriver.TransferServerNode)obj, (EasyTransferDriver.TransferServerNode)obj1);
            }

            
            {
                this$1 = EasyTransferDriver.TransferQueue.this;
                super();
            }
        };
    }

    Node(Node node)
    {
        this();
    }
}
