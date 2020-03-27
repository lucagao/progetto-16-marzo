package com.example.demo.model;

import javax.mail.MessagingException;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.example.demo.model.User;
import com.example.demo.service.*;

@Entity
@Table(name = "user_todo")
public class ToDo implements Runnable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id")
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "activity")
    private String activity;
    @Column(name = "create_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;
    @Column(name = "expiration_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull
    private LocalDateTime expiration_date;
    public Long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }



    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

	public LocalDateTime getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(LocalDateTime expiration_date) {
		this.expiration_date = expiration_date;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		User user = new User();
		MailServiceImpl mailService = new MailServiceImpl();

		try {
			mailService.sendMail(user.getEmail(), "Activity", "You have to do some activity");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
