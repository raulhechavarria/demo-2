package example.demo.services;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import example.demo.domain.EmailDto;

public interface EmailService {

	List<EmailDto> receiveMail();

}
