package com.sweattypalms.skyblock.gui;

import com.sweattypalms.skyblock.utils.SkyUtils;

public enum GUIType {
    Example_Gui(ExampleGui.class),

;

    private final Class<? extends GUI> gui;

    GUIType(Class<? extends GUI> gui) {
        this.gui = gui;
    }

    public GUI getGUI() {
        try {
            return gui.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GUI getGUI(Object... params) {
        return SkyUtils.instance(GUI.class, params);
    }

    public static GUI getGUI(String title) {
        for (GUIType type : values()) {
            if (type.getGUI().getTitle().contains(title))
                return type.getGUI();
        }
        return null;
    }
}