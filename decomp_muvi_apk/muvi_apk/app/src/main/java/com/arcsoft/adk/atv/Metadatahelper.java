// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.arcsoft.util.debug.Log;
import java.io.ByteArrayOutputStream;

// Referenced classes of package com.arcsoft.adk.atv:
//            MetadataVideo, MetadataAudio, MetadataImage

public final class Metadatahelper
{
    public static class Category
    {

        public static final int ALBUM = 1;
        public static final int ALL = 0;
        public static final int ARTISTS = 3;
        public static final int DATETAKEN = 5;
        public static final int GENRES = 2;
        public static final int PEOPLE = 6;
        public static final int PLAYLIST = 4;

        public Category()
        {
        }
    }

    public static final class DateTaken extends Enum
    {

        private static final DateTaken $VALUES[];
        public static final DateTaken Day_item;
        public static final DateTaken Item;
        public static final DateTaken Month_item;
        public static final DateTaken Year_item;

        public static DateTaken valueOf(String s)
        {
            return (DateTaken)Enum.valueOf(com/arcsoft/adk/atv/Metadatahelper$DateTaken, s);
        }

        public static DateTaken[] values()
        {
            return (DateTaken[])$VALUES.clone();
        }

        static 
        {
            Year_item = new DateTaken("Year_item", 0);
            Month_item = new DateTaken("Month_item", 1);
            Day_item = new DateTaken("Day_item", 2);
            Item = new DateTaken("Item", 3);
            DateTaken adatetaken[] = new DateTaken[4];
            adatetaken[0] = Year_item;
            adatetaken[1] = Month_item;
            adatetaken[2] = Day_item;
            adatetaken[3] = Item;
            $VALUES = adatetaken;
        }

        private DateTaken(String s, int i)
        {
            super(s, i);
        }
    }

    public static class FieldId
    {

        public static final int PATH = 1;
        public static final int SIZE = 2;
        public static final int TITLE;

        public FieldId()
        {
        }
    }

    public static class Filter
    {

        public int filter_id;
        public String strFilter;

        public Filter()
        {
        }
    }

    public static class Sort
    {

        public int sort_id;
        public int sort_type;

        public Sort()
        {
        }
    }

    public static class SortType
    {

        public static final int ASC = 1;
        public static final int DES = 2;
        public static final int NONE;

        public SortType()
        {
        }
    }

    static class ext_type
    {

        String ext;
        int type;

        public ext_type(String s, int i)
        {
            ext = s;
            type = i;
        }
    }


    private static final int CONST_THUMB_H = 60;
    private static final int CONST_THUMB_W = 60;
    public static final int IAUDIO = 2;
    public static final int IFOLDER = 0;
    public static final int IIMAGE = 3;
    public static final int ITEM_CLASS_AUDIO = 2;
    public static final int ITEM_CLASS_IMAGE = 3;
    public static final int ITEM_CLASS_UNKNOWN = 0;
    public static final int ITEM_CLASS_VIDEO = 1;
    public static final int IVIDEO = 1;
    public static final String TAG = "Metadatahelper";
    static ext_type et[];

    public Metadatahelper()
    {
    }

