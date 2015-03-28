// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.res.Resources;
import android.net.NetworkInfo;
import com.arcsoft.mediaplus.picture.ui.ListViewGL;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.aee.AEESocketClient;
import com.arcsoft.videostream.rtsp.RtspUtils;
import java.io.IOException;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity, ListViewManager

class this._cls0
    implements com.arcsoft.util.os.ctivityChangeListener
{

    final MediaPlusActivity this$0;

    public void OnConnectivityChanged(com.arcsoft.util.os.tateInfo tateinfo)
    {
        boolean flag;
        flag = true;
        Log.e("MediaPlusActivity", (new StringBuilder()).append("MediaPlusActivity OnConnectivityChanged info.networkInfo.isConnected() = ").append(tateinfo.networkInfo.isConnected()).toString());
        if (tateinfo.networkInfo != null && tateinfo.networkInfo.getType() == flag) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (!tateinfo.networkInfo.isConnected())
        {
            break; /* Loop/switch isn't completed */
        }
        MediaPlusActivity.access$802(MediaPlusActivity.this, flag);
        AEESocketClient aeesocketclient1 = AEESocketClient.getInstanceClient();
        AEESocketClient aeesocketclient = aeesocketclient1;
_L4:
        if (RtspUtils.isAmbar() && aeesocketclient != null && aeesocketclient.isConnected() && aeesocketclient.getCurTokenId() > 0 && !aeesocketclient.getIsNeedEncoding() && !MediaPlusActivity.access$900(MediaPlusActivity.this))
        {
            MediaPlusActivity.access$1000(MediaPlusActivity.this);
            MediaPlusActivity.access$1102(MediaPlusActivity.this, flag);
            MediaPlusActivity mediaplusactivity1 = MediaPlusActivity.this;
            IOException ioexception;
            if (!MediaPlusActivity.access$1100(MediaPlusActivity.this))
            {
                flag = false;
            }
            MediaPlusActivity.access$1200(mediaplusactivity1, 551, flag, -1, null, 0);
            MediaPlusActivity.access$1300(MediaPlusActivity.this, 0x10000013, -1, 0L);
            return;
        }
        if (true) goto _L1; else goto _L3
        ioexception;
        ioexception.printStackTrace();
        aeesocketclient = null;
          goto _L4
_L3:
        MediaPlusActivity.access$802(MediaPlusActivity.this, false);
        if (isRemote() && MediaPlusActivity.access$500(MediaPlusActivity.this) != null)
        {
            String s = getResources().getString(0x7f0b005c);
            Log.i("zdf", (new StringBuilder()).append("######## [MediaPlusActivity] OnConnectivityChanged(false), before sendMsgToUpdateTextStatus, strText = ").append(s).toString());
            ((ListViewGL)MediaPlusActivity.access$500(MediaPlusActivity.this).getCurrentListView()).sendMsgToUpdateTextStatus(s, flag, 0);
        }
        if (MediaPlusActivity.access$500(MediaPlusActivity.this) != null && MediaPlusActivity.access$500(MediaPlusActivity.this).getDownloadCount() > 0 && mUpDownloadEngine != null)
        {
            mUpDownloadEngine.cancelAllTask();
        }
        if (isRemote())
        {
            dismissLoadingDialog();
        }
        MediaPlusActivity.access$1102(MediaPlusActivity.this, false);
        MediaPlusActivity mediaplusactivity = MediaPlusActivity.this;
        if (!MediaPlusActivity.access$1100(MediaPlusActivity.this))
        {
            flag = false;
        }
        MediaPlusActivity.access$1200(mediaplusactivity, 551, flag, -1, null, 0);
        return;
    }

    teInfo()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
