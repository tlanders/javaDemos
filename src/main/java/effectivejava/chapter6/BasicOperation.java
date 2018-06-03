package effectivejava.chapter6;

public enum BasicOperation implements IOperation {
	ADD("+") {
		@Override
		public double apply(double a, double b) {
			return a + b;
		}		
	}, 
	SUBTRACT("-") {
		@Override
		public double apply(double a, double b) {
			return a - b;
		}
	};
	
	final private String symbol;
	
	private BasicOperation(String sym) {
		symbol = sym;
	}

	@Override
	public String toString() {
		return symbol;
	}
}
