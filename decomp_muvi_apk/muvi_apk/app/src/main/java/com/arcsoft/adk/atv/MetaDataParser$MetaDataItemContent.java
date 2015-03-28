// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import org.w3c.dom.Document;

// Referenced classes of package com.arcsoft.adk.atv:
//            MetaDataParser

public static class 
{

    stRes stRes[];
    String strDcDate;
    String strDcTitle;
    String strUpnpChannelNr;
    String strUpnpClass;
    String strUpnpGenre;

    static  parse(Document document)
    {
          = new <init>();
        .strDcTitle = MetaDataParser.access$000(document, "dc:title");
        .strDcDate = MetaDataParser.access$000(document, "dc:date");
        .strUpnpGenre = MetaDataParser.access$000(document, "upnp:genre");
        .strUpnpChannelNr = MetaDataParser.access$000(document, "upnp:channelNr");
        .strUpnpClass = MetaDataParser.access$000(document, "upnp:class");
        .stRes = stRes(document);
        return ;
    }

    public ()
    {
    }
}
