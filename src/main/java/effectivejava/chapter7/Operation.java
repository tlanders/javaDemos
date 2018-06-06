package effectivejava.chapter7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;

/**
 * Same enum as chapter 6 except this one accepts lambdas for the apply function.
 * @author tlanders
 *
 */
public enum Operation {
	ADD("+", (a,b) -> a + b),
	SUBTRACT("-", (a,b) -> a - b),
	MULTIPLY("*", (a,b) -> a * b),
	DIVIDE("/", (a,b) -> a / b);
	
	final String operationName;
	final DoubleBinaryOperator applyFcn;
	
	private Operation(String name, DoubleBinaryOperator apply) {
		operationName = name;
		applyFcn = apply;
	}
	
	@Override
	public String toString() {
		return operationName;
	}
	
	public double apply(double term1, double term2) {
		return applyFcn.applyAsDouble(term1, term2);
	}
	
	final static private Map<String,Operation> OP_SYMBOL_MAP = 
			Arrays.stream(Operation.values()).collect(Collectors.toMap(Object::toString, e -> e));
	
	static {
		for(Operation op : Operation.values()) {
			OP_SYMBOL_MAP.put(op.toString(), op);
		}
	}
	public static Optional<Operation> fromSymbol(String opSymbol) {
		return Optional.ofNullable(OP_SYMBOL_MAP.get(opSymbol));
	}
}
