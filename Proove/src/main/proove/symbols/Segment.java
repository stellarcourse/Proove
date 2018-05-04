package proove.symbols;

import java.util.Vector;

import proove.facts.Fact;
import proove.facts.MeasuresEqual;
import proove.facts.OrientsEqual;

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

	public boolean identical(Segment other){
		return (from.equals(other.from) && to.equals(other.to));
	}
	
	public Point from(){
		return from;
	}
	
	public Point to(){
		return to;
	}
	
	public boolean mirrors(Segment other){
		return (from.equals(other.to) && to.equals(other.from));
	}
	public boolean identicalOrMirrors(Segment other){
		return identical(other)||mirrors(other);
	}
	
	public Vector<Fact> MidPoint(Point M){
		Vector<Fact> output = new Vector<Fact>();
		output.add(new MeasuresEqual<Segment>(new Segment(from, M), new Segment(M, to)));
		// add about angles
		output.add(new OrientsEqual(new Segment(from, M), new Segment(from, to)));
		output.add(new OrientsEqual(new Segment(M, to), new Segment(from, to)));
		return output;
	}
	
	@Override
	public boolean mirrors(Object obj) {
		if (this.equals(obj))
		return false;
		
		if (!(obj instanceof Segment))
			return false;
		
		Segment other = (Segment) obj;
		return mirrors(other);
	}
	public Segment getMirror(){
		return new Segment(to, from);
	}	
	private Point from;
	private Point to;
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
		return identical((Segment) obj);
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

	@Override
	public boolean identical(Object obj) {
		if (this.equals(obj))
		return false;
		
		if (!(obj instanceof Segment))
			return false;
		
		Segment other = (Segment) obj;
		return identical(other);
	}
}
