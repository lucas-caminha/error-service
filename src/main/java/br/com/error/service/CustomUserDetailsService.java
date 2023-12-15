package br.com.error.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.error.dto.usuario.UsuarioDTO;
import br.com.error.entity.user.Usuario;
import br.com.error.exception.EmptyException;
import br.com.error.repository.UsuarioRepository;
import br.com.error.utils.Validador;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<String> roles = new ArrayList<>();
        roles.add("USUARIO");
		
        Optional<Usuario> optionalUsuario = usuarioRepository.findUsuarioByEmail(email);
        
        
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(optionalUsuario.get().getEmail())
                        .password(optionalUsuario.get().getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
	}
	
	public void saveUsuario(UsuarioDTO dto) {	
		if(Validador.isNullOrEmpty(dto.getEmail()) || Validador.isNullOrEmpty(dto.getPassword()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		usuarioRepository.save(usuario);
	}

}
