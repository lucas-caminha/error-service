package br.com.error.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.error.auth.JwtUtil;
import br.com.error.dto.login.ErrorResponse;
import br.com.error.dto.login.LoginRequest;
import br.com.error.dto.usuario.UsuarioDTO;
import br.com.error.entity.user.Usuario;
import br.com.error.service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
    private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailsService userService;
    
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json", "application/xml"})
    public ResponseEntity<?> login(@RequestBody LoginRequest loginReq)  {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            
            String email = authentication.getName();
            Usuario user = new Usuario(email, "");
            String token = jwtUtil.createToken(user);
            LoginRequest loginRes = new LoginRequest(email,token);

            return ResponseEntity.ok(loginRes);
        } catch (BadCredentialsException e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Usuario ou senha invalidos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e){
        	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = { "application/json", "application/xml"})
    public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO)  {
    	userService.saveUsuario(usuarioDTO);
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }


	
}
