// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.view.Surface;
import com.arcsoft.workshop.EditorEngineWrapper;
import com.arcsoft.workshop.GlobalConfig;
import com.arcsoft.workshop.WorkShop;
import com.arcsoft.workshop.ui.UIManagerConsole;
import java.io.File;
import powermobia.utils.MBitmap;
import powermobia.utils.MImgFileInfo;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;
import powermobia.utils.MStream;

// Referenced classes of package com.arcsoft.workshop.utils:
//            FitParams

public class WorkShopUtils
{

    public WorkShopUtils()
    {
    }

    public static void MakeRect1CenterInsideRect2(MRect mrect, MRect mrect1)
    {
        int i = (mrect.left + mrect.right) / 2;
        int j = (mrect.top + mrect.bottom) / 2;
        int k = mrect.right - mrect.left;
        int l = mrect.bottom - mrect.top;
        if (i <= mrect1.left)
        {
            mrect.left = 1 + (mrect1.left - k / 2);
            mrect.right = k + mrect.left;
        }
        if (i >= mrect1.right)
        {
            mrect.left = -1 + (mrect1.right - k / 2);
            mrect.right = k + mrect.left;
        }
        if (j <= mrect1.top)
        {
            mrect.top = 1 + (mrect1.top - l / 2);
            mrect.bottom = l + mrect.top;
        }
        if (j >= mrect1.bottom)
        {
            mrect.top = -1 + (mrect1.bottom - l / 2);
            mrect.bottom = l + mrect.top;
        }
    }

    public static int addFrame(MBitmap mbitmap, MRect mrect, String s, String s1)
    {
        return native_AddFrame(mbitmap, mrect, s, s1);
    }

    public static int back2BestFitMode(WorkShop workshop)
    {
        int i;
        if (workshop == null)
        {
            i = 2;
        } else
        {
            MRect mrect = new MRect();
            mrect.set(0, 0, workshop.getDisplayW() - 0, workshop.getDisplayH());
            MRect mrect1 = new MRect();
            powermobia.photoeditor.EditorEngine.State state = new powermobia.photoeditor.EditorEngine.State();
            EditorEngineWrapper editorenginewrapper = workshop.getEditorEngineWrapper();
            i = editorenginewrapper.getState(state);
            getFitInParamEx(mrect1, state.iImgWidth, state.iImgHeight, mrect.right - mrect.left, mrect.bottom - mrect.top, false);
            mrect1.top = 0 + mrect1.top;
            mrect1.bottom = 0 + mrect1.bottom;
            if (mrect1.width() > 0 && mrect1.height() > 0)
            {
                if (workshop.getUIManagerConsole() != null)
                {
                    workshop.getUIManagerConsole().onChange(2, null, null);
                }
                i = editorenginewrapper.setImgBorderDes(mrect1);
                if (workshop.getUIManagerConsole() != null)
                {
                    workshop.getUIManagerConsole().onChange(3, null, null);
                    return i;
                }
            }
        }
        return i;
    }

    public static int back2BestFitModeForEdit(WorkShop workshop)
    {
        int l;
        if (workshop == null)
        {
            l = 2;
        } else
        {
            int i = workshop.getResources().getInteger(0x7f0a001a);
            int j = workshop.getResources().getInteger(0x7f0a001b);
            int k = workshop.getResources().getInteger(0x7f0a001c);
            MRect mrect = new MRect();
            mrect.set(k, i, workshop.getDisplayW() - k, workshop.getDisplayH() - i - j);
            MRect mrect1 = new MRect();
            powermobia.photoeditor.EditorEngine.State state = new powermobia.photoeditor.EditorEngine.State();
            EditorEngineWrapper editorenginewrapper = workshop.getEditorEngineWrapper();
            l = editorenginewrapper.getState(state);
            getFitInParamEx(mrect1, state.iImgWidth, state.iImgHeight, mrect.right - mrect.left, mrect.bottom - mrect.top, false);
            mrect1.top = j + mrect1.top;
            mrect1.bottom = j + mrect1.bottom;
            mrect1.left = k + mrect1.left;
            mrect1.right = k + mrect1.right;
            if (mrect1.width() > 0 && mrect1.height() > 0)
            {
                if (workshop.getUIManagerConsole() != null)
                {
                    workshop.getUIManagerConsole().onChange(2, null, null);
                }
                l = editorenginewrapper.setImgBorderDes(mrect1);
                if (workshop.getUIManagerConsole() != null)
                {
                    workshop.getUIManagerConsole().onChange(3, null, null);
                    return l;
                }
            }
        }
        return l;
    }

