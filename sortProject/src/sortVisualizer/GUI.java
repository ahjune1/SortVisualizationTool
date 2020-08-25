import sortVisualizer.Panel;
import sortVisualizer.SortManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
    private JButton selectionSortButton, bubbleSortButton, mergeSortButton, quickSortButton, stopButton, insertionSortButton, heapSortButton;
    private JSlider numSlider, spdSlider;
    private JLabel jLabel, jLabel2;
    private Panel jPanel;
    private JPanel jPanel2;
    private Thread sortThread;
    private SortManager s;
    private int windowWidth;
    private int[] numOptions = new int[]{10, 20, 35, 50, 70, 100, 175, 350, 700};
    private int[] speedOptions = new int[]{1000, 100, 10, 1};

    public GUI() {
        //creates the frame
        super("Sort Visualization Tool");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        if (screen.height > 1300) {
            //for higher screen resolution
            this.setSize(1600, 1200);
            windowWidth = 1600;
        } else {
            //for lower screen resolution
            this.setSize(800, 600);
            windowWidth = 800;
        }
        this.getContentPane().setBackground(new Color(200, 200, 200));
        this.setResizable(false);

        //initializes SortManager
        s = new SortManager();
        s.setCount(100);

        //creates the visualization panel
        jPanel = new Panel(s, windowWidth);
        s.setPanel(jPanel);
        jPanel.setBackground(new Color(150, 150, 150));


        //creates the control panel
        jPanel2 = new JPanel(new GridLayout(2, 6, 5, 5));
        jPanel2.setBackground(new Color(100, 100, 100));
        jPanel2.setBorder(BorderFactory.createBevelBorder(1));

        //creates components
        //buttons:
        selectionSortButton = new JButton("selectionSort");
        selectionSortButton.addActionListener(this);

        bubbleSortButton = new JButton("bubbleSort");
        bubbleSortButton.addActionListener(this);

        insertionSortButton = new JButton("insertionSort");
        insertionSortButton.addActionListener(this);

        mergeSortButton = new JButton("mergeSort");
        mergeSortButton.addActionListener(this);

        quickSortButton = new JButton("quickSort");
        quickSortButton.addActionListener(this);

        heapSortButton = new JButton("heapSort");
        heapSortButton.addActionListener(this);

        stopButton = new JButton("Force Stop");
        stopButton.addActionListener(this);

        //labels:
        jLabel = new JLabel("# of elements:", SwingConstants.RIGHT);
        jLabel.setForeground(Color.WHITE);

        jLabel2 = new JLabel("Speed:", SwingConstants.RIGHT);
        jLabel2.setForeground(Color.WHITE);

        //sliders:
        numSlider = new JSlider(1, 9, 6);
        numSlider.setMajorTickSpacing(1);
        numSlider.setPaintTicks(true);
        numSlider.addChangeListener(e -> {
            if (!s.sorting() && !numSlider.getValueIsAdjusting()){
                s.setCount(numOptions[numSlider.getValue() - 1]);
            }
        });

        spdSlider = new JSlider(1, 4, 4);
        spdSlider.setMajorTickSpacing(1);
        spdSlider.setPaintTicks(true);
        spdSlider.addChangeListener(e -> {
            if (!spdSlider.getValueIsAdjusting()){
                s.setSpeed(speedOptions[spdSlider.getValue() - 1]);
            }
        });

        //adding everything to the bottom "control" panel
        jPanel2.add(selectionSortButton);
        jPanel2.add(bubbleSortButton);
        jPanel2.add(insertionSortButton);
        jPanel2.add(jLabel);
        jPanel2.add(numSlider);
        jPanel2.add(stopButton);
        jPanel2.add(mergeSortButton);
        jPanel2.add(quickSortButton);
        jPanel2.add(heapSortButton);
        jPanel2.add(jLabel2);
        jPanel2.add(spdSlider);

        //adding both panels to the GUI frame
        add(jPanel, BorderLayout.CENTER);
        add(jPanel2, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bubbleSortButton && !s.sorting()) {
            sortThread = new Thread(() -> s.bubbleSort());
            sortThread.start();
        } else if (e.getSource() == selectionSortButton && !s.sorting()) {
            sortThread = new Thread(() -> s.selectionSort());
            sortThread.start();
        } else if (e.getSource() == insertionSortButton && !s.sorting()) {
            sortThread = new Thread(() -> s.insertionSort());
            sortThread.start();
        } else if (e.getSource() == mergeSortButton && !s.sorting()) {
            sortThread = new Thread(() -> s.mergeSort());
            sortThread.start();
        } else if (e.getSource() == quickSortButton && !s.sorting()) {
            sortThread = new Thread(() -> s.quickSort());
            sortThread.start();
        } else if (e.getSource() == heapSortButton && !s.sorting()) {
            sortThread = new Thread(() -> s.heapSort());
            sortThread.start();
        } else if (e.getSource() == stopButton && s.sorting()) {
            sortThread.stop();
            s.stop();
        }
    }
}
