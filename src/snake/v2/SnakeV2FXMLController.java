package snake.v2;

import snake.v2.source.PointOfView;
import snake.v2.source.Snake;
import java.util.LinkedList;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import snake.v2.source.TableData;
import javafx.scene.input.KeyCode;
import snake.v2.source.Food;

public class SnakeV2FXMLController implements Initializable {

    @FXML
//    public static Canvas canvas;
    private Canvas canvas;
    private GraphicsContext gc;
    private int WIDTH;
    private int HEIGHT;
    private int SIZE_OF_BLOCK = 20;
    private int TIMER = 300;
    public static int SIZE_X;
    public static int SIZE_Y;
    public static LinkedList<LinkedList> TableXY;
    private LinkedList<LinkedList> copyTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WIDTH = (int) canvas.getWidth();
        HEIGHT = (int) canvas.getHeight();
        SIZE_X = WIDTH / SIZE_OF_BLOCK;
        SIZE_Y = HEIGHT / SIZE_OF_BLOCK;

        //create the table
        gc = canvas.getGraphicsContext2D();
        _drawBackground(gc);
        TableXY = _createProgramTable();
        copyTable = _clone(TableXY);
//        TableXY.get(0).set(0, TableData.SNAKE);
//        TableXY.get(0).set(1, TableData.HEAD);
//        TableXY.get(0).set(2, TableData.FOOD);
//        TableXY.get(0).set(3, TableData.BRICK);

        //set the snake
        Snake snake = new Snake();
        snake.head.xPosition = SIZE_X / 2;
        snake.head.yPosition = SIZE_Y / 2;
        snake.head.view = PointOfView.UP;
        snake.head.lastMove = PointOfView.DOWN;
        TableXY.get(SIZE_X / 2).set(SIZE_Y / 2, TableData.HEAD);
        _drawOtherObjects(gc, TableXY);

        //set food
        Food food = new Food();

        //loop
        TimerTask timerTask = new TimerTask() {
            public void run() {
                if (!_loopFunction(snake, food)) {
                    cancel();
                }
            }
        };
        Timer timer = new Timer();

        timer.schedule(timerTask, 0, TIMER);
    }

    public boolean _loopFunction(Snake snake, Food food) {
        if (!snake._move(TableXY, SnakeV2Main.key)) {
            return false;
        }
        TableXY = _refreshTable(TableXY, snake, copyTable);
        if (snake.head.xPosition == food.xPosition && snake.head.yPosition == food.yPosition) {
            food.isPlaced = false;
        }
        if (!food.isPlaced) {
            food._create();
        }
        TableXY.get(food.xPosition).set(food.yPosition, TableData.FOOD);
        _drawBackground(gc);
        _drawOtherObjects(gc, TableXY);
        return true;
    }

    public LinkedList<LinkedList> _createProgramTable() {
        LinkedList<LinkedList> TableX = new LinkedList();
        for (int x = 0; x < SIZE_X; x++) {
            LinkedList<TableData> TableY = new LinkedList();
            for (int y = 0; y < SIZE_Y; y++) {
                TableY.add(TableData.ABYSS);
            }
            TableX.add(TableY);
        }
        return TableX;
    }

    public LinkedList<LinkedList> _refreshTable(LinkedList<LinkedList> table, Snake snake, LinkedList<LinkedList> copyTable) {
        table = _clone(copyTable);
        table.get(snake.head.xPosition).set(snake.head.yPosition, TableData.HEAD);
        for (int step = 0; step < snake.body.size(); step++) {
            table.get(snake.body.get(step).xPosition).set(snake.body.get(step).yPosition, TableData.SNAKE);
        }
        return table;
    }

    public LinkedList<LinkedList> _clone(LinkedList<LinkedList> table) {
        LinkedList<LinkedList> copyTableX = new LinkedList();

        for (int x = 0; x < SIZE_X; x++) {
            LinkedList<TableData> copyTableY = new LinkedList();
            for (int y = 0; y < SIZE_Y; y++) {
                copyTableY.add((TableData) table.get(x).get(y));
            }
            copyTableX.add(copyTableY);
        }

        return copyTableX;
    }

    public void _drawBackground(GraphicsContext gc) {
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                if ((x + y) % 2 == 0) {
                    gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(x * SIZE_OF_BLOCK, y * SIZE_OF_BLOCK, SIZE_OF_BLOCK, SIZE_OF_BLOCK);
            }
        }
    }

    public void _drawOtherObjects(GraphicsContext gc, LinkedList<LinkedList> arr) {
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                TableData dataForCheck = (TableData) arr.get(x).get(y);
//                if (arr.get(x).get(y).equals(TableData.HEAD)) {
                if (dataForCheck.equals(TableData.HEAD)) {
                    gc.setFill(Color.web("151B54"));
//                    gc.fillRect(x * SIZE_OF_BLOCK, y * SIZE_OF_BLOCK, SIZE_OF_BLOCK, SIZE_OF_BLOCK);
                    gc.fillOval(x * SIZE_OF_BLOCK, y * SIZE_OF_BLOCK, SIZE_OF_BLOCK, SIZE_OF_BLOCK);
//                } else if (arr.get(x).get(y).equals(TableData.SNAKE)) {
                } else if (dataForCheck.equals(TableData.SNAKE)) {
                    gc.setFill(Color.web("4863A0"));
                    gc.fillOval(x * SIZE_OF_BLOCK + SIZE_OF_BLOCK / 4, y * SIZE_OF_BLOCK + SIZE_OF_BLOCK / 4, SIZE_OF_BLOCK / 2, SIZE_OF_BLOCK / 2);
//                } else if (arr.get(x).get(y).equals(TableData.BRICK)) {
                } else if (dataForCheck.equals(TableData.BRICK)) {
                    gc.setFill(Color.web("5C3317"));
                    gc.fillRect(x * SIZE_OF_BLOCK, y * SIZE_OF_BLOCK, SIZE_OF_BLOCK, SIZE_OF_BLOCK);
//                } else if (arr.get(x).get(y).equals(TableData.FOOD)) {
                } else if (dataForCheck.equals(TableData.FOOD)) {
                    gc.setFill(Color.web("660000"));
                    gc.fillOval(x * SIZE_OF_BLOCK, y * SIZE_OF_BLOCK, SIZE_OF_BLOCK, SIZE_OF_BLOCK);
                }
            }
        }
    }
}

//enum TableData {
//    ABYSS, HEAD, SNAKE, FOOD, BRICK
//}
//
//enum pointOfView {
//    UP, RIGHT, DOWN, LEFT
//}
