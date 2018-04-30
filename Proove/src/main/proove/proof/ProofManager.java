package proove.proof;

import java.util.HashSet;

import proove.facts.Fact;
import proove.info.IncorrectProove;
import proove.rules.Rule;


/**
 * instance of proof manager to manage the reasoning steps
 * @author Hai
 *
 */
public class ProofManager {
	private HashSet<Fact> effects= new HashSet<Fact>();
	public void addRule(Rule<? extends Fact, ? extends Fact> x) throws IncorrectProove{
		x.verify();
		effects.add(x.effect());
	}
	
	public boolean verify(Fact x){
		return effects.contains(x);
	}
}
