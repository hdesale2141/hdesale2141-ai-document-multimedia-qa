package com.hemant.aidocqa.utils;

import java.io.IOException;

public class VideoAudioExtractorUtil {

    public static String extractAudio(String videoPath, String outputAudioPath) {

        try {

            ProcessBuilder processBuilder = new ProcessBuilder(
                    "ffmpeg",
                    "-i",
                    videoPath,
                    "-q:a",
                    "0",
                    "-map",
                    "a",
                    outputAudioPath
            );

            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            process.waitFor();

            return outputAudioPath;

        } catch (IOException | InterruptedException e) {

            throw new RuntimeException(
                    "Audio extraction failed: " + e.getMessage()
            );
        }
    }
}