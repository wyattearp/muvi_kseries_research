// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import powermobia.ve.platform.MAndroidBitmapFactory;
import powermobia.ve.utils.MBitmap;

public class ThumbManagerList
{
    public class ThumbLinkList
    {

        ThumbLinkList mNext;
        ThumbLinkList mPrevious;
        Bitmap mThumbBmp;
        boolean mbDecoded;
        int midentifier;
        final ThumbManagerList this$0;

        ThumbLinkList()
        {
            this$0 = ThumbManagerList.this;
            super();
            mThumbBmp = null;
            midentifier = -1;
            mbDecoded = false;
        }

        ThumbLinkList(ThumbLinkList thumblinklist, ThumbLinkList thumblinklist1, Bitmap bitmap, int i)
        {
            this$0 = ThumbManagerList.this;
            super();
            mThumbBmp = null;
            midentifier = -1;
            mbDecoded = false;
            mPrevious = thumblinklist;
            mNext = thumblinklist1;
            mThumbBmp = bitmap;
            midentifier = i;
        }
    }


    public int m_AdditionalData;
    private int m_IdentifierStep;
    private final ThumbLinkList m_StartThumbLink;
    public boolean m_UseNewMemory;
    private boolean m_bResetedAll;
    private final android.graphics.Bitmap.Config m_eBitmapConfig;
    private int m_iLastIdentifier;
    private int m_iSize;
    private final int m_ibmpHeight;
    private final int m_ibmpWidth;
    public int m_identifierApproximate;

    public ThumbManagerList(int i, int j)
    {
        m_eBitmapConfig = android.graphics.Bitmap.Config.RGB_565;
        m_bResetedAll = false;
        m_IdentifierStep = 1;
        m_AdditionalData = -1;
        m_identifierApproximate = 2;
        m_UseNewMemory = false;
        m_iLastIdentifier = -1;
        m_bResetedAll = false;
        ThumbLinkList thumblinklist = new ThumbLinkList();
        thumblinklist.mNext = thumblinklist;
        thumblinklist.mPrevious = thumblinklist;
        m_ibmpWidth = i;
        m_ibmpHeight = j;
        m_iSize = 0;
        m_StartThumbLink = thumblinklist;
    }

    public void RecycleAllBitmap()
    {
        ThumbLinkList thumblinklist = m_StartThumbLink.mNext;
        do
        {
            if (thumblinklist == null)
            {
                break;
            }
            if (thumblinklist.mThumbBmp != null && !thumblinklist.mThumbBmp.isRecycled())
            {
                thumblinklist.mThumbBmp.recycle();
                thumblinklist.mThumbBmp = null;
            }
            thumblinklist = thumblinklist.mNext;
        } while (thumblinklist != m_StartThumbLink);
    }

    public void clearAllIdentifier()
    {
        ThumbLinkList thumblinklist = m_StartThumbLink;
        do
        {
            if (thumblinklist == null)
            {
                break;
            }
            thumblinklist.midentifier = -1;
            thumblinklist = thumblinklist.mNext;
        } while (thumblinklist != m_StartThumbLink);
    }

    public void deleteIdentifier(int i)
    {
        ThumbLinkList thumblinklist;
        ThumbLinkList thumblinklist1;
        ThumbLinkList thumblinklist2;
        thumblinklist = find(i);
        if (thumblinklist == null)
        {
            return;
        }
        if (i == m_iLastIdentifier)
        {
            m_iLastIdentifier = m_iLastIdentifier - m_IdentifierStep;
            thumblinklist.mbDecoded = false;
            thumblinklist.midentifier = -1;
            thumblinklist.mThumbBmp.eraseColor(0);
            return;
        }
        thumblinklist1 = thumblinklist.mNext;
        thumblinklist2 = thumblinklist;
_L6:
        if (thumblinklist1 == null) goto _L2; else goto _L1
_L1:
        if (thumblinklist1.midentifier > m_iLastIdentifier || thumblinklist1.midentifier <= i) goto _L4; else goto _L3
_L3:
        thumblinklist2.mbDecoded = thumblinklist1.mbDecoded;
        Canvas canvas = new Canvas(thumblinklist2.mThumbBmp);
        canvas.drawBitmap(thumblinklist1.mThumbBmp, 0.0F, 0.0F, null);
        canvas.save(31);
        if (thumblinklist1.midentifier != m_iLastIdentifier) goto _L4; else goto _L5
_L5:
        thumblinklist1.mbDecoded = false;
        thumblinklist1.midentifier = -1;
        thumblinklist1.mThumbBmp.eraseColor(0);
_L2:
        m_iLastIdentifier = m_iLastIdentifier - m_IdentifierStep;
        return;
_L4:
        thumblinklist2 = thumblinklist1;
        thumblinklist1 = thumblinklist1.mNext;
        if (thumblinklist1 != thumblinklist) goto _L6; else goto _L2
    }

