package com.megshan.splitnot.service;

import com.megshan.splitnot.data.AccountRepository;
import com.megshan.splitnot.data.CustomAccountRepository;
import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.exceptions.AuthorizationException;
import com.megshan.splitnot.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private AccountRepository accountRepository;

    public void authorize(String userId, String resourceId, ResourceType resourceType) {

        boolean isAuthorized;

        switch (resourceType) {
            case ACCOUNT:
                Account existingAccount = accountRepository.findByUserId(userId).stream()
                        .filter(account -> account.getId().equals(resourceId))
                        .findFirst()
                        .orElseThrow(() -> new NotFoundException("account not found with accountId=" + resourceId));

                isAuthorized = existingAccount.getUserId().equals(userId);
                break;

            default:
                isAuthorized = false;
        }

        if (!isAuthorized) {
            throw new AuthorizationException("user not authorized to access " + resourceType + "=" + resourceId);
        }
    }
}
