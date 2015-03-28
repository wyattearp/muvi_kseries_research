// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.webkit.MimeTypeMap;
import com.arcsoft.mediaplus.MediaPlusApplication;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.picture.controller.LocalCacheMgr;
import com.arcsoft.mediaplus.picture.data.LocalCacheProvider;
import com.arcsoft.util.debug.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

public class FileUtils
{

    public static int MRESULT_CHECK_EXCEPTION = -2;
    public static int MRESULT_SDCARD_NOT_EXIST = -1;

    public FileUtils()
    {
    }

    public static boolean CopyFile(String s, String s1)
    {
        File file;
        boolean flag;
        boolean flag1;
        file = new File(s);
        flag = file.exists();
        flag1 = false;
        if (!flag) goto _L2; else goto _L1
_L1:
        boolean flag2;
        flag2 = file.isFile();
        flag1 = false;
        if (flag2) goto _L3; else goto _L2
_L2:
        return flag1;
_L3:
        File file1;
        boolean flag3;
        file1 = new File(s1);
        flag3 = file1.exists();
        flag1 = false;
        if (flag3) goto _L2; else goto _L4
_L4:
        FileInputStream fileinputstream;
        FileOutputStream fileoutputstream;
        fileinputstream = null;
        fileoutputstream = null;
        FileInputStream fileinputstream1 = new FileInputStream(file);
        FileOutputStream fileoutputstream1 = new FileOutputStream(file1);
        byte abyte0[] = new byte[1024];
_L7:
        int i = fileinputstream1.read(abyte0);
        if (i == -1) goto _L6; else goto _L5
_L5:
        fileoutputstream1.write(abyte0, 0, i);
          goto _L7
        Exception exception1;
        exception1;
        fileoutputstream = fileoutputstream1;
        fileinputstream = fileinputstream1;
_L12:
        exception1.printStackTrace();
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
                return false;
            }
        }
        flag1 = false;
        if (fileinputstream == null) goto _L2; else goto _L8
_L8:
        fileinputstream.close();
        return false;
_L6:
        flag1 = true;
        if (fileoutputstream1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            fileoutputstream1.close();
        }
        catch (IOException ioexception2)
        {
            ioexception2.printStackTrace();
            return flag1;
        }
        if (fileinputstream1 == null) goto _L2; else goto _L9
_L9:
        fileinputstream1.close();
        return flag1;
        Exception exception;
        exception;
