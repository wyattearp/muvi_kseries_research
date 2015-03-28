// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.os;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils
{
    public static final class FileStatus
    {

        public long atime;
        public int blksize;
        public long blocks;
        public long ctime;
        public int dev;
        public int gid;
        public int ino;
        public int mode;
        public long mtime;
        public int nlink;
        public int rdev;
        public long size;
        public int uid;

        public FileStatus()
        {
        }
    }


    private static final Pattern SAFE_FILENAME_PATTERN = Pattern.compile("[\\w%+,./=_-]+");
    public static final int S_IRGRP = 32;
    public static final int S_IROTH = 4;
    public static final int S_IRUSR = 256;
    public static final int S_IRWXG = 56;
    public static final int S_IRWXO = 7;
    public static final int S_IRWXU = 448;
    public static final int S_IWGRP = 16;
    public static final int S_IWOTH = 2;
    public static final int S_IWUSR = 128;
    public static final int S_IXGRP = 8;
    public static final int S_IXOTH = 1;
    public static final int S_IXUSR = 64;

    public FileUtils()
    {
    }

    public static boolean copyFile(File file, File file1)
    {
        FileInputStream fileinputstream;
        Exception exception;
        boolean flag;
        try
        {
            fileinputstream = new FileInputStream(file);
        }
        catch (IOException ioexception)
        {
            return false;
        }
        flag = copyToFile(fileinputstream, file1);
        fileinputstream.close();
        return flag;
        exception;
        fileinputstream.close();
        throw exception;
    }

    public static boolean copyToFile(InputStream inputstream, File file)
    {
        FileOutputStream fileoutputstream;
        Exception exception;
        byte abyte0[];
        int i;
        try
        {
            if (file.exists())
            {
                file.delete();
            }
            fileoutputstream = new FileOutputStream(file);
        }
        catch (IOException ioexception)
        {
            return false;
        }
        abyte0 = new byte[4096];
_L1:
        i = inputstream.read(abyte0);
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        fileoutputstream.write(abyte0, 0, i);
          goto _L1
        exception;
        fileoutputstream.flush();
        try
        {
            fileoutputstream.getFD().sync();
        }
        catch (IOException ioexception1) { }
        fileoutputstream.close();
        throw exception;
        fileoutputstream.flush();
        try
        {
            fileoutputstream.getFD().sync();
        }
        catch (IOException ioexception2) { }
        fileoutputstream.close();
        return true;
    }

    public static native int getFatVolumeId(String s);

    public static native boolean getFileStatus(String s, FileStatus filestatus);

    public static native int getPermissions(String s, int ai[]);

    public static boolean isFilenameSafe(File file)
    {
        return SAFE_FILENAME_PATTERN.matcher(file.getPath()).matches();
    }

    public static String readTextFile(File file, int i, String s)
        throws IOException
    {
        byte abyte0[];
        boolean flag;
        boolean flag1;
        FileInputStream fileinputstream;
        abyte0 = null;
        flag = true;
        flag1 = false;
        fileinputstream = new FileInputStream(file);
        long l = file.length();
        if (i <= 0 && (l <= 0L || i != 0)) goto _L2; else goto _L1
_L13:
        byte abyte1[];
        int j;
        abyte1 = new byte[i + 1];
        j = fileinputstream.read(abyte1);
        if (j <= 0)
        {
            fileinputstream.close();
            return "";
        }
        if (j > i)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        String s3 = new String(abyte1, 0, j);
        fileinputstream.close();
        return s3;
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_124;
        }
        String s2 = new String(abyte1, 0, i);
        fileinputstream.close();
        return s2;
        String s1 = (new StringBuilder()).append(new String(abyte1, 0, i)).append(s).toString();
        fileinputstream.close();
        return s1;
_L2:
        if (i >= 0) goto _L4; else goto _L3
_L3:
        byte abyte3[] = null;
_L12:
        int j1;
        if (abyte0 != null)
        {
            flag1 = flag;
        }
        if (abyte0 != null)
        {
            break MISSING_BLOCK_LABEL_190;
        }
        j1 = -i;
        abyte0 = new byte[j1];
        int i1 = fileinputstream.read(abyte0);
        if (i1 == abyte0.length) goto _L6; else goto _L5
_L5:
        if (abyte3 == null && i1 <= 0)
        {
            fileinputstream.close();
            return "";
        }
        if (abyte3 != null)
        {
            break MISSING_BLOCK_LABEL_249;
        }
        String s7 = new String(abyte0, 0, i1);
        fileinputstream.close();
        return s7;
        if (i1 <= 0) goto _L8; else goto _L7
_L7:
        System.arraycopy(abyte3, i1, abyte3, 0, abyte3.length - i1);
        System.arraycopy(abyte0, 0, abyte3, abyte3.length - i1, i1);
          goto _L9
_L14:
        String s5 = new String(abyte3);
        fileinputstream.close();
        return s5;
_L15:
        String s6 = (new StringBuilder()).append(s).append(new String(abyte3)).toString();
        fileinputstream.close();
        return s6;
_L4:
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte2[];
        bytearrayoutputstream = new ByteArrayOutputStream();
        abyte2 = new byte[1024];
_L11:
        int k = fileinputstream.read(abyte2);
        if (k <= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        bytearrayoutputstream.write(abyte2, 0, k);
        if (k == abyte2.length) goto _L11; else goto _L10
_L10:
        String s4 = bytearrayoutputstream.toString();
        fileinputstream.close();
        return s4;
        Exception exception;
        exception;
        fileinputstream.close();
        throw exception;
_L8:
        flag = flag1;
        continue; /* Loop/switch isn't completed */
_L6:
        byte abyte4[] = abyte0;
        abyte0 = abyte3;
        abyte3 = abyte4;
          goto _L12
_L1:
        if (l > 0L && (i == 0 || l < (long)i))
        {
            i = (int)l;
        }
          goto _L13
_L9:
        if (s != null && flag) goto _L15; else goto _L14
    }

    public static native int setPermissions(String s, int i, int j, int k);

    public static boolean sync(FileOutputStream fileoutputstream)
    {
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.getFD().sync();
            }
            catch (IOException ioexception)
            {
                return false;
            }
        }
        return true;
    }

}
