package snake.v2.source;

import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.input.KeyCode;
import snake.v2.SnakeV2FXMLController;

public class Snake {

    public Head head;
    public ArrayList<Body> body;

    public class Head {

        public TableData value;
        public PointOfView view, lastMove;
        public int xPosition, yPosition;

        Head() {
            this.value = TableData.HEAD;
        }
    }

    public class Body {

        public TableData value;
        public int xPosition, yPosition;

        Body(int xPosition, int yPosition) {
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.value = TableData.SNAKE;
        }
    }

    public Snake() {
        this.head = new Head();
        this.body = new ArrayList<Body>();
    }

    public boolean _move(LinkedList<LinkedList> table, KeyCode key) {
        _check(key);
        boolean toReturn = false;
        switch (this.head.view) {
            case UP ->
                toReturn = _moveUp(table);
            case DOWN ->
                toReturn = _moveDown(table);
            case LEFT ->
                toReturn = _moveLeft(table);
            case RIGHT ->
                toReturn = _moveRight(table);
        }
        return toReturn;
    }

    public void _check(KeyCode key) {
        if (key.equals(KeyCode.UP) && !this.head.lastMove.equals(PointOfView.DOWN)) {
            this.head.view = PointOfView.UP;
        } else if (key.equals(KeyCode.DOWN) && !this.head.lastMove.equals(PointOfView.UP)) {
            this.head.view = PointOfView.DOWN;
        } else if (key.equals(KeyCode.LEFT) && !this.head.lastMove.equals(PointOfView.RIGHT)) {
            this.head.view = PointOfView.LEFT;
        } else if (key.equals(KeyCode.RIGHT) && !this.head.lastMove.equals(PointOfView.LEFT)) {
            this.head.view = PointOfView.RIGHT;
        }
    }

    public boolean _moveUp(LinkedList<LinkedList> table) {
        for (int step = body.size() - 1; step >= 1; step--) {
            body.get(step).xPosition = body.get(step - 1).xPosition;
            body.get(step).yPosition = body.get(step - 1).yPosition;
        }
        if (!body.isEmpty()) {
            body.get(0).xPosition = head.xPosition;
            body.get(0).yPosition = head.yPosition;
        }
        int nextYPosition = (head.yPosition - 1 < 0 ? SnakeV2FXMLController.SIZE_Y - 1 : head.yPosition - 1);
        if (table.get(head.xPosition).get(nextYPosition).equals(TableData.BRICK) || table.get(head.xPosition).get(nextYPosition).equals(TableData.SNAKE)) {
            return false;
        } else if (table.get(head.xPosition).get(nextYPosition).equals(TableData.FOOD)) {
            this.body.add(new Body(head.xPosition, head.yPosition));
            head.yPosition = nextYPosition;
            head.lastMove = PointOfView.UP;
            return true;
        } else {
            head.yPosition = nextYPosition;
            head.lastMove = PointOfView.UP;
            return true;
        }
    }

    public boolean _moveDown(LinkedList<LinkedList> table) {
        for (int step = body.size() - 1; step >= 1; step--) {
            body.get(step).xPosition = body.get(step - 1).xPosition;
            body.get(step).yPosition = body.get(step - 1).yPosition;
        }
        if (!body.isEmpty()) {
            body.get(0).xPosition = head.xPosition;
            body.get(0).yPosition = head.yPosition;
        }
        int nextYPosition = (head.yPosition + 1 >= SnakeV2FXMLController.SIZE_Y ? 0 : head.yPosition + 1);
        if (table.get(head.xPosition).get(nextYPosition).equals(TableData.BRICK) || table.get(head.xPosition).get(nextYPosition).equals(TableData.SNAKE)) {
            return false;
        } else if (table.get(head.xPosition).get(nextYPosition).equals(TableData.FOOD)) {
            this.body.add(new Body(head.xPosition, head.yPosition));
            head.yPosition = nextYPosition;
            head.lastMove = PointOfView.DOWN;
            return true;
        } else {
            head.yPosition = nextYPosition;
            head.lastMove = PointOfView.DOWN;
            return true;
        }
    }

    public boolean _moveLeft(LinkedList<LinkedList> table) {
        for (int step = body.size() - 1; step >= 1; step--) {
            body.get(step).xPosition = body.get(step - 1).xPosition;
            body.get(step).yPosition = body.get(step - 1).yPosition;
        }
        if (!body.isEmpty()) {
            body.get(0).xPosition = head.xPosition;
            body.get(0).yPosition = head.yPosition;
        }
        int nextXPosition = (head.xPosition - 1 < 0 ? SnakeV2FXMLController.SIZE_X - 1 : head.xPosition - 1);
        if (table.get(nextXPosition).get(head.yPosition).equals(TableData.BRICK) || table.get(nextXPosition).get(head.yPosition).equals(TableData.SNAKE)) {
            return false;
        } else if (table.get(nextXPosition).get(head.yPosition).equals(TableData.FOOD)) {
            this.body.add(new Body(head.xPosition, head.yPosition));
            head.xPosition = nextXPosition;
            head.lastMove = PointOfView.LEFT;
            return true;
        } else {
            head.xPosition = nextXPosition;
            head.lastMove = PointOfView.LEFT;
            return true;
        }
    }

    public boolean _moveRight(LinkedList<LinkedList> table) {
        for (int step = body.size() - 1; step >= 1; step--) {
            body.get(step).xPosition = body.get(step - 1).xPosition;
            body.get(step).yPosition = body.get(step - 1).yPosition;
        }
        if (!body.isEmpty()) {
            body.get(0).xPosition = head.xPosition;
            body.get(0).yPosition = head.yPosition;
        }
        int nextXPosition = (head.xPosition + 1 >= SnakeV2FXMLController.SIZE_X ? 0 : head.xPosition + 1);
        if (table.get(nextXPosition).get(head.yPosition).equals(TableData.BRICK) || table.get(nextXPosition).get(head.yPosition).equals(TableData.SNAKE)) {
            return false;
        } else if (table.get(nextXPosition).get(head.yPosition).equals(TableData.FOOD)) {
            this.body.add(new Body(head.xPosition, head.yPosition));
            head.xPosition = nextXPosition;
            head.lastMove = PointOfView.RIGHT;
            return true;
        } else {
            head.xPosition = nextXPosition;
            head.lastMove = PointOfView.RIGHT;
            return true;
        }
    }
}

//enum pointOfView {
//    UP, RIGHT, DOWN, LEFT
//}
//
//enum tableData {
//    ABYSS, HEAD, SNAKE, FOOD, BRICK
//}
