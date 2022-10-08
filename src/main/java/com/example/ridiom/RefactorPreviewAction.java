package com.example.ridiom;

// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Action class to demonstrate how to interact with the IntelliJ Platform.
 * The only action this class performs is to provide the user with a popup dialog as feedback.
 * Typically this class is instantiated by the IntelliJ Platform framework based on declarations
 * in the plugin.xml file. But when added at runtime this class is instantiated by an action group.
 */
public class RefactorPreviewAction extends AnAction implements ToolWindowFactory {
    public ArrayList<ArrayList<String>> codepairs;

    public RefactorPreviewAction() {
    }

//    public RefactorPreviewAction(ArrayList<ArrayList<String>> codepairs){
//       this.codepairs=codepairs;
//    }
    /**
     * Gives the user feedback when the dynamic action menu is chosen.
     * Pops a simple message dialog. See the psi_demo plugin for an example of how to use AnActionEvent to access data.
     *
     * @param event Event received when the associated menu item is chosen.
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();

            @NotNull DataContext datacontext = event.getDataContext();
        //System.out.print("preview: ");

//        ToolWindow refactor_window = ToolWindowManager.getInstance(event.getProject()).getToolWindow("RefactorWindow");
//        RefactorWindow refactor_window = ToolWindowManager.getInstance(event.getProject()).getToolWindow("RefactorWindow");

//        RefactorWindow refactor_window_new=new RefactorWindow(this.codepairs);
//        refactor_window_new.createToolWindowContent(project,refactor_window);

//        refactor_window.show();


//        JBPopupFactory instance=JBPopupFactory.getInstance();
//
////                    Object codePairs=getCodePairs(filepath,idiom);
//        JBPopupFactory instance_preview=JBPopupFactory.getInstance();
//
//        JPanel panel_preview=new JPanel();
        // 创建节点
//                    DefaultMutableTreeNode child1Leaf1 = new DefaultMutableTreeNode();
//                    child1Leaf1.setUserObject("child1Leaf1");
//
//                    DefaultMutableTreeNode child1 = new DefaultMutableTreeNode();
//                    child1.add(child1Leaf1);
//                    child1.setUserObject("child1");
//
//                    DefaultMutableTreeNode child1Leaf2 = new DefaultMutableTreeNode();
//                    child1Leaf2.setUserObject("child1Leaf2");
//
//                    DefaultMutableTreeNode child2 = new DefaultMutableTreeNode();
//                    child2.add(child1Leaf2);
//                    child2.setUserObject("child2");
//
//                    DefaultMutableTreeNode root = new DefaultMutableTreeNode();
//                    root.setUserObject("root");
//                    root.add(child1);
//                    root.add(child2);
//
//                    // 创建数据模型
//                    DefaultTreeModel model = new DefaultTreeModel(root);
//                    Tree tree = new Tree(model);
//                    tree.setDragEnabled(false);
//                    tree.setExpandableItemsEnabled(true);
//                    new JBTreeTable();
//        JButton cancelbutt=new JButton("Cancel");
//        JButton refactorbutt=new JButton("Refactor");
//
//
//        //panel_preview.add(tree);
//        panel_preview.add(cancelbutt);
//        panel_preview.add(refactorbutt);
//        @NotNull ComponentPopupBuilder a = instance.createComponentPopupBuilder(panel_preview, new JBLabel());
//        @NotNull JBPopup popup =a.createPopup();
//        a.setMovable(true);
//        a.setTitle("Refactor ");
//        popup.showInFocusCenter();
//
//        cancelbutt.addActionListener(e->popup.dispose());
//        refactorbutt.addActionListener(e->{refactor(filepath,idiom);popup.dispose();});
//        Messages.showMessageDialog(project,
//                "Popup dialog action",
//                "Greetings from PyCharm Basics Plugin",
//                Messages.getInformationIcon());
    }

    /**
     * Determines whether this menu item is available for the current context.
     * Requires a project to be open.
     *
     * @param e Event received when the associated group-id menu is chosen.
     */
    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        MyToolWindow myToolWindow = new MyToolWindow(toolWindow,this.codepairs);
        //System.out.println("mytoolwindow Action code pairs: "+this.codepairs);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(myToolWindow.getContent(), "refactor preview", false);
        toolWindow.getContentManager().addContent(content);
        toolWindow.show();
    }
}