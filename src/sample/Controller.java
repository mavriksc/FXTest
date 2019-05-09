package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

public class Controller {
    @FXML
    private Polygon shape;
    @FXML
    private Line line;
    @FXML
    private CheckBox checkBox;

    @FXML
    private void initialize()
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20),event -> {
            if(checkBox.isSelected()) {
                line.setStartX(line.getStartX()+ThreadLocalRandom.current().nextInt(3) - 1);
                line.setStartY(line.getStartY()+ThreadLocalRandom.current().nextInt(3) - 1);

                line.setEndX(line.getEndX()+ThreadLocalRandom.current().nextInt(3) - 1);
                line.setEndY(line.getEndY()+ThreadLocalRandom.current().nextInt(3) - 1);
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    @FXML
    private void updateCheckBox(){
        if(checkBox.isSelected())
            System.out.println("things happened");
        else
            System.out.println("things don't want to happen");
    }


}
