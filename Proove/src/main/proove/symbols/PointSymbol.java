package proove.symbols;

public class PointSymbol extends Symbol {
	public PointSymbol(String n){
		super("Point");
		name = n;
	}

	@Override
	public boolean mirrors(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
