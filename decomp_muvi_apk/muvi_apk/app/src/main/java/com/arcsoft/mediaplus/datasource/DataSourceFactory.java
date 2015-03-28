// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;
import com.arcsoft.mediaplus.datasource.db.ChannelDataMgr;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.util.debug.DebugUtils;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            IDataSource, ImageLocalDataSource, AudioLocalDataSource, VideoLocalDataSource, 
//            VideoAndImageLocalDataSource, ImageRemoteDataSource, AudioRemoteDataSource, VideoRemoteDataSource, 
//            FolderRemoteDataSource, ChannelDataSource, VideoRootFolderRemoteDS, VideoAndImageRemoteDataSource, 
//            SalixRemoteDataSource

public class DataSourceFactory
{
    public static final class DataSourceFilter
        implements Parcelable
    {

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public DataSourceFilter createFromParcel(Parcel parcel)
            {
                return new DataSourceFilter(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public DataSourceFilter[] newArray(int i)
            {
                return new DataSourceFilter[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        };
        public static final int PROTOCAL_DLNA = 0;
        public static final int PROTOCAL_SALIX_HTTP = 1;
        public static final int TYPE_AUDIO = 1;
        public static final int TYPE_BROADCAST = 16;
        public static final int TYPE_FOLDER = 8;
        public static final int TYPE_IMAGE = 2;
        public static final int TYPE_IMAGE_VIDEO = 64;
        public static final int TYPE_VIDEO = 4;
        public static final int TYPE_VIDEOROOTFOLDER = 32;
        public int channelUID;
        private IProduct dataSource;
        public boolean isLocal;
        public int mediatype;
        public int protocal;
        private int refcount;
        public String serverUDN;

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = true;
            DataSourceFilter datasourcefilter;
            datasourcefilter = (DataSourceFilter)obj;
            if (datasourcefilter.mediatype != mediatype)
            {
                break MISSING_BLOCK_LABEL_97;
            }
            if (datasourcefilter.isLocal != isLocal)
            {
                return false;
            }
            int i;
            int j;
            if (isLocal)
            {
                break MISSING_BLOCK_LABEL_94;
            }
            if (!datasourcefilter.serverUDN.equalsIgnoreCase(serverUDN))
            {
                break MISSING_BLOCK_LABEL_97;
            }
            if (mediatype != 16)
            {
                break MISSING_BLOCK_LABEL_92;
            }
            i = channelUID;
            j = datasourcefilter.channelUID;
            if (i != j)
            {
                flag = false;
            }
            return flag;
            return flag;
            return flag;
            Exception exception;
            exception;
            return false;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(serverUDN);
            int j;
            if (isLocal)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            parcel.writeInt(j);
            parcel.writeInt(mediatype);
            parcel.writeInt(channelUID);
            parcel.writeInt(protocal);
        }




/*
        static int access$108(DataSourceFilter datasourcefilter)
        {
            int i = datasourcefilter.refcount;
            datasourcefilter.refcount = i + 1;
            return i;
        }

*/


/*
        static int access$110(DataSourceFilter datasourcefilter)
        {
            int i = datasourcefilter.refcount;
            datasourcefilter.refcount = i - 1;
            return i;
        }

*/



/*
        static IProduct access$202(DataSourceFilter datasourcefilter, IProduct iproduct)
        {
            datasourcefilter.dataSource = iproduct;
            return iproduct;
        }

*/

        public DataSourceFilter()
        {
            channelUID = -1;
            isLocal = true;
            serverUDN = "";
            mediatype = 0;
            protocal = 0;
            dataSource = null;
            refcount = 0;
        }

        private DataSourceFilter(Parcel parcel)
        {
            channelUID = -1;
            isLocal = true;
            serverUDN = "";
            mediatype = 0;
            protocal = 0;
            dataSource = null;
            refcount = 0;
            serverUDN = parcel.readString();
            int i = parcel.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            isLocal = flag;
            mediatype = parcel.readInt();
            channelUID = parcel.readInt();
            protocal = parcel.readInt();
        }


        private DataSourceFilter(DataSourceFilter datasourcefilter)
        {
            channelUID = -1;
            isLocal = true;
            serverUDN = "";
            mediatype = 0;
            protocal = 0;
            dataSource = null;
            refcount = 0;
            isLocal = datasourcefilter.isLocal;
            serverUDN = datasourcefilter.serverUDN;
            mediatype = datasourcefilter.mediatype;
            channelUID = datasourcefilter.channelUID;
            protocal = datasourcefilter.protocal;
        }

    }

    public static interface IProduct
    {

        public abstract void create();

        public abstract void destory();
    }


    private static DataSourceFactory sInstance = null;
    private Application mApplication;
    private final ArrayList mFilterList = new ArrayList();

    private DataSourceFactory(Application application)
    {
        mApplication = null;
        mApplication = application;
    }

    private void init()
    {
    }

    public static void initSingleton(Application application)
    {
        if (sInstance != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            sInstance = new DataSourceFactory(application);
            sInstance.init();
            return;
        }
    }

    public static DataSourceFactory instance()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Uninitialized.");
        } else
        {
            return sInstance;
        }
    }

