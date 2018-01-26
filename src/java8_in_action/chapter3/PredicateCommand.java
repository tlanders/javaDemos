package java8_in_action.chapter3;

import java.util.function.Predicate;

public class PredicateCommand<T> {
	protected Predicate<T> finishedPredicate;
	protected T predicateObj;
	
	public PredicateCommand(T theObj, Predicate<T> thePred) {
		finishedPredicate = thePred;
		predicateObj = theObj;
	}
	
	public void run() {
		while(!isFinished()) {
			System.out.println("not finished");
		}
	}
	
	protected boolean isFinished() {
		return finishedPredicate.test(predicateObj);
	}
}
