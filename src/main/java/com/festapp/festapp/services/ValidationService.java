package com.festapp.festapp.services;

import java.util.List;

public interface ValidationService {
    List<String> getValidationErrors(Object dto);
}
