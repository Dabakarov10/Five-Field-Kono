import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class GUI implements ActionListener {
    FFV_game ffvGame = new FFV_game();
    JButton[][] button = new JButton[5][5];
    JFrame frame;

    public GUI() {
        frame = new JFrame("Five Field Kono");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                button[i][j] = new JButton(String.valueOf(ffvGame.board[i][j]));
                button[i][j].addActionListener(this);
                frame.add(button[i][j]);
            }
        }

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getContentPane().setBackground(Color.blue);
    }

    public static void main(String[] args) {
        new GUI();
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
                else if (!ffvGame.MakeMove(from_i, from_j, to_i, to_j)) System.out.println("***cant to it!***");
                ffvGame.printBoard();
            } while (true && !ffvGame.Victory());
        } catch (Exception e) {
            System.out.print("ERROR__while(flag == 1)");
        }


    }
}
