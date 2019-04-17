package com.packt.javanlp.cookbook.chapter10;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

public class GeneratingRandomNumbers {

	public static void main(String[] args) {
		// Generating random numbers
		Random random = new Random();
		System.out.println("Random Integer Numbers");
		for (int i = 0; i < 5; i++) {
			System.out.println(random.nextInt());
		}

		System.out.println("Random Numbers from 0 (Inclusive) to 500 (Exclusive)");
		for (int i = 0; i < 5; i++) {
			System.out.println(random.nextInt(500));
		}
		System.out.println("Random Numbers from 10 (Inclusive) to 20 (Exclusive)");
		for (int i = 0; i < 5; i++) {
			System.out.println(random.nextInt(10) + 10);
		}

		System.out.println("Random double: " + Math.random());

		System.out.println(ThreadLocalRandom.current().nextInt());

		System.out.println(ThreadLocalRandom.current().nextDouble(10.0, 20.0));

		random = new Random();
		System.out.println("Random Integer Numbers Using a Stream");
		for (int i = 0; i < 5; i++) {
			System.out.println(random.ints(10, 20).findFirst().getAsInt());
		}

		System.out.println("Random Integer Numbers Using a Stream");
		random.ints(5, 10, 20).forEach(num -> System.out.println(num));

		UniformRandomProvider uniformRandomProvider = RandomSource.create(RandomSource.KISS);
		System.out.println(uniformRandomProvider.nextInt(500));

	}

}
