import javax.swing.*;
import java.awt.*;

public class ChessFrame extends JFrame{
    int field;
    char color;

    // Zustandsvariablen
    private boolean drawRequested = false; // Zustand für den Draw-Button
    private Timer whiteTimer, blackTimer;
    private int whiteTime = 600; // 10 Minuten
    private int blackTime = 600; // 10 Minuten

    public ChessFrame(){
        setTitle("Chess with Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        //Titel Stuff
        JLabel titleLabel = new JLabel("Chess");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        //Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,3));

        JPanel p1 = new JPanel();
        p1.setBorder(BorderFactory.createEmptyBorder(0, 0, 150, 0));
        JPanel p2 = new JPanel();
        p2.setBorder(BorderFactory.createEmptyBorder(0, 0, 150, 0));
        JPanel p3 = new JPanel();
        p3.setBorder(BorderFactory.createEmptyBorder(0, 0, 150, 0));

        mainPanel.add(p1);
        mainPanel.add(p2);
        mainPanel.add(p3);

        // Panel Center left
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS)); // Vertikale Anordnung
        p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Innenabstand

        // Überschrift
        JLabel turnLabel = new JLabel("Turn: black/white");
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Zentrieren
        p1.add(turnLabel);

        // Timer Panel
        JPanel timerPanel = new JPanel(new GridLayout(2, 3, 5, 5)); // Abstand zwischen Feldern
        timerPanel.setMaximumSize(new Dimension(150, 100)); // Maximalgröße des Timer Panels
        p1.add(Box.createRigidArea(new Dimension(0, 10))); // Abstand zwischen Überschrift und Timer Panel
        p1.add(timerPanel);

        // Timer Felder
        JPanel cp1 = new JPanel();
        JPanel cp2 = new JPanel();
        JPanel cp3 = new JPanel();
        JPanel cp4 = new JPanel();
        JLabel tp1 = new JLabel("10:00");
        JLabel tp2 = new JLabel("10:00");

        cp1.setPreferredSize(new Dimension(40, 40)); // Kleinere Felder
        cp2.setPreferredSize(new Dimension(40, 40));
        cp1.setBackground(Color.BLACK);
        cp2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        timerPanel.add(cp1);
        timerPanel.add(cp3);
        timerPanel.add(cp2);
        timerPanel.add(tp1);
        timerPanel.add(cp4);
        timerPanel.add(tp2);

        //Panel Center mid
        JPanel playFieldPanel = new JPanel(new GridLayout(9, 9)) {
            public Dimension getPreferredSize() {
                int size = Math.min(getParent().getWidth(), getParent().getHeight());
                return new Dimension(size, size);
            }
        };
        p2.add(playFieldPanel);

        // Schachbrett erstellen
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (row == 0 && col == 0) {
                    playFieldPanel.add(new JLabel(""));
                } else if (row == 0) {
                    playFieldPanel.add(new JLabel(String.valueOf((char) ('A' + col - 1)), SwingConstants.CENTER));
                } else if (col == 0) {
                    playFieldPanel.add(new JLabel(String.valueOf(9 - row), SwingConstants.CENTER));
                } else {
                    JPanel square = new JPanel();
                    if ((row + col) % 2 == 0) {
                        square.setBackground(Color.WHITE);
                    } else {
                        square.setBackground(Color.BLACK);
                    }
                    square.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Außenlinie für jedes Feld
                    playFieldPanel.add(square);
                }
            }
        }

        


        //Panel Center right
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS)); // Vertikale Anordnung
        JLabel ue2 = new JLabel("Spielverlauf:");
        ue2.setAlignmentX(Component.CENTER_ALIGNMENT); // Zentrieren

        JPanel historyPanel = new JPanel(new GridLayout(0, 1)); // Unbegrenzt viele Zeilen, 1 Spalte
        historyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Innenabstand
        

        p3.add(ue2);



        //Bottom Buttons 
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10)); // Abstand: 20px horizontal, 10px vertikal

        JButton drawButton = new JButton("Draw");
        JButton endTurnButton = new JButton("Start");
        JButton resetButton = new JButton("Reset");

        // Timer erstellen
        whiteTimer = new Timer(1000, e -> {
            whiteTime--;
            tp2.setText(formatTime(whiteTime));
            if (whiteTime <= 0) {
                endGame("Black wins! White ran out of time.");
            }
        });

        blackTimer = new Timer(1000, e -> {
            blackTime--;
            tp1.setText(formatTime(blackTime));
            if (blackTime <= 0) {
                endGame("White wins! Black ran out of time.");
            }
        });

        drawButton.addActionListener(e -> {
            if (!drawRequested) {
                // Erster Spieler fordert ein Unentschieden an
                drawRequested = true;
                drawButton.setText("Confirm Draw"); // Button-Text ändern

                // Timer stoppen und zum nächsten Spieler wechseln
                if (turnLabel.getText().equals("Turn: black")) {
                    blackTimer.stop();
                    whiteTimer.start();
                    turnLabel.setText("Turn: white");
                } else {
                    whiteTimer.stop();
                    blackTimer.start();
                    turnLabel.setText("Turn: black");
                }
            } else {
                // Zweiter Spieler stimmt dem Unentschieden zu
                dispose(); // Schließt das aktuelle Fenster
                JFrame drawFrame = new JFrame("Game Over");
                drawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                drawFrame.setSize(400, 200);
                drawFrame.setLocationRelativeTo(null); // Zentriert das Fenster
                JLabel message = new JLabel("Keinen Gewinner. Unentschieden.", SwingConstants.CENTER);
                message.setFont(new Font("Arial", Font.BOLD, 16));
                drawFrame.add(message);
                drawFrame.setVisible(true);
            }
        });

        endTurnButton.addActionListener(e -> {
            if (endTurnButton.getText().equals("Start")) {
                endTurnButton.setText("End Turn");
                whiteTimer.start(); // Weißer Spieler beginnt
                turnLabel.setText("Turn: white");
            } else {
                if (turnLabel.getText().equals("Turn: black")) {
                    blackTimer.stop();
                    whiteTimer.start();
                    turnLabel.setText("Turn: white");
                } else {
                    whiteTimer.stop();
                    blackTimer.start();
                    turnLabel.setText("Turn: black");
                }
            }
            drawRequested = false;
            drawButton.setText("Draw");
        });

        resetButton.addActionListener(e -> {
            dispose(); // Schließt das aktuelle Fenster
            ChessFrame newFrame = new ChessFrame();
            newFrame.setLocationRelativeTo(null); // Zentriert das neue Fenster
            newFrame.setVisible(true);
        });

        bottomPanel.add(drawButton);
        bottomPanel.add(endTurnButton);
        bottomPanel.add(resetButton);



        add(titleLabel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setResizable(true);
        setLocationRelativeTo(null); // Zentriert das Fenster beim Start
        pack();

    }

    // Hilfsmethoden
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    private void endGame(String message) {
        dispose();
        JFrame endFrame = new JFrame("Game Over");
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setSize(400, 200);
        endFrame.setLocationRelativeTo(null);
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        endFrame.add(label);
        endFrame.setVisible(true);
    }
}
