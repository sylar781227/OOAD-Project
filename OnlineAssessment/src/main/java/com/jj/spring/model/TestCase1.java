package com.jj.spring.model;


public class TestCase1 {

	
public static String testcode = "	public class Grader { "+
		
	"public static int score = 0;"+	
	
	"public static void main(String[] args) { " +

		"int scoreMax = 8; "+

		"try { "+

		"	if (Midterm.isRotation(\"aabb\", \"abab\") == false) {"+

		"		System.out.println(\"Test 1 Passed\");"+

		"		score += 1; "+

		"	} else { "+

		"		System.out.println(\"Test 1 faild\"); "+

		"	} "+

		"} catch (Exception e) {"+

		"	System.out.println(\"Test 1 faild\");"+

		"}"+

		"try {"+

			"if (Midterm.isRotation(\"123\", \"321\") == false) {"+

			"	System.out.println(\"Test 2 Passed\");"+

			"	score += 1;"+

			"} else {"+

			"	System.out.println(\"Test 2 faild\");"+

		"	}"+

		"} catch (Exception e) {"+

		"	System.out.println(\"Test 2 faild\");"+

		"}"+

		"try {"+

		"	if (Midterm.isRotation(\"CCar\", \"arc\") == false) {"+

		"		System.out.println(\"Test 3 Passed\");"+

		"		score += 1;"+

		"	} else {"+

		"		System.out.println(\"Test 3 faild\");"+

		"	}"+

		"} catch (Exception e) {"+

		"	System.out.println(\"Test 3 faild\");"+

		"}"+

		"try {"+

		"	if (Midterm.isRotation(\"car\", \"car\") == true) {"+

			"	System.out.println(\"Test 4 Passed\");"+

			"	score += 1;"+

			"} else {"+

			"	System.out.println(\"Test 4 faild\");"+

			"}"+

		"} catch (Exception e) {"+

		"	System.out.println(\"Test 4 faild\");"+

		"}"+

		"try {"+

		"	if (Midterm.isRotation(\"aba\", \"aab\") == true) {"+

		"		System.out.println(\"Test 5 Passed\");"+

		"		score += 1;"+

		"	} else {"+

		"		System.out.println(\"Test 5 faild\");"+

		"	}"+

		"} catch (Exception e) {"+

		"	System.out.println(\"Test 5 faild\");"+

		"}"+

		"try {"+

		"	if (Midterm.isRotation(\"Car \", \"Arc \") == true) {"+

		"		System.out.println(\"Test 6 Passed\");"+

		"		score += 1;"+

		"	} else {"+

		"		System.out.println(\"Test 6 faild\");"+

		"	}"+

		"} catch (Exception e) {"+

		"	System.out.println(\"Test 6 faild\");"+

		"}"+

		"try {"+

		"	if (Midterm.isRotation(\"\", \" \") == true) { "+

		"		System.out.println(\"Test 7 Passed\"); "+

		"		score += 1;"+

		"	} else {"+

		"		System.out.println(\"Test 7 faild\");"+

		"	}"+

		"} catch (Exception e) {"+

		"	System.out.println(\"Test 7 faild\");"+

		"}"+

		"try {"+

			"if (Midterm.isRotation(\" C a r\", \"a r c \") == true) {"+

			"	System.out.println(\"Test 8 Passed\");"+

			"	score += 1;"+

			"} else {"+

			"	System.out.println(\"Test 8 faild\");"+

			"}"+

		"} catch (Exception e) {"+

		"	System.out.println(\"Test 8 faild\");"+

		"}"+

		"System.out.println(\"Score: \" + score + \"/\" + scoreMax + \" = \" + (score * 30) / scoreMax);"+

		"System.out.println(\"----------------------------------------------------------------\");"+

		"System.out.println();"+

		"if (score != scoreMax) {"+

		"	"+

		"} else {"+

		"	"+

		"}"+

	"}"+

"} ;";

}
