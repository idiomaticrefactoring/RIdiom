package com.example.ridiom;

// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import jep.Interpreter;
import jep.JepConfig;
import jep.MainInterpreter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Action class to demonstrate how to interact with the IntelliJ Platform.
 * The only action this class performs is to provide the user with a popup dialog as feedback.
 * Typically this class is instantiated by the IntelliJ Platform framework based on declarations
 * in the plugin.xml file. But when added at runtime this class is instantiated by an action group.
 */
public class SelectFileRefactorAction extends AnAction {
    static int count=0;

    public static void preview_code_pairs(String filepath,ToolWindow toolwind,ComboBox modifierCombobox,JBPopup popup,Project project){
        popup.dispose();
        String new_idiom = modifierCombobox.getSelectedItem().toString();
        ArrayList<ArrayList<String>> codepairs  = (ArrayList<ArrayList<String>>) RefactorMethod.getCodePairs(filepath, new_idiom);

//                String item_idiom=modifierCombobox.getItem().toString();
//                System.out.println(">>>>select idiom: "+idiom);
//                System.out.println(">>>>non select idiom: "+modifierCombobox.getSelectedItem().toString());
//                RefactorWindow refwindow = new RefactorWindow();
//                RefactorWindow.codepairs=codepairs;
//                RefactorWindow refwindow = new RefactorWindow();
//                refwindow.createToolWindowContent();
//                System.out.println("previewbutt.addActionListener: "+codepairs);

        toolwind.getContentManager().removeAllContents(false);
        //MyToolWindow myToolWindow = new MyToolWindow(toolwind,codepairs);
        codepairwindow myToolWindow = new codepairwindow(toolwind,codepairs,project,filepath,new_idiom);

//                System.out.println("hahamytoolwindow Action code pairs: ");


//
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(myToolWindow.getContent(), "refactor preview", false);
        toolwind.getContentManager().addContent(content);
//                toolwind.getContentManager().addContent(content);

        toolwind.show();
    }

