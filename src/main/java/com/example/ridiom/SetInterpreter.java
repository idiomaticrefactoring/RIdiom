package com.example.ridiom;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class SetInterpreter extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        InterpreterWindow window=new InterpreterWindow();

        JFrame klima = new JFrame("Set Python Interpreter");
        klima.setContentPane( window.getContent() );
        klima.setSize( 300, 100 );
        klima.setLocation(430, 100);
        klima.show();
        window.getOKButton().addActionListener(e->{
                Data.interpreter_path=window.getSetInterpreterPathPython3TextField().getText();
            klima.dispose();
        });



//        JBPopupFactory instance= JBPopupFactory.getInstance();
//        JPanel content = window.getContent();
//        Dialog dia=new Dialog(content);
//        @NotNull ComponentPopupBuilder a = instance.createComponentPopupBuilder(window.getContent(), new JBLabel());
//        @NotNull JBPopup popup = a.createPopup();
//        a.setMovable(true);
//        a.setTitle("Set Python Interpreter Path ");
//        popup.showInFocusCenter();

//
////        window.getContent().show();
//        window.show();
//        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
//
//        Content content = contentFactory.createContent(window.getContent(), "refactor preview", false);
//        toolwind.getContentManager().addContent(content);
////                toolwind.getContentManager().addContent(content);
//
//        toolwind.show();

    }

}
