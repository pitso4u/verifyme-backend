package com.klmsystems.javafxdesktop;

public interface ControlledScreen {
    void setScreenParent(ScreenManager screenPage);
    void runOnScreenChange();
    void cleanup();
}