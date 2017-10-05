package com.jj.utility;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

import com.jj.spring.model.Stat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;


public class CompilationUnit {

	  public static Stat compile(String submitCode,String testCode,String submitCodeClassName) throws IOException {
	    
		Stat stat = new Stat();  
		  
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	    DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

	    StringWriter testcasewriter = new StringWriter();
	    PrintWriter out = new PrintWriter(testcasewriter);
	    out.println(testCode);
	    out.close();
	    
	    StringWriter submittedwriter = new StringWriter();
	    PrintWriter out1 = new PrintWriter(submittedwriter);
	    out1.println(submitCode);
	    out1.close();
	    
	    // Name of the grader code will have to be fixed for all the testcases (Grader.java)
	    JavaFileObject file = new JavaSourceFromString("Grader", testcasewriter.toString());
	    
	    // Name of the submitted file will have to be provided by the instructor.
	    JavaFileObject file1 = new JavaSourceFromString(submitCodeClassName, submittedwriter.toString());
	    
	    Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file,file1);
	    CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

	    boolean success = task.call();	    
	    	    
	   // System.out.println("Success: " + success);

	  if (success) {
		  
		  stat.setCompileStatus(1);
		  
		  int score;
	      try {
	    	  
	    	  // Grader code must not have system.exit statements
	    	  // score should be a global variable so that we can read it 
	    	  // from outside in the calling code.
	    	  
	          URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File("").toURI().toURL() });
	          
	          Class<?> graderclass = Class.forName("Grader", true, classLoader);
	          
	          graderclass.getMethod("main", new Class[] { String[].class }).invoke(null, new Object[] { null });
	          
	          
	          Class<?> class1 = classLoader.loadClass("Grader");
	          try {
	        	 //System.out.println("Student Scored - "); 
	        	 
	        	  score = class1.getDeclaredField("total").getInt(null);
	        	  stat.setScore(score);
	        	  
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} 
	          
	        //  classLoader.close();
	          
	      } catch (ClassNotFoundException e) {
	        System.err.println("Class not found: " + e);
	      } catch (NoSuchMethodException e) {
	        System.err.println("No such method: " + e);
	      }catch (IllegalAccessException e) {
	        System.err.println("Illegal access: " + e);
	      } catch (InvocationTargetException e) {
	        System.err.println("Invocation target: " + e);
	      }
	      
	      return stat;
	    }else{
	    	
	    	stat.setCompileStatus(0);
	    	
	    	String errorMsg = getErrorMessage(testCode,submitCode,diagnostics);
	    	
	    	stat.setErrorMessage(errorMsg);
	    	return stat;
	    }
	  }

	private static String getErrorMessage(String testCode, String submitCode,
			DiagnosticCollector<JavaFileObject> diagnostics) {
		
		String errMsg = null,src = null,codeSeg = "";
		int pos = 1;
		
		for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
			
			if(diagnostic.getKind() == Diagnostic.Kind.ERROR){
			
		    //  System.out.println(diagnostic.getCode());
		    //  System.out.println(diagnostic.getKind());
				pos = (int) diagnostic.getPosition();
		    //  System.out.println(diagnostic.getStartPosition());
		   //   System.out.println(diagnostic.getEndPosition());
		  //    System.out.println(diagnostic.getSource());
				
				//System.out.println(diagnostic.getLineNumber());
				
				errMsg = diagnostic.getMessage(null);
				src = diagnostic.getSource().toString();
		        
				System.out.println(pos-50);
				
				System.out.println(submitCode.length());
				
				if(!src.contains("Grader")){
				
					if(pos>50 && pos+50<submitCode.length()){
						codeSeg = submitCode.substring(pos-50, pos+50);
					}else if(pos < 50 && pos+50<submitCode.length()){
						codeSeg = submitCode.substring(0, pos+50);
					}else if(pos > 50 && pos+50>submitCode.length()){
						codeSeg = submitCode.substring(pos-50, submitCode.length());
					}else{
						codeSeg = submitCode.substring(0, submitCode.length());
					}
				}
				break;
			}
		
	}
		return "Error - "+errMsg + " \nFile - " + src + "\nCode - "+codeSeg;
	} 

}
	class JavaSourceFromString extends SimpleJavaFileObject {
	  final String code;

	  JavaSourceFromString(String name, String code) {
	    super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),Kind.SOURCE);
	    this.code = code;
	  }

	  @Override
	  public CharSequence getCharContent(boolean ignoreEncodingErrors) {
	    return code;
	  }
	}
	
	
	
	

