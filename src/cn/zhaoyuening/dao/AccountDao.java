package cn.zhaoyuening.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Init;





import cn.zhaoyuening.domain.AccountItem;
import cn.zhaoyuening.exception.AccountInfoException;
import cn.zhaoyuening.utils.JdbcUtils;

public class AccountDao {
	private Connection conn;
	public void startAccount(AccountItem item) throws AccountInfoException{
		//获取数据库连接
		//开启事物
		accountInit();
		//检测转出方金额是否足够
		String sql = "select amount from account where name=?";
		//获取转出方金额
		int amount_out = -1;
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getOut());
			ResultSet set = pst.executeQuery();
			if (set.next()) {
				amount_out = set.getInt("amount");
			}
			//金额不足
			if(amount_out<item.getAmount()){
				throw new AccountInfoException("转出余额不足,转账失败");
			}
			pst.close();
			//转出方转出资金
			sql="update account set amount=amount-? where name=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, item.getAmount());
			pst.setString(2, item.getOut());
			pst.executeUpdate();
			pst.close();
			//转入方转入资金
			sql="update account set amount=amount+? where name=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, item.getAmount());
			pst.setString(2, item.getIn());
			pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			//转账失败
			throw new AccountInfoException();
		}finally{
			try {
				pst.close();
			} catch (Exception e) {
				
			}
		}
		
		
	}
	
	private void accountInit() {
		try {
			conn=JdbcUtils.getConnection();
			//开启事物
			conn.setAutoCommit(false);
			
		} catch (SQLException e) {
			
		}
	}
}
