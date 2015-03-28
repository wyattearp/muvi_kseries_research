// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;

public interface StreamSocket
{

    public abstract void close()
        throws IOException;

    public abstract String getDetail();

    public abstract InetAddress getInetAddress();

    public abstract InputStream getInputStream()
        throws IOException;

    public abstract InetAddress getLocalAddress();

    public abstract int getLocalPort();

    public abstract OutputStream getOutputStream()
        throws IOException;

    public abstract int getReceiveBufferSize()
        throws SocketException;

    public abstract String getRemoteHost();

    public abstract int getSendBufferSize()
        throws SocketException;

    public abstract int getSoTimeout()
        throws SocketException;

    public abstract boolean isConnected();

    public abstract void setReceiveBufferSize(int i)
        throws SocketException;

    public abstract void setRemoteHost(String s);

    public abstract void setSendBufferSize(int i)
        throws SocketException;

    public abstract void setSoTimeout(int i)
        throws SocketException;
}
