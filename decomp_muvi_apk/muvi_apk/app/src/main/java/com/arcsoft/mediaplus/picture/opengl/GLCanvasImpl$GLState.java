// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;

import android.opengl.GLES20;
import com.arcsoft.mediaplus.picture.common.Utils;
import javax.microedition.khronos.opengles.GL11;

// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            GLCanvasImpl

private static class te
{

    private boolean mBlendEnabled;
    private final GL11 mGL;
    private boolean mLineSmooth;
    private float mLineWidth;
    private int mTexEnvMode;
    private boolean mTexture2DEnabled;
    private float mTextureAlpha;

    public void setBlendEnabled(boolean flag)
    {
        if (mBlendEnabled == flag)
        {
            return;
        }
        mBlendEnabled = flag;
        if (flag)
        {
            GLES20.glEnable(3042);
            return;
        } else
        {
            GLES20.glDisable(3042);
            return;
        }
    }

    public void setColorMode(int i, float f)
    {
        boolean flag;
        float f1;
        if (!Utils.isOpaque(i) || f < 0.95F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setBlendEnabled(flag);
        mTextureAlpha = -1F;
        setTexture2DEnabled(false);
        f1 = (65535F * (f * (float)(i >>> 24))) / 255F / 255F;
        mGL.glColor4x(Math.round(f1 * (float)(0xff & i >> 16)), Math.round(f1 * (float)(0xff & i >> 8)), Math.round(f1 * (float)(i & 0xff)), Math.round(255F * f1));
    }

    public void setLineSmooth(boolean flag)
    {
        if (mLineSmooth == flag)
        {
            return;
        }
        mLineSmooth = flag;
        if (flag)
        {
            GLES20.glEnable(2848);
            return;
        } else
        {
            GLES20.glDisable(2848);
            return;
        }
    }

    public void setLineWidth(float f)
    {
        if (mLineWidth == f)
        {
            return;
        } else
        {
            mLineWidth = f;
            GLES20.glLineWidth(f);
            return;
        }
    }

    public void setTexEnvMode(int i)
    {
        if (mTexEnvMode == i)
        {
            return;
        } else
        {
            mTexEnvMode = i;
            mGL.glTexEnvf(8960, 8704, i);
            return;
        }
    }

    public void setTexture2DEnabled(boolean flag)
    {
        if (mTexture2DEnabled == flag)
        {
            return;
        }
        mTexture2DEnabled = flag;
        if (flag)
        {
            GLES20.glEnable(3553);
            return;
        } else
        {
            GLES20.glDisable(3553);
            return;
        }
    }

    public void setTextureAlpha(float f)
    {
        if (mTextureAlpha == f)
        {
            return;
        }
        mTextureAlpha = f;
        if (f >= 0.95F)
        {
            mGL.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            setTexEnvMode(7681);
            return;
        } else
        {
            mGL.glColor4f(f, f, f, f);
            setTexEnvMode(8448);
            return;
        }
    }

    public (GL11 gl11)
    {
        mTexEnvMode = 7681;
        mTextureAlpha = 1.0F;
        mTexture2DEnabled = true;
        mBlendEnabled = true;
        mLineWidth = 1.0F;
        mLineSmooth = false;
        mGL = gl11;
        GLES20.glDisable(2896);
        GLES20.glEnable(3024);
        GLES20.glEnable(3089);
        gl11.glEnableClientState(32884);
        gl11.glEnableClientState(32888);
        gl11.glEnable(3553);
        gl11.glTexEnvf(8960, 8704, 7681F);
        GLES20.glClearColor(160F, 160F, 160F, 0.0F);
        GLES20.glClearStencil(0);
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(1, 771);
        GLES20.glPixelStorei(3317, 2);
    }
}
