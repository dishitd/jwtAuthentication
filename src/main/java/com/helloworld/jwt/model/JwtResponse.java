package com.helloworld.jwt.model;

public class JwtResponse {

    private static final long serialVersionUID = 3478145875438119244L;

    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
