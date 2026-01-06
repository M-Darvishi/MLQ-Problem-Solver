package ui;

import core.model.ProcessView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class Drawing {
    private Canvas canvas;

    public Drawing(Canvas canvas) {
        this.canvas = canvas;
    }

    public void draw(List<ProcessView> gantt){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        double y = 50;
        double unitheight = 40;
        double unitWidth = 30;

        gc.setFill(Color.RED);
        gc.fillText(String.valueOf(gantt.get(0).getStartTime()), 0, y + unitheight + 12);
        for (ProcessView slot : gantt) {
            double x = slot.getStartTime() * unitWidth;
            double w = slot.getDuration() * unitWidth;

            if (slot.getProcessName().equals("IDLE")) {
                gc.setFill(Color.WHITE);
                gc.fillRect(x, y, w, unitheight);

                gc.save();
                gc.beginPath();
                gc.rect(x, y, w, unitheight);
                gc.closePath();
                gc.clip();
                gc.setStroke(Color.DARKGRAY);
                for (double i = x; i < x + w + unitheight; i += 10) {
                    gc.strokeLine(i, y, i - unitheight, y + unitheight);
                }
                gc.restore();

            } else {
                gc.setFill(slot.getColor() != null ? slot.getColor() : Color.LIGHTBLUE);
                gc.fillRect(x, y, w, unitheight);

                gc.setFill(Color.BLACK);
                gc.fillText(slot.getProcessName(), x + 5, y + unitheight / 1.5);
            }

            gc.setStroke(Color.BLACK);
            gc.strokeRect(x+0.5, y, w, unitheight);

            gc.setFill(Color.RED);

            gc.fillText(String.valueOf(slot.getStartTime() + slot.getDuration()), x + w - 5, y + unitheight + 12);
        }
    }
}
