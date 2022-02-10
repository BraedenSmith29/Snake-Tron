public class SnakeHead {
    private int direction; // up -> 1, right -> 2, down -> 3, left -> 4
    protected int xPos;
    protected int yPos;
    protected int lastX;
    protected int lastY;

    public SnakeHead(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void update() {
        //update last position
        lastX = xPos;
        lastY = yPos;
        //move
        if (direction == 1) {
            yPos--;
        } else if (direction == 3) {
            yPos++;
        } else if (direction == 2) {
            xPos++;
        } else {
            xPos--;
        }
        //check food
    }

    public void setDirection(int dir) {
        if (!(dir >= 1 && dir <= 5)) return;
        direction = dir;
    }
    
    public int getX() {
        return xPos;
    }
    public int getY() {
        return yPos;
    }
    public int getLastX() {
        return lastX;
    }
    public int getLastY() {
        return lastY;
    }
}        