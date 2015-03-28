// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.os.Handler;
import android.os.Message;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.arcsoft.mediaplus.datasource.db.SalixRemoteDBMgr;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.FileDelete;
import com.arcsoft.util.tool.ToastMgr;
import com.arcsoft.videostream.aee.AEESocketClient;
import java.io.IOException;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity, ViewManager, ListViewManager

class this._cls0 extends Handler
{

    final MediaPlusActivity this$0;

    public void handleMessage(Message message)
    {
        super.handleMessage(message);
        message.what;
        JVM INSTR lookupswitch 20: default 180
    //                   523: 957
    //                   524: 1033
    //                   525: 1041
    //                   526: 215
    //                   545: 1057
    //                   546: 1088
    //                   549: 1100
    //                   550: 1341
    //                   551: 1466
    //                   552: 393
    //                   769: 181
    //                   770: 285
    //                   771: 180
    //                   772: 313
    //                   773: 236
    //                   774: 424
    //                   775: 562
    //                   776: 570
    //                   777: 595
    //                   778: 711;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L1 _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L1:
        return;
_L12:
        if (MediaPlusActivity.access$2800(MediaPlusActivity.this) != null)
        {
            MediaPlusActivity.access$2800(MediaPlusActivity.this).switchToView(ViewManager.getViewStatus(), ((Integer)message.obj).intValue());
            return;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (MediaPlusActivity.access$500(MediaPlusActivity.this) != null)
        {
            MediaPlusActivity.access$500(MediaPlusActivity.this).showHideViews();
            return;
        }
        if (true) goto _L1; else goto _L15
_L15:
        Integer integer = (Integer)message.obj;
        Log.e("FENG", (new StringBuilder()).append("FENG MSG_SWITCH_CONTENT: contentType ").append(integer).toString());
        switchDevicesInit(integer.intValue(), 2);
        return;
_L13:
        Toast.makeText(mContext, 0x7f0b0055, 1).show();
        sendEmptyMessageDelayed(770, 3000L);
        return;
_L14:
        if (MediaPlusActivity.access$2800(MediaPlusActivity.this) != null)
        {
            MediaPlusActivity.access$2800(MediaPlusActivity.this).refreshSelectorTitle(((Integer)message.obj).intValue());
            if (((Integer)message.obj).intValue() > 0)
            {
                MediaPlusActivity.access$2800(MediaPlusActivity.this).setControlBarEnable(true);
                return;
            }
            if (((Integer)message.obj).intValue() == 0)
            {
                MediaPlusActivity.access$2800(MediaPlusActivity.this).setControlBarEnable(false);
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L11:
        if (MediaPlusActivity.access$2800(MediaPlusActivity.this) != null)
        {
            MediaPlusActivity.access$2800(MediaPlusActivity.this).setCancelAllBtnEnabled(((Boolean)message.obj).booleanValue());
            return;
        }
        if (true) goto _L1; else goto _L16
_L16:
        com.arcsoft.mediaplus.datasource.MediaItem amediaitem[] = MediaPlusActivity.access$500(MediaPlusActivity.this).getSelectedItemList();
        if (MediaPlusActivity.access$2600(MediaPlusActivity.this) != null && amediaitem != null)
        {
            String as[] = MediaPlusActivity.access$2900(MediaPlusActivity.this, MediaPlusActivity.access$500(MediaPlusActivity.this).getSelectedKeyList());
            if (isRemote())
            {
                for (int i2 = 0; i2 < amediaitem.length; i2++)
                {
                    if (mUpDownloadEngine != null && mUpDownloadEngine.isFileDownloadingOrWaiting(as[i2]))
                    {
                        ToastMgr.showToast(MediaPlusActivity.this, 0x7f0b0181, 0);
                        return;
                    }
                }

                MediaPlusActivity.access$2600(MediaPlusActivity.this).onDelete(as);
                return;
            } else
            {
                MediaPlusActivity.access$2600(MediaPlusActivity.this).onDelete(amediaitem);
                return;
            }
        }
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
_L17:
        SalixRemoteDBMgr.instance().requestServerData(true);
        return;
_L18:
        if (MediaPlusActivity.access$2800(MediaPlusActivity.this) != null)
        {
            MediaPlusActivity.access$2800(MediaPlusActivity.this).refreshControlBar(message.arg1);
            return;
        }
        if (true) goto _L1; else goto _L19
_L19:
        char c;
        if (isRemote())
        {
            MediaPlusActivity.access$3000(MediaPlusActivity.this);
            c = '\u1000';
        } else
        {
            MediaPlusActivity.access$3100(MediaPlusActivity.this);
            c = '\u1002';
        }
        if (MediaPlusActivity.access$600(MediaPlusActivity.this) != null && MediaPlusActivity.access$100(MediaPlusActivity.this) >= 1)
        {
            MediaPlusActivity.access$600(MediaPlusActivity.this).sendEmptyMessageDelayed(c, 50L);
        }
        if (ViewManager.getViewStatus() == 2)
        {
            if (!OEMConfig.OPENGL_OPTIMIZE)
            {
                ((GridView)MediaPlusActivity.access$500(MediaPlusActivity.this).getCurrentListView()).setClickable(false);
            }
            MediaPlusActivity.access$2800(MediaPlusActivity.this).setAllBtnEnable(false);
            return;
        }
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
_L20:
        Log.v("zdf", (new StringBuilder()).append("MSG_FILE_DELETED, msg.arg1 = ").append(message.arg1).append(", msg.arg2 = ").append(message.arg2).append(", mIsDMSDeleteFailed = ").append(MediaPlusActivity.access$000(MediaPlusActivity.this)).toString());
        if (ViewManager.getViewStatus() == 2)
        {
            if (!OEMConfig.OPENGL_OPTIMIZE)
            {
                GridView gridview = (GridView)MediaPlusActivity.access$500(MediaPlusActivity.this).getCurrentListView();
                if (!gridview.isClickable())
                {
                    gridview.setClickable(true);
                }
            }
            if (MediaPlusActivity.access$2800(MediaPlusActivity.this) != null)
            {
                MediaPlusActivity.access$2800(MediaPlusActivity.this).setAllBtnEnable(true);
            }
        }
        if (isRemote())
        {
            MediaPlusActivity.access$3200(MediaPlusActivity.this);
        } else
        {
            MediaPlusActivity.access$3300(MediaPlusActivity.this);
        }
        if (MediaPlusActivity.access$500(MediaPlusActivity.this) != null)
        {
            MediaPlusActivity.access$500(MediaPlusActivity.this).onFileDeleted();
        }
        if (MediaPlusActivity.access$000(MediaPlusActivity.this) || message.arg1 < message.arg2 && !MediaPlusActivity.access$2700(MediaPlusActivity.this))
        {
            ToastMgr.showToast(mContext, 0x7f0b016d, 1);
        }
        MediaPlusActivity.access$202(MediaPlusActivity.this, 0);
        MediaPlusActivity.access$402(MediaPlusActivity.this, 0);
        MediaPlusActivity.access$102(MediaPlusActivity.this, 0);
        MediaPlusActivity.access$002(MediaPlusActivity.this, false);
        return;
_L2:
        if (MediaPlusActivity.access$800(MediaPlusActivity.this))
        {
            if (MediaPlusActivity.access$3400(MediaPlusActivity.this) != null)
            {
                MediaPlusActivity.access$3400(MediaPlusActivity.this).cancel(true);
                MediaPlusActivity.access$3402(MediaPlusActivity.this, null);
            }
            MediaPlusActivity.access$3402(MediaPlusActivity.this, new nectSocketTask(MediaPlusActivity.this));
            MediaPlusActivity.access$3400(MediaPlusActivity.this).execute(new String[0]);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        requestSalixFile();
        return;
_L4:
        int l1 = message.arg1;
        switchTo(l1);
        return;
_L6:
        MediaPlusActivity mediaplusactivity3 = MediaPlusActivity.this;
        boolean flag1;
        if (message.arg1 == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        mediaplusactivity3.showNoSdcard(flag1);
        return;
_L7:
        MediaPlusActivity.access$1400(MediaPlusActivity.this).setVisibility(0);
        return;
_L8:
        int k;
        int l;
        k = message.arg1;
        l = message.arg2;
        if (k == -1 && l == -1) goto _L1; else goto _L21
_L21:
        AEESocketClient aeesocketclient = AEESocketClient.getInstanceClient();
        if (aeesocketclient == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!aeesocketclient.isConnected())
        {
            continue; /* Loop/switch isn't completed */
        }
        if (k == -1)
        {
            k = l;
            l = -1;
        }
        int i1;
        aeesocketclient.startRespondParams(k);
        i1 = aeesocketclient.sendCommand(k, null);
        aeesocketclient.setNextCMD(l);
        i1;
        JVM INSTR tableswitch 2 4: default 1492
    //                   2 1208
    //                   3 1231
    //                   4 1231;
           goto _L22 _L23 _L24 _L24
_L23:
        try
        {
            MediaPlusActivity.access$1300(MediaPlusActivity.this, k, l, 200L);
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        return;
_L24:
        if (!MediaPlusActivity.access$1100(MediaPlusActivity.this)) goto _L1; else goto _L25
_L25:
        MediaPlusActivity mediaplusactivity1;
        MediaPlusActivity.access$1102(MediaPlusActivity.this, false);
        mediaplusactivity1 = MediaPlusActivity.this;
        String s;
        int i;
        int j;
        int j1;
        MediaPlusActivity mediaplusactivity2;
        int k1;
        if (MediaPlusActivity.access$1100(MediaPlusActivity.this))
        {
            j1 = 1;
        } else
        {
            j1 = 0;
        }
        MediaPlusActivity.access$1200(mediaplusactivity1, 551, j1, -1, null, 0);
        return;
        if (!MediaPlusActivity.access$1100(MediaPlusActivity.this)) goto _L1; else goto _L26
_L26:
        MediaPlusActivity.access$1102(MediaPlusActivity.this, false);
        mediaplusactivity2 = MediaPlusActivity.this;
        if (MediaPlusActivity.access$1100(MediaPlusActivity.this))
        {
            k1 = 1;
        } else
        {
            k1 = 0;
        }
        MediaPlusActivity.access$1200(mediaplusactivity2, 551, k1, -1, null, 0);
        return;
_L9:
        message.arg2;
        s = (String)message.obj;
        i = message.arg1;
        j = 0;
        i;
        JVM INSTR tableswitch 1 1: default 1384
    //                   1 1403;
           goto _L27 _L28
_L27:
        if (j != 0)
        {
            Toast.makeText(MediaPlusActivity.this, j, 0).show();
            return;
        }
          goto _L29
_L28:
        j = 0x7f0b018e;
        if (s != null && s.contains("16777218"))
        {
            j = 0x7f0b018d;
        } else
        if (s != null && s.contains("16777220"))
        {
            j = 0x7f0b0198;
        }
        MediaPlusActivity.access$3502(MediaPlusActivity.this, j);
        if (true) goto _L27; else goto _L29
_L29:
        if (true) goto _L1; else goto _L30
_L30:
_L10:
        MediaPlusActivity mediaplusactivity = MediaPlusActivity.this;
        boolean flag;
        if (message.arg1 == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        MediaPlusActivity.access$3600(mediaplusactivity, flag);
        return;
_L22:
    }

    ()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
