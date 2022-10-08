package com.example.ridiom;


import com.intellij.ide.browsers.BrowserSpecificSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class MyRow {

    public final UUID id;
//
//    @NotNull
//    private MyIconItem icon;

    @NotNull
    public String name;
    public String idiom;
    public String idiom_code;
    public String non_idiom_code;

    public boolean active;

    public String path;

    /**
     * 保存当前表格数据
     */
    public BrowserSpecificSettings specificSettings;

//    @SuppressWarnings("UnusedDeclaration")
//    MyRow() {
//        //this(UUID.randomUUID(), MyIconItem.CHROME);
//        this(UUID.randomUUID(), idiom, path, non_idiom_code, idiom_code);
//
//    }

//    MyRow(@NotNull UUID id, @NotNull MyIconItem icon) {
//        this(id, icon, icon.getName(), icon.getExecutionPath(), true, icon.createBrowserSpecificSettings());
//    }
//    MyRow(@NotNull UUID id,String idiom, String path, String non_idiomatic_code, String idiomatic_code) {
//        this(id, idiom, path,non_idiomatic_code,idiomatic_code);
//    }

    public MyRow(@NotNull UUID id,String idiom, String path, String non_idiomatic_code, String idiomatic_code,@Nullable BrowserSpecificSettings specificSettings) {
        this.id = id;
        this.idiom = idiom;
        this.path = path;

        this.non_idiom_code = non_idiomatic_code;
        this.idiom_code = idiomatic_code;

        this.specificSettings = specificSettings;
    }
    public void setSpecificSettings(@Nullable BrowserSpecificSettings value) {
        specificSettings = value;
    }



//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof MyRow)) {
//            return false;
//        }
//
//        MyRow browser = (MyRow) o;
//        return getId().equals(browser.getId()) &&
//                icon.equals(browser.icon) &&
//                active == browser.active &&
//                Comparing.strEqual(name, browser.name) &&
//                Comparing.equal(path, browser.path) &&
//                Comparing.equal(specificSettings, browser.specificSettings);
//    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
//
//    @Override
//    @NotNull
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    @NotNull
//    public final UUID getId() {
//        return id;
//    }
//
//    @Override
//    @NotNull
//    public MyIconItem getIcon() {
//        return icon;
//    }


    @Override
    public String toString() {
        return idiom + ": "+non_idiom_code+"\n"+idiom_code;
    }
}