package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Jedis jedis = new Jedis("redis://default:JEyBxMIh8PSxSYs5P9BA4H7hom5G10Ce@redis-18408.c10.us-east-1-4.ec2.cloud.redislabs.com:18408");
        
        jedis.hset("tarefa", "1", "Limpar janelas");
        jedis.hset("tarefa", "2", "Levar os cachorros para passear");
        jedis.hset("tarefa", "3", "Dar banho nos bichos");
        System.out.println("Tarefas adicionadas com sucesso.");

        System.out.println("\nLista de Tarefas:");
        jedis.hgetAll("tarefa").forEach((id, value) -> System.out.println(id + ": " + value));

        jedis.hset("tarefas_concluidas", "1", jedis.hget("tarefa", "1"));
        jedis.hdel("tarefa", "1");
        System.out.println("\nTarefa 1 marcada como concluída.");

        jedis.hdel("tarefa", "2");
        System.out.println("\nTarefa 2 removida.");

        jedis.close();
    }
}