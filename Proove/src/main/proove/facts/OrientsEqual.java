package proove.facts;

import proove.symbols.Segment;
import proove.symbols.Symbol;

/**
 * A piece of information to state that x equals to y
 * example: segment length AB = CD
 * another: angle ��ABC = ��CDA in Parallelogram
 * <p>
 * usage suggestion: 
 * 	keep a map of objects that are equal, allow system to verify
 *  whether something are equal
 * @author Hai
 *
 * @param <KeyType>
 */
public class OrientsEqual extends Fact {
	public OrientsEqual(Segment a, Segment b){
		super("Parallel", "=//");
		x=a;
		y=b;
	}
	public Segment up(){return x;}
	public Segment down(){return y;}
	
	private Segment x, y;
	@Override
	public String explain() {
		return x.explain() + " " + getType() +" with " + y.explain();
	}
	@Override
	public String state() {
		// TODO Auto-generated method stub
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
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof OrientsEqual)) {
			return false;
		}
		OrientsEqual other = (OrientsEqual) obj;
		
		if (x.identical(other.x) && y.identical(other.y))
			return true;
		
		if (x.identical(other.y) && y.identical(other.x))
			return true;

		if (x.mirrors(other.y) && y.mirrors(other.x))
			return true;
		
		if (x.mirrors(other.x) && y.mirrors(other.y))
			return true;

		return false;
	}	
}
