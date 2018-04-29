package proove.rules;

import proove.facts.Fact;
import proove.facts.MeasuresEqual;
import proove.facts.NewMeasuresEqual;
import proove.facts.OrientsEqual;
import proove.info.IncorrectProove;
import proove.symbols.AngleSymbol;
import proove.symbols.SegmentSymbol;
import proove.symbols.Symbol;

/**
 * if Alternate Angles are equal, then two segments are in parallel<p>
 * ¡ÏABC = ¡ÏDCB  => AB//CD <p>
 * @author Hai
 *
 */
public class NewAlternateAngleRule extends NewRule<NewMeasuresEqual<AngleSymbol>, OrientsEqual>{
	public NewAlternateAngleRule(NewMeasuresEqual<AngleSymbol> input, OrientsEqual output){
		super(input, output);
	}

	private AngleSymbol checkAngle(Symbol s) throws IncorrectProove{
		if (!(s instanceof AngleSymbol))
			throw new IncorrectProove("In AlternateAngleRule, left input is not angle symbol");
		
		return (AngleSymbol)s;
	}
	
	@Override
	public boolean check() throws IncorrectProove{
		// verifies that the input is a pair of alternate angles
		
		NewMeasuresEqual<AngleSymbol> me = (NewMeasuresEqual<AngleSymbol>)input;
		
		AngleSymbol left = checkAngle(me.left());
		AngleSymbol right = checkAngle(me.right());
		
		SegmentSymbol BA = left.from();
		SegmentSymbol BC = left.to();
		
		SegmentSymbol CD = right.from();
		SegmentSymbol CB = right.to();
		
		boolean result= BC.mirrors(CB);
		
		OrientsEqual oe = (OrientsEqual)output;
		SegmentSymbol AB = oe.up();
		SegmentSymbol CD2 = oe.down();
		
		boolean result1= AB.mirrors(BA) && CD.equals(CD2);
		boolean result2= AB.equals(AB) && CD.mirrors(CD2);
		
		return result && (result1|| result2);
	}
	@Override
	public void verify() throws IncorrectProove {
		if (!check())
			throw new IncorrectProove("Two alternate angle not sharing same side");
	}

	@Override
	public Fact effect() {
		return output;
	}
}
