package com.example.ridiom;

import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.impl.DocumentMarkupModel;
import com.intellij.openapi.editor.markup.HighlighterLayer;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBColor;

import java.io.File;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*
https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
 */
public class codepairwindow {
    private JTable table;
    private JPanel panel1;
    private JButton removeButton;
    private JButton cancelButton;
    private JButton refactorButton;
    private JToolBar toolbar;
    private JScrollPane scrollpane;
    private List<List<String>> codepairs;
//    private int count_remove=0;//=0;
    private ArrayList<Integer> remove_index_list;// = new ArrayList<Integer>();
    //private ArrayList remove_index_list;//#=new ArrayList();
    public  void add_remove_index_list(String compare_str){
        for(int i=0;i<codepairs.size();i++){
            String linestr = codepairs.get(i).get(4);
            String non_idiomatic_code= codepairs.get(i).get(2);

            String path= codepairs.get(i).get(1);
            String idiom_str= codepairs.get(i).get(3);

            if ((path+non_idiomatic_code+idiom_str+linestr).equals(compare_str)){
                //System.out.println("remove non-idiomatic code: "+non_idiomatic_code);

                remove_index_list.add(i);
                break;
            }
        }


    }
    public codepairwindow(ToolWindow toolWindow, List<List<String>> codepairs, Project project,String filepath,String idiom) {
        this.codepairs=codepairs;
        this.remove_index_list=new ArrayList<Integer>();

        //this.count_remove=0;

//        this.panel1=new JPanel();
        cancelButton.addActionListener(e -> {
            //RefactorMethodRuntime.delete_json_files(filepath);
            RefactorMethodPython.delete_json_files(filepath);
            toolWindow.getContentManager().removeAllContents(true);});//toolWindow.remove();
//        this.createUIComponents();
        refactorButton.addActionListener(e -> {
            //System.out.println("all remove row index: "+remove_index_list);
            //System.out.println("idiom : "+idiom);
            //System.out.println("filepath : "+filepath);


            //System.out.println("code pairs of refactor button : "+codepairs);

            //RefactorMethod.refactor_by_codepairs(filepath,idiom,remove_index_list);
//            RefactorMethodRuntime.refactor_by_codepairs(filepath,idiom,remove_index_list);
            RefactorMethodPython.refactor_by_codepairs(filepath,idiom,remove_index_list);

            toolWindow.getContentManager().removeAllContents(false);
            //toolWindow.hide();
        });
        removeButton.addActionListener(e->{
            //JTable row_content = (JTable)e.getSource();
//            this.table.getSelectedRows()
//            int[] row_index_list = row_content.getSelectedRows();
//            row_content.remove
            int[] rows = this.table.getSelectedRows();


            while(rows.length>0)
            {
                //System.out.println("all remove index: "+Arrays.toString(rows));
                DefaultTableModel tm= (DefaultTableModel)this.table.getModel();
                String lineno_str = (String) table.getValueAt(rows[0],4);
                String non_idiom_str = (String) table.getValueAt(rows[0],2);
                String path = (String) table.getValueAt(rows[0],1);
                String idiom_str = (String) table.getValueAt(rows[0],3);
                this.add_remove_index_list(path+non_idiom_str+idiom_str+lineno_str);

                tm.removeRow(rows[0]);




//                System.out.println("remove row index: "+rows[0]+count_remove);
//                remove_index_list.add(rows[0]+this.count_remove);
//                count_remove+=1;
                //this.count_remove+=1;
//                    new DefaultTableModel()

//                tm.removeRow(table.convertRowIndexToModel(rows[0]));

                rows = this.table.getSelectedRows();
            }
            this.table.clearSelection();
//            int row_index =  table.getSelectedRow();
//            table.r
//            table.remove(row_index);
                });
        this.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
                if (e.getClickCount()>0){
                    JTable row_content = (JTable)e.getSource();
                    int row_index = row_content.getSelectedRow();



                    String file_path= (String) row_content.getModel().getValueAt(row_index,1);
//                String file_path= (String) row_content.getModel().getValueAt(row_index,1);

                   // System.out.println("row_content:"+file_path);
                    Path path = Paths.get(file_path);
                    //https://www.bbsmax.com/A/A7zgmaLV54/  快速定位到指定的文件位置
                    VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByNioFile(path);
                    Document document = FileDocumentManager.getInstance().getDocument(virtualFile);
                    MarkupModel markupModel = DocumentMarkupModel.forDocument(document, project, true);
                    markupModel.removeAllHighlighters();
                    OpenFileDescriptor openFileDescriptor = new OpenFileDescriptor(project, virtualFile);
                    Editor editor = FileEditorManager.getInstance(project).openTextEditor(openFileDescriptor, true);
                    CaretModel caretModel = editor.getCaretModel();

                    LogicalPosition logicalPosition = caretModel.getLogicalPosition();
                    logicalPosition.leanForward(true);




                    //JBColor color = new JBColor(245 * 65536 + 236 * 256 + 159, 0xff000000);
//                JBColor color = new JBColor(255 * 65536 + 255 * 256 + 204, 255 * 65536 + 255 * 256 + 204);
//                JBColor color = new JBColor(204 * 65536 + 204 * 256 , 204 * 65536 + 204 * 256);
//                JBColor color = new JBColor(0x172BB2 , 0x172BB2);
                    JBColor color = new JBColor(0x3446BF , 0x3446BF);



                    TextAttributes codeinfo = new TextAttributes(null, color, null, null, Font.PLAIN);
//                TextAttributes codeinfo = new TextAttributes(
//                        editor.getColorsScheme().getColor(EditorColors.SELECTION_FOREGROUND_COLOR),
//                        editor.getColorsScheme().getColor(EditorColors.SELECTION_BACKGROUND_COLOR),
//                        null, null, 0
//                );
                    String lineno_str = (String) table.getValueAt(row_index,4);
//                String lineno_str = codepairs.get(row_index).get(4);
//                System.out.println("select code: "+codepairs.get(row_index).get(2));
                    String[] lineno_str_list = lineno_str.split(" ");
//                HintManager instance = HintManager.getInstance();
//                instance.showInformationHint(editor, "Hello idea plugin");
                    LogicalPosition logical = new LogicalPosition(Integer.parseInt(lineno_str_list[0])-1, 0);
                    caretModel.moveToLogicalPosition(logical);
                    SelectionModel selectionModel = editor.getSelectionModel();
                    selectionModel.selectLineAtCaret();

                    for(String line_num:lineno_str_list){

                        markupModel.addLineHighlighter(Integer.parseInt(line_num)-1, HighlighterLayer.LAST, codeinfo);


                    }

//                TextAttributes codeinfo = new TextAttributes();
                    //(1,2,HighlighterLayer.ADDITIONAL_SYNTAX,codeinfo, HighlighterTargetArea.LINES_IN_RANGE);
                    //System.out.println("selected codepairwindow table:"+lineno_str);
                }

            }
        });
    }
    public JPanel getContent() {

        return this.panel1;
    }
    /*https://stackoverflow.com/questions/409784/whats-the-simplest-way-to-print-a-java-array
    https://stackoverflow.com/questions/43385892/jtable-display-strings-with-newline-characters-as-multiple-lines
     */
    private void updateRowHeights()
    {
        for (int row = 0; row < table.getRowCount(); row++)
        {
            int rowHeight = table.getRowHeight();

            for (int column = 0; column < table.getColumnCount(); column++)
            {
                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }

            table.setRowHeight(row, rowHeight);
        }
    }
    private void createUIComponents() {
        String[] columnNames = {"Idiom", "FilePath","Non-idiomatic Code","Idiomatic Code","Line Numbers"};
        String[][] stringArray =null;

        if (codepairs!=null) {

            stringArray = codepairs.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
            for(int i=0;i<stringArray.length;i++){
                //https://www.lema.fun/post/4n0cc828c how to display spaces and break lines
                stringArray[i][2]="<html><pre>"+stringArray[i][2]+"</pre></html>";
//                StringJoiner joiner = new StringJoiner("<br>", "<html>", "</html>");
//                String[] non_idiom_break = stringArray[i][2].split("\n");
//                for (String text : non_idiom_break) {
//                    joiner.add(text);
//                }
//                stringArray[i][2] = joiner.toString();

            }

        }else {
            //stringArray = new String[][]{{"Idiom1", "FilePath", "Non-idiomatic Code", "Idiomatic Code","Line Numbers"}};
        }
//        for(int i=0;i<stringArray.length;i++){
//            stringArray[i][2]="<html><p>"+stringArray[i][2]+"</p></html>";
//        }

//        for (String [] each_row:stringArray){
//            each_row[2]= "<html><p>"+each_row[2]+"</p></html>";
//        }
        DefaultTableModel defa_model = new DefaultTableModel(stringArray,columnNames);
        this.table=new JTable(defa_model){

            //Implement table cell tool tips.
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);

                try {
                    tip = getValueAt(rowIndex, colIndex).toString();
                } catch (RuntimeException e1) {
                    //catch null pointer exception if mouse is over an empty line
                }

                return tip;
            }
        };
        this.table.setFillsViewportHeight(true);
        this.updateRowHeights();
//        int sel_row = this.table.getSelectedRow();
//        defa_model=(DefaultTableModel)this.table.getModel()
//        defa_model.ge       .get(sel_row);
//        StringJoiner joiner = new StringJoiner("<br>", "<html>", "</html>");
//        for (String text : data) {
//            joiner.add(text);
//        }
//        value = joiner.toString();
//        this.table=new JTable(stringArray,columnNames);
//        new JTable()
        //System.out.println("create code in createUIComponents: "+codepairs);
        this.scrollpane = new JScrollPane(this.table);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

//        scrollpane.setColumnHeader();
//        scrollPane1.setColumnHeaderView(null);
//        new JTable()
//        mytable = new JPanel();
//        mytable.setLayout(new BorderLayout());
        // TODO: place custom component creation code here
//        table.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) {
//                    JTable target = (JTable)e.getSource();
//                    int row = target.getSelectedRow();
////                    int column = target.getSelectedColumn();
//                    // do some action if appropriate column
//                }
//            }
//        });

    }
}
