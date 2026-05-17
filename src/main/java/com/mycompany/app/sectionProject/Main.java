package com.mycompany.app.sectionProject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.mycompany.app.sectionProject.model.LogEntry;
import com.mycompany.app.sectionProject.model.LogSummary;
import com.mycompany.app.sectionProject.service.LogProcessorTask;
import com.mycompany.app.sectionProject.service.LogService;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Iniciando análisis de logs...");

        File logsFolder = new File("logs");
        File[] logFiles = logsFolder.listFiles((dir, name) -> name.endsWith(".log"));

        if (logFiles == null || logFiles.length == 0) {
            System.out.println("⚠️ No se encontraron archivos .log en la carpeta 'logs'. Asegúrate de crearla y poner archivos dentro.");
            return;
        }

        LogService service = new LogService();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Future<LogSummary>> futures = new ArrayList<>();

        for(File logFile: logFiles) {
            List<LogEntry> entries = service.readLogsFromFile(logFile.getAbsolutePath());
            LogProcessorTask task = new LogProcessorTask(entries);
            futures.add(executorService.submit(task));
        }

        for (Future<LogSummary> future: futures) {
            try {
                LogSummary summary = future.get();
                System.out.println(summary);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e.getMessage());
            }
        }
        executorService.shutdown();
    }
}