    public ThumbLinkList find(int i)
    {
        ThumbLinkList thumblinklist = m_StartThumbLink.mNext;
        do
        {
            if (thumblinklist == null)
            {
                break;
            }
            if (Math.abs(thumblinklist.midentifier - i) <= m_identifierApproximate && thumblinklist.midentifier >= 0)
            {
                return thumblinklist;
            }
            thumblinklist = thumblinklist.mNext;
        } while (thumblinklist != m_StartThumbLink);
        return null;
    }

    public int getCurDecodedIdentifier()
    {
        ThumbLinkList thumblinklist = m_StartThumbLink.mNext;
        do
        {
            if (thumblinklist == null)
            {
                break;
            }
            if (!thumblinklist.mbDecoded)
            {
                return thumblinklist.midentifier;
            }
            thumblinklist = thumblinklist.mNext;
        } while (thumblinklist != m_StartThumbLink);
        return -1;
    }

    public int getSize()
    {
        return m_iSize;
    }

    public Bitmap getThumbBitmap(int i)
    {
        ThumbLinkList thumblinklist = m_StartThumbLink.mNext;
        do
        {
            if (thumblinklist == null)
            {
                break;
            }
            if (thumblinklist.midentifier >= 0 && Math.abs(thumblinklist.midentifier - i) <= m_identifierApproximate && thumblinklist.mbDecoded && !thumblinklist.mThumbBmp.isRecycled())
            {
                return thumblinklist.mThumbBmp;
            }
            thumblinklist = thumblinklist.mNext;
        } while (thumblinklist != m_StartThumbLink);
        return null;
    }

    public Bitmap getThumbBitmap(ThumbLinkList thumblinklist)
    {
        if (thumblinklist == null || !thumblinklist.mbDecoded || thumblinklist.midentifier < 0)
        {
            return null;
        } else
        {
            return thumblinklist.mThumbBmp;
        }
    }

    public void insert(int i)
    {
        ThumbLinkList thumblinklist = m_StartThumbLink.mNext;
_L3:
        if (thumblinklist != null && thumblinklist.midentifier <= i) goto _L2; else goto _L1
_L1:
        ThumbLinkList thumblinklist1 = new ThumbLinkList();
        thumblinklist1.midentifier = i;
        thumblinklist1.mNext = thumblinklist;
        thumblinklist1.mPrevious = thumblinklist.mPrevious;
        thumblinklist.mPrevious.mNext = thumblinklist1;
        thumblinklist.mPrevious = thumblinklist1;
        try
        {
            thumblinklist1.mThumbBmp = Bitmap.createBitmap(m_ibmpWidth, m_ibmpHeight, m_eBitmapConfig);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            outofmemoryerror.printStackTrace();
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
        }
        m_iSize = 1 + m_iSize;
        return;
_L2:
        thumblinklist = thumblinklist.mNext;
        if (thumblinklist != m_StartThumbLink) goto _L3; else goto _L1
    }

    public void isUseNewMemory(boolean flag)
    {
        m_UseNewMemory = flag;
    }

    public void resetAll(boolean flag)
    {
        m_bResetedAll = true;
        if (flag)
        {
            ThumbLinkList thumblinklist = m_StartThumbLink;
            do
            {
                if (thumblinklist == null)
                {
                    break;
                }
                thumblinklist.mbDecoded = false;
                thumblinklist.mThumbBmp.eraseColor(0);
                thumblinklist = thumblinklist.mNext;
            } while (thumblinklist != m_StartThumbLink);
        }
    }

    public void setDecodedBitmap(int i, Bitmap bitmap)
    {
        ThumbLinkList thumblinklist = m_StartThumbLink.mNext;
        do
        {
label0:
            {
                if (thumblinklist != null)
                {
                    if (thumblinklist.midentifier != i || thumblinklist.mbDecoded || thumblinklist.mThumbBmp.isRecycled())
                    {
                        break label0;
                    }
                    Canvas canvas = new Canvas(thumblinklist.mThumbBmp);
                    canvas.drawBitmap(bitmap, 0.0F, 0.0F, null);
                    canvas.save(31);
                    thumblinklist.mbDecoded = true;
                }
                return;
            }
            thumblinklist = thumblinklist.mNext;
            if (thumblinklist == m_StartThumbLink)
            {
                return;
            }
        } while (true);
    }

    public void setDecodedBitmap(int i, MBitmap mbitmap)
    {
        ThumbLinkList thumblinklist = m_StartThumbLink.mNext;
        do
        {
label0:
            {
                if (thumblinklist != null)
                {
                    if (thumblinklist.midentifier != i || thumblinklist.mbDecoded || thumblinklist.mThumbBmp.isRecycled())
                    {
                        break label0;
                    }
                    MAndroidBitmapFactory.transformMBitmapIntoBitmap(mbitmap, thumblinklist.mThumbBmp);
                    thumblinklist.mbDecoded = true;
                }
                return;
            }
            thumblinklist = thumblinklist.mNext;
            if (thumblinklist == m_StartThumbLink)
            {
                return;
            }
        } while (true);
    }

    public void setIdentifierApproximate(int i)
    {
        m_identifierApproximate = i;
    }

    public void setIdentifierStep(int i)
    {
        m_IdentifierStep = i;
    }
}
