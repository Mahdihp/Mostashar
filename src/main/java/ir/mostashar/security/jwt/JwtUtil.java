package ir.mostashar.security.jwt;

import ir.mostashar.model.client.dto.ValidateCode;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Component
public class JwtUtil {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;


    public JwtResponse generateToken(@Valid @RequestBody ValidateCode validateCode) {
        Optional<User> userOptional = userRepo.findByUidAndVerificationCode(UUID.fromString(validateCode.getUserId()), validateCode.getCode());

        if (userOptional.isPresent()) {

            System.out.println("Log-----------generateToken Username " + userOptional.get().getUsername());
            System.out.println("Log-----------generateToken Password " + userOptional.get().getPassword());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userOptional.get().getUsername(),
                            userOptional.get().getUsername())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateJwtToken(authentication);
            return new JwtResponse(jwt);
        }
        return null;
    }

    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
