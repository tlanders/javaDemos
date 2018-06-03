package effectivejava.chapter6;

public enum ExtendedOperation implements IOperation {
	POWER("^") {
		@Override
		public double apply(double a, double b) {
			return Math.pow(a, b);
		}		
	}, 
	MODULUS("%") {
		@Override
		public double apply(double a, double b) {
			return a % b;
		}
	};
	
	final private String symbol;
	
	private ExtendedOperation(String sym) {
		symbol = sym;
	}

	@Override
	public String toString() {
		return symbol;
	}
}
