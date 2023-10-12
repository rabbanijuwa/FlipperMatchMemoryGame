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
    private int firstCardIndex;
    private int secondCardIndex;

    public MemoryGame() {
        setTitle("Picture Memory Game");
        setSize(2000, 2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imagePaths = new ArrayList<>();

        imagePaths.add("avacado.png");
        imagePaths.add("peach.png");
        imagePaths.add("grapes.png");
        imagePaths.add("pineapple.png");
        imagePaths.add("watermelon.png");
        imagePaths.add("orangejuice.png");
        imagePaths.add("lemon.png");
        imagePaths.add("banana.png");
        imagePaths.add("apple.png");
        imagePaths.add("strawberry.png");
        imagePaths.add("mango.png");
        imagePaths.add("orange.png");
        imagePaths.add("avacado.png");
        imagePaths.add("peach.png");
        imagePaths.add("grapes.png");
        imagePaths.add("pineapple.png");
        imagePaths.add("watermelon.png");
        imagePaths.add("orangejuice.png");
        imagePaths.add("lemon.png");
        imagePaths.add("banana.png");
        imagePaths.add("apple.png");
        imagePaths.add("strawberry.png");
        imagePaths.add("mango.png");
        imagePaths.add("orange.png");

        cardImages = new ArrayList<>();
        for (String imagePath : imagePaths) {
            cardImages.add("");
        }

        Collections.shuffle(imagePaths);
        Collections.shuffle(cardImages);

        JPanel cardPanel = new JPanel(new GridLayout(4, 6));
        cardButtons = new JButton[24];

        for (int i = 0; i < cardButtons.length; i++) {
            final int index = i;
            cardButtons[i] = new JButton();
            cardButtons[i].setIcon(new ImageIcon("nothing.png"));
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

            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (imagePaths.get(firstCardIndex).equals(imagePaths.get(secondCardIndex))) {
                       // cardButtons[firstCardIndex].setIcon(null);
                      //  cardButtons[secondCardIndex].setIcon(null);
                        cardButtons[firstCardIndex].setIcon(new ImageIcon("blue.png"));
                        cardButtons[secondCardIndex].setIcon(new ImageIcon("blue.png"));
                        cardImages.set(firstCardIndex, null);
                        cardImages.set(secondCardIndex, null);
                        numberOfMatches++;

                        if (numberOfMatches == imagePaths.size() / 2) {
                            JOptionPane.showMessageDialog(null, "Congratulations! You've won!");
                            System.exit(0);
                        }
                    } else {
                        cardButtons[firstCardIndex].setIcon(new ImageIcon("nothing.png"));
                        cardButtons[secondCardIndex].setIcon(new ImageIcon("nothing.png"));
                    }
                    firstCardIndex = -1;
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MemoryGame().setVisible(true);
            }
        });
    }


}

