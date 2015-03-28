// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


public final class Metadata
{
    public static class MetadataInfo
    {

        public boolean bPresentItem;
        public int iCategoryType;
        public long lIndex;
        public long lSize;
        public String strCategory;
        public String strMimeType;
        public String strPath;
        public String strTitle;

        public int getType()
        {
            return 0;
        }

        public MetadataInfo()
        {
        }
    }

    public static interface MetadataSetInterface
    {

        public abstract void CloseRecordSet();

        public abstract long GetCount();

        public abstract MetadataInfo GetCurrent();

        public abstract boolean MoveNext();

        public abstract long MoveTo(long l);

        public abstract long OpenRecordSet(int i, String s, Metadatahelper.Filter afilter[], Metadatahelper.Sort sort, long l);
    }


    public Metadata()
    {
    }
}
