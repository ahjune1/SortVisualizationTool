package sortVisualizer;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    SortManager s;
    JLabel jLabel;
    int w;
    public Panel(SortManager s, int windowWidth) {
        this.s = s;
        this.w = windowWidth * 7 / 8;
        jLabel = new JLabel("Sorting...");
        jLabel.setForeground(new Color(100, 0, 0));
        jLabel.setVisible(false);
        this.add(jLabel);
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (s.sorting()) {
            jLabel.setVisible(true);
        } else {
            jLabel.setVisible(false);
        }
        paintRectangles(g);
    }

    private void paintRectangles(Graphics g) {
        int[] numArr = s.getNumArr();
        int count = numArr.length;
        for (int index = 0; index < count; index++) {
            g.setColor(Color.GREEN);
            if (s.sorting() && (index == s.getCompareIndex1() || index == s.getCompareIndex2())) {
                g.setColor(Color.RED);
            }
            g.fillRect(w * 1 / 14 + index * w / count, w * 9 / 14 - (numArr[index] * w * 4 / (7 * count)), (w / count), (numArr[index] * w * 4 / (7 * count)));
            if (count <= 350) {
                g.setColor(Color.BLACK);
                g.drawRect(w * 1 / 14 + index * w / count, w * 9 / 14 - (numArr[index] * w * 4 / (7 * count)), (w / count), (numArr[index] * w * 4 / (7 * count)));
            }
        }
    }
}
