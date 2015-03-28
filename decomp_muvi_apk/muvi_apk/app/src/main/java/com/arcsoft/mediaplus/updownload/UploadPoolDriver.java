// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.UploadManager;
import com.arcsoft.mediaplus.updownload.db.UpDownloadDBMgr;
import com.arcsoft.util.debug.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            AbsTaskItem

public class UploadPoolDriver
{
    public static interface IUploadListener
    {

        public abstract void onUploadFinished(UploadResult uploadresult);

        public abstract void onUploadProgress(UploadRequest uploadrequest, long l);

        public abstract void onUploadStarted(UploadRequest uploadrequest, long l);
    }

    private class UploadHandler extends Handler
    {

        final int MESSAGE_REQUEST = 1;
        final UploadPoolDriver this$0;

        public void handleMessage(Message message)
        {
            if (message.what != 1)
            {
                return;
            }
            mLock.writeLock().lock();
            UploadRequest uploadrequest = null;
            int i = -1;
            int j = -1 + mQueue.size();
            while (j >= 0) 
            {
                uploadrequest = (UploadRequest)mQueue.get(j);
                if (uploadrequest.cancelflag || uploadrequest.abortflag)
                {
                    char c2;
                    if (uploadrequest.cancelflag)
                    {
                        c2 = '\u0330';
                    } else
                    {
                        c2 = '\u0333';
                    }
                    processUploadCancel(uploadrequest, c2);
                    mQueue.remove(uploadrequest);
                }
                j--;
            }
            if (mQueue.size() > 0)
            {
                uploadrequest = (UploadRequest)mQueue.get(0);
                i = uploadrequest.state;
            }
            mLock.writeLock().unlock();
            if (i > -1)
            {
                if (i == 1)
                {
                    processUploadStart(uploadrequest);
                } else
                if (i == 2)
                {
                    processUploadProgress(uploadrequest);
                } else
                if (i == 3)
                {
                    if (uploadrequest.uploadresult == 0)
                    {
                        processUploadSuccess(uploadrequest);
                    } else
                    {
                        char c;
                        if (uploadrequest.uploadresult == 2)
                        {
                            c = '\u03F5';
                        } else
                        {
                            c = '\u03F6';
                        }
                        processUploadError(uploadrequest, c);
                    }
                    mLock.writeLock().lock();
                    mQueue.remove(uploadrequest);
                    mLock.writeLock().unlock();
                }
                if (uploadrequest.cancelflag || uploadrequest.abortflag)
                {
                    char c1;
                    if (uploadrequest.cancelflag)
                    {
                        c1 = '\u0330';
                    } else
                    {
                        c1 = '\u0333';
                    }
                    processUploadCancel(uploadrequest, c1);
                    mLock.writeLock().lock();
                    mQueue.remove(uploadrequest);
                    mLock.writeLock().unlock();
                } else
                if (uploadrequest.state == 4)
                {
                    mLock.writeLock().lock();
                    mQueue.remove(uploadrequest);
                    mLock.writeLock().unlock();
                }
            }
            mLock.readLock().lock();
            if (mQueue.size() > 0)
            {
                mHandler.getClass();
                sendEmptyMessageDelayed(1, 1000 * mPercent);
            }
            mLock.readLock().unlock();
        }

        UploadHandler(Looper looper)
        {
            this$0 = UploadPoolDriver.this;
            super(looper);
        }
    }

    public static class UploadRequest
    {

        private boolean abortflag;
        private boolean cancelflag;
        public String dms_uuid;
        public long fileSize;
        public IUploadListener listener;
        public String protocolInfo;
        public int state;
        public long tableid;
        public String title;
        public int uploadId;
        public long uploadSize;
        private int uploadresult;
        public String uri;
        public Object userdata;



/*
        static boolean access$002(UploadRequest uploadrequest, boolean flag)
        {
            uploadrequest.cancelflag = flag;
            return flag;
        }

*/



/*
        static boolean access$102(UploadRequest uploadrequest, boolean flag)
        {
            uploadrequest.abortflag = flag;
            return flag;
        }

*/



/*
        static int access$702(UploadRequest uploadrequest, int i)
        {
            uploadrequest.uploadresult = i;
            return i;
        }

*/

        public UploadRequest()
        {
            uploadSize = 0L;
            uploadresult = -1;
            cancelflag = false;
            abortflag = false;
        }
    }

    public class UploadResult
    {

        public int errorcode;
        public UploadRequest request;
        public long tableid;
        final UploadPoolDriver this$0;
        public int uploadresult;

