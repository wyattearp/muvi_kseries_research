// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.Recyclable;

public class StorageTool
    implements Recyclable
{
    public static interface IOnMediaScanListener
    {

        public abstract void onMediaScanFinished();

        public abstract void onMediaScanStarted();
    }

    public static interface IOnStorageStatusChangeListener
    {

        public abstract void onStorageChecking();

        public abstract void onStorageMounted();

        public abstract void onStorageUnmounted();
    }

    private class NotifyHandler extends Handler
    {

        final StorageTool this$0;

        public void handleMessage(Message message)
        {
            String s = (String)message.obj;
            if (mStorageListener == null) goto _L2; else goto _L1
_L1:
            if (!s.equals("android.intent.action.MEDIA_MOUNTED")) goto _L4; else goto _L3
_L3:
            mStorageListener.onStorageMounted();
_L2:
            if (mMediaScanListener == null) goto _L6; else goto _L5
_L5:
            if (!s.equals("android.intent.action.MEDIA_SCANNER_STARTED")) goto _L8; else goto _L7
_L7:
            mMediaScanListener.onMediaScanStarted();
_L6:
            return;
_L4:
            if (s.equals("android.intent.action.MEDIA_UNMOUNTED"))
            {
                mStorageListener.onStorageUnmounted();
            } else
            if (s.equals("android.intent.action.MEDIA_CHECKING"))
            {
                mStorageListener.onStorageChecking();
            }
            continue; /* Loop/switch isn't completed */
_L8:
            if (!s.equals("android.intent.action.MEDIA_SCANNER_FINISHED")) goto _L6; else goto _L9
_L9:
            mMediaScanListener.onMediaScanFinished();
            return;
            if (true) goto _L2; else goto _L10
_L10:
        }

        private NotifyHandler()
        {
            this$0 = StorageTool.this;
            super();
        }

    }


    public static final int MEDIA_SCAN_DOING = 2;
    public static final int MEDIA_SCAN_NONE = 1;
    public static final int STORAGE_CHECKING = 2;
    public static final int STORAGE_MOUNTED = 1;
    public static final int STORAGE_UNMOUNTED = 3;
    private Context mContext;
    private Handler mHandler;
    private IOnMediaScanListener mMediaScanListener;
    private BroadcastReceiver mMediaScanReceiver;
    private IOnStorageStatusChangeListener mStorageListener;
    private BroadcastReceiver mStorageReceiver;

    public StorageTool(Context context)
    {
        mContext = null;
        mMediaScanReceiver = null;
        mStorageReceiver = null;
        mHandler = null;
        mStorageListener = null;
        mMediaScanListener = null;
        mContext = context;
    }

    public static int getMediaScanStatus(Context context)
    {
        return 1;
    }

    public static int getStorageStatus(Context context)
    {
        return 1;
    }

    private void registerMediaScanReceiver(Context context)
    {
        if (mMediaScanListener != null)
        {
            return;
        } else
        {
            mMediaScanReceiver = new BroadcastReceiver() {

                final StorageTool this$0;

                public void onReceive(Context context1, Intent intent)
                {
                    if (mHandler == null)
                    {
                        mHandler = new NotifyHandler();
                    }
                    Message message = Message.obtain();
                    message.obj = intent.getAction();
                    mHandler.sendMessage(message);
                }

            
            {
                this$0 = StorageTool.this;
                super();
            }
            };
            IntentFilter intentfilter = new IntentFilter("android.intent.action.MEDIA_SCANNER_STARTED");
            intentfilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
            intentfilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
            intentfilter.addDataScheme("file");
            context.registerReceiver(mMediaScanReceiver, intentfilter);
            return;
        }
    }

    private void registerStorageChangeReceiver(Context context)
    {
        if (mStorageReceiver != null)
        {
            return;
        } else
        {
            mStorageReceiver = new BroadcastReceiver() {

                final StorageTool this$0;

                public void onReceive(Context context1, Intent intent)
                {
                    if (mHandler == null)
                    {
                        mHandler = new NotifyHandler();
                    }
                    Message message = Message.obtain();
                    message.obj = intent.getAction();
                    mHandler.sendMessage(message);
                }

            
            {
                this$0 = StorageTool.this;
                super();
            }
            };
            IntentFilter intentfilter = new IntentFilter("android.intent.action.MEDIA_MOUNTED");
            intentfilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentfilter.addAction("android.intent.action.MEDIA_EJECT");
            intentfilter.addAction("android.intent.action.MEDIA_CHECKING");
            intentfilter.addDataScheme("file");
            context.registerReceiver(mStorageReceiver, intentfilter);
            return;
        }
    }

    private void unregisterMediaScanReceiver(Context context)
    {
        if (mMediaScanReceiver == null)
        {
            return;
        } else
        {
            context.unregisterReceiver(mMediaScanReceiver);
            mMediaScanReceiver = null;
            return;
        }
    }

    private void unregisterStorageChangeReceiver(Context context)
    {
        if (mStorageReceiver == null)
        {
            return;
        } else
        {
            context.unregisterReceiver(mStorageReceiver);
            mStorageReceiver = null;
            return;
        }
    }

    public void recycle()
    {
        unregisterStorageChangeReceiver(mContext);
        unregisterMediaScanReceiver(mContext);
        mContext = null;
    }

    public void setOnMediaScanListener(IOnMediaScanListener ionmediascanlistener)
    {
        if (ionmediascanlistener == null)
        {
            unregisterMediaScanReceiver(mContext);
        } else
        {
            registerMediaScanReceiver(mContext);
        }
        mMediaScanListener = ionmediascanlistener;
    }

    public void setOnStorageStatusChangeListener(IOnStorageStatusChangeListener ionstoragestatuschangelistener)
    {
        if (ionstoragestatuschangelistener == null)
        {
            unregisterStorageChangeReceiver(mContext);
        } else
        {
            registerStorageChangeReceiver(mContext);
        }
        mStorageListener = ionstoragestatuschangelistener;
    }



/*
    static Handler access$002(StorageTool storagetool, Handler handler)
    {
        storagetool.mHandler = handler;
        return handler;
    }

*/


}
