// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.net.Uri;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.adk.atv:
//            DLNA, UploadManager

private abstract class <init>
{

    private static final String DLNAUPLOAD_AUDIOSUFFIX = ".mp3;.m4a;.wma;.wav;.pcm;.adts;";
    private static final String DLNAUPLOAD_IMAGESUFFIX = ".gif;.jpg;.jpeg;.jpe;.jp2;.png;.bmp;.tiff;.thm;";
    private static final String DLNAUPLOAD_VIDEOSUFFIX = ".ts;.m2ts;.tts;.3gp;.3g2;.mp4;.mpg;.mpeg;.wmv;.asf;.avi;.divx;.xvid;.mov;";
    final String mMimeTypeMap[] = {
        "video/mp4", "video/3gpp"
    };
    final UploadManager this$0;

    protected boolean matchDLNAStackFileType(String s, Integer integer)
    {
        return integer.intValue() > 0;
    }

    protected boolean matchDLNAStackMimeType(String s, String s1)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        int i;
        return false;
_L2:
        if ((i = s.lastIndexOf(".")) < 0) goto _L1; else goto _L3
_L3:
        String s3;
        int j;
        int k;
        String s2 = s.substring(i);
        if (s1.startsWith("image/"))
        {
            s3 = ".gif;.jpg;.jpeg;.jpe;.jp2;.png;.bmp;.tiff;.thm;";
        } else
        {
            if (!s1.startsWith("audio/"))
            {
                continue; /* Loop/switch isn't completed */
            }
            s3 = ".mp3;.m4a;.wma;.wav;.pcm;.adts;";
        }
_L5:
        j = 0;
        k = 0;
_L7:
        k = s3.indexOf(";", k + 1);
        if (k == -1)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (s2.compareToIgnoreCase(s3.substring(j, k)) == 0)
        {
            return true;
        }
        break MISSING_BLOCK_LABEL_113;
        if (!s1.startsWith("video/")) goto _L1; else goto _L4
_L4:
        s3 = ".ts;.m2ts;.tts;.3gp;.3g2;.mp4;.mpg;.mpeg;.wmv;.asf;.avi;.divx;.xvid;.mov;";
          goto _L5
        j = k + 1;
        if (k != -1) goto _L7; else goto _L6
_L6:
        return false;
          goto _L5
    }

    public boolean matchDLNAUploadProfile(String s, Uri uri, String s1, Integer integer)
    {
        if (s == null || uri == null || s1 == null)
        {
            return false;
        }
        String s2 = uri.getEncodedPath();
        boolean flag;
        if (integer.intValue() >= 0)
        {
            flag = matchDLNAStackFileType(s2, integer);
        } else
        {
            flag = matchDLNAStackMimeType(s2, s1);
        }
        if (!flag)
        {
            Log.w("matchDLNAUploadProfile", (new StringBuilder()).append("not match dlna stack mime type =").append(s1).append(", path=").append(s2).toString());
            return false;
        }
        if (DLNA.instance().getServerManager() == null)
        {
            Log.w("matchDLNAUploadProfile", "server manager null");
            return false;
        } else
        {
            return true;
        }
    }

    protected boolean matchVideoM2TSMimeType(String s)
    {
        if (s != null)
        {
            String as[] = mMimeTypeMap;
            int i = as.length;
            int j = 0;
            while (j < i) 
            {
                if (as[j].equalsIgnoreCase(s))
                {
                    return true;
                }
                j++;
            }
        }
        return false;
    }

    abstract void recycle();

    protected String updateMimeType(String s, String s1)
    {
        int i = s1.indexOf('/');
        String s2 = null;
        if (i > 0)
        {
            s2 = s1.substring(0, 1 + s1.indexOf('/'));
        }
        if (s2 != null)
        {
            int j = s.indexOf(s2);
            if (j > 0)
            {
                int k;
                if (s.indexOf("CONTENTFORMAT=") > 0)
                {
                    k = s.indexOf('"', j);
                } else
                {
                    k = s.indexOf(':', j);
                }
                if (k > 0)
                {
                    s1 = s.substring(j, k);
                }
            }
        }
        return s1;
    }

    private ()
    {
        this$0 = UploadManager.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
