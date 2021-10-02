package microservice.associados.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import microservice.associados.servicos.AssociadoConsumidor;

 
@Service
public class AsynchronousService {
 
    @Autowired
    private TaskExecutor taskExecutor;
 
    @Autowired
    private ApplicationContext applicationContext;
 
    @EventListener(ApplicationReadyEvent.class)
    public void executeAsynchronously() throws Exception {
    	AssociadoConsumidor consumidor1 = applicationContext.getBean(AssociadoConsumidor.class);
    	consumidor1.criarFila();
        taskExecutor.execute(consumidor1);
        
    }
 
}