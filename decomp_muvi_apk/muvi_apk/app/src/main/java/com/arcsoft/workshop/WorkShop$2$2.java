// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.workshop:
//            WorkShop

class this._cls1
    implements android.content.ce.OnClickListener
{

    final  this$1;

    public void onClick(DialogInterface dialoginterface, int i)
    {
    }

    is._cls0()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class com/arcsoft/workshop/WorkShop$2

/* anonymous class */
    class WorkShop._cls2 extends Handler
    {

        final WorkShop this$0;

        public void handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                return;

            case 0: // '\0'
                String s1 = getResources().getString(0x7f0b0131);
                (new android.app.AlertDialog.Builder(WorkShop.this)).setIcon(0x7f02003e).setTitle(s1).setPositiveButton(0x7f0b012b, new WorkShop._cls2._cls1()).show().setCanceledOnTouchOutside(false);
                return;

            case 1: // '\001'
                String s = getResources().getString(0x7f0b0158);
                (new android.app.AlertDialog.Builder(WorkShop.this)).setIcon(0x7f02003e).setTitle(s).setPositiveButton(0x7f0b012b, new WorkShop._cls2._cls2()).show().setCanceledOnTouchOutside(false);
                return;

            case 10: // '\n'
                if (!((Boolean)message.obj).booleanValue())
                {
                    WorkShop.toastForSave(10, WorkShop.this);
                }
                int _tmp = WorkShop.access$1208(WorkShop.this);
                if (WorkShop.access$1300(WorkShop.this) == null)
                {
                    WorkShop.access$1302(WorkShop.this, new Intent());
                }
                WorkShop.access$1300(WorkShop.this).putExtra("save_count", WorkShop.access$1200(WorkShop.this));
                setResult(-1, WorkShop.access$1300(WorkShop.this));
                return;

            case 11: // '\013'
                WorkShop.toastForSave(11, WorkShop.this);
                return;
            }
        }

            
            {
                this$0 = WorkShop.this;
                super();
            }

        // Unreferenced inner class com/arcsoft/workshop/WorkShop$2$1

/* anonymous class */
        class WorkShop._cls2._cls1
            implements android.content.DialogInterface.OnClickListener
        {

            final WorkShop._cls2 this$1;

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

                    
                    {
                        this$1 = WorkShop._cls2.this;
                        super();
                    }
        }

    }

}
