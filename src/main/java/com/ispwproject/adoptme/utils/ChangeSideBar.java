package com.ispwproject.adoptme.utils;

import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class ChangeSideBar {
    private ChangeSideBar() {
        //Java adds an implicit public constructor to every class which does not define at least one explicitly. Hence, at least one non-public constructor should be defined.
    }

    public static void clicked(Button btn, Pane pane) {btn.getStyleClass().clear();
        btn.getStyleClass().add("open-screen");
        pane.setVisible(true);
        //btn.setBackground(new Background(new BackgroundFill(Color.rgb(i1,i2,i3), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public static void other(Button btn, Pane pane) {
        btn.getStyleClass().clear();
        btn.getStyleClass().add("other-screen");
        pane.setVisible(false);

    }

}
