package dvdav.controller;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;

import static javafx.scene.layout.BorderStrokeStyle.SOLID;
import static javafx.scene.layout.CornerRadii.EMPTY;

class BorderBuilder {
    static Border build() {
        return new Border(
                new BorderStroke(
                        Color.WHITE,
                        SOLID,
                        EMPTY,
                        new BorderWidths(0.1)
                )
        );
    }
}
