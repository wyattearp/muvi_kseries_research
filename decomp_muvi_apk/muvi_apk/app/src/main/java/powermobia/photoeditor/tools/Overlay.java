// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;

import powermobia.photoeditor.EditorEngine;
import powermobia.utils.MBitmap;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;
import powermobia.utils.MStream;

// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class Overlay extends ToolBase
{
    public static class OverlayInfo
    {

        public boolean bHorizFlip;
        public boolean bVertFlip;
        public int iDegree;
        public int iOrgHeight;
        public int iOrgWidth;

        public OverlayInfo()
        {
        }
    }

    public static abstract class TextInfo
    {

        public boolean bHorizFlip;
        public boolean bVertFlip;
        public int iClrTxt;
        public int iDegree;
        public String strText;

        public abstract MBitmap cbDrawText(String s);

        public abstract int cbFreeText();

        public TextInfo()
        {
        }
    }

    public static class VectorTextInfo
    {

        public boolean bAutoLine;
        public String dataBubble;
        public String dataTTF;
        public int iClrBubble;
        public int iClrTxt;
        public int iFontSize;
        public int iFormatBubble;
        public int iFormatTTF;
        public int iLineWidth;
        public int iTextFlag;
        public String strText;

        public VectorTextInfo()
        {
        }
    }


    private static final int DATASOURCE_BITMAP = 3;
    private static final int DATASOURCE_FILE = 1;
    private static final int DATASOURCE_STREAM = 2;
    private static final int TOOLID_OVERLAY = 0x90840332;
    public static final int VTEXT_ALIGN_BOTTOM = 8;
    public static final int VTEXT_ALIGN_CENTER_HORZ = 4;
    public static final int VTEXT_ALIGN_CENTER_VERT = 16;
    public static final int VTEXT_ALIGN_LEFT = 0;
    public static final int VTEXT_ALIGN_RIGHT = 2;
    public static final int VTEXT_ALIGN_TOP = 0;
    public static final int VTEXT_DIRECTION_LEFT_TO_RIGHT = 0;
    public static final int VTEXT_DIRECTION_RIGHT_TO_LEFT = 1;
    public static final int VTEXT_ORDER_ORIGINAL = 64;
    public static final int VTEXT_ORDER_REORDER = 0;
    public static final int VTEXT_SHAPE_CONVERT = 0;
    public static final int VTEXT_SHAPE_ORIGINAL = 32;

    public Overlay()
    {
        mToolId = 0x90840332;
    }

    private native int native_OL_Add(int i, int j, int k, Object obj, int l, int i1, Object obj1, 
            MRect mrect);

    private native int native_OL_AddEx(int i, int j, int k, Object obj, int l, int i1, Object obj1, 
            MRect mrect, int j1, int k1);

    private native int native_OL_AddText(int i, TextInfo textinfo, MRect mrect);

    private native int native_OL_AddVectorText(int i, VectorTextInfo vectortextinfo, MRect mrect);

    private native int native_OL_ChangeZOrder(int i, Integer integer, int j);

    private native int native_OL_Flip(int i, int j, boolean flag);

    private native int native_OL_GetCount(int i, Integer integer);

    private native int native_OL_GetFillColor(int i, int j, Integer integer, Integer integer1);

    private native int native_OL_GetOverlayInfo(int i, int j, OverlayInfo overlayinfo);

    private native int native_OL_GetOverlayRect(int i, int j, MRect mrect);

    private native int native_OL_GetOverlaySize(int i, int j, Integer integer, Integer integer1);

    private native int native_OL_GetTextInfo(int i, int j, TextInfo textinfo, MRect mrect);

    private native int native_OL_GetVectorTextInfo(int i, int j, VectorTextInfo vectortextinfo);

    private native int native_OL_PointToOL(int i, MPoint mpoint, Integer integer);

    private native int native_OL_Remove(int i, int j);

    private native int native_OL_Rotate(int i, int j, int k);

    private native int native_OL_SetFillColor(int i, int j, int k, int l);

    private native int native_OL_SetOverlayRect(int i, int j, MRect mrect);

    private native int native_OL_SetTextInfo(int i, int j, TextInfo textinfo, MRect mrect);

    private native int native_OL_SetVectorTextInfo(int i, int j, VectorTextInfo vectortextinfo);

    public int add(int i, String s, int j, String s1, MRect mrect)
    {
        return native_OL_Add(getNativeEngine(), 1, i, s, 1, j, s1, mrect);
    }

    public int add(int i, String s, int j, String s1, MRect mrect, int k, int l)
    {
        return native_OL_AddEx(getNativeEngine(), 1, i, s, 1, j, s1, mrect, k, l);
    }

    public int add(int i, MStream mstream, int j, MStream mstream1, MRect mrect)
    {
        return native_OL_Add(getNativeEngine(), 2, i, mstream, 2, j, mstream1, mrect);
    }

    public int add(int i, MStream mstream, int j, MStream mstream1, MRect mrect, int k, int l)
    {
        return native_OL_AddEx(getNativeEngine(), 2, i, mstream, 2, j, mstream1, mrect, k, l);
    }

    public int add(MBitmap mbitmap, MBitmap mbitmap1, MRect mrect)
    {
        return native_OL_Add(getNativeEngine(), 3, 0, mbitmap, 3, 0, mbitmap1, mrect);
    }

    public int add(MBitmap mbitmap, MBitmap mbitmap1, MRect mrect, int i, int j)
    {
        return native_OL_AddEx(getNativeEngine(), 3, 0, mbitmap, 3, 0, mbitmap1, mrect, i, j);
    }

    public int addText(TextInfo textinfo, MRect mrect)
    {
        mEngine.setTextInfo(textinfo);
        return native_OL_AddText(getNativeEngine(), textinfo, mrect);
    }

    public int addVectorText(VectorTextInfo vectortextinfo, MRect mrect)
    {
        return native_OL_AddVectorText(getNativeEngine(), vectortextinfo, mrect);
    }

    public int changeZOrder(Integer integer, int i)
    {
        return native_OL_ChangeZOrder(getNativeEngine(), integer, i);
    }

    public int getCount(Integer integer)
    {
        return native_OL_GetCount(getNativeEngine(), integer);
    }

    public int getFillColor(int i, Integer integer, Integer integer1)
    {
        return native_OL_GetFillColor(getNativeEngine(), i, integer, integer1);
    }

    public int getOverlayInfo(int i, OverlayInfo overlayinfo)
    {
        return native_OL_GetOverlayInfo(getNativeEngine(), i, overlayinfo);
    }

    public int getOverlayRect(int i, MRect mrect)
    {
        return native_OL_GetOverlayRect(getNativeEngine(), i, mrect);
    }

    public int getOverlaySize(int i, Integer integer, Integer integer1)
    {
        return native_OL_GetOverlaySize(getNativeEngine(), i, integer, integer1);
    }

    public int getTextInfo(int i, TextInfo textinfo, MRect mrect)
    {
        return native_OL_GetTextInfo(getNativeEngine(), i, textinfo, mrect);
    }

    public int getVectorTextInfo(int i, VectorTextInfo vectortextinfo)
    {
        return native_OL_GetVectorTextInfo(getNativeEngine(), i, vectortextinfo);
    }

    public int hFlip(int i)
    {
        return native_OL_Flip(getNativeEngine(), i, true);
    }

    public int pointToOL(MPoint mpoint, Integer integer)
    {
        return native_OL_PointToOL(getNativeEngine(), mpoint, integer);
    }

    public int remove(int i)
    {
        return native_OL_Remove(getNativeEngine(), i);
    }

    public int rotate(int i, int j)
    {
        return native_OL_Rotate(getNativeEngine(), i, j);
    }

    public int setFillColor(int i, int j, int k)
    {
        return native_OL_SetFillColor(getNativeEngine(), i, j, k);
    }

    public int setOverlayRect(int i, MRect mrect)
    {
        return native_OL_SetOverlayRect(getNativeEngine(), i, mrect);
    }

    public int setTextInfo(int i, TextInfo textinfo, MRect mrect)
    {
        return native_OL_SetTextInfo(getNativeEngine(), i, textinfo, mrect);
    }

    public int setVectorTextInfo(int i, VectorTextInfo vectortextinfo)
    {
        return native_OL_SetVectorTextInfo(getNativeEngine(), i, vectortextinfo);
    }

    public int vFlip(int i)
    {
        return native_OL_Flip(getNativeEngine(), i, false);
    }
}
