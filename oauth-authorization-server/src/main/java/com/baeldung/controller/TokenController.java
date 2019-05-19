package com.baeldung.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;

@Controller
public class TokenController {

    @Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;

    @Resource(name = "tokenStore")
    private TokenStore tokenStore;

    @RequestMapping(method = RequestMethod.POST, value = "/oauth/token/revokeById/{tokenId}")
    @ResponseBody
    public void revokeToken(HttpServletRequest request, @PathVariable String tokenId) {
        tokenServices.revokeToken(tokenId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tokens")
    @ResponseBody
    public List<String> getTokens() {
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("sampleClientId");
        return Optional.ofNullable(tokens).orElse(Collections.emptyList()).stream().map(OAuth2AccessToken::getValue).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tokens/certs")
    @ResponseBody
    public String getCerts() {

        String publicKey = null;
        try {
            final org.springframework.core.io.Resource resource = new ClassPathResource("public.txt");
            publicKey = IOUtils.toString(resource.getInputStream());
        } catch (final IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return publicKey;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tokens/revokeRefreshToken/{tokenId:.*}")
    @ResponseBody
    public String revokeRefreshToken(@PathVariable String tokenId) {
//        OAuth2AccessToken xx = ((JwtTokenStore) tokenStore).readAccessToken(tokenId);
//        String tokenValue  = xx.getValue();
        if (tokenStore instanceof JdbcTokenStore) {
            ((JdbcTokenStore) tokenStore).removeRefreshToken(tokenId);
        }else if(tokenStore instanceof JwtTokenStore){
            OAuth2RefreshToken xx =  tokenStore.readRefreshToken(tokenId);
            tokenStore.removeRefreshToken(xx);
        }


        return tokenId;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tokens/revokeAccessToken/{tokenId:.*}")
    @ResponseBody
    public String revokeAccessToken(@PathVariable String tokenId) {
        if (tokenStore instanceof JdbcTokenStore) {
            ((JdbcTokenStore) tokenStore).removeAccessToken(tokenId);
        }else if(tokenStore instanceof JwtTokenStore){
            OAuth2AccessToken xx =  tokenStore.readAccessToken(tokenId);
            tokenStore.removeAccessToken(xx);
        }
        return tokenId;
    }

}