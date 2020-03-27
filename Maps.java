package test1;

import java.util.*;
import java.util.Map.Entry;

public class Maps {

	public static void main(String[] args) {
		hashMap();
	}
	
	
public static void hashMap() {
	
	Map<Integer,String> m1 =  new LinkedHashMap<Integer, String>();
	m1.put(33,"Apple");
	m1.put(100, "Samsung");
	m1.put(11, "Oppo");
	m1.put(20, "Realme");
	
	boolean isVal = m1.containsValue("Oppo");
	System.out.println(isVal);

	String val1 = m1.get(2);
	System.out.println(val1);

	Map<Integer,String> m2 =  new HashMap<Integer, String>();
	m2.putAll(m1);
	
	//Entries Set
	Set<Entry<Integer, String>> entries = m1.entrySet();

	for(Entry<Integer, String> entry : entries)
	{
		System.out.println(entry.getKey() + "-->" + entry.getValue());
	}
	
	//Only Keys
	for(int key1: m1.keySet())
	{
		System.out.println(key1);
	}
	
	//Only values
	for(String vals : m1.values())
	{
		System.out.println(vals);
	}
	
	//Using iterator
	Iterator<Entry<Integer, String>> itr = entries.iterator();
	while(itr.hasNext())
	{
		Entry<Integer, String> entry1 = itr.next();
		System.out.println(entry1.getKey() + "***" +entry1.getValue());
	}

	//Sort using values
	
	Comparator<Entry<Integer,String>> vCmp = new Comparator<Entry<Integer,String>>() {

		@Override
		public int compare(Entry<Integer, String> o1, Entry<Integer, String> o2) {
			String str1 = o1.getValue();
			String str2 = o2.getValue();
			//return str1.compareToIgnoreCase(str2);
			return str1.compareTo(str2);
		}
	};
	
	List<Entry<Integer, String>> entryList =  new ArrayList<Map.Entry<Integer,String>>(entries);
	Collections.sort(entryList,vCmp);
	
	Map<Integer, String> m3 =  new LinkedHashMap<Integer, String>();
	
	for(Entry<Integer, String> e : entryList)
	{
		m3.put(e.getKey(), e.getValue());
	}
	
	for(Entry<Integer,String> e2: m3.entrySet())
	{
	 System.out.println(e2.getKey() + "---" + e2.getValue());
	}

	//Sort using key
	Map<Integer,String> m4 = new TreeMap<Integer, String>(m1);
	
	for(Entry<Integer, String> e : m4.entrySet())
	{
		System.out.println(e.getKey()+"****"+e.getValue());
	}
	
	//Search key and get value
	if(m1.containsKey(11))
	{
		String str = m1.get(11);
		System.out.println("Value is :"+str);
	}
	
	//Search value and get key
	
	Set<Entry<Integer, String>> e2 = m1.entrySet();
	
	for(Entry<Integer,String> e : e2)
	{
		if(e.getValue().equals("Apple"))
		{
			Integer key1 = e.getKey();
			System.out.println("Key for Apple is :" + key1);
		}
	}
	

	
}
	
}
