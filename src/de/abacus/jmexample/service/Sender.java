package de.abacus.jmexample.service;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import de.abacus.jmsexample.domain.Customer;

public class Sender {

	public void sendMessage(final Customer customer) {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");

		jmsTemplate.send(
				
				new MessageCreator() {
					
					@Override
					public ObjectMessage createMessage(Session session)
							throws JMSException {

						ObjectMessage message = session.createObjectMessage();
						message.setObject(customer);

						return message;
					}
				});
	}
}