    private void uninit()
    {
        Iterator iterator = mFilterList.iterator();
        DebugUtils.logObjectDebugInfo();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DataSourceFilter datasourcefilter = (DataSourceFilter)iterator.next();
            if (datasourcefilter.dataSource != null)
            {
                datasourcefilter.dataSource.destory();
            }
        } while (true);
        mFilterList.clear();
    }

    public static void uninitSingleton()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Not initialized.");
        } else
        {
            sInstance.uninit();
            sInstance = null;
            return;
        }
    }

    public IDataSource getDataSource(DataSourceFilter datasourcefilter)
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator = mFilterList.iterator();
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        DataSourceFilter datasourcefilter2 = (DataSourceFilter)iterator.next();
        if (!datasourcefilter2.equals(datasourcefilter)) goto _L4; else goto _L3
_L3:
        IDataSource idatasource;
        int i = ((Iterator) (this)).next;
        idatasource = (IDataSource)datasourcefilter2.dataSource;
_L14:
        this;
        JVM INSTR monitorexit ;
        return idatasource;
_L2:
        DataSourceFilter datasourcefilter1 = new DataSourceFilter(datasourcefilter);
        if (!datasourcefilter1.isLocal) goto _L6; else goto _L5
_L5:
        datasourcefilter1.mediatype;
        JVM INSTR lookupswitch 4: default 128
    //                   1: 239
    //                   2: 164
    //                   4: 262
    //                   64: 285;
           goto _L7 _L8 _L9 _L10 _L11
_L7:
        throw new IllegalArgumentException((new StringBuilder()).append("Can not create local media datasource with type : ").append(datasourcefilter1.mediatype).toString());
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
_L9:
        datasourcefilter1.dataSource = new ImageLocalDataSource(mApplication.getContentResolver());
_L15:
        if (datasourcefilter1.dataSource == null) goto _L13; else goto _L12
_L12:
        datasourcefilter1.dataSource.create();
        mFilterList.add(datasourcefilter1);
        int i = ((Iterator) (this)).next;
        DebugUtils.addObjectAllocDebug(datasourcefilter1.dataSource);
        idatasource = (IDataSource)datasourcefilter1.dataSource;
          goto _L14
_L8:
        datasourcefilter1.dataSource = new AudioLocalDataSource(mApplication.getContentResolver());
          goto _L15
_L10:
        datasourcefilter1.dataSource = new VideoLocalDataSource(mApplication.getContentResolver());
          goto _L15
_L11:
        datasourcefilter1.dataSource = new VideoAndImageLocalDataSource(mApplication.getContentResolver());
          goto _L15
