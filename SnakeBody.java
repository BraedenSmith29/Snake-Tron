public class SnakeBody extends SnakeHead {
    private SnakeHead parent;

    public SnakeBody(int x, int y, SnakeHead s) {
        super(x, y);
        xPos = x;
        yPos = y;
        parent = s;
    }

    @Override
    public void update() {
        lastX = xPos;
        lastY = yPos;
        xPos = parent.getLastX();
        yPos = parent.getLastY();
    }
}