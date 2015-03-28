// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

// Referenced classes of package com.arcsoft.videotrim.Utils:
//            UtilFunc

public class SDCardManager
{
    public static interface SDCardEventListener
    {

        public abstract void onSDCardEvent(Constants.SDCardEvent sdcardevent);
    }

    private static final class SubscriberNode
    {

        private final Set mInterestEventSet = new CopyOnWriteArraySet();
        private final SDCardEventListener mSDCardEventListener;

        public SDCardEventListener getSDCardEventListener()
        {
            return mSDCardEventListener;
        }

        public boolean isIntersetInEvent(Constants.SDCardEvent sdcardevent)
        {
            return mInterestEventSet.contains(sdcardevent);
        }

        public SubscriberNode(SDCardEventListener sdcardeventlistener, Constants.SDCardEvent asdcardevent[])
        {
            mSDCardEventListener = sdcardeventlistener;
            for (int i = 0; i < asdcardevent.length; i++)
            {
                mInterestEventSet.add(asdcardevent[i]);
            }

        }
    }


    private static final String TAG = "SDCardManager";
    private final BroadcastReceiver m_Receiver = new BroadcastReceiver() {

        final SDCardManager this$0;

        public void onReceive(Context context, Intent intent)
        {
            UtilFunc.Log("SDCardManager", (new StringBuilder()).append("onReceive--->action: ").append(intent.getAction()).toString());
            update(intent);
        }

            
            {
                this$0 = SDCardManager.this;
                super();
            }
    };
    private List m_SDCardEventSubscribers;

    public SDCardManager()
    {
        m_SDCardEventSubscribers = new ArrayList();
    }

    private static boolean checkFsWritable()
    {
        File file = new File(Environment.getExternalStorageDirectory().toString());
        if (file.isDirectory() || file.mkdirs()) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        File file1 = new File(file, ".probe");
        if (file1.exists())
        {
            file1.delete();
        }
        if (!file1.createNewFile()) goto _L1; else goto _L3
_L3:
        file1.delete();
        return true;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        return false;
    }

    public static String getDataVolumeName()
    {
        return Environment.getDataDirectory().getAbsolutePath();
    }

    public static IntentFilter getIntentFilter()
    {
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentfilter.addAction("android.intent.action.MEDIA_UNMOUNTABLE");
        intentfilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentfilter.addAction("android.intent.action.MEDIA_EJECT");
        intentfilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
        intentfilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
        intentfilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
        intentfilter.addAction("android.intent.action.MEDIA_SHARED");
        intentfilter.addDataScheme("file");
        return intentfilter;
    }

    public static long getInternalAvailableMemery()
    {
        StatFs statfs = new StatFs(Environment.getDataDirectory().getPath());
        return (long)statfs.getBlockSize() * (long)statfs.getAvailableBlocks();
    }

    public static long getInternalTotalMemory()
    {
        StatFs statfs = new StatFs(Environment.getDataDirectory().getPath());
        return (long)statfs.getBlockSize() * (long)statfs.getBlockCount();
    }

    public static long getSDCardAvailableMemery()
    {
        StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (long)statfs.getBlockSize() * (long)statfs.getAvailableBlocks();
    }

    public static long getSDCardTotalMemery()
    {
        StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (long)statfs.getBlockSize() * (long)statfs.getBlockCount();
    }

    public static String getSDCardVolumeName()
    {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static boolean hasSdcard()
    {
        if ("mounted".equals(Environment.getExternalStorageState()))
        {
            return checkFsWritable();
        } else
        {
            return false;
        }
    }

    public static boolean isSDCardIn()
    {
        String s = Environment.getExternalStorageState();
        return "mounted".equals(s) || "mounted_ro".equals(s);
    }

    public static boolean isSDCardPath(String s)
    {
        String s1;
        if (s != null && s.length() > 0)
        {
            if (s.toLowerCase().startsWith((s1 = getSDCardVolumeName()).toLowerCase()))
            {
                return true;
            }
        }
        return false;
    }

    private void notifyCardEvent(Constants.SDCardEvent sdcardevent)
    {
        Iterator iterator = m_SDCardEventSubscribers.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            SubscriberNode subscribernode = (SubscriberNode)iterator.next();
            if (subscribernode.isIntersetInEvent(sdcardevent))
            {
                subscribernode.getSDCardEventListener().onSDCardEvent(sdcardevent);
            }
        } while (true);
    }

    private void update(Intent intent)
    {
        String s = intent.getAction();
        if (s.equals("android.intent.action.MEDIA_MOUNTED"))
        {
            notifyCardEvent(Constants.SDCardEvent.MOUNTED);
        } else
        {
            if (s.endsWith("android.intent.action.MEDIA_UNMOUNTED"))
            {
                notifyCardEvent(Constants.SDCardEvent.UNMOUNTED);
                return;
            }
            if (s.endsWith("android.intent.action.MEDIA_REMOVED"))
            {
                notifyCardEvent(Constants.SDCardEvent.REMOVED);
                return;
            }
            if (s.endsWith("android.intent.action.MEDIA_BAD_REMOVAL"))
            {
                notifyCardEvent(Constants.SDCardEvent.BAD_REMOVAL);
                return;
            }
            if (s.endsWith("android.intent.action.MEDIA_SHARED"))
            {
                notifyCardEvent(Constants.SDCardEvent.SHARED);
                return;
            }
            if (s.endsWith("android.intent.action.MEDIA_EJECT"))
            {
                notifyCardEvent(Constants.SDCardEvent.EJECT);
                return;
            }
            if (s.endsWith("android.intent.action.MEDIA_SCANNER_STARTED"))
            {
                notifyCardEvent(Constants.SDCardEvent.SCANNER_STARTED);
                return;
            }
            if (s.endsWith("android.intent.action.MEDIA_SCANNER_FINISHED"))
            {
                notifyCardEvent(Constants.SDCardEvent.SCANNER_FINISHED);
                return;
            }
        }
    }

    public void destroy()
    {
        if (m_SDCardEventSubscribers != null)
        {
            m_SDCardEventSubscribers.clear();
            m_SDCardEventSubscribers = null;
        }
    }

    public BroadcastReceiver getSDCardReceiver()
    {
        return m_Receiver;
    }

    public void registerSDCardEventListener(SDCardEventListener sdcardeventlistener)
    {
        registerSDCardEventListener(sdcardeventlistener, Constants.SDCardEvent.values());
    }

    public void registerSDCardEventListener(SDCardEventListener sdcardeventlistener, Constants.SDCardEvent asdcardevent[])
    {
        SubscriberNode subscribernode = new SubscriberNode(sdcardeventlistener, asdcardevent);
        m_SDCardEventSubscribers.add(subscribernode);
    }

    public void unRegisterSDCardEventListener(SDCardEventListener sdcardeventlistener)
    {
        Iterator iterator = m_SDCardEventSubscribers.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            if (((SubscriberNode)iterator.next()).getSDCardEventListener().equals(sdcardeventlistener))
            {
                iterator.remove();
            }
        } while (true);
    }

}
