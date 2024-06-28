package com.hendisantika.springbootrestapipostgresql.service;

import com.hendisantika.springbootrestapipostgresql.entity.Admin;
import com.hendisantika.springbootrestapipostgresql.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Optional<Admin> findByEmail(String email) {
        return Optional.ofNullable(adminRepository.findByEmail(email));
    }

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }
}
