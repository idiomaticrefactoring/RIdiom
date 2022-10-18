package com.example.ridiom;
import jep.Interpreter;
import jep.JepConfig;
import jep.MainInterpreter;

import java.util.ArrayList;

public class helloworld
{
    public static void refactor(String filepath,String idiom){
        MainInterpreter.setJepLibraryPath("/opt/homebrew/lib/python3.9/site-packages/jep/libjep.jnilib");
//        PythonInterpreter interpreter = new PythonInterpreter();
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
        String command="res_spacy = main.jave_main('"+filepath+"','"+idiom+"')";
        System.out.println(command);
        subInterp.eval(command);
        System.out.println(subInterp.getValue("res_spacy"));
    }
    public static Object getCodePairs(String filepath,String idiom){
        try {

            MainInterpreter.setJepLibraryPath("/opt/homebrew/lib/python3.9/site-packages/jep/libjep.jnilib");
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
//        Object codepairs = subInterp.getValue("res_spacy");
        ArrayList<ArrayList<String>> codepairs = (ArrayList<ArrayList<String>>) subInterp.getValue("res_spacy");
//        ArrayList<ArrayList<String>> codepairs= new ArrayList<ArrayList<String>>(subInterp.getValue("res_spacy"));
        System.out.print("codepairs: "+codepairs.getClass().getClass().getClass()+codepairs);
        subInterp.close();

        return codepairs;
    }
    public static void main(String[] args) {
        String var1 = System.getenv("PYTHONPATH");
        System.out.print(var1);
//        String filepath="/Users/zhangzejunzhangzejun/PycharmProjects/code_test_java_plugin/bjst.py";
//        String idiom="All";
//        //refactor(filepath,idiom);
//        getCodePairs(filepath, idiom);
//        ArrayList<ArrayList<String>> codepairs  = (ArrayList<ArrayList<String>>) RefactorMethod.getCodePairs(filepath, idiom);

        /*
        MainInterpreter.setJepLibraryPath("/opt/homebrew/lib/python3.9/site-packages/jep/libjep.jnilib");

        // set path for python docs with python script to run
        jep.JepConfig jepConf = new JepConfig();
//            jepConf.addIncludePaths(System.getProperty("user.dir")+"/src/main/java/");
        jepConf.addIncludePaths(System.getProperty("user.dir")+"/src/main/RefactoringIdioms/");
        System.out.print(System.getProperty("user.dir")+"/src/main/RefactoringIdioms/");
        Interpreter subInterp = jepConf.createSubInterpreter();
        subInterp.eval("import main");
        String filepath="/Users/zhangzejunzhangzejun/PycharmProjects/code_test_java_plugin/bjst.py";
        String idiom="All";
//        String command="res_spacy = main.jave_main('"+filepath+"','"+idiom+"')";
//        System.out.println(command);
//        subInterp.eval(command);
//        System.out.println(subInterp.getValue("res_spacy"));

    */

    }
}
