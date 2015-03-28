// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.app.Application;
import android.net.Uri;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.oem.SharpUtils;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.arcsoft.adk.atv:
//            DLNA, UPCPCallback, ServerManager

public final class UploadManager
{
    private class ArcMatchDLNAUpload extends LocalMatchDLNAUpload
    {

        final UploadManager this$0;

        public boolean matchDLNAUploadProfile(String s, Uri uri, String s1, Integer integer)
        {
            if (!super.matchDLNAUploadProfile(s, uri, s1, integer))
            {
                return false;
            }
            String s2 = uri.getEncodedPath();
            if (s1.startsWith("image/"))
            {
                String s5 = SharpUtils.getMPOFilePath(s2);
                if (s5 != null)
                {
                    s2 = s5;
                }
            }
            String s3 = DLNA.instance().getFileProtocolInfo(s2);
            if (s3 == null || s3.length() < 1)
            {
                Log.w("matchDLNAUploadProfile", "getFileProtocolInfo fail");
                return false;
            } else
            {
                String s4 = updateMimeType(s3, s1);
                return DLNA.instance().getServerManager().matchMediaSupportDLNAUpload(s, s3, s4);
            }
        }

        public void recycle()
        {
        }

        private ArcMatchDLNAUpload()
        {
            this$0 = UploadManager.this;
            super();
        }

    }

    public static interface IUploadResultListener
    {

        public abstract void OnUploadResult(int i, int j);
    }

    private abstract class LocalMatchDLNAUpload
    {

        private static final String DLNAUPLOAD_AUDIOSUFFIX = ".mp3;.m4a;.wma;.wav;.pcm;.adts;";
        private static final String DLNAUPLOAD_IMAGESUFFIX = ".gif;.jpg;.jpeg;.jpe;.jp2;.png;.bmp;.tiff;.thm;";
        private static final String DLNAUPLOAD_VIDEOSUFFIX = ".ts;.m2ts;.tts;.3gp;.3g2;.mp4;.mpg;.mpeg;.wmv;.asf;.avi;.divx;.xvid;.mov;";
        final String mMimeTypeMap[] = {
            "video/mp4", "video/3gpp"
        };
        final UploadManager this$0;

        protected boolean matchDLNAStackFileType(String s, Integer integer)
        {
            return integer.intValue() > 0;
        }

        protected boolean matchDLNAStackMimeType(String s, String s1)
        {
            if (s != null) goto _L2; else goto _L1
_L1:
            int i;
            return false;
_L2:
            if ((i = s.lastIndexOf(".")) < 0) goto _L1; else goto _L3
_L3:
            String s3;
            int j;
            int k;
            String s2 = s.substring(i);
            if (s1.startsWith("image/"))
            {
                s3 = ".gif;.jpg;.jpeg;.jpe;.jp2;.png;.bmp;.tiff;.thm;";
            } else
            {
                if (!s1.startsWith("audio/"))
                {
                    continue; /* Loop/switch isn't completed */
                }
                s3 = ".mp3;.m4a;.wma;.wav;.pcm;.adts;";
            }
_L5:
            j = 0;
            k = 0;
_L7:
            k = s3.indexOf(";", k + 1);
            if (k == -1)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (s2.compareToIgnoreCase(s3.substring(j, k)) == 0)
            {
                return true;
            }
            break MISSING_BLOCK_LABEL_113;
            if (!s1.startsWith("video/")) goto _L1; else goto _L4
_L4:
            s3 = ".ts;.m2ts;.tts;.3gp;.3g2;.mp4;.mpg;.mpeg;.wmv;.asf;.avi;.divx;.xvid;.mov;";
              goto _L5
            j = k + 1;
            if (k != -1) goto _L7; else goto _L6
_L6:
            return false;
              goto _L5
        }

