// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.listview.IItemListView;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity, ListViewManager

class this._cls0
    implements com.arcsoft.util.tool.istener
{

    final MediaPlusActivity this$0;

    public void onDeleteStart(int i)
    {
        Log.e("MediaPlusActivity", (new StringBuilder()).append("onDeleteStart fileNum = ").append(i).toString());
        MediaPlusActivity.access$002(MediaPlusActivity.this, false);
        MediaPlusActivity.access$102(MediaPlusActivity.this, i);
        MediaPlusActivity.access$202(MediaPlusActivity.this, 0);
        synchronized (MediaPlusActivity.access$300(MediaPlusActivity.this))
        {
            MediaPlusActivity.access$402(MediaPlusActivity.this, 0);
        }
        synchronized (mHandler)
        {
            mHandler.removeMessages(777);
            Message message = mHandler.obtainMessage(777);
            message.obj = Integer.valueOf(i);
            mHandler.sendMessageDelayed(message, 5L);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        exception1;
        handler;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public void onDeleted(int i, int j)
    {
        Log.e("zdf", (new StringBuilder()).append("isRemote() = ").append(isRemote()).append(", onDeleted sucNum = ").append(i).append(" fileNum = ").append(j).append(", mOnDeleteRecvCount = ").append(MediaPlusActivity.access$200(MediaPlusActivity.this)).append(", mOnDeleteFileNum = ").append(MediaPlusActivity.access$100(MediaPlusActivity.this)).toString());
        if (isRemote())
        {
            MediaPlusActivity.access$700(MediaPlusActivity.this, MediaPlusActivity.access$200(MediaPlusActivity.this), MediaPlusActivity.access$100(MediaPlusActivity.this));
            return;
        } else
        {
            MediaPlusActivity.access$700(MediaPlusActivity.this, i, j);
            return;
        }
    }

    public void onDeleting(MediaItem mediaitem, int i, int j, boolean flag)
    {
        Log.e("MediaPlusActivity", (new StringBuilder()).append(" onDeleting  filePros = ").append(i).toString());
        int _tmp = MediaPlusActivity.access$208(MediaPlusActivity.this);
        if (!isRemote() && flag)
        {
            IDataSource idatasource = MediaPlusActivity.access$500(MediaPlusActivity.this).getCurrentListView().getDataSource();
            if (idatasource != null)
            {
                boolean flag1 = idatasource.delete(mediaitem);
                Log.e("MediaPlusActivity", (new StringBuilder()).append("cxj onDeleting delete = ").append(flag1).append(" fileItem.id = ").append(mediaitem._id).toString());
            }
            if (MediaPlusActivity.access$600(MediaPlusActivity.this) != null && MediaPlusActivity.access$100(MediaPlusActivity.this) > 1)
            {
                MediaPlusActivity.access$600(MediaPlusActivity.this).sendEmptyMessage(4098);
            }
        }
    }

    public void onDeletingRemote(int i)
    {
        synchronized (MediaPlusActivity.access$300(MediaPlusActivity.this))
        {
            MediaPlusActivity.access$402(MediaPlusActivity.this, i);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
