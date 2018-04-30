package proove.rules;

import proove.facts.Fact;
import proove.facts.NewMeasuresEqual;
import proove.facts.OrientsEqual;
import proove.info.IncorrectProove;
import proove.symbols.Angle;
import proove.symbols.Segment;
import proove.symbols.Symbol;

/**
 * if Alternate Angles are equal, then two segments are in parallel<p>
 * ¡ÏABC = ¡ÏDCB  => AB//CD <p>
 * @author Hai
 *
 */
public class NewAlternateAngleRule extends NewRule<NewMeasuresEqual<Angle>, OrientsEqual>{
	public NewAlternateAngleRule(NewMeasuresEqual<Angle> input, OrientsEqual output){
		super(input, output);
	}

	private Angle checkAngle(Symbol s) throws IncorrectProove{
		if (!(s instanceof Angle))
			throw new IncorrectProove("In AlternateAngleRule, left input is not angle symbol");
		
		return (Angle)s;
	}
	
	@Override
	public boolean check() throws IncorrectProove{
		// verifies that the input is a pair of alternate angles
		
		NewMeasuresEqual<Angle> me = (NewMeasuresEqual<Angle>)input;
		
		Angle left = checkAngle(me.left());
		Angle right = checkAngle(me.right());
		
		Segment BA = left.from();
		Segment BC = left.to();
		
		Segment CD = right.from();
		Segment CB = right.to();
		
		boolean result= BC.mirrors(CB);
		
		OrientsEqual oe = (OrientsEqual)output;
		Segment AB = oe.up();
		Segment CD2 = oe.down();
		
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
