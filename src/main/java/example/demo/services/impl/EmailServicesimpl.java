package example.demo.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import example.demo.domain.EmailDto;
import example.demo.services.EmailService;

@Service
public class EmailServicesimpl implements EmailService {

	private String host = "outlook.office365.com";
	private Object port = "993";
	private IMAPStore store;
	private String user = "raulhperez@rhpfamily.club";
	private String pwd = "day?0208O";
	private IMAPFolder folder;

	public List<EmailDto> receiveMail() {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imap");
		props.put("mail.imap.host", host);
		props.put("mail.imap.port", port);
		props.put("mail.imap.auth.login.disable", "true");
		props.put("mail.imap.auth.plain.disable", "false");
		props.put("mail.imap.ssl.enable", "true");
		props.put("mail.debug", "true");
		props.put("mail.debug.auth", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pwd);
			}
		});
		List<EmailDto> result = null;
		try {
			store = (IMAPStore) session.getStore("imap");
			store.connect(host, user, pwd);
			folder = (IMAPFolder) store.getFolder("INBOX");

			result = openFolder(folder);
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public List<EmailDto> openFolder(IMAPFolder folder) throws MessagingException, IOException {
		String subject = null;
		if (!folder.isOpen())
			folder.open(Folder.READ_WRITE);
		Message[] messages = folder.getMessages();
		System.out.println("No of Messages : " + folder.getMessageCount());
		System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
		System.out.println(messages.length);
		List<EmailDto> result = new ArrayList<>();
		int msm = folder.getMessageCount() - 1;
		if (folder.getUnreadMessageCount() > 0) {
			for (int i = 0; i < folder.getUnreadMessageCount()/* messages.length */; i++) {

			//	System.out.println("*****************************************************************************");
			//	System.out.println("MESSAGE " + (i + 1) + ":");
				Message msg = messages[msm--];
				/*
				 * System.out.println("Message Number: " + msg.getMessageNumber()); // Object
				 * String; System.out.println("UID: " + folder.getUID(msg));
				 * 
				 * subject = msg.getSubject();
				 * 
				 * System.out.println("Subject: " + subject); System.out.println("From: " +
				 * msg.getFrom()[0]); System.out.println("To: " + msg.getAllRecipients()[0]);
				 * System.out.println("Date: " + msg.getReceivedDate());
				 * System.out.println("Size: " + msg.getSize());
				 * System.out.println(msg.getFlags()); System.out.println("Body: \n" +
				 * msg.getContent()); System.out.println(msg.getContentType());
				 */

				EmailDto dto = new EmailDto();
				dto.setFrom(msg.getFrom()[0].toString());
				dto.setSubjet(msg.getSubject());
				dto.setBody(getTextFromMessage(msg));
				result.add(dto);

			}
		}
		return result;
	}
	
	private String getTextFromMessage(Message message) throws MessagingException, IOException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	private String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}

}
