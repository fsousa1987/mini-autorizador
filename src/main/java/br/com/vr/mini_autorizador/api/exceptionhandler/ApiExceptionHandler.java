package br.com.vr.mini_autorizador.api.exceptionhandler;

import br.com.vr.mini_autorizador.api.exceptionhandler.enums.TipoProblema;
import br.com.vr.mini_autorizador.api.exceptionhandler.exceptions.CartaoJaExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CartaoJaExistenteException.class)
    public ResponseEntity<?> handleCartaoJaExistenteException(CartaoJaExistenteException ex, WebRequest request) {

        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        var problemType = TipoProblema.RECURSO_JA_EXISTE;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private Problema.ProblemaBuilder createProblemBuilder(HttpStatusCode status, TipoProblema tipoProblema, String detail) {
        return Problema.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .title(tipoProblema.getTitle())
                .detail(detail);
    }

}
