package com.example.demo.service;

import javax.mail.MessagingException;

public interface MailService {
	
	public void sendMail(String mailAddressee, String mailObject, String mailMessage) throws MessagingException;

}

