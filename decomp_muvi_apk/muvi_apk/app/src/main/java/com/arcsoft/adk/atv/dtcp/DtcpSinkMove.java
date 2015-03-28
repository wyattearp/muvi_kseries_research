// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv.dtcp;

import com.arcsoft.adk.atv.Recycleble;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.adk.atv.dtcp:
//            IDtcpSinkMoveListener

public class DtcpSinkMove
    implements Recycleble
{

    private final String logTAG = "DtcpSinkMove";
    private boolean m_ContentIsUsable;
    private int m_hdtcp;
    private int m_hdtcpSinkMove;
    private IDtcpSinkMoveListener m_listener;

    public DtcpSinkMove(int i, IDtcpSinkMoveListener idtcpsinkmovelistener)
    {
        m_hdtcp = 0;
        m_hdtcpSinkMove = 0;
        m_listener = null;
        m_ContentIsUsable = false;
        m_hdtcp = i;
        m_listener = idtcpsinkmovelistener;
    }

    private static native void JNI_DtcpSinkMove_Cleanup(int i);

    private static native int JNI_DtcpSinkMove_GetKxmLable(int i, byte abyte0[]);

    private static native int JNI_DtcpSinkMove_InitiateAKE(int i, String s, long l, DtcpSinkMove dtcpsinkmove, int ai[]);

    private static native int JNI_DtcpSinkMove_InitiateCommitment(int i);

    private static native void JNI_DtcpSinkMove_StreamClose(int i);

    private static native int JNI_DtcpSinkMove_StreamDelete(int i);

    private static native int JNI_DtcpSinkMove_StreamOpenAES(int i, int j, String s);

    private static native int JNI_DtcpSinkMove_StreamOpenCPRM(int i, int j, String s);

    private static native int JNI_DtcpSinkMove_StreamWrite(int i, byte abyte0[], int j, int ai[]);

    public void CleanUp()
    {
        if (m_hdtcpSinkMove == 0)
        {
            return;
        }
        if (!m_ContentIsUsable)
        {
            JNI_DtcpSinkMove_StreamDelete(m_hdtcpSinkMove);
            JNI_DtcpSinkMove_StreamClose(m_hdtcpSinkMove);
        }
        JNI_DtcpSinkMove_Cleanup(m_hdtcpSinkMove);
        m_hdtcpSinkMove = 0;
    }

    public void CloseStream()
    {
        while (m_hdtcpSinkMove == 0 || m_ContentIsUsable) 
        {
            return;
        }
        JNI_DtcpSinkMove_StreamDelete(m_hdtcpSinkMove);
        JNI_DtcpSinkMove_StreamClose(m_hdtcpSinkMove);
    }

    public int GetKxmLable(byte abyte0[])
    {
        if (m_hdtcpSinkMove == 0)
        {
            return -1;
        } else
        {
            return JNI_DtcpSinkMove_GetKxmLable(m_hdtcpSinkMove, abyte0);
        }
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
        int i = JNI_DtcpSinkMove_InitiateAKE(m_hdtcp, s, l, this, ai);
        if (i != 0)
        {
            Log.e("DtcpSinkMove", "DtcpSinkMove::InitiateAKE == return false;");
            return i;
        } else
        {
            m_hdtcpSinkMove = ai[0];
            return 0;
        }
    }

    public int InitiateCommitment()
    {
        int i;
        if (m_hdtcpSinkMove == 0)
        {
            i = -1;
        } else
        {
            i = JNI_DtcpSinkMove_InitiateCommitment(m_hdtcpSinkMove);
            if (i != 0)
            {
                Log.e("DtcpSinkMove", "DtcpSinkMove::InitiateCommitment == return false;");
                return i;
            }
        }
        return i;
    }

    public void OnAkeEnd(int i)
    {
        Log.i("DtcpSinkMove", (new StringBuilder()).append("Java--call from native: OnAkeEnd, nResult = ").append(i).toString());
        if (m_listener != null)
        {
            m_listener.OnDtcpSinkMoveAkeEnd(i);
        }
    }

    public int OnMakeContentDiscard()
    {
        Log.i("DtcpSinkMove", "Java--call from native: OnMakeContentDiscard");
        if (m_hdtcpSinkMove == 0)
        {
            return -1;
        } else
        {
            JNI_DtcpSinkMove_StreamDelete(m_hdtcpSinkMove);
            return 0;
        }
    }

    public int OnMakeContentUsable()
    {
        Log.i("DtcpSinkMove", "Java--call from native: OnMakeContentUsable");
        if (m_hdtcpSinkMove == 0)
        {
            return -1;
        } else
        {
            JNI_DtcpSinkMove_StreamClose(m_hdtcpSinkMove);
            m_ContentIsUsable = true;
            return 0;
        }
    }

    public void OnMoveEnd(int i)
    {
        Log.i("DtcpSinkMove", (new StringBuilder()).append("Java--call from native: OnMoveEnd, nResult = ").append(i).toString());
        if (m_listener != null)
        {
            m_listener.OnDtcpSinkMoveEnd(i);
        }
    }

    public int OpenStream(int i, int j, String s)
    {
        int k;
        if (m_hdtcpSinkMove == 0)
        {
            k = -1;
        } else
        {
            if (i == 0)
            {
                k = JNI_DtcpSinkMove_StreamOpenAES(m_hdtcpSinkMove, j, s);
            } else
            if (i == 1)
            {
                k = JNI_DtcpSinkMove_StreamOpenCPRM(m_hdtcpSinkMove, j, s);
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
        if (m_hdtcpSinkMove == 0)
        {
            return;
        } else
        {
            JNI_DtcpSinkMove_Cleanup(m_hdtcpSinkMove);
            m_hdtcpSinkMove = 0;
            return;
        }
    }

    public int WriteStream(byte abyte0[], int i, int ai[])
    {
        int j;
        if (m_hdtcpSinkMove == 0)
        {
            j = -1;
        } else
        {
            j = JNI_DtcpSinkMove_StreamWrite(m_hdtcpSinkMove, abyte0, i, ai);
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
