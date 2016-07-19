package org.iti.framework.hebut.testColeection;

import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("StringNote")
@Scope("prototype")
public class StringNote extends AbstractHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8318087634605939758L;

	@Override
	public String defaultExecute() throws Throwable {

		String a = "a";
		String b = "b";
		System.out.println("a's hasCode is:"+a.hashCode());
		System.out.println("b's hasCode is:"+b.hashCode());
		StringNote ts = new StringNote();
		ts.change(a, b);
		System.out.println(a+b);
		System.out.println("--------");
		System.out.println(ts.change(b));
		System.out.println(b);
		System.out.println("_bbb's hasCode is:"+ts.change(b).hashCode());
		System.out.println("b's hasCode is:"+b.hashCode());
		return super.defaultExecute();
	}
	
	public void change(String _a, String _b){
		_a = "aa";
		_b = "bb";
		System.out.println("method change _a's hasCode is:"+_a.hashCode());
		System.out.println("method change _b's hasCode is:"+_b.hashCode());
	}
	
	public String change(String _b){
		_b = "bbb";
		return _b;
	}
	
	@Override
	public String getResponState() {
		return responState;
	}

	@Override
	public String getResponResult() {
		return responResult;
	}

}
