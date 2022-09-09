package Main;

import org.jfree.ui.tabbedui.VerticalLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame implements ActionListener {

    JPanel leftSide = new JPanel();
    JLabel processes_list = new JLabel("Processes list");
    JPanel rightSide = new JPanel();
    JLabel Options = new JLabel("Options");

    JButton Add = new JButton("+");
    JButton Del = new JButton("-");
    JPanel process = new JPanel();
    JPanel start = new JPanel();

    ButtonGroup Algorithm = new ButtonGroup();

    public static JRadioButton AGAT = new JRadioButton("AGAT");
    public static JRadioButton PriorityScheduling = new JRadioButton("Priority Scheduling");
    public static JRadioButton shortestJobFirst = new JRadioButton("Shortest Job First");
    public static JRadioButton SRTF = new JRadioButton("Shortest-Remaining Time First");
    public static JPanel Algs = new JPanel();

    static String[] col = {"PID", "Name", "Arrival", "Burst", "Priority", "Quantum"};
    public static DefaultTableModel model = new DefaultTableModel(null, col);
    public static JTable processtable = new JTable(model);
    public static JLabel jLabel = new JLabel("Age:");
    public static JTextField Age = new JTextField("32");
    public static JLabel jLabel1 = new JLabel("Context Switching:");
    public static JTextField Contix = new JTextField("1");
    JButton run = new JButton("Run");

    Border lineBorder = BorderFactory.createLineBorder(Color.gray);

    GUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setVisible(true);
        this.setSize(815, 520);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0xe6e6e6));
        this.setTitle("CPU Scheduler Simulator");
        this.add(process);

        this.setLayout(null);
        this.setResizable(false);

        this.add(leftSide);

        Add.setPreferredSize(new Dimension(45, 20));
        Del.setPreferredSize(new Dimension(45, 20));
        process.add(Add);
        process.add(Del);
        process.setBackground(Color.white);
        process.setBounds(16, 16, 100, 25);

        leftSide.setBounds(15, 15, 385, 455);
        leftSide.setBackground(Color.white);
        leftSide.add(processes_list);
        leftSide.setBorder(lineBorder);

        this.add(start);
        this.add(rightSide);
        rightSide.setBounds(415, 15, 385, 455);
        rightSide.setBackground(new Color(0xe6e6e6));
        rightSide.add(Options);


        processtable.setBounds(17, 45, 380, 400);
        this.add(processtable);

        processes_list.setHorizontalTextPosition(JLabel.CENTER);
        processes_list.setVerticalTextPosition(JLabel.TOP);


        start.setBounds(500, 170, 200, 200);

        start.setBackground(new Color(0xe6e6e6));
        Algs.setBackground(new Color(0xe6e6e6));
        shortestJobFirst.setBackground(new Color(0xe6e6e6));
        PriorityScheduling.setBackground(new Color(0xe6e6e6));
        AGAT.setBackground(new Color(0xe6e6e6));
        SRTF.setBackground(new Color(0xe6e6e6));

        Algorithm.add(shortestJobFirst);
        Algorithm.add(PriorityScheduling);
        Algorithm.add(AGAT);
        Algorithm.add(SRTF);

        Algs.setLayout(new VerticalLayout());
        Algs.add(shortestJobFirst);
        Algs.add(PriorityScheduling);
        Algs.add(AGAT);
        Algs.add(SRTF);

        start.add(Algs);

        start.add(jLabel);

        start.add(Age);
        Age.setPreferredSize(new Dimension(110, 20));

        start.add(jLabel1);
        start.add(Contix);
        Contix.setPreferredSize(new Dimension(30, 20));

        start.setBorder(lineBorder);
        run.setPreferredSize(new Dimension(60, 20));
        start.add(run);

        Add.addActionListener(this);
        Del.addActionListener(this);
        run.addActionListener(this);
        processtable.setPreferredSize(new Dimension(380, 450));
        processtable.setShowVerticalLines(false);
        processtable.setShowHorizontalLines(false);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == run) {
            starter.run();
        } else if (e.getSource() == Add) {
            System.out.println("add");
            AddProcess frameText = new AddProcess();
            frameText.setVisible(true);
            frameText.pack();
            frameText.setLocationRelativeTo(null);
            frameText.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else if (e.getSource() == Del) {

            //Delete Selected Row
            int getSelectedRowForDeletion = processtable.getSelectedRow();

            //Check if their is a row selected
            if (getSelectedRowForDeletion >= 0) {
                while (getSelectedRowForDeletion >= 0) {
                    int ID = Integer.parseInt(model.getValueAt(getSelectedRowForDeletion, 0).toString());
                    System.out.println(ID);
                    for (Process p : starter.processes) {
                        if (p.getPID() == ID) {
                            starter.processes.remove(p);
                            break;
                        }
                    }
                    model.removeRow(getSelectedRowForDeletion);
                    getSelectedRowForDeletion = processtable.getSelectedRow();
                }
                JOptionPane.showMessageDialog(null, "Processes Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Unable To Delete");
            }

        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
