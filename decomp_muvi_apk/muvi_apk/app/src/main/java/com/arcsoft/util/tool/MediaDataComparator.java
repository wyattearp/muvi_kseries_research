// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import com.arcsoft.mediaplus.datasource.MediaItem;
import java.util.Comparator;

// Referenced classes of package com.arcsoft.util.tool:
//            CharConverter

public class MediaDataComparator
    implements Comparator
{

    public MediaDataComparator()
    {
    }

    public int compare(Object obj, Object obj1)
    {
        if (obj == null || obj1 == null)
        {
            return 0;
        }
        MediaItem mediaitem = (MediaItem)obj;
        MediaItem mediaitem1 = (MediaItem)obj1;
        String s = CharConverter.ConverterString(mediaitem.title);
        String s1 = CharConverter.ConverterString(mediaitem1.title);
        if (s == null || s1 == null)
        {
            return 0;
        }
        char ac[] = s.toCharArray();
        char ac1[] = s1.toCharArray();
        int i;
        for (i = 0; i < s.length() && i < s1.length(); i++)
        {
            int j = CharConverter.GetFirstCharType(s.substring(i));
            int k = CharConverter.GetFirstCharType(s1.substring(i));
            if (j < k)
            {
                return -1;
            }
            if (j > k)
            {
                return 1;
            }
            if (ac[i] < ac1[i])
            {
                return -1;
            }
            if (ac[i] > ac1[i])
            {
                return 1;
            }
            if (i == 0)
            {
                return 0;
            }
        }

        if (i == ac.length && i != ac1.length)
        {
            return -1;
        }
        return i == ac.length || i != ac1.length ? 0 : 1;
    }
}
