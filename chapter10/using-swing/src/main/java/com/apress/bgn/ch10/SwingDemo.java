/*
Freeware License, some rights reserved

Copyright (c) 2018 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.bgn.ch10;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class SwingDemo extends JFrame {
    //private static  String[] data = {"John Mayer", "Frank Sinatra", "Seth MacFarlane", "Nina Simone", "BB King", "Peggy Lee"};
    private static  String[] data;
    private JPanel mainPanel;
    private JList<String> list;
    private JTextArea textArea;
    private JButton exitButton;

    public SwingDemo(String title) {
        super(title);
        mainPanel = (JPanel) this.getContentPane();
        textArea = new JTextArea(50, 10);

        //NORTH
        list = new JList<>(data);
        list.addListSelectionListener(new LFListener(this));
        mainPanel.add(list, BorderLayout.NORTH);

        //CENTER
        JScrollPane txtPanel = new JScrollPane(textArea);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setEditable(false);
        mainPanel.add(txtPanel, BorderLayout.CENTER);

        //SOUTH
        exitButton = new JButton("Bye Bye!");
        exitButton.addActionListener(e -> System.exit(0));

        JPanel exitPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        exitPanel.setLayout(flowLayout);
        exitPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        exitPanel.add(exitButton);

        mainPanel.add(exitPanel, BorderLayout.SOUTH);
    }

    public static void main(String... args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        data = new String[looks.length];
        int i =0;
        for (UIManager.LookAndFeelInfo look : looks) {
            data[i++] = look.getClassName();
        }
        //data[i] = "com.seaglasslookandfeel.SeaGlassLookAndFeel";

        SwingDemo swingDemo = new SwingDemo("Swing Demo Window");
        swingDemo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        swingDemo.setSize(new Dimension(500, 500));

        swingDemo.setVisible(true);
    }

    private class LFListener implements ListSelectionListener {
        private JFrame parent;

        public LFListener(JFrame swingDemo) {
            parent = swingDemo;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                textArea.append(list.getSelectedValue() + "\n");
                try {
                    UIManager.setLookAndFeel(list.getSelectedValue());
                    Thread.sleep(1000);
                    parent.repaint();
                } catch (Exception ee) {
                    System.err.println(" Could nto set look and feel! ");
                }
            }
        }
    }
}

