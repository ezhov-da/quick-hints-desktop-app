package ru.ezhov.note.ui.terminal;

import com.redpois0n.terminal.InputListener;
import com.redpois0n.terminal.JTerminal;
import ru.ezhov.note.ui.TextAreaWithText;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class TerminalPanel extends JPanel {
    private Process p;
    private JTerminal terminal;
    private JTextArea textArea = new TextAreaWithText("Input command...");
    private String shellCommand;

    public TerminalPanel(String shellCommand) {
        super(new BorderLayout());

        this.shellCommand = shellCommand;

        terminal = new JTerminal();

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(terminal);

        terminal.addInputListener(new InputListener() {
            @Override
            public void processCommand(JTerminal terminal, char c) {
//				append(c);
            }

            @Override
            public void onTerminate(JTerminal terminal) {
                try {
                    if (p != null) {
                        p.destroy();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        textArea.setFont(new Font("monospaced", Font.PLAIN, 14));

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    startShell(textArea.getText());
                }
            }
        });

        this.addKeyListener(terminal.getKeyListener());
        this.add(textArea, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        JLabel label = new JLabel("Shell: " + shellCommand);

        this.add(label, BorderLayout.SOUTH);
    }

    public void setCommandAndExecute(String command) {
        SwingUtilities.invokeLater(() -> {
            textArea.setText(command);
            startShell(command);
        });
    }

    private void startShell(String command) {
        PrintWriter writer;

        try {
            ProcessBuilder builder = new ProcessBuilder(shellCommand);
            builder.redirectErrorStream(true);
            p = builder.start();
            writer = new PrintWriter(p.getOutputStream(), true);
            writer.println(command);

            new Thread(() -> {
                try {
                    while (true) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line;

                        while ((line = reader.readLine()) != null) {
                            terminal.append(line + "\n");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
