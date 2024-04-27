package com.vmaffioli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.vmaffioli.enums.TypingEnum;
import com.vmaffioli.robot.RobotTyping;

public class Main implements NativeKeyListener {

    private boolean loopRunning;
    private Thread loopThread;

    private RobotTyping robot;

    public Main() {
        this.robot = new RobotTyping();
        loopRunning = false;
        loopThread = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Force Paste");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(new BorderLayout());
            frame.setResizable(false); 

            JPanel executionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel bottomPanel = new JPanel(new BorderLayout());
            JPanel buttonPanel = new JPanel(new FlowLayout());

            JButton startButton = new JButton("Iniciar");
            JButton stopButton = new JButton("Parar");
            JButton padraoButton = new JButton("Padrão");
            JButton acessibilidadeButton = new JButton("Acessibilidade");
            JButton helpButton = new JButton("Ajuda");

            padraoButton.setEnabled(false);

            Main main = new Main();

            Dimension buttonSize = new Dimension(200, 30);
            startButton.setPreferredSize(buttonSize);
            stopButton.setPreferredSize(buttonSize);
            padraoButton.setPreferredSize(buttonSize);
            acessibilidadeButton.setPreferredSize(buttonSize);
            helpButton.setPreferredSize(new Dimension(20, 30));

            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    main.startLoop();
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                }
            });

            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    main.stopLoop();
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                }
            });

            padraoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    main.robot.setMode(TypingEnum.DEFAULT);
                    acessibilidadeButton.setEnabled(true);
                    padraoButton.setEnabled(false);
                }
            });

            acessibilidadeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    main.robot.setMode(TypingEnum.ACCESSIBILITY);
                    acessibilidadeButton.setEnabled(false);
                    padraoButton.setEnabled(true);
                }
            });

            helpButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Essa é a janela de ajuda!", "Ajuda",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });

            executionPanel.add(startButton);
            executionPanel.add(stopButton);

            buttonPanel.add(padraoButton);
            buttonPanel.add(acessibilidadeButton);

            bottomPanel.add(buttonPanel, BorderLayout.CENTER);
            bottomPanel.add(helpButton, BorderLayout.SOUTH);

            frame.getContentPane().add(executionPanel, BorderLayout.NORTH);
            frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
            frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

            frame.pack();
            frame.setVisible(true);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    main.stopLoop();
                }
            });
        });
    }

    public void startLoop() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("Erro ao registrar o hook nativo: " + ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(this);
        loopRunning = true;
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                runLoop();
                return null;
            }
        };
        worker.execute();
    }

    public void stopLoop() {
        loopRunning = false;
        try {
            GlobalScreen.removeNativeKeyListener(this);
            GlobalScreen.unregisterNativeHook();
            if (loopThread != null) {
                loopThread.join();
            }
        } catch (InterruptedException | NativeHookException ex) {
            System.err.println("Erro ao parar o loop: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void runLoop() {
        while (loopRunning) {
            // Mantém o programa rodando
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getModifiers() == (NativeKeyEvent.CTRL_L_MASK | NativeKeyEvent.SHIFT_L_MASK)
                && e.getKeyCode() == NativeKeyEvent.VC_F) {
            String text = getClipboardText();
            if (text != null && !text.isEmpty()) {
                try {
                    Thread.sleep(1000);
                    this.robot.execute(text);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        // Não faz nada aqui
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // Não faz nada aqui
    }

    private String getClipboardText() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            return (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
