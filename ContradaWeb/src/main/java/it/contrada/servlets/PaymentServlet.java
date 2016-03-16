package it.contrada.servlets;

import it.contrada.businessdelegate.GestioneTesseraBD;
import it.contrada.mail.BaseMail;
import it.contrada.web.util.PayPalUtil;
import it.othala.payment.paypal.dto.ProfilePayPalDTO;
import it.othala.payment.paypal.dto.RedirectDTO;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentServlet
 */
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Properties prop=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	 doPost(request, response);
		
		
		//GetExpressCheckoutDetails details = service.getExpressCheckoutDetails(token, profile);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProfilePayPalDTO prof = PayPalUtil.getProfile("it",request);
		String token=request.getParameter("token");
		try {
			RedirectDTO redirect=GestioneTesseraBD.confermaOrderPayment(token, prof);
			response.sendRedirect(redirect.getRedirectUrl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		} 
		
	}
	
	
}
