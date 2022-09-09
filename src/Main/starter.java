package Main;

import AGAT.AGAT;
import PriorityScheduling.PriorityScheduling;
import SRTF.SRTF;
import shortestJobFirst.shortestJobFirst;

import javax.swing.*;
import java.util.ArrayList;

public class starter {

    public static ArrayList<Process> processes = new ArrayList<>();

    public static void run() {
        PlotWindow Window;
        ArrayList<Process> processes1 = new ArrayList<>();
//        processes1.add(new Process("p1", 0, 7, 4, 4, 0));
//        processes1.add(new Process("P2", 2, 4, 9, 3, 1));
//        processes1.add(new Process("P3", 4, 1, 3, 5, 2));
//        processes1.add(new Process("P4", 5, 4, 8, 2, 3));

        for (Process p : processes) {
            processes1.add(p.clone());
        }

        if (GUI.shortestJobFirst.isSelected()) {
            Window = new PlotWindow("shortestJobFirst", new shortestJobFirst().start(processes1, Integer.parseInt(GUI.Age.getText())));
        } else if (GUI.PriorityScheduling.isSelected()) {
            Window = new PlotWindow("PriorityScheduling", new PriorityScheduling().start(processes1, Integer.parseInt(GUI.Contix.getText()), Integer.parseInt(GUI.Age.getText())));
        } else if (GUI.AGAT.isSelected()) {
            Window = new PlotWindow("AGAT", new AGAT().start(processes1));
            Window.message = AGAT.message.append(Window.message);
        } else if (GUI.SRTF.isSelected()) {
            Window = new PlotWindow("SRTF", new SRTF().start(processes1, Integer.parseInt(GUI.Contix.getText()), Integer.parseInt(GUI.Age.getText())));
        } else {
            JOptionPane.showMessageDialog(null,
                    "Please choose a Scheduler.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        Window.setVisible(true);
        Window.pack();
        Window.setLocationRelativeTo(null);

        Info info = new Info(Window.type, Window.message);
        info.setVisible(true);
        info.pack();
        info.setLocationRelativeTo(null);
    }

}
//4
//        P1 0 17 4 4
//        P2 3 6 9 3
//        P3 4 10 3 5
//        P4 29 4 8 2