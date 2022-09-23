package com.example.demo.Controllers;


import com.example.demo.Service.SendEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/email")
public class EmailController {

        private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);


        @Autowired
        SendEmailService sendEmailService;

        @GetMapping(value = "/simple-email/{email}")
        public @ResponseBody
        ResponseEntity<String> sendSimpleEmail(@PathVariable("email") String email) {

            try {
               sendEmailService.sendEmail(email, "Welcome", "This is a welcome email for your!!");
            } catch (MailException mailException) {
                LOG.error("Error while sending out email..{}", mailException.getStackTrace());
                LOG.error("Error while sending out email..{}", mailException.fillInStackTrace());
                return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
        }
}
