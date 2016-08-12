package cn.zhaoyuening.domain;

import javax.servlet.http.HttpServletRequest;

import cn.zhaoyuening.exception.AccountInfoException;

public class AccountItem {
	private String in;
	private String out;
	private int amount;
	
	public String getIn() {
		return in;
	}
	public void setIn(String in) {
		this.in = in;
	}
	public String getOut() {
		return out;
	}
	public void setOut(String out) {
		this.out = out;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public static AccountItem getAccountItem(HttpServletRequest request) throws AccountInfoException{
		String out = request.getParameter("account.out");
		String in = request.getParameter("account.in");
		int amount = Integer.parseInt(request.getParameter("amount"));
		
	
		if(out==null||in==null||amount<=0){
			throw new AccountInfoException();
		}
		
		AccountItem item = new AccountItem();
		item.setIn(in);
		item.setOut(out);
		item.setAmount(amount);
		return item;
	}
}
