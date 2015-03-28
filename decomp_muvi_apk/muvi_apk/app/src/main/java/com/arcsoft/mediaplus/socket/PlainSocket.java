// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.socket;

import com.arcsoft.util.debug.Log;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

// Referenced classes of package com.arcsoft.mediaplus.socket:
//            StreamSocket

public class PlainSocket extends Socket
    implements StreamSocket
{

    protected String remoteHostname;

    public PlainSocket()
    {
    }

    public PlainSocket(String s, int i)
        throws IOException
    {
        super(s, i);
    }

    public PlainSocket(InetAddress inetaddress, int i)
        throws IOException
    {
        super(inetaddress, i);
    }

    public static PlainSocket createPlainSocket(String s, int i, int j)
        throws IOException
    {
        PlainSocket plainsocket = new PlainSocket();
        plainsocket.connect(new InetSocketAddress(s, i), j);
        return plainsocket;
    }

    public static PlainSocket createPlainSocket(InetAddress inetaddress, int i, int j)
        throws IOException
    {
        InetSocketAddress inetsocketaddress = new InetSocketAddress(inetaddress, i);
        Log.e("testP", (new StringBuilder()).append("addr:").append(inetsocketaddress.toString()).toString());
        PlainSocket plainsocket = new PlainSocket();
        plainsocket.connect(inetsocketaddress, j);
        return plainsocket;
    }

    public String getDetail()
    {
        return toString();
    }

    public String getRemoteHost()
    {
        return remoteHostname;
    }

    public void setRemoteHost(String s)
    {
        remoteHostname = s;
    }
}
