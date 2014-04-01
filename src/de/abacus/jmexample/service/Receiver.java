package de.abacus.jmexample.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import de.abacus.jmsexample.domain.Customer;

public class Receiver {

	private Customer customer;

	public Customer receiveMessage() throws JMSException {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");

		Message receivedMessage = jmsTemplate.receive("CustomerQueue");
		ObjectMessage msg = (ObjectMessage) receivedMessage;

		customer = (Customer) msg.getObject();

		return customer;
	}

}
