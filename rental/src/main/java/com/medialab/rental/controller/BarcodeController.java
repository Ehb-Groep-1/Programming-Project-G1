package com.medialab.rental.controller;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.medialab.rental.util.BarcodeScanner;
import com.medialab.rental.util.QRCodeGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/barcode")
public class BarcodeController {

    @PostMapping("/scan")
    public ResponseEntity<String> scanBarcode(@RequestParam("file") MultipartFile file) {
        try {
            File tempFile = File.createTempFile("barcode", ".png");
            file.transferTo(tempFile);

            String decodedText = BarcodeScanner.decodeBarcode(tempFile);
            tempFile.delete();

            return ResponseEntity.ok(decodedText);
        } catch (IOException | NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed: " + e.getMessage());
        }
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateQRCode(@RequestParam("text") String text) {
        try {
            String filePath = "QRCode.png";
            QRCodeGenerator.generateQRCode(text, 300, 300, filePath);
            return ResponseEntity.ok("QR Code generated success: " + filePath); // Pas op ben op filepath, ben op mac als jullie op windows zijn.
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate QR code: " + e.getMessage());
        }
    }
}
