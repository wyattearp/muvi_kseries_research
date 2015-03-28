// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;

import android.opengl.GLES20;
import com.arcsoft.mediaplus.picture.common.Utils;
import com.arcsoft.util.debug.Log;
import java.nio.ByteBuffer;
import java.util.HashMap;

// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            BasicTexture, GLCanvas

public class EtcTexture extends BasicTexture
{
    private static class BorderKey
        implements Cloneable
    {

        public android.graphics.Bitmap.Config config;
        public int length;
        public boolean vertical;

        public BorderKey clone()
        {
            BorderKey borderkey;
            try
            {
                borderkey = (BorderKey)super.clone();
            }
            catch (CloneNotSupportedException clonenotsupportedexception)
            {
                throw new AssertionError(clonenotsupportedexception);
            }
            return borderkey;
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public boolean equals(Object obj)
        {
            BorderKey borderkey;
            if (obj instanceof BorderKey)
            {
                if (vertical == (borderkey = (BorderKey)obj).vertical && config == borderkey.config && length == borderkey.length)
                {
                    return true;
                }
            }
            return false;
        }

        public int hashCode()
        {
            int i = config.hashCode() ^ length;
            if (vertical)
            {
                return i;
            } else
            {
                return -i;
            }
        }

        private BorderKey()
        {
        }

    }


    private static final String TAG = "EtcTexture";
    private static final int UPLOAD_LIMIT = 100;
    private static BorderKey sBorderKey = new BorderKey();
    private static HashMap sBorderLines = new HashMap();
    static float sCropRect[] = new float[4];
    static int sTextureId[] = new int[1];
    private static int sUploadedCount;
    private int mBorder;
    private boolean mContentValid;
    private android.opengl.ETC1Util.ETC1Texture mEtc1texture;
    private boolean mOpaque;
    private boolean mThrottled;

    public EtcTexture(android.opengl.ETC1Util.ETC1Texture etc1texture)
    {
        this(false);
        mEtc1texture = etc1texture;
    }

    protected EtcTexture(boolean flag)
    {
        super(null, 0, 0);
        mEtc1texture = null;
        mContentValid = true;
        mOpaque = true;
        mThrottled = false;
        if (flag)
        {
            setBorder(true);
            mBorder = 1;
        }
    }

    private void freeETC()
    {
        boolean flag;
        if (mEtc1texture != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        Utils.assertTrue(flag);
        mEtc1texture = null;
    }

    public static void resetUploadLimit()
    {
        sUploadedCount = 0;
    }

    public static boolean uploadLimitReached()
    {
        return sUploadedCount > 100;
    }

    private void uploadToCanvas(GLCanvas glcanvas)
    {
        glcanvas.getGLInstance();
        if (mEtc1texture == null)
        {
            break MISSING_BLOCK_LABEL_304;
        }
        int i;
        int j;
        int k;
        int l;
        i = mEtc1texture.getWidth();
        j = mEtc1texture.getHeight();
        setSize(i, j);
        k = getTextureWidth();
        l = getTextureHeight();
        sCropRect[0] = mBorder;
        sCropRect[1] = j + mBorder;
        sCropRect[2] = i;
        sCropRect[3] = -j;
        GLES20.glGenTextures(1, sTextureId, 0);
        GLES20.glBindTexture(3553, sTextureId[0]);
        GLES20.glTexParameterfv(3553, 35741, sCropRect, 0);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexParameterf(3553, 10241, 9729F);
        GLES20.glTexParameterf(3553, 10240, 9729F);
        if (i != k || j != l) goto _L2; else goto _L1
_L1:
        GLES20.glCompressedTexImage2D(3553, 0, 36196, i, j, 0, mEtc1texture.getData().capacity(), mEtc1texture.getData());
_L4:
        freeETC();
        setAssociatedCanvas(glcanvas);
        mId = sTextureId[0];
        mState = 1;
        mContentValid = true;
        return;
_L2:
        GLES20.glTexImage2D(3553, 0, 36196, k, l, 0, 36196, 33635, null);
        GLES20.glCompressedTexSubImage2D(3553, 0, 0, 0, i, j, 36196, mEtc1texture.getData().capacity(), mEtc1texture.getData());
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        freeETC();
        throw exception;
        mState = -1;
        try
        {
            Log.e("EtcTexture", "Texture load fail, no etc");
            throw new RuntimeException("Texture load fail, no etc");
        }
        catch (RuntimeException runtimeexception)
        {
            runtimeexception.printStackTrace();
        }
        return;
    }

    public volatile void draw(GLCanvas glcanvas, int i, int j)
    {
        super.draw(glcanvas, i, j);
    }

    public volatile void draw(GLCanvas glcanvas, int i, int j, int k, int l)
    {
        super.draw(glcanvas, i, j, k, l);
    }

    public int getHeight()
    {
        return mHeight;
    }

    public volatile int getId()
    {
        return super.getId();
    }

    public volatile int getTextureHeight()
    {
        return super.getTextureHeight();
    }

    public volatile int getTextureWidth()
    {
        return super.getTextureWidth();
    }

    public int getWidth()
    {
        return mWidth;
    }

    public volatile boolean hasBorder()
    {
        return super.hasBorder();
    }

    protected void invalidateContent()
    {
        if (mEtc1texture != null)
        {
            freeETC();
        }
        mContentValid = false;
    }

    public boolean isContentValid(GLCanvas glcanvas)
    {
        return isLoaded(glcanvas) && mContentValid;
    }

    public volatile boolean isLoaded(GLCanvas glcanvas)
    {
        return super.isLoaded(glcanvas);
    }

    public boolean isOpaque()
    {
        return mOpaque;
    }

    protected boolean onBind(GLCanvas glcanvas)
    {
        updateContent(glcanvas);
        return isContentValid(glcanvas);
    }

    public void recycle()
    {
        super.recycle();
        if (mEtc1texture != null)
        {
            freeETC();
        }
    }

    public void setOpaque(boolean flag)
    {
        mOpaque = flag;
    }

    protected void setThrottled(boolean flag)
    {
        mThrottled = flag;
    }

    public void updateContent(GLCanvas glcanvas)
    {
        if (isLoaded(glcanvas)) goto _L2; else goto _L1
_L1:
        if (!mThrottled) goto _L4; else goto _L3
_L3:
        int i;
        i = 1 + sUploadedCount;
        sUploadedCount = i;
        if (i <= 100) goto _L4; else goto _L5
_L5:
        return;
_L4:
        uploadToCanvas(glcanvas);
        return;
_L2:
        if (!mContentValid)
        {
            mContentValid = true;
            return;
        }
        if (true) goto _L5; else goto _L6
_L6:
    }

    public volatile void yield()
    {
        super.yield();
    }

}
