public class Main {
  private JPanel boardPanel;
    private JLabel[][] squares;

    public Main() {
        setTitle("Checkers Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));
        getContentPane().add(boardPanel);

        squares = new JLabel[8][8];
        boolean dark = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new JLabel();
                squares[i][j].setOpaque(true);
                squares[i][j].setPreferredSize(new Dimension(75, 75));
                if (dark) {
                    squares[i][j].setBackground(Color.BLACK);
                } else {
                    squares[i][j].setBackground(Color.WHITE);
                }
                dark = !dark;
                boardPanel.add(squares[i][j]);
            }
            dark = !dark;
        }

        // TODO: add checkers pieces to the board

        boardPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / (boardPanel.getHeight() / 8);
                int col = e.getX() / (boardPanel.getWidth() / 8);

                // TODO: handle the user's move
            }
        });
    }
}
