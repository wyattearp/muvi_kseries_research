// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.graphics.Bitmap;
import android.graphics.Point;
import com.arcsoft.adk.image.IFileList;
import com.arcsoft.adk.image.ThumbEngine;
import com.arcsoft.mediaplus.oem.OEMConfig;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            CompoundDataSource, Property, IDataSource

public class PictureDataSource extends CompoundDataSource
{
    public static class PictureDataSourceController extends AbsDataSource.Controller
    {

        private final PictureDataSource mPictureDataSource;

        public volatile void release()
        {
            super.release();
        }

        public void slowDown(boolean flag)
        {
            mPictureDataSource.slowDown(mSession, flag);
        }

        public PictureDataSourceController(PictureDataSource picturedatasource, int i)
        {
            super(picturedatasource, i);
            mPictureDataSource = picturedatasource;
        }
    }


    public static final Property PROP_BITMAP = new Property("PROP_BITMAP");
    private static final String TAG = "PictureDataSource";
    private IFileList mFileList;
    private IDataSource.OnDataBuildListener mInternalDataBuildListener;
    private IDataSource.OnDataChangeListener mInternalDataChangeListener;
    private final boolean mIsDecodeThumbnail;
    private final com.arcsoft.adk.image.ThumbEngine.DecodeParam mOutputParam;
    private ThumbEngine mThumbEngine;
    private com.arcsoft.adk.image.ThumbEngine.ThumbEngineListener mThumbEngineListener;

    public PictureDataSource(IDataSource idatasource, com.arcsoft.adk.image.ThumbEngine.DecodeParam decodeparam, boolean flag)
    {
        super(idatasource);
        mIsDecodeThumbnail = flag;
        mOutputParam = decodeparam;
    }

    private void slowDown(int i, boolean flag)
    {
        checkSession(i);
        if (mThumbEngine != null)
        {
            mThumbEngine.slowDown(flag);
        }
    }

    protected IDataSource.IController createController(int i)
    {
        return new PictureDataSourceController(this, i);
    }

    public Bitmap getBitmap(int i)
    {
        if (isEnable() && mThumbEngine != null)
        {
            return mThumbEngine.getBitmap(i);
        } else
        {
            return null;
        }
    }

    public Bitmap getBitmap(int i, Point point)
    {
        int j = -1;
        int k = -1;
        boolean flag = isEnable();
        Bitmap bitmap = null;
        if (flag)
        {
            ThumbEngine thumbengine = mThumbEngine;
            bitmap = null;
            if (thumbengine != null)
            {
                bitmap = mThumbEngine.getBitmap(i);
            }
            if (bitmap != null && point != null)
            {
                com.arcsoft.adk.image.ThumbEngine.ThumbnailInfo thumbnailinfo = mThumbEngine.getThumbnailInfo(i);
                j = thumbnailinfo.orgWidth;
                k = thumbnailinfo.orgHeight;
            }
        }
        if (point != null)
        {
            point.x = j;
            point.y = k;
        }
        return bitmap;
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        if (property == PROP_BITMAP)
        {
            return getBitmap(i);
        } else
        {
            return super.getObjectProp(i, property, obj);
        }
    }

    protected void onDisable()
    {
        if (mThumbEngine != null)
        {
            mThumbEngine.enable(false);
        }
        mDataSource.unregisterOnDataChangeListener(mInternalDataChangeListener);
        mDataSource.unregisterOnDataBuildListener(mInternalDataBuildListener);
        if (mThumbEngine != null)
        {
            mThumbEngine.setFileList(null);
            mThumbEngine.setThumbEngineListener(null);
            ThumbEngine.unLock(mThumbEngine);
            mThumbEngine = null;
        }
        super.onDisable();
    }

    protected void onEnable()
    {
        super.onEnable();
        mThumbEngine = ThumbEngine.lock();
        if (mThumbEngine != null)
        {
            mThumbEngine.setOutputFormat(mOutputParam);
            mThumbEngine.setFileList(mFileList);
            mThumbEngine.setThumbEngineListener(mThumbEngineListener);
        }
        mDataSource.registerOnDataChangeListener(mInternalDataChangeListener);
        mDataSource.registerOnDataBuildListener(mInternalDataBuildListener);
        if (mThumbEngine != null)
        {
            mThumbEngine.enable(true);
            mThumbEngine.refresh();
        }
    }

