package mr.mayatech.securedoc.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import mr.mayatech.securedoc.enumeration.Authority;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@JsonInclude(NON_DEFAULT)
public class RoleEntity extends Auditable{
    private String name;
    private List<Authority> authorities;
}