        public boolean matchDLNAUploadProfile(String s, Uri uri, String s1, Integer integer)
        {
            if (s == null || uri == null || s1 == null)
            {
                return false;
            }
            String s2 = uri.getEncodedPath();
            boolean flag;
            if (integer.intValue() >= 0)
            {
                flag = matchDLNAStackFileType(s2, integer);
            } else
            {
                flag = matchDLNAStackMimeType(s2, s1);
            }
            if (!flag)
            {
                Log.w("matchDLNAUploadProfile", (new StringBuilder()).append("not match dlna stack mime type =").append(s1).append(", path=").append(s2).toString());
                return false;
            }
            if (DLNA.instance().getServerManager() == null)
            {
                Log.w("matchDLNAUploadProfile", "server manager null");
                return false;
            } else
            {
                return true;
            }
        }

        protected boolean matchVideoM2TSMimeType(String s)
        {
            if (s != null)
            {
                String as[] = mMimeTypeMap;
                int i = as.length;
                int j = 0;
                while (j < i) 
                {
                    if (as[j].equalsIgnoreCase(s))
                    {
                        return true;
                    }
                    j++;
                }
            }
            return false;
        }

        abstract void recycle();

        protected String updateMimeType(String s, String s1)
        {
            int i = s1.indexOf('/');
            String s2 = null;
            if (i > 0)
            {
                s2 = s1.substring(0, 1 + s1.indexOf('/'));
            }
            if (s2 != null)
            {
                int j = s.indexOf(s2);
                if (j > 0)
                {
                    int k;
                    if (s.indexOf("CONTENTFORMAT=") > 0)
                    {
                        k = s.indexOf('"', j);
                    } else
                    {
                        k = s.indexOf(':', j);
                    }
                    if (k > 0)
                    {
                        s1 = s.substring(j, k);
                    }
                }
            }
            return s1;
        }

        private LocalMatchDLNAUpload()
        {
            this$0 = UploadManager.this;
            super();
        }

    }


    public static final String PROFILE_3GP = "*:*:video/3gpp:*";
    public static final String PROFILE_IO_DATA = "AVC_MP4_BL_L31_HD_AAC";
    public static final String PROFILE_MP4 = "*:*:video/mp4:*";
    public static final String PROFILE_SHARP_BD = "AVC_TS_JP_AAC_T";
    public static final String UPLOAD_OBJECT_ID = "DLNA.ORG_AnyContainer";
    private Application mApplication;
    private DLNA mDLNA;
    private final DLNA.IOnDLNAStatusChangeListener mDLNAStatusListener = new DLNA.IOnDLNAStatusChangeListener() {

        final UploadManager this$0;

        public void OnDLNAConnected()
        {
        }

        public void OnDLNADisconnected()
        {
        }

        public void OnDLNAInternalError(int i)
        {
        }

            
            {
                this$0 = UploadManager.this;
                super();
            }
    };
    private LocalMatchDLNAUpload mLocalMatchDLNAUpload;
    private final UPCPCallback mUpcpCallback = new UPCPCallback() {

        final UploadManager this$0;

        public void OnUploadResult(int i, int j)
        {
            IUploadResultListener aiuploadresultlistener[] = getContentListenersCopy();
            if (aiuploadresultlistener != null)
            {
                int k = aiuploadresultlistener.length;
                for (int l = 0; l < k; l++)
                {
                    aiuploadresultlistener[l].OnUploadResult(i, j);
                }

            }
        }

            
            {
                this$0 = UploadManager.this;
                super();
            }
    };
    private final List mUploadResultListener = new ArrayList();

    UploadManager(Application application, DLNA dlna)
    {
        mApplication = null;
        mDLNA = null;
        mLocalMatchDLNAUpload = null;
        mDLNA = dlna;
        mApplication = application;
        mDLNA.registerDLNAStatusListener(mDLNAStatusListener);
        static class _cls3
        {

            static final int $SwitchMap$com$arcsoft$mediaplus$oem$OEMConfig$OEMName[] = new int[com.arcsoft.mediaplus.oem.OEMConfig.OEMName.values().length];

        }

        int _tmp = _cls3..SwitchMap.com.arcsoft.mediaplus.oem.OEMConfig.OEMName[OEMConfig.PROJECT_NAME.ordinal()];
        mLocalMatchDLNAUpload = new ArcMatchDLNAUpload();
    }

