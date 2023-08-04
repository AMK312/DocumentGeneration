package org.nisum.document;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class HtmlToPdfConverter {

    public static void main(String[] args) {
        // Replace with the actual paths to your HTML and PDF files
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String inputHtmlFile = "croTemplate.html";
        String outputPdfFile = "PDF_DOCUMENT.pdf";

        convertHtmlToPdf(inputHtmlFile, outputPdfFile);
    }

    public static void convertHtmlToPdf(String inputHtmlFile, String outputPdfFile) {
        try {
            // Command to run wkhtmltopdf from the command line
            String[] command = {"wkhtmltopdf", inputHtmlFile, outputPdfFile};

            // Create a ProcessBuilder to execute the command
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Redirect any error output to a log file (optional)
            File logFile = new File("error_log.txt");
            processBuilder.redirectError(logFile);

            // Start the process and wait for it to complete
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            // Check if the process exited successfully
            if (exitCode == 0) {
                System.out.println("HTML to PDF conversion successful!");
            } else {
                System.out.println("HTML to PDF conversion failed. Check the error log for details.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
