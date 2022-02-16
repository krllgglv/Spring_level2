package ru.k.gogolev.consumer;

import com.rabbitmq.client.*;

import java.util.Scanner;

public class Consumer {
    private static final String EXCHANGE_NAME = "hometask_9_exchanger";

    public static void main(String[] argv) throws Exception {
        String userInput = "";
        String routingKey = "";
        Scanner scanner = new Scanner(System.in);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String queueName = channel.queueDeclare().getQueue();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");

        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
        // доступные темы подписки java, php, c++
        //set_topic php
        //sunsubscribe php
        System.out.println("введите команду");

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("exit")) {
                System.exit(0);
            }
            if (userInput.split(" ", 2)[0].equals("set_topic")) {
                routingKey = userInput.split(" ", 2)[1];
                channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
            }
            if (userInput.split(" ", 2)[0].equals("unsubscribe")) {
                routingKey = userInput.split(" ", 2)[1];
                channel.queueUnbind(queueName, EXCHANGE_NAME, routingKey);
            }
            routingKey = "";

        }
    }
}
