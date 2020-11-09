package sample;

import javafx.scene.control.Tooltip;

public class ToolTipWindow {

    public static Tooltip createToolTip(String message){
        Tooltip tooltip = new Tooltip();
        tooltip.setText(message);
        tooltip.setGraphicTextGap(0.0);

        return tooltip;
    }
}
