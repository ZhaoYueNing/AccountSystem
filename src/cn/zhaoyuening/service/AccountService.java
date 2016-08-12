package cn.zhaoyuening.service;

import cn.zhaoyuening.dao.AccountDao;
import cn.zhaoyuening.domain.AccountItem;
import cn.zhaoyuening.exception.AccountInfoException;

public class AccountService {
	public static void startAccount(AccountItem item) throws AccountInfoException{
		AccountDao dao = new AccountDao();
		dao.startAccount(item);
	}
}
