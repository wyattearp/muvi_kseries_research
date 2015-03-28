// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.provider.BaseColumns;

// Referenced classes of package com.arcsoft.adk.atv:
//            DeviceInfoCache

private final class this._cls0
{
    final class Columns
        implements BaseColumns
    {

        public static final String ICONDATA = "icon_data";
        public static final String ICON_URL = "icon_url";
        public static final String NAME = "name";
        public static final String UDN = "udn";
        final DeviceInfoCache.ServerTable this$1;

        Columns()
        {
            this$1 = DeviceInfoCache.ServerTable.this;
            super();
        }
    }


    static final String TABLE_NAME = "ServerTable";
    final DeviceInfoCache this$0;

    private Columns.this._cls1()
    {
        this$0 = DeviceInfoCache.this;
        super();
    }
}
