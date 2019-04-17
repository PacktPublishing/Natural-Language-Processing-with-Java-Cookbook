package com.packt.javanlp.cookbook.chapter10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class CreatingInvertingAndUsingDictionaries {

	public static void main(String[] args) {
		Map<String, String> initalHashMap = new HashMap<>();
		
		initalHashMap.put("The", "DT");
		initalHashMap.put("dog", "NN");
		initalHashMap.put("eats", "VBZ");
		initalHashMap.put("regularly", "RB");
		
		Map<String, String> invertedHashMap = new HashMap<>();
		for(Map.Entry<String, String> entry : initalHashMap.entrySet()){
		    invertedHashMap.put(entry.getValue(), entry.getKey());
		}

		System.out.println(initalHashMap);
		System.out.println(invertedHashMap);
		System.out.println();
		
		BiMap<String, String> initialBiMap = HashBiMap.create();
		initialBiMap.put("The", "DT");
		initialBiMap.put("dog", "NN");
		initialBiMap.put("eats", "VBZ");
		initialBiMap.put("regularly", "RB");

		BiMap<String, String> invertedBiMap = initialBiMap.inverse();
		
		System.out.println(initialBiMap);
		System.out.println(invertedBiMap);
		System.out.println();
		
		Map<String, String> mapInversed = 
				initalHashMap.entrySet()
		       .stream()
		       .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
		
		System.out.println(initalHashMap);
		System.out.println(mapInversed);
		System.out.println();
		
		
		initalHashMap = new HashMap<>();
		initalHashMap.put("Time", "N");
		initalHashMap.put("flies", "V");
		initalHashMap.put("like", "P");
		initalHashMap.put("an", "D");
		initalHashMap.put("arrow", "N");

		initalHashMap.put("Time", "V");
		initalHashMap.put("flies", "N");
		initalHashMap.put("like", "P");
		initalHashMap.put("an", "D");
		initalHashMap.put("arrow", "N");

		initalHashMap.put("Time", "N");
		initalHashMap.put("flies", "N");
		initalHashMap.put("like", "V");
		initalHashMap.put("an", "D");
		initalHashMap.put("arrow", "N");

		initalHashMap.put("Time", "V");
		initalHashMap.put("flies", "V");
		initalHashMap.put("like", "V");
		initalHashMap.put("an", "D");
		initalHashMap.put("arrow", "N");

		Map<String, List<String>> invertedMap = 
		initalHashMap.entrySet()
		   .stream()
		   .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
		   
		System.out.println(initalHashMap);
		System.out.println(invertedMap);
		
	}

}
