package mr.mayatech.securedoc.utils;

import jakarta.servlet.http.HttpServletRequest;
import mr.mayatech.securedoc.domain.Response;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static java.time.LocalDateTime.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class RequestUtils {

    public static Response getResponse(HttpServletRequest request, Map<?, ?> data, String message, HttpStatus status){
        return new Response(
                now().toString(),
                status.value(),
                request.getRequestURI(),
                HttpStatus.valueOf(status.value()),
                message,
                EMPTY,
                data
        );
    }
}
