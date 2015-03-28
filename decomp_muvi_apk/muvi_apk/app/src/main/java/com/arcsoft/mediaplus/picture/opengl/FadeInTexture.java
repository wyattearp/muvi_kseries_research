// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;

import android.os.SystemClock;
import com.arcsoft.mediaplus.picture.common.Utils;

// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            Texture, BasicTexture, GLCanvas

public class FadeInTexture
    implements Texture
{

    private static final int DURATION = 180;
    private static final String TAG = "FadeInTexture";
    private final int mColor;
    private final int mHeight;
    private boolean mIsAnimating;
    private final boolean mIsOpaque;
    private final long mStartTime = now();
    private final BasicTexture mTexture;
    private final int mWidth;

    public FadeInTexture(int i, BasicTexture basictexture)
    {
        mColor = i;
        mTexture = basictexture;
        mWidth = mTexture.getWidth();
        mHeight = mTexture.getHeight();
        mIsOpaque = mTexture.isOpaque();
        mIsAnimating = true;
    }

    private float getRatio()
    {
        return Utils.clamp(1.0F - (float)(now() - mStartTime) / 180F, 0.0F, 1.0F);
    }

    private long now()
    {
        return SystemClock.uptimeMillis();
    }

    public void draw(GLCanvas glcanvas, int i, int j)
    {
        draw(glcanvas, i, j, mWidth, mHeight);
    }

    public void draw(GLCanvas glcanvas, int i, int j, int k, int l)
    {
        if (isAnimating())
        {
            glcanvas.drawMixed(mTexture, mColor, getRatio(), i, j, k, l);
            return;
        } else
        {
            mTexture.draw(glcanvas, i, j, k, l);
            return;
        }
    }

    public int getHeight()
    {
        return mHeight;
    }

    public int getWidth()
    {
        return mWidth;
    }

    public boolean isAnimating()
    {
        if (mIsAnimating && now() - mStartTime >= 180L)
        {
            mIsAnimating = false;
        }
        return false;
    }

    public boolean isOpaque()
    {
        return mIsOpaque;
    }

    public void recycle()
    {
        mTexture.recycle();
    }
}
