package com.example.ridiom;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MyToolWindow {
    private JButton cancelButton;
    private JButton refactorButton;
    private JPanel mytable;
    private JPanel mytablecontent;
    private ArrayList<ArrayList<String>> codepairs;

    public JPanel gettable() {

        return this.mytable;
    }
    public MyToolWindow(ToolWindow toolWindow,ArrayList<ArrayList<String>> codepairs) {
        //toolWindow.hide(null)
        this.codepairs=codepairs;
        cancelButton.addActionListener(e -> {toolWindow.hide();});//toolWindow.remove();
        System.out.println("mytoolwindow update code pairs: "+codepairs);

        this.updateMyTable(codepairs);
        refactorButton.addActionListener(e -> {toolWindow.hide();
            System.out.println("code pairs of refactor button : "+codepairs);
        });
//        createUIComponents();

        mytable.getComponent(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("selected row click for mytable.getComponent:");
            }
        });
        mytable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("selected row click:");
            }
        });
//        mytable.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                super.focusGained(e);
//                JTable source = (JTable) e.getSource();
////                source.getSelectedRow()
//                int row = source.getSelectedRow();
//                System.out.println("selected row:" + row);
//            }
//        });

        mytablecontent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("selected row:");
                super.mouseClicked(e);
            }
        });
    }
    public JPanel getContent() {

        return this.mytablecontent;
    }

    public void updateMyTable(ArrayList<ArrayList<String>> codepairs) {
//        System.out.println("MyToolWindow mytable: "+codepairs);
        if (codepairs!=null) {
//            System.out.println("This is not null MyToolWindow mytable: " + codepairs);
            MyTable myTable1;
            myTable1 = new MyTable(codepairs);
            mytable = new JPanel();
            mytable.setLayout(new BorderLayout());

            JComponent a = myTable1.getBrowsersTable();

//        @NotNull JComponent tablecomponent = toolWindow.getComponent();
            mytable.add(myTable1.getBrowsersTable(), BorderLayout.CENTER);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
//        MyTable myTable1 = new MyTable();
//        System.out.println("MyToolWindow createUIComponents: "+codepairs);

        MyTable myTable1;
        if (codepairs!=null){
            myTable1 = new MyTable(codepairs);
        }
        else{
           myTable1 = new MyTable();
        }
        mytable = new JPanel();
        mytable.setLayout(new BorderLayout());

        JComponent a = myTable1.getBrowsersTable();
//        JScrollPane scrollPane = ScrollPaneFactory.createScrollPane(myTable);
//        panel.add(scrollPane, BorderLayout.CENTER);

//        @NotNull JComponent tablecomponent = toolWindow.getComponent();
        mytable.add(myTable1.getBrowsersTable(),BorderLayout.CENTER);



    }
//    private void $$$setupUI$$$() {
//        createUIComponents();
//
//        cancelButton = new JButton();
//        refactorButton= new JButton();
////        radioButton3.setText("RadioButton");
//
//    }
}
