package proove.theorem;

import java.util.ArrayList;
import java.util.HashSet;

import proove.facts.Fact;

/**
 * Theorem can be constructed with facts and generate new set of facts<p>
 * e.g. ¡ÏABC = ¡ÏDCB ==> AB//CD<p>
 * @author Hai
 *
 */
public abstract class Axiom<TI extends Fact, TO extends Fact> {
	protected TI input;
	protected HashSet<TO> output=new HashSet<TO>();
	public Axiom(TI input){
		this.input = input;
	}
	public String explain(){
		StringBuilder result=new StringBuilder("as long as " + input.explain() + ", ");
		for(Fact f: output)
			result.append(f.explain());
		result.append(" will always be true");
		return result.toString();
	}
	public ArrayList<TO> effect()	{
		return new ArrayList<TO>(output);
	}
}