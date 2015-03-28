// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class SalixHttpUpdater
{
    public static class HttpFileEntity
    {

        public int mCreateTime;
        public String mFileName;
        public long mSize;
        public String mUrl;
        public boolean mbIsVideo;

        public String toString()
        {
            return (new StringBuilder()).append(mFileName).append(" ").append(mUrl).append(" ").append(mCreateTime).append(" ").append(mSize).append(" ").append(mbIsVideo).toString();
        }

        public HttpFileEntity()
        {
            mFileName = "";
            mUrl = "";
            mCreateTime = 0;
            mSize = 0L;
            mbIsVideo = false;
        }
    }


    private static boolean DUMP_FILE = false;
    private static boolean IS_SDA = true;
    private static String SALIX_MEDIA_URL_SDA = "http://10.10.1.1/:sda1:.xml:Sub";
    private static String SALIX_MEDIA_URL_SDB = "http://10.10.1.1/:sdb1:.xml:Sub";
    private static boolean UNIT_TEST = false;

    public SalixHttpUpdater()
    {
    }

    public static String convertStreamToString(InputStream inputstream)
    {
        BufferedReader bufferedreader;
        StringBuilder stringbuilder;
        if (inputstream == null)
        {
            return null;
        }
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        stringbuilder = new StringBuilder();
_L2:
        String s = bufferedreader.readLine();
        if (s == null)
        {
            break; /* Loop/switch isn't completed */
        }
        stringbuilder.append((new StringBuilder()).append(s).append("\n").toString());
        IOException ioexception;
        if (true) goto _L2; else goto _L1
_L4:
        return stringbuilder.toString();
_L1:
        try
        {
            inputstream.close();
            bufferedreader.close();
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static boolean dumpFile(String s, String s1)
    {
        if (s1 == null || s == null)
        {
            return false;
        }
        File file;
        FileOutputStream fileoutputstream;
        try
        {
            for (file = new File(s1); file.exists(); file = new File(s1))
            {
                s1 = (new StringBuilder()).append(s1).append("0").toString();
            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
        file.createNewFile();
_L1:
        fileoutputstream = new FileOutputStream(file, true);
        fileoutputstream.write(s.getBytes());
        fileoutputstream.flush();
        fileoutputstream.close();
        return true;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
          goto _L1
    }

    public static List getSalixMediaList(String s)
        throws Exception
    {
        HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL(s)).openConnection();
        httpurlconnection.setConnectTimeout(30000);
        httpurlconnection.setReadTimeout(30000);
        httpurlconnection.setRequestProperty("Connection", "Keep-Alive");
        httpurlconnection.setRequestMethod("GET");
        httpurlconnection.connect();
        return parseXML(convertStreamToString(httpurlconnection.getInputStream()));
    }

    private static List parseXML(String s)
        throws Exception
    {
        ArrayList arraylist;
        HttpFileEntity httpfileentity;
        XmlPullParser xmlpullparser;
        int i;
        if (DUMP_FILE)
        {
            dumpFile(s, "/sdcard/salix-dump-xml");
        }
        arraylist = null;
        httpfileentity = null;
        XmlPullParserFactory xmlpullparserfactory = XmlPullParserFactory.newInstance();
        xmlpullparserfactory.setNamespaceAware(true);
        xmlpullparser = xmlpullparserfactory.newPullParser();
        xmlpullparser.setInput(new StringReader(s));
        i = xmlpullparser.getEventType();
_L6:
        if (i == 1)
        {
            break MISSING_BLOCK_LABEL_413;
        }
        i;
        JVM INSTR tableswitch 0 3: default 96
    //                   0 108
    //                   1 96
    //                   2 119
    //                   3 360;
           goto _L1 _L2 _L1 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_360;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L7:
        i = xmlpullparser.next();
        if (true) goto _L6; else goto _L5
_L5:
        arraylist = new ArrayList();
          goto _L7
_L3:
        String s1 = xmlpullparser.getName();
        if ("Picture".equals(s1))
        {
            httpfileentity = new HttpFileEntity();
            httpfileentity.mbIsVideo = false;
        }
        if ("Video".equals(s1))
        {
            httpfileentity = new HttpFileEntity();
            httpfileentity.mbIsVideo = true;
        }
        if (httpfileentity != null)
        {
            if ("NAME".equals(s1))
            {
                xmlpullparser.next();
                httpfileentity.mFileName = xmlpullparser.getText();
                int j = httpfileentity.mFileName.indexOf('.');
                httpfileentity.mFileName = httpfileentity.mFileName.substring(0, j);
            }
            if ("FPATH".equals(s1))
            {
                xmlpullparser.next();
                httpfileentity.mUrl = (new StringBuilder()).append("http://10.10.1.1/").append(xmlpullparser.getText()).toString();
            }
            if ("SIZE".equals(s1))
            {
                xmlpullparser.next();
                httpfileentity.mSize = Long.valueOf(xmlpullparser.getText()).longValue();
            }
            if ("TIMECODE".equals(s1))
            {
                xmlpullparser.next();
                xmlpullparser.getText();
                httpfileentity.mCreateTime = Integer.valueOf(xmlpullparser.getText()).intValue();
            }
        }
          goto _L7
        if ("Picture".equals(xmlpullparser.getName()))
        {
            arraylist.add(httpfileentity);
            httpfileentity = null;
        }
        if ("Video".equals(xmlpullparser.getName()))
        {
            arraylist.add(httpfileentity);
            httpfileentity = null;
        }
          goto _L7
        return arraylist;
    }

    public static void setIsSDA(boolean flag)
    {
        IS_SDA = flag;
    }

    public static List testParseXML()
    {
        File file = new File("/sdcard/test_salix.xml");
        FileInputStream fileinputstream = new FileInputStream(file);
        List list = parseXML(convertStreamToString(fileinputstream));
        return list;
        Exception exception;
        exception;
_L2:
        exception.printStackTrace();
        return null;
        exception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static List updateContent()
        throws Exception
    {
        if (UNIT_TEST)
        {
            return testParseXML();
        }
        String s;
        if (IS_SDA)
        {
            s = SALIX_MEDIA_URL_SDA;
        } else
        {
            s = SALIX_MEDIA_URL_SDB;
        }
        return getSalixMediaList(s);
    }

}
