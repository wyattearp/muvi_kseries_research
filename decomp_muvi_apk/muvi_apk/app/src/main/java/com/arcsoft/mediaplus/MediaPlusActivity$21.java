// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.os.Handler;
import android.os.RemoteException;
import android.widget.Toast;
import com.arcsoft.mediaplus.service.util.IDlnaSettingBinder;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.FileDelete;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity, ViewManager, ListViewManager

class this._cls0
    implements com.arcsoft.adk.atv.StatusListener
{

    final MediaPlusActivity this$0;

    public void OnDestroyObject(String s, int i)
    {
        if (ViewManager.getViewStatus() == 2)
        {
            int _tmp = MediaPlusActivity.access$208(MediaPlusActivity.this);
            Log.e("tht", (new StringBuilder()).append("OnDestroyObeject updateid = ").append(s).append(" errorcode = ").append(i).append(", selectorNum: ").append(ListViewManager.getSelectedItemsNum()).toString());
            Log.e("zdf", (new StringBuilder()).append("OnDestroyObeject, mOnDeleteRecvCount = ").append(MediaPlusActivity.access$200(MediaPlusActivity.this)).append(", mOnDeleteSendCount = ").append(MediaPlusActivity.access$400(MediaPlusActivity.this)).append(", mOnDeleteFileNum = ").append(MediaPlusActivity.access$100(MediaPlusActivity.this)).toString());
            if (i != 0)
            {
                MediaPlusActivity.access$002(MediaPlusActivity.this, true);
            }
            if (ListViewManager.getSelectedItemsNum() > 0)
            {
                MediaPlusActivity.access$2500(MediaPlusActivity.this, s, i);
            }
            if (MediaPlusActivity.access$100(MediaPlusActivity.this) > 1 && MediaPlusActivity.access$600(MediaPlusActivity.this) != null)
            {
                MediaPlusActivity.access$600(MediaPlusActivity.this).sendEmptyMessage(4096);
            }
            if (MediaPlusActivity.access$2600(MediaPlusActivity.this) != null)
            {
                if (MediaPlusActivity.access$2700(MediaPlusActivity.this))
                {
                    MediaPlusActivity.access$2600(MediaPlusActivity.this).cancel();
                    return;
                }
                if (MediaPlusActivity.access$200(MediaPlusActivity.this) < MediaPlusActivity.access$100(MediaPlusActivity.this))
                {
                    MediaPlusActivity.access$2600(MediaPlusActivity.this).deleteNext();
                    return;
                }
                if (MediaPlusActivity.access$200(MediaPlusActivity.this) == MediaPlusActivity.access$100(MediaPlusActivity.this))
                {
                    MediaPlusActivity.access$2600(MediaPlusActivity.this).stopDelete();
                    return;
                } else
                {
                    MediaPlusActivity.access$2600(MediaPlusActivity.this).stopDelete();
                    return;
                }
            }
        }
    }

    public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.cordTasks cordtasks, int i)
    {
    }

    public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.cordSchedule cordschedule, int i)
    {
    }

    public void OnDigaDeleteRecordSchedule(String s, int i)
    {
    }

    public void OnDigaDisableRecordSchedule(String s, int i)
    {
    }

    public void OnDigaEnableRecordSchedule(String s, int i)
    {
    }

    public void OnDigaXP9eGetContainerIds(String s, String s1, int i)
    {
    }

    public void onGetSearchCapabilities(String s, String s1, int i)
    {
    }

    public void onGetSortCapabilities(String s, String s1, int i)
    {
    }

    public void onServerAdded(com.arcsoft.adk.atv._cls1 _pcls1)
    {
        Log.v("zdf", (new StringBuilder()).append("[MediaPlusActivity] onServerAdded, desc.m_strUuid = ").append(_pcls1.m_strUuid).append(", desc.m_strFriendlyName = ").append(_pcls1.m_strFriendlyName).append(", mStrAutoUDN = ").append(MediaPlusActivity.access$2100(MediaPlusActivity.this)).toString());
        String s = Settings.instance().getDefaultDMSUDN();
        MediaPlusActivity.access$2000(MediaPlusActivity.this, _pcls1.m_strUuid);
        if (_pcls1.m_strUuid != null)
        {
            if (_pcls1.m_strUuid.equals(s));
        }
    }

    public void onServerMetaChanged(com.arcsoft.adk.atv._cls1 _pcls1)
    {
    }

    public void onServerRemoved(com.arcsoft.adk.atv._cls1 _pcls1)
    {
        String s;
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.cancelAllTask();
        }
        s = Settings.instance().getDefaultDMSUDN();
        Log.v("zdf", (new StringBuilder()).append("[MediaPlusActivity] onServerRemoved, desc.m_strUuid = ").append(_pcls1.m_strUuid).append(", serverudn = ").append(s).toString());
        if (MediaPlusActivity.access$1900(MediaPlusActivity.this) != null && MediaPlusActivity.access$1900(MediaPlusActivity.this).equals(_pcls1.m_strUuid))
        {
            if (MediaPlusActivity.access$2200(MediaPlusActivity.this) != null && MediaPlusActivity.access$2200(MediaPlusActivity.this).isShowing())
            {
                MediaPlusActivity.access$700(MediaPlusActivity.this, 0, ListViewManager.getSelectedItemsNum());
            }
            if (isRemote() && ViewManager.getViewStatus() == 2)
            {
                clearMarkView();
            }
        }
        if (s == null) goto _L2; else goto _L1
_L1:
        if (MediaPlusActivity.access$2300(MediaPlusActivity.this) == null) goto _L2; else goto _L3
_L3:
        String as[] = MediaPlusActivity.access$2300(MediaPlusActivity.this).getOnlineDmsUDN();
        if (as == null)
        {
            break MISSING_BLOCK_LABEL_291;
        }
        int i;
        if (as.length <= 0)
        {
            break MISSING_BLOCK_LABEL_291;
        }
        i = as.length;
        int j = 0;
_L8:
        if (j >= i) goto _L2; else goto _L4
_L4:
        String s3;
        String s4;
        s3 = as[j];
        s4 = MediaPlusActivity.access$2300(MediaPlusActivity.this).getDmsFriendlyName(s3);
        if (s4 == null) goto _L6; else goto _L5
_L5:
        if (!s4.equalsIgnoreCase("DMS for DV")) goto _L6; else goto _L7
_L7:
        MediaPlusActivity.access$2102(MediaPlusActivity.this, s3);
        MediaPlusActivity.access$2400(MediaPlusActivity.this, MediaPlusActivity.access$2100(MediaPlusActivity.this));
_L2:
        if (_pcls1.m_strUuid != null && !_pcls1.m_strUuid.equals(s))
        {
            return;
        } else
        {
            dismissLoadingDialog();
            String s1 = getResources().getString(0x7f0b0018);
            Object aobj[] = new Object[1];
            aobj[0] = _pcls1.m_strFriendlyName;
            String s2 = String.format(s1, aobj);
            Toast.makeText(mContext, s2, 0).show();
            return;
        }
_L6:
        j++;
          goto _L8
        try
        {
            MediaPlusActivity.access$2400(MediaPlusActivity.this, null);
        }
        catch (RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
        }
          goto _L2
    }

    rdSchedule()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
