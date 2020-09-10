package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.model.ConfirmationToken;
import vn.codegym.flightagency.repository.AccountRepository;
import vn.codegym.flightagency.repository.ConfirmationTokenRepository;
import vn.codegym.flightagency.service.AccountService;
import vn.codegym.flightagency.service.EmailSenderService;

import java.util.Random;

//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@Controller
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private AccountService accountService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String GenerateCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }

    @GetMapping("/account/test")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/account/register")
    public ResponseEntity<?> registration(@RequestBody Account account) {

        System.out.println(account.toString());
        Account existingAccount = accountRepository.findAccountByEmail(account.getEmail());
        if (existingAccount != null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            accountService.savingAccount(account);
            ConfirmationToken confirmationToken = new ConfirmationToken(account);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(account.getEmail());
            mailMessage.setSubject("Complete Registration!");
            //tieu de Email
            mailMessage.setFrom("quangtien14dt1bkdn@gmail.com");
            //dia chi nguoi gui
            mailMessage.setText("To confirm your account, please click here: "
                    + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);
        }
        accountService.savingAccount(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/account/confirmation-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmAccount(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        
        if (token != null) {
            Account account = accountRepository.findAccountByEmail(token.getAccount().getEmail());
            account.setStatus(true);

            accountRepository.save(account);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
