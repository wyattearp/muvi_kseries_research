// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.arcsoft.mediaplus.MediaPlusActivity;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.picture.controller.CacheMgr;
import com.arcsoft.mediaplus.picture.opengl.FadeInTexture;
import com.arcsoft.mediaplus.picture.opengl.GLCanvasImpl;
import com.arcsoft.util.debug.Log;
import java.util.HashMap;
import javax.microedition.khronos.opengles.GL10;

// Referenced classes of package com.arcsoft.mediaplus.picture.ui:
//            BaseView, LayoutController

public class ThumbGlView extends BaseView
{

    private static final int PADDING_LEFT = 8;
    private static final int PADDING_TOP = 8;
    private static final int SPACE_X = 10;
    private static final int SPACE_Y = 10;
    private static final int UNIT_COUNT_L = 6;
    private static final int UNIT_COUNT_P = 4;
    private final Context mContext;
    protected int mCurSurfaceWidth;
    boolean mIsScreenReady;
    protected boolean mKeepPosition;
    int mLastIndex;
    protected com.arcsoft.mediaplus.listview.IItemListView.IListOpListener mOpListener;
    protected int mScreenOrientation;

    public ThumbGlView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mOpListener = null;
        mScreenOrientation = 0;
        mKeepPosition = false;
        mIsScreenReady = false;
        mCurSurfaceWidth = 0;
        mLastIndex = 0;
        mContext = context;
    }

    private boolean checkViewSize(int i, int j)
    {
        if (!isViewSizeValid(i, j))
        {
            mIsScreenReady = false;
        }
        mIsScreenReady = true;
        return mIsScreenReady;
    }

    private void resetScreenIfNeeded()
    {
        if (mLayoutController != null && mDataSource != null)
        {
            byte byte0 = 4;
            int i;
            int j;
            int k;
            int l;
            if (getResources().getConfiguration().orientation == 2)
            {
                byte0 = 6;
            } else
            if (getResources().getConfiguration().orientation == 1)
            {
                byte0 = 4;
            }
            i = getWidth();
            j = getHeight();
            k = (i - byte0 * 11) / byte0;
            mLayoutController.setPadding(8, 8, 10, 10);
            l = (int)mContext.getResources().getDimension(0x7f080057);
            mLayoutController.setLayoutSize(i, j, k, k, mDataSource.getCount(), byte0, l);
            mLayoutController.setOnePageHeight(j);
            updateScrollPosition(mLayoutController.getPosition(), true);
            if (mCanvas != null)
            {
                mCanvas.setSize(i, j);
                return;
            }
        }
    }

    private boolean setDecodePath(MediaItem mediaitem, int i)
    {
        if (mContext == null || mDataSource == null || mediaitem == null)
        {
            return false;
        }
        if (((MediaPlusActivity)mContext).isRemote())
        {
            mediaitem.strDecodePath = mDataSource.getStringProp(i, Property.PROP_THUMBNAIL_FILEPATH, null);
            return mediaitem.strDecodePath != null;
        } else
        {
            return true;
        }
    }

    boolean canDrawFrame()
    {
        return mIsScreenReady;
    }

    boolean doSelectItem(int i)
    {
        return false;
    }

    int getLastIndex()
    {
        return mLastIndex;
    }

    int getUnitCount()
    {
        byte byte0 = 4;
        if (getResources().getConfiguration().orientation == 2)
        {
            byte0 = 6;
        } else
        if (getResources().getConfiguration().orientation == 1)
        {
            return 4;
        }
        return byte0;
    }

    public void init(CacheMgr cachemgr)
    {
        super.init(cachemgr);
    }

    boolean isViewSizeValid(int i, int j)
    {
        if (getResources().getConfiguration().orientation != 2) goto _L2; else goto _L1
_L1:
        if (i <= j) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (j <= i)
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        Log.e("ThumbGlView", (new StringBuilder()).append("onConfigurationChanged: mScreenOrientation = ").append(mScreenOrientation).toString());
    }

    public void onLongPress(MotionEvent motionevent)
    {
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if (mLayoutController != null)
        {
            setLastIndex(mLayoutController.getVisibleStart());
        }
        return super.onScroll(motionevent, motionevent1, f, f1);
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        int i = (int)motionevent.getX();
        int j = (int)motionevent.getY();
        if (mScrolling)
        {
            mScrolling = false;
        } else
        if (mLayoutController != null && mDataSource != null)
        {
            int k = mLayoutController.getIndexByPosition(i, j);
            if (k >= 0 && k < mDataSource.getCount())
            {
                Log.d("ThumbGlView", (new StringBuilder()).append("clicked item index = ").append(k).toString());
                return doSelectItem(k);
            }
        }
        return false;
    }

    public void onSurfaceChanged(GL10 gl10, int i, int j)
    {
        if (mDataSource == null)
        {
            return;
        }
        if (!checkViewSize(i, j))
        {
            Log.e("test", "Screen size is ilegal");
            return;
        }
        if (getResources().getConfiguration().orientation != 2) goto _L2; else goto _L1
_L1:
        if (mSurfaceWidthMax == 0)
        {
            mSurfaceWidthMax = i;
            mSurfaceHeightMin = j;
        }
_L4:
        resetScreenIfNeeded();
        if (mScreenOrientation != getResources().getConfiguration().orientation || mCurSurfaceWidth != i)
        {
            mScreenOrientation = getResources().getConfiguration().orientation;
            mCurSurfaceWidth = i;
            resetPositionAfterRotate();
        }
        mClipRect.set(0, 0, i, j);
        mClipRetryCount = 2;
        return;
_L2:
        if (getResources().getConfiguration().orientation == 1 && mSurfaceWidthMin == 0)
        {
            mSurfaceWidthMin = i;
            mSurfaceHeightMax = j;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void preDecode(int i, int j, int k)
    {
        while (mDataSource == null || mTextures == null || j < i) 
        {
            return;
        }
        if (mLayoutController.mCount <= mTextures.size())
        {
            Log.i("ThumbGlView", "all cached");
            Log.e("ThumbGlView", (new StringBuilder()).append("mLayoutController.mCount = ").append(mLayoutController.mCount).toString());
            Log.e("ThumbGlView", (new StringBuilder()).append("mTextures.size() = ").append(mTextures.size()).toString());
            return;
        }
        HashMap hashmap = mTextures;
        hashmap;
        JVM INSTR monitorenter ;
        int l = 0;
_L19:
        if (l >= i) goto _L2; else goto _L1
_L1:
        FadeInTexture fadeintexture1 = getTexture(l);
        if (fadeintexture1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        removeTexture(l);
        fadeintexture1.recycle();
        continue; /* Loop/switch isn't completed */
_L20:
        int i1;
        if (i1 >= mLayoutController.mCount) goto _L4; else goto _L3
_L3:
        FadeInTexture fadeintexture = getTexture(i1);
        if (fadeintexture == null) goto _L6; else goto _L5
_L5:
        removeTexture(i1);
        fadeintexture.recycle();
          goto _L6
_L4:
        if (mCacheMgr != null)
        {
            mCacheMgr.removeAllTask();
        }
        if (k == 0) goto _L8; else goto _L7
_L7:
        if (k <= 0) goto _L10; else goto _L9
_L9:
        int i2 = mLayoutController.getVisibleEnd();
          goto _L11
_L21:
        int j2;
        if (getTexture(j2) != null || j2 < 0)
        {
            break MISSING_BLOCK_LABEL_296;
        }
        if (mDataSource != null)
        {
            setDecodePath(mDataSource.getItem(j2), j2);
            if (mCacheMgr != null)
            {
                mCacheMgr.submitTask(this, mDataSource.getItem(j2), j2);
            }
        }
        int k2;
        if (getTexture(k2) == null && k2 < mLayoutController.mCount && mDataSource != null)
        {
            setDecodePath(mDataSource.getItem(k2), k2);
            if (mCacheMgr != null)
            {
                mCacheMgr.submitTask(this, mDataSource.getItem(k2), k2);
            }
        }
          goto _L12
_L10:
        i2 = mLayoutController.getVisibleStart();
          goto _L11
_L8:
        int j1 = mLayoutController.getVisibleStart();
_L24:
        if (j1 >= mLayoutController.getVisibleEnd()) goto _L14; else goto _L13
_L13:
        if (getTexture(j1) != null || j1 < 0) goto _L16; else goto _L15
_L15:
        if (mDataSource != null)
        {
            setDecodePath(mDataSource.getItem(j1), j1);
            if (mCacheMgr != null)
            {
                mCacheMgr.submitTask(this, mDataSource.getItem(j1), j1);
            }
        }
          goto _L16
_L14:
        int k1;
        int l1;
        k1 = -1 + mLayoutController.getVisibleStart();
        l1 = 1 + mLayoutController.getVisibleEnd();
          goto _L17
_L25:
        if (getTexture(k1) != null || k1 < 0)
        {
            break MISSING_BLOCK_LABEL_562;
        }
        if (mDataSource != null)
        {
            setDecodePath(mDataSource.getItem(k1), k1);
            if (mCacheMgr != null)
            {
                mCacheMgr.submitTask(this, mDataSource.getItem(k1), k1);
            }
        }
        if (getTexture(l1) == null && l1 < mLayoutController.mCount && mDataSource != null)
        {
            setDecodePath(mDataSource.getItem(l1), l1);
            if (mCacheMgr != null)
            {
                mCacheMgr.submitTask(this, mDataSource.getItem(l1), l1);
            }
        }
          goto _L18
_L22:
        hashmap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
          goto _L19
_L2:
        i1 = j + 1;
          goto _L20
_L6:
        i1++;
          goto _L20
_L11:
        j2 = i2;
        k2 = i2 + 1;
_L23:
        if (j2 < i && k2 > j) goto _L22; else goto _L21
_L12:
        j2--;
        k2++;
          goto _L23
_L16:
        j1++;
          goto _L24
_L17:
        if (k1 < i && l1 > j) goto _L22; else goto _L25
_L18:
        k1--;
        l1++;
          goto _L17
    }

    public void refresh(boolean flag)
    {
        Log.e("ThumbGlView", (new StringBuilder()).append("refreshGlView: this = ").append(this).toString());
        if (mCanvas == null || mDataSource == null || mDataSource.getCount() < 0)
        {
            return;
        }
        if (mLayoutController.getPosition() != 0 && mDataSource.getCount() <= mLayoutController.getCountOnePage())
        {
            mLayoutController.setPosition(0);
        }
        synchronized (mSyncRefreshObject)
        {
            pause(flag);
            resetScreenIfNeeded();
            resume(false);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void resetPositionAfterRotate()
    {
        if (mLayoutController == null)
        {
            return;
        }
        int i = getLastIndex();
        int j = getUnitCount();
        int k = i / j;
        int l = (getWidth() - j * 11) / j;
        int i1 = 0;
        if (k > 0)
        {
            i1 = 8 + k * l + 10 * (k - 1);
        }
        mLayoutController.setPosition(i1);
        updateScrollPosition(mLayoutController.getPosition(), true);
    }

    protected void setItemVisibleInScreenEx(int i)
    {
        if (mLayoutController != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Log.e("ThumbGlView", (new StringBuilder()).append("setItemVisibleInScreenEx: index = ").append(i).toString());
        Log.e("ThumbGlView", (new StringBuilder()).append("setItemVisibleInScreenEx: mLayoutController.getItemCount() = ").append(mLayoutController.getItemCount()).toString());
        if (i < 0 || i >= mLayoutController.getItemCount()) goto _L1; else goto _L3
_L3:
        int j;
        int k;
        j = mLayoutController.getVisibleStart();
        k = mLayoutController.getVisibleEnd();
        Log.e("ThumbGlView", (new StringBuilder()).append("setItemVisibleInScreenEx: first = ").append(j).toString());
        Log.e("ThumbGlView", (new StringBuilder()).append("setItemVisibleInScreenEx: last = ").append(k).toString());
        if (i >= j && i <= k || mCanvas == null || mDataSource == null || mDataSource.getCount() < 0) goto _L1; else goto _L4
_L4:
        Object obj = mSyncRefreshObject;
        obj;
        JVM INSTR monitorenter ;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        l = getUnitCount();
        i1 = getWidth();
        j1 = getHeight();
        k1 = (i1 - l * 11) / l;
        l1 = i / l;
        int i2;
        i2 = 0;
        if (l1 > 0)
        {
            i2 = 8 + l1 * k1 + 10 * (l1 - 1);
        }
        mLayoutController.setPosition(i2);
        mLayoutController.setPadding(8, 8, 10, 10);
        int j2 = (int)mContext.getResources().getDimension(0x7f080057);
        mLayoutController.setLayoutSize(i1, j1, k1, k1, mDataSource.getCount(), l, j2);
        updateScrollPosition(mLayoutController.getPosition(), true);
        mCanvas.setSize(i1, j1);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void setLastIndex(int i)
    {
        mLastIndex = i;
    }

    public void uninit()
    {
        pause(true);
        super.uninit();
    }
}
