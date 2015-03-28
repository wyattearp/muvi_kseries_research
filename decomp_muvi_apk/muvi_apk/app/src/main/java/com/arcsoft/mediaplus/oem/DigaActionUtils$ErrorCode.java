// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.oem;

import android.content.Context;
import android.widget.Toast;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.oem:
//            DigaActionUtils

public static class _cls9
{

    public static final int ERROR_BROWSE = 5;
    public static final int ERROR_BROWSE_RECORD = 7;
    public static final int ERROR_BUSY = 3;
    public static final int ERROR_CREATE = 6;
    public static final int ERROR_DELETE = 4;
    public static final int ERROR_DISABLE = 8;
    public static final int ERROR_ENABLE = 9;
    public static final int ERROR_LIST_BROWSING = 2;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_SERVER_OFFLINE = 10;
    public static final int ERROR_UNKNOWN = 1;

    public static transient void showDigaActionError(Context context, int i, Object aobj[])
    {
        Log.v("DigaActionUtils", (new StringBuilder()).append("showDigaActionError errorCode = ").append(i).toString());
        if (context != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j;
        switch (i)
        {
        default:
            return;

        case 2: // '\002'
            break; /* Loop/switch isn't completed */

        case 3: // '\003'
            break MISSING_BLOCK_LABEL_117;

        case 1: // '\001'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
            j = 0x7f0b0060;
            break;
        }
_L4:
        if (j > 0)
        {
            Toast.makeText(context, context.getString(j, aobj), 0).show();
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        j = 0x7f0b010e;
          goto _L4
        j = 0x7f0b0110;
          goto _L4
    }

    public _cls9()
    {
    }
}
