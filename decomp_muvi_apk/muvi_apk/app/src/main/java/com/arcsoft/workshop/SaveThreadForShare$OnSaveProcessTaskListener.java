// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import android.net.Uri;

// Referenced classes of package com.arcsoft.workshop:
//            SaveThreadForShare

public static interface 
{

    public abstract void onSaveProcessCancel();

    public abstract void onSaveProcessDone(Uri uri, boolean flag);

    public abstract void onSaveProcessErrorOccured(boolean flag);
}
