// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.service.util;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.service.util:
//            IDlnaSettingBinder, DeviceIcon, IDlnaSettingCallback

public static abstract class attachInterface extends Binder
    implements IDlnaSettingBinder
{
    private static class Proxy
        implements IDlnaSettingBinder
    {

        private IBinder mRemote;

        public boolean abortUploadAndDownloadTask(boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            int i;
            int j;
            if (flag)
            {
                i = ((flag1) ? 1 : 0);
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            j = parcel1.readInt();
            if (j == 0)
            {
                flag1 = false;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getActiveDmrUDN()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getActiveDmsUDN()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getDmrFriendlyName(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s1 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getDmrManufacture(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            s1 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getDmrModelName(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            s1 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getDmrUDNicon(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(DeviceIcon.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getDmsFriendlyName(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            s1 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getDmsManufacture(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            s1 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getDmsModelName(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            s1 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getDmsUDNicon(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(DeviceIcon.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.arcsoft.mediaplus.service.util.IDlnaSettingBinder";
        }

        public boolean getOnlineContentsListviewMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String[] getOnlineDmrUDN()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String[] getOnlineDmsUDN()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getTelevisionStreamingResolution()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getUploadAndDownloadTaskCount()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isDmrOnline(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isDmsOnline(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isResAdapttoActiveDMR(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isResAdapttoDMR(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void registerCallback(IDlnaSettingCallback idlnasettingcallback)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            if (idlnasettingcallback == null)
            {
                break MISSING_BLOCK_LABEL_59;
            }
            IBinder ibinder = idlnasettingcallback.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean resumeUploadAndDownloadTask()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean setActiveDmr(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean setActiveDms(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            parcel.writeString(s);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean setOnlineContentsListviewMode(boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            int i;
            int j;
            if (flag)
            {
                i = ((flag1) ? 1 : 0);
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            j = parcel1.readInt();
            if (j == 0)
            {
                flag1 = false;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean setTelevisionStreamingResolution(boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            int i;
            int j;
            if (flag)
            {
                i = ((flag1) ? 1 : 0);
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            j = parcel1.readInt();
            if (j == 0)
            {
                flag1 = false;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void unregisterCallback(IDlnaSettingCallback idlnasettingcallback)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            if (idlnasettingcallback == null)
            {
                break MISSING_BLOCK_LABEL_59;
            }
            IBinder ibinder = idlnasettingcallback.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    private static final String DESCRIPTOR = "com.arcsoft.mediaplus.service.util.IDlnaSettingBinder";
    static final int TRANSACTION_abortUploadAndDownloadTask = 22;
    static final int TRANSACTION_getActiveDmrUDN = 10;
    static final int TRANSACTION_getActiveDmsUDN = 8;
    static final int TRANSACTION_getDmrFriendlyName = 6;
    static final int TRANSACTION_getDmrManufacture = 17;
    static final int TRANSACTION_getDmrModelName = 13;
    static final int TRANSACTION_getDmrUDNicon = 16;
    static final int TRANSACTION_getDmsFriendlyName = 5;
    static final int TRANSACTION_getDmsManufacture = 18;
    static final int TRANSACTION_getDmsModelName = 14;
    static final int TRANSACTION_getDmsUDNicon = 15;
    static final int TRANSACTION_getOnlineContentsListviewMode = 26;
    static final int TRANSACTION_getOnlineDmrUDN = 4;
    static final int TRANSACTION_getOnlineDmsUDN = 3;
    static final int TRANSACTION_getTelevisionStreamingResolution = 24;
    static final int TRANSACTION_getUploadAndDownloadTaskCount = 21;
    static final int TRANSACTION_isDmrOnline = 12;
    static final int TRANSACTION_isDmsOnline = 11;
    static final int TRANSACTION_isResAdapttoActiveDMR = 20;
    static final int TRANSACTION_isResAdapttoDMR = 19;
    static final int TRANSACTION_registerCallback = 1;
    static final int TRANSACTION_resumeUploadAndDownloadTask = 23;
    static final int TRANSACTION_setActiveDmr = 9;
    static final int TRANSACTION_setActiveDms = 7;
    static final int TRANSACTION_setOnlineContentsListviewMode = 27;
    static final int TRANSACTION_setTelevisionStreamingResolution = 25;
    static final int TRANSACTION_unregisterCallback = 2;

    public static IDlnaSettingBinder asInterface(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        android.os.IInterface iinterface = ibinder.queryLocalInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
        if (iinterface != null && (iinterface instanceof IDlnaSettingBinder))
        {
            return (IDlnaSettingBinder)iinterface;
        } else
        {
            return new Proxy(ibinder);
        }
    }

    public IBinder asBinder()
    {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        switch (i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1598968902: 
            parcel1.writeString("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            return true;

        case 1: // '\001'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            registerCallback(b.asInterface(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 2: // '\002'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            unregisterCallback(b.asInterface(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 3: // '\003'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            String as1[] = getOnlineDmsUDN();
            parcel1.writeNoException();
            parcel1.writeStringArray(as1);
            return true;

        case 4: // '\004'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            String as[] = getOnlineDmrUDN();
            parcel1.writeNoException();
            parcel1.writeStringArray(as);
            return true;

        case 5: // '\005'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            String s7 = getDmsFriendlyName(parcel.readString());
            parcel1.writeNoException();
            parcel1.writeString(s7);
            return true;

        case 6: // '\006'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            String s6 = getDmrFriendlyName(parcel.readString());
            parcel1.writeNoException();
            parcel1.writeString(s6);
            return true;

        case 7: // '\007'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            boolean flag14 = setActiveDms(parcel.readString());
            parcel1.writeNoException();
            int k3 = 0;
            if (flag14)
            {
                k3 = 1;
            }
            parcel1.writeInt(k3);
            return true;

        case 8: // '\b'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            String s5 = getActiveDmsUDN();
            parcel1.writeNoException();
            parcel1.writeString(s5);
            return true;

        case 9: // '\t'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            boolean flag13 = setActiveDmr(parcel.readString());
            parcel1.writeNoException();
            int j3 = 0;
            if (flag13)
            {
                j3 = 1;
            }
            parcel1.writeInt(j3);
            return true;

        case 10: // '\n'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            String s4 = getActiveDmrUDN();
            parcel1.writeNoException();
            parcel1.writeString(s4);
            return true;

        case 11: // '\013'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            boolean flag12 = isDmsOnline(parcel.readString());
            parcel1.writeNoException();
            int i3 = 0;
            if (flag12)
            {
                i3 = 1;
            }
            parcel1.writeInt(i3);
            return true;

        case 12: // '\f'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            boolean flag11 = isDmrOnline(parcel.readString());
            parcel1.writeNoException();
            int l2 = 0;
            if (flag11)
            {
                l2 = 1;
            }
            parcel1.writeInt(l2);
            return true;

        case 13: // '\r'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            String s3 = getDmrModelName(parcel.readString());
            parcel1.writeNoException();
            parcel1.writeString(s3);
            return true;

        case 14: // '\016'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            String s2 = getDmsModelName(parcel.readString());
            parcel1.writeNoException();
            parcel1.writeString(s2);
            return true;

        case 15: // '\017'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            List list1 = getDmsUDNicon(parcel.readString());
            parcel1.writeNoException();
            parcel1.writeTypedList(list1);
            return true;

        case 16: // '\020'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            List list = getDmrUDNicon(parcel.readString());
            parcel1.writeNoException();
            parcel1.writeTypedList(list);
            return true;

        case 17: // '\021'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            String s1 = getDmrManufacture(parcel.readString());
            parcel1.writeNoException();
            parcel1.writeString(s1);
            return true;

        case 18: // '\022'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            String s = getDmsManufacture(parcel.readString());
            parcel1.writeNoException();
            parcel1.writeString(s);
            return true;

        case 19: // '\023'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            boolean flag10 = isResAdapttoDMR(parcel.readString(), parcel.readString());
            parcel1.writeNoException();
            int k2 = 0;
            if (flag10)
            {
                k2 = 1;
            }
            parcel1.writeInt(k2);
            return true;

        case 20: // '\024'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            boolean flag9 = isResAdapttoActiveDMR(parcel.readString());
            parcel1.writeNoException();
            int j2 = 0;
            if (flag9)
            {
                j2 = 1;
            }
            parcel1.writeInt(j2);
            return true;

        case 21: // '\025'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            int i2 = getUploadAndDownloadTaskCount();
            parcel1.writeNoException();
            parcel1.writeInt(i2);
            return true;

        case 22: // '\026'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            boolean flag7;
            boolean flag8;
            int l1;
            if (parcel.readInt() != 0)
            {
                flag7 = true;
            } else
            {
                flag7 = false;
            }
            flag8 = abortUploadAndDownloadTask(flag7);
            parcel1.writeNoException();
            l1 = 0;
            if (flag8)
            {
                l1 = 1;
            }
            parcel1.writeInt(l1);
            return true;

        case 23: // '\027'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            boolean flag6 = resumeUploadAndDownloadTask();
            parcel1.writeNoException();
            int k1 = 0;
            if (flag6)
            {
                k1 = 1;
            }
            parcel1.writeInt(k1);
            return true;

        case 24: // '\030'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            boolean flag5 = getTelevisionStreamingResolution();
            parcel1.writeNoException();
            int j1 = 0;
            if (flag5)
            {
                j1 = 1;
            }
            parcel1.writeInt(j1);
            return true;

        case 25: // '\031'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            boolean flag3;
            boolean flag4;
            int i1;
            if (parcel.readInt() != 0)
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
            flag4 = setTelevisionStreamingResolution(flag3);
            parcel1.writeNoException();
            i1 = 0;
            if (flag4)
            {
                i1 = 1;
            }
            parcel1.writeInt(i1);
            return true;

        case 26: // '\032'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            boolean flag2 = getOnlineContentsListviewMode();
            parcel1.writeNoException();
            int l = 0;
            if (flag2)
            {
                l = 1;
            }
            parcel1.writeInt(l);
            return true;

        case 27: // '\033'
            parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
            break;
        }
        boolean flag;
        boolean flag1;
        int k;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        flag1 = setOnlineContentsListviewMode(flag);
        parcel1.writeNoException();
        k = 0;
        if (flag1)
        {
            k = 1;
        }
        parcel1.writeInt(k);
        return true;
    }

    public Proxy.mRemote()
    {
        attachInterface(this, "com.arcsoft.mediaplus.service.util.IDlnaSettingBinder");
    }
}
