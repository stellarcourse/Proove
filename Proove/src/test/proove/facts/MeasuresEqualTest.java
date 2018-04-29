package proove.facts;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import proove.facts.Fact;
import proove.facts.MeasuresEqual;
import proove.facts.MeasuresMirror;
import proove.info.IncorrectProove;
import proove.rules.AlternateAngleRule;
import proove.symbols.AngleSymbol;
import proove.symbols.PointSymbol;
import proove.symbols.SegmentSymbol;

public class MeasuresEqualTest {
    PointSymbol A=new PointSymbol("A");
    PointSymbol B=new PointSymbol("B");
    PointSymbol C=new PointSymbol("C");
    PointSymbol D=new PointSymbol("D");
    PointSymbol E=new PointSymbol("E");
    PointSymbol F=new PointSymbol("F");
    
    SegmentSymbol AB = new SegmentSymbol(A, B);
    SegmentSymbol BA = new SegmentSymbol(B, A);
    SegmentSymbol CD = new SegmentSymbol(C, D);
    SegmentSymbol DC = new SegmentSymbol(D, C);
    SegmentSymbol EF = new SegmentSymbol(E, F);
    
    AngleSymbol ABC = new AngleSymbol(A, B, C);
    AngleSymbol CDA = new AngleSymbol(C, D, A);
    
    MeasuresEqual seABCD =new MeasuresEqual(AB, CD); 
    MeasuresEqual seCDAB =new MeasuresEqual(CD, AB);
    
    MeasuresMirror smABEF = new MeasuresMirror(AB, EF);
    MeasuresMirror smEFAB = new MeasuresMirror(EF, AB);
    
    MeasuresEqual aeBD = new MeasuresEqual(ABC, CDA);
    MeasuresEqual aeDB = new MeasuresEqual(CDA, ABC);
    
    OrientsEqual oeABCD = new OrientsEqual(AB, CD);
    OrientsEqual oeCDAB = new OrientsEqual(CD, AB);
    
    HashSet<Fact> all=new HashSet<Fact>();
    
	@Before
	public void setUp() throws Exception {
		all.add(seABCD);
	    all.add(new MeasuresMirror(AB, EF));
	    
	    all.add(new MeasuresEqual(ABC, CDA));
	    all.add(new MeasuresEqual(CDA, ABC));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualsObject() {
		assertEquals(A, A);
		assertEquals(AB, AB);
        assertNotEquals(A, AB);
        
        assertEquals(seABCD, seCDAB);
        assertEquals(smABEF, smEFAB);
        
        assertEquals(all.size(), 3);
        
        assertEquals(aeBD, aeDB);
        
        assertEquals(oeABCD, oeCDAB);
        
        System.out.println(smABEF.explain());
        System.out.println(smABEF.state());
	}
	@Test
	public void testSetSize() {
		all.clear();
		all.add(new MeasuresEqual(AB, CD));
		all.add(new MeasuresEqual(AB, CD));
		assertEquals(all.size(), 1);
		
	    SegmentSymbol BA = new SegmentSymbol(B, A);
	    SegmentSymbol DC = new SegmentSymbol(D, C);
	    
	    MeasuresEqual seBADC =new MeasuresEqual(BA, DC); 
		assertEquals(seABCD, (seBADC));
		
		all.add(seBADC);
		assertEquals(all.size(), 1);
		
		MeasuresMirror smABDC = new MeasuresMirror(AB, DC);
		MeasuresMirror smDCAB = new MeasuresMirror(DC, AB);
		MeasuresMirror smBACD = new MeasuresMirror(BA, CD);
		assertEquals(smABDC, smDCAB);
		assertEquals(smABDC, smBACD);
		
		all.add(smABDC);
		all.add(smDCAB);
		all.add(smBACD);
		
		assertEquals(all.size(), 2);
		
		OrientsEqual oeABCD = new OrientsEqual(AB, CD);
		OrientsEqual oeCDAB = new OrientsEqual(CD, AB);
		OrientsEqual oeBADC = new OrientsEqual(BA, DC);
		assertEquals(oeABCD, oeCDAB);
		assertEquals(oeABCD, oeBADC);
		
		
		OrientsMirror omABDC = new OrientsMirror(AB, DC);
		OrientsMirror omDCAB = new OrientsMirror(DC, AB);
		OrientsMirror omBACD = new OrientsMirror(BA, CD);
		assertEquals(omABDC, omDCAB);
		assertEquals(omABDC, omBACD);
		
		all.add(oeABCD);
		all.add(oeCDAB);
		all.add(oeBADC);
		
		assertEquals(all.size(), 3);
	}
	@Test
	public void testSetAngles() {
	    //MeasuresEqual aeBD = new MeasuresEqual(ABC, CDA);
	    //MeasuresEqual aeDB = new MeasuresEqual(CDA, ABC);
		AngleSymbol CBA = new AngleSymbol(C, B, A);
	    AngleSymbol ADC= new AngleSymbol(A, D, C);
	    
	    MeasuresMirror amBD = new MeasuresMirror(ABC, CDA);
	    MeasuresMirror amDB = new MeasuresMirror(CDA, ABC);
	    MeasuresMirror amBD2 = new MeasuresMirror(CBA, ADC);
	    
	    assertEquals(amBD, amDB);
	    System.out.println(amBD.hashCode());
	    System.out.println(amBD2.hashCode());
	    System.out.println(amBD.equals(amBD2));
	    assertEquals(amBD, amBD2);
	    
		MeasuresMirror amBB = new MeasuresMirror(ABC, CBA);
	}
	@Test
	public void testAlternateRule(){
		AngleSymbol DCB = new AngleSymbol(D, C, B);
		MeasuresEqual amBC = new MeasuresEqual(ABC, DCB);
		OrientsEqual oeABCD = new OrientsEqual(AB, CD);
		AlternateAngleRule aar = new AlternateAngleRule(amBC, oeABCD);
		try {
			assertTrue(aar.check());
		} catch (IncorrectProove e) {
			assertTrue(false);
		}
		
		OrientsEqual oeBADC = new OrientsEqual(BA, DC);
		
		AlternateAngleRule aar2 = new AlternateAngleRule(amBC, oeBADC);
		try {
			assertTrue(aar.check());
		} catch (IncorrectProove e) {
			assertTrue(false);
		}
	}
	@Test
	public void testGenericAlternateRule(){
		AngleSymbol DCB = new AngleSymbol(D, C, B);
		NewMeasuresEqual<AngleSymbol> amBC = new NewMeasuresEqual<>(ABC, DCB);
		OrientsEqual oeABCD = new OrientsEqual(AB, CD);
		
	}
}