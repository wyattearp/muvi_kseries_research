// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;

import android.os.Bundle;
import android.os.FileObserver;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

// Referenced classes of package com.arcsoft.videotrim.Utils:
//            UtilFunc

public class MediaFileObserver extends FileObserver
{
    class MediaFileObserverProxy extends Observable
    {

        final MediaFileObserver this$0;

        public int destroy()
        {
            deleteObservers();
            return 0;
        }

        public void onFileChange(Bundle bundle)
        {
            setChanged();
            notifyObservers(bundle);
        }

        public MediaFileObserverProxy(Observer observer)
        {
            this$0 = MediaFileObserver.this;
            super();
            addObserver(observer);
        }
    }


    private static final String TAG = "MediaFileObserver";
    private MediaFileObserverProxy mMediaFileObserverProxy;
    private volatile boolean m_bIgnoreEvent;
    private String m_strFileChangeRenamedItem;
    private String m_strObservePath;

    public MediaFileObserver(String s, Observer observer)
    {
        super(s, 3780);
        m_bIgnoreEvent = false;
        m_strObservePath = s;
        m_strFileChangeRenamedItem = null;
        mMediaFileObserverProxy = new MediaFileObserverProxy(observer);
    }

    private void onFileChange(String s, int i, String s1)
    {
        if (s1 != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        boolean flag = (new File(s1)).isDirectory();
        long l = 0L;
        int j = i & 0x200;
        boolean flag1 = false;
        String s2 = null;
        if (j != 0)
        {
            if (flag)
            {
                l = 3L;
            } else
            {
                l = 2L;
            }
            s2 = s1;
            flag1 = true;
        }
        if ((i & 0x400) != 0 || (i & 4) != 0)
        {
            if (flag)
            {
                l = 4L;
            } else
            {
                l = 1L;
            }
            s2 = s1;
            flag1 = true;
        }
        if ((i & 0x800) != 0)
        {
            if (flag)
            {
                l = 4L;
            } else
            {
                l = 1L;
            }
            s2 = s1;
            flag1 = true;
        }
        if ((i & 0x40) != 0)
        {
            m_strFileChangeRenamedItem = s1;
            return;
        }
        int k = i & 0x80;
        String s3 = null;
        if (k == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        Bundle bundle;
        if (flag)
        {
            l = 4L;
        } else
        {
            l = 1L;
        }
        if (m_strFileChangeRenamedItem == null || m_strFileChangeRenamedItem.length() <= 0) goto _L1; else goto _L3
_L3:
        s2 = m_strFileChangeRenamedItem;
        s3 = s1;
        flag1 = true;
        if (!flag1) goto _L1; else goto _L4
_L4:
        bundle = new Bundle();
        bundle.putLong("filechange_eventid", l);
        bundle.putString("filechange_item_name", s2);
        bundle.putString("filechange_item_name_2", s3);
        mMediaFileObserverProxy.onFileChange(bundle);
        return;
    }

    public void destroy()
    {
        m_bIgnoreEvent = false;
        if (mMediaFileObserverProxy != null)
        {
            mMediaFileObserverProxy.destroy();
            mMediaFileObserverProxy = null;
        }
        m_strFileChangeRenamedItem = null;
        m_strObservePath = null;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof MediaFileObserver))
        {
            return false;
        } else
        {
            MediaFileObserver mediafileobserver = (MediaFileObserver)obj;
            return m_strObservePath.equalsIgnoreCase(mediafileobserver.m_strObservePath);
        }
    }

    public String getPath()
    {
        return m_strObservePath;
    }

    public int hashCode()
    {
        if (m_strObservePath == null)
        {
            return super.hashCode();
        } else
        {
            return 629 + m_strObservePath.hashCode();
        }
    }

    public void onEvent(int i, String s)
    {
        if (m_bIgnoreEvent)
        {
            return;
        }
        UtilFunc.Log("MediaFileObserver", (new StringBuilder()).append("onEvent, event=").append(i).append(",path=").append(s).toString());
        if (s != null && s.length() > 0)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append(m_strObservePath);
            if ('/' != stringbuffer.charAt(-1 + stringbuffer.length()))
            {
                stringbuffer.append('/');
            }
            stringbuffer.append(s);
            onFileChange("file", i, stringbuffer.toString());
            return;
        } else
        {
            onFileChange("file", i, m_strObservePath);
            return;
        }
    }

    public void setPath(String s)
    {
        m_strObservePath = s;
    }
}
