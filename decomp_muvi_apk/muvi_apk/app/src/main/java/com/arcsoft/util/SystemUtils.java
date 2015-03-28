// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class SystemUtils
{

    public SystemUtils()
    {
    }

    public static int getSDKVersion()
    {
        return android.os.Build.VERSION.SDK_INT;
    }

    public static boolean isLandscape(Context context)
    {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static void keepScreenOn(Activity activity, boolean flag)
    {
        if (flag)
        {
            activity.getWindow().addFlags(128);
            return;
        } else
        {
            activity.getWindow().clearFlags(128);
            return;
        }
    }

    public static void loadLibrary(String s)
    {
        try
        {
            System.loadLibrary(s);
            return;
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }

    public static void safeSleep(long l)
    {
        try
        {
            Thread.sleep(l);
            return;
        }
        catch (InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
        }
    }

    public static void unbindDrawables(View view)
    {
        if (view != null)
        {
            if (view.getBackground() != null)
            {
                view.getBackground().setCallback(null);
            }
            if (view instanceof ViewGroup)
            {
                for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++)
                {
                    unbindDrawables(((ViewGroup)view).getChildAt(i));
                }

                try
                {
                    ((ViewGroup)view).removeAllViews();
                    view.destroyDrawingCache();
                    return;
                }
                catch (Exception exception)
                {
                    return;
                }
            }
        }
    }
}
