package com.example.ridiom;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.extensions.PluginId;
import jep.Interpreter;
import jep.JepConfig;
import jep.MainInterpreter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class RefactorMethod {
    //Paths.get("./").toAbsolutePath().toString();//"../";//jep_path
    //System.getProperty()
//    System.out.println("sp1 = " + sp1);
//    public static  String jep_path=sp1;

    public static void refactor(String filepath,String idiom){
        String jep_path =get_jep_path();
        try {
            MainInterpreter.setJepLibraryPath(jep_path);

            //MainInterpreter.setJepLibraryPath("/opt/homebrew/lib/python3.9/site-packages/jep/libjep.jnilib");
        }catch (Exception e){
            System.out.println("catch the exception"+e.toString());
        }



        // set path for python docs with python script to run
        jep.JepConfig jepConf = new JepConfig();
        //jepConf.addIncludePaths(System.getProperty("user.dir")+"/src/main/java/");
        String refactor_idom_code_path=get_python_path();

       // String refactor_idom_code_path="/Users/zhangzejunzhangzejun/IdeaProjects/RefactorIdiom/src/main/RefactoringIdioms/";
        jepConf.addIncludePaths(refactor_idom_code_path);

        //        jepConf.addIncludePaths(System.getProperty("user.dir")+"/src/main/RefactoringIdioms/");
        Interpreter subInterp = jepConf.createSubInterpreter();
        //MainInterpreter.setJepLibraryPath("/opt/homebrew/lib/python3.9/site-packages/jep/libjep.jnilib");

        //System.out.print(refactor_idom_code_path);
        //create the interpreter for python executing

        //import  .py doc with to run
        subInterp.eval("import main");

        // run each function from the .py doc I
        String command="res_spacy = main.jave_main('"+filepath+"','"+idiom+"')";
        //System.out.println(command);
        subInterp.eval(command);
        //System.out.println(subInterp.getValue("res_spacy"));
        subInterp.close();
    }

    public static void refactor_by_codepairs(String filepath,String idiom,ArrayList indexlist){
        try {
            String jep_path =get_jep_path();
            MainInterpreter.setJepLibraryPath(jep_path);

            //MainInterpreter.setJepLibraryPath("/opt/homebrew/lib/python3.9/site-packages/jep/libjep.jnilib");
        }catch (Exception e){
            System.out.println("catch the exception"+e.toString());
        }



        // set path for python docs with python script to run
        jep.JepConfig jepConf = new JepConfig();
        //jepConf.addIncludePaths(System.getProperty("user.dir")+"/src/main/java/");
        String refactor_idom_code_path=get_python_path();

        //String refactor_idom_code_path="/Users/zhangzejunzhangzejun/IdeaProjects/RefactorIdiom/src/main/RefactoringIdioms/";
        jepConf.addIncludePaths(refactor_idom_code_path);

        //        jepConf.addIncludePaths(System.getProperty("user.dir")+"/src/main/RefactoringIdioms/");
        Interpreter subInterp = jepConf.createSubInterpreter();
        //MainInterpreter.setJepLibraryPath("/opt/homebrew/lib/python3.9/site-packages/jep/libjep.jnilib");

        //System.out.print(refactor_idom_code_path);
        //create the interpreter for python executing
        try {
            //import  .py doc with to run
            subInterp.eval("import main");

            // run each function from the .py doc I
            String command = "res_spacy = main.jave_refactor_by_code_pairs('" + filepath + "','" + idiom + "'," + indexlist + ")";
            //System.out.println(command);
            subInterp.eval(command);
            //System.out.println(subInterp.getValue("res_spacy"));
            subInterp.close();
        }
        catch (Exception e){
            subInterp.close();
            System.out.println("catch the subInterp exception"+e.toString());
        }

    }
    public static String get_jep_path(){
        URL res = RefactorMethod.class.getResource("RefactorMethod.class");
        //System.out.println("jep_path"+res.getPath());
        String class_path = res.getPath();
        String pattern = Pattern.quote(System.getProperty("file.separator"));
        String[] splittedFileName = class_path.split(pattern);
        String dir_jep = String.join("/", Arrays.asList(splittedFileName).subList(0, 5));
        //System.out.println("dir_jep"+dir_jep);
        String jep_path=dir_jep+"/jep/libjep.jnilib";
        //System.out.println("jep_path abosulute"+jep_path.substring(5));
        PluginId pluginId = PluginId.getId("com.example.RIdiom");
        IdeaPluginDescriptor plugin = PluginManager.getPlugin(pluginId);
        jep_path= String.valueOf(plugin.getPluginPath());
        //System.out.println("jep_path abosulute plugin_path"+jep_path.substring(5));

//        jep_path=jep_path.substring(5);
//        jep_path="/Users/zhangzejunzhangzejun/Library/Application Support/jep/libjep.jnilib";
        jep_path+="/lib/jep/libjep.jnilib";
        return jep_path;
    }
    public static String get_python_path(){
        URL res = RefactorMethod.class.getResource("RefactorMethod.class");
        //System.out.println("jep_path"+res.getPath());
        String class_path = res.getPath();
        String pattern = Pattern.quote(System.getProperty("file.separator"));
        String[] splittedFileName = class_path.split(pattern);
        String dir_jep = String.join("/", Arrays.asList(splittedFileName).subList(0, 5));
        String refactor_idom_code_path=dir_jep+"/RefactoringIdioms/";
        PluginId pluginId = PluginId.getId("com.example.RIdiom");
        IdeaPluginDescriptor plugin = PluginManager.getPlugin(pluginId);
        dir_jep= String.valueOf(plugin.getPluginPath());
        refactor_idom_code_path=dir_jep+"/lib/RefactoringIdioms/";


        return refactor_idom_code_path;
    }
    public static Object getCodePairs(String filepath,String idiom){
//        String sp1 = this.getClass().getResource("").getPath();


        //"/opt/homebrew/lib/python3.9/site-packages/jep/libjep.jnilib";//dir_jep+"/jep/libjep.jnilib";
//        Process p = Runtime.getRuntime().exec("python python/jep_path.py");
//        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String ret = in.readLine();
//        System.out.println("the jep's built C library is at: "+ret);

        String jep_path =get_jep_path();
        try {
            MainInterpreter.setJepLibraryPath(jep_path);

//            MainInterpreter.setJepLibraryPath("/opt/homebrew/lib/python3.9/site-packages/jep/libjep.jnilib");
        }catch (Exception e){
            System.out.println("catch the exception"+e.toString());
        }



        // set path for python docs with python script to run
        jep.JepConfig jepConf = new JepConfig();
        //jepConf.addIncludePaths(System.getProperty("user.dir")+"/src/main/java/");

        String refactor_idom_code_path=get_python_path();

        //String refactor_idom_code_path="/Users/zhangzejunzhangzejun/IdeaProjects/RefactorIdiom/src/main/RefactoringIdioms/";
        jepConf.addIncludePaths(refactor_idom_code_path);

        //        jepConf.addIncludePaths(System.getProperty("user.dir")+"/src/main/RefactoringIdioms/");
        Interpreter subInterp = jepConf.createSubInterpreter();
        //MainInterpreter.setJepLibraryPath("/opt/homebrew/lib/python3.9/site-packages/jep/libjep.jnilib");

        //System.out.print(refactor_idom_code_path);
        //create the interpreter for python executing

        //import  .py doc with to run
        subInterp.eval("import main");
        try {
        // run each function from the .py doc I
        String command="res_spacy = main.jave_get_code_pairs('"+filepath+"','"+idiom+"')";
            //System.out.println(command);
        subInterp.eval(command);

        Object codepairs= subInterp.getValue("res_spacy");
            //System.out.print("codepairs: "+codepairs);
        subInterp.close();
        return codepairs;
        }
        catch (Exception e){
            subInterp.close();
            System.out.println("catch the exception"+e.toString());
            return null;
        }


    }

}
