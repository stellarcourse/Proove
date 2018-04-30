package proove.theorem;

import java.util.HashSet;

import proove.facts.Fact;

/**
 * Theorem can be constructed with facts and generate new set of facts<p>
 * e.g. ¡ÏABC = ¡ÏDCB ==> AB//CD<p>
 * @author Hai
 *
 */
public abstract class Theorem<TI extends Fact> {
	protected TI input;
	protected HashSet<Fact> output=new HashSet<Fact>();
	public Theorem(TI input){
		this.input = input;
	}
	public String explain(){
		StringBuilder result=new StringBuilder("as long as " + input.explain() + ", ");
		for(Fact f: output)
			result.append(f.explain());
		result.append(" will always be true");
		return result.toString();
	}
	public HashSet<Fact> effect()	{
		return output;
	}
}