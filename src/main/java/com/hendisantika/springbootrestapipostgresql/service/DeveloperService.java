package com.hendisantika.springbootrestapipostgresql.service;

import com.hendisantika.springbootrestapipostgresql.entity.Developer;
import com.hendisantika.springbootrestapipostgresql.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    public List<Developer> findAll() {
        return developerRepository.findAll();
    }

    public Optional<Developer> findById(Long id) {
        return developerRepository.findById(id);
    }

    public Developer save(Developer developer) {
        return developerRepository.save(developer);
    }

    public void deleteById(Long id) {
        developerRepository.deleteById(id);
    }
}
