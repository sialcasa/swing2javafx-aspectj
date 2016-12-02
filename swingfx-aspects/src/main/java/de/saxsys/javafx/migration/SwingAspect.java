package de.saxsys.javafx.migration;

import java.awt.EventQueue;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SwingAspect {

    @Pointcut("call (* javax.swing..*+.*(..)) || " + "call (* javafx.embed.swing.SwingNode.setContent(..))" + " || "
            + "call (javax.swing..*+.new(..))")
    public void swingMethods() {
    }

    @Pointcut("withincode(@UnsafeThreading * *..*(..)) || " + "call (* javafx.embed.swing.JFXPanel*+.*(..))" + " || "
            + "call (* javax.swing..*+.add*Listener(..)) || " + "call (* javax.swing..*+.remove*Listener(..)) || "
            + "call (void javax.swing.JComponent+.setText(java.lang.String)) || "
            + "call (* javax.swing.SwingUtilities.invokeLater(..))")
    public void safeMethods() {
    }

    @Before("swingMethods() && !safeMethods() ")
    public void checkCallingThread(JoinPoint.StaticPart thisJoinPointStatic) {
        if (!EventQueue.isDispatchThread()) {
            System.out.println("You should perform the call on the EDT, otherwise you may get layout issues: "
                    + thisJoinPointStatic);
            Thread.dumpStack();
        }
    }
}