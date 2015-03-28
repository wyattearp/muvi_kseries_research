// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Hashtable;

// Referenced classes of package com.arcsoft.videostream:
//            ResizableByteArrayOutputStream

public class StreamSplit
{

    public static final String BOUNDARY_MARKER_PREFIX = "--";
    public static final String BOUNDARY_MARKER_TERM = "--";
    protected DataInputStream m_dis;
    private boolean m_streamEnd;

    public StreamSplit(DataInputStream datainputstream)
    {
        m_dis = datainputstream;
        m_streamEnd = false;
    }

    protected static void addPropValue(String s, Hashtable hashtable)
    {
        int i = s.indexOf(":");
        if (i == -1)
        {
            return;
        } else
        {
            String s1 = s.substring(0, i);
            String s2 = s.substring(i + 1).trim();
            hashtable.put(s1.toLowerCase(), s2);
            return;
        }
    }

    public static Hashtable readHeaders(URLConnection urlconnection)
    {
        Hashtable hashtable = new Hashtable();
        int i = 0;
        do
        {
            String s = urlconnection.getHeaderFieldKey(i);
            if (s == null)
            {
                if (i == 0)
                {
                    i++;
                } else
                {
                    return hashtable;
                }
            } else
            {
                String s1 = urlconnection.getHeaderField(i);
                hashtable.put(s.toLowerCase(), s1);
                i++;
            }
        } while (true);
    }

    public boolean isAtStreamEnd()
    {
        return m_streamEnd;
    }

    public Hashtable readHeaders()
        throws IOException
    {
        Hashtable hashtable;
        boolean flag;
        hashtable = new Hashtable();
        flag = false;
_L6:
        String s = m_dis.readLine();
        if (s != null) goto _L2; else goto _L1
_L1:
        m_streamEnd = true;
_L4:
        return hashtable;
_L2:
        if (!s.equals(""))
        {
            break; /* Loop/switch isn't completed */
        }
        if (flag) goto _L4; else goto _L3
_L3:
        addPropValue(s, hashtable);
        if (true) goto _L6; else goto _L5
_L5:
        flag = true;
          goto _L3
        if (true) goto _L6; else goto _L7
_L7:
    }

    public byte[] readToBoundary(String s)
        throws IOException
    {
        ResizableByteArrayOutputStream resizablebytearrayoutputstream;
        StringBuffer stringbuffer;
        int i;
        int j;
        resizablebytearrayoutputstream = new ResizableByteArrayOutputStream();
        stringbuffer = new StringBuffer();
        i = 0;
        j = 0;
_L6:
        byte byte0 = m_dis.readByte();
        int k;
        if (byte0 != 10 && byte0 != 13)
        {
            break; /* Loop/switch isn't completed */
        }
        k = stringbuffer.toString().indexOf("--");
        if (k == -1) goto _L2; else goto _L1
_L1:
        String s1 = stringbuffer.substring(k);
        if (!s1.startsWith(s)) goto _L2; else goto _L3
_L3:
        if (s1.substring(s.length()).equals("--"))
        {
            m_streamEnd = true;
        }
        j = i + k;
_L4:
        resizablebytearrayoutputstream.close();
        resizablebytearrayoutputstream.resize(j);
        return resizablebytearrayoutputstream.toByteArray();
        EOFException eofexception;
        eofexception;
        m_streamEnd = true;
        if (true) goto _L4; else goto _L2
_L2:
        stringbuffer = new StringBuffer();
        i = j + 1;
_L7:
        j++;
        resizablebytearrayoutputstream.write(byte0);
        if (true) goto _L6; else goto _L5
_L5:
        stringbuffer.append((char)byte0);
          goto _L7
        if (true) goto _L6; else goto _L8
_L8:
    }

    public void skipToBoundary(String s)
        throws IOException
    {
        readToBoundary(s);
    }
}