    private static boolean canWrite(String s)
    {
        File file = new File(s);
        file.createNewFile();
        boolean flag;
        flag = false;
        if (true)
        {
            try
            {
                file.delete();
            }
            catch (Exception exception4)
            {
                exception4.printStackTrace();
                flag = false;
            }
        }
_L1:
        Exception exception;
        Exception exception2;
        return !flag;
        exception2;
        flag = true;
        if (!flag)
        {
            try
            {
                file.delete();
            }
            catch (Exception exception3)
            {
                exception3.printStackTrace();
            }
        }
          goto _L1
        exception;
        if (true)
        {
            try
            {
                file.delete();
            }
            catch (Exception exception1)
            {
                exception1.printStackTrace();
            }
        }
        throw exception;
    }

    public static String checkSaveDir()
    {
        String s1;
        if (!Environment.getExternalStorageState().equals("mounted"))
        {
            s1 = null;
        } else
        {
            String s = new String(GlobalConfig.MEDIAPLUS_DIR);
            s1 = new String(GlobalConfig.EDITOR_SAVE_DIR);
            File file = new File(s);
            if (!file.exists() && !file.mkdir())
            {
                return null;
            }
            File file1 = new File(s1);
            if (!file1.exists() && !file1.mkdir())
            {
                return null;
            }
        }
        return s1;
    }

    public static boolean checkSaveDir(String s)
    {
        return canWrite(s);
    }

    public static String checkSaveTmpDir()
    {
        String s1;
        if (!Environment.getExternalStorageState().equals("mounted"))
        {
            s1 = null;
        } else
        {
            String s = new String(GlobalConfig.MEDIAPLUS_DIR);
            s1 = new String(GlobalConfig.EDITOR_SAVE_TMPDIR);
            File file = new File(s);
            if (!file.exists() && !file.mkdir())
            {
                return null;
            }
            File file1 = new File(s1);
            if (file1.exists())
            {
                File afile[] = file1.listFiles();
                int i = afile.length;
                for (int j = 0; j < i; j++)
                {
                    afile[j].delete();
                }

            }
            if (!file1.exists() && !file1.mkdir())
            {
                return null;
            }
        }
        return s1;
    }

    public static int clipBitmap(MBitmap mbitmap, MBitmap mbitmap1, MRect mrect)
    {
        return native_MBitmapClip(mbitmap, mbitmap1, mrect);
    }

    public static int convertValue2(int i, int j, int k, int l, int i1)
    {
        return k + ((i1 - i) * (l - k)) / (j - i);
    }

    public static int copyBitmap(MBitmap mbitmap, MBitmap mbitmap1, MPoint mpoint)
    {
        return native_MBitmapCopy(mbitmap, mbitmap1, mpoint);
    }

    public static int copyBitmap(MBitmap mbitmap, MBitmap mbitmap1, MPoint mpoint, MRect mrect)
    {
        return native_MBitmapCopy(mbitmap, mbitmap1, mpoint, mrect);
    }

    public static int createNativeBitmap()
    {
        return native_NativeBitmapCreate();
    }

    public static int destory_workshoputils()
    {
        return native_Destory();
    }

    public static int destroyNativeBitmap(int i, boolean flag)
    {
        return native_NativeBitmapDestroy(i, flag);
    }

    public static int doDraw(Object obj, MBitmap mbitmap, MRect mrect)
    {
        return native_doDraw(obj, mbitmap, mrect);
    }

    public static int doDraw4Collage(Surface surface, MRect mrect, MRect amrect[], MRect amrect1[], MBitmap ambitmap[])
    {
        return native_DoDraw4Collage(surface, mrect, amrect, amrect1, ambitmap);
    }

    public static int doEffect(MBitmap mbitmap, MBitmap mbitmap1, int i, int ai[])
    {
        return native_UtilsDoEffect(mbitmap, mbitmap1, i, ai);
    }

    public static void getFitInParamEx(MRect mrect, int i, int j, int k, int l, boolean flag)
    {
        FitParams fitparams = new FitParams();
        getFitParamers(i, j, k, l, true, flag, fitparams);
        if (mrect != null)
        {
            int i1 = fitparams.getFitW();
            int j1 = fitparams.getFitH();
            mrect.left = (k - i1) / 2;
            mrect.top = (l - j1) / 2;
            mrect.right = i1 + mrect.left;
            mrect.bottom = j1 + mrect.top;
        }
    }

