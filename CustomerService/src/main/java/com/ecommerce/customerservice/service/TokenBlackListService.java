package com.ecommerce.customerservice.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenBlackListService {

    // In-memory blacklist for token revocation
    private final Set<String> blacklist = new HashSet<>();

    public void addToBlackList(String token) {
        blacklist.add(token);
    }

    public boolean isBlackListed(String token) {
        return blacklist.contains(token);
    }


}