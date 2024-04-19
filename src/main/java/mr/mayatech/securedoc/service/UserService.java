package mr.mayatech.securedoc.service;

import mr.mayatech.securedoc.entity.RoleEntity;
import mr.mayatech.securedoc.enumeration.Authority;

public interface UserService {
    void createUser(String firstName, String lastName, String email, String password);

    RoleEntity getRoleName(String name);

    void verifyAccountKey(String key);
}
