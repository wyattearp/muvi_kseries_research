// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;

import android.os.Bundle;
import java.util.Observable;
import java.util.Observer;

// Referenced classes of package com.arcsoft.videotrim.Utils:
//            MediaFileObserver

class addObserver extends Observable
{

    final MediaFileObserver this$0;

    public int destroy()
    {
        deleteObservers();
        return 0;
    }

    public void onFileChange(Bundle bundle)
    {
        setChanged();
        notifyObservers(bundle);
    }

    public (Observer observer)
    {
        this$0 = MediaFileObserver.this;
        super();
        addObserver(observer);
    }
}
