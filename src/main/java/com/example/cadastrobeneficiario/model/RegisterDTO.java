package com.example.cadastrobeneficiario.model;

import com.example.cadastrobeneficiario.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
