package mr.mayatech.securedoc.repository;

import mr.mayatech.securedoc.entity.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {

    Optional<CredentialEntity> getCredentialByUserEntityId(Long useerId);
}
