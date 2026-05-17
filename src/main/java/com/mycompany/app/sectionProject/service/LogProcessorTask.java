package com.mycompany.app.sectionProject.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import com.mycompany.app.sectionProject.model.LogEntry;
import com.mycompany.app.sectionProject.model.LogSummary;

public class LogProcessorTask implements Callable<LogSummary>{
    private final List<LogEntry> logEntries;

    public LogProcessorTask(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    @Override
    public LogSummary call() throws Exception {

        System.out.println("Tarea: Procesando " + logEntries.size() + " " +
            "entradas de log en hilo " + Thread.currentThread().getName());

        int totalEntries = logEntries.size();

        List<LogEntry> errorLogs = logEntries.stream()
            .filter(error -> error.getStatusCode() >= 400).toList();
        int errorCount = errorLogs.size();

        Set<String> uniqueUsers = logEntries.stream()
            .map(LogEntry::getUser)
            .collect(Collectors.toSet());
        
        // double averageResponseTime = logEntries.stream()
        //     .map(LogEntry::getResponseTimeMs)
        //     .collect(Collectors.averagingInt(Integer::intValue));
        //* Es mejor emplear esta manera, ya que nos devuelve un Optional
        double averageResponseTime = logEntries.stream()
            .mapToInt(LogEntry::getResponseTimeMs)
            .average()
            .orElse(0.0);

        Map<Integer, Long> errorCountsByCode = errorLogs.stream()
            .collect(Collectors.groupingBy(
                LogEntry::getStatusCode,
                Collectors.counting()
            ));

        System.out.println("Finalizando: " + logEntries.size() + " " +
            "entradas de log en hilo " + Thread.currentThread().getName());

        return new LogSummary(totalEntries, errorCount, uniqueUsers, averageResponseTime, errorCountsByCode);
    }

    

    
}
