// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import com.arcsoft.Recyclable;
import com.arcsoft.util.SystemUtils;

public class NetworkTool
    implements Recyclable
{
    public static interface IOnConnectivityChangeListener
    {

        public abstract void OnConnectivityChanged(NetworkStateInfo networkstateinfo);
    }

    public static interface IOnSettingChangeListener
    {

        public abstract void OnBackgroundDataSettingChanged();
    }

    public class NetworkStateInfo
    {

        public boolean failover;
        public NetworkInfo networkInfo;
        public boolean noconnectivity;
        final NetworkTool this$0;

        public NetworkStateInfo()
        {
            this$0 = NetworkTool.this;
            super();
        }
    }


    private IOnConnectivityChangeListener mConnectiveListener;
    private BroadcastReceiver mConnectiveReceiver;
    private Context mContext;
    private Handler mHandler;
    private IOnSettingChangeListener mSettingListener;
    private BroadcastReceiver mSettingReceiver;

    public NetworkTool(Context context)
    {
        mContext = null;
        mConnectiveReceiver = null;
        mSettingReceiver = null;
        mHandler = null;
        mConnectiveListener = null;
        mSettingListener = null;
        mContext = context;
    }

    private void registerConnectiveReceiver(Context context)
    {
        if (mConnectiveReceiver != null)
        {
            return;
        } else
        {
            mConnectiveReceiver = new BroadcastReceiver() {

                final NetworkTool this$0;

                public void onReceive(Context context1, Intent intent)
                {
                    NetworkStateInfo networkstateinfo;
                    String s;
                    networkstateinfo = new NetworkStateInfo();
                    s = intent.getAction();
                    if (!s.equals("android.net.wifi.WIFI_STATE_CHANGED")) goto _L2; else goto _L1
_L1:
                    if (SystemUtils.getSDKVersion() > 10 || intent.getIntExtra("wifi_state", 1) != 1) goto _L4; else goto _L3
_L3:
                    networkstateinfo.noconnectivity = intent.getBooleanExtra("noConnectivity", false);
                    networkstateinfo.failover = intent.getBooleanExtra("isFailover", false);
                    networkstateinfo.networkInfo = ((ConnectivityManager)context1.getSystemService("connectivity")).getNetworkInfo(1);
_L6:
                    if (networkstateinfo.networkInfo == null || !networkstateinfo.networkInfo.getState().equals(android.net.NetworkInfo.State.CONNECTING))
                    {
                        break; /* Loop/switch isn't completed */
                    }
_L4:
                    return;
_L2:
                    if (s.equals("android.net.wifi.STATE_CHANGE"))
                    {
                        networkstateinfo.noconnectivity = intent.getBooleanExtra("noConnectivity", false);
                        networkstateinfo.failover = intent.getBooleanExtra("isFailover", false);
                        networkstateinfo.networkInfo = (NetworkInfo)intent.getParcelableExtra("networkInfo");
                    }
                    if (true) goto _L6; else goto _L5
_L5:
                    if (mHandler == null)
                    {
                        mHandler = new Handler();
                    }
                    mHandler.post(networkstateinfo. new Runnable() {

                        final _cls1 this$1;
                        final NetworkStateInfo val$tmpinfo;

                        public void run()
                        {
                            if (mConnectiveListener != null)
                            {
                                mConnectiveListener.OnConnectivityChanged(tmpinfo);
                            }
                        }

            
            {
                this$1 = final__pcls1;
                tmpinfo = NetworkStateInfo.this;
                super();
            }
                    });
                    return;
                }

            
            {
                this$0 = NetworkTool.this;
                super();
            }
            };
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("android.net.wifi.STATE_CHANGE");
            intentfilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            context.registerReceiver(mConnectiveReceiver, intentfilter);
            return;
        }
    }

    private void registerSettingReceiver(Context context)
    {
        if (mSettingReceiver != null)
        {
            return;
        } else
        {
            mSettingReceiver = new BroadcastReceiver() {

                final NetworkTool this$0;

                public void onReceive(Context context1, Intent intent)
                {
                    if (mHandler == null)
                    {
                        mHandler = new Handler();
                    }
                    mHandler.post(new Runnable() {

                        final _cls2 this$1;

                        public void run()
                        {
                            if (mSettingListener != null)
                            {
                                mSettingListener.OnBackgroundDataSettingChanged();
                            }
                        }

            
            {
                this$1 = _cls2.this;
                super();
            }
                    });
                }

            
            {
                this$0 = NetworkTool.this;
                super();
            }
            };
            IntentFilter intentfilter = new IntentFilter("android.net.conn.BACKGROUND_DATA_SETTING_CHANGED");
            context.registerReceiver(mSettingReceiver, intentfilter);
            return;
        }
    }

    private void unregisterConnectiveReceiver(Context context)
    {
        if (mConnectiveReceiver == null)
        {
            return;
        } else
        {
            context.unregisterReceiver(mConnectiveReceiver);
            return;
        }
    }

    private void unregisterSettingReceiver(Context context)
    {
        if (mSettingReceiver == null)
        {
            return;
        } else
        {
            context.unregisterReceiver(mSettingReceiver);
            return;
        }
    }

    public void recycle()
    {
        unregisterConnectiveReceiver(mContext);
        unregisterSettingReceiver(mContext);
        mContext = null;
    }

    public void setOnConnectivityChangeListener(IOnConnectivityChangeListener ionconnectivitychangelistener)
    {
        if (ionconnectivitychangelistener == null)
        {
            unregisterConnectiveReceiver(mContext);
        } else
        {
            registerConnectiveReceiver(mContext);
        }
        mConnectiveListener = ionconnectivitychangelistener;
    }

    public void setOnSettingChangeListener(IOnSettingChangeListener ionsettingchangelistener)
    {
        if (ionsettingchangelistener == null)
        {
            unregisterSettingReceiver(mContext);
        } else
        {
            registerSettingReceiver(mContext);
        }
        mSettingListener = ionsettingchangelistener;
    }



/*
    static Handler access$002(NetworkTool networktool, Handler handler)
    {
        networktool.mHandler = handler;
        return handler;
    }

*/


}