    private void checkDLNAorThrow()
    {
        if (mDLNA == null)
        {
            throw new IllegalStateException("Invalide ServerManager State - DLNA obj is null");
        } else
        {
            return;
        }
    }

    private IUploadResultListener[] getContentListenersCopy()
    {
        List list = mUploadResultListener;
        list;
        JVM INSTR monitorenter ;
        int i = mUploadResultListener.size();
        IUploadResultListener aiuploadresultlistener[];
        aiuploadresultlistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        IUploadResultListener aiuploadresultlistener1[] = new IUploadResultListener[mUploadResultListener.size()];
        aiuploadresultlistener = (IUploadResultListener[])mUploadResultListener.toArray(aiuploadresultlistener1);
        list;
        JVM INSTR monitorexit ;
        return aiuploadresultlistener;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean EnableUploader(boolean flag)
    {
        checkDLNAorThrow();
        return DLNA.JNI_EnableUploader(mDLNA.mNativeUPnP, flag) == 0;
    }

    public boolean GetUploaderProgress(int i, int ai[], int ai1[])
    {
        checkDLNAorThrow();
        return DLNA.JNI_GetUploaderProgress(mDLNA.mNativeUPnP, i, ai, ai1) == 0;
    }

    public boolean SetUploadRateLevel(int i)
    {
        checkDLNAorThrow();
        return DLNA.JNI_SetUploadRateLevel(mDLNA.mNativeUPnP, i) == 0;
    }

    public boolean UploadFile(String s, String s1, String s2, String s3, int ai[])
    {
        checkDLNAorThrow();
        return DLNA.JNI_UploadFile(mDLNA.mNativeUPnP, s, "DLNA.ORG_AnyContainer", s1, s2, s3, ai) == 0;
    }

    public boolean UploadFileWithM2TS(String s, String s1, String s2, int ai[])
    {
        checkDLNAorThrow();
        return DLNA.JNI_UploadM2TSFromMp4(mDLNA.mNativeUPnP, s, "DLNA.ORG_AnyContainer", s1, s2, ai) == 0;
    }

    public boolean UploaderCancel(int i)
    {
        checkDLNAorThrow();
        return DLNA.JNI_UploaderCancel(mDLNA.mNativeUPnP, i) == 0;
    }

    UPCPCallback getProxyCallback()
    {
        return mUpcpCallback;
    }

    public boolean isSupportUpload(String s)
    {
        checkDLNAorThrow();
        if (s == null)
        {
            return false;
        } else
        {
            return DLNA.JNI_IsSupportUploader(mDLNA.mNativeUPnP, s);
        }
    }

    public boolean matchDLNAUploadProfile(String s, Uri uri, String s1, Integer integer)
    {
        checkDLNAorThrow();
        if (mLocalMatchDLNAUpload != null)
        {
            return mLocalMatchDLNAUpload.matchDLNAUploadProfile(s, uri, s1, integer);
        } else
        {
            return false;
        }
    }

    public void registerContentUpdatedListener(IUploadResultListener iuploadresultlistener)
    {
        synchronized (mUploadResultListener)
        {
            if (!mUploadResultListener.contains(iuploadresultlistener))
            {
                mUploadResultListener.add(iuploadresultlistener);
            }
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void release()
    {
        mDLNA.unregisterDLNAStatusListener(mDLNAStatusListener);
        mLocalMatchDLNAUpload.recycle();
    }

    public void unregisterContentUpdatedListener(IUploadResultListener iuploadresultlistener)
    {
        synchronized (mUploadResultListener)
        {
            if (mUploadResultListener.contains(iuploadresultlistener))
            {
                mUploadResultListener.remove(iuploadresultlistener);
            }
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

}
