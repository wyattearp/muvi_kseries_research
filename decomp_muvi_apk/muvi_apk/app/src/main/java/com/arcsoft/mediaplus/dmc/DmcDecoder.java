// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.picture.controller.LocalFrameDecoder;
import com.arcsoft.util.VideoUtils;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcPlayingDataProvider, RenderDevSelector, IDmcDecoder

public class DmcDecoder
{

    private final String TAG = "Dmc";
    protected Context mContext;
    protected DmcPlayingDataProvider mDataProvider;
    protected boolean mDecodeExit;
    protected Thread mDecodeThread;
    protected IDmcDecoder mDecoderListener;
    private final String mThreadNameDMS = "DmcDecoderDmsBoot";
    private final String mThreadNameLocal = "DmcDecoderLocal";
    protected int mThumbnailHeight;
    protected int mThumbnailWidth;
    protected int mTvCoverHeight;
    protected int mTvCoverWidth;

    public DmcDecoder(Context context, DmcPlayingDataProvider dmcplayingdataprovider)
    {
        mThumbnailWidth = 80;
        mThumbnailHeight = 80;
        mTvCoverWidth = 80;
        mTvCoverHeight = 80;
        mContext = null;
        mDecodeThread = null;
        mDataProvider = null;
        mDecodeExit = false;
        mDecoderListener = null;
        mContext = context;
        mDataProvider = dmcplayingdataprovider;
        init();
    }

    private Bitmap getBitmap(MediaItem mediaitem, int i, int j)
    {
        if (mediaitem == null)
        {
            return null;
        }
        if (!mediaitem.videoOrImage) goto _L2; else goto _L1
_L1:
        if (mediaitem.strDecodePath == null) goto _L4; else goto _L3
_L3:
        Bitmap bitmap = BitmapFactory.decodeFile(mediaitem.strDecodePath);
_L5:
        return bitmap;
_L4:
        if (mediaitem.uri == null)
        {
            return null;
        }
        boolean flag = mediaitem.uri.toString().startsWith("file://");
        bitmap = null;
        if (flag)
        {
            bitmap = VideoUtils.getVideoFrame2(mediaitem.uri.toString().substring(7), i, j, true);
        }
        if (true) goto _L5; else goto _L2
_L2:
        return LocalFrameDecoder.decodeImage(mediaitem, mContext, i, j, false);
    }

    public void decode()
    {
        if (mDataProvider == null)
        {
            return;
        } else
        {
            mDecodeExit = false;
            mDecodeThread = new Thread("DmcDecoderLocal") {

                final DmcDecoder this$0;

                public void run()
                {
                    if (mDecodeExit) goto _L2; else goto _L1
_L1:
                    int i;
                    int j;
                    Log.d("Dmc", "DmcDecoderLocal begins.");
                    i = mDataProvider.getCount();
                    j = 0;
_L5:
                    if (j < i && !mDecodeExit && mDataProvider != null) goto _L3; else goto _L2
_L2:
                    Log.d("Dmc", "DmcDecoderLocal end.");
                    return;
_L3:
                    MediaItem mediaitem = mDataProvider.getMediaItem(j);
                    if (mediaitem != null)
                    {
                        if (!RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL)
                        {
                            mediaitem.strDecodePath = mDataProvider.getRemoteThumbnaiPath(j);
                        }
                        Bitmap bitmap = getBitmap(mediaitem, mThumbnailWidth, mThumbnailHeight);
                        Log.d("Dmc", (new StringBuilder()).append("DmcDecoderLocal : index = ").append(j).append(" bmp = ").append(bitmap).toString());
                        if (mDecoderListener != null && bitmap != null)
                        {
                            mDecoderListener.decodeFinish(j, bitmap, DmcUtils.COVER_TYPE.TYPE_THUMBNAIL);
                        }
                    }
                    j++;
                    if (true) goto _L5; else goto _L4
_L4:
                }

            
            {
                this$0 = DmcDecoder.this;
                super(s);
            }
            };
            mDecodeThread.start();
            return;
        }
    }

