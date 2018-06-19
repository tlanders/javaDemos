package effectivejava.chapter7;

import java.util.*;

public class PowerSet {   
	public static final <E> List<Set<E>> of(Set<E> s) {      
		List<E> src = new ArrayList<>(s);
		if (src.size() > 30) {         
			throw new IllegalArgumentException("Set too big " + s);
		}
		
		return new AbstractList<Set<E>>() {         
			@Override 
			public int size() {            
				return 1 << src.size();
				// 2 to the power srcSize         
			}
			
			@Override 
			public boolean contains(Object o) {            
				return o instanceof Set && src.containsAll((Set)o);
			}
			
			/**
			 * Returns power set item corresponding to the index.
			 * Index is used as a bit vector to decide which items are in the 
			 * returned set.
			 */
			@Override 
			public Set<E> get(int index) {            
				Set<E> result = new HashSet<>();
				for (int i = 0; index != 0; i++, index >>= 1) {              
					if ((index & 1) == 1) {
						result.add(src.get(i));
					}
				}
				return result;
			}      
		};
	}
}