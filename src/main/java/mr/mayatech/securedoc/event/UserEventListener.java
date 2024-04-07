package mr.mayatech.securedoc.event;

import lombok.RequiredArgsConstructor;
import mr.mayatech.securedoc.service.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventListener {
    private final EmailService emailService;

    @EventListener
    public void onUserEvent(UserEvent event){
        switch (event.getType()) {
            case REGISTRATION -> emailService.sendNewAccountEmail(
                    event.getUser().getFirstname(),
                    event.getUser().getEmail(),
                    (String) event.getData().get("key")
            );

            case RESETPASSWORD -> emailService.sendPasswordResetEmail(
                    event.getUser().getFirstname(),
                    event.getUser().getEmail(),
                    (String) event.getData().get("key")
            );
            default -> {}
        }
    }
}
