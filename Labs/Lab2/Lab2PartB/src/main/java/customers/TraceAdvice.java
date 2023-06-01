package customers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class TraceAdvice {

    private ILogger logger;

    @Autowired
    public TraceAdvice(ILogger logger) {
        this.logger = logger;
    }

    @After("execution(* customers.EmailSender.sendEmail(..))")
    public void afterSendEmail(JoinPoint joinpoint) {
        logger.log("method="+joinpoint.getSignature().getName());
    }

    @After("execution(* customers.EmailSender.sendEmail(..))")
    public void afterSendEmailGetArgs(JoinPoint joinpoint) {
        Object[] signatureArgs = joinpoint.getArgs();
        String email = signatureArgs[0].toString();
        String message = signatureArgs[1].toString();
        logger.log("method="+joinpoint.getSignature().getName()
                + " address="+email
                + " message= " + message);
    }

    @After("execution(* customers.EmailSender.sendEmail(..))")
    public void afterSendEmailGetMailServer(JoinPoint joinpoint) {
        Object[] signatureArgs = joinpoint.getArgs();
        String email = signatureArgs[0].toString();
        String message = signatureArgs[1].toString();
        EmailSender emailSender = (EmailSender)joinpoint.getTarget();
        logger.log("method="+joinpoint.getSignature().getName()
                + " address="+email
                + " message= " + message + " outgoing mail server = " + emailSender.getOutgoingMailServer());
    }

    @Around("execution(* customers.CustomerDAO.*(..))")
    public Object invoke(ProceedingJoinPoint call ) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();

        long totaltime = sw.getLastTaskTimeMillis();
        // print the time to the console
       logger.log(sw.prettyPrint());

        return retVal;
    }
}
