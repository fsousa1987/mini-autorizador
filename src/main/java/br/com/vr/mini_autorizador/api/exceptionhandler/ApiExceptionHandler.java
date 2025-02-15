package br.com.vr.mini_autorizador.api.exceptionhandler;

import br.com.vr.mini_autorizador.api.exceptionhandler.enums.TipoProblema;
import br.com.vr.mini_autorizador.api.exceptionhandler.exceptions.CartaoNaoEncontradoException;
import br.com.vr.mini_autorizador.api.exceptionhandler.exceptions.SaldoInsuficienteException;
import br.com.vr.mini_autorizador.api.exceptionhandler.exceptions.SenhaInvalidaException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {

        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    @ExceptionHandler(CartaoNaoEncontradoException.class)
    public ResponseEntity<?> handleCartaoNaoEncontradoException(CartaoNaoEncontradoException ex, WebRequest request) {

        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<?> handleSenhaInvalidaException(SenhaInvalidaException ex, WebRequest request) {

        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<?> handleSaldoInsuficienteException(SaldoInsuficienteException ex, WebRequest request) {

        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private Problema.ProblemaBuilder createProblemBuilder(HttpStatusCode status, TipoProblema tipoProblema, String detail) {
        return Problema.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .titulo(tipoProblema.getTitle())
                .detalhe(detail);
    }

    private Problema.ProblemaBuilder createProblemBuilder(String detail) {
        return Problema.builder()
                .detalhe(detail);
    }

    private ResponseEntity<Object> handleValidationInternal(
            Exception ex, BindingResult bindingResult, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var problemType = TipoProblema.DADOS_INVALIDOS;
        var detail = "Um ou mais campos estão inválidos. Preencha corretamente e tente outra vez";

        var problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    var message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    var name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return Problema.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        var problem = createProblemBuilder(status, problemType, detail)
                .objetos(problemObjects)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

}
