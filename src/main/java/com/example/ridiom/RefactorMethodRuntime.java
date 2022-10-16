package com.example.ridiom;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.extensions.PluginId;
import jep.Interpreter;
import jep.JepConfig;
import jep.MainInterpreter;
//import net.minidev.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RefactorMethodRuntime {
    public static String get_python_path(){
//        URL res = RefactorMethod.class.getResource("RefactorMethod.class");
//        //System.out.println("jep_path"+res.getPath());
//        String class_path = res.getPath();
//        String pattern = Pattern.quote(System.getProperty("file.separator"));
//        String[] splittedFileName = class_path.split(pattern);
//        String dir_jep = String.join("/", Arrays.asList(splittedFileName).subList(0, 5));
//        String refactor_idom_code_path=dir_jep+"/RefactoringIdioms/";
        PluginId pluginId = PluginId.getId("com.example.RIdiom");
        IdeaPluginDescriptor plugin = PluginManager.getPlugin(pluginId);
        String dir_jep= String.valueOf(plugin.getPluginPath());
        System.out.println("jep_path"+dir_jep);
        //refactor_idom_code_path=dir_jep+"/lib/RefactoringIdioms/";
//        refactor_idom_code_path=dir_jep+"/lib/RefactoringIdioms/main.py";
        String refactor_idom_code_path=dir_jep+"/lib/RefactoringIdioms/dist/main/main";




        return refactor_idom_code_path;
    }
    public static void delete_json_files(String filepath){
        String json_dir=get_json_dir(filepath);
        File f= new File(json_dir+"result_code_refactor_pair.json");
        f.delete();
        File f_table= new File(json_dir+"result_code_refactor_pair_table.json");
        f_table.delete();
    }
    public static String get_json_dir(String filepath){
        File file_refactor = new File(filepath);
        String json_path_dir=null;
        if (file_refactor.isDirectory()){
            json_path_dir=filepath+"/";
        }else{
            json_path_dir=file_refactor.getParent()+"/";

        }
        return json_path_dir;
    }
    public static List<List<String>> get_json_data(String filepath){
        File file_refactor = new File(filepath);
        String json_path=null;
        if (file_refactor.isDirectory()){
            json_path=filepath+"/result_code_refactor_pair_table.json";
        }else{
            json_path=file_refactor.getParent()+"/result_code_refactor_pair_table.json";

        }
        try {

            File file=new File(json_path);
            String jsonString= FileUtils.readFileToString(file,"UTF-8");
//            JSONObject json_obj = JSONObject.fromObject(jsonString);
//            CodeInfo [][] user = JSON.parseObject(jsonString, CodeInfo [][].class);
            String [][] user = JSON.parseObject(jsonString, String [][].class);
            List<List<String>> collect2 = Arrays.stream(user).map(Arrays::asList)
                    .collect(Collectors.toList());
            //System.out.println("the anwser is: " +collect2.get(0));
//            return null;
            return collect2;
        } catch (IOException e) {
            //System.out.println("the anwser is: " + python_path);
            System.out.println("catch the exception"+e.toString());
//            throw new RuntimeException(e);
        }
        return null;

    }

    public static List<List<String>> getCodePairs(String filepath, String idiom) {
//        String sp1 = this.getClass().getResource("").getPath();
        String python_path=get_python_path();
//        File file_refactor = new File(filepath);
//        String json_path=null;
//        if (file_refactor.isDirectory()){
//           json_path=filepath+"/result_code_refactor_pair_table.json";
//        }else{
//            json_path=file_refactor.getParent()+"/result_code_refactor_pair_table.json";
//
//        }

//更多请阅读：https://www.yiibai.com/java/check-file-directory-file-java.html



//        String[] args1 = new String[] { "python ", python_path, " jave_get_code_pairs ", '"'+filepath+'"', " '"+idiom+"'"};
//        String[] args1 = new String[] { "python", python_path, "jave_get_code_pairs_parallel", '"'+filepath+'"', '"'+idiom+'"'};
//        String[] args1 = new String[] { python_path, "jave_get_code_pairs", '"'+filepath+'"', " '"+idiom+"'"};
        String[] args1 = new String[] { python_path, "jave_get_code_pairs_parallel", filepath, idiom};

        try {
            Process proc = Runtime.getRuntime().exec(args1);

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            BufferedReader error = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

//            String stdout = IOUtils.toString(proc.getInputStream(), Charset.defaultCharset());
            String stderror = IOUtils.toString(proc.getErrorStream(), Charset.defaultCharset());
            return  get_json_data(filepath);
//            File file=new File(json_path);
//            String jsonString= FileUtils.readFileToString(file,"UTF-8");
////            JSONObject json_obj = JSONObject.fromObject(jsonString);
////            CodeInfo [][] user = JSON.parseObject(jsonString, CodeInfo [][].class);
//            String [][] user = JSON.parseObject(jsonString, String [][].class);
//            List<List<String>> collect2 = Arrays.stream(user).map(Arrays::asList)
//                    .collect(Collectors.toList());
//            System.out.println("the anwser is: " + python_path+user+stderror);
////            return null;
//            return collect2;
        } catch (IOException e) {
            //System.out.println("the anwser is: " + python_path);
            System.out.println("catch the exception"+e.toString());
//            throw new RuntimeException(e);
        }

        return null;
    }

    public static void refactor(String filepath,String idiom){
        String python_path=get_python_path();
        String[] args1 = new String[] { python_path, "jave_main", filepath, idiom};

        try {
            Runtime.getRuntime().exec(args1);

        } catch (IOException e) {
            //System.out.println("the anwser is: " + python_path);
            System.out.println("catch the exception"+e.toString());
//            throw new RuntimeException(e);
        }

    }

    public static void refactor_by_codepairs(String filepath,String idiom,List indexlist){
        String python_path=get_python_path();

//        String[] args1 = new String[] { "python ", python_path, " jave_get_code_pairs ", '"'+filepath+'"', " '"+idiom+"'"};
//        String[] args1 = new String[] { "python", python_path, "jave_get_code_pairs_parallel", '"'+filepath+'"', '"'+idiom+'"'};
//        String[] args1 = new String[] { python_path, "jave_get_code_pairs", '"'+filepath+'"', " '"+idiom+"'"};
        String[] args1 = new String[] { python_path, "jave_refactor_by_code_pairs_parallel", filepath, idiom, String.valueOf(indexlist)};

        try {
            Process proc = Runtime.getRuntime().exec(args1);
            String stderror = IOUtils.toString(proc.getErrorStream(), Charset.defaultCharset());
            System.out.println("refactor: " + python_path+stderror);

        } catch (IOException e) {
        //System.out.println("the anwser is: " + python_path);
            System.out.println("catch the exception"+e.toString());
        }


    }

}
