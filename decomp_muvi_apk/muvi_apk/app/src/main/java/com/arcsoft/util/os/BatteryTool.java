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

public class BatteryTool
    implements Recyclable
{
    public class BatteryInfo
    {

        public int health;
        public int level;
        public int maxlevel;
        public int pluged;
        public boolean present;
        public int status;
        public int temperature;
        final BatteryTool this$0;
        public int voltage;

        public BatteryInfo()
        {
            this$0 = BatteryTool.this;
            super();
        }
    }

    public static interface IOnBatteryChangeListener
    {

        public abstract void OnBatteryLevelChanged(BatteryInfo batteryinfo);

        public abstract void OnBatteryPlugedChanged(BatteryInfo batteryinfo);

        public abstract void OnBatteryStatusChanged(BatteryInfo batteryinfo);
    }

    private class NotifyHandler extends Handler
    {

        final BatteryTool this$0;

        public void handleMessage(Message message)
        {
            if (mListener == null)
            {
                return;
            }
            switch (message.what)
            {
            default:
                return;

            case 1: // '\001'
                mListener.OnBatteryStatusChanged(mBatteryInfo);
                return;

            case 2: // '\002'
                mListener.OnBatteryPlugedChanged(mBatteryInfo);
                return;

            case 3: // '\003'
                mListener.OnBatteryLevelChanged(mBatteryInfo);
                break;
            }
        }

        private NotifyHandler()
        {
            this$0 = BatteryTool.this;
            super();
        }

    }


    private static final int TYPE_LEVEL_CHANGED = 3;
    private static final int TYPE_PLUGED_CHANGE = 2;
    private static final int TYPE_STATUS_CHANGE = 1;
    private BatteryInfo mBatteryInfo;
    private Context mContext;
    private Handler mHandler;
    private IOnBatteryChangeListener mListener;
    private BroadcastReceiver mReceiver;

    public BatteryTool(Context context)
    {
        mContext = null;
        mReceiver = null;
        mHandler = null;
        mBatteryInfo = null;
        mListener = null;
        mContext = context;
    }

    private void registerReceiver(Context context)
    {
        if (mReceiver != null)
        {
            return;
        } else
        {
            mReceiver = new BroadcastReceiver() {

                final BatteryTool this$0;

                public void onReceive(Context context1, Intent intent)
                {
                    BatteryInfo batteryinfo = new BatteryInfo();
                    batteryinfo.present = intent.getBooleanExtra("present", true);
                    batteryinfo.maxlevel = intent.getIntExtra("scale", 0);
                    batteryinfo.level = intent.getIntExtra("level", 0);
                    batteryinfo.status = intent.getIntExtra("status", 1);
                    batteryinfo.pluged = intent.getIntExtra("plugged", 2);
                    batteryinfo.temperature = intent.getIntExtra("temperature", 0);
                    batteryinfo.voltage = intent.getIntExtra("voltage", 5);
                    if (mHandler == null)
                    {
                        mHandler = new NotifyHandler();
                    }
                    if (mBatteryInfo == null || batteryinfo.level != mBatteryInfo.level)
                    {
                        mHandler.sendEmptyMessage(3);
                    }
                    if (mBatteryInfo == null || batteryinfo.status != mBatteryInfo.status)
                    {
                        mHandler.sendEmptyMessage(1);
                    }
                    if (mBatteryInfo == null || batteryinfo.pluged != mBatteryInfo.pluged)
                    {
                        mHandler.sendEmptyMessage(2);
                    }
                    mBatteryInfo = batteryinfo;
                }

            
            {
                this$0 = BatteryTool.this;
                super();
            }
            };
            IntentFilter intentfilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
            context.registerReceiver(mReceiver, intentfilter);
            return;
        }
    }

    private void unregisterReceiver(Context context)
    {
        if (mReceiver == null)
        {
            return;
        } else
        {
            context.unregisterReceiver(mReceiver);
            mReceiver = null;
            return;
        }
    }

    public int getBatteryHealth(Context context)
    {
        throw new UnsupportedOperationException("This function has not implemented");
    }

    public int getBatteryPluged(Context context)
    {
        throw new UnsupportedOperationException("This function has not implemented");
    }

    public int getBatteryStatus(Context context)
    {
        throw new UnsupportedOperationException("This function has not implemented");
    }

    public void recycle()
    {
        unregisterReceiver(mContext);
        mContext = null;
    }

    public void setOnBatteryChangeListener(IOnBatteryChangeListener ionbatterychangelistener)
    {
        if (ionbatterychangelistener == null)
        {
            unregisterReceiver(mContext);
        } else
        {
            registerReceiver(mContext);
        }
        mListener = ionbatterychangelistener;
    }



/*
    static Handler access$002(BatteryTool batterytool, Handler handler)
    {
        batterytool.mHandler = handler;
        return handler;
    }

*/



/*
    static BatteryInfo access$202(BatteryTool batterytool, BatteryInfo batteryinfo)
    {
        batterytool.mBatteryInfo = batteryinfo;
        return batteryinfo;
    }

*/

}
