package com.suzy.entity;

public class PageResult {

    public String getRedirectUrl() {
		return RedirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		RedirectUrl = redirectUrl;
	}

	public String getPageHtml() {
		return PageHtml;
	}

	public void setPageHtml(String pageHtml) {
		PageHtml = pageHtml;
	}

	private String RedirectUrl;
	
	private String PageHtml;
	
	
}
