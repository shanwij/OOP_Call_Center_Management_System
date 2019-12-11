package project.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueueHandler {
	public static Map<String, List<String>> queue;
	
	public static void enqueueAgent(String agentCode) {
		if (queue == null)
			queue = new HashMap<>();
		List<String> customers;
		if ((customers = queue.get(agentCode)) == null)
			customers = new ArrayList<>();
		queue.put(agentCode, customers);
		System.out.println(queue);
	}
	
	public static void dequeueAgent(String agentCode) {
		if (queue != null) {
			List<String> customers = queue.get(agentCode);
			for (String customer : customers)
				enqueueCustomer(customer);
			queue.remove(agentCode);
		}
		System.out.println(queue);
	}
	
	public static boolean enqueueCustomer(String customerCode) {
		dequeueCustomer(customerCode);
		boolean result = false;
		if (queue != null && !queue.isEmpty()) {
			List<String> keySet = new ArrayList<>();
			List<Integer> queueLength = new ArrayList<>();
			keySet.addAll(queue.keySet());
			for (String key : keySet) {
				queueLength.add(queue.get(key).size());
			}
			String keyMin = keySet.get(queueLength.indexOf(Collections.min(queueLength)));
			List<String> customers = queue.get(keyMin);
			customers.add(customerCode);
			queue.put(keyMin, customers);
			result = true;
		}
		System.out.println(queue);
		return result;
	}
	
	public static void dequeueCustomer(String customerCode) {
		System.out.println(queue != null && !queue.isEmpty());
		if (queue != null && !queue.isEmpty()) {
			List<String> keySet = new ArrayList<>();
			keySet.addAll(queue.keySet());
			for (String key : keySet) {
				List<String> customers = queue.get(key);
				if (customers.indexOf(customerCode) != -1) {
					customers.remove(customerCode);
					break;
				}
			}
		}
		System.out.println(queue);
	}
	
	public static boolean isInQueue(String customerCode) {
		boolean result = false;
		if (queue != null && !queue.isEmpty()) {
			List<String> keySet = new ArrayList<>();
			keySet.addAll(queue.keySet());
			for (String key : keySet) {
				List<String> customers = queue.get(key);
				if (customers.indexOf(customerCode) != -1) {
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	public static String getNextCustomer(String agentCode) {
		String result = null;
		if (queue != null && !queue.isEmpty()) {
			List<String> customers = queue.get(agentCode);
			if (customers != null && !customers.isEmpty()) {
				result = customers.get(0);
			}
		}
		return result;
	}
}
