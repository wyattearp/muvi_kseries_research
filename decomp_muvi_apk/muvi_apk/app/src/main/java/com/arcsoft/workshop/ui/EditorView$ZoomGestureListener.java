// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;


// Referenced classes of package com.arcsoft.workshop.ui:
//            EditorView, MultiTouchDetector

private class <init>
    implements Listener
{

    final EditorView this$0;

    public boolean OnMultiTouch(MultiTouchDetector multitouchdetector)
    {
        boolean flag = EditorView.access$600(EditorView.this);
        boolean flag1 = false;
        if (flag)
        {
            com.arcsoft.workshop.Listener listener = EditorView.access$200(EditorView.this);
            flag1 = false;
            if (listener != null)
            {
                float f = multitouchdetector.getPreviousSpan();
                float f1 = multitouchdetector.getCurrentSpan();
                if ((int)f == 0)
                {
                    f = f1;
                }
                int i = EditorView.access$200(EditorView.this).OnZoom(f1 / f);
                flag1 = false;
                if (i == 0)
                {
                    flag1 = true;
                }
            }
        }
        return flag1;
    }

    public boolean OnMultiTouchBegin(MultiTouchDetector multitouchdetector)
    {
        if (EditorView.access$200(EditorView.this) != null)
        {
            if (EditorView.access$300(EditorView.this))
            {
                EditorView.access$302(EditorView.this, false);
                if (EditorView.access$400(EditorView.this))
                {
                    EditorView.access$500(EditorView.this).OnPanEnd();
                    EditorView.access$402(EditorView.this, false);
                }
            }
            if (EditorView.access$200(EditorView.this).OnZoomBegin() == 0)
            {
                EditorView.access$602(EditorView.this, true);
                return true;
            } else
            {
                return false;
            }
        } else
        {
            return false;
        }
    }

    public void OnMultiTouchEnd(MultiTouchDetector multitouchdetector)
    {
        if (EditorView.access$600(EditorView.this))
        {
            EditorView.access$602(EditorView.this, false);
            if (EditorView.access$200(EditorView.this) != null)
            {
                EditorView.access$200(EditorView.this).OnZoomEnd();
            }
        }
    }

    private ener()
    {
        this$0 = EditorView.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
