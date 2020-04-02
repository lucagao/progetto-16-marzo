package com.example.demo.model;

import javax.mail.MessagingException;

import com.example.demo.service.MailService;

public class MyRunnable implements Runnable {
	
	private MailService mail;
	
	private Activity activity;
	
	public MyRunnable(Activity activity, MailService mail) {
		this.activity = activity;
		this.mail = mail;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		User currUser = activity.getUser();
		try {
			mail.sendMail(currUser.getEmail(), "Reminder " + activity.getActivityTitle(), "This activity will expired in 30 minutes.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

}