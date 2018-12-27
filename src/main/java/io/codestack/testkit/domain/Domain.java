package io.codestack.testkit.domain;

import io.codestack.testkit.domain.Method;
import io.codestack.testkit.domain.Result;

import java.util.List;

public class Domain {
    private String id;
    private List<Method> methods;
    private Result result;

    private Integer age;
    private String hello;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
