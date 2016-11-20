package leetcode.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class MyValue {
	int value, freq;
	
	public MyValue(int value, int freq) {
		this.value = value;
		this.freq = freq;
	}
}

public class LfuCache {
	private Map<Integer, MyValue> lfuCache;
	private List<LinkedList<Integer>> freqList;
	int CAPACITY;

	public LfuCache(int cap) {
		this.CAPACITY = cap;
		this.lfuCache = new HashMap<>();
		this.freqList = new ArrayList<>();
	}
	
	private int get(int key) { 
		MyValue myValue = lfuCache.get(key);
		
		// value doesn't exist
		if(null == myValue) {
			return -1;
		}
		
		freqList.get(myValue.freq - 1).remove(new Integer(key));

		if(myValue.freq < CAPACITY)
			++myValue.freq;
		
		
		if(freqList.size() <= myValue.freq -1 || null == freqList.get(myValue.freq -1)) {
			freqList.add(myValue.freq -1, new LinkedList<>()); 
		}
		
		freqList.get(myValue.freq -1).add(key);
		
		lfuCache.put(key, myValue);
		
		return myValue.value;
		
	}

	private void put(int key, int value) {
		MyValue myValue = lfuCache.get(key);
		
		// value doesn't exist
		if(null == myValue) {
			myValue = new MyValue(value, 1);
			
			// time to remove least frequently used value
			if(lfuCache.size() == CAPACITY) {
				int myEvictedKey = freqList.get(0).removeFirst();
				lfuCache.remove(myEvictedKey);
			}
			
			lfuCache.put(key, myValue);
			
			if(freqList.size() < 1 || null == freqList.get(0)) {
				freqList.add(0, new LinkedList<>()); 
			}

			freqList.get(0).add(key);

		} else {
			// value already exist, so just update the frequency
			
			freqList.get(myValue.freq - 1).remove(new Integer(key));

			if(myValue.freq < CAPACITY)
				++myValue.freq;

			if(freqList.size() <= myValue.freq -1 || null == freqList.get(myValue.freq -1)) {
				freqList.add(myValue.freq -1, new LinkedList<>()); 
			}
			
			freqList.get(myValue.freq -1).add(key);
			
			lfuCache.put(key, myValue);
		}
	}

	public static void main(String[] args) {
		LfuCache cache = new LfuCache(3);

		// Key 1 : freq 1
		cache.put(1, 10);

		// Key 2 : freq 1
		cache.put(2, 20);
		
		// Key 1 : freq 2
		System.out.println(cache.get(1));
		
		// Key 3 : freq 1
		cache.put(3, 30);

		// Key 4 : freq 1
		cache.put(4, 40);

		// Now one of the LFU element should get removed which is 2. Let's see
		System.out.println(cache.get(2));

	}

}
