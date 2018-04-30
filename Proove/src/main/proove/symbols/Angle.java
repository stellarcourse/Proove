package proove.symbols;
/**
 * ¡ÏABC is from BA to BC counter clockwise
 *    C
 *   /
 *  /       
 * B --- A
 *  
 * @author Hai
 *
 */
public class Angle extends Symbol {
	public Angle(Point a, Point b, Point c){
		super("¡Ï");
		BA=new Segment(b, a);
		BC=new Segment(b, c);
		name = a.name+b.name+c.name;
	}
	public Segment from(){return BA;}
	public Segment to(){return BC;}
	private Segment BA, BC;	// angle from BA to BC

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		int less = Math.min((BA == null) ? 0 : BA.hashCode(),(BC == null) ? 0 : BC.hashCode());
		int more = Math.max((BA == null) ? 0 : BA.hashCode(),(BC == null) ? 0 : BC.hashCode());

		result = prime * result + less;
		result = prime * result + more;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}	
	public boolean equals(Angle other){
		return 	(BA.equals(other.BA) && BC.equals(other.BC)) ||
				(BA.mirrors(other.BA) && BC.mirrors(other.BC));
	}
	
	public boolean mirrors(Angle other){
		return	(BA.equals(other.BC) && BC.equals(other.BA)) ||
				(BA.equals(other.BA) && BC.equals(other.BC));
	}
	@Override
	public boolean mirrors(Object obj) {
		if (this==obj)
			return false;
		
		if (!(obj instanceof Angle))
			return false;
		
		Angle other = (Angle) obj;
		return mirrors(other);
	}	
}
