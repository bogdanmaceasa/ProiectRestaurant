package com.siit.finalproject.security.service;

import com.siit.finalproject.userAccounts.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetDataFromSecurityContext {

    private final UsersRepository usersRepository;

    public String getUsernameFromSecurityContext() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public boolean isAdminFromSecurityContext() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));
    }

    public boolean checkIfRequestPermittedForLoggedUser(Integer id) {
        if (!isAdminFromSecurityContext()) {
            if (!usersRepository.findByUsername(getUsernameFromSecurityContext()).get().getId().equals(id)) {
                return false;
            }
        }
        return true;
    }
}