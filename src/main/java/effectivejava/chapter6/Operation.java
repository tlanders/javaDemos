package effectivejava.chapter6;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Operation {
	ADD("+") { 
		@Override
		public double apply(double term1, double term2) { return term1 + term2; } 
		},
	SUBTRACT("-") {
		@Override
		public double apply(double term1, double term2) {
			return term1 - term2;
		}  },
	MULTIPLY("*") {
		@Override
		public double apply(double term1, double term2) {
			return term1 * term2;
		}  },
	DIVIDE("/") {
		@Override
		public double apply(double term1, double term2) {
			return term1 / term2;
		}  };
	
	final String operationName;
	
	private Operation(String name) {
		operationName = name;
	}
	
	@Override
	public String toString() {
		return operationName;
	}
	
	abstract public double apply(double term1, double term2);
	
	final static private Map<String,Operation> OP_SYMBOL_MAP = new HashMap<>();
	
	static {
		for(Operation op : Operation.values()) {
			OP_SYMBOL_MAP.put(op.toString(), op);
		}
	}
	public static Optional<Operation> fromSymbol(String opSymbol) {
		return Optional.ofNullable(OP_SYMBOL_MAP.get(opSymbol));
	}
}
