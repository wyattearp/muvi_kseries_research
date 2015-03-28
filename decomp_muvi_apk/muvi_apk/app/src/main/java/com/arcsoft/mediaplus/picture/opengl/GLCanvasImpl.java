// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;

import android.graphics.Rect;
import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.opengl.Matrix;
import com.arcsoft.mediaplus.picture.common.Utils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Stack;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;

// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            GLCanvas, IntArray, BasicTexture, RawTexture, 
//            GLPaint

public class GLCanvasImpl
    implements GLCanvas
{
    private static class ConfigState
    {

        float mAlpha;
        float mMatrix[];
        ConfigState mNextFree;
        Rect mRect;

        public void restore(GLCanvasImpl glcanvasimpl)
        {
            if (mAlpha >= 0.0F)
            {
                glcanvasimpl.setAlpha(mAlpha);
            }
            if (mRect.left != 0x7fffffff)
            {
                Rect rect = mRect;
                glcanvasimpl.mClipRect.set(rect);
                GLES20.glScissor(rect.left, rect.top, rect.width(), rect.height());
            }
            if (mMatrix[0] != (-1.0F / 0.0F))
            {
                System.arraycopy(mMatrix, 0, glcanvasimpl.mMatrixValues, 0, 16);
            }
        }

        private ConfigState()
        {
            mRect = new Rect();
            mMatrix = new float[16];
        }

    }

    private static class GLState
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

        public GLState(GL11 gl11)
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


    private static final float BOX_COORDINATES[] = {
        0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 
        1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F
    };
    private static final int MSCALE_X = 0;
    private static final int MSCALE_Y = 5;
    private static final int MSKEW_X = 4;
    private static final int MSKEW_Y = 1;
    private static final int OFFSET_DRAW_LINE = 4;
    private static final int OFFSET_DRAW_RECT = 6;
    private static final int OFFSET_FILL_RECT = 0;
    private static final float OPAQUE_ALPHA = 0.95F;
    private static final String TAG = "GLCanvasImp";
    private float mAlpha;
    private long mAnimationTime;
    private boolean mBlendEnabled;
    private int mBoxCoords;
    private final Rect mClipRect = new Rect();
    int mCountDrawLine;
    int mCountDrawMesh;
    int mCountFillRect;
    int mCountTextureOES;
    int mCountTextureRect;
    private final IntArray mDeleteBuffers = new IntArray();
    private final RectF mDrawTextureSourceRect = new RectF();
    private final RectF mDrawTextureTargetRect = new RectF();
    private final GL11 mGL;
    private final GLState mGLState;
    private int mHeight;
    private final float mMapPointsBuffer[] = new float[10];
    private final float mMatrixValues[] = new float[16];
    private ConfigState mRecycledRestoreAction;
    private final Stack mRestoreStack = new Stack();
    private final float mTempMatrix[] = new float[32];
    private final float mTextureColor[] = new float[4];
    private final float mTextureMatrixValues[] = new float[16];
    private final IntArray mUnboundTextures = new IntArray();

    public GLCanvasImpl(GL11 gl11)
    {
        mBlendEnabled = true;
        mGL = gl11;
        mGLState = new GLState(gl11);
        initialize();
    }

    private static ByteBuffer allocateDirectNativeOrderBuffer(int i)
    {
        return ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
    }

    private void convertCoordinate(RectF rectf, RectF rectf1, BasicTexture basictexture)
    {
        int i = basictexture.getWidth();
        int j = basictexture.getHeight();
        int k = basictexture.getTextureWidth();
        int l = basictexture.getTextureHeight();
        rectf.left = rectf.left / (float)k;
        rectf.right = rectf.right / (float)k;
        rectf.top = rectf.top / (float)l;
        rectf.bottom = rectf.bottom / (float)l;
        float f = (float)i / (float)k;
        if (rectf.right > f)
        {
            rectf1.right = rectf1.left + (rectf1.width() * (f - rectf.left)) / rectf.width();
            rectf.right = f;
        }
        float f1 = (float)j / (float)l;
        if (rectf.bottom > f1)
        {
            rectf1.bottom = rectf1.top + (rectf1.height() * (f1 - rectf.top)) / rectf.height();
            rectf.bottom = f1;
        }
    }

    private void drawBoundTexture(BasicTexture basictexture, int i, int j, int k, int l)
    {
        if (isMatrixRotatedOrFlipped(mMatrixValues))
        {
            if (basictexture.hasBorder())
            {
                setTextureCoords(1.0F / (float)basictexture.getTextureWidth(), 1.0F / (float)basictexture.getTextureHeight(), ((float)basictexture.getWidth() - 1.0F) / (float)basictexture.getTextureWidth(), ((float)basictexture.getHeight() - 1.0F) / (float)basictexture.getTextureHeight());
            } else
            {
                setTextureCoords(0.0F, 0.0F, (float)basictexture.getWidth() / (float)basictexture.getTextureWidth(), (float)basictexture.getHeight() / (float)basictexture.getTextureHeight());
            }
            textureRect(i, j, k, l);
        } else
        {
            float af[] = mapPoints(mMatrixValues, i, j + l, i + k, j);
            int i1 = Math.round(af[0]);
            int j1 = Math.round(af[1]);
            int k1 = Math.round(af[2]) - i1;
            int l1 = Math.round(af[3]) - j1;
            if (k1 > 0 && l1 > 0)
            {
                ((GL11Ext)mGL).glDrawTexiOES(i1, j1, 0, k1, l1);
                mCountTextureOES = 1 + mCountTextureOES;
                return;
            }
        }
    }

    private void drawMixed(BasicTexture basictexture, int i, float f, int j, int k, int l, int i1, 
            float f1)
    {
        if (f <= 0.0F)
        {
            drawTexture(basictexture, j, k, l, i1, f1);
        } else
        {
            if (f >= 1.0F)
            {
                fillRect(j, k, l, i1, i);
                return;
            }
            GLState glstate = mGLState;
            boolean flag;
            GL11 gl11;
            if (mBlendEnabled && (!basictexture.isOpaque() || !Utils.isOpaque(i) || f1 < 0.95F))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            glstate.setBlendEnabled(flag);
            gl11 = mGL;
            if (bindTexture(basictexture))
            {
                float f2 = 1.0F - f1 * f;
                float f3;
                if (f1 < 0.95F)
                {
                    mGLState.setTextureAlpha((f1 * (1.0F - f)) / f2);
                } else
                {
                    mGLState.setTextureAlpha(1.0F);
                }
                mGLState.setTexEnvMode(34160);
                f3 = (float)(i >>> 24) / 65025F;
                setTextureColor(f3 * (float)(0xff & i >>> 16), f3 * (float)(0xff & i >>> 8), f3 * (float)(i & 0xff), f2);
                gl11.glTexEnvfv(8960, 8705, mTextureColor, 0);
                gl11.glTexEnvf(8960, 34161, 34165F);
                gl11.glTexEnvf(8960, 34162, 34165F);
                gl11.glTexEnvf(8960, 34177, 34166F);
                gl11.glTexEnvf(8960, 34193, 768F);
                gl11.glTexEnvf(8960, 34185, 34166F);
                gl11.glTexEnvf(8960, 34201, 770F);
                gl11.glTexEnvf(8960, 34178, 34166F);
                gl11.glTexEnvf(8960, 34194, 770F);
                gl11.glTexEnvf(8960, 34186, 34166F);
                gl11.glTexEnvf(8960, 34202, 770F);
                drawBoundTexture(basictexture, j, k, l, i1);
                mGLState.setTexEnvMode(7681);
                return;
            }
        }
    }

    private void drawMixed(BasicTexture basictexture, BasicTexture basictexture1, float f, int i, int j, int k, int l, 
            float f1)
    {
        if (f <= 0.0F)
        {
            drawTexture(basictexture, i, j, k, l, f1);
        } else
        {
            if (f >= 1.0F)
            {
                drawTexture(basictexture1, i, j, k, l, f1);
                return;
            }
            boolean flag;
            GLState glstate;
            boolean flag1;
            GL11 gl11;
            if (basictexture.getWidth() == basictexture1.getWidth() && basictexture.getHeight() == basictexture1.getHeight())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            Utils.assertTrue(flag);
            glstate = mGLState;
            if (mBlendEnabled && (!basictexture.isOpaque() || !basictexture1.isOpaque() || f1 < 0.95F))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            glstate.setBlendEnabled(flag1);
            gl11 = mGL;
            if (bindTexture(basictexture))
            {
                float f2 = 1.0F - f1 * f;
                if (f1 < 0.95F)
                {
                    mGLState.setTextureAlpha((f1 * (1.0F - f)) / f2);
                } else
                {
                    mGLState.setTextureAlpha(1.0F);
                }
                gl11.glActiveTexture(33985);
                if (!bindTexture(basictexture1))
                {
                    gl11.glDisable(3553);
                    gl11.glActiveTexture(33984);
                    return;
                } else
                {
                    gl11.glEnable(3553);
                    mGLState.setTexEnvMode(34160);
                    gl11.glTexEnvf(8960, 34161, 34165F);
                    gl11.glTexEnvf(8960, 34162, 34165F);
                    setTextureColor(0.0F, 0.0F, 0.0F, f2);
                    gl11.glTexEnvfv(8960, 8705, mTextureColor, 0);
                    gl11.glTexEnvf(8960, 34178, 34166F);
                    gl11.glTexEnvf(8960, 34194, 770F);
                    gl11.glTexEnvf(8960, 34186, 34166F);
                    gl11.glTexEnvf(8960, 34202, 770F);
                    drawBoundTexture(basictexture1, i, j, k, l);
                    gl11.glDisable(3553);
                    gl11.glActiveTexture(33984);
                    return;
                }
            }
        }
    }

    private void freeRestoreConfig(ConfigState configstate)
    {
        configstate.mNextFree = mRecycledRestoreAction;
        mRecycledRestoreAction = configstate;
    }

    private void initialize()
    {
        GL11 gl11 = mGL;
        FloatBuffer floatbuffer = allocateDirectNativeOrderBuffer((32 * BOX_COORDINATES.length) / 8).asFloatBuffer();
        floatbuffer.put(BOX_COORDINATES, 0, BOX_COORDINATES.length).position(0);
        int ai[] = new int[1];
        gl11.glGenBuffers(1, ai, 0);
        mBoxCoords = ai[0];
        GLES20.glBindBuffer(34962, mBoxCoords);
        GLES20.glBufferData(34962, 4 * floatbuffer.capacity(), floatbuffer, 35044);
        gl11.glVertexPointer(2, 5126, 0, 0);
        gl11.glTexCoordPointer(2, 5126, 0, 0);
        gl11.glClientActiveTexture(33985);
        gl11.glTexCoordPointer(2, 5126, 0, 0);
        gl11.glClientActiveTexture(33984);
        gl11.glEnableClientState(32888);
        mAlpha = 1.0F;
    }

    private static boolean isMatrixRotatedOrFlipped(float af[])
    {
        boolean flag;
label0:
        {
            if (Math.abs(af[4]) <= 1E-05F && Math.abs(af[1]) <= 1E-05F && af[0] >= -1E-05F)
            {
                int i = af[5] != 1E-05F;
                flag = false;
                if (i <= 0)
                {
                    break label0;
                }
            }
            flag = true;
        }
        return flag;
    }

    private float[] mapPoints(float af[], int i, int j, int k, int l)
    {
        float af1[] = mMapPointsBuffer;
        af1[6] = i;
        af1[7] = j;
        af1[8] = 0.0F;
        af1[9] = 1.0F;
        Matrix.multiplyMV(af1, 0, af, 0, af1, 6);
        af1[0] = af1[0] / af1[3];
        af1[1] = af1[1] / af1[3];
        af1[6] = k;
        af1[7] = l;
        Matrix.multiplyMV(af1, 2, af, 0, af1, 6);
        af1[2] = af1[2] / af1[5];
        af1[3] = af1[3] / af1[5];
        return af1;
    }

    private ConfigState obtainRestoreConfig()
    {
        if (mRecycledRestoreAction != null)
        {
            ConfigState configstate = mRecycledRestoreAction;
            mRecycledRestoreAction = configstate.mNextFree;
            return configstate;
        } else
        {
            return new ConfigState();
        }
    }

    private void restoreTransform()
    {
        System.arraycopy(mTempMatrix, 0, mMatrixValues, 0, 16);
    }

    private void saveTransform()
    {
        System.arraycopy(mMatrixValues, 0, mTempMatrix, 0, 16);
    }

    private void setTextureColor(float f, float f1, float f2, float f3)
    {
        float af[] = mTextureColor;
        af[0] = f;
        af[1] = f1;
        af[2] = f2;
        af[3] = f3;
    }

    private void setTextureCoords(float f, float f1, float f2, float f3)
    {
        mGL.glMatrixMode(5890);
        mTextureMatrixValues[0] = f2 - f;
        mTextureMatrixValues[5] = f3 - f1;
        mTextureMatrixValues[10] = 1.0F;
        mTextureMatrixValues[12] = f;
        mTextureMatrixValues[13] = f1;
        mTextureMatrixValues[15] = 1.0F;
        mGL.glLoadMatrixf(mTextureMatrixValues, 0);
        mGL.glMatrixMode(5888);
    }

    private void setTextureCoords(RectF rectf)
    {
        setTextureCoords(rectf.left, rectf.top, rectf.right, rectf.bottom);
    }

    private void textureRect(float f, float f1, float f2, float f3)
    {
        GL11 gl11 = mGL;
        saveTransform();
        translate(f, f1, 0.0F);
        scale(f2, f3, 1.0F);
        gl11.glLoadMatrixf(mMatrixValues, 0);
        GLES20.glDrawArrays(5, 0, 4);
        restoreTransform();
        mCountTextureRect = 1 + mCountTextureRect;
    }

    public boolean bindTexture(BasicTexture basictexture)
    {
        if (!basictexture.onBind(this))
        {
            return false;
        } else
        {
            mGLState.setTexture2DEnabled(true);
            GLES20.glBindTexture(3553, basictexture.getId());
            return true;
        }
    }

    public void clearBuffer()
    {
        GLES20.glClear(16384);
    }

    public boolean clipRect(int i, int j, int k, int l)
    {
        float af[] = mapPoints(mMatrixValues, i, j, k, l);
        int i1;
        int j1;
        int k1;
        int l1;
        Rect rect;
        boolean flag;
        if (af[0] > af[2])
        {
            i1 = (int)af[2];
            j1 = (int)af[0];
        } else
        {
            i1 = (int)af[0];
            j1 = (int)af[2];
        }
        if (af[1] > af[3])
        {
            k1 = (int)af[3];
            l1 = (int)af[1];
        } else
        {
            k1 = (int)af[1];
            l1 = (int)af[3];
        }
        rect = mClipRect;
        flag = rect.intersect(i1, k1, j1, l1);
        if (!flag)
        {
            rect.set(0, 0, 0, 0);
        }
        GLES20.glScissor(rect.left, rect.top, rect.width(), rect.height());
        return flag;
    }

    public BasicTexture copyTexture(int i, int j, int k, int l)
    {
        if (isMatrixRotatedOrFlipped(mMatrixValues))
        {
            throw new IllegalArgumentException("cannot support rotated matrix");
        } else
        {
            float af[] = mapPoints(mMatrixValues, i, j + l, i + k, j);
            int i1 = (int)af[0];
            int j1 = (int)af[1];
            int k1 = (int)af[2] - i1;
            int l1 = (int)af[3] - j1;
            GL11 _tmp = mGL;
            RawTexture rawtexture = RawTexture.newInstance(this);
            GLES20.glBindTexture(3553, rawtexture.getId());
            rawtexture.setSize(k1, l1);
            GLES20.glTexParameteriv(3553, 35741, new int[] {
                0, 0, k1, l1
            }, 0);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            GLES20.glTexParameterf(3553, 10241, 9729F);
            GLES20.glTexParameterf(3553, 10240, 9729F);
            GLES20.glCopyTexImage2D(3553, 0, 6407, i1, j1, rawtexture.getTextureWidth(), rawtexture.getTextureHeight(), 0);
            return rawtexture;
        }
    }

    public long currentAnimationTimeMillis()
    {
        return mAnimationTime;
    }

    public void deleteBuffer(int i)
    {
        synchronized (mUnboundTextures)
        {
            mDeleteBuffers.add(i);
        }
        return;
        exception;
        intarray;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void deleteRecycledResources()
    {
        synchronized (mUnboundTextures)
        {
            IntArray intarray1 = mUnboundTextures;
            if (intarray1.size() > 0)
            {
                GLES20.glDeleteTextures(intarray1.size(), intarray1.getInternalArray(), 0);
                intarray1.clear();
            }
            IntArray intarray2 = mDeleteBuffers;
            if (intarray2.size() > 0)
            {
                GLES20.glDeleteBuffers(intarray2.size(), intarray2.getInternalArray(), 0);
                intarray2.clear();
            }
        }
        return;
        exception;
        intarray;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void drawLine(float f, float f1, float f2, float f3, GLPaint glpaint)
    {
        GL11 gl11 = mGL;
        mGLState.setColorMode(glpaint.getColor(), mAlpha);
        mGLState.setLineWidth(glpaint.getLineWidth());
        mGLState.setLineSmooth(glpaint.getAntiAlias());
        saveTransform();
        translate(f, f1, 0.0F);
        scale(f2 - f, f3 - f1, 1.0F);
        gl11.glLoadMatrixf(mMatrixValues, 0);
        GLES20.glDrawArrays(3, 4, 2);
        restoreTransform();
        mCountDrawLine = 1 + mCountDrawLine;
    }

    public void drawMesh(BasicTexture basictexture, int i, int j, int k, int l, int i1, int j1)
    {
        float f = mAlpha;
        if (!bindTexture(basictexture))
        {
            return;
        }
        GLState glstate = mGLState;
        boolean flag;
        if (mBlendEnabled && (!basictexture.isOpaque() || f < 0.95F))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        glstate.setBlendEnabled(flag);
        mGLState.setTextureAlpha(f);
        setTextureCoords(0.0F, 0.0F, 1.0F, 1.0F);
        saveTransform();
        translate(i, j, 0.0F);
        mGL.glLoadMatrixf(mMatrixValues, 0);
        GLES20.glBindBuffer(34962, k);
        mGL.glVertexPointer(2, 5126, 0, 0);
        GLES20.glBindBuffer(34962, l);
        mGL.glTexCoordPointer(2, 5126, 0, 0);
        GLES20.glBindBuffer(34963, i1);
        mGL.glDrawElements(5, j1, 5121, 0);
        GLES20.glBindBuffer(34962, mBoxCoords);
        mGL.glVertexPointer(2, 5126, 0, 0);
        mGL.glTexCoordPointer(2, 5126, 0, 0);
        restoreTransform();
        mCountDrawMesh = 1 + mCountDrawMesh;
    }

    public void drawMixed(BasicTexture basictexture, int i, float f, int j, int k, int l, int i1)
    {
        drawMixed(basictexture, i, f, j, k, l, i1, mAlpha);
    }

    public void drawMixed(BasicTexture basictexture, BasicTexture basictexture1, float f, int i, int j, int k, int l)
    {
        drawMixed(basictexture, basictexture1, f, i, j, k, l, mAlpha);
    }

    public void drawRect(float f, float f1, float f2, float f3, GLPaint glpaint)
    {
        GL11 gl11 = mGL;
        mGLState.setColorMode(glpaint.getColor(), mAlpha);
        mGLState.setLineWidth(glpaint.getLineWidth());
        mGLState.setLineSmooth(glpaint.getAntiAlias());
        saveTransform();
        translate(f, f1, 0.0F);
        scale(f2, f3, 1.0F);
        gl11.glLoadMatrixf(mMatrixValues, 0);
        GLES20.glDrawArrays(2, 6, 4);
        restoreTransform();
        mCountDrawLine = 1 + mCountDrawLine;
    }

    public void drawTexture(BasicTexture basictexture, int i, int j, int k, int l)
    {
        drawTexture(basictexture, i, j, k, l, mAlpha);
    }

    public void drawTexture(BasicTexture basictexture, int i, int j, int k, int l, float f)
    {
        if (k > 0 && l > 0)
        {
            GLState glstate = mGLState;
            boolean flag;
            if (mBlendEnabled && (!basictexture.isOpaque() || f < 0.95F))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            glstate.setBlendEnabled(flag);
            if (bindTexture(basictexture))
            {
                mGLState.setTextureAlpha(f);
                drawBoundTexture(basictexture, i, j, k, l);
                return;
            }
        }
    }

    public void drawTexture(BasicTexture basictexture, RectF rectf, RectF rectf1)
    {
        if (rectf1.width() > 0.0F && rectf1.height() > 0.0F)
        {
            mDrawTextureSourceRect.set(rectf);
            mDrawTextureTargetRect.set(rectf1);
            RectF rectf2 = mDrawTextureSourceRect;
            RectF rectf3 = mDrawTextureTargetRect;
            GLState glstate = mGLState;
            boolean flag;
            if (mBlendEnabled && (!basictexture.isOpaque() || mAlpha < 0.95F))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            glstate.setBlendEnabled(flag);
            if (bindTexture(basictexture))
            {
                convertCoordinate(rectf2, rectf3, basictexture);
                setTextureCoords(rectf2);
                mGLState.setTextureAlpha(mAlpha);
                textureRect(rectf3.left, rectf3.top, rectf3.width(), rectf3.height());
                return;
            }
        }
    }

    public void dumpStatisticsAndClear()
    {
        Object aobj[] = new Object[5];
        aobj[0] = Integer.valueOf(mCountDrawMesh);
        aobj[1] = Integer.valueOf(mCountTextureRect);
        aobj[2] = Integer.valueOf(mCountTextureOES);
        aobj[3] = Integer.valueOf(mCountFillRect);
        aobj[4] = Integer.valueOf(mCountDrawLine);
        String.format("MESH:%d, TEX_OES:%d, TEX_RECT:%d, FILL_RECT:%d, LINE:%d", aobj);
        mCountDrawMesh = 0;
        mCountTextureRect = 0;
        mCountTextureOES = 0;
        mCountFillRect = 0;
        mCountDrawLine = 0;
    }

    public void fillRect(float f, float f1, float f2, float f3, int i)
    {
        mGLState.setColorMode(i, mAlpha);
        GL11 gl11 = mGL;
        saveTransform();
        translate(f, f1, 0.0F);
        scale(f2, f3, 1.0F);
        gl11.glLoadMatrixf(mMatrixValues, 0);
        GLES20.glDrawArrays(5, 0, 4);
        restoreTransform();
        mCountFillRect = 1 + mCountFillRect;
    }

    public float getAlpha()
    {
        return mAlpha;
    }

    public GL11 getGLInstance()
    {
        return mGL;
    }

    public void multiplyAlpha(float f)
    {
        boolean flag;
        if (f >= 0.0F && f <= 1.0F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        Utils.assertTrue(flag);
        mAlpha = f * mAlpha;
    }

    public void multiplyMatrix(float af[], int i)
    {
        float af1[] = mTempMatrix;
        Matrix.multiplyMM(af1, 0, mMatrixValues, 0, af, i);
        System.arraycopy(af1, 0, mMatrixValues, 0, 16);
    }

    public void restore()
    {
        if (mRestoreStack.isEmpty())
        {
            throw new IllegalStateException();
        } else
        {
            ConfigState configstate = (ConfigState)mRestoreStack.pop();
            configstate.restore(this);
            freeRestoreConfig(configstate);
            return;
        }
    }

    public void rotate(float f, float f1, float f2, float f3)
    {
        float af[] = mTempMatrix;
        Matrix.setRotateM(af, 0, f, f1, f2, f3);
        Matrix.multiplyMM(af, 16, mMatrixValues, 0, af, 0);
        System.arraycopy(af, 16, mMatrixValues, 0, 16);
    }

    public int save()
    {
        return save(-1);
    }

    public int save(int i)
    {
        ConfigState configstate = obtainRestoreConfig();
        if ((i & 2) != 0)
        {
            configstate.mAlpha = mAlpha;
        } else
        {
            configstate.mAlpha = -1F;
        }
        if ((i & 1) != 0)
        {
            configstate.mRect.set(mClipRect);
        } else
        {
            configstate.mRect.left = 0x7fffffff;
        }
        if ((i & 4) != 0)
        {
            System.arraycopy(mMatrixValues, 0, configstate.mMatrix, 0, 16);
        } else
        {
            configstate.mMatrix[0] = (-1.0F / 0.0F);
        }
        mRestoreStack.push(configstate);
        return -1 + mRestoreStack.size();
    }

    public void scale(float f, float f1, float f2)
    {
        Matrix.scaleM(mMatrixValues, 0, f, f1, f2);
    }

    public void setAlpha(float f)
    {
        boolean flag;
        if (f >= 0.0F && f <= 1.0F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        Utils.assertTrue(flag);
        mAlpha = f;
    }

    public void setBlendEnabled(boolean flag)
    {
        mBlendEnabled = flag;
    }

    public void setCurrentAnimationTimeMillis(long l)
    {
        boolean flag;
        if (l >= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        Utils.assertTrue(flag);
        mAnimationTime = l;
    }

    public void setSize(int i, int j)
    {
        boolean flag;
        GL11 gl11;
        float af[];
        if (i >= 0 && j >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        Utils.assertTrue(flag);
        mHeight = j;
        gl11 = mGL;
        gl11.glViewport(0, 0, i, j);
        gl11.glMatrixMode(5889);
        gl11.glLoadIdentity();
        GLU.gluOrtho2D(gl11, 0.0F, i, 0.0F, j);
        gl11.glMatrixMode(5888);
        gl11.glLoadIdentity();
        af = mMatrixValues;
        Matrix.setIdentityM(af, 0);
        Matrix.translateM(af, 0, 0.0F, mHeight, 0.0F);
        Matrix.scaleM(af, 0, 1.0F, -1F, 1.0F);
        mClipRect.set(0, 0, i, j);
        gl11.glScissor(0, 0, i, j);
    }

    public void translate(float f, float f1, float f2)
    {
        Matrix.translateM(mMatrixValues, 0, f, f1, f2);
    }

    public boolean unloadTexture(BasicTexture basictexture)
    {
label0:
        {
            synchronized (mUnboundTextures)
            {
                if (basictexture.isLoaded(this))
                {
                    break label0;
                }
            }
            return false;
        }
        mUnboundTextures.add(basictexture.mId);
        intarray;
        JVM INSTR monitorexit ;
        return true;
        exception;
        intarray;
        JVM INSTR monitorexit ;
        throw exception;
    }



}