    private static byte[] Bitmap2Bytes(Bitmap bitmap)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, bytearrayoutputstream);
        return bytearrayoutputstream.toByteArray();
    }

    public static void CloseSet(Metadata.MetadataSetInterface metadatasetinterface)
    {
        Log.d("Metadatahelper", "CloseSet");
        metadatasetinterface.CloseRecordSet();
    }

    private static Bitmap CovertBp(Bitmap bitmap, int i, int j)
    {
        int k = bitmap.getWidth();
        int l = bitmap.getHeight();
        float f = (float)i / (float)k;
        float f1 = (float)j / (float)l;
        Log.d("Metadatahelper", (new StringBuilder()).append("bitmap width is :").append(k).toString());
        Log.d("Metadatahelper", (new StringBuilder()).append("bitmap height is :").append(l).toString());
        Log.d("Metadatahelper", (new StringBuilder()).append("new width is :").append(i).toString());
        Log.d("Metadatahelper", (new StringBuilder()).append("new height is :").append(j).toString());
        Log.d("Metadatahelper", (new StringBuilder()).append("scale width is  :").append(f).toString());
        Log.d("Metadatahelper", (new StringBuilder()).append("scale height is  :").append(f1).toString());
        Matrix matrix = new Matrix();
        matrix.postScale(f, f1);
        return Bitmap.createBitmap(bitmap, 0, 0, k, l, matrix, true);
    }

    public static long GetCount(Metadata.MetadataSetInterface metadatasetinterface)
    {
        if (metadatasetinterface != null)
        {
            Log.d("Metadatahelper", (new StringBuilder()).append("GetCount:").append(metadatasetinterface.GetCount()).toString());
            return metadatasetinterface.GetCount();
        } else
        {
            return -1L;
        }
    }

    public static Metadata.MetadataInfo GetCurrent(Metadata.MetadataSetInterface metadatasetinterface)
    {
        if (metadatasetinterface != null)
        {
            Metadata.MetadataInfo metadatainfo = metadatasetinterface.GetCurrent();
            Log.d("Metadatahelper", "GetCurrent");
            return metadatainfo;
        } else
        {
            return null;
        }
    }

    public static DateTaken GetDateTaken(String s, String as[])
    {
        if (s != null)
        {
            Log.d("Metadatahelper", s);
        }
        if (s == null)
        {
            Log.d("Metadatahelper", "aaaaa");
            return DateTaken.Year_item;
        }
        if (s.length() == 4)
        {
            Log.d("Metadatahelper", "bbbb");
            as[0] = s.split("/")[0];
            return DateTaken.Month_item;
        }
        if (s.length() == 7)
        {
            Log.d("Metadatahelper", "ccccc");
            String as2[] = s.split("/");
            as[0] = as2[0];
            as[1] = as2[1];
            return DateTaken.Day_item;
        }
        if (s.length() > 7)
        {
            Log.d("Metadatahelper", "dddddd");
            String as1[] = s.split("/");
            as[0] = as1[0];
            as[1] = as1[1];
            as[2] = as1[2];
            return DateTaken.Item;
        } else
        {
            return null;
        }
    }

    public static Metadata.MetadataInfo GetMetaData(int i, long l)
    {
        switch (i)
        {
        default:
            return null;

        case 1: // '\001'
            return MetadataVideo.GetMetaData(l);

        case 2: // '\002'
            return MetadataAudio.GetMetaData(l);

        case 3: // '\003'
            return MetadataImage.GetMetaData(l);
        }
    }

    public static Metadata.MetadataInfo GetMetaData(String s)
    {
        Metadata.MetadataInfo metadatainfo = MetadataImage.GetMetaData(s);
        if (metadatainfo == null)
        {
            metadatainfo = MetadataAudio.GetMetaData(s);
        }
        if (metadatainfo == null)
        {
            metadatainfo = MetadataVideo.GetMetaData(s);
        }
        return metadatainfo;
    }

    public static byte[] GetThumbnail(String s)
    {
        return null;
    }

    public static boolean MoveNext(Metadata.MetadataSetInterface metadatasetinterface)
    {
        if (metadatasetinterface != null)
        {
            return metadatasetinterface.MoveNext();
        } else
        {
            return false;
        }
    }

    public static long MoveTo(Metadata.MetadataSetInterface metadatasetinterface, long l)
    {
        Log.d("Metadatahelper", "MoveTo");
        if (metadatasetinterface != null)
        {
            return metadatasetinterface.MoveTo(l);
        } else
        {
            return -1L;
        }
    }

    public static Metadata.MetadataSetInterface OpenSet(int i, int j, String s, Filter afilter[], Sort sort, long l)
    {
        Object obj;
        Log.d("Metadatahelper", (new StringBuilder()).append("OpenSet index").append(l).toString());
        obj = null;
        i;
        JVM INSTR tableswitch 0 3: default 60
    //                   0 60
    //                   1 123
    //                   2 135
    //                   3 147;
           goto _L1 _L1 _L2 _L3 _L4
_L1:
        if (obj != null && 0L > ((Metadata.MetadataSetInterface) (obj)).OpenRecordSet(j, s, afilter, sort, l))
        {
            Log.d("Metadatahelper", (new StringBuilder()).append("0 > in.OpenRecordSet").append(l).toString());
            ((Metadata.MetadataSetInterface) (obj)).CloseRecordSet();
            obj = null;
        }
        return ((Metadata.MetadataSetInterface) (obj));
_L2:
        obj = new MetadataVideo();
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new MetadataAudio();
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new MetadataImage();
        if (true) goto _L1; else goto _L5
_L5:
    }

    public static native int getFatVolumeId(String s);

    static 
    {
        ext_type aext_type[] = new ext_type[8];
        aext_type[0] = new ext_type("mp3", 2);
        aext_type[1] = new ext_type("jpg", 3);
        aext_type[2] = new ext_type("png", 3);
        aext_type[3] = new ext_type("bmp", 3);
        aext_type[4] = new ext_type("mid", 2);
        aext_type[5] = new ext_type("mp4", 1);
        aext_type[6] = new ext_type("3gp", 1);
        aext_type[7] = new ext_type("gif", 3);
        et = aext_type;
    }
}
