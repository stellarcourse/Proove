package proove.facts;

import proove.symbols.Symbol;

/**
 * A piece of information to state that x equals to y
 * example: segment length AB = CD
 * another: angle ¡ÏABC = ¡ÏCDA in Parallelogram
 * <p>
 * usage suggestion: 
 * 	keep a set of objects that are equal, allow system to verify
 *  whether something are equal
 * @author Hai
 *
 * @param <KeyType>
 */
public class MeasuresEqual<T extends Symbol> extends Fact {
	public MeasuresEqual(T a, T b){
		super("Measures Equal", "=");
		x=a;
		y=b;
	}
	private T x, y;
	public T left(){return x;}
	public T right(){return y;}
	@Override
	public String explain() {
		return x.explain() + " " + getType() +" with " + y.explain();
	}
	@Override
	public String state() {
		return x.explain() +  symbol  + y.explain();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		int less = Math.min((x == null) ? 0 : x.hashCode(),(y == null) ? 0 : y.hashCode());
		int more = Math.max((x == null) ? 0 : x.hashCode(),(y == null) ? 0 : y.hashCode());
		
		result = prime * result + less;
		result = prime * result + more;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean identical(MeasuresEqual<T> other) {
		if (this == other) {
			return true;
		}
		
		if (x.equals(other.x) && y.equals(other.y))
			return true;
		
		if (x.equals(other.y) && y.equals(other.x))
			return true;

		if (x.mirrors(other.y) && y.mirrors(other.x))
			return true;
		
		if (x.mirrors(other.x) && y.mirrors(other.y))
			return true;
		
		
		return false;
	}	
	
	@Override
	public boolean equals(Object obj){
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MeasuresEqual<?>))
			return false;
		//return equals((MeasuresEqual<T>)other);
		MeasuresEqual<?> other = (MeasuresEqual<?>) obj;
		if (x.equals(other.x) && y.equals(other.y))
			return true;
		
		if (x.equals(other.y) && y.equals(other.x))
			return true;

		if (x.mirrors(other.y) && y.mirrors(other.x))
			return true;
		
		if (x.mirrors(other.x) && y.mirrors(other.y))
			return true;
		
		
		return false;
	}
}
