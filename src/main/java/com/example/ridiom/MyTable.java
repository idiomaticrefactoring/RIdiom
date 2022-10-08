package com.example.ridiom;


import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.util.Function;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import com.intellij.util.ui.table.TableModelEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;


/**
 * 参考
 * {@link } com.intellij.ide.browsers.BrowserSettingsPanel
 */
public class MyTable {
    private TableModelEditor<MyRow> browsersEditor;

    private JComponent browsersTable;

    public JComponent getBrowsersTable() {
        return browsersTable;
    }

    private static final FileChooserDescriptor APP_FILE_CHOOSER_DESCRIPTOR = FileChooserDescriptorFactory.createSingleFileOrExecutableAppDescriptor();


    private static final TableModelEditor.EditableColumnInfo<MyRow, String> idiom = new TableModelEditor.EditableColumnInfo<MyRow, String>("Idiom") {
        @Override
        public Class getColumnClass() {
            return String.class;
        }

        @Override
        public boolean isCellEditable(MyRow item) {
            return false;
        }


        @Override
        public String valueOf(MyRow item) {
            return item.idiom;
        }
        @NotNull
        @Override
        public Comparator<MyRow> getComparator() {
            return new Comparator<MyRow>() {
                @Override
                public int compare(MyRow o1, MyRow o2) {
                    if (o1.idiom.compareTo(o2.idiom) > 0) {
                        return 1;
                    } else if (o1.idiom.compareTo(o2.idiom) == 0) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            };
        }

//        @Override
//        public void setValue(MyRow item, Boolean value) {
//            item.setActive(value);
//        }
    };
    private static final TableModelEditor.EditableColumnInfo<MyRow, String> path = new TableModelEditor.EditableColumnInfo<MyRow, String>("Path") {
        @Override
        public Class getColumnClass() {
            return String.class;
        }
        @Override
        public boolean isCellEditable(MyRow item) {
            return false;
        }
        @Override
        public String valueOf(MyRow item) {
            return item.path;
        }

//        @Override
//        public void setValue(MyRow item, Boolean value) {
//            item.setActive(value);
//        }
    };
    private static final TableModelEditor.EditableColumnInfo<MyRow, String> non_idiom_code = new TableModelEditor.EditableColumnInfo<MyRow, String>("Non-idiomatic code") {
        @Override
        public Class getColumnClass() {
            return String.class;
        }
        @Override
        public boolean isCellEditable(MyRow item) {
            return true;
        }
        @Override
        public String valueOf(MyRow item) {
            return item.non_idiom_code;
        }

//        @Override
//        public void setValue(MyRow item, Boolean value) {
//            item.setActive(value);
//        }
    };
    private static final TableModelEditor.EditableColumnInfo<MyRow, String> idiom_code = new TableModelEditor.EditableColumnInfo<MyRow, String>("Non-idiomatic code") {
        @Override
        public Class getColumnClass() {
            return String.class;
        }
        @Override
        public boolean isCellEditable(MyRow item) {
            return true;
        }
        @Override
        public String valueOf(MyRow item) {
            return item.idiom_code;
        }

//        @Override
//        public void setValue(MyRow item, Boolean value) {
//            item.setActive(value);
//        }
    };




    private static final ColumnInfo[] COLUMNS = {
            idiom,
            path,
            non_idiom_code, idiom_code
            };


    public MyTable() {


        TableModelEditor.DialogItemEditor<MyRow> itemEditor = new TableModelEditor.DialogItemEditor<MyRow>() {
            @Override
            public @NotNull Class<? extends MyRow> getItemClass() {
                return null;
            }

            @Override
            public MyRow clone(@NotNull MyRow myRow, boolean b) {
                return null;
            }

            @Override
            public void edit(@NotNull MyRow myRow, @NotNull Function<? super MyRow, ? extends MyRow> function, boolean b) {

            }

            @Override
            public void applyEdited(@NotNull MyRow myRow, @NotNull MyRow t1) {

            }
        };

        TableModelEditor.DataChangedListener<MyRow> dataChangedListener = new TableModelEditor.DataChangedListener<MyRow>() {
            @Override
            public void tableChanged(@NotNull TableModelEvent event) {
            }

            @Override
            public void dataChanged(@NotNull ColumnInfo<MyRow, ?> columnInfo, int rowIndex) {

            }
        };

        browsersEditor = new TableModelEditor<>(COLUMNS, itemEditor, "No web browsers configured");
        browsersEditor.modelListener(dataChangedListener);

        ListTableModel<MyRow> model = this.browsersEditor.getModel();
        MyRow myRow1 = new MyRow(
                UUID.randomUUID(),
               // MyIconItem.CHROME,
                "3",
                "path1",
                "code1",
                "code2",null
        );
        MyRow myRow2 = new MyRow(
                UUID.randomUUID(),
                "list comprehension",
//                UUID.randomUUID(),
               // MyIconItem.CHROME,

                "path2",
                "code3",
                "code4",null
        );

        model.addRow(myRow1);
        model.addRow(myRow2);

        browsersTable = browsersEditor.createComponent();
        browsersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("selected browsersTable:");
//                super.mouseClicked(e);
            }
        });
    }

    public MyTable(ArrayList<ArrayList<String>> row_list) {


        TableModelEditor.DialogItemEditor<MyRow> itemEditor = new TableModelEditor.DialogItemEditor<MyRow>() {
            @Override
            public @NotNull Class<? extends MyRow> getItemClass() {
                return null;
            }

            @Override
            public MyRow clone(@NotNull MyRow myRow, boolean b) {
                return null;
            }

            @Override
            public void edit(@NotNull MyRow myRow, @NotNull Function<? super MyRow, ? extends MyRow> function, boolean b) {

            }

            @Override
            public void applyEdited(@NotNull MyRow myRow, @NotNull MyRow t1) {

            }
        };

        TableModelEditor.DataChangedListener<MyRow> dataChangedListener = new TableModelEditor.DataChangedListener<MyRow>() {
            @Override
            public void tableChanged(@NotNull TableModelEvent event) {

            }

            @Override
            public void dataChanged(@NotNull ColumnInfo<MyRow, ?> columnInfo, int rowIndex) {
            }
        };

        browsersEditor = new TableModelEditor<>(COLUMNS, itemEditor, "No web browsers configured");
        browsersEditor.modelListener(dataChangedListener);

        ListTableModel<MyRow> model = this.browsersEditor.getModel();
        for(ArrayList<String> component:row_list){

            MyRow myRow1 = new MyRow(
                    UUID.randomUUID(),
                    // MyIconItem.CHROME,
                    component.get(0),
                    component.get(1),
                    component.get(2),
                    component.get(3),null
            );

            System.out.println("myRow1: "+myRow1.toString());

            model.addRow(myRow1);


        }



        browsersTable = browsersEditor.createComponent();

    }

}


