package com.webapp.web.servlet.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.webapp.web.jpa.dao.ContactDao;

/**
 * BlacklistedMSISDN form controller
 * 
 * @author Faisal Adnan
 */

@Controller
public class BlacklistedMSISDNController {
	private static final String SEARCH_VIEW_KEY = "redirect:search.html";
	private static final String SEARCH_MODEL_KEY = "blacklistedmsisdns";
	
	@Autowired
	protected ContactDao contactDao = null;
}
