package it.othala.payment.paypal.dto;

public class RedirectDTO {

	private String redirectUrl;
	private String contentMail;
	private String tosMail;
	private String subjectsMail;
	
	public String getSubjectsMail() {
		return subjectsMail;
	}
	public void setSubjectsMail(String subjectsMail) {
		this.subjectsMail = subjectsMail;
	}
	public String getTosMail() {
		return tosMail;
	}
	public void setTosMail(String tosMail) {
		this.tosMail = tosMail;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getContentMail() {
		return contentMail;
	}
	public void setContentMail(String contentMail) {
		this.contentMail = contentMail;
	}
	
	
}
