package com.example.ridiom;


import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RefactorWindow implements ToolWindowFactory {

//    public static JComponent createConsolePanel(ConsoleView view) {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//        panel.add(view.getComponent(), BorderLayout.CENTER);
//        return panel;
//    }
    public static ArrayList<ArrayList<String>> codepairs;
//    public RefactorWindow(ArrayList<ArrayList<String>> codepairs){
//        //codepairs=codepairs;
//    }
//    @Override
//    public void init(ToolWindow window) {
//
//    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
//        MyToolWindow myToolWindow = new MyToolWindow(toolWindow,this.codepairs);
//        codepairwindow myToolWindow = new codepairwindow(toolWindow,this.codepairs,project,);
        // System.out.println("mytoolwindow Action code pairs: "+this.codepairs);

//        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
//        Content content = contentFactory.createContent(myToolWindow.getContent(), "refactor preview", false);
//        toolWindow.getContentManager().addContent(content);
        toolWindow.show();
//        TextConsoleBuilder consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(project);
//
//        ConsoleView console = consoleBuilder.getConsole();
//        console.print("------------Result Summary------------" + "\n", ConsoleViewContentType.LOG_INFO_OUTPUT);
//        JComponent consolePanel = createConsolePanel(console);
//        Dimension maximumSize = consolePanel.getMaximumSize();
//        System.out.println("height_consle: "+maximumSize.toString());
//        JButton cancelbutt=new JButton("Cancel");
//        JButton refactorbutt=new JButton("Refactor");
//
//
//        MyTable myTable = new MyTable();
//        consolePanel.add(myTable);
//        consolePanel.
//        consolePanel.add(cancelbutt);
//        consolePanel.add(refactorbutt);
//        consolePanel.add(myTable.getBrowsersTable());
//        JComponent browsetable = myTable.getBrowsersTable();
//        browsetable.setSize(maximumSize);
//
//        @NotNull ContentFactory factory = toolWindow.getContentManager().getFactory();
//        JBPopupFactory instance=JBPopupFactory.getInstance();
//        JPanel panel=new JPanel();
//
//        panel.add(browsetable);
////        consolePanel.setMaximumSize(maximumSize);
//
//        panel.add(cancelbutt);
//        panel.setSize(maximumSize);
//
//        @NotNull ComponentPopupBuilder a = instance.createComponentPopupBuilder(panel, new JBLabel());
//        @NotNull JBPopup popup = a.createPopup();
//        popup.setSize(maximumSize);
//        @NotNull JComponent popuupcontent = popup.getContent();
//        popuupcontent.setSize(maximumSize);
//        System.out.println("height_popupcontent: "+popuupcontent);
//
//        Content content  = toolWindow.getContentManager().getFactory().createContent(myTable.getBrowsersTable(), "Refactor Preview", false);
//        toolWindow.getContentManager().addContent(content);
//        System.out.println("height_tool_window: "+toolWindow.getComponent().getSize().toString());
//        JComponent browsetable = myTable.getBrowsersTable();
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//        panel.add(browsetable, BorderLayout.CENTER);
//        panel.add(cancelbutt);
//
//        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
//        JBPopupFactory instance= JBPopupFactory.getInstance();
//
//        @NotNull ComponentPopupBuilder a = instance.createComponentPopupBuilder(panel, new JBLabel());
//
////        Content content = contentFactory.createContent(a.createPopup().pack(), "Refactor Preview", false);
////        toolWindow.getContentManager().addContent(content);
//        @NotNull JBPopup popup = a.createPopup();
//        popup.pack(true,true);
//        Content content  = toolWindow.getContentManager().getFactory().createContent(popup.getContent(), "Refactor Preview", false);
//        toolWindow.getContentManager().addContent(content);
//        MyToolWindow myToolWindow = new MyToolWindow(toolWindow);
//        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
//        Content content = contentFactory.createContent(myToolWindow.getContent(), "", false);
//        toolWindow.getContentManager().addContent(content);


//        new ToolWindowConsole(toolWindow, console, project);
//        PropertiesCenter.init(project);
//        MyTable myTable = new MyTable();
//        JComponent browsetable = myTable.getBrowsersTable();

    }

}
