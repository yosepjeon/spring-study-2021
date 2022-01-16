package com.yosep.spring.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SpringBootTest
public class AopTest {
    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();
        Assertions.assertEquals(hello.sayHello("Toby"), "Hello Toby");

        Hello proxiedHello = new HelloUppercase(new HelloTarget());
        Assertions.assertEquals(proxiedHello.sayHello("Toby"), "HELLO TOBY");
        Assertions.assertEquals(proxiedHello.sayHi("Toby"), "HI TOBY");
        Assertions.assertEquals(proxiedHello.sayThankYou("Toby"), "THANK YOU TOBY");
    }

    @Test
    public void proxyTest1() {
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[] { Hello.class },
                new UppercaseHandler(new HelloTarget())
        );
    }

    @Test
    public void reflectionTest() throws Exception {
        String name = "Spring";

        Method lengthMethod = String.class.getMethod("length");
        Assertions.assertEquals((Integer) lengthMethod.invoke(name), name.length());

        Method charAtMethod = String.class.getMethod("charAt", int.class);
        Assertions.assertEquals((Character) charAtMethod.invoke(name,0), name.charAt(0));
    }
}
