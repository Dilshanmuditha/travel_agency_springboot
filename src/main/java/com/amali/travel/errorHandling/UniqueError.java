package com.amali.travel.errorHandling;

import org.springframework.dao.DataIntegrityViolationException;

public class UniqueError {
    public static String extractErrorMessage(DataIntegrityViolationException ex) {
        String errorMessage = "Unknown error occurred";
        String message = ex.getMessage();
        if (message.contains("UK_47bvqemyk6vlm0w7crc3opdd4")) {
            errorMessage = "Email already exists";
        } else if (message.contains("UK_dtiqkqtpy0uyer2t3orvl687t")) {
            errorMessage = "Admin Mobile number already exists";
        } else if (message.contains("UK_4vlci9iqxv3qny8m1hrwic7gp")) {
            errorMessage = "Lecture email already exists";
        } else if (message.contains("UK_y1003ley7ruejgpmif8wb76o")) {
            errorMessage = "NIC already exists";
        }
        return errorMessage;
    }
}
