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

import java.awt.*;
import java.awt.event.*;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class AwtDemo {
    private Frame frame;
    private Label message;
    private Label headerLabel;
    private Label footerLabel;
    private Panel controlPanel;
    private Button button;
    private List list;
    private Panel listPanel;
    private TextArea textArea;
    private ScrollPane textPanel;

    public static void main(String... args) {
        AwtDemo awtDemo = new AwtDemo();
        awtDemo.show();
    }

    public AwtDemo() {
        frame = new Frame("Awt Demo Window");
        frame.setSize(new Dimension(500,500));
        frame.setLayout(new GridLayout (5,1));
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        headerLabel = new Label();
        headerLabel.setText("-- This is the header --");
        headerLabel.setAlignment(Label.CENTER);

        list = new List();
        list.add("John Mayer", 0);
        list.add("Frank Sinatra", 1);
        list.add("Seth MacFarlane", 2);
        list.add("Nina Simone", 3);
        list.add("BB King", 4);
        list.add("Peggy Lee", 5);

        listPanel = new Panel();
        listPanel.setLayout(new FlowLayout());
        listPanel.add(list);
        list.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    textArea.append(list.getSelectedItem() + "\n");
                }
            }
        });


        footerLabel = new Label();
        footerLabel.setText("-- This is the footer --");
        footerLabel.setAlignment(Label.CENTER);

        message = new Label();
        message.setAlignment(Label.CENTER);
        message.setText("This is a simple AWT window.");

        button= new Button("Bye Bye");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        controlPanel = new Panel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.add(button);

        textArea = new TextArea(50,10);
        textArea.setEditable(false);
        textPanel = new ScrollPane();
        textPanel.add(textArea);
        Panel txtPane = new Panel();
        txtPane.setLayout(new BorderLayout());
//        txtPane.add(textPanel, BorderLayout.CENTER);
        textArea.setBackground(Color.LIGHT_GRAY);

        frame.add(headerLabel);
        frame.add(listPanel);
        frame.add(txtPane);
        frame.add(controlPanel);
        frame.add(footerLabel);
    }

    public void show(){
        frame.setVisible(true);
    }
}
