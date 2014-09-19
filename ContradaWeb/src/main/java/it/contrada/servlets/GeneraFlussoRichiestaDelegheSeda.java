package it.contrada.servlets;

import it.contrada.backingbeans.PrintFile;
import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneFlusso;
import it.contrada.preautrid.dto.FlussoPreautInviatoDTO;
import it.contrada.web.util.Costante;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icesoft.faces.context.effects.JavascriptContext;

/**
 * Servlet implementation class GeneraFlussoRichiestaDelegheSeda
 */
public class GeneraFlussoRichiestaDelegheSeda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneraFlussoRichiestaDelegheSeda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 try {
		IGestioneFlusso flusso = ContradaPojoFactory.getGestioneFlussoInstance();
			FlussoPreautInviatoDTO flussoOut=flusso.preparaFlussoPreautorizzazioniRidSeda();
			
			PrintFile filePrint = new PrintFile();
			filePrint.setNomeFileCompleto(flussoOut.getNomeFile());
			filePrint.setNomeFile(flussoOut.getNomeFileSemplice());

			String keyDownloadFile = filePrint.getNomeFile();
			
			response.setContentType("text/plain");

			



			try {
				File file = new File(filePrint.getNomeFileCompleto());
				
				response.addHeader("Content-Disposition", "attachment; filename=\""
						+ filePrint.getNomeFile()+".crm" + "\"");
				byte[] buf = new byte[1024];

				long length = file.length();
				BufferedInputStream in = new BufferedInputStream(
						new FileInputStream(file));
				ServletOutputStream out = response.getOutputStream();
				response.setContentLength((int) length);
				while ((in != null) && ((length = in.read(buf)) != -1)) {
					out.write(buf, 0, (int) length);
				}
				in.close();
				out.close();
			} catch (Exception exc) {
				exc.printStackTrace();
			}


						
		} catch (ContradaExceptionBloccante e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContradaExceptionNonBloccante e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
