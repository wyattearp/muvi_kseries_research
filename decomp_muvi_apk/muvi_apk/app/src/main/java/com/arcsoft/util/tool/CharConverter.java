// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;


public class CharConverter
{

    public CharConverter()
    {
    }

    public static String ConverterString(String s)
    {
        if (s == null || s.length() == 0)
        {
            return null;
        } else
        {
            return IterationMarksInvert(Hiragana2Katakana(LatinLettet2SmallLetter(FullWidth2HalfWidth(s)))).toLowerCase();
        }
    }

    public static String FullWidth2HalfWidth(String s)
    {
        if (s == null || s.length() == 0)
        {
            return null;
        }
        char ac[] = s.toCharArray();
        for (int i = 0; i < ac.length && ac != null; i++)
        {
            if (ac[i] >= '\uFF01' && ac[i] <= '\uFF5E')
            {
                ac[i] = (char)(-65248 + ac[i]);
            }
        }

        return String.valueOf(ac);
    }

    public static int GetFirstCharType(String s)
    {
        char c = s.charAt(0);
        if (c < '\uFF61' || c > '\uFF9F') goto _L2; else goto _L1
_L1:
        byte byte0 = 7;
_L4:
        return byte0;
_L2:
        if (c >= '\u3041' && c <= '\u309F')
        {
            return 8;
        }
        if (c >= '\u30A1' && c <= '\u30FF')
        {
            return 9;
        }
        if (c >= 'A' && c <= 'Z')
        {
            return 5;
        }
        if (c >= 'a' && c <= 'z')
        {
            return 6;
        }
        if (c >= '0' && c <= '9')
        {
            return 4;
        }
        if (c < ' ')
        {
            break; /* Loop/switch isn't completed */
        }
        byte0 = 0;
        if (c <= '/') goto _L4; else goto _L3
_L3:
        if (c < ':')
        {
            break; /* Loop/switch isn't completed */
        }
        byte0 = 0;
        if (c <= '@') goto _L4; else goto _L5
_L5:
        if (c < '[')
        {
            break; /* Loop/switch isn't completed */
        }
        byte0 = 0;
        if (c <= '`') goto _L4; else goto _L6
_L6:
        if (c < '{')
        {
            break; /* Loop/switch isn't completed */
        }
        byte0 = 0;
        if (c <= '~') goto _L4; else goto _L7
_L7:
        if (c >= '\241' && c <= '\277' || c == '\327' || c == '\367')
        {
            return 1;
        }
        if (c >= '\300' && c <= '\336')
        {
            return 2;
        }
        if (c >= '\337' && c <= '\377')
        {
            return 3;
        }
        return c < '\u4E00' || c > '\u9FFF' ? 11 : 10;
    }

    public static String Hiragana2Katakana(String s)
    {
        if (s == null || s.length() == 0)
        {
            return null;
        }
        char ac[] = s.toCharArray();
        int i = 0;
        while (i < ac.length && ac != null) 
        {
            int j = GetFirstCharType(s.substring(i));
            if (7 == j)
            {
                if (ac[i] == '\uFF66')
                {
                    ac[i] = '\u30F2';
                } else
                if (ac[i] >= '\uFF67' && ac[i] <= '\uFF6B')
                {
                    ac[i] = (char)(12449 + 2 * (ac[i] - 65383));
                } else
                if (ac[i] >= '\uFF6C' && ac[i] <= '\uFF6E')
                {
                    ac[i] = (char)(12515 + 2 * (ac[i] - 65388));
                } else
                if (ac[i] == '\uFF6F')
                {
                    ac[i] = '\u30C3';
                } else
                if (ac[i] >= '\uFF71' && ac[i] <= '\uFF75')
                {
                    ac[i] = (char)(12450 + 2 * (ac[i] - 65393));
                } else
                if (ac[i] >= '\uFF76' && ac[i] <= '\uFF81')
                {
                    ac[i] = (char)(12459 + 2 * (ac[i] - 65398));
                } else
                if (ac[i] >= '\uFF82' && ac[i] <= '\uFF84')
                {
                    ac[i] = (char)(12484 + 2 * (ac[i] - 65410));
                } else
                if (ac[i] >= '\uFF85' && ac[i] <= '\uFF89')
                {
                    ac[i] = (char)(12490 + (ac[i] - 65413));
                } else
                if (ac[i] >= '\uFF8A' && ac[i] <= '\uFF8E')
                {
                    ac[i] = (char)(12495 + 3 * (ac[i] - 65418));
                } else
                if (ac[i] >= '\uFF8F' && ac[i] <= '\uFF93')
                {
                    ac[i] = (char)(12510 + (ac[i] - 65423));
                } else
                if (ac[i] >= '\uFF94' && ac[i] <= '\uFF96')
                {
                    ac[i] = (char)(12516 + 2 * (ac[i] - 65428));
                } else
                if (ac[i] >= '\uFF97' && ac[i] <= '\uFF9B')
                {
                    ac[i] = (char)(12521 + (ac[i] - 65431));
                } else
                if (ac[i] == '\uFF9C')
                {
                    ac[i] = '\u30EF';
                } else
                if (ac[i] == '\uFF9D')
                {
                    ac[i] = '\u30F3';
                }
            }
            if (8 == j)
            {
                ac[i] = (char)(96 + ac[i]);
            }
            i++;
        }
        return String.valueOf(ac);
    }

