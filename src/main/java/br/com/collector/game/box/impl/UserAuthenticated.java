package br.com.collector.game.box.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.collector.game.box.model.Usuario;
import lombok.Getter;

@Getter
public class UserAuthenticated implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

    public UserAuthenticated(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getAcessos()
                .stream()
                .map(funcao -> new SimpleGrantedAuthority(funcao.getNome().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return usuario.getUserPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}