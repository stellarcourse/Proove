package proove.facts;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import proove.facts.Fact;
import proove.info.IncorrectCondition;
import proove.info.IncorrectProove;
import proove.proof.ProofManager;
import proove.rules.AlternateAngleRule;
import proove.symbols.Angle;
import proove.symbols.Point;
import proove.symbols.Segment;
import proove.symbols.Sum;
import proove.symbols.Triangle;
import proove.theorem.Axiom;
import proove.theorem.AxiomAngleFlat;
import proove.theorem.AxiomAngleSum;
import proove.theorem.AxiomEqualTransit;

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
    Angle ABC2 = new Angle(A, B, C);
    Angle CDA = new Angle(C, D, A);
    
    MeasuresEqual<Segment> seABCD =new MeasuresEqual<>(AB, CD); 
    MeasuresEqual<Segment> seCDAB =new MeasuresEqual<>(CD, AB);
    
    MeasuresMirror<Segment> smABEF = new MeasuresMirror<>(AB, EF);
    MeasuresMirror<Segment> smEFAB = new MeasuresMirror<>(EF, AB);
    
    MeasuresEqual<Angle> aeBD = new MeasuresEqual<>(ABC, CDA);
    MeasuresEqual<Angle> aeDB = new MeasuresEqual<>(CDA, ABC);
    
    OrientsEqual oeABCD = new OrientsEqual(AB, CD);
    OrientsEqual oeCDAB = new OrientsEqual(CD, AB);
    
    HashSet<Fact> all=new HashSet<Fact>();
    
	@Before
	public void setUp() throws Exception {
		all.add(seABCD);
	    all.add(new MeasuresMirror<Segment>(AB, EF));
	    
	    all.add(new MeasuresEqual<Angle>(ABC, CDA));
	    all.add(new MeasuresEqual<Angle>(CDA, ABC));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualsObject() {
		assertEquals(A, A);
		assertTrue(A.mirrors(A));
		assertFalse(A.mirrors(AB));
		
		Segment AB2 = new Segment(A, B);
		AB.equals(AB2);
		assertEquals(AB, AB2);
        assertNotEquals(A, AB);
        
        assertEquals(ABC, ABC2);
        assertNotEquals(ABC, CDA);
        
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
		all.add(new MeasuresEqual<Segment>(AB, CD));
		all.add(new  MeasuresEqual<Segment>(AB, CD));
		assertEquals(all.size(), 1);
		
	    Segment BA = new Segment(B, A);
	    Segment DC = new Segment(D, C);
	    
	    MeasuresEqual<Segment> seBADC =new  MeasuresEqual<Segment>(BA, DC); 
		assertEquals(seABCD, (seBADC));
		
		all.add(seBADC);
		assertEquals(all.size(), 1);
		
		MeasuresMirror<Segment> smABDC = new MeasuresMirror<>(AB, DC);
		MeasuresMirror<Segment> smDCAB = new MeasuresMirror<>(DC, AB);
		MeasuresMirror<Segment> smBACD = new MeasuresMirror<>(BA, CD);
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
	    
	    MeasuresMirror<Angle> amBD = new MeasuresMirror<>(ABC, CDA);
	    MeasuresMirror<Angle> amDB = new MeasuresMirror<>(CDA, ABC);
	    MeasuresMirror<Angle> amBD2 = new MeasuresMirror<>(CBA, ADC);
	    
	    assertEquals(amBD, amDB);
	    System.out.println(amBD.hashCode());
	    System.out.println(amBD2.hashCode());
	    System.out.println(amBD.identical(amBD2));
	    assertEquals(amBD, amBD2);
	    
		MeasuresMirror<Angle> amBB = new MeasuresMirror<>(ABC, CBA);
	}
	@Test
	public void testSetTriangles() {
		Triangle Tabc = new Triangle(A, B, C);
		Triangle Tabc2 = new Triangle(A, B, C);
		Triangle Tacb = new Triangle(A, C, B);
		assertTrue(Tabc.equals(Tabc));
		assertTrue(Tabc.equals(Tabc2));
		assertTrue(Tabc.mirrors(Tacb));
		
		MeasuresEqual<Triangle> te1 = new MeasuresEqual<>(Tabc, Tacb);
		MeasuresEqual<Triangle> te2 = new MeasuresEqual<>(Tacb, Tabc);
		assertTrue(te1.identical(te2));
	}	
	@Test
	public void testAlternateRule(){
		Angle DCB = new Angle(D, C, B);
		MeasuresEqual<Angle> amBC = new MeasuresEqual<>(ABC, DCB);
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
	public void testGenericAlternateRule() throws IncorrectProove{
		Angle DCB = new Angle(D, C, B);
		MeasuresEqual<Angle> amBC = new MeasuresEqual<>(ABC, DCB);
		OrientsEqual oeABCD = new OrientsEqual(AB, CD);
		AlternateAngleRule aar = new AlternateAngleRule(amBC, oeABCD);
		ProofManager pm = new ProofManager();
		pm.addRule(aar);
		pm.verify(oeABCD);
	}
	@Test
	public void testVerticalFact() throws IncorrectProove, IncorrectCondition{
		// Vertical angles are equal
		
		// Given assumptions, or just names
		Point X = new Point("X");
		
		Segment AX = new Segment(A, X);
		Segment XB = new Segment(X, B);
		Segment CX = new Segment(C, X);
		Segment XD = new Segment(X, D);
		
		OrientsEqual oeAXB = new OrientsEqual(AX, XB);
		OrientsEqual oeCXD = new OrientsEqual(CX, XD);
		
		// deductive reasoning starts here
		MeasuresEqual<Angle> e1 = new AxiomAngleFlat(oeAXB).effect().get(0); 
		MeasuresEqual<Angle> e2 = new AxiomAngleFlat(oeCXD).effect().get(0);
		
		Sum<Angle> sa1 = new Sum<Angle>(new Angle(A, X, C), new Angle(C, X, B));
		Sum<Angle> sa2 = new Sum<Angle>(new Angle(C, X, B), new Angle(A, X, C));
		boolean act=sa1.equals(sa2);
		System.out.println(act);
		
		System.out.println(e1.explain());
		System.out.println(e2.explain());
		
		MeasuresEqual<Angle> res=new AxiomEqualTransit<Angle>(new Pair<MeasuresEqual<Angle>>(e1, e2)).effect().get(0);
		System.out.println(res.explain());
	}
	@Test
	public void testIsoscelesFact() throws IncorrectProove, IncorrectCondition{
		Triangle Tabc = new Triangle(A, B, C);
		Segment AC = new Segment(A, C);
		// AB = AC
		MeasuresEqual<Segment> smIs = new MeasuresEqual<Segment>(AB, AC);
		
		// let M be the middle point of BC
		Point  M=new Point("M"); 
		Segment BC = new Segment(B, C);
		Vector<Fact> vf=BC.MidPoint(M);
		
		Angle CBD = new Angle(C, B, D);
		Angle ABD = new Angle(A, B, D);
		AnyTwo<Angle> two=new AnyTwo<>(ABC, CBD);
		Axiom<AnyTwo<Angle>, MeasuresEqualSum<Angle>> a = new AxiomAngleSum(two);
		ArrayList<MeasuresEqualSum<Angle>> fs=a.effect();
		for(Fact f: fs){
			System.out.println(f.state());
		}
	}	
	
}