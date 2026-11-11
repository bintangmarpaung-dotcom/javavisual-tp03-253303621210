package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class AppTheme {
    public static final Color COLOR_PRIMARY = Color.decode("#2C3E50");
    public static final Color COLOR_SECONDARY = Color.decode("#ECF0F1");
    public static final Color COLOR_ACCENT = Color.decode("#F39C12");

    public static final Color COLOR_WARRIOR = Color.decode("#3498DB");
    public static final Color COLOR_MAGE = Color.decode("#9B59B6");
    public static final Color COLOR_ASSASSIN = Color.decode("#E74C3C");

    public static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 18);
    public static final Font FONT_BODY = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font FONT_MONO = new Font("Monospaced", Font.PLAIN, 12);

    public static final Border BORDER_CARD = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.GRAY, 1),
        BorderFactory.createEmptyBorder(8, 8, 8, 8)
    );

    public static Color colorFor(String role) {
        if (role == null) return COLOR_SECONDARY;
        switch (role.toUpperCase()) {
            case "WARRIOR":
                return COLOR_WARRIOR;
            case "MAGE":
                return COLOR_MAGE;
            case "ASSASSIN":
                return COLOR_ASSASSIN;
            default:
                return COLOR_SECONDARY;
        }
    }
}