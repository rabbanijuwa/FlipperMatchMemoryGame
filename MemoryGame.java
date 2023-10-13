import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class MemoryGame extends JFrame {
    private ArrayList<String> imagePaths;
    private ArrayList<String> cardImages;
    private JButton[] cardButtons;
    private int numberOfMatches;
    private int firstCardIndex = -1;
    private int secondCardIndex;
    private int moves;
    private Timer timer;
    private int gridSize;


    public MemoryGame(int gridSize) {
        this.gridSize = gridSize;
        setTitle("Picture Memory Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imagePaths = new ArrayList<>();
        cardImages = new ArrayList<>();
        numberOfMatches = 0;
        firstCardIndex = -1;
        secondCardIndex = -1;
        moves = 0;

        initializeImagePaths();
        initializeCardImages();

        JPanel cardPanel = new JPanel(new GridLayout(this.gridSize / 6, 6));
        cardButtons = new JButton[this.gridSize];

        for (int i = 0; i < cardButtons.length; i++) {
            final int index = i;
            cardButtons[i] = new JButton();
            cardButtons[i].setIcon(new ImageIcon("cardsback.png"));
            cardButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleCardClick(index);
                }
            });
            cardPanel.add(cardButtons[i]);
        }

        add(cardPanel);
    }

    private void initializeImagePaths() {
        imagePaths.add("avacado.png");
        imagePaths.add("peach.png");
        imagePaths.add("grapes.png");
        imagePaths.add("pineapple.png");
        imagePaths.add("orangejuice.png");
        imagePaths.add("strawberry.png");
        imagePaths.add("avacado.png");
        imagePaths.add("peach.png");
        imagePaths.add("grapes.png");
        imagePaths.add("pineapple.png");
        imagePaths.add("orangejuice.png");
        imagePaths.add("strawberry.png");

        imagePaths.addAll(imagePaths);

        // Shuffle the image paths
        Collections.shuffle(imagePaths);
    }
    private void initializeCardImages() {
        // Initialize cardImages based on the grid size
        for (int i = 0; i < gridSize; i++) {
            cardImages.add(imagePaths.get(i));
        }
        Collections.shuffle(cardImages);
    }


    private void handleCardClick(int index) {
        if (cardButtons[index].getIcon() == null) {
            return; // Already matched card, do nothing
        }

        if (firstCardIndex == -1) {
            firstCardIndex = index;
            cardButtons[firstCardIndex].setIcon(new ImageIcon(imagePaths.get(index)));
        } else {
            secondCardIndex = index;
            cardButtons[secondCardIndex].setIcon(new ImageIcon(imagePaths.get(index)));

            // Increment the moves
            moves++;

            timer = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (imagePaths.get(firstCardIndex).equals(imagePaths.get(secondCardIndex))) {
                        cardButtons[firstCardIndex].setIcon(null);
                        cardButtons[secondCardIndex].setIcon(null);
                        cardImages.set(firstCardIndex, null);
                        cardImages.set(secondCardIndex, null);
                        numberOfMatches++;

                        if (gridSize == 12 && numberOfMatches == 6) {
                            JOptionPane.showMessageDialog(null, "Congratulations! You've won in " + moves + " moves!");
                            resetGame();
                        } else if (gridSize == 24 && numberOfMatches == 12) {
                            JOptionPane.showMessageDialog(null, "Congratulations! You've won in " + moves + " moves!");
                            resetGame();
                        }
                    }else {
                        cardButtons[firstCardIndex].setIcon(new ImageIcon("cardsback.png"));
                        cardButtons[secondCardIndex].setIcon(new ImageIcon("cardsback.png"));
                    }
                    firstCardIndex = -1;
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void resetGame() {
        // Reset the game by creating a new MemoryGame instance
        // You can also provide an option for the user to start a new game
        new MemoryGame(gridSize).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Prompt the user for the grid size (12 cards or 24 cards)
                String gridSizeInput = JOptionPane.showInputDialog("Enter the grid size (12 or 24):");
                int gridSize = Integer.parseInt(gridSizeInput);

                if (gridSize != 12 && gridSize != 24) {
                    JOptionPane.showMessageDialog(null, "Invalid grid size. Please enter 12 or 24.");
                    return;
                }

                new MemoryGame(gridSize).setVisible(true);
            }
        });
    }
}
