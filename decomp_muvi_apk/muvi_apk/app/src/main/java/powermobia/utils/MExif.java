// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;


// Referenced classes of package powermobia.utils:
//            MStream, MBitmapFactory, MBitmap

public final class MExif
{

    public static final int TAGID_APERTUREVALUE = 37378;
    public static final int TAGID_BRIGHTNESSVALUE = 37379;
    public static final int TAGID_COLORSPACE = 40961;
    public static final int TAGID_COMPONENTCONFIGURATION = 37121;
    public static final int TAGID_COMPRESSEDBITSPERPIXEL = 37122;
    public static final int TAGID_COMPRESSED_LARGETHUMBNAIL = -65290;
    public static final int TAGID_COMPRESSED_THUMBNAIL = -65292;
    public static final int TAGID_CONTRAST = 41992;
    public static final int TAGID_COPYRIGHT = 33432;
    public static final int TAGID_CUSTOM_DATA_VOICE = -65285;
    public static final int TAGID_DATETIMEDIGITIZED = 36868;
    public static final int TAGID_DATETIMEORIGINAL = 36867;
    public static final int TAGID_DIGITALZOOMRATIO = 41988;
    public static final int TAGID_EXIFPOINTER = 34665;
    public static final int TAGID_EXIFVERSION = 36864;
    public static final int TAGID_EXISTENTFLAG = -65294;
    public static final int TAGID_EXPOSUREBIASVALUE = 37380;
    public static final int TAGID_EXPOSUREINDEX = 41493;
    public static final int TAGID_EXPOSUREMODE = 41986;
    public static final int TAGID_EXPOSUREPROGRAM = 34850;
    public static final int TAGID_EXPOSURETIME = 33434;
    public static final int TAGID_EXTRATHUMBNAIL = -65287;
    public static final int TAGID_EXTRATHUMBNAIL_SIZE = -65286;
    public static final int TAGID_FILESOURCE = 41728;
    public static final int TAGID_FLASH = 37385;
    public static final int TAGID_FLASHENERGY = 41483;
    public static final int TAGID_FLASHPIXVERSION = 40960;
    public static final int TAGID_FNUMBER = 33437;
    public static final int TAGID_FOCALLENGTH = 37386;
    public static final int TAGID_GPS_ALTITUDE = 6;
    public static final int TAGID_GPS_ALTITUDEREF = 5;
    public static final int TAGID_GPS_AREAINFORMATION = 28;
    public static final int TAGID_GPS_LATITUDE = 2;
    public static final int TAGID_GPS_LATITUDEREF = 1;
    public static final int TAGID_GPS_LONGITUDE = 4;
    public static final int TAGID_GPS_LONGITUDEREF = 3;
    public static final int TAGID_GPS_VERSION = 0;
    public static final int TAGID_IMAGECREATETIME = 306;
    public static final int TAGID_IMAGEDESCRIPTION = 270;
    public static final int TAGID_INTERCHANGEFORMAT = 513;
    public static final int TAGID_INTERCHANGEFORMATLENFTH = 514;
    public static final int TAGID_INTEROPERABILITYINDEX = -65288;
    public static final int TAGID_INTEROPERABILITYPOINTER = 40965;
    public static final int TAGID_ISOSPEEDRATINGS = 34855;
    public static final int TAGID_LARGETHUMBNAIL = -65291;
    public static final int TAGID_LIGHTSOURCE = 37384;
    public static final int TAGID_MAKE = 271;
    public static final int TAGID_MAKERNOTE = 37500;
    public static final int TAGID_MAXAPERTUREVALUE = 37381;
    public static final int TAGID_METERINGMODE = 37383;
    public static final int TAGID_MODEL = 272;
    public static final int TAGID_OECF = 34856;
    public static final int TAGID_ORIENTATION = 274;
    public static final int TAGID_PIXELXDIMENSION = 40962;
    public static final int TAGID_PIXELYDIMENSION = 40963;
    public static final int TAGID_RELATEDSOUNDFILE = 40964;
    public static final int TAGID_RESOLUTIONUNIT = 296;
    public static final int TAGID_SATURATION = 41993;
    public static final int TAGID_SCENECAPTURETYPE = 41990;
    public static final int TAGID_SCENETYPE = 41729;
    public static final int TAGID_SENSINGMETHOD = 41495;
    public static final int TAGID_SHARPNESS = 41994;
    public static final int TAGID_SHUTTERSPEEDVALUE = 37377;
    public static final int TAGID_SOFTWARE = 305;
    public static final int TAGID_SPATIALFREQUENCYRESPONSE = 41484;
    public static final int TAGID_SUBJECTDISTANCE = 37382;
    public static final int TAGID_SUBJECTLOCATION = 41492;
    public static final int TAGID_THUMBNAIL = -65295;
    public static final int TAGID_THUMBNAIL_SIZE = -65293;
    public static final int TAGID_USERCOMMENT = 37510;
    public static final int TAGID_USERDEFINED = -65289;
    public static final int TAGID_WHITEBALANCE = 41987;
    public static final int TAGID_XRESOLUTION = 282;
    public static final int TAGID_YCBCRPOSITION = 531;
    public static final int TAGID_YRESOLUTION = 283;
    private boolean mApply;
    private int mExif;
    private int mNativeStream;
    private String mstrFilename;
    private MStream mstream;

    public MExif(String s)
    {
        mExif = 0;
        mstrFilename = s;
        mstream = null;
        mApply = false;
    }

    public MExif(MStream mstream1)
    {
        mExif = 0;
        mNativeStream = mstream1.getStreamHandle();
        mstrFilename = null;
        mstream = null;
        mApply = false;
    }

    private native int native_ExifApply(int i);

    private native int native_ExifCreate(int i);

    private native Object native_ExifGetFieldData(int i, int j);

    private native int native_ExifSetFieldData(int i, int j, Object obj);

    public final int apply()
    {
        if (mApply)
        {
            return 0;
        }
        int i = native_ExifApply(mExif);
        if (mstream.isValidStream())
        {
            mstream.close();
        }
        mApply = true;
        return i;
    }

    protected void finalize()
        throws Throwable
    {
        apply();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public final Object getFieldData(int i)
    {
        return native_ExifGetFieldData(mExif, i);
    }

    public final MBitmap getThumbnail(int i)
    {
        Integer.valueOf(0);
        byte abyte0[] = (byte[])getFieldData(-65292);
        if (abyte0 == null)
        {
            return null;
        } else
        {
            MStream mstream1 = new MStream();
            mstream1.open(abyte0);
            MBitmap mbitmap = MBitmapFactory.createMBitmapFromMStream(mstream1, 0, 0, i);
            mstream1.close();
            return mbitmap;
        }
    }

    public final boolean init()
    {
        if (mstrFilename.length() > 0)
        {
            mstream = new MStream();
            if (mstream.open(mstrFilename, 4))
            {
                mNativeStream = mstream.getNativeHandle();
                mExif = native_ExifCreate(mNativeStream);
            }
        }
        return mExif != 0;
    }

    final int ni()
    {
        return mExif;
    }

    public final int setFieldData(int i, Object obj)
    {
        return native_ExifSetFieldData(mExif, i, obj);
    }
}