_L11:
        if (fileoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_222;
        }
        fileoutputstream.close();
        if (fileinputstream != null)
        {
            try
            {
                fileinputstream.close();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        }
        throw exception;
        exception;
        fileinputstream = fileinputstream1;
        fileoutputstream = null;
        continue; /* Loop/switch isn't completed */
        exception;
        fileoutputstream = fileoutputstream1;
        fileinputstream = fileinputstream1;
        if (true) goto _L11; else goto _L10
_L10:
        exception1;
        fileinputstream = null;
        fileoutputstream = null;
          goto _L12
        exception1;
        fileinputstream = fileinputstream1;
        fileoutputstream = null;
          goto _L12
    }

    private static boolean checkFsWritable()
    {
        File file = new File(Environment.getExternalStorageDirectory().toString());
        if (!file.isDirectory() && !file.mkdirs())
        {
            return false;
        } else
        {
            return file.canWrite();
        }
    }

    public static void closeCacheDB(Context context)
    {
        if (context != null)
        {
            ((MediaPlusApplication)context.getApplicationContext()).setCacheCleared(true);
            if (OEMConfig.OPENGL_OPTIMIZE)
            {
                com.arcsoft.mediaplus.picture.controller.CacheMgr cachemgr = ((MediaPlusApplication)context.getApplicationContext()).getLocalCacheManager();
                if (cachemgr != null)
                {
                    ((LocalCacheMgr)cachemgr).closeCacheDB();
                    return;
                }
            }
        }
    }

    public static boolean createFolder(String s)
    {
        File file = new File(s);
        if (file.exists() && file.isDirectory())
        {
            return true;
        } else
        {
            return file.mkdirs();
        }
    }

    public static boolean deleteDirectory(String s)
    {
        File file;
        if (!s.endsWith(File.separator))
        {
            s = (new StringBuilder()).append(s).append(File.separator).toString();
        }
        file = new File(s);
        if (file.exists() && file.isDirectory()) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        boolean flag;
        File afile[];
        int i;
        flag = true;
        afile = file.listFiles();
        i = 0;
_L4:
        if (i >= afile.length || (afile[i].isFile() ? !(flag = deleteFile(afile[i].getAbsolutePath())) : !(flag = deleteDirectory(afile[i].getAbsolutePath()))))
        {
            if (flag && file.delete())
            {
                return true;
            }
        } else
        {
            break MISSING_BLOCK_LABEL_126;
        }
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
        i++;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L1; else goto _L5
_L5:
    }

    public static boolean deleteFile(String s)
    {
        File file = new File(s);
        boolean flag = file.isFile();
        boolean flag1 = false;
        if (flag)
        {
            boolean flag2 = file.exists();
            flag1 = false;
            if (flag2)
            {
                file.delete();
                flag1 = true;
            }
        }
        return flag1;
    }

    public static boolean deleteFolder(String s)
    {
        File file = new File(s);
        if (!file.exists())
        {
            return false;
        }
        if (file.isFile())
        {
            return deleteFile(s);
        } else
        {
            return deleteDirectory(s);
        }
    }

    public static void deleteOldDBIfNeeded(Context context)
    {
        File file = new File(OEMConfig.CACHE_BASE_PATH);
        if (file.exists()) goto _L2; else goto _L1
_L1:
        File afile[];
        return;
_L2:
        if ((afile = file.listFiles()) == null || 0L == file.length())
        {
            continue; /* Loop/switch isn't completed */
        }
        String s;
        int i;
        int j;
        File file1;
        String s1;
        try
        {
            s = LocalCacheProvider.getDBName(context);
            i = afile.length;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return;
        }
        j = 0;
        if (j >= i)
        {
            continue; /* Loop/switch isn't completed */
        }
        file1 = afile[j];
        s1 = file1.getName();
        if (s1.startsWith("thumb_cache") && !s1.startsWith(s))
        {
            deleteFile(file1.getAbsolutePath());
        }
        j++;
        if (true) goto _L4; else goto _L3
_L3:
        break MISSING_BLOCK_LABEL_101;
_L4:
        break MISSING_BLOCK_LABEL_50;
        if (true) goto _L1; else goto _L5
_L5:
    }

    public static Uri filePathToContentUri(Context context, Uri uri, boolean flag)
    {
        if (uri == null)
        {
            return null;
        }
        String s;
        Uri uri1;
        String as[];
        int i;
        if (flag)
        {
            s = "_data";
        } else
        {
            s = "_data";
        }
        if (flag)
        {
            uri1 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else
        {
            uri1 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        if (flag)
        {
            as = (new String[] {
                "_id"
            });
        } else
        {
            as = (new String[] {
                "_id"
            });
        }
        i = -1;
        if (uri.toString().startsWith("file://"))
        {
            String s1 = uri.getPath();
            Log.e("testP", (new StringBuilder()).append("testP path1 is ").append(s1).toString());
            if (s1 != null)
            {
                ContentResolver contentresolver = context.getContentResolver();
                StringBuffer stringbuffer = new StringBuffer();
                stringbuffer.append("(").append(s).append("=").append((new StringBuilder()).append("'").append(s1).append("'").toString()).append(")");
                Cursor cursor = contentresolver.query(uri1, as, stringbuffer.toString(), null, null);
                Log.e("testP", (new StringBuilder()).append("testP cur.getCount() is ").append(cursor.getCount()).toString());
                if (cursor.getCount() > 0 && cursor.moveToFirst())
                {
                    i = cursor.getInt(cursor.getColumnIndex("_id"));
                }
                if (i == -1)
                {
                    return Uri.parse(uri.toString());
                }
            }
            return Uri.parse((new StringBuilder()).append(uri1.toString()).append("/").append(i).toString());
        } else
        {
            return Uri.parse(uri.toString());
        }
    }

    public static Uri filePathToContentUri(Uri uri, long l, boolean flag)
    {
        if (uri == null)
        {
            return null;
        }
        if (uri.toString().startsWith("file://"))
        {
            if (flag)
            {
                return Uri.parse((new StringBuilder()).append(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI.toString()).append("/").append(l).toString());
            } else
            {
                return Uri.parse((new StringBuilder()).append(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()).append("/").append(l).toString());
            }
        } else
        {
            return Uri.parse(uri.toString());
        }
    }

    public static String formatSize(long l)
    {
        if (l <= 0L)
        {
            return "0MB";
        }
        if (l < 1024L)
        {
            return (new StringBuilder()).append(String.valueOf(l)).append("B").toString();
        }
        long l1 = l / 1024L;
        if (l1 < 1024L)
        {
            return (new StringBuilder()).append(String.valueOf(l1)).append("KB").toString();
        }
        double d = (double)l / 1024D / 1024D;
        if (d < 1024D)
        {
            String s1 = String.valueOf(d);
            int k = s1.lastIndexOf(".");
            StringBuilder stringbuilder1 = new StringBuilder();
            int i1;
            if (k + 3 < s1.length())
            {
                i1 = k + 3;
            } else
            {
                i1 = s1.length();
            }
            return stringbuilder1.append(s1.substring(0, i1)).append("MB").toString();
        }
        String s = String.valueOf((double)l / 1024D / 1024D / 1024D);
        int i = s.lastIndexOf(".");
        StringBuilder stringbuilder = new StringBuilder();
        int j;
        if (i + 3 < s.length())
        {
            j = i + 3;
        } else
        {
            j = s.length();
        }
        return stringbuilder.append(s.substring(0, j)).append("GB").toString();
    }

    public static long getAndroidDataAvailableSize()
    {
        float f;
        int i;
        try
        {
            StatFs statfs = new StatFs(Environment.getDataDirectory().toString());
            f = statfs.getAvailableBlocks();
            i = statfs.getBlockSize();
        }
        catch (Exception exception)
        {
            return (long)MRESULT_CHECK_EXCEPTION;
        }
        return (long)((f * (float)i) / 1024F);
    }

    public static String getExtension(File file)
    {
        if (file != null)
        {
            return getExtension(file.getName());
        } else
        {
            return "";
        }
    }

    public static String getExtension(String s)
    {
        return getExtension(s, "");
    }

    public static String getExtension(String s, String s1)
    {
        if (s != null && s.length() > 0)
        {
            int i = s.lastIndexOf('.');
            if (i > -1 && i < -1 + s.length())
            {
                s1 = s.substring(i + 1);
            }
        }
        return s1;
    }

    public static long getFileSize(String s)
    {
        if (s == null)
        {
            return 0L;
        }
        long l;
        try
        {
            l = (new File(s)).length();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return 0L;
        }
        return l;
    }

    public static long getFolderSize(File file)
    {
        long l = 0L;
        File afile[] = file.listFiles();
        int i = 0;
_L2:
        if (i >= afile.length)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        if (afile[i].isDirectory())
        {
            l += getFolderSize(afile[i]);
            break MISSING_BLOCK_LABEL_65;
        }
        long l1 = afile[i].length();
        l += l1;
        break MISSING_BLOCK_LABEL_65;
        Exception exception;
        exception;
        return l;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static long getFolderSizeFormated(String s)
    {
        if (s == null)
        {
            return 0L;
        } else
        {
            return getFolderSize(new File(s));
        }
    }

    public static String getMPOFilePath(String s)
    {
        File file = new File(s);
        String s1 = file.getName();
        int i = s1.lastIndexOf('.');
        if (i > 0)
        {
            s1 = s1.substring(0, i);
        }
        FilenameFilter filenamefilter = new FilenameFilter(s1) {

            final String val$cmpname;

            public boolean accept(File file2, String s2)
            {
                int j = s2.lastIndexOf('.');
                if (j >= 0)
                {
                    String s3 = s2.substring(j).toLowerCase();
                    if (s2.substring(0, j).equals(cmpname) && s3.equals(".mpo"))
                    {
                        return true;
                    }
                }
                return false;
            }

            
            {
                cmpname = s;
                super();
            }
        };
        File file1 = file.getParentFile();
        String as[] = file.getParentFile().list(filenamefilter);
        if (as != null && as.length > 0)
        {
            return (new StringBuilder()).append(file1.getAbsolutePath()).append("/").append(as[0]).toString();
        } else
        {
            return null;
        }
    }

    public static long getMemoryAvailableSize(Context context)
    {
        ActivityManager activitymanager;
        if (context != null)
        {
            if ((activitymanager = (ActivityManager)context.getSystemService("activity")) != null)
            {
                android.app.ActivityManager.MemoryInfo memoryinfo = new android.app.ActivityManager.MemoryInfo();
                activitymanager.getMemoryInfo(memoryinfo);
                return memoryinfo.availMem / 1024L;
            }
        }
        return -1L;
    }

    public static String getNewFileName(String s)
    {
        String s1;
        if (s == null || s.length() == 0)
        {
            s1 = null;
        } else
        {
            s1 = s;
            File file = new File(s1);
            if (file.exists())
            {
                file.delete();
                return s1;
            }
        }
        return s1;
    }

    public static long getSDCardAvailableSize()
    {
        float f;
        int i;
        try
        {
            if (!hasStorage(true))
            {
                return (long)MRESULT_SDCARD_NOT_EXIST;
            }
            StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().toString());
            f = statfs.getAvailableBlocks();
            i = statfs.getBlockSize();
        }
        catch (Exception exception)
        {
            return (long)MRESULT_CHECK_EXCEPTION;
        }
        return (long)((f * (float)i) / 1024F);
    }

    public static String getVersionName(Context context)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        if (context == null)
        {
            return "";
        } else
        {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }
    }

    public static boolean hasStorage(boolean flag)
    {
        boolean flag1 = true;
        String s = Environment.getExternalStorageState();
        if ("mounted".equals(s))
        {
            if (flag)
            {
                flag1 = checkFsWritable();
            }
        } else
        if (flag || !"mounted_ro".equals(s))
        {
            return false;
        }
        return flag1;
    }

    public static boolean isLocalItem(Uri uri)
    {
        String s = uri.toString();
        return s != null && (s.startsWith("file://") || s.startsWith("content://"));
    }

    public static boolean isMediaStoreSupported(String s)
    {
        if (s == null)
        {
            return false;
        }
        Object obj1;
        Object obj3;
        String s1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(getExtension(s));
        Class class1 = Class.forName("android.media.MediaFile");
        Method method = class1.getDeclaredMethod("getFileTypeForMimeType", new Class[] {
            java/lang/String
        });
        method.setAccessible(true);
        int i = ((Integer)method.invoke(class1.newInstance(), new Object[] {
            s1
        })).intValue();
        Class aclass[] = new Class[1];
        aclass[0] = Integer.TYPE;
        Method method1 = class1.getDeclaredMethod("isImageFileType", aclass);
        method1.setAccessible(true);
        Object obj = class1.newInstance();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        obj1 = method1.invoke(obj, aobj);
        Class aclass1[] = new Class[1];
        aclass1[0] = Integer.TYPE;
        Method method2 = class1.getDeclaredMethod("isVideoFileType", aclass1);
        method2.setAccessible(true);
        Object obj2 = class1.newInstance();
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(i);
        obj3 = method2.invoke(obj2, aobj1);
        if (((Boolean)obj1).booleanValue()) goto _L2; else goto _L1
_L1:
        boolean flag1 = ((Boolean)obj3).booleanValue();
        if (!flag1) goto _L3; else goto _L2
_L2:
        boolean flag = true;
_L5:
        return flag;
_L3:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        exception.printStackTrace();
        return false;
    }

    public static void recreateCacheMgr(Context context)
    {
        if (context != null)
        {
            ((MediaPlusApplication)context.getApplicationContext()).setCacheCleared(true);
            if (OEMConfig.OPENGL_OPTIMIZE)
            {
                com.arcsoft.mediaplus.picture.controller.CacheMgr cachemgr = ((MediaPlusApplication)context.getApplicationContext()).getLocalCacheManager();
                if (cachemgr != null)
                {
                    ((LocalCacheMgr)cachemgr).recreateCache();
                    return;
                }
            }
        }
    }

    public static String trimExtension(String s)
    {
        if (s != null && s.length() > 0)
        {
            int i = s.lastIndexOf('.');
            if (i > -1 && i < s.length())
            {
                s = s.substring(0, i);
            }
        }
        return s;
    }

    public static String uriToFilePath(Context context, Uri uri)
    {
        String s = null;
        if (uri == null) goto _L2; else goto _L1
_L1:
        s = null;
        if (context != null) goto _L3; else goto _L2
_L2:
        return s;
_L3:
        Cursor cursor;
        s = null;
        cursor = null;
        String s1 = uri.toString();
        if (s1 == null) goto _L5; else goto _L4
_L4:
        boolean flag = s1.startsWith("http://");
        s = null;
        cursor = null;
        if (flag) goto _L7; else goto _L6
_L6:
        if (!uri.toString().startsWith("rtsp://")) goto _L5; else goto _L7
_L7:
        s = Uri.decode(uri.toString());
_L9:
        if (cursor == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        cursor.close();
        if (cursor == null) goto _L2; else goto _L8
_L8:
        cursor.close();
        return s;
_L5:
        boolean flag1 = uri.toString().startsWith("file://");
        s = null;
        cursor = null;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_129;
        }
        s = Uri.decode(uri.toString()).substring(7);
        cursor = null;
          goto _L9
        cursor = context.getContentResolver().query(uri, null, null, null, null);
        s = null;
        if (cursor == null) goto _L9; else goto _L10
_L10:
        boolean flag2 = cursor.moveToFirst();
        s = null;
        if (!flag2) goto _L9; else goto _L11
_L11:
        String s2 = cursor.getString(cursor.getColumnIndex("_data"));
        s = s2;
          goto _L9
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        if (cursor == null) goto _L2; else goto _L12
_L12:
        cursor.close();
        return s;
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
    }

}