    public void decode(int i, DmcUtils.COVER_TYPE cover_type)
    {
        if (mDataProvider != null)
        {
            MediaItem mediaitem = mDataProvider.getMediaItem(i);
            if (mediaitem != null)
            {
                if (!RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL)
                {
                    mediaitem.strDecodePath = mDataProvider.getRemoteThumbnaiPath(i);
                }
                int j;
                int k;
                Bitmap bitmap;
                if (cover_type == DmcUtils.COVER_TYPE.TYPE_THUMBNAIL)
                {
                    j = mThumbnailWidth;
                } else
                {
                    j = mTvCoverWidth;
                }
                if (cover_type == DmcUtils.COVER_TYPE.TYPE_THUMBNAIL)
                {
                    k = mThumbnailHeight;
                } else
                {
                    k = mTvCoverHeight;
                }
                bitmap = getBitmap(mediaitem, j, k);
                Log.d("Dmc", (new StringBuilder()).append("decodeImage index = ").append(i).append(" type = ").append(cover_type).append("bmp =  ").append(bitmap).toString());
                if (mDecoderListener != null && bitmap != null)
                {
                    mDecoderListener.decodeFinish(i, bitmap, cover_type);
                    return;
                }
            }
        }
    }

    public void decode(ArrayList arraylist)
    {
        if (arraylist == null)
        {
            return;
        } else
        {
            mDecodeExit = false;
            mDecodeThread = new Thread(arraylist) {

                final DmcDecoder this$0;
                final ArrayList val$array;

                public void run()
                {
                    if (mDecodeExit) goto _L2; else goto _L1
_L1:
                    int i;
                    int j;
                    Log.d("Dmc", "DmcDecoderDMS begin.");
                    i = array.size();
                    j = 0;
_L5:
                    if (j < i && !mDecodeExit && mDataProvider != null) goto _L3; else goto _L2
_L2:
                    Log.d("Dmc", "DmcDecoderDMS end.");
                    return;
_L3:
                    int k = ((Integer)array.get(j)).intValue();
                    MediaItem mediaitem = mDataProvider.getMediaItem(k);
                    if (mediaitem != null)
                    {
                        if (!RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL)
                        {
                            mediaitem.strDecodePath = mDataProvider.getRemoteThumbnaiPath(k);
                        }
                        Bitmap bitmap = getBitmap(mediaitem, mThumbnailWidth, mThumbnailHeight);
                        Log.d("Dmc", (new StringBuilder()).append("DmcDecoderDMS : index = ").append(j).append(" bmp = ").append(bitmap).toString());
                        if (mDecoderListener != null && bitmap != null)
                        {
                            mDecoderListener.decodeFinish(k, bitmap, DmcUtils.COVER_TYPE.TYPE_THUMBNAIL);
                        }
                    }
                    j++;
                    if (true) goto _L5; else goto _L4
_L4:
                }

            
            {
                this$0 = DmcDecoder.this;
                array = arraylist;
                super(final_s);
            }
            };
            mDecodeThread.start();
            return;
        }
    }

    public void destroy()
    {
        if (mDecodeThread != null)
        {
            mDecodeExit = true;
            try
            {
                mDecodeThread.join();
            }
            catch (InterruptedException interruptedexception)
            {
                mDecodeThread = null;
                interruptedexception.printStackTrace();
            }
            mDecodeThread = null;
        }
    }

    protected void init()
    {
        if (mContext != null)
        {
            int ai[] = mContext.getResources().getIntArray(0x7f060005);
            if (ai != null)
            {
                mThumbnailWidth = ai[0];
                mThumbnailHeight = ai[1];
            }
            int ai1[] = mContext.getResources().getIntArray(0x7f060006);
            if (ai1 != null)
            {
                mTvCoverWidth = ai1[0];
                mTvCoverHeight = ai1[1];
                return;
            }
        }
    }

    public void setDecoderListener(IDmcDecoder idmcdecoder)
    {
        mDecoderListener = idmcdecoder;
    }

}
