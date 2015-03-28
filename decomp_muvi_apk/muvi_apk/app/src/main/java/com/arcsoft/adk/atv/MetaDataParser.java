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

public class MetaDataParser
{
    public static class MetaDataItem
    {

        MetaDataItemContent stContent;
        String strID;
        String strParentID;
        String strRestricted;

        public static MetaDataItem parse(String s)
        {
            if (s == null)
            {
                return null;
            }
            InputSource inputsource = new InputSource(new StringReader(s));
            DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
            MetaDataItem metadataitem;
            try
            {
                Document document = documentbuilderfactory.newDocumentBuilder().parse(inputsource);
                document.normalize();
                metadataitem = parse(document);
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
            return metadataitem;
        }

        static MetaDataItem parse(Document document)
        {
            NodeList nodelist = document.getElementsByTagName("item");
            if (nodelist != null && nodelist.getLength() > 1)
            {
                return null;
            }
            MetaDataItem metadataitem = new MetaDataItem();
            NamedNodeMap namednodemap = nodelist.item(0).getAttributes();
            if (namednodemap == null)
            {
                return null;
            }
            Node node = namednodemap.getNamedItem("id");
            if (node != null)
            {
                metadataitem.strID = node.getNodeValue();
            }
            Node node1 = namednodemap.getNamedItem("parentID");
            if (node1 != null)
            {
                metadataitem.strParentID = node1.getNodeValue();
            }
            Node node2 = namednodemap.getNamedItem("restricted");
            if (node2 != null)
            {
                metadataitem.strRestricted = node2.getNodeValue();
            }
            metadataitem.stContent = MetaDataItemContent.parse(document);
            return metadataitem;
        }

        public MetaDataItem()
        {
        }
    }

    public static class MetaDataItemContent
    {

        MetaDataRes stRes[];
        String strDcDate;
        String strDcTitle;
        String strUpnpChannelNr;
        String strUpnpClass;
        String strUpnpGenre;

        static MetaDataItemContent parse(Document document)
        {
            MetaDataItemContent metadataitemcontent = new MetaDataItemContent();
            metadataitemcontent.strDcTitle = MetaDataParser.getTagValue(document, "dc:title");
            metadataitemcontent.strDcDate = MetaDataParser.getTagValue(document, "dc:date");
            metadataitemcontent.strUpnpGenre = MetaDataParser.getTagValue(document, "upnp:genre");
            metadataitemcontent.strUpnpChannelNr = MetaDataParser.getTagValue(document, "upnp:channelNr");
            metadataitemcontent.strUpnpClass = MetaDataParser.getTagValue(document, "upnp:class");
            metadataitemcontent.stRes = MetaDataRes.parse(document);
            return metadataitemcontent;
        }

        public MetaDataItemContent()
        {
        }
    }

    public static class MetaDataRes
    {

        public String strBitrate;
        public String strContent;
        public String strDidl;
        public String strDuration;
        public String strProtocolInfo;
        public String strResolution;
        public String strSize;

        public static MetaDataRes[] parse(String s)
        {
            if (s == null)
            {
                return null;
            }
            InputSource inputsource = new InputSource(new StringReader(s));
            DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
            MetaDataRes ametadatares[];
            try
            {
                Document document = documentbuilderfactory.newDocumentBuilder().parse(inputsource);
                document.normalize();
                ametadatares = parse(document);
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
            return ametadatares;
        }

        static MetaDataRes[] parse(Document document)
        {
            NodeList nodelist = document.getElementsByTagName("res");
            int i;
            MetaDataRes ametadatares[];
            if (nodelist != null)
            {
                i = nodelist.getLength();
            } else
            {
                i = 0;
            }
            if (i <= 0)
            {
                ametadatares = null;
            } else
            {
                ametadatares = new MetaDataRes[i];
                int j = 0;
                while (j < ametadatares.length) 
                {
                    NamedNodeMap namednodemap = nodelist.item(j).getAttributes();
                    if (namednodemap != null)
                    {
                        ametadatares[j] = new MetaDataRes();
                        Node node = namednodemap.getNamedItem("protocolInfo");
                        if (node != null)
                        {
                            ametadatares[j].strProtocolInfo = node.getNodeValue();
                        }
                        Node node1 = namednodemap.getNamedItem("resolution");
                        if (node1 != null)
                        {
                            ametadatares[j].strResolution = node1.getNodeValue();
                        }
                        Node node2 = namednodemap.getNamedItem("duration");
                        if (node2 != null)
                        {
                            ametadatares[j].strDuration = node2.getNodeValue();
                        }
                        Node node3 = namednodemap.getNamedItem("bitrate");
                        if (node3 != null)
                        {
                            ametadatares[j].strBitrate = node3.getNodeValue();
                        }
                        Node node4 = namednodemap.getNamedItem("size");
                        if (node4 != null)
                        {
                            ametadatares[j].strSize = node4.getNodeValue();
                        }
                        Node node5 = nodelist.item(j).getFirstChild();
                        if (node5 != null)
                        {
                            ametadatares[j].strContent = node5.getNodeValue();
                        }
                    }
                    j++;
                }
            }
            return ametadatares;
        }

        public MetaDataRes()
        {
        }
    }


    private static final String PROP_ITEM_ID = "id";
    private static final String PROP_ITEM_PARENT_ID = "parentID";
    private static final String PROP_ITEM_RESTRICTED = "restricted";
    private static final String PROP_RES_BITRATE = "bitrate";
    private static final String PROP_RES_DURATION = "duration";
    private static final String PROP_RES_PROTOCOL_INFO = "protocolInfo";
    private static final String PROP_RES_RESOLUTION = "resolution";
    private static final String PROP_RES_SIZE = "size";
    private static final String TAG = "MetaDataParser";
    private static final String TAG_DC_DATE = "dc:date";
    private static final String TAG_DC_TITLE = "dc:title";
    private static final String TAG_ITEM = "item";
    private static final String TAG_RES = "res";
    private static final String TAG_UPNP_CHANNELNR = "upnp:channelNr";
    private static final String TAG_UPNP_CLASS = "upnp:class";
    private static final String TAG_UPNP_GENRE = "upnp:genre";

    public MetaDataParser()
    {
    }

    private static String getTagValue(Document document, String s)
    {
        NodeList nodelist = document.getElementsByTagName(s);
        Node node;
        if (nodelist != null && nodelist.getLength() == 1)
        {
            if ((node = nodelist.item(0).getFirstChild()) != null)
            {
                return node.getNodeValue();
            }
        }
        return null;
    }

}