_L6:
        if (datasourcefilter1.serverUDN == null)
        {
            throw new NullPointerException("Can not create data with null serverudn");
        }
        datasourcefilter1.mediatype;
        JVM INSTR lookupswitch 7: default 396
    //                   1: 450
    //                   2: 427
    //                   4: 473
    //                   8: 496
    //                   16: 519
    //                   32: 547
    //                   64: 567;
           goto _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23
_L16:
        throw new IllegalArgumentException((new StringBuilder()).append("Can not create local media datasource with type : ").append(datasourcefilter1.mediatype).toString());
_L18:
        datasourcefilter1.dataSource = new ImageRemoteDataSource(mApplication, RemoteDBMgr.instance());
          goto _L15
_L17:
        datasourcefilter1.dataSource = new AudioRemoteDataSource(mApplication, RemoteDBMgr.instance());
          goto _L15
_L19:
        datasourcefilter1.dataSource = new VideoRemoteDataSource(mApplication, RemoteDBMgr.instance());
          goto _L15
_L20:
        datasourcefilter1.dataSource = new FolderRemoteDataSource(mApplication, RemoteDBMgr.instance());
          goto _L15
_L21:
        datasourcefilter1.dataSource = new ChannelDataSource(mApplication, ChannelDataMgr.instance(), datasourcefilter1.channelUID);
          goto _L15
_L22:
        datasourcefilter1.dataSource = new VideoRootFolderRemoteDS(mApplication, RemoteDBMgr.instance());
_L23:
        if (datasourcefilter1.protocal != 0) goto _L25; else goto _L24
_L24:
        datasourcefilter1.dataSource = new VideoAndImageRemoteDataSource(mApplication, RemoteDBMgr.instance());
          goto _L15
_L25:
        if (1 != datasourcefilter1.protocal) goto _L15; else goto _L26
_L26:
        datasourcefilter1.dataSource = new SalixRemoteDataSource();
          goto _L15
_L13:
        throw new IllegalArgumentException("Invalid datasoure filter");
          goto _L14
    }

    public void lockDataSource(IDataSource idatasource)
    {
        this;
        JVM INSTR monitorenter ;
        if (idatasource == null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        Iterator iterator = mFilterList.iterator();
        DataSourceFilter datasourcefilter;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_52;
            }
            datasourcefilter = (DataSourceFilter)iterator.next();
        } while (datasourcefilter.dataSource != idatasource);
        int i = ((Iterator) (this)).next;
        this;
        JVM INSTR monitorexit ;
        return;
        throw new IllegalArgumentException("Can not lock the datasource which is not created from DataSourceFactory");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public DataSourceFilter queryFilter(IDataSource idatasource)
    {
        this;
        JVM INSTR monitorenter ;
        if (idatasource == null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        Iterator iterator = mFilterList.iterator();
        DataSourceFilter datasourcefilter;
        IProduct iproduct;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_52;
            }
            datasourcefilter = (DataSourceFilter)iterator.next();
            iproduct = datasourcefilter.dataSource;
        } while (iproduct != idatasource);
        this;
        JVM INSTR monitorexit ;
        return datasourcefilter;
        throw new IllegalArgumentException("Can not lock the datasource which is not created from DataSourceFactory");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void releaseDataSource(IDataSource idatasource)
    {
        this;
        JVM INSTR monitorenter ;
        Log.e("FENG", (new StringBuilder()).append("FENG DataSourceFactory releaseDataSource() dataSource = ").append(idatasource).toString());
        Iterator iterator = mFilterList.iterator();
        DataSourceFilter datasourcefilter;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_109;
            }
            datasourcefilter = (DataSourceFilter)iterator.next();
        } while (datasourcefilter.dataSource != idatasource);
        int i = ((Iterator) (this)).next;
        if (datasourcefilter.refcount <= 0)
        {
            datasourcefilter.dataSource.destory();
            iterator.remove();
            DebugUtils.removeObjectDebug(datasourcefilter.dataSource);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        throw new IllegalArgumentException("Can not release the datasource which is not created from DataSourceFactory");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

}
