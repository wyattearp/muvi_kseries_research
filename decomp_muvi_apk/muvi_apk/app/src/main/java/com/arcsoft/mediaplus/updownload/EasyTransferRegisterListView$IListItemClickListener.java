// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            EasyTransferRegisterListView

public static interface 
{

    public abstract void onItemClick(AdapterView adapterview, View view, int i, long l);

    public abstract boolean onItemLongClick(AdapterView adapterview, View view, int i, long l);
}
