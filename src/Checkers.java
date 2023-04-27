import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class Checkers {
    int row;
    int column;
    Color color;
    boolean isKing;
    public Checkers(int row, int column, Color color, boolean isKing){
        this.row = row;
        this.column = column;
        this.color = color;
        this.isKing = false;
    }

}
