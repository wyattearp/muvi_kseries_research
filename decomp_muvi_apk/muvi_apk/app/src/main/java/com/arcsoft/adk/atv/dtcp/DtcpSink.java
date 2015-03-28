// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv.dtcp;

import com.arcsoft.adk.atv.Recycleble;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.adk.atv.dtcp:
//            IDtcpSinkListener

public class DtcpSink
    implements Recycleble
{

    private final String logTAG = "DtcpSink";
    private int m_hdtcp;
    private int m_hdtcpSink;
    private IDtcpSinkListener m_listener;

    public DtcpSink(int i, IDtcpSinkListener idtcpsinklistener)
    {
        m_hdtcp = 0;
        m_hdtcpSink = 0;
        m_listener = null;
        m_hdtcp = i;
        m_listener = idtcpsinklistener;
    }

    private static native void JNI_DtcpSink_Cleanup(int i);

    private static native int JNI_DtcpSink_InitiateAKE(int i, String s, long l, DtcpSink dtcpsink, int ai[]);

    private static native void JNI_DtcpSink_StreamClose(int i);

    private static native int JNI_DtcpSink_StreamDelete(int i);

    private static native int JNI_DtcpSink_StreamOpenAES(int i, int j, String s);

    private static native int JNI_DtcpSink_StreamOpenCPRM(int i, int j, String s);

    private static native int JNI_DtcpSink_StreamWrite(int i, byte abyte0[], int j, int ai[]);

    public void CleanUp()
    {
        if (m_hdtcpSink == 0)
        {
            return;
        } else
        {
            JNI_DtcpSink_Cleanup(m_hdtcpSink);
            m_hdtcpSink = 0;
            return;
        }
    }

    public void CloseStream(boolean flag)
    {
        if (m_hdtcpSink == 0)
        {
            return;
        }
        if (flag)
        {
            JNI_DtcpSink_StreamDelete(m_hdtcpSink);
        }
        JNI_DtcpSink_StreamClose(m_hdtcpSink);
    }

    public int InitiateAKE(String s, long l)
    {
        if (m_hdtcp == 0)
        {
            return -1;
        }
        int ai[] = {
            0
        };
        int i = JNI_DtcpSink_InitiateAKE(m_hdtcp, s, l, this, ai);
        if (i != 0)
        {
            Log.e("DtcpSink", "DtcpSink::InitiateAKE == return false;");
            return i;
        } else
        {
            m_hdtcpSink = ai[0];
            return 0;
        }
    }

    public void OnAkeEnd(int i)
    {
        Log.i("OnAkeEnd", (new StringBuilder()).append("Java--call from native: OnAkeEnd, nResult = ").append(i).toString());
        if (m_listener != null)
        {
            m_listener.OnDtcpSinkAkeEnd(i);
        }
    }

    public int OpenStream(int i, int j, String s)
    {
        int k;
        if (m_hdtcpSink == 0)
        {
            k = -1;
        } else
        {
            if (i == 0)
            {
                k = JNI_DtcpSink_StreamOpenAES(m_hdtcpSink, j, s);
            } else
            if (i == 1)
            {
                k = JNI_DtcpSink_StreamOpenCPRM(m_hdtcpSink, j, s);
            } else
            {
                return -4;
            }
            if (k != 0)
            {
                return k;
            }
        }
        return k;
    }

    public void Recycle()
    {
        if (m_hdtcpSink == 0)
        {
            return;
        } else
        {
            JNI_DtcpSink_Cleanup(m_hdtcpSink);
            m_hdtcpSink = 0;
            return;
        }
    }

    public int WriteStream(byte abyte0[], int i, int ai[])
    {
        int j;
        if (m_hdtcpSink == 0)
        {
            j = -1;
        } else
        {
            j = JNI_DtcpSink_StreamWrite(m_hdtcpSink, abyte0, i, ai);
            if (j != 0)
            {
                return j;
            }
        }
        return j;
    }

    protected void finalize()
        throws Throwable
    {
        super.finalize();
        Recycle();
    }
}
