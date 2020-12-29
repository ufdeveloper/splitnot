package com.megshan.splitnot.service;

import com.megshan.splitnot.domain.Account;
import com.megshan.splitnot.exceptions.AuthorizationException;
import com.megshan.splitnot.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public void authorize(String userId, String resourceId, ResourceType resourceType) {

        boolean isAuthorized;

        switch (resourceType) {
            case ACCOUNT:

                Account existingAccount = AccountService.ACCOUNTS_STORE.stream()
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
