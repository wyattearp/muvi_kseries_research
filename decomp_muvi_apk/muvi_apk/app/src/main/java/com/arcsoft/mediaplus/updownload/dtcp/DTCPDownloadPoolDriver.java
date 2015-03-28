// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.dtcp;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Process;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.adk.atv.UPnPFlagsParameterUtils;
import com.arcsoft.adk.atv.dtcp.Dtcp;
import com.arcsoft.adk.atv.dtcp.DtcpSink;
import com.arcsoft.adk.atv.dtcp.DtcpSinkMove;
import com.arcsoft.adk.atv.dtcp.IDtcpSinkListener;
import com.arcsoft.adk.atv.dtcp.IDtcpSinkMoveListener;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.AbsPoolDriver;
import com.arcsoft.mediaplus.updownload.AbsTaskItem;
import com.arcsoft.mediaplus.updownload.IPoolDriver;
import com.arcsoft.mediaplus.updownload.MyUPnPUtils;
import com.arcsoft.mediaplus.updownload.db.UpDownloadDBMgr;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.NetworkUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DTCPDownloadPoolDriver extends AbsPoolDriver
    implements IPoolDriver
{
    public static class DTCPDownloadRequest extends com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest
    {

        public String mAkeIPAddr;
        public long mAkePort;
        private DtcpSink mDtcpSink;



/*
        static DtcpSink access$1902(DTCPDownloadRequest dtcpdownloadrequest, DtcpSink dtcpsink)
        {
            dtcpdownloadrequest.mDtcpSink = dtcpsink;
            return dtcpsink;
        }

*/

        public DTCPDownloadRequest(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest)
        {
            mAkeIPAddr = null;
            mAkePort = 0L;
            mDtcpSink = null;
            tableid = downloadrequest.tableid;
            dms_uuid = downloadrequest.dms_uuid;
            item_uuid = downloadrequest.item_uuid;
            upnp_class = downloadrequest.upnp_class;
            title = downloadrequest.title;
            uri = downloadrequest.uri;
            fileSize = downloadrequest.fileSize;
            listener = downloadrequest.listener;
            protocolInfo = downloadrequest.protocolInfo;
            parentdir = downloadrequest.parentdir;
        }
    }

    protected class DTCPDownloadTask
        implements Runnable
    {

        private int dest;
        private boolean mCancel;
        protected ReadWriteLock mLock;
        protected boolean mNeedControlSpeed;
        protected com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest mRequest;
        private boolean mbCopyFlag;
        final DTCPDownloadPoolDriver this$0;

        private void closeStream(boolean flag)
        {
            if (mbCopyFlag)
            {
                Log.i("DTCPDownloadPoolDriver", "close stream : copy");
                ((DTCPDownloadRequest)mRequest).mDtcpSink.CloseStream(flag);
                Log.i("DTCPDownloadPoolDriver", "after close stream : copy");
                return;
            } else
            {
                Log.i("DTCPDownloadPoolDriver", "close stream : move");
                ((DTCPMoveDownloadRequest)mRequest).mDtcpSinkMove.CloseStream();
                Log.i("DTCPDownloadPoolDriver", "after close stream : move");
                return;
            }
        }

        private int openStream(int i)
        {
            Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("open stream : drm_type = ").append(dest).toString());
            if (mbCopyFlag)
            {
                return ((DTCPDownloadRequest)mRequest).mDtcpSink.OpenStream(dest, i, mRequest.title);
            } else
            {
                return ((DTCPMoveDownloadRequest)mRequest).mDtcpSinkMove.OpenStream(dest, i, mRequest.title);
            }
        }

        private int realErrorcode()
        {
            if (mRequest.abortflag != 1);
            return mRequest.cancelflag != 1 ? 817 : 816;
        }

        private boolean setConnProp(HttpURLConnection httpurlconnection)
        {
            if (!mbCopyFlag)
            {
                byte abyte0[] = {
                    0
                };
                if (((DTCPMoveDownloadRequest)mRequest).mDtcpSinkMove.GetKxmLable(abyte0) != 0)
                {
                    Log.i("DTCP-Move", "GetKxmLable failed");
                    return false;
                }
                int i = (new Byte(abyte0[0])).intValue();
                if (i < 0)
                {
                    i += 256;
                }
                Log.i("DTCP-Move", (new StringBuilder()).append("BLKMove.dtcp.com: ").append(Integer.toHexString(i)).toString());
                httpurlconnection.setRequestProperty("BLKMove.dtcp.com", Integer.toHexString(i));
            }
            return true;
        }

        private int writeStream(byte abyte0[], int i, int ai[])
        {
            if (mbCopyFlag)
            {
                return ((DTCPDownloadRequest)mRequest).mDtcpSink.WriteStream(abyte0, i, ai);
            } else
            {
                return ((DTCPMoveDownloadRequest)mRequest).mDtcpSinkMove.WriteStream(abyte0, i, ai);
            }
        }

        public void abortTask(int i)
        {
            mRequest.abortflag = i;
        }

        public void cancelTask(int i)
        {
            mRequest.cancelflag = i;
        }

        public void controlSpeed()
        {
            mNeedControlSpeed = true;
        }

        public com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest getDownloadRequest()
        {
            return mRequest;
        }

        public void run()
        {
            int i;
            com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource;
            int l;
            InputStream inputstream;
            HttpURLConnection httpurlconnection;
            boolean flag;
            long l1;
            URL url;
            mLock.writeLock().lock();
            mWorks.add(this);
            mLock.writeLock().unlock();
            Process.setThreadPriority(10);
            String s;
            String as[];
            int j;
            int k;
            String s2;
            int i1;
            int k4;
            if (mRequest instanceof DTCPDownloadRequest)
            {
                mbCopyFlag = true;
            } else
            {
                mbCopyFlag = false;
            }
            s = Settings.instance().getDefaultDownloadDestination();
            as = 
// JavaClassFileOutputException: get_constant: invalid tag

        protected DTCPDownloadTask(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest, ReadWriteLock readwritelock)
        {
            this$0 = DTCPDownloadPoolDriver.this;
            super();
            mRequest = null;
            mLock = null;
            dest = 1;
            mNeedControlSpeed = false;
            mCancel = false;
            mRequest = downloadrequest;
            mLock = readwritelock;
        }
    }

    public static class DTCPMoveDownloadRequest extends com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest
    {

        public String mAkeIPAddr;
        public long mAkePort;
        private DtcpSinkMove mDtcpSinkMove;



/*
        static DtcpSinkMove access$1102(DTCPMoveDownloadRequest dtcpmovedownloadrequest, DtcpSinkMove dtcpsinkmove)
        {
            dtcpmovedownloadrequest.mDtcpSinkMove = dtcpsinkmove;
            return dtcpsinkmove;
        }

*/

        public DTCPMoveDownloadRequest(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest)
        {
            mAkeIPAddr = null;
            mAkePort = 0L;
            mDtcpSinkMove = null;
            tableid = downloadrequest.tableid;
            dms_uuid = downloadrequest.dms_uuid;
            item_uuid = downloadrequest.item_uuid;
            upnp_class = downloadrequest.upnp_class;
            title = downloadrequest.title;
            uri = downloadrequest.uri;
            fileSize = downloadrequest.fileSize;
            listener = downloadrequest.listener;
            protocolInfo = downloadrequest.protocolInfo;
            parentdir = downloadrequest.parentdir;
        }
    }


    public static final int MAX_THREAD_POOL_SIZE = 1;
    private final String TAG = "DTCPDownloadPoolDriver";
    private DTCPMoveDownloadRequest mCurMoveRequest;
    private DTCPDownloadRequest mCurRequest;
    private final IDtcpSinkListener mDtcpSinkListener = new IDtcpSinkListener() {

        final DTCPDownloadPoolDriver this$0;

        public void OnDtcpSinkAkeEnd(int i)
        {
            Log.d("DTCPDownloadPoolDriver", "OnDtcpSinkAkeEnd");
            if (i != 0)
            {
                Toast.makeText(show, (new StringBuilder()).append("ake error").append(i).toString(), 0).show();
                processDownloadError(mCurRequest, 820);
                return;
            }
            Toast.makeText(show, "ake ok", 0).show();
            access$300.writeLock().lock();
            DTCPDownloadTask dtcpdownloadtask = new DTCPDownloadTask(mCurRequest, access$300);
            mThreadPool.execute(dtcpdownloadtask);
            access$300.writeLock().unlock();
            return;
            RejectedExecutionException rejectedexecutionexception;
            rejectedexecutionexception;
            Log.e("DTCPDownloadPoolDriver", "RejectedExecutionException at discretion of RejectedExecutionHandler, if the task cannot be accepted for execution");
            access$300.writeLock().unlock();
            return;
            NullPointerException nullpointerexception;
            nullpointerexception;
            Log.e("DTCPDownloadPoolDriver", "NullPointerException - request is null");
            access$300.writeLock().unlock();
            return;
            Exception exception;
            exception;
            access$300.writeLock().unlock();
            throw exception;
        }

            
            {
                this$0 = DTCPDownloadPoolDriver.this;
                super();
            }
    };
    private final IDtcpSinkMoveListener mDtcpSinkMoveListener = new IDtcpSinkMoveListener() {

        final DTCPDownloadPoolDriver this$0;

        public void OnDtcpSinkMoveAkeEnd(int i)
        {
            Log.d("DTCPDownloadPoolDriver", "OnDtcpSinkMoveAkeEnd");
            if (i != 0)
            {
                Toast.makeText(show, (new StringBuilder()).append("ake error").append(i).toString(), 0).show();
                processDownloadError(mCurMoveRequest, 820);
                return;
            }
            Toast.makeText(show, "ake ok", 0).show();
            access$1500.writeLock().lock();
            DTCPDownloadTask dtcpdownloadtask = new DTCPDownloadTask(mCurMoveRequest, access$1500);
            mThreadPool.execute(dtcpdownloadtask);
            access$1500.writeLock().unlock();
            return;
            RejectedExecutionException rejectedexecutionexception;
            rejectedexecutionexception;
            Log.e("DTCPDownloadPoolDriver", "RejectedExecutionException at discretion of RejectedExecutionHandler, if the task cannot be accepted for execution");
            access$1500.writeLock().unlock();
            return;
            NullPointerException nullpointerexception;
            nullpointerexception;
            Log.e("DTCPDownloadPoolDriver", "NullPointerException - request is null");
            access$1500.writeLock().unlock();
            return;
            Exception exception;
            exception;
            access$1500.writeLock().unlock();
            throw exception;
        }

        public void OnDtcpSinkMoveEnd(int i)
        {
            if (i != 0)
            {
                Toast.makeText(show, (new StringBuilder()).append("move fail : ").append(i).toString(), 0).show();
            } else
            {
                Toast.makeText(show, (new StringBuilder()).append("move fail : ").append(i).toString(), 0).show();
            }
            access$1500.writeLock().lock();
            mCurMoveRequest.mDtcpSinkMove.CloseStream();
            access$1500.writeLock().unlock();
        }

            
            {
                this$0 = DTCPDownloadPoolDriver.this;
                super();
            }
    };
    protected ThreadPoolExecutor mThreadPool;
    protected ArrayList mWorks;

    public DTCPDownloadPoolDriver()
    {
        mCurRequest = null;
        mCurMoveRequest = null;
        mThreadPool = null;
        mWorks = new ArrayList();
    }

    private boolean download(DTCPDownloadRequest dtcpdownloadrequest)
    {
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("download id = ").append(dtcpdownloadrequest.tableid).toString());
        dtcpdownloadrequest.mDtcpSink = new DtcpSink(Dtcp.getInstance(), mDtcpSinkListener);
        mLock.writeLock().lock();
        mCurRequest = dtcpdownloadrequest;
        int i = mCurRequest.mDtcpSink.InitiateAKE(mCurRequest.mAkeIPAddr, mCurRequest.mAkePort);
        mLock.writeLock().unlock();
        return i == 0;
    }

    private boolean download(DTCPMoveDownloadRequest dtcpmovedownloadrequest)
    {
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("download id = ").append(dtcpmovedownloadrequest.tableid).toString());
        dtcpmovedownloadrequest.mDtcpSinkMove = new DtcpSinkMove(Dtcp.getInstance(), mDtcpSinkMoveListener);
        mLock.writeLock().lock();
        mCurMoveRequest = dtcpmovedownloadrequest;
        int i = mCurMoveRequest.mDtcpSinkMove.InitiateAKE(mCurMoveRequest.mAkeIPAddr, mCurMoveRequest.mAkePort);
        mLock.writeLock().unlock();
        return i == 0;
    }

    private int getCopyCount(String s, String s1)
    {
        return RemoteDBMgr.instance().getRemoteItemCopyCount(s, s1);
    }

    private String getHost(String s)
    {
        if (s != null && !s.equals(""))
        {
            String as[] = s.split(";");
            int i = as.length;
            int j = 0;
            while (j < i) 
            {
                String s1 = as[j];
                if (s1.contains("DTCP1HOST"))
                {
                    return s1.substring(10);
                }
                j++;
            }
        }
        return null;
    }

    private long getPort(String s)
    {
        if (s == null || s.equals(""))
        {
            return 0L;
        }
        String as[] = s.split(";");
        int i = as.length;
        int j = 0;
        do
        {
label0:
            {
                String s1 = null;
                if (j < i)
                {
                    String s2 = as[j];
                    if (!s2.contains("DTCP1PORT"))
                    {
                        break label0;
                    }
                    s1 = s2.substring(10);
                }
                return Long.parseLong(s1);
            }
            j++;
        } while (true);
    }

    public void abortAllTask()
    {
        Log.i("DTCPDownloadPoolDriver", "abortAllTask");
        mLock.writeLock().lock();
        for (Iterator iterator = mWorks.iterator(); iterator.hasNext();)
        {
            ((DTCPDownloadTask)iterator.next()).getDownloadRequest().abortflag = 2;
        }

        for (Iterator iterator1 = mThreadPool.getQueue().iterator(); iterator1.hasNext();)
        {
            ((DTCPDownloadTask)(Runnable)iterator1.next()).getDownloadRequest().abortflag = 2;
        }

        mLock.writeLock().unlock();
    }

    public int abortTask(String s)
    {
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("abortTask =").append(s).toString());
        int i = 0;
        mLock.writeLock().lock();
        Iterator iterator = mWorks.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DTCPDownloadTask dtcpdownloadtask = (DTCPDownloadTask)iterator.next();
            if (dtcpdownloadtask.getDownloadRequest().dms_uuid.equalsIgnoreCase(s))
            {
                dtcpdownloadtask.getDownloadRequest().abortflag = 1;
                i++;
            }
        } while (true);
        Iterator iterator1 = mThreadPool.getQueue().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Runnable runnable = (Runnable)iterator1.next();
            if (((DTCPDownloadTask)runnable).getDownloadRequest().dms_uuid.equalsIgnoreCase(s))
            {
                ((DTCPDownloadTask)runnable).getDownloadRequest().abortflag = 1;
                i++;
            }
        } while (true);
        mLock.writeLock().unlock();
        return i;
    }

    public void cancelAllTask(boolean flag)
    {
        Log.i("DTCPDownloadPoolDriver", "cancelAllTask");
        mLock.writeLock().lock();
        for (Iterator iterator = mWorks.iterator(); iterator.hasNext();)
        {
            DTCPDownloadTask dtcpdownloadtask = (DTCPDownloadTask)iterator.next();
            if (flag)
            {
                dtcpdownloadtask.getDownloadRequest().listener = null;
            }
            dtcpdownloadtask.getDownloadRequest().cancelflag = 2;
        }

        for (Iterator iterator1 = mThreadPool.getQueue().iterator(); iterator1.hasNext();)
        {
            Runnable runnable = (Runnable)iterator1.next();
            if (flag)
            {
                ((DTCPDownloadTask)runnable).getDownloadRequest().listener = null;
            }
            ((DTCPDownloadTask)runnable).getDownloadRequest().cancelflag = 2;
        }

        mLock.writeLock().unlock();
    }

    public int cancelTask(String s)
    {
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("cancelTask=").append(s).toString());
        int i = -1;
        mLock.writeLock().lock();
        Iterator iterator = mWorks.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DTCPDownloadTask dtcpdownloadtask = (DTCPDownloadTask)iterator.next();
            if (dtcpdownloadtask.getDownloadRequest().dms_uuid.equalsIgnoreCase(s))
            {
                dtcpdownloadtask.getDownloadRequest().cancelflag = 1;
                i++;
            }
        } while (true);
        Iterator iterator1 = mThreadPool.getQueue().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Runnable runnable = (Runnable)iterator1.next();
            if (((DTCPDownloadTask)runnable).getDownloadRequest().dms_uuid.equalsIgnoreCase(s))
            {
                ((DTCPDownloadTask)runnable).getDownloadRequest().cancelflag = 1;
                i++;
            }
        } while (true);
        mLock.writeLock().unlock();
        return i;
    }

    public boolean cancelTask(long l)
    {
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("cancelTask=").append(l).toString());
        mLock.writeLock().lock();
        Iterator iterator = mWorks.iterator();
        boolean flag1;
        do
        {
            boolean flag = iterator.hasNext();
            flag1 = false;
            if (!flag)
            {
                break;
            }
            DTCPDownloadTask dtcpdownloadtask = (DTCPDownloadTask)iterator.next();
            if (dtcpdownloadtask.getDownloadRequest().tableid != l)
            {
                continue;
            }
            dtcpdownloadtask.getDownloadRequest().cancelflag = 1;
            flag1 = true;
            break;
        } while (true);
        Iterator iterator1 = mThreadPool.getQueue().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Runnable runnable = (Runnable)iterator1.next();
            if (((DTCPDownloadTask)runnable).getDownloadRequest().tableid != l)
            {
                continue;
            }
            ((DTCPDownloadTask)runnable).getDownloadRequest().cancelflag = 1;
            break;
        } while (true);
        mLock.writeLock().unlock();
        return flag1;
    }

    public boolean cancelTask(Uri uri, boolean flag)
    {
        String s = uri.toString();
        if (flag)
        {
            mLock.writeLock().lock();
        }
        Iterator iterator = mWorks.iterator();
        boolean flag2;
        do
        {
            boolean flag1 = iterator.hasNext();
            flag2 = false;
            if (!flag1)
            {
                break;
            }
            DTCPDownloadTask dtcpdownloadtask = (DTCPDownloadTask)iterator.next();
            if (!dtcpdownloadtask.getDownloadRequest().uri.equals(s))
            {
                continue;
            }
            dtcpdownloadtask.getDownloadRequest().cancelflag = 1;
            flag2 = true;
            break;
        } while (true);
        Iterator iterator1 = mThreadPool.getQueue().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Runnable runnable = (Runnable)iterator1.next();
            if (!((DTCPDownloadTask)runnable).getDownloadRequest().uri.equals(s))
            {
                continue;
            }
            ((DTCPDownloadTask)runnable).getDownloadRequest().cancelflag = 1;
            break;
        } while (true);
        if (flag)
        {
            mLock.writeLock().unlock();
        }
        return flag2;
    }

    public void controlSpeed(int i, int j)
    {
        if (i <= 0)
        {
            i = 0;
            break MISSING_BLOCK_LABEL_6;
        } else
        {
            if (i > 100)
            {
                i = 100;
            }
            continue;
        }
        do
        {
            mPercent = i;
            int k;
            if (j < 1)
            {
                k = 1024;
            } else
            if (j > 100)
            {
                k = 0x19000;
            } else
            {
                k = j * 1024;
            }
            mBandWidth = k;
            mLock.writeLock().lock();
            for (Iterator iterator = mWorks.iterator(); iterator.hasNext(); ((DTCPDownloadTask)iterator.next()).controlSpeed()) { }
            mLock.writeLock().unlock();
            return;
        } while (true);
    }

    public boolean download(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest)
    {
        if (downloadrequest != null)
        {
            String s = getHost(downloadrequest.protocolInfo);
            long l = getPort(downloadrequest.protocolInfo);
            int i = getCopyCount(downloadrequest.dms_uuid, downloadrequest.item_uuid);
            Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("copy count = ").append(i).toString());
            if (UPnPFlagsParameterUtils.getflagbyBitFilter(downloadrequest.protocolInfo, com.arcsoft.adk.atv.UPnPFlagsParameterUtils.Flags.bit12_dtcp_move) == 0)
            {
                Log.i("DTCPDownloadPoolDriver", "===== COPY =====");
                DTCPDownloadRequest dtcpdownloadrequest = new DTCPDownloadRequest(downloadrequest);
                dtcpdownloadrequest.mAkeIPAddr = s;
                dtcpdownloadrequest.mAkePort = l;
                return download(dtcpdownloadrequest);
            }
            if (i >= 1)
            {
                Log.i("DTCPDownloadPoolDriver", "===== MOVE =====");
                DTCPMoveDownloadRequest dtcpmovedownloadrequest = new DTCPMoveDownloadRequest(downloadrequest);
                dtcpmovedownloadrequest.mAkeIPAddr = s;
                dtcpmovedownloadrequest.mAkePort = l;
                return download(dtcpmovedownloadrequest);
            }
        }
        return false;
    }

    public boolean getTask(long l, AbsTaskItem abstaskitem)
    {
        boolean flag1;
label0:
        {
            Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("getTask = ").append(l).toString());
            if (abstaskitem == null)
            {
                return false;
            }
            mLock.readLock().lock();
            Iterator iterator = mWorks.iterator();
            do
            {
                boolean flag = iterator.hasNext();
                flag1 = false;
                if (!flag)
                {
                    break;
                }
                DTCPDownloadTask dtcpdownloadtask = (DTCPDownloadTask)iterator.next();
                if (dtcpdownloadtask.getDownloadRequest().tableid != l)
                {
                    continue;
                }
                com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest1 = dtcpdownloadtask.getDownloadRequest();
                abstaskitem.currentbytes = downloadrequest1.downloadSize;
                abstaskitem.totalbytes = downloadrequest1.fileSize;
                flag1 = true;
                break;
            } while (true);
            if (flag1)
            {
                break label0;
            }
            Iterator iterator1 = mThreadPool.getQueue().iterator();
            Runnable runnable;
            do
            {
                if (!iterator1.hasNext())
                {
                    break label0;
                }
                runnable = (Runnable)iterator1.next();
            } while (((DTCPDownloadTask)runnable).getDownloadRequest().tableid != l);
            com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest = ((DTCPDownloadTask)runnable).getDownloadRequest();
            abstaskitem.currentbytes = downloadrequest.downloadSize;
            abstaskitem.totalbytes = downloadrequest.fileSize;
            flag1 = true;
        }
        mLock.readLock().unlock();
        return flag1;
    }

    public void init(Context context, UpDownloadDBMgr updownloaddbmgr)
    {
        Log.i("DTCPDownloadPoolDriver", "init");
        if (mThreadPool != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            mContext = context;
            mUpDownloadDBMgr = updownloaddbmgr;
            mThreadPool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
            mLock = new ReentrantReadWriteLock(false);
            return;
        }
    }

    public boolean isThreadPoolActive()
    {
        mLock.readLock().lock();
        boolean flag;
        if (mWorks.size() > 0 || mThreadPool.getActiveCount() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mLock.readLock().unlock();
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("isThreadPoolActive = ").append(flag).append("; mWorks = ").append(mWorks.size()).append("; thread = ").append(mThreadPool.getActiveCount()).toString());
        return flag;
    }

    public void recycle()
    {
    }

    public void uninit()
    {
        Log.i("DTCPDownloadPoolDriver", "uninit");
        if (mThreadPool == null)
        {
            throw new IllegalStateException("Has Uninitialized.");
        } else
        {
            cancelAllTask(true);
            mThreadPool.shutdown();
            mThreadPool = null;
            mLock = null;
            mUpDownloadDBMgr = null;
            return;
        }
    }






















}
