// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream;

import java.io.ByteArrayOutputStream;

class ResizableByteArrayOutputStream extends ByteArrayOutputStream
{

    public ResizableByteArrayOutputStream()
    {
    }

    public void resize(int i)
    {
        count = i;
    }
}
