package com.example.ridiom;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.wm.ToolWindow;
import jdk.jshell.Diag;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class InterpreterWindow {
    private JButton OKButton;
    private JButton button1;
    private JTextField setInterpreterPathPython3TextField;
    private JPanel panel1;
    public  int satus=0;

    public JTextField getSetInterpreterPathPython3TextField() {
        return setInterpreterPathPython3TextField;
    }

    public JButton getOKButton() {
        return OKButton;
    }

    public InterpreterWindow() {

        //toolWindow.hide(null)

        button1.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(2);
            int result = jFileChooser.showOpenDialog(panel1);
            if (JFileChooser.APPROVE_OPTION == result) {
                File pythonEnv = jFileChooser.getSelectedFile();
                setInterpreterPathPython3TextField.setText(pythonEnv.getPath());
            }

        });

        OKButton.addActionListener(e -> {
            Data.interpreter_path=setInterpreterPathPython3TextField.getText();
            this.satus=1;

        });

    }
    public JPanel getContent() {

        return this.panel1;
    }


}
