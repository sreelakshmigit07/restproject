package com.pms.ust.email;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ust.pms.model.ShoppingCart;
import com.ust.pms.service.CartService;


@Service("emailService")
public class EmailService 
{
    @Autowired
    private JavaMailSender mailSender;
      
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
    
    @Autowired
    CartService cartService;
  
    /**
     * This method will send compose and send the message 
     * */
    public void sendMail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
       
        mailSender.send(message);
    }
    
    
    
    
    public String sendCartDetails(List<ShoppingCart> cartItems,String emailId,String subject, String name, Boolean isCheckout) {
    	String result =null;
        MimeMessage message =mailSender.createMimeMessage();
    try {
    	
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
    	StringBuilder sb1 = new StringBuilder(200);
    	 sb1.append("<html>"
    	 		+ "<body style='border: 1px solid #1e1563;'>"
    	 		+ "<section class='jumbotron text-center' style='background:#1e1563;'>"
    	 		+ "    <div class='container'>"
    	 		+ "        <h1 class='jumbotron-heading' style='color:white;'>Product-Portal </h1>"
    	 		
    	 		+ "     </div>"
    	 		+ "</section>"
    	 		+ "Hi "+name+",<br/><br/><br/>"+ "<div class='container'>");
    	 if(!cartItems.isEmpty()) {
    		 if(isCheckout) {
    			 sb1.append("Your Order has been placed! Thank you for Shopping with us!");
    		 }else {
    			 sb1.append("Your Cart is full! Please checkout some items."); 
    		 }
    		 sb1.append("<table  border='10px'  style='border:purple'>");
    		
    		 sb1.append("<tr>");
    		 
             sb1.append("<td><strong>")
             .append("PRODUCT NAME")
             .append("</strong></td><td><strong>")
             .append("PRODUCT ID")
             .append("</strong></td><td><strong>")
             .append("QUANTITY IN CART")
             .append("</strong></td><td><strong>")
             .append("PRICE");
          sb1.append("</strong></td><td>");
    		 for(ShoppingCart cat : cartItems){

                 sb1.append("<tr class='info'><br/>");
                 sb1.append("")
                    .append("<td>")
                    .append(cat.getPdoductName())
                    .append("</td><td>")
                    .append(cat.getProductIds())
                    .append("</td><td>")
                    .append(cat.getQuantityInCart())
                    .append("</td><td>")
                    .append(cat.getPrice());
                 sb1.append("</td><td>");
                 String in = sb1.toString();

             } 
    		 sb1.append("<tr>");
    		 sb1.append("<strong>")
                .append("TOTAL (in rupees) : ")
                .append("</strong><strong>")
                .append(cartService.subTotal(cartItems));
             sb1.append("</strong>");
    	 }else {
    		 sb1.append("<span class='success'><strong>");
    		 sb1.append("Your Cart is empty! Please Check new Arrivals! ");
    		 
          sb1.append("</strong></span>");
    	 }
         
         sb1.append("</table><hr>This is an automated message."
         		+ "</br><span>Please <a href='mailto:productportalhelp@gmail.com'>write to us </a>if you need more information."
         		+ "<br/><br/></body></html>");
         
         
         message.setContent(sb1.toString() , "text/html");
         helper.setTo(emailId);
         helper.setSubject(subject);
         result="success";
         mailSender.send(message);
     } catch (MessagingException e) {
         throw new MailParseException(e);
     }finally {
         if(result !="success"){
             result="fail";
         }
     }

     return result;
    }
    public String sendEmailToUsers(String emailId,String subject, String name){
        String result =null;
        MimeMessage message =mailSender.createMimeMessage();
        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            String htmlMsg = "<html>"
        	 		+ "<body style='border: 1px solid #1e1563;'>"
        	 		+ "<section class='jumbotron text-center' style='background:#1e1563;'>"
        	 		+ "    <div class='container'>"
        	 		+ "        <h1 class='jumbotron-heading' style='color:white;'>Product-Portal </h1>"
        	 		
        	 		+ "     </div>"
        	 		+ "</section>"
                        +"<div> Hi "+name+" ,</div> <div> Your registration is successfull.  " 
                            + "Please login to complete your new user registration.</div><div><i style='color:blue'>Product-portal.com</i></div>"+
                              "<hr>This is an automated message.</br><span>Please <a href='mailto:productportalhelp@gmail.com'>write to us </a>if you need more information.<br/><br/></body></html>";
            
            
            message.setContent(htmlMsg, "text/html");
            helper.setTo(emailId);
            helper.setSubject(subject);
            result="success";
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailParseException(e);
        }finally {
            if(result !="success"){
                result="fail";
            }
        }

        return result;

    }
  
    /**
     * This method will send a pre-configured message
     * */
    public void sendPreConfiguredMail(String message) 
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

   
}