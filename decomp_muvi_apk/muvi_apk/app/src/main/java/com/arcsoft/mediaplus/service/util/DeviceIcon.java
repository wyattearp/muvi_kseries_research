// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.service.util;

import android.os.Parcel;
import android.os.Parcelable;

public final class DeviceIcon
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DeviceIcon createFromParcel(Parcel parcel)
        {
            return new DeviceIcon(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DeviceIcon[] newArray(int i)
        {
            return new DeviceIcon[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    };
    public long m_lDepth;
    public long m_lHeight;
    public long m_lWidth;
    public String m_strMimeType;
    public String m_strUrl;

    public DeviceIcon()
    {
        m_strMimeType = null;
        m_lWidth = 0L;
        m_lHeight = 0L;
        m_lDepth = 0L;
        m_strUrl = null;
    }

    private DeviceIcon(Parcel parcel)
    {
        m_strMimeType = null;
        m_lWidth = 0L;
        m_lHeight = 0L;
        m_lDepth = 0L;
        m_strUrl = null;
        readFromParcel(parcel);
    }


    public int describeContents()
    {
        return 0;
    }

    public void readFromParcel(Parcel parcel)
    {
        m_strMimeType = parcel.readString();
        m_lWidth = parcel.readLong();
        m_lHeight = parcel.readLong();
        m_lDepth = parcel.readLong();
        m_strUrl = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(m_strMimeType);
        parcel.writeLong(m_lWidth);
        parcel.writeLong(m_lHeight);
        parcel.writeLong(m_lDepth);
        parcel.writeString(m_strUrl);
    }

}
