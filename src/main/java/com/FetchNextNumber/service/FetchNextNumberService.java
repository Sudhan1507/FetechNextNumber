package com.FetchNextNumber.service;

import com.FetchNextNumber.dao.FetchNextNumberDao;
import com.FetchNextNumber.entity.FetchNextNumberRequest;
import com.FetchNextNumber.entity.FetchNextNumberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchNextNumberService {

    private final FetchNextNumberDao fetchNextNumberDao;

    @Autowired
    public FetchNextNumberService(FetchNextNumberDao fetchNextNumberDAO) {
        this.fetchNextNumberDao = fetchNextNumberDAO;
    }

    public FetchNextNumberResponse fetchNextNumber(FetchNextNumberRequest request) {
        // Call the DAO to fetch and update the number
        int oldValue = fetchNextNumberDao.fetchNumber(request.getCategoryCode());
        int newValue = generateNextNumber(oldValue);
        fetchNextNumberDao.updateNumber(request.getCategoryCode(), newValue);
        return new FetchNextNumberResponse(oldValue, newValue);
    }

    public int generateNextNumber(int currentValue) {
        // Convert the current value to a string to manipulate its digits
        String currentValueString = String.valueOf(currentValue);

        // Loop until a valid next number is found
        int nextNumber = currentValue;
        boolean isValidNextNumber = false;
        while (!isValidNextNumber) {
            nextNumber++;

            // Check if the sum of the digits equals 1
            int sumOfDigits = calculateSumOfDigits(nextNumber);
            if (sumOfDigits == 1) {
                isValidNextNumber = true;
            }
        }

        return nextNumber;
    }

    private int calculateSumOfDigits(int number) {
        int sum = 0;

        // Convert the number to a string to iterate through its digits
        String numberString = String.valueOf(number);
        for (int i = 0; i < numberString.length(); i++) {
            // Convert each digit back to an integer and add it to the sum
            int digit = Integer.parseInt(String.valueOf(numberString.charAt(i)));
            sum += digit;
        }

        return sum;
    }

}
