package com.example.demo.service;

import javax.mail.MessagingException;

public interface MailService {
	public void sendMail(String destinatarioMail, String oggettoMail, String messaggioMail) throws MessagingException;

}