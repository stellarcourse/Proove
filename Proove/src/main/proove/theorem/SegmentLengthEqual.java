package proove.theorem;

import proove.symbols.SegmentSymbol;

public class SegmentLengthEqual extends Statement {
	private SegmentSymbol AB, CD;
	public SegmentLengthEqual(SegmentSymbol ab, SegmentSymbol cd){
		AB = ab;
		CD = cd;
	}
	public boolean equals(SegmentLengthEqual another){
		return 	(AB.equalsOrMirrors(another.AB) && CD.equalsOrMirrors(another.CD)) ||
				(AB.equalsOrMirrors(another.CD) && CD.equalsOrMirrors(another.AB));
	}
}
