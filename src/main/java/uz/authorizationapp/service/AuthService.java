package uz.authorizationapp.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import uz.authorizationapp.entity.Tokens;
import uz.authorizationapp.entity.User;
import uz.authorizationapp.repository.AuthRepository;
import uz.authorizationapp.repository.RoleRepository;
import uz.authorizationapp.repository.TokenRepository;
import uz.authorizationapp.security.UUIDGenerate;
import uz.authorizationapp.upload.ApiResponse;
import uz.authorizationapp.upload.LoginDto;
import uz.authorizationapp.upload.LoginResponse;
import uz.authorizationapp.upload.RegisterDto;
import uz.authorizationapp.utils.AppConstants;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager authenticationManager;

    @Autowired
    UUIDGenerate UUIDGenerate;

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
            return new ApiResponse(false, "parol bir biriga mos emas");
        if (authRepository.existsByUsernameAndState(registerDto.getUsername(), 1))
            return new ApiResponse(false, "username band !");
        User user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.getByName(AppConstants.USER)
        );
        authRepository.save(user);
        return new ApiResponse(true, "muvaffaqiyatli saqlandi");
    }


    public ApiResponse loginUser(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            ));
            String token = UUIDGenerate.generateUUID(loginDto.getUsername());
            User user = (User) authenticate.getPrincipal();
            LoginResponse response = new LoginResponse(token, user);
            return new ApiResponse(true, null, response);
        } catch (UsernameNotFoundException err) {
            return new ApiResponse(false, "Username or password is incorrect");
        }
    }
    public ApiResponse logoutUser( HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Tokens byUser = tokenRepository.findByTokenAndActive(token,true);
            if (byUser == null) {
                return new ApiResponse(false, "user not found");
            }
            byUser.setActive(false);
            tokenRepository.save(byUser);
            return new ApiResponse(true,"logout");
        }
        return new ApiResponse(false,"UnAuthorization");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
