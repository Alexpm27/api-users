package com.example.securitywithjwt.persistence.exceptions;

public class NotFoundException extends RuntimeException{
        public NotFoundException() {
            super("Data Not Found");
        }

}