        public UploadResult()
        {
            this$0 = UploadPoolDriver.this;
            super();
        }
    }


    private static final int STATE_FAILED = 4;
    private static final int STATE_RUNNING = 2;
    private static final int STATE_START = 1;
    private static final int STATE_UPLOADRESULT = 3;
    private final String DLNA_VIDEOSUFFIX = ".3gp;.3g2;.mp4;.mpg;.mpeg;";
    private final String TAG = "UploadPoolDriver";
    private Context mContext;
    private UploadHandler mHandler;
    private ReadWriteLock mLock;
    private int mPercent;
    private ArrayList mQueue;
    private HandlerThread mThreadPool;
    private UpDownloadDBMgr mUpDownloadDBMgr;
    private com.arcsoft.adk.atv.UploadManager.IUploadResultListener mUploadResultlistener;

    public UploadPoolDriver()
    {
        mThreadPool = null;
        mLock = null;
        mHandler = null;
        mQueue = new ArrayList();
        mContext = null;
        mUpDownloadDBMgr = null;
        mPercent = 1;
        mUploadResultlistener = new com.arcsoft.adk.atv.UploadManager.IUploadResultListener() {

            final UploadPoolDriver this$0;

            public void OnUploadResult(int i, int j)
            {
                Log.i("UploadPoolDriver", (new StringBuilder()).append("OnUploadResult upload_id=").append(i).append(", errorcode=").append(j).toString());
                mLock.writeLock().lock();
                Iterator iterator = mQueue.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    UploadRequest uploadrequest = (UploadRequest)iterator.next();
                    if (uploadrequest.uploadId != i)
                    {
                        continue;
                    }
                    uploadrequest.state = 3;
                    uploadrequest.uploadresult = j;
                    break;
                } while (true);
                mLock.writeLock().unlock();
            }

            
            {
                this$0 = UploadPoolDriver.this;
                super();
            }
        };
    }

    private boolean matchVideoM2TSMimeType(String s)
    {
        int i;
        if (s != null)
        {
            if ((i = s.lastIndexOf(".")) >= 0)
            {
                String s1 = s.substring(i);
                int j = 0;
                int k = 0;
                do
                {
                    k = ".3gp;.3g2;.mp4;.mpg;.mpeg;".indexOf(";", k + 1);
                    if (k != -1)
                    {
                        if (s1.compareToIgnoreCase(".3gp;.3g2;.mp4;.mpg;.mpeg;".substring(j, k)) == 0)
                        {
                            return true;
                        }
                        j = k + 1;
                    }
                } while (k != -1);
                return false;
            }
        }
        return false;
    }

    private void processUploadCancel(UploadRequest uploadrequest, int i)
    {
        Log.i("UploadPoolDriver", "processUploadCancel");
        if (uploadrequest.state == 2)
        {
            DLNA.instance().getUploadManager().UploaderCancel(uploadrequest.uploadId);
        }
        if (uploadrequest.listener != null)
        {
            UploadResult uploadresult = new UploadResult();
            uploadresult.request = uploadrequest;
            uploadresult.tableid = uploadrequest.tableid;
            uploadresult.errorcode = i;
            uploadrequest.listener.onUploadFinished(uploadresult);
        }
    }

    private void processUploadError(UploadRequest uploadrequest, int i)
    {
        Log.i("UploadPoolDriver", "processUploadError");
        if (uploadrequest.listener != null)
        {
            UploadResult uploadresult = new UploadResult();
            uploadresult.request = uploadrequest;
            uploadresult.tableid = uploadrequest.tableid;
            uploadresult.errorcode = i;
            uploadrequest.listener.onUploadFinished(uploadresult);
        }
    }

    private boolean processUploadProgress(UploadRequest uploadrequest)
    {
        Log.i("UploadPoolDriver", "processUploadProgress");
        int ai[] = {
            0
        };
        int ai1[] = {
            0
        };
        boolean flag = DLNA.instance().getUploadManager().GetUploaderProgress(uploadrequest.uploadId, ai, ai1);
        if (uploadrequest.cancelflag)
        {
            return false;
        }
        uploadrequest.fileSize = ai[0];
        uploadrequest.uploadSize = ai1[0];
        Log.i("UploadPoolDriver", (new StringBuilder()).append("GetUploaderProgress result=").append(flag).append(", upload size=").append(ai1[0]).append(", total=").append(ai[0]).toString());
        if (uploadrequest.listener != null)
        {
            uploadrequest.listener.onUploadProgress(uploadrequest, uploadrequest.tableid);
        }
        return true;
    }

    private boolean processUploadStart(UploadRequest uploadrequest)
    {
        int ai[];
        char c;
        String s;
        Log.i("UploadPoolDriver", "processUploadStart");
        if (uploadrequest.listener != null)
        {
            uploadrequest.listener.onUploadStarted(uploadrequest, uploadrequest.tableid);
        }
        ai = (new int[] {
            0
        });
        c = '\u0320';
        if (uploadrequest.uri == null)
        {
            Log.e("UploadPoolDriver", "uri is null!");
            c = '\u0323';
        } else
        {
label0:
            {
                s = Uri.parse(uploadrequest.uri).getPath();
                if (s != null)
                {
                    break label0;
                }
                Log.e("UploadPoolDriver", "path is null!");
                c = '\u0323';
            }
        }
_L1:
        Exception exception;
        long l;
        if (c == '\u0320')
        {
            uploadrequest.uploadId = ai[0];
            uploadrequest.state = 2;
            return true;
        } else
        {
            uploadrequest.state = 4;
            processUploadError(uploadrequest, c);
            return false;
        }
        l = (new File(s)).length();
        if (l / 2L < 0x40000000L)
        {
            break MISSING_BLOCK_LABEL_203;
        }
        Log.e("UploadPoolDriver", (new StringBuilder()).append("file size excel 2G =").append(l).toString());
        c = '\u03F4';
          goto _L1
        exception;
        exception.printStackTrace();
        Log.e("UploadPoolDriver", "get file length fail!");
        c = '\u0322';
          goto _L1
        DLNA.instance().getUploadManager().EnableUploader(true);
        Log.v("UploadPoolDriver", (new StringBuilder()).append("upload protocolinfo = ").append(uploadrequest.protocolInfo).toString());
        boolean flag = DLNA.instance().getUploadManager().UploadFile(uploadrequest.dms_uuid, uploadrequest.title, s, uploadrequest.protocolInfo, ai);
        Log.i("UploadPoolDriver", (new StringBuilder()).append("processUploadStart(), UploadFile result = ").append(flag).toString());
        if (!flag)
        {
            c = '\u03F6';
        }
          goto _L1
    }

    private void processUploadSuccess(UploadRequest uploadrequest)
    {
        Log.i("UploadPoolDriver", "processUploadSuccess");
        if (uploadrequest.listener != null)
        {
            UploadResult uploadresult = new UploadResult();
            uploadresult.request = uploadrequest;
            uploadresult.tableid = uploadrequest.tableid;
            uploadresult.errorcode = 1015;
            uploadrequest.listener.onUploadFinished(uploadresult);
        }
    }

    private void recycle()
    {
    }

    public void abortAllTask()
    {
        Log.i("UploadPoolDriver", "abortAllTask");
        mLock.writeLock().lock();
        for (Iterator iterator = mQueue.iterator(); iterator.hasNext();)
        {
            ((UploadRequest)iterator.next()).abortflag = true;
        }

        mLock.writeLock().unlock();
    }

    public boolean abortTask(String s)
    {
        Log.i("UploadPoolDriver", (new StringBuilder()).append("abortTask =").append(s).toString());
        mLock.writeLock().lock();
        Iterator iterator = mQueue.iterator();
        boolean flag1;
        do
        {
            boolean flag = iterator.hasNext();
            flag1 = false;
            if (!flag)
            {
                break;
            }
            UploadRequest uploadrequest = (UploadRequest)iterator.next();
            if (!uploadrequest.dms_uuid.equalsIgnoreCase(s))
            {
                continue;
            }
            uploadrequest.abortflag = true;
            flag1 = true;
            break;
        } while (true);
        mLock.writeLock().unlock();
        return flag1;
    }

    public void cancelAllTask(boolean flag)
    {
        Log.i("UploadPoolDriver", "cancelAllTask");
        mLock.writeLock().lock();
        UploadRequest uploadrequest;
        for (Iterator iterator = mQueue.iterator(); iterator.hasNext(); processUploadCancel(uploadrequest, 816))
        {
            uploadrequest = (UploadRequest)iterator.next();
            if (flag)
            {
                uploadrequest.listener = null;
            }
            uploadrequest.cancelflag = true;
        }

        mQueue.clear();
        mLock.writeLock().unlock();
    }

    public boolean cancelTask(long l)
    {
        Log.i("UploadPoolDriver", (new StringBuilder()).append("cancelTask =").append(l).toString());
        mLock.writeLock().lock();
        Iterator iterator = mQueue.iterator();
        boolean flag1;
        do
        {
            boolean flag = iterator.hasNext();
            flag1 = false;
            if (!flag)
            {
                break;
            }
            UploadRequest uploadrequest = (UploadRequest)iterator.next();
            if (uploadrequest.tableid != l)
            {
                continue;
            }
            uploadrequest.cancelflag = true;
            flag1 = true;
            break;
        } while (true);
        mLock.writeLock().unlock();
        return flag1;
    }

    public void controlSpeed(int i, int j)
    {
        Log.i("UploadPoolDriver", "controlSpeed");
        mLock.writeLock().lock();
        if (j != 10) goto _L2; else goto _L1
_L1:
        int k = 1;
_L4:
        DLNA.instance().getUploadManager().SetUploadRateLevel(k);
        mLock.writeLock().unlock();
        return;
_L2:
        k = 0;
        if (j == 100)
        {
            k = 2;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public boolean getTask(long l, AbsTaskItem abstaskitem)
    {
        Log.i("UploadPoolDriver", (new StringBuilder()).append("getTask=").append(l).toString());
        if (abstaskitem == null)
        {
            return false;
        }
        mLock.readLock().lock();
        Iterator iterator = mQueue.iterator();
        boolean flag1;
        do
        {
            boolean flag = iterator.hasNext();
            flag1 = false;
            if (!flag)
            {
                break;
            }
            UploadRequest uploadrequest = (UploadRequest)iterator.next();
            if (uploadrequest.tableid != l)
            {
                continue;
            }
            abstaskitem.currentbytes = uploadrequest.uploadSize;
            abstaskitem.totalbytes = uploadrequest.fileSize;
            flag1 = true;
            break;
        } while (true);
        mLock.readLock().unlock();
        return flag1;
    }

    public void init(Context context, UpDownloadDBMgr updownloaddbmgr)
    {
        if (mThreadPool != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            mContext = context;
            mUpDownloadDBMgr = updownloaddbmgr;
            mThreadPool = new HandlerThread("UploadPoolDriver");
            mThreadPool.setPriority(10);
            mThreadPool.start();
            mHandler = new UploadHandler(mThreadPool.getLooper());
            mLock = new ReentrantReadWriteLock(false);
            DLNA.instance().getUploadManager().registerContentUpdatedListener(mUploadResultlistener);
            return;
        }
    }

    public boolean isThreadPoolActive()
    {
        mLock.readLock().lock();
        boolean flag;
        if (mQueue.size() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mLock.readLock().unlock();
        Log.i("UploadPoolDriver", (new StringBuilder()).append("isThreadPoolActive = ").append(flag).toString());
        return flag;
    }

    public void uninit()
    {
        Log.i("UploadPoolDriver", "uninit");
        if (mThreadPool == null)
        {
            throw new IllegalStateException("Has Uninitialized.");
        } else
        {
            DLNA.instance().getUploadManager().unregisterContentUpdatedListener(mUploadResultlistener);
            mUpDownloadDBMgr = null;
            cancelAllTask(true);
            UploadHandler uploadhandler = mHandler;
            mHandler.getClass();
            uploadhandler.removeMessages(1);
            mHandler = null;
            mThreadPool.quit();
            mThreadPool = null;
            return;
        }
    }

    public boolean upload(UploadRequest uploadrequest)
    {
        Log.i("UploadPoolDriver", (new StringBuilder()).append("upload id = ").append(uploadrequest.tableid).toString());
        mLock.writeLock().lock();
        for (Iterator iterator = mQueue.iterator(); iterator.hasNext();)
        {
            if (((UploadRequest)iterator.next()).tableid == uploadrequest.tableid)
            {
                Log.i("UploadPoolDriver", (new StringBuilder()).append("request has exist,id=").append(uploadrequest.tableid).append(", uri=").append(uploadrequest.uri).toString());
                Log.i("UploadPoolDriver", (new StringBuilder()).append("request has exist,uploadid=").append(uploadrequest.uploadId).append(", uuid=").append(uploadrequest.dms_uuid).toString());
                mLock.writeLock().unlock();
                return false;
            }
        }

        uploadrequest.state = 1;
        mQueue.add(uploadrequest);
        UploadHandler uploadhandler = mHandler;
        mHandler.getClass();
        if (!uploadhandler.hasMessages(1))
        {
            UploadHandler uploadhandler1 = mHandler;
            mHandler.getClass();
            uploadhandler1.sendEmptyMessageDelayed(1, 1000L);
        }
        mLock.writeLock().unlock();
        return true;
    }









}
