// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcDecoder, DmcPlayingDataProvider, RenderDevSelector, IDmcDecoder

class this._cls0 extends Thread
{

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
            android.graphics.Bitmap bitmap = DmcDecoder.access$000(DmcDecoder.this, mediaitem, mThumbnailWidth, mThumbnailHeight);
            Log.d("Dmc", (new StringBuilder()).append("DmcDecoderLocal : index = ").append(j).append(" bmp = ").append(bitmap).toString());
            if (mDecoderListener != null && bitmap != null)
            {
                mDecoderListener.decodeFinish(j, bitmap, R_TYPE.TYPE_THUMBNAIL);
            }
        }
        j++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    R_TYPE(String s)
    {
        this$0 = DmcDecoder.this;
        super(s);
    }
}
