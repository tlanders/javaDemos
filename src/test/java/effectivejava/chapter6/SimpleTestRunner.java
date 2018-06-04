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
			} else if(m.isAnnotationPresent(SimpleExceptionTest.class)) {
				tests++;

				try {
					m.invoke(null);
					System.out.println(m + " test failed: no exception thrown");
				} catch(InvocationTargetException ite) {
					Throwable t = ite.getCause();
					Class<? extends Throwable> expectedException = m.getAnnotation(SimpleExceptionTest.class).value();
					if(expectedException.isInstance(t)) {
						passed++;
					} else {
						System.out.println(m + " failed: wrong exception thrown, cause=" + t + ", expected=" + expectedException);
						ite.printStackTrace();						
					}
				} catch(Exception ex) {
					System.out.println(m + " invalid exception test: exception=" + ex);
					ex.printStackTrace();
				}
			}
		}
		
		System.out.printf("tests: %d\npassed: %d\nfailed: %d\n", tests, passed, tests - passed);
	}
}
