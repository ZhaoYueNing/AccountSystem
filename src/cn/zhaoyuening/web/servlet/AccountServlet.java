package cn.zhaoyuening.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhaoyuening.domain.AccountItem;
import cn.zhaoyuening.exception.AccountInfoException;
import cn.zhaoyuening.service.AccountService;

public class AccountServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/account.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ����
		AccountItem item;
		try {
			item = AccountItem.getAccountItem(request);
		} catch (AccountInfoException e) {
			//����Ϣ��д����
			request.getSession().setAttribute("account_info", "����Ϣ��д����ת��ʧ�ܣ���ȷ����Ϣ������");
			response.sendRedirect(request.getContextPath()+"/account.jsp");
			return ;
		}
		//��ת��ҵ�񽻸�service->dao�㴦��
		//�õ�ת�˽��
		try {
			AccountService.startAccount(item);
			//�ɹ�
			request.getSession().setAttribute("account_info", "ת�˳ɹ�");
		} catch (AccountInfoException e) {
			//ʧ��
			String msg = e.getMessage();
			request.getSession().setAttribute("account_info", msg);
			e.printStackTrace();
		}
		//����
		response.sendRedirect(request.getContextPath()+"/account.jsp");
		
	}

}
