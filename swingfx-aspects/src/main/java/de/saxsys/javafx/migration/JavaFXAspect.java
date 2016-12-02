package de.saxsys.javafx.migration;

import java.awt.EventQueue;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javafx.application.Platform;

@Aspect
public class JavaFXAspect {

    @Pointcut("call ( * javafx..*+.*(..)) || " + "call (javafx..*+.new(..))")
    public void javafxMethods() {
    }

    @Pointcut("withincode(@UnsafeThreading * *..*(..)) || " + "call (javafx.embed.swing.JFXPanel*+.new(..))" + " || "
            + "call (* javafx.embed.swing.JFXPanel*+.*(..))" + " || "
            + "call (javafx.embed.swing.SwingNode*+.new(..)) || "
            + "call (* javafx.application.Platform.runLater(..)) || "
            + "call (* javafx.application.Application.launch(..)) || " + "call (* javafx.embed.swing..*+.*(..)) || "
            + "call (* javafx.application.Platform.isFxApplicationThread())")
    public void safeMethods() {
    }

    @Before("javafxMethods() && !safeMethods()")
    public void checkCallingThread(JoinPoint thisJoinPoint) {

        if (!Platform.isFxApplicationThread()) {
            System.out.println("Warn - possible JavaFX Thread Violation: " + thisJoinPoint + " in EDT:"
                    + EventQueue.isDispatchThread());
            Thread.dumpStack();
        }
    }

}