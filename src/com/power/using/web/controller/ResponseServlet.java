package com.power.using.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.power.using.domain.Order;
import com.power.using.service.BusinessService;
import com.power.using.service.impl.BusinessServiceImpl;
import com.power.using.utils.PaymentUtil;

public class ResponseServlet extends HttpServlet {
	
	private BusinessService s=new BusinessServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		// 支付结果 1成功
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		// 订单号
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		// 1重定向2点对点
		String r9_BType = request.getParameter("r9_BType");
		String hmac = request.getParameter("hmac");

		// 验证数据的正确性
		boolean b = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid,
				r6_Order, r7_Uid, r8_MP, r9_BType, "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");

		if (b) {
			if ("1".equals(r1_Code)) {
				if ("2".equals(r9_BType)) {
					response.getWriter().write("success");
				}
				// 支付成功后更改订单状态
				Order order = s.findOrderByNum(r6_Order);
				order.setStatus(1);

				s.changeOrderStatus(order);

				response.getWriter().write("支付成功");

			} else {
				response.getWriter().write("交易失败,请重新付款");
			}

		} else {
			response.getWriter().write("支付返回的信息有误");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	}

}
