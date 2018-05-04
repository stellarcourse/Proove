package proove.symbols;

import proove.info.IncorrectCondition;

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
	
	public Angle(Segment ba, Segment bc) throws IncorrectCondition{
		super("¡Ï");
		BA = ba;
		if (!bc.from().equals(ba.from()))
			throw new IncorrectCondition("Angle created without sharing the start point");
		BC = bc;
		name = BA.to().name + BA.from().name+ BC.to().name;
	}
	
	public static Angle FlatAngle = new Angle(Point.LeftExtreme, Point.Origin, Point.RightExtreme);
	static{
		FlatAngle.rename("Flat_Angle");
	}
	public Segment from(){return BA;}
	public Segment to(){return BC;}
	private Segment BA, BC;	// angle from BA to BC
	public boolean identical(Angle other){
		return 	(BA.identical(other.BA) && BC.identical(other.BC)) ||
				(BA.mirrors(other.BA) && BC.mirrors(other.BC));
	}
	
	public boolean mirrors(Angle other){
		return	(BA.identical(other.BC) && BC.identical(other.BA)) ||
				(BA.mirrors(other.BA) && BC.mirrors(other.BC));
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
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Symbol)) {
			return false;
		}
		return identical((Angle) obj);
	}

	@Override
	public boolean identical(Object obj) {
		if (this==obj)
			return false;
		
		if (!(obj instanceof Angle))
			return false;
		
		Angle other = (Angle) obj;
		return identical(other);
	}	
}
