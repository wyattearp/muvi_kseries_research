// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.DisplayMetrics;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.DoubleBufferList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsRemoteDataSource, Property, MediaItem, ImageItem

public class ImageRemoteDataSource extends AbsRemoteDataSource
    implements DataSourceFactory.IProduct
{
    public static class RemoteImageItem extends ImageItem
    {

        String largepath;
        String thumbnailurl;
        String thumbpath;

        public String toString()
        {
            return (new StringBuilder()).append("Image : ").append(title).append(" - ").append(datestring).toString();
        }

        public RemoteImageItem()
        {
            thumbnailurl = null;
            thumbpath = null;
            largepath = null;
        }
    }


    private static final long FILE_SIZE_LIMIT = 0x3e800L;
    public static String PROJECTIONS[] = {
        "_ID", "TITLE", "DATE", "THUMBNAIL_URL", "URL", "SIZE", "VGAURL"
    };
    private static final int RESOLUTION_BOUND = 1200;
    private static final int RESOLUTION_UP_LIMIT = 1320;
    private static final Property SORT_CAPBILITIES[];

    ImageRemoteDataSource(Context context, RemoteDBMgr remotedbmgr)
    {
        super(context, remotedbmgr);
    }

    public static MediaItem createMediaItems(Cursor cursor)
    {
        if (cursor == null)
        {
            return null;
        } else
        {
            RemoteImageItem remoteimageitem = new RemoteImageItem();
            remoteimageitem._id = cursor.getLong(0);
            remoteimageitem.title = cursor.getString(1);
            remoteimageitem.datestring = cursor.getString(2);
            remoteimageitem.thumbnailurl = cursor.getString(3);
            remoteimageitem.uri = Uri.parse(cursor.getString(4));
            remoteimageitem.size = cursor.getLong(5);
            remoteimageitem.vgaUri = Uri.parse(cursor.getString(6));
            remoteimageitem.isDir = false;
            remoteimageitem.videoOrImage = false;
            return remoteimageitem;
        }
    }

    public static String getImageDownloadUrl(MediaItem mediaitem, boolean flag)
    {
        RemoteImageItem remoteimageitem = (RemoteImageItem)mediaitem;
        String s;
        if (mediaitem == null || mediaitem.isDir)
        {
            s = null;
        } else
        {
            boolean flag1;
            String s1;
            if (Settings.instance().getDefaultContentAccess() == 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag)
            {
                s1 = remoteimageitem.thumbpath;
            } else
            {
                s1 = remoteimageitem.largepath;
            }
            if (flag)
            {
                s = remoteimageitem.thumbnailurl;
            } else
            {
                Uri uri;
                if (flag1)
                {
                    uri = remoteimageitem.vgaUri;
                } else
                {
                    uri = remoteimageitem.uri;
                }
                s = uri.toString();
            }
            if (s == null || s1 != null)
            {
                return null;
            }
        }
        return s;
    }

    public static Object getObjectProp(MediaItem mediaitem, Property property, Object obj, boolean flag)
    {
        RemoteImageItem remoteimageitem;
        remoteimageitem = (RemoteImageItem)mediaitem;
        break MISSING_BLOCK_LABEL_6;
        while (true) 
        {
            do
            {
                do
                {
                    return obj;
                } while (property.equals(Property.PROP_WIDTH) || property.equals(Property.PROP_HEIGHT));
                if (property.equals(Property.PROP_ADDED_TIME) || property.equals(Property.PROP_MODIFIED_TIME) || property.equals(Property.PROP_TAKEN_TIME))
                {
                    return remoteimageitem.datestring;
                }
                if (property.equals(Property.PROP_MIMETYPE))
                {
                    return "image/*";
                }
                if (!property.equals(Property.PROP_IMAGE_FILEPATH))
                {
                    break;
                }
                if (remoteimageitem.largepath != null)
                {
                    return remoteimageitem.largepath;
                }
            } while (true);
            if (property.equals(Property.PROP_THUMBNAIL_FILEPATH))
            {
                if (remoteimageitem.thumbpath != null)
                {
                    return remoteimageitem.thumbpath;
                }
            } else
            if (property.equals(Property.PROP_URI))
            {
                if (flag && remoteimageitem.vgaUri != null && remoteimageitem.vgaUri.getPath().length() != 0)
                {
                    Log.e("AbsRemoteDataSource", (new StringBuilder()).append("ImageRemoteDataSource: vgaUri = ").append(remoteimageitem.vgaUri).toString());
                    return remoteimageitem.vgaUri;
                } else
                {
                    Log.e("AbsRemoteDataSource", (new StringBuilder()).append("ImageRemoteDataSource: Uri = ").append(mediaitem.uri).toString());
                    return mediaitem.uri;
                }
            } else
            {
                return null;
            }
        }
    }

    private String getSortOrder()
    {
        return getSortOrder(mSortProperty, mIsDesc);
    }

    public static String getSortOrder(Property property, boolean flag)
    {
        String s;
        if (property == null)
        {
            s = null;
        } else
        {
            if (!property.isBelongsTo(SORT_CAPBILITIES))
            {
                return null;
            }
            if (property.equals(Property.PROP_ADDED_TIME) || property.equals(Property.PROP_MODIFIED_TIME) || property.equals(Property.PROP_TAKEN_TIME))
            {
                s = "DATE";
            } else
            if (property.equals(Property.PROP_SIZE))
            {
                s = "SIZE";
            } else
            if (property.equals(Property.PROP_TITLE))
            {
                s = "lower(TITLE)";
            } else
            {
                return null;
            }
            if (flag)
            {
                return (new StringBuilder()).append(s).append(" DESC").toString();
            }
        }
        return s;
    }

    public static boolean onImageDownloadError(MediaItem mediaitem, boolean flag)
    {
        RemoteImageItem remoteimageitem;
        for (remoteimageitem = (RemoteImageItem)mediaitem; remoteimageitem == null || !flag || remoteimageitem.thumbpath != null;)
        {
            return false;
        }

        remoteimageitem.thumbnailurl = remoteimageitem.uri.toString();
        return true;
    }

    public static boolean updateRemoteImageDownloadPath(MediaItem mediaitem, String s, boolean flag)
    {
        RemoteImageItem remoteimageitem;
        boolean flag1;
        for (remoteimageitem = (RemoteImageItem)mediaitem; remoteimageitem == null || flag && remoteimageitem.thumbpath != null || !flag && remoteimageitem.largepath != null;)
        {
            return false;
        }

        if (remoteimageitem.uri != null && remoteimageitem.thumbnailurl != null && remoteimageitem.uri.toString().equals(remoteimageitem.thumbnailurl))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        if (remoteimageitem.thumbpath != null && !flag1)
        {
            (new File(remoteimageitem.thumbpath)).delete();
        }
        remoteimageitem.thumbpath = s;
        remoteimageitem.largepath = s;
_L4:
        return true;
_L2:
        remoteimageitem.thumbpath = s;
        if (flag1)
        {
            remoteimageitem.largepath = remoteimageitem.thumbpath;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void OnDBDataUpdated(String s, com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.UpdateInfo updateinfo)
    {
        if (updateinfo == null || updateinfo.imageadded)
        {
            super.OnDBDataUpdated(s, updateinfo);
        }
    }

    protected boolean compressFile(String s, String s1)
    {
        if (s != null && s1 != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        Bitmap bitmap;
        File file1;
        FileOutputStream fileoutputstream;
        File file = new File(s);
        if (!file.exists() || file.isDirectory())
        {
            return false;
        }
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        BitmapFactory.decodeFile(s, options);
        options.inJustDecodeBounds = true;
        int i = file.length() != 0x3e800L;
        boolean flag = false;
        if (i > 0)
        {
            flag = true;
        }
        if (!flag && (options.outWidth > 1320 || options.outHeight > 1320))
        {
            flag = true;
        }
        int j = options.outWidth / 1200;
        int k = options.outHeight / 1200;
        if (j > k)
        {
            j = k;
        }
        options.outWidth = options.outWidth / j;
        options.outHeight = options.outHeight / j;
        int l = Math.min(mContext.getResources().getDisplayMetrics().heightPixels, mContext.getResources().getDisplayMetrics().widthPixels);
        if (options.outWidth < l || options.outHeight < l)
        {
            flag = false;
        }
        if (!flag)
        {
            return false;
        }
        final android.graphics.BitmapFactory.Options optcheck = new android.graphics.BitmapFactory.Options();
        optcheck.inSampleSize = j;
        bitmap = BitmapFactory.decodeFile(s, optcheck);
        (new Thread() {

            final ImageRemoteDataSource this$0;
            final android.graphics.BitmapFactory.Options val$optcheck;

            public void run()
            {
                try
                {
                    sleep(5000L);
                    optcheck.requestCancelDecode();
                    return;
                }
                catch (Exception exception6)
                {
                    return;
                }
            }

            
            {
                this$0 = ImageRemoteDataSource.this;
                optcheck = options;
                super();
            }
        }).start();
        if (bitmap == null)
        {
            return false;
        }
        file1 = new File(s1);
        fileoutputstream = null;
        FileOutputStream fileoutputstream1 = new FileOutputStream(file1);
        bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 80, fileoutputstream1);
        fileoutputstream1.flush();
        bitmap.recycle();
        Exception exception2;
        Exception exception5;
        if (fileoutputstream1 != null)
        {
            try
            {
                fileoutputstream1.close();
            }
            catch (Exception exception4) { }
        }
        Exception exception;
        Exception exception1;
        Exception exception3;
        if (false)
        {
            file1.delete();
            return true;
        } else
        {
            return true;
        }
        exception5;
_L6:
        bitmap.recycle();
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception1) { }
        }
        if (false) goto _L1; else goto _L3
_L3:
        file1.delete();
        return false;
        exception2;
_L5:
        bitmap.recycle();
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception3) { }
        }
        if (true)
        {
            file1.delete();
        }
        throw exception2;
        exception2;
        fileoutputstream = fileoutputstream1;
        if (true) goto _L5; else goto _L4
