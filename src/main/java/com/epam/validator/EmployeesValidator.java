package com.epam.validator;

import com.epam.entity.CrewMan;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeesValidator {
    private static final Logger LOG = Logger.getLogger(EmployeesValidator.class);

    public static ArrayList<String> validateEmployee(CrewMan man) {
        ArrayList<String> list = new ArrayList<>();
        if (man.getFirstName().isEmpty()) {
            list.add("Name cannot be empty");
        }
        Pattern namePattern = Pattern.compile("[A-ZА-Я]{1}[a-zа-я]+");
        LOG.trace("Name" + man.getFirstName());
        Matcher m = namePattern.matcher(man.getFirstName());
        if (!m.find()) {
            list.add("Name must be\"John, Bob, Susan\"");
        }
        LOG.trace("Last Name" + man.getLastName());
        m = namePattern.matcher(man.getLastName());
        if (!m.find()) {
            list.add("SurName must be\"Smith, Snow, Barateon\"");
        }
        if (man.getAge() < 18 && man.getAge() > 100) {
            list.add("Age must be from 18 to 100");
        }
        return list;
    }
}
