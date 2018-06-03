package effectivejava.chapter6;

public enum Operation {
	PLUS("+") { 
		@Override
		public double apply(double term1, double term2) { return term1 + term2; } 
		},
	MINUS("-") {
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
}