_L4:
        exception;
        fileoutputstream = fileoutputstream1;
          goto _L6
    }

    public void create()
    {
        super.init();
    }

    protected MediaItem createMediaItem(Cursor cursor)
    {
        return createMediaItems(cursor);
    }

    public void destory()
    {
        super.unInit();
    }

    protected void destoryItem(MediaItem mediaitem)
    {
    }

    protected String getDownloadUrlIfNeed(MediaItem mediaitem, boolean flag)
    {
        return getImageDownloadUrl(mediaitem, flag);
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        Object obj1 = getObjectProp((MediaItem)mList.get(i), property, obj, mIsVgaResource);
        if (obj1 != null)
        {
            return obj1;
        } else
        {
            return super.getObjectProp(i, property, obj);
        }
    }

    public Property[] getSortCapability()
    {
        return SORT_CAPBILITIES;
    }

    protected boolean onDownloadError(MediaItem mediaitem, boolean flag)
    {
        return onImageDownloadError(mediaitem, flag);
    }

    protected Cursor openCursor()
    {
        return RemoteDBMgr.instance().queryImage(PROJECTIONS, null, null, null, null, getSortOrder(), null);
    }

    protected boolean updateDownloadedPath(MediaItem mediaitem, String s, boolean flag)
    {
        return updateRemoteImageDownloadPath(mediaitem, s, flag);
    }

    static 
    {
        Property aproperty[] = new Property[5];
        aproperty[0] = Property.PROP_TITLE;
        aproperty[1] = Property.PROP_ADDED_TIME;
        aproperty[2] = Property.PROP_MODIFIED_TIME;
        aproperty[3] = Property.PROP_TAKEN_TIME;
        aproperty[4] = Property.PROP_SIZE;
        SORT_CAPBILITIES = aproperty;
    }
}
