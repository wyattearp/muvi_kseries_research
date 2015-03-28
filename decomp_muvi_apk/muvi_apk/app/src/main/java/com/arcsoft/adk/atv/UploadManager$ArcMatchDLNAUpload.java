// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.net.Uri;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.oem.SharpUtils;

// Referenced classes of package com.arcsoft.adk.atv:
//            DLNA, ServerManager, UploadManager

private class <init> extends d
{

    final UploadManager this$0;

    public boolean matchDLNAUploadProfile(String s, Uri uri, String s1, Integer integer)
    {
        if (!super.matchDLNAUploadProfile(s, uri, s1, integer))
        {
            return false;
        }
        String s2 = uri.getEncodedPath();
        if (s1.startsWith("image/"))
        {
            String s5 = SharpUtils.getMPOFilePath(s2);
            if (s5 != null)
            {
                s2 = s5;
            }
        }
        String s3 = DLNA.instance().getFileProtocolInfo(s2);
        if (s3 == null || s3.length() < 1)
        {
            Log.w("matchDLNAUploadProfile", "getFileProtocolInfo fail");
            return false;
        } else
        {
            String s4 = updateMimeType(s3, s1);
            return DLNA.instance().getServerManager().matchMediaSupportDLNAUpload(s, s3, s4);
        }
    }

    public void recycle()
    {
    }

    private d()
    {
        this$0 = UploadManager.this;
        super(UploadManager.this, null);
    }

    d(d d)
    {
        this();
    }
}
