package proove.symbols;

/**
 * A segment is directional from point A to point B
 * @author Hai
 *
 */
public class Segment extends Symbol {
	public Segment(Point a, Point b){
		super("Segment ");
		from = a;
		to = b;
		name = a.getName()+ b.getName();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		int less = Math.min((from == null) ? 0 : from.hashCode(),(to == null) ? 0 : to.hashCode());
		int more = Math.max((from == null) ? 0 : from.hashCode(),(to == null) ? 0 : to.hashCode());
		
		result = prime * result + less;
		result = prime * result + more;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	public boolean equals(Segment other){
		return (from.equals(other.from) && to.equals(other.to));
	}
	
	public boolean mirrors(Segment other){
		return (from.equals(other.to) && to.equals(other.from));
	}
	public boolean equalsOrMirrors(Segment other){
		return equals(other)||mirrors(other);
	}
	private Point from;
	private Point to;
	@Override
	public boolean mirrors(Object obj) {
		if (this.equals(obj))
		return false;
		
		if (!(obj instanceof Segment))
			return false;
		
		Segment other = (Segment) obj;
		if (from.equals(other.to) && to.equals(other.from))
			return true;
		
		return false;
	}
	public Segment getMirror(){
		return new Segment(to, from);
	}
}
