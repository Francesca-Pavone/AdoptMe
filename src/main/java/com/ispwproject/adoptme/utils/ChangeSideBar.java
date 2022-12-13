package com.ispwproject.adoptme.utils;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ChangeSideBar {


    public static void changeSideBar(HBox hBox, int i1, int i2, int i3) {
        hBox.setBackground(new Background(new BackgroundFill(Color.rgb(i1,i2,i3), CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
