package vn.codegym.flightagency.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.JwtResponse;
import vn.codegym.flightagency.dto.TokenDto;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.security.JwtTokenUtil;
import vn.codegym.flightagency.service.AccountService;
import vn.codegym.flightagency.service.impl.UserDetailServiceImpl;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AccountService accountService;

    @Autowired(required = false)
    private AuthenticationManager authenticationManager;

    @Autowired(required = false)
    private UserDetailServiceImpl userDetailServiceImpl;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword())
        );
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(authentication.getName());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        Account accountLogin = accountService.findByEmail(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwtToken,accountLogin.getId(), userDetails.getUsername(), accountLogin.getAvatarImageUrl(),userDetails.getAuthorities()));
    }


    @PostMapping("/google")
    public ResponseEntity<?> google(@RequestBody TokenDto tokenDto)  {
      Account accountGoogle ;

//        Google google = new GoogleTemplate(tokenDto.getValue());
//        Person googleUser = google.plusOperations().getGoogleProfile();
//        System.out.println(googleUser);

        if (accountService.existsEmail(tokenDto.getEmail())){
            accountGoogle = accountService.findByEmail(tokenDto.getEmail());
        }else{
            accountGoogle = accountService.getProfileGoogle(tokenDto);
            accountGoogle = accountService.saveAccount(accountGoogle);
        }
        UserDetails userDetails = accountService.getUserDetail(accountGoogle);
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken,accountGoogle.getId(),userDetails.getUsername(),accountGoogle.getAvatarImageUrl(), userDetails.getAuthorities()));
    }

    @PostMapping("/facebook")
    public ResponseEntity<?> facebook(@RequestBody TokenDto tokenDto)  {
        System.out.println(accountService.getProfileFacebook(tokenDto));
        Account accountFacebook ;
        if (accountService.existsEmail(tokenDto.getEmail())){
            accountFacebook = accountService.findByEmail(tokenDto.getEmail());
        }
        else{
            accountFacebook = accountService.getProfileFacebook(tokenDto);
            accountFacebook = accountService.saveAccount(accountFacebook);
        }

        UserDetails userDetails = accountService.getUserDetail(accountFacebook);
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken,accountFacebook.getId(), userDetails.getUsername(), accountFacebook.getAvatarImageUrl(),userDetails.getAuthorities()));
    }

}
