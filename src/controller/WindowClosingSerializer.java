package controller;

import model.Model;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

class WindowClosingSerializer implements WindowListener {
    private final ThreadControl threadControl;

    WindowClosingSerializer(ThreadControl threadControl) {
        this.threadControl = threadControl;
    }

    @Override
    public void windowOpened(WindowEvent e) { }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            threadControl.stop();
            FileOutputStream fs = new FileOutputStream("ParkingLot.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            Model.close(Calendar.getInstance()); //save the date of exit as the current date
            os.writeObject(Model.getInstance());
            os.close();
        } catch (Exception ex) {
            System.out.println("trouble");
        }
    }

    @Override
    public void windowClosed(WindowEvent e) { }

    @Override
    public void windowIconified(WindowEvent e) { }

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) { }

    @Override
    public void windowDeactivated(WindowEvent e) { }
}
