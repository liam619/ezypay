package com.demo.ezypay.service;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.WEEKS;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.demo.ezypay.model.Subplan;
import com.demo.ezypay.model.Subscription;
import com.demo.ezypay.model.Subtype;

@Component
public class AppService {

    public Subscription performSubscription(Subplan plan) {

        var invoices = new ArrayList<LocalDate>();
        var type = Subtype.valueOf(plan.getType());
        var startDate = LocalDate.parse(plan.getStartDate());
        var endDate = LocalDate.parse(plan.getEndDate());
        var maxDate = startDate.plusMonths(3);

        endDate = (endDate.isAfter(maxDate) ? maxDate : endDate);

        switch (type) {
        case DAILY:
            var days = DAYS.between(startDate.minusDays(1), endDate);

            for (int x = 1; x <= days; x++) {
                invoices.add(startDate.plusDays(x));
            }

            break;
        case WEEKLY:

            if (plan.getDayOfWeek() < 1 && plan.getDayOfWeek() > 7) {
                throw new RuntimeException("Invalid Day of Week!");
            } else {
                
                var dow = DayOfWeek.of(plan.getDayOfWeek());
                
                while (startDate.getDayOfWeek() != dow) {
                    startDate = startDate.plusDays(1);
                }
                
                var weeks = WEEKS.between(startDate, endDate);
                
                invoices.add(startDate);
                
                for (int x = 1; x <= weeks; x++) {
                    invoices.add(startDate.plusWeeks(x));
                }
            }

            break;
        case MONTHLY:
            
            startDate = startDate.withDayOfMonth(plan.getDayOfMonth());
            var months = MONTHS.between(startDate, endDate);
            
            invoices.add(startDate);
            
            for (int x = 1; x <= months; x++) {
                invoices.add(startDate.plusMonths(x));
            }
            
            break;
        default:
            throw new RuntimeException("Invalid Subscription Type!");
        }

        var sub = new Subscription();
        sub.setAmount(plan.getAmount());
        sub.setType(type);
        sub.setInvoices(invoices);

        return sub;
    }
}
