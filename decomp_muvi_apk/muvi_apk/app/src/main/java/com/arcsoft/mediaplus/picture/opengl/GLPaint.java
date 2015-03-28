// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;


public class GLPaint
{

    public static final int FLAG_ANTI_ALIAS = 1;
    private int mColor;
    private int mFlags;
    private float mLineWidth;

    public GLPaint()
    {
        mFlags = 0;
        mLineWidth = 1.0F;
        mColor = 0;
    }

    public boolean getAntiAlias()
    {
        return (1 & mFlags) != 0;
    }

    public int getColor()
    {
        return mColor;
    }

    public int getFlags()
    {
        return mFlags;
    }

    public float getLineWidth()
    {
        return mLineWidth;
    }

    public void setAntiAlias(boolean flag)
    {
        if (flag)
        {
            mFlags = 1 | mFlags;
            return;
        } else
        {
            mFlags = -2 & mFlags;
            return;
        }
    }

    public void setColor(int i)
    {
        mColor = i;
    }

    public void setFlags(int i)
    {
        mFlags = i;
    }

    public void setLineWidth(float f)
    {
        mLineWidth = f;
    }
}
