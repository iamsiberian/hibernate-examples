package api.security;

import com.lineate.api.dto.ExtendedUserDetails;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static com.lineate.api.SecurityConstants.HEADER_STRING;
import static com.lineate.api.SecurityConstants.SECRET;
import static com.lineate.api.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        DefaultClaims defaultClaims;
        try {
            defaultClaims = getClaims(request);
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token corrupted.");
            return;
        }
        if (defaultClaims == null || defaultClaims.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token claims empty.");
            return;
        }
        if (defaultClaims.getExpiration().before(new Date())) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired.");
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(getAuthentication(defaultClaims));
        chain.doFilter(request, response);
    }

    private DefaultClaims getClaims(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            token = token.replace(TOKEN_PREFIX, "");
            DefaultClaims claims;
            Jwt jwt = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(SECRET.getBytes()))
                .parse(token);
            claims = (DefaultClaims) jwt.getBody();
            return claims;
        }
        return null;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(DefaultClaims claims) {
        List<GrantedAuthority> roles = getAuthorities(claims.get("clientType", String.class));
        boolean notExpired = claims.getExpiration().after(new Date());
        return new UsernamePasswordAuthenticationToken(
            new ExtendedUserDetails(
                Integer.valueOf(claims.getId()),
                claims.getSubject(),
                "",
                true,
                notExpired,
                true,
                true,
                roles
            ),
            "",
            roles
        );
    }

    private static List<GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}
