import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Popuppoints extends JFrame implements ActionListener {
    boolean wid=false;
    JMenu jmenu;

    public final JMenu menu1 = new JMenu("Wyjdz");
    JMenuBar menuBar = new JMenuBar();
        public Popuppoints (JMenu jmenu) {
        super("Twoja liczba punktow");
        try {
            this.jmenu = jmenu;
            menu1.add( new JMenuItem("Wyjdz"));
            menu1.getItem(0).addActionListener(this);
            setVisible(wid);
            menuBar.add(menu1);
            setJMenuBar(menuBar);

        } catch (Exception e) {
            e.printStackTrace();
        }
        setSize(300, 300);

    }

    public void paint(Graphics gDC) {
        gDC.clearRect(20, 20, getSize().width, getSize().height);
        gDC.drawString (App.contentpopuppoints, 50, getHeight()/2);
    }
    void Refresh(){
        setVisible(wid);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Wyjdz")) {
                jmenu.getItem(3).setBackground(jmenu.getItem(0).getBackground());
                wid = false;
                dispose();
            }
    }

    @Override
    protected void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            jmenu.getItem(3).setBackground(jmenu.getItem(0).getBackground());
            dispose();
            wid = false;
        }
    }
}
