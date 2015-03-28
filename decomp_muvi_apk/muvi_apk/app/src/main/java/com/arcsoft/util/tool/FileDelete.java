// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.DLNAException;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.ListViewManager;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.util.debug.Log;
import java.io.File;
import java.util.ArrayList;

public class FileDelete
{
    private class DeleteAsyncTask extends AsyncTask
    {

        final FileDelete this$0;

        protected transient Integer doInBackground(Integer ainteger[])
        {
            Log.e("FileDelete", "doDeleteRemote doInBackground");
            if (isLocalDel)
            {
                return Integer.valueOf(doDeleteLocal(ainteger[0].intValue()));
            } else
            {
                return Integer.valueOf(doDeleteRemote(ainteger[0].intValue()));
            }
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Integer[])aobj);
        }

        protected void onPostExecute(Integer integer)
        {
            Log.e("FileDelete", (new StringBuilder()).append("DeleteAsyncTask onPostExecute sucNum = ").append(integer).append(" deleteNum = ").append(deleteNum).toString());
            mDeleteListener.onDeleted(integer.intValue(), deleteNum);
            recycleData();
            super.onPostExecute(integer);
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Integer)obj);
        }

        private DeleteAsyncTask()
        {
            this$0 = FileDelete.this;
            super();
        }

    }

    public class DeleteData
    {

        public static final int DATA_TYPE_IMAGE = 1;
        public static final int DATA_TYPE_UNDEFINE = 0;
        public static final int DATA_TYPE_VIDEO = 2;
        public int dataType;
        public MediaItem fileItem;
        public String filePath;
        public long mId;
        public String mstr_objId;
        final FileDelete this$0;
        public String title;

        public DeleteData()
        {
            this$0 = FileDelete.this;
            super();
            dataType = 0;
            mId = 0L;
            filePath = null;
            title = null;
            mstr_objId = null;
            fileItem = null;
        }
    }

    public static interface IRelatedDeleteList
    {

        public abstract void delete(DeleteData deletedata);
    }

    public static interface onDeleteListener
    {

        public abstract void onDeleteStart(int i);

        public abstract void onDeleted(int i, int j);

        public abstract void onDeleting(MediaItem mediaitem, int i, int j, boolean flag);

        public abstract void onDeletingRemote(int i);
    }


    private static final String TAG = "FileDelete";
    private static final Uri imageURI;
    private static final Uri videoURI;
    private DeleteAsyncTask deletTask;
    private int deleteNum;
    private boolean isLocalDel;
    private ContentResolver mContentResolver;
    private ArrayList mDeleteData;
    private onDeleteListener mDeleteListener;
    private IRelatedDeleteList mRelatedDeleteList;
    private boolean m_bOnRemoteFileDeleteWaitingNext;
    private boolean mbCancelDelete;
    private boolean mbStopDelete;

    public FileDelete(Context context, onDeleteListener ondeletelistener)
    {
        mDeleteListener = null;
        mDeleteData = null;
        deletTask = null;
        deleteNum = 0;
        mRelatedDeleteList = null;
        isLocalDel = true;
        mbCancelDelete = false;
        mbStopDelete = false;
        m_bOnRemoteFileDeleteWaitingNext = false;
        init(context);
        setDeleteListener(ondeletelistener);
    }

    private void deleteWaiting(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        m_bOnRemoteFileDeleteWaitingNext = flag;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private int doDeleteLocal(int i)
    {
        int j;
        int k;
        DeleteData deletedata;
        boolean flag;
        j = 0;
        k = 0;
        do
        {
label0:
            {
                if (k >= i)
                {
                    break MISSING_BLOCK_LABEL_140;
                }
                deletedata = (DeleteData)mDeleteData.get(k);
                if (deletedata.dataType == 1)
                {
                    flag = onImageDelete(String.valueOf(deletedata.mId), deletedata.filePath);
                } else
                {
                    flag = onVideoDelete(String.valueOf(deletedata.mId), deletedata.filePath);
                }
                if (!flag)
                {
                    break label0;
                }
                j++;
                if (mRelatedDeleteList != null)
                {
                    mRelatedDeleteList.delete(deletedata);
                }
                mDeleteListener.onDeleting(deletedata.fileItem, k, j, flag);
                k++;
            }
        } while (true);
        mDeleteListener.onDeleting(deletedata.fileItem, k, j, flag);
        return j;
    }

    private int doDeleteRemote(int i)
    {
        int j;
        int k;
        int l;
        Log.e("FileDelete", (new StringBuilder()).append("doDeleteRemote deleteNum = ").append(i).toString());
        Log.e("tht", (new StringBuilder()).append("doDeleteRemote --- in, deleteNum = ").append(i).toString());
        j = 0;
        k = 0;
        mbStopDelete = false;
        ListViewManager.clearUpdateItems();
        l = 0;
_L4:
        DeleteData deletedata;
        if (l >= i)
        {
            break MISSING_BLOCK_LABEL_365;
        }
        deletedata = (DeleteData)mDeleteData.get(l);
        if (deletedata == null)
        {
            break MISSING_BLOCK_LABEL_100;
        }
        if (deletedata.mstr_objId != null)
        {
            break MISSING_BLOCK_LABEL_224;
        }
        int i1;
        i1 = 1;
        Exception exception;
        try
        {
            Log.v("tht", "doDeleteRemote pDeleteData == null || pDeleteData.mstr_objId == null");
        }
        catch (DLNAException dlnaexception)
        {
            dlnaexception.printStackTrace();
            i1 = 1;
        }
        Log.v("zdf", (new StringBuilder()).append("111111111111, deleteIndex = ").append(l).append(", m_bOnRemoteFileDeleteWaitingNext = ").append(m_bOnRemoteFileDeleteWaitingNext).append(", mbCancelDelete = ").append(mbCancelDelete).append(", mbStopDelete = ").append(mbStopDelete).toString());
        while (m_bOnRemoteFileDeleteWaitingNext && !mbCancelDelete && !mbStopDelete && l < i && i1 == 0) 
        {
            try
            {
                Thread.sleep(100L);
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        break; /* Loop/switch isn't completed */
        i1 = DLNA.instance().getServerManager().destroyObeject(Settings.instance().getDefaultDMSUDN(), deletedata.mstr_objId);
        k++;
        Log.v("tht", (new StringBuilder()).append("doDeleteRemote --- index: ").append(l).append(", ret: ").append(i1).toString());
        deleteWaiting(true);
        if (mDeleteListener != null)
        {
            mDeleteListener.onDeletingRemote(k);
        }
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_111;
_L1:
        Log.v("zdf", (new StringBuilder()).append("2222222222, ret = ").append(i1).toString());
        if (i1 == 0)
        {
            j++;
        }
        if (!mbCancelDelete)
        {
            break MISSING_BLOCK_LABEL_394;
        }
        Log.e("tht", (new StringBuilder()).append("doDeleteRemote --- out, sucNum = ").append(j).toString());
        return j;
        l++;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private int getDataType(String s)
    {
        if (s != null)
        {
            if (s.matches("image/.*"))
            {
                return 1;
            }
            if (s.matches("video/.*"))
            {
                return 2;
            }
        }
        return 0;
    }

    private int getDeleteData(MediaItem amediaitem[])
    {
        mDeleteData.clear();
        isLocalDel = true;
        int i = amediaitem.length;
        int j = 0;
        while (j < i) 
        {
            MediaItem mediaitem = amediaitem[j];
            DeleteData deletedata = new DeleteData();
            deletedata.dataType = getDataType(mediaitem.mime_type);
            deletedata.mId = mediaitem._id;
            if (mediaitem.uri != null)
            {
                deletedata.filePath = mediaitem.uri.getEncodedPath();
            } else
            {
                deletedata.filePath = "";
            }
            deletedata.title = mediaitem.title;
            deletedata.fileItem = mediaitem;
            mDeleteData.add(deletedata);
            j++;
        }
        return amediaitem.length;
    }

    private int getDeleteData(String as[])
    {
        mDeleteData.clear();
        isLocalDel = false;
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String s = as[j];
            DeleteData deletedata = new DeleteData();
            deletedata.mstr_objId = s;
            mDeleteData.add(deletedata);
        }

        return as.length;
    }

    private void init(Context context)
    {
        mContentResolver = context.getContentResolver();
        mDeleteData = new ArrayList();
    }

    private boolean onImageDelete(String s, String s1)
    {
        boolean flag = (new File(s1)).delete();
        if (flag)
        {
            String s2 = (new StringBuilder()).append("_id = ").append(s).toString();
            mContentResolver.delete(imageURI, s2, null);
        }
        return flag;
    }

    private boolean onVideoDelete(String s, String s1)
    {
        boolean flag = (new File(s1)).delete();
        if (flag)
        {
            String s2 = (new StringBuilder()).append("_id = ").append(s).toString();
            mContentResolver.delete(videoURI, s2, null);
        }
        return flag;
    }

    private void recycleData()
    {
        if (mDeleteData != null)
        {
            mDeleteData.clear();
        }
        deleteNum = 0;
        if (deletTask != null)
        {
            deletTask.cancel(true);
            deletTask = null;
        }
    }

    public void cancel()
    {
        this;
        JVM INSTR monitorenter ;
        mbCancelDelete = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void deleteNext()
    {
        deleteWaiting(false);
    }

    public void onDelete(String s)
    {
        if (s == null || mDeleteData == null)
        {
            return;
        }
        mbCancelDelete = false;
        deleteNum = 1;
        isLocalDel = true;
        mDeleteData.clear();
        DeleteData deletedata = new DeleteData();
        deletedata.mstr_objId = s;
        isLocalDel = false;
        mDeleteData.add(deletedata);
        mDeleteListener.onDeleteStart(deleteNum);
        DeleteAsyncTask deleteasynctask;
        Integer ainteger[];
        if (deletTask == null)
        {
            deletTask = new DeleteAsyncTask();
        } else
        {
            deletTask.cancel(true);
            deletTask = null;
            deletTask = new DeleteAsyncTask();
        }
        deleteasynctask = deletTask;
        ainteger = new Integer[1];
        ainteger[0] = Integer.valueOf(deleteNum);
        deleteasynctask.execute(ainteger);
    }

    public void onDelete(MediaItem amediaitem[])
    {
        if (mContentResolver == null || amediaitem == null || mDeleteData == null)
        {
            return;
        }
        mbCancelDelete = false;
        isLocalDel = true;
        deleteNum = getDeleteData(amediaitem);
        mDeleteListener.onDeleteStart(deleteNum);
        DeleteAsyncTask deleteasynctask;
        Integer ainteger[];
        if (deletTask == null)
        {
            deletTask = new DeleteAsyncTask();
        } else
        {
            deletTask.cancel(true);
            deletTask = null;
            deletTask = new DeleteAsyncTask();
        }
        deleteasynctask = deletTask;
        ainteger = new Integer[1];
        ainteger[0] = Integer.valueOf(deleteNum);
        deleteasynctask.execute(ainteger);
    }

    public void onDelete(String as[])
    {
        if (as == null)
        {
            return;
        }
        mbCancelDelete = false;
        isLocalDel = false;
        deleteNum = getDeleteData(as);
        mDeleteListener.onDeleteStart(deleteNum);
        DeleteAsyncTask deleteasynctask;
        Integer ainteger[];
        if (deletTask == null)
        {
            deletTask = new DeleteAsyncTask();
        } else
        {
            deletTask.cancel(true);
            deletTask = null;
            deletTask = new DeleteAsyncTask();
        }
        deleteasynctask = deletTask;
        ainteger = new Integer[1];
        ainteger[0] = Integer.valueOf(deleteNum);
        deleteasynctask.execute(ainteger);
    }

    public void setDeleteListener(onDeleteListener ondeletelistener)
    {
        mDeleteListener = ondeletelistener;
    }

    public void setRelatedDeleteLis(IRelatedDeleteList irelateddeletelist)
    {
        mRelatedDeleteList = irelateddeletelist;
    }

    public void stopDelete()
    {
        mbStopDelete = true;
    }

    static 
    {
        imageURI = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        videoURI = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    }






}
