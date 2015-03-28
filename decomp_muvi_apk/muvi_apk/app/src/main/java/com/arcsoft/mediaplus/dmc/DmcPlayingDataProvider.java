// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.app.Activity;
import android.net.Uri;
import com.arcsoft.mediaplus.datasource.DMCDataSource;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.ImageDataSourceHelper;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.datasource.RemoteDateStringItem;
import com.arcsoft.mediaplus.datasource.VideoDataSourceHelper;
import com.arcsoft.mediaplus.datasource.VideoItem;
import com.arcsoft.util.TimeUtils;
import java.text.SimpleDateFormat;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            RenderDevSelector

public class DmcPlayingDataProvider
{

    private Activity mContext;
    private long mCurrentId;
    private int mCurrentIndex;
    private DMCDataSource mDataSource;
    private SimpleDateFormat mDateFormater;
    private com.arcsoft.mediaplus.playengine.AbsPlayList.IOnPlaylistChangeListener mListener;
    private MediaItem mMultSelectedItem[];

    public DmcPlayingDataProvider(IDataSource idatasource, Activity activity)
    {
        mMultSelectedItem = null;
        mDataSource = null;
        mContext = null;
        mListener = null;
        mCurrentIndex = -1;
        mCurrentId = 0L;
        mDateFormater = null;
        mContext = activity;
        createDMCDataSource(idatasource);
        init();
    }

    public DmcPlayingDataProvider(MediaItem amediaitem[], IDataSource idatasource, Activity activity)
    {
        mMultSelectedItem = null;
        mDataSource = null;
        mContext = null;
        mListener = null;
        mCurrentIndex = -1;
        mCurrentId = 0L;
        mDateFormater = null;
        mMultSelectedItem = amediaitem;
        mContext = activity;
        createDMCDataSource(idatasource);
        init();
    }

    private long getLocalTakenTime(int i)
    {
        int j = getOriginIndex(i);
        Long long1;
        if (j == -1)
        {
            long1 = null;
        } else
        {
            long l;
            if (getMediaItem(i).videoOrImage)
            {
                l = VideoDataSourceHelper.getTakenTime(j, mDataSource);
            } else
            {
                l = ImageDataSourceHelper.getTakenTime(j, mDataSource);
            }
            long1 = Long.valueOf(l);
        }
        return long1.longValue();
    }

    private void init()
    {
        mDateFormater = new SimpleDateFormat("yyyy-MM-dd");
    }

    protected void createDMCDataSource(IDataSource idatasource)
    {
        if (idatasource == null || mContext == null)
        {
            return;
        } else
        {
            mDataSource = new DMCDataSource(idatasource, mContext.getApplication());
            mDataSource.init();
            return;
        }
    }

    public void destroy()
    {
    }

