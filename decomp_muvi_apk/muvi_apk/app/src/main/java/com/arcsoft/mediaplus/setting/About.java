// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class About extends Activity
{

    private ScrollView ScrollView;
    private ImageView mBackBtn;
    private final android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() {

        final About this$0;

        public void onClick(View view)
        {
            switch (view.getId())
            {
            default:
                return;

            case 2131296262: 
                finish();
                break;
            }
        }

            
            {
                this$0 = About.this;
                super();
            }
    };

    public About()
    {
        mBackBtn = null;
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        switch (keyevent.getKeyCode())
        {
        default:
            return false;

        case 4: // '\004'
            finish();
            return false;

        case 19: // '\023'
            ScrollView.smoothScrollBy(0, -5);
            return false;

        case 20: // '\024'
            ScrollView.smoothScrollBy(0, 5);
            break;
        }
        return false;
    }

    protected void onCreate(Bundle bundle)
    {
        PackageManager packagemanager;
        super.onCreate(bundle);
        setContentView(0x7f030000);
        ScrollView = (ScrollView)findViewById(0x7f090009);
        mBackBtn = (ImageView)findViewById(0x7f090006);
        if (mBackBtn != null)
        {
            mBackBtn.setOnClickListener(mOnClickListener);
        }
        packagemanager = getPackageManager();
        PackageInfo packageinfo1 = packagemanager.getPackageInfo(getPackageName(), 0);
        PackageInfo packageinfo = packageinfo1;
_L2:
        if (packageinfo != null)
        {
            ((TextView)findViewById(0x7f090008)).setText((new StringBuilder()).append(getString(0x7f0b0113)).append(packageinfo.versionName).toString());
        }
        return;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        namenotfoundexception.printStackTrace();
        packageinfo = null;
        if (true) goto _L2; else goto _L1
_L1:
    }
}
