// Filter sebagai pintu utama untuk pengecekan apakah user mambawa token
package com.Xuerns.backend.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private CustomerUsersDetailsService customerUsersDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // ambil header dari header "Authirization"
        String header = request.getHeader("Authorization");
        String token = null;
        String email = null;

        // validasi apakah header berawalan "Bearer"
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            try {
                email = jwtUtil.extractEmail(token);
            } catch (Exception e) {
                System.out.println("Token tidak valid atau sudah expired");
            }
        } else {
            System.out.println("Header Authorization kosong atau tidak pakai bearer");
        }

        // jika tiket terbaca, namun user belum login di sistem
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // cari user di daftar
            UserDetails userDetails = customerUsersDetailsService.loadUserByUsername(email);

            // validasi untuk keaslian token
            if (jwtUtil.validateToken(token)) {
                // jika user sah maka pintu dibuka dan user dapat mengakses aplikasi
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // lanjut request ke controller yang dituju
        filterChain.doFilter(request, response);
    }
}
