// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.net.Uri;
import com.arcsoft.adk.atv.DLNA;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            Property, DMCDataSource

private class <init>
    implements n
{

    final DMCDataSource this$0;

    public n getResourceInfosFromLocalDMS(n n)
    {
        String s = ((Uri)getObjectProp(n.ndex, Property.PROP_URI, null)).getPath();
        n n1 = new nit>(DMCDataSource.this, null);
        n1.source = new com.arcsoft.adk.atv.it>();
        n1.source.trProtocolInfo = DLNA.instance().getFileProtocolInfo(s);
        n1.source.trUri = DLNA.instance().getUri(s);
        n1.tadata = DLNA.instance().getLocalMediaDidlData(s);
        return n1;
    }

    public void recycle()
    {
    }

    private n()
    {
        this$0 = DMCDataSource.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
