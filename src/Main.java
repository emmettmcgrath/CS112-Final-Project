import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Main extends JPanel implements MouseListener{
    boolean selectedRedPiece = false;
    boolean selectedWhitePiece = false;
    int clickCount = 0;
    int WIDTH = 600;
    int HEIGHT = 600;
    int FPS = 60;
    static int [][] board = new int[8][8];
    static Checkers[] WhitePieces = new Checkers[12];
    static Checkers[] RedPieces = new Checkers[12];
    public static void main(String[] args){
        JFrame frame = new JFrame("Checkers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main game = new Main();
        frame.setContentPane(game);
        frame.pack();
        frame.setVisible(true);
        fillBoard();
        setRedCheckers();
        setWhiteCheckers();
    }
    public Main(){
        addMouseListener(this);
        //addMouseListener(this);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }
    public static void fillBoard(){
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                if ((i+j)%2==0){
                    board[i][j]=0;//white square (out of field of play)
                }
                else board[i][j]=1;//black square
            }
        }
    }
    public static void setRedCheckers(){
        for (int i = 0; i<3; i++){
            for (int j = 0; j<8; j++){
                if (board[i][j]==1){
                    board[i][j]=2;
                    for (int k = 0; k<12; k++){
                        RedPieces[k] = new Checkers(i,j,Color.RED,false);
                    }
                }
            }
        }
    }
    public static void setWhiteCheckers(){
        for (int i = 5; i<8; i++){
            for (int j = 0; j<8; j++){
                if (board[i][j]==1){
                    board[i][j]=3;
                    for (int k = 0; k<12; k++){
                        WhitePieces[k] = new Checkers(i,j,Color.WHITE,false);
                    }
                }
            }
        }
    }
    public void paintComponent(Graphics g){
        int cellWidth = WIDTH/8;
        int cellHeight = HEIGHT/8;
        int dx = 0;
        int dy = 0;
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                if(board[i][j]==0){
                    g.setColor(Color.WHITE);
                    g.fillRect(dx,dy,cellWidth,cellHeight);
                }
                else {
                    g.setColor(Color.BLACK);
                    g.fillRect(dx,dy,cellWidth,cellHeight);
                }
                if (board[i][j]==2){
                    g.setColor(Color.RED);
                    g.fillOval(dx,dy,cellWidth,cellHeight);
                }
                if (board[i][j]==3){
                    g.setColor(Color.WHITE);
                    g.fillOval(dx,dy,cellWidth,cellHeight);
                }
                if (j != 7) dx += cellWidth;
                else dx = 0;
            }
            dy += cellWidth;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickCount++;
        int x = e.getX();
        int y = e.getY();
        int cellWidth = WIDTH/8;
        int cellHeight = HEIGHT/8;
        int dx = y/cellWidth;
        int dy = x/cellHeight;
        System.out.println(dx +" " + dy);
        if (board[dx][dy]==2){
            System.out.println(dx + " " + dy + " red piece");
            selectedRedPiece = true;

            //System.out.println(clickCount);
        }
        if (board[dx][dy]==3){
            System.out.println(dx + " " + dy + " white piece");
            //selectedWhitePiece = true;
            //System.out.println(clickCount);
        }
        if (board[dx][dy]==1){
            System.out.println(dx + " " + dy + " available space");
            //System.out.println(clickCount);
        }
        if ((clickCount%2!=0)&&board[dx][dy]==2){
            System.out.println("Selected red");
            selectedRedPiece=true;
            selectedWhitePiece=false;
            board[dx][dy] = 1;
            System.out.println("odd click");
        }
        else if ((clickCount%2==0)&& board[dx][dy]==1 && selectedRedPiece){
            board[dx][dy] = 2;
            System.out.println("selected red: " + selectedRedPiece);
        }
        else if ((clickCount%2!=0)&&board[dx][dy]==3){
            System.out.println("Selected white");
            selectedWhitePiece=true;
            selectedRedPiece = false;
            board[dx][dy] = 1;
            System.out.println("odd click");
        }
        else if ((clickCount%2==0)&& board[dx][dy]==1 && selectedWhitePiece){
            board[dx][dy] = 3;
            System.out.println("selected white: " + selectedWhitePiece);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    class Runner implements Runnable {
        public void run() {
            while(true) {
                repaint();
                try {
                    Thread.sleep(1000/FPS);
                }
                catch(InterruptedException e){}
            }
        }
    }
}

