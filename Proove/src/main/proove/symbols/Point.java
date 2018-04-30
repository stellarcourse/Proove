package proove.symbols;

public class Point extends Symbol {
	public Point(String n){
		super("Point");
		name = n;
	}

	@Override
	public boolean mirrors(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