    public int getCount()
    {
        if (RenderDevSelector.CURRENT_PROVIDER_TYPE != DmcUtils.PROVIDER_TYPE.TYPE_FROM_MULTI_VIEW) goto _L2; else goto _L1
_L1:
        if (mMultSelectedItem != null) goto _L4; else goto _L3
_L3:
        return 0;
_L4:
        return mMultSelectedItem.length;
_L2:
        if (mDataSource != null)
        {
            return mDataSource.getCount();
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public long getCurrentId()
    {
        return mCurrentId;
    }

    public int getCurrentIndex()
    {
        return mCurrentIndex;
    }

    public String getDuration(int i)
    {
        MediaItem mediaitem = getMediaItem(i);
        if (mediaitem != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        if (!mediaitem.videoOrImage) goto _L1; else goto _L3
_L3:
        String s = TimeUtils.convertSecToTimeString((999L + ((VideoItem)mediaitem).duration) / 1000L, false);
        return s;
        Exception exception;
        exception;
        exception.printStackTrace();
        return null;
    }

    public long getId(int i)
    {
        long l = 0L;
        if (RenderDevSelector.CURRENT_PROVIDER_TYPE != DmcUtils.PROVIDER_TYPE.TYPE_FROM_MULTI_VIEW)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        if (mMultSelectedItem == null)
        {
            return l;
        }
        return mMultSelectedItem[i]._id;
        long l1;
        if (mDataSource == null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        l1 = mDataSource.getLongProp(i, Property.PROP_ID, 0L);
        return l1;
        Exception exception;
        exception;
        l = -1L;
        return l;
    }

    public int getListIndex(int i)
    {
        if (RenderDevSelector.CURRENT_PROVIDER_TYPE != DmcUtils.PROVIDER_TYPE.TYPE_FROM_MULTI_VIEW)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        MediaItem mediaitem;
        int j;
        long l;
        long l1;
        try
        {
            mediaitem = mDataSource.getItem(i);
        }
        catch (Exception exception)
        {
            return -1;
        }
        j = 0;
        if (j >= getCount())
        {
            break; /* Loop/switch isn't completed */
        }
        l = getMediaItem(j)._id;
        l1 = mediaitem._id;
        if (l == l1)
        {
            return j;
        }
        j++;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_21;
_L1:
        return -1;
        return i;
    }

    public MediaItem getMediaItem(int i)
    {
        MediaItem mediaitem;
        try
        {
            if (RenderDevSelector.CURRENT_PROVIDER_TYPE == DmcUtils.PROVIDER_TYPE.TYPE_FROM_MULTI_VIEW)
            {
                return mMultSelectedItem[i];
            }
            mediaitem = mDataSource.getItem(i);
        }
        catch (Exception exception)
        {
            return null;
        }
        return mediaitem;
    }

    public String getMediaName(int i)
    {
        MediaItem mediaitem;
        if (mDataSource != null)
        {
            if ((mediaitem = getMediaItem(i)) != null)
            {
                return mediaitem.title;
            }
        }
        return null;
    }

    public int getNextIndex()
    {
        if (mCurrentIndex >= -1 + getCount())
        {
            return -1;
        } else
        {
            return 1 + mCurrentIndex;
        }
    }

    public int getOriginIndex(int i)
    {
        if (RenderDevSelector.CURRENT_PROVIDER_TYPE != DmcUtils.PROVIDER_TYPE.TYPE_FROM_MULTI_VIEW) goto _L2; else goto _L1
_L1:
        MediaItem mediaitem;
        int j;
        long l;
        long l1;
        try
        {
            mediaitem = getMediaItem(i);
        }
        catch (Exception exception)
        {
            return -1;
        }
        j = 0;
        if (j >= mDataSource.getCount())
        {
            break; /* Loop/switch isn't completed */
        }
        l = mDataSource.getItem(j)._id;
        l1 = mediaitem._id;
        if (l == l1)
        {
            return j;
        }
        j++;
        if (true) goto _L3; else goto _L2
_L3:
        break MISSING_BLOCK_LABEL_18;
_L2:
        return i;
    }

    public void getPlayURLAsync(int i, com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc, Object obj, com.arcsoft.mediaplus.datasource.DMCDataSource.IOnGetPlayURLListener iongetplayurllistener)
    {
        com.arcsoft.mediaplus.datasource.DMCDataSource.GetURLRequest geturlrequest = new com.arcsoft.mediaplus.datasource.DMCDataSource.GetURLRequest();
        geturlrequest.index = getOriginIndex(i);
        geturlrequest.listener = iongetplayurllistener;
        geturlrequest.renderDesc = mediarenderdesc;
        geturlrequest.userdata = obj;
        mDataSource.getPlayURLAsync(geturlrequest, iongetplayurllistener);
    }

    public int getPrevIndex()
    {
        if (mCurrentIndex <= 0)
        {
            return -1;
        } else
        {
            return -1 + mCurrentIndex;
        }
    }

    public String getRemoteThumbnaiPath(int i)
    {
        if (mDataSource == null)
        {
            return null;
        } else
        {
            return mDataSource.getStringProp(getOriginIndex(i), Property.PROP_THUMBNAIL_FILEPATH, null);
        }
    }

    public String getTime(int i)
    {
        MediaItem mediaitem = getMediaItem(i);
        if (mediaitem == null)
        {
            return null;
        }
        String s;
        try
        {
            if (!RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL)
            {
                return ((RemoteDateStringItem)mediaitem).datestring;
            }
            s = mDateFormater.format(Long.valueOf(getLocalTakenTime(i)));
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return s;
    }

    public Uri getUri(int i)
    {
        if (mDataSource != null)
        {
            if (RenderDevSelector.CURRENT_PROVIDER_TYPE == DmcUtils.PROVIDER_TYPE.TYPE_FROM_MULTI_VIEW)
            {
                if (mMultSelectedItem != null)
                {
                    return mMultSelectedItem[i].uri;
                }
            } else
            {
                return (Uri)mDataSource.getObjectProp(i, Property.PROP_URI, null);
            }
        }
        return null;
    }

    public boolean hasNext()
    {
        return mCurrentIndex < -1 + getCount();
    }

    public boolean hasPrev()
    {
        return mCurrentIndex > 0;
    }

    public boolean isPlayOver()
    {
        return mCurrentIndex == -1 + getCount();
    }

    public boolean isPlaylistEmpty()
    {
        return getCount() == 0;
    }

    public boolean isVideoFile(int i)
    {
        MediaItem mediaitem = getMediaItem(i);
        if (mediaitem == null)
        {
            return false;
        } else
        {
            return mediaitem.videoOrImage;
        }
    }

    public int next()
    {
        int i;
        if (mCurrentIndex >= -1 + getCount())
        {
            i = -1;
        } else
        {
            i = 1 + mCurrentIndex;
        }
        if (i >= 0)
        {
            mCurrentIndex = i;
            mCurrentId = getId(i);
            if (mListener != null)
            {
                mListener.onPlayIndexChanged(i);
            }
        }
        return i;
    }

    public int prev()
    {
        int i;
        if (mCurrentIndex <= 0)
        {
            i = -1;
        } else
        {
            i = -1 + mCurrentIndex;
        }
        if (i >= 0)
        {
            mCurrentIndex = i;
            mCurrentId = getId(i);
            if (mListener != null)
            {
                mListener.onPlayIndexChanged(i);
            }
        }
        return i;
    }

    public void resetPlayingInfo()
    {
        mCurrentIndex = 0;
        mCurrentId = getId(0);
    }

    public void setCurrentIndex(int i, boolean flag)
    {
        mCurrentIndex = i;
        if (mListener != null && flag)
        {
            mListener.onPlayIndexChanged(mCurrentIndex);
        }
    }

    public void setOnPlaylistChangeListener(com.arcsoft.mediaplus.playengine.AbsPlayList.IOnPlaylistChangeListener ionplaylistchangelistener)
    {
        mListener = ionplaylistchangelistener;
    }
}
