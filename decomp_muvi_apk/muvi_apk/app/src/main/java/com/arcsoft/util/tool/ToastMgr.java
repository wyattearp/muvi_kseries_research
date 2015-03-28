// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import android.content.Context;
import android.widget.Toast;

public class ToastMgr
{

    private static Toast mToast = null;

    public ToastMgr()
    {
    }

    public static void cancelToast()
    {
        if (mToast != null)
        {
            mToast.cancel();
            mToast = null;
        }
    }

    public static void showToast(Context context, int i, int j)
    {
        if (context == null)
        {
            return;
        } else
        {
            showToast(context, context.getString(i), j);
            return;
        }
    }

    public static void showToast(Context context, String s, int i)
    {
        if (context != null)
        {
            if (mToast == null)
            {
                mToast = Toast.makeText(context, s, i);
            } else
            {
                mToast.setText(s);
            }
            if (mToast != null)
            {
                mToast.show();
                return;
            }
        }
    }

}
