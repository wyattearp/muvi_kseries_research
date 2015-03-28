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

public static class 
{

    public String strBitrate;
    public String strContent;
    public String strDidl;
    public String strDuration;
    public String strProtocolInfo;
    public String strResolution;
    public String strSize;

    public static [] parse(String s)
    {
        if (s == null)
        {
            return null;
        }
        InputSource inputsource = new InputSource(new StringReader(s));
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
         a[];
        try
        {
            Document document = documentbuilderfactory.newDocumentBuilder().parse(inputsource);
            document.normalize();
            a = parse(document);
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
        return a;
    }

    static printStackTrace[] parse(Document document)
    {
        NodeList nodelist = document.getElementsByTagName("res");
        int i;
        printStackTrace aprintstacktrace[];
        if (nodelist != null)
        {
            i = nodelist.getLength();
        } else
        {
            i = 0;
        }
        if (i <= 0)
        {
            aprintstacktrace = null;
        } else
        {
            aprintstacktrace = new printStackTrace[i];
            int j = 0;
            while (j < aprintstacktrace.length) 
            {
                NamedNodeMap namednodemap = nodelist.item(j).getAttributes();
                if (namednodemap != null)
                {
                    aprintstacktrace[j] = new <init>();
                    Node node = namednodemap.getNamedItem("protocolInfo");
                    if (node != null)
                    {
                        aprintstacktrace[j].strProtocolInfo = node.getNodeValue();
                    }
                    Node node1 = namednodemap.getNamedItem("resolution");
                    if (node1 != null)
                    {
                        aprintstacktrace[j].strResolution = node1.getNodeValue();
                    }
                    Node node2 = namednodemap.getNamedItem("duration");
                    if (node2 != null)
                    {
                        aprintstacktrace[j].strDuration = node2.getNodeValue();
                    }
                    Node node3 = namednodemap.getNamedItem("bitrate");
                    if (node3 != null)
                    {
                        aprintstacktrace[j].strBitrate = node3.getNodeValue();
                    }
                    Node node4 = namednodemap.getNamedItem("size");
                    if (node4 != null)
                    {
                        aprintstacktrace[j].strSize = node4.getNodeValue();
                    }
                    Node node5 = nodelist.item(j).getFirstChild();
                    if (node5 != null)
                    {
                        aprintstacktrace[j].strContent = node5.getNodeValue();
                    }
                }
                j++;
            }
        }
        return aprintstacktrace;
    }

    public ()
    {
    }
}
