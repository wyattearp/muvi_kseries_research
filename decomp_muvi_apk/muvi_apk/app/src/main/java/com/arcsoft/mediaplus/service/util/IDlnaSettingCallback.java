// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.service.util;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDlnaSettingCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDlnaSettingCallback
    {

        private static final String DESCRIPTOR = "com.arcsoft.mediaplus.service.util.IDlnaSettingCallback";
        static final int TRANSACTION_onDmrOffline = 4;
        static final int TRANSACTION_onDmrOnline = 2;
        static final int TRANSACTION_onDmsOffline = 3;
        static final int TRANSACTION_onDmsOnline = 1;

        public static IDlnaSettingCallback asInterface(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingCallback");
            if (iinterface != null && (iinterface instanceof IDlnaSettingCallback))
            {
                return (IDlnaSettingCallback)iinterface;
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
                parcel1.writeString("com.arcsoft.mediaplus.service.util.IDlnaSettingCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingCallback");
                onDmsOnline(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingCallback");
                onDmrOnline(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingCallback");
                onDmsOffline(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.arcsoft.mediaplus.service.util.IDlnaSettingCallback");
                onDmrOffline(parcel.readString());
                parcel1.writeNoException();
                return true;
            }
        }

        public Stub()
        {
            attachInterface(this, "com.arcsoft.mediaplus.service.util.IDlnaSettingCallback");
        }
    }

    private static class Stub.Proxy
        implements IDlnaSettingCallback
    {

        private IBinder mRemote;

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.arcsoft.mediaplus.service.util.IDlnaSettingCallback";
        }

        public void onDmrOffline(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingCallback");
            parcel.writeString(s);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onDmrOnline(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingCallback");
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onDmsOffline(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingCallback");
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onDmsOnline(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.arcsoft.mediaplus.service.util.IDlnaSettingCallback");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onDmrOffline(String s)
        throws RemoteException;

    public abstract void onDmrOnline(String s)
        throws RemoteException;

    public abstract void onDmsOffline(String s)
        throws RemoteException;

    public abstract void onDmsOnline(String s)
        throws RemoteException;
}
