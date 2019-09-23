package com.cryptape.cita.console;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.cryptape.cita.codegen.Console;
import com.cryptape.cita.crypto.CipherException;
import com.cryptape.cita.crypto.Credentials;
import com.cryptape.cita.crypto.WalletUtils;

import static com.cryptape.cita.codegen.Console.exitError;

/**
 * Common functions used by the wallet console tools.
 */
abstract class WalletManager {

    final IODevice console;

    WalletManager() {
        console = new ConsoleDevice();

        if (console == null) {
            Console.exitError("Unable to access console - please ensure you are running "
                    + "from the command line");
        }
    }

    WalletManager(IODevice console) {
        this.console = console;
    }

    String getPassword(String initialPrompt) {
        while (true) {
            char[] input1 = console.readPassword(initialPrompt);
            char[] input2 = console.readPassword("Please re-enter the password: ");

            if (Arrays.equals(input1, input2)) {
                return new String(input1);
            } else {
                console.printf("Sorry, passwords did not match\n");
            }
        }
    }

    String getDestinationDir() {
        String defaultDir = WalletUtils.getTestnetKeyDirectory();
        String destinationDir = console.readLine(
                "Please enter a destination directory location [" + defaultDir + "]: ");
        if (destinationDir.equals("")) {
            return defaultDir;
        } else if (destinationDir.startsWith("~")) {
            return System.getProperty("user.home") + destinationDir.substring(1);
        } else {
            return destinationDir;
        }
    }

    File createDir(String destinationDir) {
        File destination = new File(destinationDir);

        if (!destination.exists()) {
            console.printf("Creating directory: " + destinationDir + " ...");
            if (!destination.mkdirs()) {
                Console.exitError("Unable to create destination directory ["
                        + destinationDir + "], exiting...");
            } else {
                console.printf("complete\n");
            }
        }

        return destination;
    }

    Credentials getCredentials(File walletFile) {
        if (!walletFile.exists() || !walletFile.isFile()) {
            Console.exitError("Unable to read wallet file: " + walletFile);
        }
        return loadWalletFile(walletFile);
    }

    private Credentials loadWalletFile(File walletFile) {
        while (true) {
            char[] password = console.readPassword(
                    "Please enter your existing wallet file password: ");
            String currentPassword = new String(password);
            try {
                return WalletUtils.loadCredentials(currentPassword, walletFile);
            } catch (CipherException e) {
                console.printf("Invalid password specified\n");
            } catch (IOException e) {
                Console.exitError("Unable to load wallet file: " + walletFile + "\n" + e.getMessage());
            }
        }
    }
}