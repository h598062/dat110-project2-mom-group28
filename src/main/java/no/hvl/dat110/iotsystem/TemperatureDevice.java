package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		System.out.println("Temperature device starting ... ");


		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();


		// create a client object and use it to
		// - connect to the broker - user "sensor" as the user name
		// - publish the temperature(s)
		// - disconnect from the broker

		Client client = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);
		client.connect();
		client.createTopic(Common.TEMPTOPIC);
		for (int i = 0; i < COUNT; i++) {
			client.publish(Common.TEMPTOPIC, "Temp: " + sn.read());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		client.disconnect();



		System.out.println("Temperature device stopping ... ");
	}
}
