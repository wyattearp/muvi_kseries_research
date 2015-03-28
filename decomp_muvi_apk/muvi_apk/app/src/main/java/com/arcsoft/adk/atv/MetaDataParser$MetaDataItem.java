// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

// Referenced classes of package com.arcsoft.adk.atv:
//            MetaDataParser

public static class ontent
{

    ontent stContent;
    String strID;
    String strParentID;
    String strRestricted;

    public static ontent parse(String s)
    {
        if (s == null)
        {
            return null;
        }
        InputSource inputsource = new InputSource(new StringReader(s));
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        ontent ontent;
        try
        {
            Document document = documentbuilderfactory.newDocumentBuilder().parse(inputsource);
            document.normalize();
            ontent = parse(document);
        }
        catch (ParserConfigurationException parserconfigurationexception)
        {
            parserconfigurationexception.printStackTrace();
            return null;
        }
        catch (SAXException saxexception)
        {
            saxexception.printStackTrace();
            return null;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return null;
        }
        return ontent;
    }

    static rintStackTrace parse(Document document)
    {
        NodeList nodelist = document.getElementsByTagName("item");
        if (nodelist != null && nodelist.getLength() > 1)
        {
            return null;
        }
        rintStackTrace rintstacktrace = new <init>();
        NamedNodeMap namednodemap = nodelist.item(0).getAttributes();
        if (namednodemap == null)
        {
            return null;
        }
        Node node = namednodemap.getNamedItem("id");
        if (node != null)
        {
            rintstacktrace.strID = node.getNodeValue();
        }
        Node node1 = namednodemap.getNamedItem("parentID");
        if (node1 != null)
        {
            rintstacktrace.strParentID = node1.getNodeValue();
        }
        Node node2 = namednodemap.getNamedItem("restricted");
        if (node2 != null)
        {
            rintstacktrace.strRestricted = node2.getNodeValue();
        }
        rintstacktrace.stContent = ontent.parse(document);
        return rintstacktrace;
    }

    public ontent()
    {
    }
}
