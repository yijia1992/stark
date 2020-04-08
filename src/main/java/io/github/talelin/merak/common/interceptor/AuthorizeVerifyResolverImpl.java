package io.github.talelin.merak.common.interceptor;

import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import io.github.talelin.autoconfigure.exception.AuthenticationException;
import io.github.talelin.autoconfigure.exception.AuthorizationException;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.autoconfigure.exception.TokenInvalidException;
import io.github.talelin.core.annotation.RouteMeta;
import io.github.talelin.merak.common.LocalUser;
import io.github.talelin.merak.model.PermissionDO;
import io.github.talelin.merak.model.UserDO;
import io.github.talelin.merak.service.GroupService;
import io.github.talelin.merak.service.UserService;
import io.github.talelin.autoconfigure.interfaces.AuthorizeVerifyResolver;
import io.github.talelin.core.token.DoubleJWT;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class AuthorizeVerifyResolverImpl implements AuthorizeVerifyResolver {

    public final static String authorizationHeader = "Authorization";

    public final static String bearerPattern = "^Bearer$";

    @Autowired
    private DoubleJWT jwt;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;


    public boolean handleLogin(HttpServletRequest request, HttpServletResponse response, RouteMeta meta) {
        String tokenStr = verifyHeader(request, response);
        Map<String, Claim> claims = null;
        try {
            claims = jwt.decodeAccessToken(tokenStr);
        } catch (TokenExpiredException e) {
            throw new io.github.talelin.autoconfigure.exception.TokenExpiredException(e.getMessage(), 10051);
        } catch (AlgorithmMismatchException | SignatureVerificationException | JWTDecodeException | InvalidClaimException e) {
            throw new TokenInvalidException(e.getMessage(), 10041);
        }
        return getClaim(claims);
    }

    @Override
    public boolean handleGroup(HttpServletRequest request, HttpServletResponse response, RouteMeta meta) {
        handleLogin(request, response, meta);
        UserDO user = LocalUser.getLocalUser();
        if (verifyAdmin(user))
            return true;
        long userId = user.getId();
        String permission = meta.permission();
        String module = meta.module();
        List<PermissionDO> permissions = userService.getUserPermissions(userId);
        boolean matched = permissions.stream().anyMatch(it -> it.getModule().equals(module) && it.getName().equals(permission));
        if (!matched)
            throw new AuthenticationException("you don't have the permission to access", 10001);
        return true;
    }

    public boolean handleAdmin(HttpServletRequest request, HttpServletResponse response, RouteMeta meta) {
        handleLogin(request, response, meta);
        UserDO user = LocalUser.getLocalUser();
        if (!verifyAdmin(user))
            throw new AuthenticationException("you don't have the permission to access", 10001);
        return true;
    }


    public boolean handleRefresh(HttpServletRequest request, HttpServletResponse response, RouteMeta meta) {
        String tokenStr = verifyHeader(request, response);
        Map<String, Claim> claims = null;
        try {
            claims = jwt.decodeRefreshToken(tokenStr);
        } catch (TokenExpiredException e) {
            throw new io.github.talelin.autoconfigure.exception.TokenExpiredException(e.getMessage(), 10051);
        } catch (AlgorithmMismatchException | SignatureVerificationException | JWTDecodeException | InvalidClaimException e) {
            throw new TokenInvalidException(e.getMessage(), 10041);
        }
        return getClaim(claims);
    }

    @Override
    public boolean handleNotHandlerMethod(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    private boolean getClaim(Map<String, Claim> claims) {
        if (claims == null) {
            throw new TokenInvalidException("token is invalid, can't be decode", 10041);
        }
        int identity = claims.get("identity").asInt();
        UserDO user = userService.getById(identity);
        if (user == null) {
            throw new NotFoundException("user is not found", 10021);
        }
        LocalUser.setLocalUser(user);
        return true;
    }

    /**
     * 检查用户是否为管理员
     *
     * @param user 用户
     */
    private boolean verifyAdmin(UserDO user) {
        return groupService.checkIsRootByUserId(user.getId());
    }

    private String verifyHeader(HttpServletRequest request, HttpServletResponse response) {
        // 处理头部header,带有access_token的可以访问
        String authorization = request.getHeader(authorizationHeader);
        if (authorization == null || Strings.isBlank(authorization)) {
            throw new AuthorizationException("authorization field is required", 10012);
        }
        String[] splits = authorization.split(" ");
        if (splits.length != 2) {
            throw new AuthorizationException("authorization field is invalid", 10013);
        }
        // Bearer 字段
        String scheme = splits[0];
        // token 字段
        String tokenStr = splits[1];
        if (!Pattern.matches(bearerPattern, scheme)) {
            throw new AuthorizationException("authorization field is invalid", 10013);
        }
        return tokenStr;
    }
}
