package xyz.codestack.testkit.controller;

import com.alibaba.fastjson.JSON;
import xyz.codestack.testkit.domain.Domain;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class Controller {

    @RequestMapping("/getAllUrl")
    @ResponseBody
    public String getAllUrl(HttpServletRequest request) {
        Set<String> result = new HashSet<String>();
        Map<String, Object> map = new HashMap<>();
        WebApplicationContext wc = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            PatternsRequestCondition pc = rmi.getPatternsCondition();
            Set<String> pSet = pc.getPatterns();
            result.addAll(pSet);
            map.put(rmi.getName(), rmi.getPatternsCondition().getPatterns());


            //
            HandlerMethod handlerMethod = handlerMethods.get(rmi);
            MethodParameter returnType = handlerMethod.getReturnType();
//            result.add(JSON.toJSONString(returnType));
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            for (MethodParameter methodParameter : methodParameters) {
                result.add(methodParameter.getParameter().getName());
                map.put(methodParameter.getParameterName(), methodParameter.getParameter().getName());
            }
            MethodParameter rt = handlerMethod.getReturnType();
//            result.add(JSON.toJSONString(rt));

        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/getHello")
    public String getHello(Integer age, String Hi) {
        return "hello";
    }

    @RequestMapping("/getWorld")
    public String getWorld(Domain domain) {
        return "world";
    }


}
