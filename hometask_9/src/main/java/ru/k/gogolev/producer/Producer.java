package ru.k.gogolev.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    private static final String EXCHANGE_NAME = "hometask_9_exchanger";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            String routingKey1 = "java";
            String routingKey2 = "php";
            String routingKey3 = "c++";

            String message1 = "message123_"+routingKey1;
            String message2 = "message123_"+routingKey2;
            String message3 = "message123_"+routingKey3;

            while (true){
                Thread.sleep(5000);
                channel.basicPublish(EXCHANGE_NAME, routingKey1, null, message1.getBytes("UTF-8"));
                channel.basicPublish(EXCHANGE_NAME, routingKey2, null, message2.getBytes("UTF-8"));
                channel.basicPublish(EXCHANGE_NAME, routingKey3, null, message3.getBytes("UTF-8"));

                System.out.println(" [x] Sent '" + routingKey1 + "':'" + message1 + "'");
                System.out.println(" [x] Sent '" + routingKey2 + "':'" + message2 + "'");
                System.out.println(" [x] Sent '" + routingKey3 + "':'" + message3 + "'");
            }
        }
    }
}
