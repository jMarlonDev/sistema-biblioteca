package com.biblioteca.repository;

import com.biblioteca.entity.Administrator;

public interface AdministratorRepository {

    Administrator findByEmail(String email);

    boolean existsByEmail(String email);

}
