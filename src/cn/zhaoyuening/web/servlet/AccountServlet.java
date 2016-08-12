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
		//获取数据
		AccountItem item;
		try {
			item = AccountItem.getAccountItem(request);
		} catch (AccountInfoException e) {
			//表单信息填写错误
			request.getSession().setAttribute("account_info", "表单信息填写错误，转账失败，请确认信息后重试");
			response.sendRedirect(request.getContextPath()+"/account.jsp");
			return ;
		}
		//将转账业务交给service->dao层处理
		//得到转账结果
		try {
			AccountService.startAccount(item);
			//成功
			request.getSession().setAttribute("account_info", "转账成功");
		} catch (AccountInfoException e) {
			//失败
			String msg = e.getMessage();
			request.getSession().setAttribute("account_info", msg);
			e.printStackTrace();
		}
		//处理
		response.sendRedirect(request.getContextPath()+"/account.jsp");
		
	}

}
