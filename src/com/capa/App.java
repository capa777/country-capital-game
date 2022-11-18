package com.capa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class App extends JFrame implements ActionListener {
    public static String contentpopup = " ";
    public static String contentpopuppoints="";

    public static String path="Europa.txt";
    public FileToObjectCompiler fileToObjectCompiler;
    ArrayList<String> answears = new ArrayList<>();
    int points =0;
    Popup helppopup = new Popup();

    public String contentinsidewindow = "";
    public boolean insidetext = false;
    public boolean consoletext = false;
    private final JMenu menu1 = new JMenu("Opcje");
    private final JMenu menu2 = new JMenu("Panstwa");
    private final JMenu menu3 = new JMenu("Stolice");
    private final JMenu menu4 = new JMenu("Pomoc");

    public JMenu getMenu4() { return menu4; }

    public JMenu getMenu1() {
        return menu1;
    }

    public JMenu getMenu2() {
        return menu2;
    }

    public JMenu getMenu3() {
        return menu3;
    }
    public Popuppoints pointspopup = new Popuppoints(getMenu1());

    JFrame frame = new JFrame();

    public App() throws InterruptedException {
        createGameFrame();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        checkClickedKey(e);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.clearRect(0,70, getSize().width, getSize().height);
        g.drawString(contentinsidewindow, getSize().width/2-(contentinsidewindow.length()*3)+8, getSize().height/2);
    }

    public void changefile() throws IOException {

        getMenu2().removeAll();
        getMenu3().removeAll();

        JFileChooser fileChooser = new JFileChooser();

        int response = fileChooser.showOpenDialog(null); // wyswietla plik do otwarcia

        if(response == 0){
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }

        countries();

    }
    public void countries(){
        fileToObjectCompiler = new FileToObjectCompiler(path);
        fileToObjectCompiler.getAnswers();
        ArrayList<Country> countries = fileToObjectCompiler.getAnswearobjects();
        ArrayList<String> co = new ArrayList<>();
        co.clear();
        ArrayList<String> cp = new ArrayList<>();
        cp.clear();
        for (int i = 0; i < countries.size(); i++) {
            co.add(countries.get(i).getCountry());
            cp.add(countries.get(i).getCapital());
        }
        Collections.shuffle(co);
        Collections.shuffle(cp);

        for (int i = 0; i < co.size(); i++) {
            menu2.add(new JMenuItem(co.get(i)));
            menu2.getItem(i).addActionListener(this);

            menu3.add(new JMenuItem(cp.get(i)));
            menu3.getItem(i).addActionListener(this);
        }
    }

    public void createGameFrame(){
        String message = "Domyslnie zostal zaladowany plik z panstwami i stolicami krajow europejskich. By to zmienic wybierz zmien plik w zakladce opcje";
        frame.setSize(800,125);
        frame.setLocation(550,90);
        frame.setLayout(new GridBagLayout());
        JLabel messageLabel = new JLabel(message);
        frame.add(messageLabel);
        frame.setVisible(true);
        try {

            setTitle("Panstwa i Miasta");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JMenuBar menuBar = new JMenuBar();
            menu1.add( new JMenuItem("wyswietlaj punkty"));
            menu1.add(new JMenuItem("wylacz wyswietlanie"));
            menu1.add(new JMenuItem("okno wewnetrzne"));
            menu1.add(new JMenuItem("okno zewnetrzne"));
            menu1.add(new JMenuItem("konsola"));
            menu1.add(new JMenuItem("wyjdz"));
            menu1.add(new JMenuItem("zmien plik"));
            menu4.add(new JMenuItem("samouczek"));
            menu4.getItem(0).addActionListener(this);
            for(int i =0; i<menu1.getItemCount(); i++){
                menu1.getItem(i).addActionListener(this);
            }

            countries();

            getMenu1().getItem(2).setEnabled(false);
            getMenu1().getItem(3).setEnabled(false);
            getMenu1().getItem(4).setEnabled(false);
            getMenu1().getItem(1).setEnabled(false);


            menuBar.add(menu1);
            menuBar.add(menu2);
            menuBar.add(menu3);
            menuBar.add(menu4);
            setJMenuBar(menuBar);

        } catch (Exception e) {
            e.printStackTrace();
        }
        setSize(440, 640);
        setVisible(true);
        setLocation(1920/2-220,1080/2-320);
    }

    public void checkClickedKey(ActionEvent e){
        if(e.getActionCommand().equals("zmien plik")){

            try {
                changefile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getActionCommand().equals("wyswietlaj punkty")){
            getMenu1().getItem(2).setEnabled(true);
            getMenu1().getItem(3).setEnabled(true);
            getMenu1().getItem(4).setEnabled(true);
            getMenu1().getItem(0).setEnabled(false);
            getMenu1().getItem(1).setEnabled(true);
        }
        else if(e.getActionCommand().equals("wylacz wyswietlanie")){
            getMenu1().getItem(2).setEnabled(false);
            getMenu1().getItem(3).setEnabled(false);
            getMenu1().getItem(4).setEnabled(false);
            getMenu1().getItem(0).setEnabled(true);
            getMenu1().getItem(1).setEnabled(false);
            insidetext = false;
            contentinsidewindow = "";
            pointspopup.isVisible =false;
            repaint();
            pointspopup.dispose();
            consoletext = false;
            //System.out.print("\033\143");
            for (int i = 0; i < 20; ++i) System.out.println();
            getMenu1().getItem(4).setBackground(getMenu4().getBackground());
            getMenu1().getItem(3).setBackground(getMenu4().getBackground());
            getMenu1().getItem(2).setBackground(getMenu4().getBackground());
        }
        else if(e.getActionCommand().equals("samouczek")){
            contentpopup = "gra polega na zaznaczaniu poprawnych par kraj - stolica";
            helppopup.isVisible =true;
            helppopup.refresh();
        }
        else if(e.getActionCommand().equals("okno wewnetrzne")){
            contentinsidewindow = "aktualna liczba punktow to: " + points;
            repaint();
            getMenu1().getItem(2).setBackground(Color.GREEN);
            insidetext= !insidetext;
            if(!insidetext){
                getMenu1().getItem(2).setBackground(getMenu4().getBackground());
                contentinsidewindow = "";
                repaint();
            }
        }
        else if(e.getActionCommand().equals("okno zewnetrzne")){
            pointspopup.isVisible =!pointspopup.isVisible;
            contentpopuppoints = "aktualna liczba punktow to:" + points;
            pointspopup.refresh();
            getMenu1().getItem(3).setBackground(Color.GREEN);
            if(!pointspopup.isVisible) getMenu1().getItem(3).setBackground(getMenu4().getBackground());
        }
        else if (e.getActionCommand().equals("wyjdz")) {
            System.out.println("Meg used="+(Runtime.getRuntime().totalMemory()-
                    Runtime.getRuntime().freeMemory())/(1000*1000)+"M");

            dispose();
            helppopup.dispose();
            pointspopup.dispose();
            frame.dispose();
        }
        else if (e.getActionCommand().equals("konsola")) {
            consoletext = !consoletext;
            getMenu1().getItem(4).setBackground(Color.GREEN);
            if(!consoletext) {
                getMenu1().getItem(4).setBackground(getMenu4().getBackground());
                //System.out.print("\033\143");
                for (int i = 0; i < 20; ++i) System.out.println();
            }
            if(consoletext) System.out.println("aktualna liczba punktow to:" + points);
        }
        else {
            fileToObjectCompiler = new FileToObjectCompiler(path);
            fileToObjectCompiler.getAnswers();
            ArrayList<Country> countries = fileToObjectCompiler.getAnswearobjects();
            countries = fileToObjectCompiler.getAnswearobjects();

            if (answears.size() == 2) answears.clear();
            answears.add(e.getActionCommand());
            if(answears.size() == 1){
                for(int i = 0; i<countries.size(); i++){
                    if(countries.get(i).getCountry().equals(answears.get(0))){
                        getMenu2().setEnabled(false);
                        break;
                    }
                    if(countries.get(i).getCapital().equals(answears.get(0))){
                        getMenu3().setEnabled(false);
                        break;
                    }
                }
            }

            if (answears.size() == 2) {
                for (Country go : countries) {
                    if (go.getCapital().equals(answears.get(0)) || go.getCapital().equals(answears.get(1))) {
                        if (go.getCountry().equals(answears.get(0)) || go.getCountry().equals(answears.get(1))) {
                            points++;
                            contentinsidewindow = "aktualna liczba punktow to: " + points;
                            contentpopuppoints = "aktualna liczba punktow to:" + points;
                            pointspopup.refresh();
                            if(insidetext) {
                                repaint();
                            }
                            break;
                        }else{
                            points = 0;
                            contentinsidewindow = "aktualna liczba punktow to: 0";
                            if(insidetext) {
                                repaint();
                            }
                            contentpopuppoints = "aktualna liczba punktow to:" + points;
                            pointspopup.refresh();
                        }
                    }
                }
                getMenu3().setEnabled(true);
                getMenu2().setEnabled(true);
                if(consoletext) System.out.println("aktualna liczba punktow to: " + points);
            }
        }
    }
}