    /*public static Object getCodePairs(String filepath,String idiom){
        try {

            MainInterpreter.setJepLibraryPath("/opt/homebrew/lib/python3.9/site-packages/jep/libjep.jnilib");
            count += 1;
        }catch (Exception e){
            System.out.println("catch the exception"+e.toString());
        }



        // set path for python docs with python script to run
        jep.JepConfig jepConf = new JepConfig();
        //jepConf.addIncludePaths(System.getProperty("user.dir")+"/src/main/java/");
        String refactor_idom_code_path="/Users/zhangzejunzhangzejun/IdeaProjects/RefactorIdiom/src/main/RefactoringIdioms/";
        jepConf.addIncludePaths(refactor_idom_code_path);

        //        jepConf.addIncludePaths(System.getProperty("user.dir")+"/src/main/RefactoringIdioms/");
        Interpreter subInterp = jepConf.createSubInterpreter();
        //MainInterpreter.setJepLibraryPath("/opt/homebrew/lib/python3.9/site-packages/jep/libjep.jnilib");

        System.out.print(refactor_idom_code_path);
        //create the interpreter for python executing

        //import  .py doc with to run
        subInterp.eval("import main");

        // run each function from the .py doc I
        String command="res_spacy = main.jave_get_code_pairs('"+filepath+"','"+idiom+"')";
        System.out.println(command);
        subInterp.eval(command);
        Object codepairs= subInterp.getValue("res_spacy");
        subInterp.close();
        return codepairs;
    }

    /**
     * Gives the user feedback when the dynamic action menu is chosen.
     * Pops a simple message dialog. See the psi_demo plugin for an example of how to use AnActionEvent to access data.
     *
     * @param event Event received when the associated menu item is chosen.
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        DataContext dataContext=event.getDataContext();
        String filepath=getFilePath(dataContext);
        if (filepath!=null){
            JBPopupFactory instance=JBPopupFactory.getInstance();

            JButton cancelbutt=new JButton("Cancel");
//            Messages.showMessageDialog(project,);
            JButton previewbutt=new JButton("Preview");
            JButton refactorbutt=new JButton("Refactor");
//            JTextField tf1=new JTextField("List Comprehension");
//            JTextField tf2=new JTextField("Set Comprehension");
            ComboBox modifierCombobox = new ComboBox();
            modifierCombobox.addItem("All");
            modifierCombobox.addItem("List Comprehension");
            modifierCombobox.addItem("Set Comprehension");
            modifierCombobox.addItem("Dict Comprehension");
            modifierCombobox.addItem("Chain Comparison");
            modifierCombobox.addItem("Truth Value Test");
            modifierCombobox.addItem("Assign Multiple Targets");
            modifierCombobox.addItem("For Multiple Targets");
            modifierCombobox.addItem("For Else");
            modifierCombobox.addItem("Star in Call");

            String[] splitString = null;
            splitString = filepath.split("/");
            String last_filename=splitString[splitString.length - 1];//this is where your string will be
//            ArrayList<String> list= (ArrayList<String>) Arrays.asList(filepath.split("/|\\\\"));
            //+list[list.length-1]
//            String last_filename=list.get(list.size()-1);
//            System.out.println(list[list.length-1]);
            JPanel panel=new JPanel();
            panel.add(modifierCombobox);
            panel.add(cancelbutt);
            panel.add(previewbutt);
            panel.add(refactorbutt);


            @NotNull ComponentPopupBuilder a = instance.createComponentPopupBuilder(panel, new JBLabel());
            //a.createPopup().getContent()
//            a.setCancelKeyEnabled(true);
//            a.setCancelOnWindowDeactivation(true);

            @NotNull JBPopup popup = a.createPopup();
            a.setMovable(true);
            a.setTitle("Refactor " + last_filename);
            popup.showInFocusCenter();
            cancelbutt.addActionListener( e->popup.dispose());

            String idiom = modifierCombobox.getSelectedItem().toString();
            //System.out.println(">>>>path: "+filepath+idiom);

            refactorbutt.addActionListener( e->{RefactorMethod.refactor(filepath,idiom);popup.dispose();});

            //customFileRefactorAction filecustomrefac = new customFileRefactorAction(popup);

//            previewbutt.addActionListener(filecustomrefac);
            ToolWindow toolwind = ToolWindowManager.getInstance(event.getProject()).getToolWindow("RefactorWindow");

            previewbutt.addActionListener(e->{preview_code_pairs(filepath,toolwind,modifierCombobox,popup,event.getProject());
//                Component mytable = mytable_panel.getComponent(0);
//                mytable.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
//                        if (e.getClickCount() == 2) {
//                            System.out.println("come here click");
//                            JTable target = (JTable) e.getSource();
//                            int row = target.getSelectedRow();
//                            System.out.println("selected row:" + row);
////                    int column = target.getSelectedColumn();
//                            // do some action if appropriate column
//                        }
//                    }
//
//
//                });
//                toolwind.hide();
//                toolwind.
//                toolwind.
//                JComponent component = toolwind.getComponent();
////                System.out.print("component k: "+component.getComponent(2));
//
//                @Nullable Content content = toolwind.getContentManager().getContent(0);
//                JPanel component_content = (JPanel) content.getComponent().getComponent(0);
//                Component mytable = component_content.getComponent(0);
//                System.out.println("content0: "+content.getComponent().getComponent(0));
//
//                System.out.println("content componet 0: "+content.getComponent().getComponent(0));
//                System.out.println("content componet 1: "+content.getComponent().getComponent(1));
//                System.out.println("content componet 2: "+content.getComponent().getComponent(2));
//                System.out.println("mytable "+mytable);
//                @NotNull JComponent mytable_won = toolwind.getComponent();
//                System.out.println("MyToolWindow "+mytable_won);
//                toolwind.addContentManagerListener();
                //                toolwind.createToolWindowContent()
//                toolwind.addContentManagerListener();
//                refwindow.createToolWindowContent(project,toolwind);
//                ContentManager contentManagerIfCreated = toolwind.getContentManagerIfCreated();
//                refwindow.createToolWindowContent(project,toolwind);
//                toolwind.show();
//                RefactorPreviewAction refactorPreview = new RefactorPreviewAction(codepairs);
//                refactorPreview.actionPerformed(event);
            });//refactorPreview.actionPerformed(event);
//            toolwind.getComponent().addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    System.out.println("come here click toolwindow");
//                }
//            });
////            toolwind.addContentManagerListener(new ContentManagerListener(){
////
////            } );
//
//            @Nullable Content content = toolwind.getContentManager().getContent(0);
//            JPanel first_table_panel = (JPanel) content.getComponent().getComponent(0);
//            Component first_table = first_table_panel.getComponent(0);
////                JPanel component_content = (JPanel) content.getComponent().getComponent(0);
////            JComponent component = toolwind.getComponent();
//
//            first_table.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
////                        if (e.getClickCount() == 2) {
//                            System.out.println("come here click");
//                            JTable target = (JTable) e.getSource();
//                            int row = target.getSelectedRow();
//                            System.out.println("selected row:" + row);
////                    int column = target.getSelectedColumn();
//                            // do some action if appropriate column
////                        }
//                    }
//
//
//                });
//            System.out.println("component k: "+first_table.toString())
//            JTable first_table_1= (JTable)first_table.getComponent(0);
            ;
//            System.out.print("component k: "+first_table.getComponent(0)+first_table.getComponents());

//            System.out.print("component k: "+first_table.getComponent(0));




//            previewbutt.addActionListener(e->{popup.dispose();RefactorPreviewAction refactorPreview = new RefactorPreviewAction();});//refactorPreview.actionPerformed(event);

            //previewbutt.addActionListener();
            //.setTitle("Refactor " + last_filename).setMovable(true).createPopup().showInFocusCenter();
//            cancelbutt.addActionListener(e->);
            //            instance.createActionGroupPopup();
//            Messages.showMessageDialog(project,
//                    "select a refactor file",
//                    "Greetings refactor "+filepath,
//                    Messages.getInformationIcon());

        }
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

    public static String getFilePath(DataContext dataContext){
        VirtualFile file= CommonDataKeys.VIRTUAL_FILE.getData(dataContext);
        return file==null?null:file.getPath();
    }

}


