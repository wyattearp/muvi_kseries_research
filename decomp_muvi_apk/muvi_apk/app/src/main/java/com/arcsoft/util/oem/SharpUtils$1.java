// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.oem;

import java.io.File;
import java.io.FilenameFilter;

// Referenced classes of package com.arcsoft.util.oem:
//            SharpUtils

static final class val.cmpname
    implements FilenameFilter
{

    final String val$cmpname;

    public boolean accept(File file, String s)
    {
        int i = s.lastIndexOf('.');
        if (i >= 0)
        {
            String s1 = s.substring(i).toLowerCase();
            if (s.substring(0, i).equals(val$cmpname) && s1.equals(".mpo"))
            {
                return true;
            }
        }
        return false;
    }

    (String s)
    {
        val$cmpname = s;
        super();
    }
}
