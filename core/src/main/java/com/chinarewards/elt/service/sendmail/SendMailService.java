package com.chinarewards.elt.service.sendmail;

import com.chinarewards.elt.model.user.UserContext;

/**
 * Service of corporation.
 * 
 * @author lw
 * @since 1.5
 */
public interface SendMailService {

	
	
	/**
	 * 发邮件
	 * @param 
	 * @return
	 */
	public String sendMail(String info,String staffId);
	
}
