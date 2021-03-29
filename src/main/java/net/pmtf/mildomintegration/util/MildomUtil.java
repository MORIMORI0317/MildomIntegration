package net.pmtf.mildomintegration.util;

import net.minecraft.util.text.TextFormatting;

public class MildomUtil {
    public static TextFormatting getTextFormatCloerByLevel(int lv) {
        if (lv >= 1 && lv <= 4)
            return TextFormatting.GRAY;
        else if (lv > 4 && lv <= 36)
            return TextFormatting.BLUE;
        else if (lv > 36 && lv <= 61)
            return TextFormatting.GREEN;
        else if (lv > 61 && lv <= 81)
            return TextFormatting.AQUA;
        else if (lv > 81 && lv <= 101)
            return TextFormatting.LIGHT_PURPLE;
        else if (lv > 101 && lv <= 116)
            return TextFormatting.RED;
        else if (lv > 116 && lv <= 131)
            return TextFormatting.YELLOW;
        else if (lv > 131 && lv <= 146)
            return TextFormatting.GOLD;
        else if (lv > 146 && lv <= 151)
            return TextFormatting.DARK_PURPLE;

        return TextFormatting.BLACK;
    }
}
