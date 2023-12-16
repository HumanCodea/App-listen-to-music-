import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame implements ActionListener{

    JLabel label;
    JPanel panel, panel1;
    JButton[] buttons = new JButton[3];
    JButton startButton, stopButton, returnButton;
    File file;
    AudioInputStream audioInputStream;
    Clip clip;
    ImageIcon icon,newicon;
    Image seticon,imageSacle;

    public MyFrame() throws LineUnavailableException, UnsupportedAudioFileException, IOException{

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("App Listen Music");
        this.setSize(500,500);
        this.setLayout(new BorderLayout());

        startButton = new JButton("Play");
        stopButton = new JButton("Stop");
        returnButton = new JButton("Restart");

        buttons[0] = startButton;
        buttons[1] = stopButton;
        buttons[2] = returnButton;

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(150, 100));
        panel.setBackground(Color.GREEN);
        panel.setOpaque(true);
        panel.setLayout(new GridLayout(1, 3, 10, 10));

        panel1 = new JPanel();
        panel1.setBounds(0, 0, 500, 360);
        panel1.setLayout(new BorderLayout());

        // cach can chinh anh sao cho phu hop voi panel, label, frame,...
        icon = new ImageIcon("icon.png");
        seticon = icon.getImage();
        imageSacle = seticon.getScaledInstance(panel1.getWidth(), panel1.getHeight(), Image.SCALE_SMOOTH);
        newicon = new ImageIcon(imageSacle);

        label = new JLabel();
        label.setIcon(newicon);


        file = new File("Ryder.wav");
        audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        startButton.setBackground(Color.BLUE);
        stopButton.setBackground(Color.RED);
        returnButton.setBackground(Color.ORANGE);


        for(int i = 0; i < 3; i++){
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font("MV Boly", Font.BOLD, 25));
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setOpaque(true);
            buttons[i].setFocusable(false);
        }

        panel.add(startButton);
        panel.add(stopButton);
        panel.add(returnButton);
        panel1.add(label, BorderLayout.NORTH);
        
        this.add(panel1);
        this.add(panel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == startButton){
            clip.start();
        }

        if(e.getSource() == stopButton){
            clip.stop();
        }

        if(e.getSource() == returnButton){
            clip.setMicrosecondPosition(0);
        }
    } 
}
