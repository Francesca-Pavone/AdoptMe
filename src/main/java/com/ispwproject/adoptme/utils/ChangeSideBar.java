package com.ispwproject.adoptme.utils;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ChangeSideBar {
    private ChangeSideBar() {
        //Java adds an implicit public constructor to every class which does not define at least one explicitly. Hence, at least one non-public constructor should be defined.
    }

    public static void changeSideBar(HBox hBox, int i1, int i2, int i3) {
        hBox.setBackground(new Background(new BackgroundFill(Color.rgb(i1,i2,i3), CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
