// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.arcsoft.Recyclable;

public class TelephonyTool
    implements Recyclable
{
    public static interface IOnCallStatusChangeListener
    {

        public abstract void onCallStatusIdle();

        public abstract void onCallStatusOffHook();

        public abstract void onCallStatusRinging();
    }

    private class PhoneStateListener extends android.telephony.PhoneStateListener
    {

        final TelephonyTool this$0;

        public void onCallStateChanged(int i, String s)
        {
            if (mListener == null)
            {
                return;
            }
            switch (i)
            {
            default:
                return;

            case 0: // '\0'
                mListener.onCallStatusIdle();
                return;

            case 2: // '\002'
                mListener.onCallStatusOffHook();
                return;

            case 1: // '\001'
                mListener.onCallStatusRinging();
                return;
            }
        }

        private PhoneStateListener()
        {
            this$0 = TelephonyTool.this;
            super();
        }

    }


    private IOnCallStatusChangeListener mListener;
    private PhoneStateListener mPhoneListener;
    private TelephonyManager mTelephonyManager;

    public TelephonyTool(Context context)
    {
        mPhoneListener = null;
        mTelephonyManager = null;
        mListener = null;
        mTelephonyManager = (TelephonyManager)context.getSystemService("phone");
    }

    private void registerReceiver()
    {
        if (mPhoneListener != null)
        {
            return;
        } else
        {
            mPhoneListener = new PhoneStateListener();
            mTelephonyManager.listen(mPhoneListener, 32);
            return;
        }
    }

    private void unregisterReceiver()
    {
        if (mPhoneListener == null)
        {
            return;
        } else
        {
            mTelephonyManager.listen(mPhoneListener, 0);
            mPhoneListener = null;
            return;
        }
    }

    public int getCallStatus(Context context)
    {
        return mTelephonyManager.getCallState();
    }

    public void recycle()
    {
        unregisterReceiver();
        mTelephonyManager = null;
    }

    public void setOnCallStatusChangeListener(IOnCallStatusChangeListener ioncallstatuschangelistener)
    {
        if (ioncallstatuschangelistener == null)
        {
            unregisterReceiver();
        } else
        {
            registerReceiver();
        }
        mListener = ioncallstatuschangelistener;
    }

}