    protected void onInit()
    {
        super.onInit();
        mFileList = new IFileList() {

            final PictureDataSource this$0;

            public int getCount()
            {
                return PictureDataSource.this.getCount();
            }

            public String getFilePath(int i)
            {
                boolean flag = mIsDecodeThumbnail;
                String s = null;
                if (!flag)
                {
                    s = getStringProp(i, Property.PROP_IMAGE_FILEPATH, null);
                }
                if (s == null)
                {
                    s = getStringProp(i, Property.PROP_THUMBNAIL_FILEPATH, null);
                }
                return s;
            }

            
            {
                this$0 = PictureDataSource.this;
                super();
            }
        };
        mThumbEngineListener = new com.arcsoft.adk.image.ThumbEngine.ThumbEngineListener() {

            final PictureDataSource this$0;

            public void onThumbReady(ThumbEngine thumbengine, int i)
            {
                notifyOnDataChanged(i, PictureDataSource.PROP_BITMAP);
            }

            
            {
                this$0 = PictureDataSource.this;
                super();
            }
        };
        mInternalDataChangeListener = new IDataSource.OnDataChangeListener() {

            final PictureDataSource this$0;

            public void onCountChanged(int i, int j)
            {
                refreshThumbEngine();
                notifyOnCountChanged(i, j);
            }

            public void onDataChanged(int i, Property property)
            {
                if (!OEMConfig.OPENGL_OPTIMIZE && (property.equals(Property.PROP_THUMBNAIL_FILEPATH) || property.equals(Property.PROP_IMAGE_FILEPATH)))
                {
                    if (mThumbEngine != null)
                    {
                        mThumbEngine.editItem(i);
                    }
                    return;
                } else
                {
                    notifyOnDataChanged(i, property);
                    return;
                }
            }

            
            {
                this$0 = PictureDataSource.this;
                super();
            }
        };
        mInternalDataBuildListener = new IDataSource.OnDataBuildListener() {

            final PictureDataSource this$0;

            public void onDataBuiltBegin()
            {
                NotifyOnDataBuiltBegin();
            }

            public void onDataBuiltFinish()
            {
                NotifyOnDataBuiltFinish();
            }

            
            {
                this$0 = PictureDataSource.this;
                super();
            }
        };
    }

    protected void onPause()
    {
        if (mThumbEngine != null)
        {
            mThumbEngine.pause();
        }
        super.onPause();
    }

    protected void onResume()
    {
        super.onResume();
        if (mThumbEngine != null)
        {
            mThumbEngine.resume();
        }
    }

    protected void onUninit()
    {
        mFileList = null;
        mThumbEngineListener = null;
        mInternalDataChangeListener = null;
        mInternalDataBuildListener = null;
        super.onUninit();
    }

    protected transient void prefetch(int i, int j, Property aproperty[])
    {
        if (OEMConfig.OPENGL_OPTIMIZE)
        {
            if (mDataSourceController != null)
            {
                mDataSourceController.prefetch(i, j, aproperty);
            }
        } else
        if (PROP_BITMAP.isBelongsTo(aproperty))
        {
            if (!mThumbEngine.isSlowDown() && mDataSourceController != null)
            {
                IDataSource.IController icontroller = mDataSourceController;
                Property aproperty1[] = new Property[1];
                Property property;
                if (mIsDecodeThumbnail)
                {
                    property = Property.PROP_THUMBNAIL_FILEPATH;
                } else
                {
                    property = Property.PROP_IMAGE_FILEPATH;
                }
                aproperty1[0] = property;
                icontroller.prefetch(i, j, aproperty1);
            }
            if (isEnable())
            {
                mThumbEngine.prefetch(i, j);
                return;
            }
        }
    }

    protected transient void prefetchEx(int ai[], Property aproperty[])
    {
        if (OEMConfig.OPENGL_OPTIMIZE)
        {
            if (mDataSourceController != null)
            {
                mDataSourceController.prefetchEx(ai, aproperty);
            }
        } else
        if (PROP_BITMAP.isBelongsTo(aproperty))
        {
            if (!mThumbEngine.isSlowDown() && mDataSourceController != null)
            {
                IDataSource.IController icontroller = mDataSourceController;
                Property aproperty1[] = new Property[1];
                int k;
                Property property;
                if (mIsDecodeThumbnail)
                {
                    property = Property.PROP_THUMBNAIL_FILEPATH;
                } else
                {
                    property = Property.PROP_IMAGE_FILEPATH;
                }
                aproperty1[0] = property;
                icontroller.prefetchEx(ai, aproperty1);
            }
            if (isEnable())
            {
                int i = 0;
                int j = 0;
                for (k = 0; k < ai.length; k++)
                {
                    if (i > ai[k])
                    {
                        i = ai[k];
                    }
                    if (j < ai[k])
                    {
                        j = ai[k];
                    }
                }

                mThumbEngine.prefetch(i, j);
                return;
            }
        }
    }

    protected void refresh()
    {
        super.refresh();
        if (mThumbEngine != null)
        {
            mThumbEngine.refresh();
        }
    }

    public void refreshThumbEngine()
    {
        if (mThumbEngine != null)
        {
            mThumbEngine.refresh();
        }
    }




}
