// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;

import com.arcsoft.mediaplus.picture.common.Utils;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            Texture, GLCanvas

abstract class BasicTexture
    implements Texture
{

    protected static final int STATE_ERROR = -1;
    protected static final int STATE_LOADED = 1;
    protected static final int STATE_UNLOADED = 0;
    private static final String TAG = "BasicTexture";
    protected static final int UNSPECIFIED = -1;
    private static WeakHashMap sAllTextures = new WeakHashMap();
    private static ThreadLocal sInFinalizer = new ThreadLocal();
    protected WeakReference mCanvasRef;
    private boolean mHasBorder;
    protected int mHeight;
    protected int mId;
    protected int mState;
    private int mTextureHeight;
    private int mTextureWidth;
    protected int mWidth;

    protected BasicTexture()
    {
        this(null, 0, 0);
    }

    protected BasicTexture(GLCanvas glcanvas, int i, int j)
    {
        mWidth = -1;
        mHeight = -1;
        mCanvasRef = null;
        setAssociatedCanvas(glcanvas);
        mId = i;
        mState = j;
        synchronized (sAllTextures)
        {
            sAllTextures.put(this, null);
        }
        return;
        exception;
        weakhashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void freeResource()
    {
        GLCanvas glcanvas;
        if (mCanvasRef == null)
        {
            glcanvas = null;
        } else
        {
            glcanvas = (GLCanvas)mCanvasRef.get();
        }
        if (glcanvas != null && isLoaded(glcanvas))
        {
            glcanvas.unloadTexture(this);
        }
        mState = 0;
        setAssociatedCanvas(null);
    }

    public static boolean inFinalizer()
    {
        return sInFinalizer.get() != null;
    }

    public static void yieldAllTextures()
    {
        WeakHashMap weakhashmap = sAllTextures;
        weakhashmap;
        JVM INSTR monitorenter ;
        for (Iterator iterator = sAllTextures.keySet().iterator(); iterator.hasNext(); ((BasicTexture)iterator.next()).yield()) { }
        break MISSING_BLOCK_LABEL_47;
        Exception exception;
        exception;
        weakhashmap;
        JVM INSTR monitorexit ;
        throw exception;
        weakhashmap;
        JVM INSTR monitorexit ;
    }

    public void draw(GLCanvas glcanvas, int i, int j)
    {
        glcanvas.drawTexture(this, i, j, getWidth(), getHeight());
    }

    public void draw(GLCanvas glcanvas, int i, int j, int k, int l)
    {
        glcanvas.drawTexture(this, i, j, k, l);
    }

    protected void finalize()
    {
        sInFinalizer.set(com/arcsoft/mediaplus/picture/opengl/BasicTexture);
        recycle();
        sInFinalizer.set(null);
    }

    public int getHeight()
    {
        return mHeight;
    }

    public int getId()
    {
        return mId;
    }

    public int getTextureHeight()
    {
        return mTextureHeight;
    }

    public int getTextureWidth()
    {
        return mTextureWidth;
    }

    public int getWidth()
    {
        return mWidth;
    }

    public boolean hasBorder()
    {
        return mHasBorder;
    }

    public boolean isLoaded(GLCanvas glcanvas)
    {
        return mState == 1 && mCanvasRef.get() == glcanvas;
    }

    protected abstract boolean onBind(GLCanvas glcanvas);

    public void recycle()
    {
        freeResource();
    }

    protected void setAssociatedCanvas(GLCanvas glcanvas)
    {
        WeakReference weakreference;
        if (glcanvas == null)
        {
            weakreference = null;
        } else
        {
            weakreference = new WeakReference(glcanvas);
        }
        mCanvasRef = weakreference;
    }

    protected void setBorder(boolean flag)
    {
        mHasBorder = flag;
    }

    protected void setSize(int i, int j)
    {
        mWidth = i;
        mHeight = j;
        mTextureWidth = Utils.nextPowerOf2(i);
        mTextureHeight = Utils.nextPowerOf2(j);
    }

    public void yield()
    {
        freeResource();
    }

}
