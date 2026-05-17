package com.mycompany.app.sectionProject.model;

import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogSummary {
    private int totalEntries;
    private int errorCount;
    private Set<String> uniqueUsers;
    private double averageResponseTime;
    private Map<Integer, Long> errorCountsByCode;

    @Override
    public String toString() {
        return "Total: " + totalEntries +
                ", Errores: " + errorCount +
                ", Usuarios unicos: " + uniqueUsers.size() +
                ", Tiempo promedio: " + averageResponseTime + "ms" +
                ", Errores por codigo: " + errorCountsByCode;
    }
}