    public static int[] getFitInSize(int i, int j, int k, int l)
    {
        MRect mrect = new MRect();
        mrect.set(0, 0, i - 0, j);
        MRect mrect1 = new MRect();
        getFitInParamEx(mrect1, k, l, mrect.right - mrect.left, mrect.bottom - mrect.top, false);
        mrect1.top = 0 + mrect1.top;
        mrect1.bottom = 0 + mrect1.bottom;
        int ai[] = new int[2];
        if (mrect1.width() > 0 && mrect1.height() > 0)
        {
            ai[0] = mrect1.width();
            ai[1] = mrect1.height();
            return ai;
        } else
        {
            ai[0] = k;
            ai[1] = l;
            return ai;
        }
    }

    private static void getFitParamers(int i, int j, int k, int l, boolean flag, boolean flag1, FitParams fitparams)
    {
        if (i >= 1 && j >= 1 && k >= 1 && l >= 1 && fitparams != null)
        {
            if (i <= k && j <= l && !flag1)
            {
                fitparams.setFitW(i);
                fitparams.setFitH(j);
                fitparams.setScale(1.0D);
                return;
            }
            int i1 = i * l;
            int j1 = j * k;
            if (flag)
            {
                if (i1 > j1)
                {
                    fitparams.setFitW(k);
                    fitparams.setFitH((j1 + (i >> 1)) / i);
                    fitparams.setScale((double)k / (double)i);
                } else
                {
                    fitparams.setFitW((i1 + (j >> 1)) / j);
                    fitparams.setFitH(l);
                    fitparams.setScale((double)l / (double)j);
                }
            } else
            if (i1 < j1)
            {
                fitparams.setFitW(k);
                fitparams.setFitH((j1 + (i >> 1)) / i);
                fitparams.setScale((double)k / (double)i);
            } else
            {
                fitparams.setFitW((i1 + (j >> 1)) / j);
                fitparams.setFitH(l);
                fitparams.setScale((double)l / (double)j);
            }
            if (fitparams.getFitW() < 0)
            {
                fitparams.setFitW(1);
            }
            if (fitparams.getFitH() < 0)
            {
                fitparams.setFitH(1);
                return;
            }
        }
    }

    public static void getFitParamers2(int i, int j, int k, int l, boolean flag, boolean flag1, MRect mrect)
    {
        FitParams fitparams = new FitParams();
        getFitParamers(i, j, k, l, flag, flag1, fitparams);
        mrect.left = 0;
        mrect.top = 0;
        mrect.right = fitparams.getFitW();
        mrect.bottom = fitparams.getFitH();
    }

    public static void getFitoutRect(int i, int j, int k, int l, Rect rect)
    {
        FitParams fitparams = new FitParams();
        getFitParamers(i, j, k, l, false, true, fitparams);
        if (rect != null)
        {
            rect.set(0, 0, fitparams.getFitW(), fitparams.getFitH());
        }
    }

    public static int getImageFormat(String s)
    {
        MStream mstream = new MStream();
        if (mstream.open(s, 1)) goto _L2; else goto _L1
_L1:
        int i = 2;
_L4:
        if (i == 0)
        {
            i = 2;
        }
        return i;
_L2:
        MImgFileInfo mimgfileinfo = new MImgFileInfo(mstream);
        int j = mimgfileinfo.getFileFormat();
        i = j;
_L5:
        mstream.close();
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception1;
        exception1;
_L6:
        i = 2;
          goto _L5
        Exception exception;
        exception;
          goto _L6
    }

    public static String getOutputFilePath(String s)
    {
        new String();
        String s1 = s.substring(s.lastIndexOf("."));
        String s2 = s.substring(0, s.lastIndexOf("."));
        File file = new File(s);
        boolean flag = file.exists();
        int i = 0;
        if (!flag)
        {
            return file.getAbsolutePath();
        }
        File file1;
        do
        {
            i++;
            file1 = new File((new StringBuilder()).append(s2).append("_").append(i).append(s1).toString());
        } while (file1.exists());
        return file1.getAbsolutePath();
    }

    public static boolean getRectIntersect(Rect rect, Rect rect1, Rect rect2)
    {
        if (rect.left >= rect1.right || rect.right <= rect1.left || rect.top >= rect1.bottom || rect.bottom <= rect1.top)
        {
            return false;
        }
        if (rect2 != null)
        {
            rect2.left = Math.max(rect.left, rect1.left);
            rect2.right = Math.min(rect.right, rect1.right);
            rect2.top = Math.max(rect.top, rect1.top);
            rect2.bottom = Math.min(rect.bottom, rect1.bottom);
        }
        return true;
    }

