// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.listview.LoadingDialog;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.updownload.AbsPoolDriver;
import com.arcsoft.mediaplus.updownload.MyUPnPUtils;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.ToastMgr;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

// Referenced classes of package com.arcsoft.mediaplus:
//            DownloadWaitDialog

public class DownloadFacade
    implements com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.IOnUpDownloadListener, DownloadWaitDialog.IBackPressListener
{
    class CancelDownloadingTask extends AsyncTask
    {

        final DownloadFacade this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected transient Void doInBackground(Void avoid[])
        {
            if (mNeedToDownloadTasks != null)
            {
                mHandler.sendEmptyMessage(5);
                if (mUpDownloadEngine != null)
                {
                    mUpDownloadEngine.cancelDownloadTasks(mNeedToDownloadTasks);
                    return null;
                }
            }
            return null;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Void)obj);
        }

        protected void onPostExecute(Void void1)
        {
            super.onPostExecute(void1);
            mHandler.sendEmptyMessage(6);
        }

        CancelDownloadingTask()
        {
            this$0 = DownloadFacade.this;
            super();
        }
    }

    public static interface IPreDownloadListener
    {

        public abstract void onDownloadBegin();

        public abstract void onDownloadFinish(ArrayList arraylist);
    }


    static final int MSG_DOWNLOAD_CONFIRM = 2;
    static final int MSG_DOWNLOAD_EXIST = 1;
    static final int MSG_DOWNLOAD_FINISH = 4;
    static final int MSG_DOWNLOAD_START = 3;
    static final int MSG_HIDE_CANCEL_DLG = 6;
    static final int MSG_SHOW_CANCEL_DLG = 5;
    boolean TEST;
    CancelDownloadingTask mCancelDownloadingTask;
    LoadingDialog mCancelWaitDialog;
    Context mContext;
    DownloadWaitDialog mDownloadWaitDialog;
    Handler mHandler;
    com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask mNeedToDownloadTask;
    ArrayList mNeedToDownloadTasks;
    ArrayList mOriginDownloadTasks;
    int mOverageTaskCount;
    IPreDownloadListener mPreDownloadListener;
    UpDownloadEngine mUpDownloadEngine;

    public DownloadFacade(Context context)
    {
        mContext = null;
        mUpDownloadEngine = null;
        mOverageTaskCount = 0;
        mNeedToDownloadTask = null;
        mOriginDownloadTasks = null;
        mNeedToDownloadTasks = null;
        mDownloadWaitDialog = null;
        mCancelWaitDialog = null;
        mPreDownloadListener = null;
        mCancelDownloadingTask = null;
        TEST = false;
        mHandler = new Handler() {

            final DownloadFacade this$0;

            public void handleMessage(Message message)
            {
                switch (message.what)
                {
                default:
                    return;

                case 1: // '\001'
                    showDownloadExistDlg();
                    return;

                case 2: // '\002'
                    showDownloadConfirmDlg(message.arg1);
                    return;

                case 3: // '\003'
                    notifyDownloadStart();
                    showWaitingDlg();
                    return;

                case 4: // '\004'
                    hideWaitingDlg();
                    toastDownloadFailedIfNeeded();
                    notifyDownloadFinish();
                    return;

                case 5: // '\005'
                    hideWaitingDlg();
                    showCancelWaitingDlg();
                    return;

                case 6: // '\006'
                    hideCancelWaitingDlg();
                    break;
                }
            }

            
            {
                this$0 = DownloadFacade.this;
                super();
            }
        };
        mContext = context;
        reCreateWaitDialog();
    }

    public static MediaItem downloadTaskToMediaItem(com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask, boolean flag)
    {
        MediaItem mediaitem = new MediaItem();
        mediaitem._id = downloadtask.mediaId;
        mediaitem.title = downloadtask.title;
        if (downloadtask.uri != null)
        {
            mediaitem.uri = Uri.parse(downloadtask.uri);
        }
        mediaitem.size = downloadtask.fileSize;
        mediaitem.isDownloadingFile = flag;
        mediaitem.videoOrImage = downloadtask.videoOrImage;
        mediaitem.status = downloadtask.status;
        return mediaitem;
    }

    private ArrayList getDownloadUris()
    {
        ArrayList arraylist;
        if (mOriginDownloadTasks == null)
        {
            arraylist = null;
        } else
        {
            arraylist = new ArrayList();
            Iterator iterator = mOriginDownloadTasks.iterator();
            while (iterator.hasNext()) 
            {
                com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask = (com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)iterator.next();
                Uri uri = makeUri(downloadtask.title, downloadtask.uri, OEMConfig.DOWNLOAD_PATH, downloadtask.dms_uuid, downloadtask.item_uuid);
                boolean flag = isFileExist(uri.toString().substring(7));
                Log.d("test", (new StringBuilder()).append("Download finish uri = ").append(uri).append(flag).toString());
                if (flag)
                {
                    arraylist.add(uri);
                }
            }
        }
        return arraylist;
    }

    private Vector getTestMediaItems()
    {
        Vector vector = new Vector();
        for (int i = 0; i < 10; i++)
        {
            MediaItem mediaitem = new MediaItem();
            mediaitem.isDownloadingFile = true;
            vector.add(mediaitem);
        }

        return vector;
    }

    public static boolean isFileExist(String s)
    {
        boolean flag;
        try
        {
            flag = (new File(s)).exists();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
        return flag;
    }

    public static boolean isMediaStoreSupported(com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask)
    {
        if (downloadtask != null)
        {
            String s = AbsPoolDriver.getDownloadPath(-1, OEMConfig.DOWNLOAD_PATH, downloadtask.title, downloadtask.uri, MyUPnPUtils.getItemResource(downloadtask.dms_uuid, downloadtask.item_uuid));
            String s1 = FileUtils.getExtension(s);
            if (FileUtils.isMediaStoreSupported(s) || s1.equalsIgnoreCase("mov"))
            {
                return true;
            }
        }
        return false;
    }

    private ArrayList makeAllDownloadTasks(IDataSource idatasource, int i)
    {
        ArrayList arraylist = new ArrayList();
        int j = idatasource.getCount();
        for (int k = 0; k < j; k++)
        {
            com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask = idatasource.makeDownloadTask(k);
            downloadtask.priority = i;
            if (!mUpDownloadEngine.isTaskExist(downloadtask.item_uuid, false) || mUpDownloadEngine.isFileDownloadFailed(downloadtask.item_uuid) || downloadtask.priority == 1 && !mUpDownloadEngine.isFileDownloading(downloadtask.item_uuid))
            {
                arraylist.add(downloadtask);
            }
        }

        return arraylist;
    }

    public static Uri makeUri(String s, String s1, String s2, String s3, String s4)
    {
        String s5 = AbsPoolDriver.getDownloadPath(-1, s2, s, s1, MyUPnPUtils.getItemResource(s3, s4));
        return Uri.parse((new StringBuilder()).append("file://").append(s5).toString());
    }

    public static com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask mediaItemToDownloadTask(MediaItem mediaitem, int i, Uri uri)
    {
        if (mediaitem == null)
        {
            return null;
        }
        com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask = new com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask();
        downloadtask.mediaClass = 3L;
        downloadtask.dms_uuid = RemoteDBMgr.instance().getCurrentServer();
        downloadtask.item_uuid = RemoteDBMgr.instance().getRemoteItemUUID(mediaitem._id);
        downloadtask.mediaId = mediaitem._id;
        downloadtask.title = mediaitem.title;
        if (uri == null)
        {
            uri = mediaitem.uri;
        }
        String s = null;
        if (uri != null)
        {
            s = uri.toString();
        }
        downloadtask.uri = s;
        downloadtask.fileSize = mediaitem.size;
        downloadtask.priority = i;
        downloadtask.videoOrImage = mediaitem.videoOrImage;
        return downloadtask;
    }

    private boolean needNotifyPreDownloadOk(String s)
    {
        if (mNeedToDownloadTasks != null)
        {
            Iterator iterator = mNeedToDownloadTasks.iterator();
            while (iterator.hasNext()) 
            {
                if (((com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)iterator.next()).uri.toString().equals(s))
                {
                    mOverageTaskCount = -1 + mOverageTaskCount;
                    if (mOverageTaskCount == 0)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void reCreateWaitDialog()
    {
        mDownloadWaitDialog = null;
    }

    void addDownloadByTask(com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask)
    {
        if (downloadtask != null && mContext != null)
        {
            if (!isMediaStoreSupported(downloadtask))
            {
                String s = AbsPoolDriver.getDownloadPath(-1, OEMConfig.DOWNLOAD_PATH, downloadtask.title, downloadtask.uri, MyUPnPUtils.getItemResource(downloadtask.dms_uuid, downloadtask.item_uuid));
                Context context = mContext;
                Context context1 = mContext;
                Object aobj[] = new Object[1];
                aobj[0] = (new StringBuilder()).append(".").append(FileUtils.getExtension(s)).toString();
                ToastMgr.showToast(context, context1.getString(0x7f0b019d, aobj), 0);
                return;
            }
            if (mUpDownloadEngine.downloadTask(downloadtask))
            {
                com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadStarted(mContext, 0);
                return;
            }
        }
    }

    public void cancelAll()
    {
        if (mUpDownloadEngine == null)
        {
            return;
        } else
        {
            mUpDownloadEngine.cancelAllTask();
            return;
        }
    }

    public void destroy()
    {
        mContext = null;
        mNeedToDownloadTask = null;
        mUpDownloadEngine = null;
        if (mDownloadWaitDialog != null)
        {
            mDownloadWaitDialog = null;
        }
    }

    public void download(com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask)
    {
        if (mUpDownloadEngine == null)
        {
            Log.e("test", "UpDownloadEngine is null");
        } else
        {
            if (downloadtask == null)
            {
                Log.e("test", "Download task is null");
                return;
            }
            if (mUpDownloadEngine.isFileDownloaded(downloadtask.item_uuid))
            {
                mNeedToDownloadTask = downloadtask;
                mHandler.sendEmptyMessage(1);
                return;
            }
            if (!mUpDownloadEngine.isTaskExist(downloadtask.item_uuid, false))
            {
                addDownloadByTask(downloadtask);
                return;
            }
            if (mUpDownloadEngine.isFileDownloadFailed(downloadtask.item_uuid))
            {
                addDownloadByTask(downloadtask);
                return;
            }
            if (downloadtask.priority == 1 && !mUpDownloadEngine.isFileDownloading(downloadtask.item_uuid))
            {
                addDownloadByTask(downloadtask);
                return;
            }
        }
    }

    public int downloadAll(ArrayList arraylist)
    {
        return mUpDownloadEngine.downloadTask(arraylist);
    }

    public void downloadAll(IDataSource idatasource, int i)
    {
        ArrayList arraylist;
        if (idatasource != null && mUpDownloadEngine != null && mContext != null)
        {
            if ((arraylist = makeAllDownloadTasks(idatasource, i)) != null && arraylist.size() != 0)
            {
                int j = mUpDownloadEngine.downloadTask(arraylist);
                if (j == 1)
                {
                    com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadStarted(mContext, 0);
                } else
                if (j > 1)
                {
                    Context context = mContext;
                    Resources resources = mContext.getResources();
                    Object aobj[] = new Object[1];
                    aobj[0] = String.valueOf(j);
                    ToastMgr.showToast(context, resources.getString(0x7f0b00b2, aobj), 0);
                }
                if (arraylist != null)
                {
                    arraylist.clear();
                    return;
                }
            }
        }
    }

    public void downloadAllWithConfirmDlg(int i, ArrayList arraylist)
    {
        if (arraylist == null)
        {
            return;
        }
        mOriginDownloadTasks = arraylist;
        ArrayList arraylist1 = needDownloadItems(arraylist);
        if (arraylist1 == null || arraylist1.size() == 0)
        {
            mHandler.sendEmptyMessage(4);
            return;
        }
        mNeedToDownloadTasks = arraylist1;
        if (isAllTasksDownloading(arraylist1))
        {
            ToastMgr.showToast(mContext, 0x7f0b0181, 0);
            return;
        } else
        {
            Message message = new Message();
            message.what = 2;
            message.arg1 = i;
            mHandler.sendMessage(message);
            return;
        }
    }

    public int getCurrentDownloadProgress()
    {
        if (mUpDownloadEngine == null)
        {
            return 0;
        } else
        {
            return mUpDownloadEngine.getCurrentDownloadProgress();
        }
    }

    public long getDownloadStatus(IDataSource idatasource, int i)
    {
        String s;
        if (mUpDownloadEngine != null && idatasource != null)
        {
            if ((s = idatasource.getRemoteItemUUID(i)) != null)
            {
                return mUpDownloadEngine.getDownloadStatus(s);
            }
        }
        return 0L;
    }

    public Vector getDownloadingItems()
    {
        Vector vector;
        if (TEST)
        {
            vector = getTestMediaItems();
        } else
        {
            if (mUpDownloadEngine == null)
            {
                Log.e("test", "getDownloading Items  upDownloadEngine is null");
                return null;
            }
            Hashtable hashtable = mUpDownloadEngine.getDownloadStates();
            vector = null;
            if (hashtable != null)
            {
                int i = hashtable.size();
                vector = null;
                if (i != 0)
                {
                    Log.d("test", (new StringBuilder()).append("getDownloading Items  in + size = ").append(hashtable.size()).toString());
                    Iterator iterator = hashtable.entrySet().iterator();
                    vector = null;
                    if (iterator != null)
                    {
                        vector = new Vector();
                        do
                        {
                            if (!iterator.hasNext())
                            {
                                break;
                            }
                            com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask = (com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)((java.util.Map.Entry)iterator.next()).getValue();
                            if (1L == downloadtask.status)
                            {
                                vector.add(0, downloadTaskToMediaItem(downloadtask, true));
                            } else
                            if (2L == downloadtask.status)
                            {
                                vector.add(downloadTaskToMediaItem(downloadtask, true));
                            }
                        } while (true);
                        if (vector.size() == 0)
                        {
                            return null;
                        }
                    }
                }
            }
        }
        return vector;
    }

    public UpDownloadEngine getUpDownloadEngine()
    {
        return mUpDownloadEngine;
    }

    void hideCancelWaitingDlg()
    {
        if (mCancelWaitDialog != null)
        {
            mCancelWaitDialog.dismiss();
        }
    }

    void hideWaitingDlg()
    {
        if (mDownloadWaitDialog != null)
        {
            mDownloadWaitDialog.dismiss();
        }
    }

    public boolean isAllTasksDownloadWaiting(ArrayList arraylist)
    {
        if (mUpDownloadEngine != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        int i;
        flag = false;
        i = 0;
_L6:
        if (i >= arraylist.size()) goto _L4; else goto _L3
_L3:
        flag = mUpDownloadEngine.isFileDownloadWaiting(((com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)arraylist.get(i)).item_uuid);
        if (!flag) goto _L4; else goto _L5
_L5:
        i++;
          goto _L6
    }

    public boolean isAllTasksDownloading(ArrayList arraylist)
    {
        if (mUpDownloadEngine != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        int i;
        flag = false;
        i = 0;
_L6:
        if (i >= arraylist.size()) goto _L4; else goto _L3
_L3:
        flag = mUpDownloadEngine.isFileDownloading(((com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)arraylist.get(i)).item_uuid);
        if (!flag) goto _L4; else goto _L5
_L5:
        i++;
          goto _L6
    }

    public boolean isAllTasksDownloadingOrWaiting(ArrayList arraylist)
    {
        if (mUpDownloadEngine != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        int i;
        flag = false;
        i = 0;
_L6:
        if (i >= arraylist.size()) goto _L4; else goto _L3
_L3:
        flag = mUpDownloadEngine.isFileDownloadingOrWaiting(((com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)arraylist.get(i)).item_uuid);
        if (!flag) goto _L4; else goto _L5
_L5:
        i++;
          goto _L6
    }

    public boolean isDownloading()
    {
        while (mUpDownloadEngine == null || mUpDownloadEngine.isDownloadPoolIdle()) 
        {
            return false;
        }
        return true;
    }

    public boolean isFileDownloadWaiting(String s)
    {
        if (mUpDownloadEngine == null)
        {
            return false;
        } else
        {
            return mUpDownloadEngine.isFileDownloadWaiting(s);
        }
    }

    public boolean isFileDownloaded(String s)
    {
        if (mUpDownloadEngine == null)
        {
            return false;
        } else
        {
            return mUpDownloadEngine.isFileDownloaded(s);
        }
    }

    public boolean isFileDownloading(String s)
    {
        if (mUpDownloadEngine == null)
        {
            return false;
        } else
        {
            return mUpDownloadEngine.isFileDownloading(s);
        }
    }

    public boolean isFileDownloadingOrWaiting(String s)
    {
        if (mUpDownloadEngine == null)
        {
            return false;
        } else
        {
            return mUpDownloadEngine.isFileDownloadingOrWaiting(s);
        }
    }

    ArrayList needDownloadItems(ArrayList arraylist)
    {
        ArrayList arraylist1;
        if (arraylist == null || arraylist.size() == 0)
        {
            arraylist1 = null;
        } else
        {
            arraylist1 = new ArrayList();
            Iterator iterator = arraylist.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask = (com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)iterator.next();
                if (downloadtask != null && downloadtask.item_uuid != null && !isFileDownloaded(downloadtask.item_uuid) && isMediaStoreSupported(downloadtask))
                {
                    arraylist1.add(downloadtask);
                }
            } while (true);
            if (arraylist1.size() <= 0)
            {
                arraylist1.clear();
                return null;
            }
        }
        return arraylist1;
    }

    void notifyDownloadFinish()
    {
        if (mPreDownloadListener != null)
        {
            mPreDownloadListener.onDownloadFinish(getDownloadUris());
        }
        if (mNeedToDownloadTasks != null)
        {
            mNeedToDownloadTasks.clear();
            mNeedToDownloadTasks = null;
        }
        if (mOriginDownloadTasks != null)
        {
            mOriginDownloadTasks.clear();
            mOriginDownloadTasks = null;
        }
        mOverageTaskCount = 0;
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.unregisterUpDownloadListener(this);
        }
    }

    void notifyDownloadStart()
    {
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.registerUpDownloadListener(this);
        }
        if (mPreDownloadListener != null)
        {
            mPreDownloadListener.onDownloadBegin();
        }
    }

    public void onProgress(String s, String s1, long l, long l1)
    {
    }

    public void onUpDownloadFinish(String s, String s1, Object obj, int i)
    {
        Log.d("zdf", (new StringBuilder()).append("[DownloadFacade] onUpDownloadFinish, errorcode = ").append(i).append(", uri = ").append(s1).toString());
        if (i != 816 && i != 818 && i != 817 && i != 819 && needNotifyPreDownloadOk(s1))
        {
            Log.d("zdf", "[DownloadFacade] onUpDownloadFinish, needNotifyPreDownloadOk: true");
            mHandler.sendEmptyMessage(4);
        }
        if (819 == i || 817 == i)
        {
            if (mOriginDownloadTasks != null)
            {
                mOriginDownloadTasks.clear();
                mOriginDownloadTasks = null;
            }
            mHandler.sendEmptyMessage(4);
        }
    }

    public void onUpDownloadStart(String s, String s1)
    {
    }

    public void onWaittingDlgBackPressed()
    {
        onWaittingDlgCancel();
    }

    void onWaittingDlgCancel()
    {
        if (mCancelDownloadingTask != null)
        {
            mCancelDownloadingTask.cancel(true);
            mCancelDownloadingTask = null;
        }
        mCancelDownloadingTask = new CancelDownloadingTask();
        mCancelDownloadingTask.execute(new Void[0]);
    }

    public void setContext(Context context)
    {
        mContext = context;
        reCreateWaitDialog();
    }

    public void setPreDownloadListener(IPreDownloadListener ipredownloadlistener)
    {
        mPreDownloadListener = ipredownloadlistener;
    }

    public void setUpDownloadEngine(UpDownloadEngine updownloadengine)
    {
        mUpDownloadEngine = updownloadengine;
    }

    void showCancelWaitingDlg()
    {
        if (mCancelWaitDialog == null)
        {
            mCancelWaitDialog = new LoadingDialog(mContext, 0x7f0d0004);
            mCancelWaitDialog.setText(0x7f0b0182);
            mCancelWaitDialog.setCancelable(false);
        }
        mCancelWaitDialog.show();
    }

    void showDownloadConfirmDlg(int i)
    {
        AlertDialog alertdialog;
        if (mContext != null)
        {
            if ((alertdialog = (new android.app.AlertDialog.Builder(mContext)).setMessage(mContext.getString(i)).setPositiveButton(0x7f0b001f, new android.content.DialogInterface.OnClickListener() {

        final DownloadFacade this$0;

        public void onClick(DialogInterface dialoginterface, int j)
        {
            mOverageTaskCount = downloadAll(mNeedToDownloadTasks);
            Log.i("zdf", (new StringBuilder()).append("*** [DownloadFacade] showDownloadConfirmDlg, mOverageTaskCount = ").append(mOverageTaskCount).toString());
            if (mOverageTaskCount > 0)
            {
                mHandler.sendEmptyMessage(3);
            } else
            if (mOverageTaskCount == 0)
            {
                toastDownloadFailedIfNeeded();
                return;
            }
        }

            
            {
                this$0 = DownloadFacade.this;
                super();
            }
    }).setNegativeButton(0x7f0b0020, new android.content.DialogInterface.OnClickListener() {

        final DownloadFacade this$0;

        public void onClick(DialogInterface dialoginterface, int j)
        {
            if (mUpDownloadEngine != null)
            {
                mUpDownloadEngine.unregisterUpDownloadListener(DownloadFacade.this);
            }
        }

            
            {
                this$0 = DownloadFacade.this;
                super();
            }
    }).setCancelable(true).create()) != null)
            {
                alertdialog.show();
                return;
            }
        }
    }

    void showDownloadExistDlg()
    {
        AlertDialog alertdialog;
        if (mContext != null)
        {
            if ((alertdialog = (new android.app.AlertDialog.Builder(mContext)).setMessage(0x7f0b017e).setPositiveButton(0x7f0b001f, new android.content.DialogInterface.OnClickListener() {

        final DownloadFacade this$0;

        public void onClick(DialogInterface dialoginterface, int i)
        {
            addDownloadByTask(mNeedToDownloadTask);
            if (mPreDownloadListener != null)
            {
                mPreDownloadListener.onDownloadBegin();
            }
        }

            
            {
                this$0 = DownloadFacade.this;
                super();
            }
    }).setNegativeButton(0x7f0b0020, new android.content.DialogInterface.OnClickListener() {

        final DownloadFacade this$0;

        public void onClick(DialogInterface dialoginterface, int i)
        {
        }

            
            {
                this$0 = DownloadFacade.this;
                super();
            }
    }).setCancelable(true).create()) != null)
            {
                alertdialog.show();
                return;
            }
        }
    }

    void showWaitingDlg()
    {
        if (mDownloadWaitDialog == null)
        {
            mDownloadWaitDialog = new DownloadWaitDialog(mContext, 0x7f0d0004);
        }
        mDownloadWaitDialog.setBackPressListener(this);
        mDownloadWaitDialog.show();
    }

    void toastDownloadFailedIfNeeded()
    {
        if (mContext == null || mOriginDownloadTasks == null || mOriginDownloadTasks.size() == 0)
        {
            return;
        }
        ArrayList arraylist = getDownloadUris();
        if (arraylist == null || arraylist.size() == 0)
        {
            boolean flag = true;
            boolean flag1 = false;
            for (Iterator iterator = mOriginDownloadTasks.iterator(); iterator.hasNext();)
            {
                if (!isMediaStoreSupported((com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)iterator.next()))
                {
                    flag1 = true;
                } else
                {
                    flag = false;
                }
            }

            String s = "";
            if (flag)
            {
                if (1 == mOriginDownloadTasks.size())
                {
                    com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask = (com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)mOriginDownloadTasks.get(0);
                    if (downloadtask != null)
                    {
                        String s1 = AbsPoolDriver.getDownloadPath(-1, OEMConfig.DOWNLOAD_PATH, downloadtask.title, downloadtask.uri, MyUPnPUtils.getItemResource(downloadtask.dms_uuid, downloadtask.item_uuid));
                        Context context = mContext;
                        Object aobj[] = new Object[1];
                        aobj[0] = (new StringBuilder()).append(".").append(FileUtils.getExtension(s1)).toString();
                        s = context.getString(0x7f0b019d, aobj);
                    }
                } else
                {
                    s = mContext.getResources().getString(0x7f0b0184);
                }
            } else
            if (flag1)
            {
                s = mContext.getResources().getString(0x7f0b0183);
            } else
            {
                s = mContext.getResources().getString(0x7f0b00da);
            }
            ToastMgr.showToast(mContext, s, 0);
        }
    }
}
