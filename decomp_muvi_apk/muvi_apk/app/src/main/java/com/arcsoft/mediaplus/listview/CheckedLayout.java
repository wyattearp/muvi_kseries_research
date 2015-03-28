// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.listview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class CheckedLayout extends LinearLayout
    implements Checkable
{

    private Checkable mCheckMarkView;

    public CheckedLayout(Context context)
    {
        super(context);
    }

    public CheckedLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.AEEMagiCam.MediaPlus.R.styleable.CheckedLayout);
        int i = typedarray.getResourceId(1, 0);
        if (i > 0)
        {
            setCheckMarkView(i);
        }
        setChecked(typedarray.getBoolean(0, false));
    }

    public boolean isChecked()
    {
        if (mCheckMarkView != null)
        {
            return mCheckMarkView.isChecked();
        } else
        {
            return false;
        }
    }

    public void setCheckMarkView(int i)
    {
        android.view.View view = findViewById(i);
        if (view != null && (view instanceof Checkable))
        {
            mCheckMarkView = (Checkable)view;
        }
    }

    public void setCheckMarkView(Checkable checkable)
    {
        if (checkable != null)
        {
            mCheckMarkView = checkable;
        }
    }

    public void setChecked(boolean flag)
    {
        if (mCheckMarkView != null)
        {
            mCheckMarkView.setChecked(flag);
        }
    }

    public void toggle()
    {
        if (mCheckMarkView != null)
        {
            mCheckMarkView.toggle();
        }
    }
}
