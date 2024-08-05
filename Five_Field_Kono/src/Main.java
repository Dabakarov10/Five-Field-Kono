import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.Scanner;

public class Main implements ActionListener {
    FFV_game ffvGame = new FFV_game();
    JButton[][] button = new JButton[5][5];
    JFrame frame;
    int[][] movesArray = new int[2][2];
    /* movesArray:
     *  {{from_i,from_j},
     *   {to_i,to_j}}
     * */
    int movesArrayMone = 0;

    public Main() {
        frame = new JFrame("Five Field Kono");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setMinimumSize(new Dimension(800, 800));
        frame.setMaximumSize(new Dimension(800, 800));
        frame.setLayout(new GridLayout(5, 5,1,1));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (ffvGame.board[i][j] == 'w')
                    button[i][j] = new JButton(new ImageIcon("white_dot.png"));
                else if (ffvGame.board[i][j] == 'b')
                    button[i][j] = new JButton(new ImageIcon("black_dot.png"));
                else
                    button[i][j] = new JButton();
                button[i][j].setBackground(new Color(190, 75, 0));
                button[i][j].addActionListener(this);
                frame.add(button[i][j]);
            }
        }
        frame.setVisible(true);

    }

    public void setBoardToButtons() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (ffvGame.board[i][j] == 'w')
                    button[i][j].setIcon(new ImageIcon("white_dot.png"));
                else if (ffvGame.board[i][j] == 'b')
                    button[i][j].setIcon(new ImageIcon("black_dot.png"));
                else
                    button[i][j].setIcon(new ImageIcon());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (e.getSource() == button[i][j]) {
                    if (movesArrayMone % 2 == 0 && movesArrayMone != 0) {
                        makeMovesButtons();
                        movesArrayMone = 0;
                    } else {
                        movesArray[movesArrayMone % 2][0] = i;
                        movesArray[movesArrayMone % 2][1] = j;
                        movesArrayMone++;
                        makeMovesButtons();
                    }
                }
            }
        }
        System.out.println(movesArray[0][0] + "" + movesArray[0][1] + "" + movesArray[1][0] + "" + movesArray[1][1]);
        System.out.println(movesArrayMone);
    }

    private void makeMovesButtons() {
        if (!ffvGame.MakeMove(movesArray[0][0], movesArray[0][1], movesArray[1][0], movesArray[1][1]))
            System.out.println("***Cant Do it!***");
        else {
            ffvGame.printBoard();
            setBoardToButtons();
        }
    }

    public static void main(String[] args) {
        new Main();
        init1();
    }

    public static void init1() {
        FFV_game ffvGame = new FFV_game();
        ffvGame.printBoard();
        Scanner scanner = new Scanner(System.in);
        try {
            do {
                ffvGame.whose_turn();
                int from_i, from_j, to_i, to_j;
                ///*
                System.out.println("from index i,j");
                int ezer = scanner.nextInt();
                from_i = ezer / 10;
                from_j = ezer % 10;
                System.out.println("to index i,j");
                ezer = scanner.nextInt();
                to_i = ezer / 10;
                to_j = ezer % 10;
                if (ezer == 100)
                    ffvGame.cheatBWin();

                    //*/
                else if (!ffvGame.MakeMove(from_i, from_j, to_i, to_j))
                    System.out.println("***cant to it!***");
                ffvGame.printBoard();
            } while (true && !ffvGame.Victory());
        } catch (Exception e) {
            System.out.print("ERROR__while(flag == 1)");
        }


    }
}
