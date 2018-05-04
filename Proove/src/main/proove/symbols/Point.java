package proove.symbols;

public class Point extends Symbol {
	public static Point Origin = new Point("System Unique Point Origin");
	
	public static Point LeftExtreme = new Point("System Unique Point Left Extreme");
	
	public static Point RightExtreme = new Point("System Unique Point Right Extreme");
	
	public Point(String n){
		super("Point");
		name = n;
	}

	
	
	@Override
	public boolean mirrors(Object obj) {
		return false;
	}
	
	public boolean mirrors(Point obj) {
		if (this==obj)
			return true;
		else 
			return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Point)) {
			return false;
		}
		Point p = (Point)obj;
		return name.equals(p.name);	// if name equals, they are same point.  If we can proove two points are same, we can rename one of the point.
	}



	@Override
	public boolean identical(Object obj) {
		if (this==obj)
			return true;
		else 
			return false;
	}		
}