    public static String IterationMarksInvert(String s)
    {
        if (s == null || s.length() == 0)
        {
            return null;
        }
        char ac[] = s.toCharArray();
        char c = ac[0];
        for (int i = 1; i < ac.length && ac != null; i++)
        {
            if (ac[i] == '\u309D' || ac[i] == '\u309E' || ac[i] == '\u30FD' || ac[i] == '\u30FE' || ac[i] == '\u3005')
            {
                ac[i] = c;
            }
            c = ac[i];
        }

        return String.valueOf(ac);
    }

    public static String LatinLettet2SmallLetter(String s)
    {
        if (s == null || s.length() == 0)
        {
            return null;
        }
        char ac[] = s.toCharArray();
        int i = 0;
        while (i < ac.length) 
        {
            int j = GetFirstCharType(s.substring(i));
            if (1 == j)
            {
                if (ac[i] == '\252')
                {
                    ac[i] = 'a';
                } else
                if (ac[i] == '\262')
                {
                    ac[i] = '2';
                } else
                if (ac[i] == '\263')
                {
                    ac[i] = '3';
                } else
                if (ac[i] == '\271')
                {
                    ac[i] = '1';
                } else
                if (ac[i] == '\274')
                {
                    ac[i] = '1';
                } else
                if (ac[i] == '\275')
                {
                    ac[i] = '1';
                } else
                if (ac[i] == '\276')
                {
                    ac[i] = '1';
                }
            } else
            if (2 == j)
            {
                if (ac[i] >= '\300' && ac[i] <= '\306')
                {
                    ac[i] = 'a';
                } else
                if (ac[i] == '\307')
                {
                    ac[i] = 'c';
                } else
                if (ac[i] >= '\310' && ac[i] <= '\313')
                {
                    ac[i] = 'e';
                } else
                if (ac[i] >= '\314' && ac[i] <= '\317')
                {
                    ac[i] = 'i';
                } else
                if (ac[i] == '\320')
                {
                    ac[i] = 'e';
                } else
                if (ac[i] == '\321')
                {
                    ac[i] = 'n';
                } else
                if (ac[i] >= '\322' && ac[i] <= '\326')
                {
                    ac[i] = 'o';
                } else
                if (ac[i] == '\330')
                {
                    ac[i] = 'o';
                } else
                if (ac[i] >= '\331' && ac[i] <= '\334')
                {
                    ac[i] = 'u';
                } else
                if (ac[i] == '\335')
                {
                    ac[i] = 'y';
                } else
                if (ac[i] == '\336')
                {
                    ac[i] = 't';
                }
            } else
            if (3 == j)
            {
                if (ac[i] == '\337')
                {
                    ac[i] = 's';
                } else
                if (ac[i] >= '\340' && ac[i] <= '\346')
                {
                    ac[i] = 'a';
                } else
                if (ac[i] == '\347')
                {
                    ac[i] = 'c';
                } else
                if (ac[i] >= '\350' && ac[i] <= '\353')
                {
                    ac[i] = 'e';
                } else
                if (ac[i] >= '\354' && ac[i] <= '\357')
                {
                    ac[i] = 'i';
                } else
                if (ac[i] == '\360')
                {
                    ac[i] = 'e';
                } else
                if (ac[i] == '\361')
                {
                    ac[i] = 'n';
                } else
                if (ac[i] >= '\362' && ac[i] <= '\366')
                {
                    ac[i] = 'o';
                } else
                if (ac[i] == '\370')
                {
                    ac[i] = 'o';
                } else
                if (ac[i] >= '\371' && ac[i] <= '\374')
                {
                    ac[i] = 'u';
                } else
                if (ac[i] == '\375')
                {
                    ac[i] = 'y';
                } else
                if (ac[i] == '\376')
                {
                    ac[i] = 't';
                } else
                if (ac[i] == '\377')
                {
                    ac[i] = 'y';
                }
            }
            i++;
        }
        return String.valueOf(ac);
    }

    public static String Letter2SmallLetter(String s)
    {
        if (s == null || s.length() == 0)
        {
            return null;
        }
        char ac[] = s.toCharArray();
        for (int i = 0; i < ac.length; i++)
        {
            if (5 == GetFirstCharType(s.substring(i)))
            {
                ac[i] = (char)(32 + ac[i]);
            }
        }

        return String.valueOf(ac);
    }
}
