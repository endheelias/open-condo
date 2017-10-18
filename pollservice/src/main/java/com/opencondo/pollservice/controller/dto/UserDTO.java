package com.opencondo.pollservice.controller.dto;

import com.opencondo.pollservice.domain.model.UserAccount;
import lombok.Getter;

/**
 * Data Transfer Object for the UserAccount Entity
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */

@Getter
public class UserDTO implements DTOMapper<UserAccount>{

    private Long id;
    private String name;
    private String username;

    @Override
    public String toString() {
        return String.format(
                "UserAccountDTO[id=%d, name='%s', username='%s']",
                id, name, username);
    }

    /**
     * Fills the DTO with information from the <code>UserAccount</code> entity.
     *
     * @param account the <code>UserAccount</code> entity
     */
    @Override
    public void buildFromEntity(UserAccount account) {
        this.id = account.getId();
        this.name = account.getName();
        this.username = account.getUsername();
    }
}
