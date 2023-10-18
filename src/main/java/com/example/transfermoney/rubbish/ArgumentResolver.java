package com.example.transfermoney.rubbish;

import com.example.transfermoney.model.Amount;
import com.example.transfermoney.model.PayInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
@RequiredArgsConstructor // TODO Пока не знаю для чего добавил его(
public class ArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(PayInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String cardFromNumber = webRequest.getParameter("cardFromNumber");
        String cardFromValidTill = webRequest.getParameter("cardFromValidTill");
        String cardFromCVV = webRequest.getParameter("cardFromCVV");
        String cardToNumber = webRequest.getParameter("cardToNumber");

        return new PayInfo(cardFromNumber, cardFromValidTill, cardFromCVV, cardToNumber,null);
    }
}