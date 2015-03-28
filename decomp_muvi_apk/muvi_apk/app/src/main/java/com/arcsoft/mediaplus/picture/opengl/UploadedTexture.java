// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;

import android.graphics.Bitmap;
import android.opengl.GLUtils;
import com.arcsoft.mediaplus.picture.common.Utils;
import java.util.HashMap;
import javax.microedition.khronos.opengles.GL11;

// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            BasicTexture, GLCanvas

public abstract class UploadedTexture extends BasicTexture
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


    private static final String TAG = "Texture";
    private static final int UPLOAD_LIMIT = 100;
    private static BorderKey sBorderKey = new BorderKey();
    private static HashMap sBorderLines = new HashMap();
    static float sCropRect[] = new float[4];
    static int sTextureId[] = new int[1];
    private static int sUploadedCount;
    protected Bitmap mBitmap;
    private int mBorder;
    private boolean mContentValid;
    private boolean mOpaque;
    private boolean mThrottled;

    protected UploadedTexture()
    {
        this(false);
    }

    protected UploadedTexture(boolean flag)
    {
        super(null, 0, 0);
        mContentValid = true;
        mOpaque = true;
        mThrottled = false;
        if (flag)
        {
            setBorder(true);
            mBorder = 1;
        }
    }

    private void freeBitmap()
    {
        boolean flag;
        if (mBitmap != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        Utils.assertTrue(flag);
        onFreeBitmap(mBitmap);
        mBitmap = null;
    }

    private Bitmap getBitmap()
    {
        if (mBitmap == null)
        {
            mBitmap = onGetBitmap();
            int i = mBitmap.getWidth() + 2 * mBorder;
            int j = mBitmap.getHeight() + 2 * mBorder;
            if (mWidth == -1)
            {
                setSize(i, j);
            } else
            if (mWidth != i || mHeight != j)
            {
                Object aobj[] = new Object[5];
                aobj[0] = toString();
                aobj[1] = Integer.valueOf(mWidth);
                aobj[2] = Integer.valueOf(mHeight);
                aobj[3] = Integer.valueOf(i);
                aobj[4] = Integer.valueOf(j);
                throw new IllegalStateException(String.format("cannot change size: this = %s, orig = %sx%s, new = %sx%s", aobj));
            }
        }
        return mBitmap;
    }

    private static Bitmap getBorderLine(boolean flag, android.graphics.Bitmap.Config config, int i)
    {
        BorderKey borderkey = sBorderKey;
        borderkey.vertical = flag;
        borderkey.config = config;
        borderkey.length = i;
        Bitmap bitmap = (Bitmap)sBorderLines.get(borderkey);
        if (bitmap == null)
        {
            if (flag)
            {
                bitmap = Bitmap.createBitmap(1, i, config);
            } else
            {
                bitmap = Bitmap.createBitmap(i, 1, config);
            }
            sBorderLines.put(borderkey.clone(), bitmap);
        }
        return bitmap;
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
        GL11 gl11;
        Bitmap bitmap;
        gl11 = glcanvas.getGLInstance();
        bitmap = getBitmap();
        if (bitmap == null) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        int k;
        int l;
        i = bitmap.getWidth();
        j = bitmap.getHeight();
        i + 2 * mBorder;
        j + 2 * mBorder;
        k = getTextureWidth();
        l = getTextureHeight();
        sCropRect[0] = mBorder;
        sCropRect[1] = j + mBorder;
        sCropRect[2] = i;
        sCropRect[3] = -j;
        gl11.glGenTextures(1, sTextureId, 0);
        gl11.glBindTexture(3553, sTextureId[0]);
        gl11.glTexParameterfv(3553, 35741, sCropRect, 0);
        gl11.glTexParameteri(3553, 10242, 33071);
        gl11.glTexParameteri(3553, 10243, 33071);
        gl11.glTexParameterf(3553, 10241, 9729F);
        gl11.glTexParameterf(3553, 10240, 9729F);
        if (i != k || j != l) goto _L4; else goto _L3
_L3:
        GLUtils.texImage2D(3553, 0, bitmap, 0);
_L7:
        freeBitmap();
        setAssociatedCanvas(glcanvas);
        mId = sTextureId[0];
        mState = 1;
        mContentValid = true;
        return;
_L4:
        int i1 = GLUtils.getInternalFormat(bitmap);
        int k1 = GLUtils.getType(bitmap);
        int j1 = k1;
_L5:
        android.graphics.Bitmap.Config config = bitmap.getConfig();
        if (config != null)
        {
            break MISSING_BLOCK_LABEL_280;
        }
        config = android.graphics.Bitmap.Config.ARGB_8888;
        gl11.glTexImage2D(3553, 0, i1, k, l, 0, i1, j1, null);
        GLUtils.texSubImage2D(3553, 0, mBorder, mBorder, bitmap, i1, j1);
        if (mBorder > 0)
        {
            GLUtils.texSubImage2D(3553, 0, 0, 0, getBorderLine(true, config, l), i1, j1);
            GLUtils.texSubImage2D(3553, 0, 0, 0, getBorderLine(false, config, k), i1, j1);
        }
        if (i + mBorder < k)
        {
            Bitmap bitmap2 = getBorderLine(true, config, l);
            GLUtils.texSubImage2D(3553, 0, i + mBorder, 0, bitmap2, i1, j1);
        }
        if (j + mBorder < l)
        {
            Bitmap bitmap1 = getBorderLine(false, config, k);
            GLUtils.texSubImage2D(3553, 0, 0, j + mBorder, bitmap1, i1, j1);
        }
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        freeBitmap();
        throw exception;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        j1 = 5121;
        if (true) goto _L5; else goto _L2
_L2:
        mState = -1;
        throw new RuntimeException("Texture load fail, no bitmap");
        if (true) goto _L7; else goto _L6
_L6:
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
        if (mWidth == -1)
        {
            getBitmap();
        }
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
        if (mWidth == -1)
        {
            getBitmap();
        }
        return mWidth;
    }

    public volatile boolean hasBorder()
    {
        return super.hasBorder();
    }

    protected void invalidateContent()
    {
        if (mBitmap != null)
        {
            freeBitmap();
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

    protected abstract void onFreeBitmap(Bitmap bitmap);

    protected abstract Bitmap onGetBitmap();

    public void recycle()
    {
        super.recycle();
        if (mBitmap != null)
        {
            freeBitmap();
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
        int k;
        k = 1 + sUploadedCount;
        sUploadedCount = k;
        if (k <= 100) goto _L4; else goto _L5
_L5:
        return;
_L4:
        uploadToCanvas(glcanvas);
        return;
_L2:
        if (!mContentValid)
        {
            Bitmap bitmap = getBitmap();
            int i = GLUtils.getInternalFormat(bitmap);
            int j = GLUtils.getType(bitmap);
            glcanvas.getGLInstance().glBindTexture(3553, mId);
            GLUtils.texSubImage2D(3553, 0, mBorder, mBorder, bitmap, i, j);
            freeBitmap();
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
