package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Controller {

    @FXML
    private Polygon shape;
    @FXML
    private Line line;
    @FXML
    private CheckBox checkBox;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            if (checkBox.isSelected()) {
                line.setStartX(line.getStartX() + ThreadLocalRandom.current().nextInt(3) - 1);
                line.setStartY(line.getStartY() + ThreadLocalRandom.current().nextInt(3) - 1);

                line.setEndX(line.getEndX() + ThreadLocalRandom.current().nextInt(3) - 1);
                line.setEndY(line.getEndY() + ThreadLocalRandom.current().nextInt(3) - 1);
                shape.setRotate(shape.getRotate() + ThreadLocalRandom.current().nextInt(3) - 1);
                //shape.getPoints().set(2,shape.getPoints().get(2)+ThreadLocalRandom.current().nextInt(3) - 1);
                //shape.getPoints().set(3,shape.getPoints().get(3)+ThreadLocalRandom.current().nextInt(3) - 1);
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void incPoly() {
        int points = shape.getPoints().size() / 2;
        if (points < 100) {
            update(points+1);
        }
    }

    @FXML
    private void decPoly() {
        int points = shape.getPoints().size() / 2;
        if (points >= 4) {
            update(points-1);
        }
    }

    private void update(int sides){
        List<Double> points = getPoints(sides);
        for (int i = 0; i <Math.min(points.size(),shape.getPoints().size()); i++) {
            shape.getPoints().set(i,points.get(i));
        }
        while (points.size()<shape.getPoints().size()){
            shape.getPoints().remove(shape.getPoints().size() - 1);
        }
        if (points.size()>shape.getPoints().size()){
            shape.getPoints().addAll(points.subList(shape.getPoints().size(),points.size()));
        }
    }

    @FXML
    private void updateCheckBox() {
        if (checkBox.isSelected())
            System.out.println("things happened");
        else
            System.out.println("things don't want to happen");
    }

    private List<Double> getPoints(int sides) {
        List<Double> points = new ArrayList<>();
        double a = (Math.PI*2)/sides;
        for (int i = 0; i < sides; i++) {
            points.addAll(polarToCartesian(i*a,50));
        }
        return points;
    }

    private List<Double> polarToCartesian(double a,double r){
        List<Double> points  = new ArrayList<>();
        points.add(r*Math.cos(a));
        points.add(r*Math.sin(a));
        return points;
    }

}
