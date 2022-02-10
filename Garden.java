import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Garden extends JPanel implements ActionListener, KeyListener {
    private static final int UNIVERSAL_OFFSET = 41;
    private static ArrayList<SnakeHead> p1 = new ArrayList<SnakeHead>();
    private static ArrayList<SnakeHead> p2 = new ArrayList<SnakeHead>();
    private static JFrame window = new JFrame("Snake");
    private static Garden garden = new Garden();
    private static int foodX = 25;
    private static int foodY = 25;
    private static int p1Score = 0;
    private static int p2Score = 0;

    public static void main(String[] args) {
        p1.add(new SnakeHead(15, 25));
        p1.get(0).setDirection(2);
        p1.add(new SnakeBody(14, 25, p1.get(0)));
        p1.add(new SnakeBody(13, 25, p1.get(1)));
        p2.add(new SnakeHead(35, 25));
        p2.get(0).setDirection(4);
        p2.add(new SnakeBody(36, 25, p2.get(0)));
        p2.add(new SnakeBody(37, 25, p2.get(1)));
        moveFood();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.add(garden, BorderLayout.CENTER);
        window.setSize(700, 700);
        window.setVisible(true);
    }

    public Garden() {
        setBackground(new Color(0, 255, 0));
        setFocusable(true);
        addKeyListener(this);
        Timer timer = new Timer(1000/10, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        update();
    }

    public static void update() {
        int p1Size = p1.size();
        for (int i = 0; i < p1Size; i++) {
            p1.get(i).update();
        }
        int p2Size = p2.size();
        for (int i = 0; i < p2Size; i++) {
            p2.get(i).update();
        }
        checkFood();
        checkCollision();
        garden.repaint();
    }

    public static void reset() {
        p1 = new ArrayList<SnakeHead>();
        p2 = new ArrayList<SnakeHead>();
        p1.add(new SnakeHead(15, 25));
        p1.get(0).setDirection(2);
        p1.add(new SnakeBody(14, 25, p1.get(0)));
        p1.add(new SnakeBody(13, 25, p1.get(1)));
        p2.add(new SnakeHead(35, 25));
        p2.get(0).setDirection(4);
        p2.add(new SnakeBody(36, 25, p2.get(0)));
        p2.add(new SnakeBody(37, 25, p2.get(1)));
        moveFood();
    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.drawLine(UNIVERSAL_OFFSET - 1, UNIVERSAL_OFFSET - 1, UNIVERSAL_OFFSET - 1, 600 + UNIVERSAL_OFFSET);
        g.drawLine(UNIVERSAL_OFFSET - 1, UNIVERSAL_OFFSET - 1, 600 + UNIVERSAL_OFFSET, UNIVERSAL_OFFSET - 1);
        g.drawLine(600 + UNIVERSAL_OFFSET, 600 + UNIVERSAL_OFFSET, 600 + UNIVERSAL_OFFSET, UNIVERSAL_OFFSET - 1);
        g.drawLine(600 + UNIVERSAL_OFFSET, 600 + UNIVERSAL_OFFSET, UNIVERSAL_OFFSET - 1, 600 + UNIVERSAL_OFFSET);
        int dark = 200;
        g.setColor(new Color(dark, dark, dark));
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                g.fillRect(j * 12 + UNIVERSAL_OFFSET, i * 12 + UNIVERSAL_OFFSET, 12, 12);
                if (dark == 200) {
                    dark = 180;
                } else {
                    dark = 200;
                }
                g.setColor(new Color(dark, dark, dark));
            }
            if (dark == 200) {
                dark = 180;
            } else {
                dark = 200;
            }
            g.setColor(new Color(dark, dark, dark));;
        }
        g.setColor(new Color(100, 0, 200));
        for (int i = 0; i < p1.size(); i++) {
            g.fillRect(p1.get(i).getX() * 12 + 1 + UNIVERSAL_OFFSET, p1.get(i).getY() * 12 + 1 + UNIVERSAL_OFFSET, 10, 10);
            if (i == 0) g.setColor(new Color(0, 50, 200));
        }
        g.setColor(new Color(200, 100, 0));
        for (int i = 0; i < p2.size(); i++) {
            g.fillRect(p2.get(i).getX() * 12 + 1 + UNIVERSAL_OFFSET, p2.get(i).getY() * 12 + 1 + UNIVERSAL_OFFSET, 10, 10);
            if (i == 0) g.setColor(new Color(200, 0, 200));
        }
        g.setColor(new Color(255, 0, 0));
        g.fillRect(foodX * 12 + 1 + UNIVERSAL_OFFSET, foodY * 12 + 1 + UNIVERSAL_OFFSET, 10, 10);
        g.setColor(new Color(255, 255, 255));
        g.fillRect(220, 0, 240, 40);
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        g.drawString("Player 1 | " + p1Score + " : " + p2Score + " | Player 2", 225, 30);
    }

    public static void checkFood() {
        boolean move = false;
        if (p1.get(0).getX() == foodX && p1.get(0).getY() == foodY) {
            growSnake(1);
            move = true;
        }
        if (p2.get(0).getX() == foodX && p2.get(0).getY() == foodY) {
            growSnake(2);
            move = true;
        }
        if (move) {
            moveFood();
        }
    }

    public static void moveFood() {
        boolean onSnake = true;
        while (onSnake) {
            onSnake = false;
            foodX = (int)(Math.random() * 50);
            foodY = (int)(Math.random() * 50);
            for (int i = 0; i < p1.size(); i++) {
                if(p1.get(i).getX() == foodX && p1.get(i).getY() == foodY) {
                    onSnake = true;
                    break;
                }
            }
            if (!onSnake) {
                for (int i = 0; i < p2.size(); i++) {
                    if(p2.get(i).getX() == foodX && p2.get(i).getY() == foodY) {
                        onSnake = true;
                        break;
                    }
                }
            }
        }
    }

    public static void checkCollision() {
        SnakeHead p1Head = p1.get(0);
        SnakeHead p2Head = p2.get(0);
        if (p1Head.getX() == p2Head.getX() && p1Head.getY() == p2Head.getY()) {
            reset();
            return;
        }
        for (int i = 1; i < p2.size(); i++) {
            if (p1Head.getX() == p2.get(i).getX() && p1Head.getY() == p2.get(i).getY()) {
                killPlayer(1);
                return;
            }
            if (p2Head.getX() == p2.get(i).getX() && p2Head.getY() == p2.get(i).getY()) {
                killPlayer(2);
                return;
            }
        }
        for (int i = 1; i < p1.size(); i++) {
            if (p2Head.getX() == p1.get(i).getX() && p2Head.getY() == p1.get(i).getY()) {
                killPlayer(2);
                return;
            }
            if (p1Head.getX() == p1.get(i).getX() && p1Head.getY() == p1.get(i).getY()) {
                killPlayer(1);
                return;
            }
        }
        if (p1Head.getX() < 0 || p1Head.getX() > 49 || p1Head.getY() < 0 || p1Head.getY() > 49) {
            killPlayer(1);
        }
        if (p2Head.getX() < 0 || p2Head.getX() > 49 || p2Head.getY() < 0 || p2Head.getY() > 49) {
            killPlayer(2);
        }
    }

    public static void growSnake(int player) {
        if (!(player == 1 || player == 2)) return;
        ArrayList<SnakeHead> thisOne = player == 1 ? p1 : p2;
        SnakeHead currentTail = thisOne.get(thisOne.size() - 1);
        for (int i = 0; i < 10; i++) {
            currentTail = new SnakeBody(currentTail.getX(), currentTail.getY(), currentTail);
            thisOne.add(currentTail);
        }
    }

    public static void killPlayer(int player) {
        if (!(player == 1 || player == 2)) return;
        if (player == 1) {
            p2Score++;
        } else {
            p1Score++;
        }
        reset();
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (p1.get(0).getY() - 1 != p1.get(1).getY()) p1.get(0).setDirection(1);
                break;
            case KeyEvent.VK_DOWN:
                if (p1.get(0).getY() + 1 != p1.get(1).getY()) p1.get(0).setDirection(3);
                break;
            case KeyEvent.VK_LEFT:
                if (p1.get(0).getX() - 1 != p1.get(1).getX()) p1.get(0).setDirection(4);
                break;
            case KeyEvent.VK_RIGHT:
                if (p1.get(0).getX() + 1 != p1.get(1).getX()) p1.get(0).setDirection(2);
                break;
            case KeyEvent.VK_W:
                if (p2.get(0).getY() - 1 != p2.get(1).getY()) p2.get(0).setDirection(1);
                break;
            case KeyEvent.VK_S:
                if (p2.get(0).getY() + 1 != p2.get(1).getY()) p2.get(0).setDirection(3);
                break;
            case KeyEvent.VK_A:
                if (p2.get(0).getX() - 1 != p2.get(1).getX()) p2.get(0).setDirection(4);
                break;
            case KeyEvent.VK_D:
                if (p2.get(0).getX() + 1 != p2.get(1).getX()) p2.get(0).setDirection(2);
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}