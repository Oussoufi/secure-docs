package mr.mayatech.securedoc.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mr.mayatech.securedoc.entity.UserEntity;
import mr.mayatech.securedoc.enumeration.EventType;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserEvent {

    private UserEntity user;
    private EventType type;
    private Map<?, ?> data;
}
