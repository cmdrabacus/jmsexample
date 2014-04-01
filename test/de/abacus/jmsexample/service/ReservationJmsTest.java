package de.abacus.jmsexample.service;

import java.util.Random;

import javax.jms.JMSException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.abacus.jmexample.service.Receiver;
import de.abacus.jmexample.service.Sender;
import de.abacus.jmsexample.domain.Customer;

@SuppressWarnings("deprecation")
public class ReservationJmsTest {
	private Receiver receiver;
	private Sender sender;
	private Customer customer;

	private Random rnd;

	@Before
	public void setUp() throws Exception {

		@SuppressWarnings({ "resource", "unused" })
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		sender = new Sender();
		
		customer = new Customer();
		customer.setName("Abacus");
		customer.setCustomerId(generateRnd());

		sender.sendMessage(customer);

	}

	@After
	public void tearDownAfterClass() throws Exception {
	}

	@Test
	public void isObjectDelivered() throws JMSException {

		receiver = new Receiver();
		customer = receiver.receiveMessage();

		Assert.assertNotNull(customer);
		
		System.out.println("TESTEST");

	}

	public int generateRnd() {
		rnd = new Random();
		int min = 1;
		int max = 1000;
		int randomNum = rnd.nextInt((max - min) + 1) + min;
		return randomNum;

	}

}
