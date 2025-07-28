package com.example.employeecrud.controllers;

import com.example.employeecrud.Security.JwtUtils;
import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.repository.EmployeesRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private EmployeesRepo employeesRepo;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @PostMapping("/login")
    public GenericResponseEntity<Map<String, String>> login(@RequestBody Employees user, HttpServletResponse response) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Employees employee = employeesRepo.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            String token = jwtUtil.generateJwtToken(userDetails,employee);
            String refreshToken = jwtUtil.generateRefreshToken(userDetails);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return GenericResponseEntity.<Map<String,String>>builder()
                    .message("Tokens generated successfully")
                    .data(Map.of(
                            "accessToken", token,
                            "refreshToken", refreshToken))
                    .statusCode(201)
                    .status(HttpStatus.CREATED)
                    .success(true)
                    .build();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return GenericResponseEntity.<Map<String,String>>builder()
                    .message("Tokens not generated")
                    .data(Map.of("error", "Invalid credentials"+user.getEmail()+" "+user.getPassword()))
                    .statusCode(401)
                    .status(HttpStatus.UNAUTHORIZED)
                    .success(false)
                    .build();

        }
    }
    @PostMapping("/refresh-token")
    public GenericResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> body, HttpServletResponse response) {
        try {
            String refreshToken = body.get("refreshToken");
            String username = jwtUtil.extractUsername(refreshToken);
           UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.isTokenValid(refreshToken, userDetails)) {
                Employees emp = employeesRepo.findByEmail(username).get();
                String newAccessToken = jwtUtil.generateJwtToken(userDetails, emp);
                response.setStatus(HttpServletResponse.SC_CREATED);
                return GenericResponseEntity.<Map<String,String>>builder()
                        .message("Token generated successfully")
                        .data(Map.of("accessToken", newAccessToken))
                        .statusCode(201)
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .build();
                            }
            else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return GenericResponseEntity.<Map<String,String>>builder()
                        .message("Token not generated")
                        .data(Map.of("error", "Invalid refresh token"))
                        .statusCode(401)
                        .status(HttpStatus.UNAUTHORIZED)
                        .success(false)
                        .build();

            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return GenericResponseEntity.<Map<String,String>>builder()
                    .message("Tokens not refreshed")
                    .data(Map.of("error", "Token refresh failed"))
                    .statusCode(401)
                    .status(HttpStatus.UNAUTHORIZED)
                    .success(false)
                    .build();

        }
    }


}
