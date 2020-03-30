package com.example.demo.model;

import javax.mail.MessagingException;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.Date;

import com.example.demo.model.User;
import com.example.demo.service.*;

@Entity
@Table(name = "user_todo")
public class ToDo implements Runnable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="activity_id")
    private Long id;

    private String activity;

    private String type;

    private String description;

    private Date createdDate;

    private Date updatedDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    private LocalDateTime expiration_date;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    
    
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
