package proove.rules;

import proove.facts.Fact;
import proove.info.IncorrectProove;

/**
 * Rules can be applied to facts and generate new facts<p>
 * e.g. AB//CD so ��ABC = ��DCB<p>
 * or 	��ABC = ��DCB so AB//CD<p>
 * @author Hai
 *
 */
public abstract class Rule {
	protected Fact input;
	protected Fact output;
	public Rule(Fact input, Fact output){
		this.input = input;
		this.output= output;
	}
	public String explain(){
		return "as long as " + input.explain() + ", "+ output.explain()+" will always be true";
	}
	// passes normally or throw exception
	public abstract boolean check() throws IncorrectProove;
	public abstract void verify()throws IncorrectProove;
	public abstract Fact effect();
}