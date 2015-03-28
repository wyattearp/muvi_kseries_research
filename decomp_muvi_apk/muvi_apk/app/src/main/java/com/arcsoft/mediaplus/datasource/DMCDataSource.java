// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.app.Application;
import android.net.Uri;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.RenderManager;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.util.debug.DebugUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.SafeBuffer;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            CompoundDataSource, Property, IDataSource

public class DMCDataSource extends CompoundDataSource
{
    private class ArcDMSFunction
        implements LocalDMSFunction
    {

        final DMCDataSource this$0;

        public ResourceInfo getResourceInfosFromLocalDMS(GetURLRequest geturlrequest)
        {
            String s = ((Uri)getObjectProp(geturlrequest.index, Property.PROP_URI, null)).getPath();
            ResourceInfo resourceinfo = new ResourceInfo();
            resourceinfo.resource = new com.arcsoft.adk.atv.UPnP.PresentItem_Resource();
            resourceinfo.resource.m_strProtocolInfo = DLNA.instance().getFileProtocolInfo(s);
            resourceinfo.resource.m_strUri = DLNA.instance().getUri(s);
            resourceinfo.metadata = DLNA.instance().getLocalMediaDidlData(s);
            return resourceinfo;
        }

        public void recycle()
        {
        }

        private ArcDMSFunction()
        {
            this$0 = DMCDataSource.this;
            super();
        }

    }

    public static class GetURLRequest
    {

        public int index;
        public IOnGetPlayURLListener listener;
        boolean mCancelFlag;
        public com.arcsoft.adk.atv.UPnP.MediaRenderDesc renderDesc;
        public Object userdata;

        public GetURLRequest()
        {
            mCancelFlag = false;
        }
    }

    private class GetURLThread extends Thread
    {

        private static final int STATUS_DOING = 1;
        private static final int STATUS_NONE = 0;
        private static final int STATUS_QUIT = 2;
        GetURLRequest mRequest;
        private final AtomicInteger mStatus;
        final DMCDataSource this$0;

        private void requestCancel()
        {
            this;
            JVM INSTR monitorenter ;
            if (mRequest != null)
            {
                mRequest.mCancelFlag = true;
            }
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private void requestQuit()
        {
            requestCancel();
            mStatus.set(2);
            wake(mGetURLRequestQueue);
            wake(this);
        }

        public void run()
        {
_L2:
            if (mStatus.get() == 2)
            {
                break; /* Loop/switch isn't completed */
            }
            this;
            JVM INSTR monitorenter ;
            if (mStatus.get() == 1)
            {
                break MISSING_BLOCK_LABEL_42;
            }
            waitForTask(this);
            this;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            this;
            JVM INSTR monitorexit ;
            SafeBuffer safebuffer = mGetURLRequestQueue;
            safebuffer;
            JVM INSTR monitorenter ;
            GetURLRequest geturlrequest = (GetURLRequest)mGetURLRequestQueue.get();
            if (geturlrequest != null)
            {
                break MISSING_BLOCK_LABEL_98;
            }
            waitForTask(mGetURLRequestQueue);
            safebuffer;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
            Exception exception1;
            exception1;
            safebuffer;
            JVM INSTR monitorexit ;
            throw exception1;
            safebuffer;
            JVM INSTR monitorexit ;
            this;
            JVM INSTR monitorenter ;
            mRequest = geturlrequest;
            this;
            JVM INSTR monitorexit ;
            ResourceInfo resourceinfo;
            long l = System.currentTimeMillis();
            resourceinfo = getResourceInfos(geturlrequest);
            long l1 = System.currentTimeMillis();
            if (l1 - l > 50L)
            {
                Log.w("DMSDataSource", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("getResourceInfos cost: ").append(l1 - l).toString());
            }
            this;
            JVM INSTR monitorenter ;
            mRequest = null;
            this;
            JVM INSTR monitorexit ;
            Exception exception2;
            Exception exception3;
            if (resourceinfo != null)
            {
                if (geturlrequest.listener != null)
                {
                    geturlrequest.listener.onGetPlayURL(resourceinfo.resource.m_strUri, resourceinfo.metadata, geturlrequest.userdata);
                }
            } else
            if (geturlrequest.listener != null)
            {
                geturlrequest.listener.onGetPlayURLError(geturlrequest.userdata);
            }
            continue; /* Loop/switch isn't completed */
            exception2;
            this;
            JVM INSTR monitorexit ;
            throw exception2;
            exception3;
            this;
            JVM INSTR monitorexit ;
            throw exception3;
            if (true) goto _L2; else goto _L1
_L1:
        }



        private GetURLThread()
        {
            this$0 = DMCDataSource.this;
            super();
            mRequest = null;
            mStatus = new AtomicInteger(0);
            mStatus.set(1);
        }

    }

