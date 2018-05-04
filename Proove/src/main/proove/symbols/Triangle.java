package proove.symbols;

public class Triangle extends Symbol {
	public Triangle(Point a, Point b, Point c){
		super("Triangle ");
		A = a;
		B = b;
		C = c;
		name = a.getName()+ b.getName() + c.getName();
	}
	private Point A, B, C;

	public Point[] Points(){
		return new Point[]{A, B, C};
	}
	
	public Segment AB(){return new Segment(A, B);}
	public Segment BC(){return new Segment(B, C);}
	public Segment CA(){return new Segment(C, A);}
	
	public Angle ABC(){return new Angle(A, B, C);}
	public Angle BCA(){return new Angle(B, C, A);}
	public Angle CAB(){return new Angle(C, A, B);}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + A.hashCode();
		result = prime * result + B.hashCode();
		result = prime * result + C.hashCode();
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}	
	
	public boolean equals(Triangle other){
		return (A.equals(other.A) && B.equals(other.B) && C.equals(other.C));
	}
	
	public boolean mirrors(Triangle other){
		return (A.equals(other.A) && C.equals(other.B) && B.equals(other.C));
	}
	public boolean equalsOrMirrors(Segment other){
		return equals(other)||mirrors(other);
	}

	@Override
	public boolean mirrors(Object obj) {
		if (!(obj instanceof Triangle))
			return false;
		
		Triangle other = (Triangle) obj;
		if (A.equals(other.A) && B.equals(other.C) && C.equals(other.B))
			return true;
		
		return false;
	}

	@Override
	public boolean identical(Object obj) {
		if (!(obj instanceof Triangle))
			return false;
		
		Triangle other = (Triangle) obj;
		return identical(other);
	}	
}
