// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusListView

class this._cls0
    implements android.widget.ngClickListener
{

    final MediaPlusListView this$0;

    public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
    {
        if (i == 65535)
        {
            return true;
        }
        if (mOpListener != null)
        {
            mOpListener.OnItemLongPress(i);
        }
        return false;
    }

    ew.IListOpListener()
    {
        this$0 = MediaPlusListView.this;
        super();
    }
}
