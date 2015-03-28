// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.service.util;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class RemoteVideoInfo
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RemoteVideoInfo createFromParcel(Parcel parcel)
        {
            return new RemoteVideoInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RemoteVideoInfo[] newArray(int i)
        {
            return new RemoteVideoInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    };
    public String mContentFormat;
    public String mDuration;
    public long mSeekTime;
    public Uri mUri;
    public boolean mbSupportSeekByTime;

    public RemoteVideoInfo()
    {
        mUri = null;
        mDuration = null;
        mContentFormat = null;
        mbSupportSeekByTime = true;
        mSeekTime = 0L;
    }

    private RemoteVideoInfo(Parcel parcel)
    {
        mUri = null;
        mDuration = null;
        mContentFormat = null;
        mbSupportSeekByTime = true;
        mSeekTime = 0L;
        readFromParcel(parcel);
    }


    public int describeContents()
    {
        return 0;
    }

    public void readFromParcel(Parcel parcel)
    {
        mUri = (Uri)parcel.readValue(null);
        mContentFormat = parcel.readString();
        mDuration = parcel.readString();
        boolean flag;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mbSupportSeekByTime = flag;
        mSeekTime = parcel.readLong();
    }

    public String toString()
    {
        return (new StringBuilder()).append("[RemoteVideoInfo]\n mUri : ").append(mUri).append("\n mContentFormat : ").append(mContentFormat).append("\n mDuration : ").append(mDuration).append("\n mbSupportSeekByTime : ").append(mbSupportSeekByTime).append("\n mSeekTime : ").append(mSeekTime).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeValue(mUri);
        parcel.writeString(mContentFormat);
        parcel.writeString(mDuration);
        int j;
        if (mbSupportSeekByTime)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        parcel.writeLong(mSeekTime);
    }

}
