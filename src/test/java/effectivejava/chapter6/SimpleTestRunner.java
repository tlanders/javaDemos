package effectivejava.chapter6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimpleTestRunner {
	public static void main(String [] args) {
		int tests = 0;
		int passed = 0;
		
		Class<?> testClass = SimpleTestSample.class;
		for(Method m : testClass.getDeclaredMethods()) {
			if(m.isAnnotationPresent(SimpleTest.class)) {
				tests++;
				try {
					m.invoke(null);
					passed++;
				} catch(InvocationTargetException ite) {
					Throwable t = ite.getCause();
					System.out.println(m + " failed: cause=" + t);
					t.printStackTrace();
				} catch(Exception ex) {
					System.out.println(m + " invalid test: exception=" + ex);
					ex.printStackTrace();
				}
			}
		}
		
		System.out.printf("tests: %d\npassed: %d\n", tests, passed);
	}
}
