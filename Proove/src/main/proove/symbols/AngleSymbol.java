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
public class AngleSymbol extends Symbol {
	public AngleSymbol(PointSymbol a, PointSymbol b, PointSymbol c){
		super("¡Ï");
		BA=new SegmentSymbol(b, a);
		BC=new SegmentSymbol(b, c);
		name = a.name+b.name+c.name;
	}
	public SegmentSymbol from(){return BA;}
	public SegmentSymbol to(){return BC;}
	private SegmentSymbol BA, BC;	// angle from BA to BC

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
	public boolean equals(AngleSymbol other){
		return 	(BA.equals(other.BA) && BC.equals(other.BC)) ||
				(BA.mirrors(other.BA) && BC.mirrors(other.BC));
	}
	
	public boolean mirrors(AngleSymbol other){
		return	(BA.equals(other.BC) && BC.equals(other.BA)) ||
				(BA.equals(other.BA) && BC.equals(other.BC));
	}
	@Override
	public boolean mirrors(Object obj) {
		if (this==obj)
			return false;
		
		if (!(obj instanceof AngleSymbol))
			return false;
		
		AngleSymbol other = (AngleSymbol) obj;
		return mirrors(other);
	}	
}
