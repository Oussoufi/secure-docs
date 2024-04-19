package mr.mayatech.securedoc.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.mayatech.securedoc.entity.ConfirmationEntity;
import mr.mayatech.securedoc.entity.CredentialEntity;
import mr.mayatech.securedoc.entity.RoleEntity;
import mr.mayatech.securedoc.entity.UserEntity;
import mr.mayatech.securedoc.enumeration.Authority;
import mr.mayatech.securedoc.enumeration.EventType;
import mr.mayatech.securedoc.event.UserEvent;
import mr.mayatech.securedoc.exception.ApiException;
import mr.mayatech.securedoc.repository.ConfirmationRepository;
import mr.mayatech.securedoc.repository.CredentialRepository;
import mr.mayatech.securedoc.repository.RoleRepository;
import mr.mayatech.securedoc.repository.UserRepository;
import mr.mayatech.securedoc.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

import static mr.mayatech.securedoc.utils.UserUtils.createUserEntity;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CredentialRepository credentialRepository;
    private final ConfirmationRepository confirmationRepository;
    //private final BCryptPasswordEncoder encoder;
    private final ApplicationEventPublisher publisher;

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        var userEntity = userRepository.save((createNewUser(firstName, lastName, email)));
        var credentialEntity = new CredentialEntity(userEntity, password);
        credentialRepository.save(credentialEntity);
        var confirmationEntity = new ConfirmationEntity(userEntity);
        confirmationRepository.save(confirmationEntity);
        publisher.publishEvent(new UserEvent(userEntity, EventType.REGISTRATION, Map.of("key", confirmationEntity.getKey())));
    }


    @Override
    public RoleEntity getRoleName(String name) {
         var role = roleRepository.findByNameIgnoreCase(name);
         return role.orElseThrow(() -> new ApiException("Role not found"));
    }

    @Override
    public void verifyAccountKey(String key) {
        var confirmationEntity = getUserConfirmation(key);
        UserEntity userEntity = getUserEntityByEmail(confirmationEntity.getUserEntity().getEmail());
        userEntity.setEnabled(true);
        userRepository.save(userEntity);
        confirmationRepository.delete(confirmationEntity);

    }

    private UserEntity getUserEntityByEmail(String email) {
        var userByEmail = userRepository.findByEmailIgnoreCase(email);
        return userByEmail.orElseThrow(()-> new ApiException("User not found"));
    }

    private ConfirmationEntity getUserConfirmation(String key) {

        return confirmationRepository.findByKey(key).orElseThrow(() -> new ApiException("Confirmation key not found"));
    }

    private UserEntity createNewUser(String firstName, String lastName, String email){
        var role = getRoleName(Authority.USER.name());
        return createUserEntity(firstName, lastName, email, role);
    }


}
