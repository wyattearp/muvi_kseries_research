// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import com.arcsoft.Recyclable;
import com.arcsoft.util.debug.Log;

public class AudioTool
    implements Recyclable
{
    public static interface IOnAudioFocusChangeListener
    {

        public abstract void onAudioFocusGain();

        public abstract void onAudioFocusLoss();

        public abstract void onAudioFocusPlayPause();
    }


    public static final String CMDPAUSE = "pause";
    public static final String CMDSTOP = "stop";
    public static final String CMDTOGGLEPAUSE = "togglepause";
    public static final String PAUSE_ACTION = "com.android.music.musicservicecommand.pause";
    public static final String SERVICECMD = "com.android.music.musicservicecommand";
    private static final String TAG = "AudioTool";
    public static final String TOGGLEPAUSE_ACTION = "com.android.music.musicservicecommand.togglepause";
    private AudioManager mAudioManager;
    private Context mContext;
    private android.media.AudioManager.OnAudioFocusChangeListener mFocusListener;
    private IOnAudioFocusChangeListener mListener;
    private boolean mPausedByTransientLossOfFocus;
    private BroadcastReceiver mReceiver;

    public AudioTool(Context context)
    {
        mAudioManager = null;
        mContext = null;
        mReceiver = null;
        mListener = null;
        mPausedByTransientLossOfFocus = false;
        mFocusListener = new android.media.AudioManager.OnAudioFocusChangeListener() {

            final AudioTool this$0;

            public void onAudioFocusChange(int i)
            {
                Log.v("AudioTool", (new StringBuilder()).append("focusChange is ").append(i).toString());
                i;
                JVM INSTR tableswitch -3 1: default 60
            //                           -3 83
            //                           -2 83
            //                           -1 61
            //                           0 60
            //                           1 105;
                   goto _L1 _L2 _L2 _L3 _L1 _L4
_L1:
                return;
_L3:
                mPausedByTransientLossOfFocus = false;
                mListener.onAudioFocusLoss();
                return;
_L2:
                mPausedByTransientLossOfFocus = true;
                mListener.onAudioFocusLoss();
                return;
_L4:
                if (mPausedByTransientLossOfFocus)
                {
                    mPausedByTransientLossOfFocus = false;
                    mListener.onAudioFocusGain();
                    return;
                }
                if (true) goto _L1; else goto _L5
_L5:
            }

            
            {
                this$0 = AudioTool.this;
                super();
            }
        };
        mContext = context;
    }

    private void registerReceiver(Context context)
    {
        if (mReceiver != null)
        {
            return;
        }
        if (mAudioManager == null)
        {
            mAudioManager = (AudioManager)context.getSystemService("audio");
        }
        if (mAudioManager != null)
        {
            mAudioManager.requestAudioFocus(mFocusListener, 3, 1);
        }
        mReceiver = new BroadcastReceiver() {

            final AudioTool this$0;

            public void onReceive(Context context1, Intent intent)
            {
                if (intent != null)
                {
                    String s = intent.getAction();
                    String s1 = intent.getStringExtra("command");
                    boolean flag = intent.getBooleanExtra("isFromMediaSee", false);
                    Log.v("AudioTool", (new StringBuilder()).append("mAudioFocusReceiver cmd=").append(s1).toString());
                    Log.v("AudioTool", (new StringBuilder()).append("mAudioFocusReceiver action=").append(s).toString());
                    if (!flag)
                    {
                        if ("com.android.music.musicservicecommand".equals(s) && "pause".equals(s1))
                        {
                            intent.removeExtra("isFromMediaSee");
                            mListener.onAudioFocusLoss();
                            return;
                        }
                        if ("togglepause".equals(s1) || "com.android.music.musicservicecommand.togglepause".equals(s) || "stop".equals(s1))
                        {
                            mListener.onAudioFocusLoss();
                            return;
                        }
                        if ("com.android.music.musicservicecommand.pause".equals(s))
                        {
                            mListener.onAudioFocusPlayPause();
                            return;
                        }
                    }
                }
            }

            
            {
                this$0 = AudioTool.this;
                super();
            }
        };
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("com.android.music.musicservicecommand");
        intentfilter.addAction("com.android.music.musicservicecommand.togglepause");
        intentfilter.addAction("com.android.music.musicservicecommand.pause");
        context.registerReceiver(mReceiver, intentfilter);
    }

    private void unregisterReceiver(Context context)
    {
        if (mReceiver == null)
        {
            return;
        }
        if (mAudioManager != null && mFocusListener != null)
        {
            mAudioManager.abandonAudioFocus(mFocusListener);
        }
        context.unregisterReceiver(mReceiver);
        mReceiver = null;
    }

    public void recycle()
    {
        unregisterReceiver(mContext);
        mContext = null;
    }

    public void setOnAudioFocusChangeListener(IOnAudioFocusChangeListener ionaudiofocuschangelistener)
    {
        if (ionaudiofocuschangelistener == null)
        {
            unregisterReceiver(mContext);
        } else
        {
            registerReceiver(mContext);
        }
        mListener = ionaudiofocuschangelistener;
    }




/*
    static boolean access$102(AudioTool audiotool, boolean flag)
    {
        audiotool.mPausedByTransientLossOfFocus = flag;
        return flag;
    }

*/
}
