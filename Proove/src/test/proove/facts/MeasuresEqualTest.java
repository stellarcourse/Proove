package proove.facts;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import proove.facts.Fact;
import proove.info.IncorrectProove;
import proove.proof.ProofManager;
import proove.rules.NewAlternateAngleRule;
import proove.symbols.Angle;
import proove.symbols.Point;
import proove.symbols.Segment;

public class MeasuresEqualTest {
    Point A=new Point("A");
    Point B=new Point("B");
    Point C=new Point("C");
    Point D=new Point("D");
    Point E=new Point("E");
    Point F=new Point("F");
    
    Segment AB = new Segment(A, B);
    Segment BA = new Segment(B, A);
    Segment CD = new Segment(C, D);
    Segment DC = new Segment(D, C);
    Segment EF = new Segment(E, F);
    
    Angle ABC = new Angle(A, B, C);
    Angle CDA = new Angle(C, D, A);
    
    NewMeasuresEqual<Segment> seABCD =new NewMeasuresEqual<>(AB, CD); 
    NewMeasuresEqual<Segment> seCDAB =new NewMeasuresEqual<>(CD, AB);
    
    NewMeasuresMirror<Segment> smABEF = new NewMeasuresMirror<>(AB, EF);
    NewMeasuresMirror<Segment> smEFAB = new NewMeasuresMirror<>(EF, AB);
    
    NewMeasuresEqual<Angle> aeBD = new NewMeasuresEqual<>(ABC, CDA);
    NewMeasuresEqual<Angle> aeDB = new NewMeasuresEqual<>(CDA, ABC);
    
    OrientsEqual oeABCD = new OrientsEqual(AB, CD);
    OrientsEqual oeCDAB = new OrientsEqual(CD, AB);
    
    HashSet<Fact> all=new HashSet<Fact>();
    
	@Before
	public void setUp() throws Exception {
		all.add(seABCD);
	    all.add(new NewMeasuresMirror<Segment>(AB, EF));
	    
	    all.add(new NewMeasuresEqual<Angle>(ABC, CDA));
	    all.add(new NewMeasuresEqual<Angle>(CDA, ABC));
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
		all.add(new NewMeasuresEqual<Segment>(AB, CD));
		all.add(new  NewMeasuresEqual<Segment>(AB, CD));
		assertEquals(all.size(), 1);
		
	    Segment BA = new Segment(B, A);
	    Segment DC = new Segment(D, C);
	    
	    NewMeasuresEqual<Segment> seBADC =new  NewMeasuresEqual<Segment>(BA, DC); 
		assertEquals(seABCD, (seBADC));
		
		all.add(seBADC);
		assertEquals(all.size(), 1);
		
		NewMeasuresMirror<Segment> smABDC = new NewMeasuresMirror<>(AB, DC);
		NewMeasuresMirror<Segment> smDCAB = new NewMeasuresMirror<>(DC, AB);
		NewMeasuresMirror<Segment> smBACD = new NewMeasuresMirror<>(BA, CD);
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
		Angle CBA = new Angle(C, B, A);
	    Angle ADC= new Angle(A, D, C);
	    
	    NewMeasuresMirror<Angle> amBD = new NewMeasuresMirror<>(ABC, CDA);
	    NewMeasuresMirror<Angle> amDB = new NewMeasuresMirror<>(CDA, ABC);
	    NewMeasuresMirror<Angle> amBD2 = new NewMeasuresMirror<>(CBA, ADC);
	    
	    assertEquals(amBD, amDB);
	    System.out.println(amBD.hashCode());
	    System.out.println(amBD2.hashCode());
	    System.out.println(amBD.equals(amBD2));
	    assertEquals(amBD, amBD2);
	    
		NewMeasuresMirror<Angle> amBB = new NewMeasuresMirror<>(ABC, CBA);
	}
	@Test
	public void testAlternateRule(){
		Angle DCB = new Angle(D, C, B);
		NewMeasuresEqual<Angle> amBC = new NewMeasuresEqual<>(ABC, DCB);
		OrientsEqual oeABCD = new OrientsEqual(AB, CD);
		NewAlternateAngleRule aar = new NewAlternateAngleRule(amBC, oeABCD);
		try {
			assertTrue(aar.check());
		} catch (IncorrectProove e) {
			assertTrue(false);
		}
		
		OrientsEqual oeBADC = new OrientsEqual(BA, DC);
		
		NewAlternateAngleRule aar2 = new NewAlternateAngleRule(amBC, oeBADC);
		try {
			assertTrue(aar.check());
		} catch (IncorrectProove e) {
			assertTrue(false);
		}
	}
	@Test
	public void testGenericAlternateRule() throws IncorrectProove{
		Angle DCB = new Angle(D, C, B);
		NewMeasuresEqual<Angle> amBC = new NewMeasuresEqual<>(ABC, DCB);
		OrientsEqual oeABCD = new OrientsEqual(AB, CD);
		NewAlternateAngleRule aar = new NewAlternateAngleRule(amBC, oeABCD);
		ProofManager pm = new ProofManager();
		pm.addRule(aar);
		pm.verify(oeABCD);
	}
}