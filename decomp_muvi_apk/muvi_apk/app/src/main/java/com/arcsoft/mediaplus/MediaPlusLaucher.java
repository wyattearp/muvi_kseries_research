// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.videostream.aee.AEEConnectActivity;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

public class MediaPlusLaucher extends Activity
{

    private static final int MSG_LUANCHER_START = 1;
    private static final int SLEEP_TIME = 1000;
    public static boolean mSupportLivingView = true;
    private RelativeLayout bkView;
    private ImageView logoView;
    private BitmapDrawable mBgRes;
    private final Handler mHandler = new Handler() {

        final MediaPlusLaucher this$0;

        public void handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                return;

            case 1: // '\001'
                switchActivity();
                break;
            }
        }

            
            {
                this$0 = MediaPlusLaucher.this;
                super();
            }
    };
    private Drawable mIconRes;
    private Drawable mPoweredIconRes;
    private ImageView poweredLogoView;

    public MediaPlusLaucher()
    {
        mBgRes = null;
        mIconRes = null;
        mPoweredIconRes = null;
        bkView = null;
        logoView = null;
        poweredLogoView = null;
    }

    private void switchActivity()
    {
        Intent intent = new Intent();
        if (getSharedPreferences(getPackageName(), 0).getBoolean("key_support_living_view", true))
        {
            if (SystemUtils.getSDKVersion() >= 9)
            {
                intent.setClass(getApplicationContext(), com/arcsoft/videostream/aee/AEEConnectActivity);
            } else
            {
                intent.setClass(getApplicationContext(), com/arcsoft/mediaplus/MediaPlusActivity);
            }
        } else
        {
            intent.setClass(getApplicationContext(), com/arcsoft/mediaplus/MediaPlusActivity);
        }
        startActivity(intent);
        finish();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mBgRes = (BitmapDrawable)getResources().getDrawable(0x7f02025c);
        mBgRes.setDither(false);
        bkView = new RelativeLayout(this);
        bkView.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -1));
        bkView.setBackgroundDrawable(mBgRes);
        setContentView(bkView);
        SharedPreferences sharedpreferences = getSharedPreferences(getPackageName(), 0);
        if (sharedpreferences != null)
        {
            mSupportLivingView = sharedpreferences.getBoolean("key_support_living_view", true);
        }
        if (mHandler != null)
        {
            mHandler.sendEmptyMessageDelayed(1, 1000L);
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if (mBgRes != null)
        {
            mBgRes = null;
        }
        if (mIconRes != null)
        {
            mIconRes = null;
        }
        if (mPoweredIconRes != null)
        {
            mPoweredIconRes = null;
        }
        if (logoView != null)
        {
            logoView.destroyDrawingCache();
        }
        if (poweredLogoView != null)
        {
            poweredLogoView.destroyDrawingCache();
        }
        if (bkView != null)
        {
            bkView.removeAllViews();
            bkView.destroyDrawingCache();
        }
    }


}
