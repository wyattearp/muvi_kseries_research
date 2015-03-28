// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import java.io.File;
import java.io.FilenameFilter;

// Referenced classes of package com.arcsoft.util.tool:
//            CachePathManager

class this._cls1
    implements FilenameFilter
{

    final this._cls1 this$1;

    public boolean accept(File file, String s)
    {
        return s != null && s.endsWith("_delete");
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
