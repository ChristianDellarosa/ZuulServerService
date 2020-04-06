package com.springboot.app.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);

    @Override
    public String filterType() {
        return "post"; /*Post-Filter*/
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info("Entrando a post filter");

        Long tiempoInicial = (Long) request.getAttribute("tiempoInicio");
        Long tiempoFinal = System.currentTimeMillis();
        Long tiempoTranscurrido = tiempoFinal - tiempoInicial;
        logger.info(String.format("Tiempo transcurrido en segundos %s seg ", (tiempoTranscurrido.doubleValue()/1000.00)));
        logger.info(String.format("Tiempo transcurrido en milisegundos %s ms", tiempoTranscurrido));
        return null;
    }
}