    public static interface IOnGetPlayURLListener
    {

        public abstract void onGetPlayURL(String s, String s1, Object obj);

        public abstract void onGetPlayURLError(Object obj);
    }

    private static interface LocalDMSFunction
    {

        public abstract ResourceInfo getResourceInfosFromLocalDMS(GetURLRequest geturlrequest);

        public abstract void recycle();
    }

    private class ResourceInfo
    {

        public String metadata;
        com.arcsoft.adk.atv.UPnP.PresentItem_Resource resource;
        final DMCDataSource this$0;

        private ResourceInfo()
        {
            this$0 = DMCDataSource.this;
            super();
        }

    }


    public static final Property PROP_METADATA = new Property("PROP_METADATA");
    public static final Property PROP_RESOURCES = new Property("PROP_RESOURCES");
    private static final String TAG = "DMSDataSource";
    private final Application mApplication;
    private final SafeBuffer mGetURLRequestQueue = new SafeBuffer();
    private GetURLThread mGetURLThread;
    private final IDataSource.OnDataChangeListener mInternalDataChangeListener = new IDataSource.OnDataChangeListener() {

        final DMCDataSource this$0;

        public void onCountChanged(int i, int j)
        {
            notifyOnCountChanged(i, j);
        }

        public void onDataChanged(int i, Property property)
        {
            notifyOnDataChanged(i, property);
        }

            
            {
                this$0 = DMCDataSource.this;
                super();
            }
    };
    private LocalDMSFunction mLocalDMSFunction;

    public DMCDataSource(IDataSource idatasource, Application application)
    {
        super(idatasource);
        mLocalDMSFunction = null;
        mGetURLThread = null;
        mApplication = application;
        if (application == null)
        {
            throw new IllegalArgumentException("Input application is null.");
        } else
        {
            static class _cls2
            {

                static final int $SwitchMap$com$arcsoft$mediaplus$oem$OEMConfig$OEMName[] = new int[com.arcsoft.mediaplus.oem.OEMConfig.OEMName.values().length];

            }

            int _tmp = _cls2..SwitchMap.com.arcsoft.mediaplus.oem.OEMConfig.OEMName[OEMConfig.PROJECT_NAME.ordinal()];
            mLocalDMSFunction = new ArcDMSFunction();
            return;
        }
    }

    private int getMatchResource(List list, GetURLRequest geturlrequest)
    {
        if (list != null) goto _L2; else goto _L1
_L1:
        byte byte1 = -1;
_L4:
        return byte1;
_L2:
        int ai[];
        int i;
        String s = getStringProp(geturlrequest.index, Property.PROP_MIMETYPE, "").toLowerCase();
        byte byte0 = -1;
        if (s.startsWith("audio/"))
        {
            byte0 = 2;
        } else
        if (s.startsWith("video/"))
        {
            byte0 = 1;
        } else
        if (s.startsWith("image/"))
        {
            byte0 = 3;
        }
        ai = DLNA.getSortedResources(byte0, list);
        byte1 = -1;
        i = 0;
_L6:
        if (i < ai.length)
        {
            if (geturlrequest.mCancelFlag)
            {
                return -1;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (geturlrequest.mCancelFlag) goto _L4; else goto _L5
_L5:
        com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)list.get(ai[i]);
        if (isMatchAquosTV(presentitem_resource.m_strResolution, geturlrequest.renderDesc) && isResourceMatch(presentitem_resource.m_strProtocolInfo, geturlrequest.renderDesc))
        {
            return ai[i];
        }
        i++;
          goto _L6
    }

    private ResourceInfo getResourceInfos(GetURLRequest geturlrequest)
    {
        Uri uri = (Uri)getObjectProp(geturlrequest.index, Property.PROP_URI, null);
        if (uri != null)
        {
            if (isLocalData(uri))
            {
                if (mLocalDMSFunction != null)
                {
                    return mLocalDMSFunction.getResourceInfosFromLocalDMS(geturlrequest);
                }
            } else
            {
                return getResourceInfosFromRemoteDBMgr(geturlrequest);
            }
        }
        return null;
    }

    private ResourceInfo getResourceInfosFromRemoteDBMgr(GetURLRequest geturlrequest)
    {
        if (getLongProp(geturlrequest.index, Property.PROP_ID, 0L) == 0L)
        {
            return null;
        }
        com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc = getRemoteItemDesc(geturlrequest.index);
        if (remoteitemdesc == null)
        {
            return null;
        }
        int i = getMatchResource(remoteitemdesc.m_PresentItem.m_ResourceList, geturlrequest);
        if (i < 0)
        {
            return null;
        } else
        {
            com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)remoteitemdesc.m_PresentItem.m_ResourceList.get(i);
            remoteitemdesc.m_PresentItem.m_ResourceList.clear();
            remoteitemdesc.m_PresentItem.m_ResourceList.add(presentitem_resource);
            ResourceInfo resourceinfo = new ResourceInfo();
            resourceinfo.resource = presentitem_resource;
            resourceinfo.metadata = DLNA.instance().getRemoteMediaDidlData(remoteitemdesc);
            return resourceinfo;
        }
    }

