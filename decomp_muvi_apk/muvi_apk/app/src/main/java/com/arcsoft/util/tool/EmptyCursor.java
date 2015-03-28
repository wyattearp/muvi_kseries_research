// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

public class EmptyCursor
    implements Cursor
{

    public static final EmptyCursor EMPTY_CURSOR = new EmptyCursor();

    public EmptyCursor()
    {
    }

    public void close()
    {
    }

    public void copyStringToBuffer(int i, CharArrayBuffer chararraybuffer)
    {
    }

    public void deactivate()
    {
    }

    public byte[] getBlob(int i)
    {
        return null;
    }

    public int getColumnCount()
    {
        return 0;
    }

    public int getColumnIndex(String s)
    {
        return 0;
    }

    public int getColumnIndexOrThrow(String s)
        throws IllegalArgumentException
    {
        return 0;
    }

    public String getColumnName(int i)
    {
        return null;
    }

    public String[] getColumnNames()
    {
        return null;
    }

    public int getCount()
    {
        return 0;
    }

    public double getDouble(int i)
    {
        return 0.0D;
    }

    public Bundle getExtras()
    {
        return null;
    }

    public float getFloat(int i)
    {
        return 0.0F;
    }

    public int getInt(int i)
    {
        return 0;
    }

    public long getLong(int i)
    {
        return 0L;
    }

    public int getPosition()
    {
        return 0;
    }

    public short getShort(int i)
    {
        return 0;
    }

    public String getString(int i)
    {
        return null;
    }

    public boolean getWantsAllOnMoveCalls()
    {
        return false;
    }

    public boolean isAfterLast()
    {
        return false;
    }

    public boolean isBeforeFirst()
    {
        return false;
    }

    public boolean isClosed()
    {
        return false;
    }

    public boolean isFirst()
    {
        return false;
    }

    public boolean isLast()
    {
        return false;
    }

    public boolean isNull(int i)
    {
        return false;
    }

    public boolean move(int i)
    {
        return false;
    }

    public boolean moveToFirst()
    {
        return false;
    }

    public boolean moveToLast()
    {
        return false;
    }

    public boolean moveToNext()
    {
        return false;
    }

    public boolean moveToPosition(int i)
    {
        return false;
    }

    public boolean moveToPrevious()
    {
        return false;
    }

    public void registerContentObserver(ContentObserver contentobserver)
    {
    }

    public void registerDataSetObserver(DataSetObserver datasetobserver)
    {
    }

    public boolean requery()
    {
        return false;
    }

    public Bundle respond(Bundle bundle)
    {
        return null;
    }

    public void setNotificationUri(ContentResolver contentresolver, Uri uri)
    {
    }

    public void unregisterContentObserver(ContentObserver contentobserver)
    {
    }

    public void unregisterDataSetObserver(DataSetObserver datasetobserver)
    {
    }

}
