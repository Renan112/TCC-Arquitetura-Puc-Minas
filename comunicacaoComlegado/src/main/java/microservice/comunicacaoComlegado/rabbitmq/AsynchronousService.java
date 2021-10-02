package microservice.comunicacaoComlegado.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import microservice.comunicacaoComlegado.servicos.AutorizacaoExameConsumidor;

 
@Service
public class AsynchronousService {
 
    @Autowired
    private TaskExecutor taskExecutor;
 
    @Autowired
    private ApplicationContext applicationContext;
 
    @EventListener(ApplicationReadyEvent.class)
    public void executeAsynchronously() throws Exception {
    	AutorizacaoExameConsumidor consumidor1 = applicationContext.getBean(AutorizacaoExameConsumidor.class);
    	consumidor1.criarFila();
        taskExecutor.execute(consumidor1);
        
    }
 
}