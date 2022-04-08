package snake.v2.source;

import static snake.v2.SnakeV2FXMLController.SIZE_X;
import static snake.v2.SnakeV2FXMLController.SIZE_Y;
import static snake.v2.SnakeV2FXMLController.TableXY;

public class Food {

    public int xPosition, yPosition;
    public boolean isPlaced = false;
    public TableData value = TableData.FOOD;

    public void _create() {
        boolean isBreak = false;
        int randomNumber = (int) (Math.random() * SIZE_X * SIZE_Y);
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                if (randomNumber <= 0 && TableXY.get(x).get(y).equals(TableData.ABYSS)) {
                    xPosition = x;
                    yPosition = y;
                    isBreak = true;
                    break;
                }
                randomNumber--;
            }
            if (isBreak) {
                break;
            }
        }
        this.isPlaced = true;
    }
}
