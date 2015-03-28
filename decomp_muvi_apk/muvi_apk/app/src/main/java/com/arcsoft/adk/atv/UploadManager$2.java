// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            UPCPCallback, UploadManager

class this._cls0
    implements UPCPCallback
{

    final UploadManager this$0;

    public void OnUploadResult(int i, int j)
    {
        ploadResultListener aploadresultlistener[] = UploadManager.access$100(UploadManager.this);
        if (aploadresultlistener != null)
        {
            int k = aploadresultlistener.length;
            for (int l = 0; l < k; l++)
            {
                aploadresultlistener[l].OnUploadResult(i, j);
            }

        }
    }

    ploadResultListener()
    {
        this$0 = UploadManager.this;
        super();
    }
}
