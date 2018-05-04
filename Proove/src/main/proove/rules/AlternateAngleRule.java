package proove.rules;

import proove.facts.Fact;
import proove.facts.MeasuresEqual;
import proove.facts.OrientsEqual;
import proove.info.IncorrectProove;
import proove.symbols.Angle;
import proove.symbols.Segment;

/**
 * if Alternate Angles are equal, then two segments are in parallel<p>
 * ¡ÏABC = ¡ÏDCB  => AB//CD <p>
 * @author Hai
 *
 */
public class AlternateAngleRule extends Rule<MeasuresEqual<Angle>, OrientsEqual>{
	public AlternateAngleRule(MeasuresEqual<Angle> input, OrientsEqual output){
		super(input, output);
	}

	@Override
	public boolean check() throws IncorrectProove{
		// verifies that the input is a pair of alternate angles
		
		Angle left = input.left();
		Angle right = input.right();
		
		Segment BA = left.from();
		Segment BC = left.to();
		
		Segment CD = right.from();
		Segment CB = right.to();
		
		boolean result= BC.mirrors(CB);
		
		OrientsEqual oe = (OrientsEqual)output;
		Segment AB = oe.up();
		Segment CD2 = oe.down();
		
		boolean result1= AB.mirrors(BA) && CD.identical(CD2);
		boolean result2= AB.identical(AB) && CD.mirrors(CD2);
		
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
