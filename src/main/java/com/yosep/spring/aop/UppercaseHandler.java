package com.yosep.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UppercaseHandler implements InvocationHandler {
    Object target;

    public UppercaseHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = method.invoke(target, args);

        // 호출한 메서드의 리턴 타입이 String인 경우만 대문자 변경 기능을
        // 적용하도록 수정
        if(ret instanceof String) {
            return ((String) ret).toUpperCase();
        }else {
            return ret;
        }
    }
}
