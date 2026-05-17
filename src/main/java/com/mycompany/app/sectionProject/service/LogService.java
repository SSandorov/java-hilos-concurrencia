package com.mycompany.app.sectionProject.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import com.mycompany.app.sectionProject.model.LogEntry;
import com.mycompany.app.sectionProject.util.LogParser;

public class LogService {
    public List<LogEntry> readLogsFromFile(String filePath) {
        try {
            return Files.lines(Path.of(filePath))
                .map(LogParser::parseLine)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return List.of();
        }
    }
}