    public static boolean getRectIntersect(MRect mrect, MRect mrect1, MRect mrect2)
    {
        if (mrect.left >= mrect1.right || mrect.right <= mrect1.left || mrect.top >= mrect1.bottom || mrect.bottom <= mrect1.top)
        {
            return false;
        }
        if (mrect2 != null)
        {
            mrect2.left = Math.max(mrect.left, mrect1.left);
            mrect2.right = Math.min(mrect.right, mrect1.right);
            mrect2.top = Math.max(mrect.top, mrect1.top);
            mrect2.bottom = Math.min(mrect.bottom, mrect1.bottom);
        }
        return true;
    }

    public static int[] getResizeSize(int i, int j, int k, int l)
    {
        int ai[] = new int[2];
        if (i < j)
        {
            k = 800;
            l = 1280;
        }
        int i1;
        int j1;
        if (i < k || j < l)
        {
            i1 = i;
            j1 = j;
        } else
        {
            double d = (double)k / (double)i;
            double d1 = (double)l / (double)j;
            double d2;
            if (d > d1)
            {
                d2 = d;
            } else
            {
                d2 = d1;
            }
            if (d2 > 1.0D)
            {
                d2 = 1.0D;
            }
            i1 = (int)(d2 * (double)i);
            j1 = (int)(d2 * (double)j);
        }
        ai[0] = i1;
        ai[1] = j1;
        return ai;
    }

    public static int[] getTextPixelsSize(String s, Paint paint)
    {
        int ai[] = new int[2];
        Rect rect = new Rect(0, 0, 0, 0);
        paint.getTextBounds(s, 0, s.length(), rect);
        ai[0] = rect.right - rect.left;
        ai[1] = rect.bottom - rect.top;
        return ai;
    }

    public static boolean isPointInRect(Rect rect, int i, int j)
    {
        return i >= rect.left && j >= rect.top && i < rect.right && j < rect.bottom;
    }

    public static boolean isPointInRect(MRect mrect, int i, int j)
    {
        return i >= mrect.left && j >= mrect.top && i < mrect.right && j < mrect.bottom;
    }

    public static boolean isRectEqualRect(MRect mrect, MRect mrect1)
    {
        return mrect.left == mrect1.left && mrect.top == mrect1.top && mrect.right == mrect1.right && mrect.bottom == mrect1.bottom;
    }

    public static boolean isRectInsideRect(Rect rect, Rect rect1)
    {
        return rect.left >= rect1.left && rect.top >= rect1.top && rect.right <= rect1.right && rect.bottom <= rect1.bottom;
    }

    public static boolean isRectIntersect(MRect mrect, MRect mrect1)
    {
        return mrect.left < mrect1.right && mrect.right > mrect1.left && mrect.top < mrect1.bottom && mrect.bottom > mrect1.top;
    }

    private static native int native_AddFrame(MBitmap mbitmap, MRect mrect, String s, String s1);

    private static native int native_Destory();

    private static native int native_DoDraw4Collage(Object obj, MRect mrect, MRect amrect[], MRect amrect1[], MBitmap ambitmap[]);

    private static native int native_MBitmapClip(MBitmap mbitmap, MBitmap mbitmap1, MRect mrect);

    private static native int native_MBitmapCopy(MBitmap mbitmap, MBitmap mbitmap1, MPoint mpoint);

    private static native int native_MBitmapCopy(MBitmap mbitmap, MBitmap mbitmap1, MPoint mpoint, MRect mrect);

    private static native int native_NativeBitmapCreate();

    private static native int native_NativeBitmapDestroy(int i, boolean flag);

    private static native int native_UtilsDoEffect(MBitmap mbitmap, MBitmap mbitmap1, int i, Object obj);

    private static native int native_UtilsSetFrame(MBitmap mbitmap, MBitmap mbitmap1, int i, Object obj, int j, Object obj1, MRect mrect);

    private static native int native_doDraw(Object obj, MBitmap mbitmap, MRect mrect);

    private static native void native_releaseANativeWindow();

    public static void releaseANativeWindow()
    {
        native_releaseANativeWindow();
    }

    public static int setFrame(MBitmap mbitmap, MBitmap mbitmap1, int i, Object obj, int j, Object obj1, MRect mrect)
    {
        return native_UtilsSetFrame(mbitmap, mbitmap1, i, obj, j, obj1, mrect);
    }

    public static void sharePhotoByUri(Uri uri, Context context)
    {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setType("image/*");
        intent.setFlags(0x10000000);
        context.startActivity(Intent.createChooser(intent, context.getResources().getString(0x7f0b011d)));
    }
}
