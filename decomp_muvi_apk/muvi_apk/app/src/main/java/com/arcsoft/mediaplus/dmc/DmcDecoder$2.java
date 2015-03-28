// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcDecoder, DmcPlayingDataProvider, RenderDevSelector, IDmcDecoder

class val.array extends Thread
{

    final DmcDecoder this$0;
    final ArrayList val$array;

    public void run()
    {
        if (mDecodeExit) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        Log.d("Dmc", "DmcDecoderDMS begin.");
        i = val$array.size();
        j = 0;
_L5:
        if (j < i && !mDecodeExit && mDataProvider != null) goto _L3; else goto _L2
_L2:
        Log.d("Dmc", "DmcDecoderDMS end.");
        return;
_L3:
        int k = ((Integer)val$array.get(j)).intValue();
        MediaItem mediaitem = mDataProvider.getMediaItem(k);
        if (mediaitem != null)
        {
            if (!RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL)
            {
                mediaitem.strDecodePath = mDataProvider.getRemoteThumbnaiPath(k);
            }
            android.graphics.Bitmap bitmap = DmcDecoder.access$000(DmcDecoder.this, mediaitem, mThumbnailWidth, mThumbnailHeight);
            Log.d("Dmc", (new StringBuilder()).append("DmcDecoderDMS : index = ").append(j).append(" bmp = ").append(bitmap).toString());
            if (mDecoderListener != null && bitmap != null)
            {
                mDecoderListener.decodeFinish(k, bitmap, R_TYPE.TYPE_THUMBNAIL);
            }
        }
        j++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    R_TYPE(ArrayList arraylist)
    {
        this$0 = final_dmcdecoder;
        val$array = arraylist;
        super(String.this);
    }
}
