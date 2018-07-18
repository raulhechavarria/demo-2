package example.demo.hacker.rank.services.impl;

import org.springframework.stereotype.Service;

import example.demo.domain.MaxMin;

@Service
public class MiniMaxSum {

	static void miniMaxSum(int[] arr) {
		 Long max =  new Long(0L);
		 		Long min = new Long(1000000000L);
		 		for (int i = 0; i < arr.length; i++) {
		 			 Long sum =  new Long(0L);
		 			for (int j = 0; j < arr.length; j++) {
		 				if (j != i) {
		 					sum += arr[j];					
		 				}
		 			}
		 			if (max < sum ) {
		 				max = sum;
		 			}
		 			if (min > sum) {
		 				min = sum;
		 			}
		 		}
		 		System.out.println(min +" " + max);
		 	}

	public MaxMin maxMin(int[] arr) {
		miniMaxSum(arr);
		return null;
	}

}
