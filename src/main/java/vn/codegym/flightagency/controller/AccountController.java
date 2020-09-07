package vn.codegym.flightagency.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.JwtResponse;
import vn.codegym.flightagency.dto.TokenDto;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.security.JwtTokenUtil;
import vn.codegym.flightagency.service.AccountService;




@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AccountService accountService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
         UserDetails userDetails = accountService.getUserDetail(account);
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getUsername(), userDetails.getAuthorities()));
    }


    @PostMapping("/google")
    public ResponseEntity<?> google(@RequestBody TokenDto tokenDto)  {
      Account accountGoogle ;

//        Google google = new GoogleTemplate(tokenDto.getValue());
//        Person googleUser = google.plusOperations().getGoogleProfile();
//        System.out.println(googleUser);

        if (accountService.existsEmail(tokenDto.getEmail())){
            accountGoogle = accountService.findByEmail(tokenDto.getEmail()).get();
        }else{
            accountGoogle = accountService.getProfileGoogle(tokenDto);
            accountGoogle = accountService.saveAccount(accountGoogle);
        }
        UserDetails userDetails = accountService.getUserDetail(accountGoogle);
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/facebook")
    public ResponseEntity<?> facebook(@RequestBody TokenDto tokenDto)  {
        Account accountFacebook ;
        if (accountService.existsEmail(tokenDto.getEmail())){
            accountFacebook = accountService.findByEmail(tokenDto.getEmail()).get();
        }
        else{
            accountFacebook = accountService.getProfileFacebook(tokenDto);
            accountFacebook = accountService.saveAccount(accountFacebook);
        }

        UserDetails userDetails = accountService.getUserDetail(accountFacebook);
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getUsername(), userDetails.getAuthorities()));
    }

}
