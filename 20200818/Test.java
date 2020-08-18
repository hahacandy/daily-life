package com.thuban.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		List<String> targetList = Arrays.asList("KAL12", "KAL2", "JAL23", "ANA13", "KAL5", "JAL5", "JAL1", "ANA3", "ANA25");
		
		System.out.println(targetList);
		
		//Collections.sort(targetList);
		
		
		Collections.sort(targetList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				String str1 = o1.replaceAll("[0-9]", "");
				String str2 = o2.replaceAll("[0-9]", "");
				
				int int1 = Integer.valueOf(o1.replaceAll("[^0-9]", ""));
				int int2 = Integer.valueOf(o2.replaceAll("[^0-9]", ""));
				

				if(str1.compareTo(str2) != 0) {
					return str1.compareTo(str2);
				}else {
					
					if(int1 > int2) {
						return 1;
					}else if(int1 < int2) {
						return -1;
					}else {
						return 0;
					}

				}
		
			}
		});
		
		
		
		System.out.println(targetList);
		
		
	}
	
}
