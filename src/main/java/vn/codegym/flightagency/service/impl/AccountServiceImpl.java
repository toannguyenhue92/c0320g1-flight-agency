package vn.codegym.flightagency.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Date;
import com.google.api.services.people.v1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.TokenDto;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.model.dto.employeeInfoDto;
import vn.codegym.flightagency.repository.AccountRepository;
import vn.codegym.flightagency.security.JwtTokenUtil;
import vn.codegym.flightagency.service.AccountService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Value("${secretPsw}")
    String secretPsw;

    @Autowired(required = false)
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired(required = false)
    private AuthenticationManager authenticationManager;
    //creator: Mậu
    @Override
    public employeeInfoDto findEmployeeInfoDtoById(Long id) {
        employeeInfoDto employeeInfoDtos = new employeeInfoDto();
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            employeeInfoDtos.setFullName(account.getFullName());
            employeeInfoDtos.setGender(account.getGender());
            employeeInfoDtos.setBirthday(account.getBirthDate());
            employeeInfoDtos.setEmail(account.getEmail());
            employeeInfoDtos.setPhoneNumber(account.getPhoneNumber());
            employeeInfoDtos.setAddress(account.getAddress());
            employeeInfoDtos.setAvatarUrl(account.getAvatarImageUrl());
            return employeeInfoDtos;
        }
        return null;
    }

    //creator: Mậu
    @Override
    public Account findEmployeeById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    //creator: Mậu
    @Override
    public void changePassword(employeeInfoDto employeeInfoDto) {
        Account account = accountRepository.findById(employeeInfoDto.getId()).orElse(null);
        assert account != null;
        List<String> messages = new ArrayList<>();
        if (!employeeInfoDto.getPassword().equals("")) {
            if (!employeeInfoDto.getNewPassword().equals("")) {
                if (BCrypt.checkpw(employeeInfoDto.getPassword(), account.getPassword())) {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    account.setPassword(encoder.encode(employeeInfoDto.getNewPassword()));
                } else {
                    messages.add("Mật khẩu hiện tại không đúng. Xin vui lòng nhập lại.");
                }
            }
        }
        employeeInfoDto.setBackendMessage(messages);
        if (employeeInfoDto.getBackendMessage().size() == 0) {
            accountRepository.save(account);
        }

    }

    //Created by: Quân
    @Override
    public boolean existsEmail(String email) {
        return accountRepository.existsAccountByEmailAndStatusTrue(email);
    }
    //Created by: Quân
    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmailAndStatusIsTrue(email);
    }
    //Created by: Quân
    @Override
    public Account saveAccount(Account account) {
        accountRepository.save(account);
        return account;
    }

    //Created by: Quân
    @Override
    public UserDetails getUserDetail(Account account) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getEmail(), secretPsw)
        );
        return userDetailServiceImpl.loadUserByUsername(authentication.getName());
    }
    //Created by: Quân
    @Override
    public Account getProfileGoogle(TokenDto tokenDto) {
        final NetHttpTransport transport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();

        GoogleCredential credential = new GoogleCredential.Builder().build().setAccessToken(tokenDto.getValue());
        PeopleService peopleService =
                new PeopleService(transport, jacksonFactory, credential);
        Person profile = null;
        try {
            profile = peopleService.people().get("people/me")
                    .setPersonFields("emailAddresses,birthdays,names,genders,photos,addresses,phoneNumbers")
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Account account = null;
        if (profile != null) {
            String email = profile.getEmailAddresses().get(0).getValue();
            Date date = profile.getBirthdays().get(0).getDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate birthday = LocalDate.parse(date.getDay() + "/" + date.getMonth() + "/" + date.getYear(), formatter );
            String name = profile.getNames().get(0).getDisplayName();
            String gender = profile.getGenders().get(0).getValue();
            gender = checkGender(gender);
            account = new Account(email, passwordEncoder.encode(secretPsw), "ROLE_USER",
                    true, name, birthday, tokenDto.getAvatarURL(), gender);

        } else {
            Logger.getLogger("Not profile google");
        }

        return account;
    }
    //Created by: Quân
    @Override
    public Account getProfileFacebook(TokenDto tokenDto) {
        Facebook facebook = new FacebookTemplate(tokenDto.getValue());
        final String[] fields = {"email", "birthday", "name", "gender"};
        User userFacebook = facebook.fetchObject("me", User.class, fields);
        String email = userFacebook.getEmail();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthday = LocalDate.parse(userFacebook.getBirthday(),formatter);
        String name = userFacebook.getName();
        String gender = userFacebook.getGender();
         gender = checkGender(gender);
        return new Account(email, passwordEncoder.encode(secretPsw), "ROLE_USER",
                true, name, birthday, tokenDto.getAvatarURL(), gender);
    }

    public String checkGender(String gender){
        if("male".equals(gender)){
            return "nam";
        }else {
            return "nữ";
        }


    }
}