    private static boolean isLocalData(Uri uri)
    {
        return uri.getScheme() != null && uri.getScheme().equalsIgnoreCase("file");
    }

    private boolean isMatchAquosTV(String s, com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc)
    {
_L2:
        return true;
        if (mediarenderdesc == null || s == null || s.equals("") || !DLNA.instance().getRenderManager().isSharpDMR(mediarenderdesc)) goto _L2; else goto _L1
_L1:
        int i = s.indexOf("x");
        if (i == -1) goto _L2; else goto _L3
_L3:
        int j1 = Integer.parseInt(s.substring(0, i));
        int j = j1;
_L4:
        int k = i + 1;
        int i1 = Integer.parseInt(s.substring(k));
        int l = i1;
_L5:
        if (j > 4096 || l > 4096)
        {
            return false;
        }
          goto _L2
        Exception exception;
        exception;
        j = 0;
          goto _L4
        Exception exception1;
        exception1;
        l = 0;
          goto _L5
    }

    private boolean isResourceMatch(String s, com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc)
    {
        com.arcsoft.adk.atv.MRCPCallback.DataOnGetProtocolInfo dataongetprotocolinfo = DLNA.instance().getRenderManager().getRenderProtocolInfo(mediarenderdesc.m_strUuid);
        if (dataongetprotocolinfo != null && dataongetprotocolinfo.m_SinkValues != null) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        String as[] = dataongetprotocolinfo.m_SinkValues;
        int i = as.length;
        int j = 0;
label0:
        do
        {
label1:
            {
                if (j >= i)
                {
                    break label1;
                }
                if (DLNA.isProtocolMatched(s, as[j]))
                {
                    break label0;
                }
                j++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return false;
    }

    private void waitForTask(Object obj)
    {
        obj;
        JVM INSTR monitorenter ;
        obj.wait();
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        interruptedexception.printStackTrace();
          goto _L1
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void wake(Object obj)
    {
        obj;
        JVM INSTR monitorenter ;
        obj.notify();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void getPlayURLAsync(GetURLRequest geturlrequest, IOnGetPlayURLListener iongetplayurllistener)
    {
        mGetURLRequestQueue.clear();
        mGetURLThread.requestCancel();
        mGetURLRequestQueue.append(geturlrequest);
        wake(mGetURLRequestQueue);
    }

    protected void onDisable()
    {
        mDataSource.unregisterOnDataChangeListener(mInternalDataChangeListener);
        super.onDisable();
    }

    protected void onEnable()
    {
        super.onEnable();
        mDataSource.registerOnDataChangeListener(mInternalDataChangeListener);
    }

    protected void onInit()
    {
        super.onInit();
        mGetURLThread = new GetURLThread();
        mGetURLThread.start();
    }

    protected void onUninit()
    {
        if (mGetURLThread != null)
        {
            mGetURLThread.requestCancel();
            mGetURLThread.requestQuit();
            mGetURLThread = null;
        }
        if (mLocalDMSFunction != null)
        {
            mLocalDMSFunction.recycle();
        }
        super.onUninit();
    }





}
