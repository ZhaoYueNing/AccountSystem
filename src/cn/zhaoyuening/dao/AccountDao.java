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
		//��ȡ���ݿ�����
		//��������
		accountInit();
		//���ת��������Ƿ��㹻
		String sql = "select amount from account where name=?";
		//��ȡת�������
		int amount_out = -1;
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getOut());
			ResultSet set = pst.executeQuery();
			if (set.next()) {
				amount_out = set.getInt("amount");
			}
			//����
			if(amount_out<item.getAmount()){
				throw new AccountInfoException("ת������,ת��ʧ��");
			}
			pst.close();
			//ת����ת���ʽ�
			sql="update account set amount=amount-? where name=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, item.getAmount());
			pst.setString(2, item.getOut());
			pst.executeUpdate();
			pst.close();
			//ת�뷽ת���ʽ�
			sql="update account set amount=amount+? where name=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, item.getAmount());
			pst.setString(2, item.getIn());
			pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			//ת��ʧ��
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
			//��������
			conn.setAutoCommit(false);
			
		} catch (SQLException e) {
			
		}
	}
}
